package net.canang.cfi.biz.jm.manager.workflow;

import net.canang.cfi.biz.Util;
import net.canang.cfi.biz.event.AccessEvent;
import net.canang.cfi.biz.integration.activiti.WorkflowSupport;
import net.canang.cfi.biz.integration.springacl.CfAclPermission;
import net.canang.cfi.core.jm.dao.CfJournalDao;
import net.canang.cfi.core.jm.model.CfJournal;
import net.canang.cfi.core.jm.model.CfJournalType;
import net.canang.cfi.core.jm.model.CfManualJournal;
import net.canang.cfi.core.so.dao.CfPrincipalDao;
import net.canang.cfi.core.so.model.impl.CfDocumentImpl;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
@Transactional
@Service("journalWorkfLow")
public class JournalWorkflowImpl extends WorkflowSupport implements JournalWorkflow {

    private Logger log = LoggerFactory.getLogger(JournalWorkflowImpl.class);

    private static final String DOCUMENT_ID = "documentId";
    public static final String DOCUMENT_CLASS = "documentClass";
    public static final String DOCUMENT_REFERENCE_NO = "referenceNo";
    private static final String PROCESS_DEF = "jm_manual_journal_workflow";
    private static final String PROCESS_BPMN = "net/canang/cfi/biz/jm/workflow/manual_journal_workflow.bpmn20.xml";


    @Autowired
    private ApplicationContext context;

    @Autowired
    protected SessionFactory sessionFactory;

    @Autowired
    protected CfJournalDao journalDao;

    @Autowired
    protected CfPrincipalDao principalDao;

    @Autowired
    protected ProcessEngine processEngine;

    @Autowired
    protected RuntimeService runtimeService;

    @Autowired
    protected TaskService taskService;

    @Autowired
    protected IdentityService identityService;

    @Autowired
    protected HistoryService historyService;

    @Autowired
    protected RepositoryService repositoryService;

    @PostConstruct
    public void init() {
        if (!isProcessDefExists(PROCESS_DEF))
            deploy(PROCESS_DEF, PROCESS_BPMN);
    }

    @Override
    public void process(CfJournal journal) {
        journalDao.save(journal, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().refresh(journal);
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(PROCESS_DEF, toVariables(journal));

        context.publishEvent(new AccessEvent(journal, Util.getCurrentUser(), CfAclPermission.VIEW));


    }

    public List<JournalTask> findTasks(CfJournalType journalType, Integer offset, Integer limit) {
        String prefix = CfJournal.class.getCanonicalName() + ":" + journalType.name() + ":" + "%";
        log.debug("prefix: " + prefix);
        TaskQuery taskQuery = taskService.createTaskQuery();
        taskQuery.taskNameLike(prefix);
        taskQuery.orderByTaskCreateTime();
        taskQuery.desc();
        List<Task> list = taskQuery.listPage(offset, limit);
        return toDocumentTasks(list);
    }

    @Override
    public void assignTask(JournalTask task) {
        assignTask((Task) task);
    }

    @Override
    public void assignTask(JournalTask task, String username) {
        assignTask((Task) task, username);
    }

    @Override
    public void claimTask(JournalTask task) {
        claimTask((Task) task);
    }

    @Override
    public void releaseTask(JournalTask task) {
        releaseTask((Task) task);
    }

    @Override
    public void stealTask(JournalTask task) {
        stealTask((Task) task);
    }

    @Override
    public void completeTask(JournalTask task) {
        completeTask((Task) task);
    }

    @Override
    public void completeTask(JournalTask task, Map<String, Object> variables) {
        completeTask((Task) task, variables);
    }

    private List<JournalTask> toDocumentTasks(List<Task> list) {
        List<JournalTask> tasks = new ArrayList<JournalTask>();
        for (Task task : list) {
            JournalTask journalTask = new JournalTask(task, toJournal(task));
            tasks.add(journalTask);
        }
        return tasks;
    }

    private Map<String, Object> toVariables(CfJournal journal) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userCreator", Util.getCurrentUser().getUsername());
        map.put("removeDecision", false);
        map.put(DOCUMENT_REFERENCE_NO, journal.getReferenceNo());
        map.put(DOCUMENT_CLASS, journal.getClass().getCanonicalName());
        map.put(DOCUMENT_ID, journal.getId());
        return map;
    }

    private CfJournal toJournal(Task task) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) runtimeService.getVariable(task.getExecutionId(), DOCUMENT_ID);
        return (CfJournal) session.get(CfDocumentImpl.class, id);
    }

    private Class<?> toImplementationClass(CfJournalType type) {
        Class impl = null;
        switch (type) {
            case MANUAL:
                impl = CfManualJournal.class;
                break;
            case AUTO:
                impl = CfManualJournal.class;
                break;
        }
        return impl;
    }
}
