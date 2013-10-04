package net.canang.cfi.biz.jm.manager.workflow;

import net.canang.cfi.core.jm.model.CfJournal;
import net.canang.cfi.core.so.model.impl.CfDocumentImpl;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
@Service("journalWorklfow")
public class JournalWorkflowImpl implements JournalWorkflow {

    private static final String DOCUMENT_ID = "documentId";
    private static final String PROCESS_DEF = "jm_manual_journal_workflow";
    private static final String PROCESS_BPMN = "/net/canang/cfi/biz/jm/workflow/manual_journal_workflow.bpmn20.xml";


    @Autowired
    private ApplicationContext context;

    @Autowired
    protected SessionFactory sessionFactory;

    @Autowired(required = false)
    protected ProcessEngine processEngine;

    @Autowired(required = false) // todo
    protected RuntimeService runtimeService;

    @Autowired(required = false) // todo
    protected TaskService taskService;

    @Autowired(required = false) // todo
    protected IdentityService identityService;

    @Autowired(required = false) // todo
    protected HistoryService historyService;

    @Autowired(required = false) // todo
    protected RepositoryService repositoryService;


    @Override
    public void process(CfJournal journal) {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(PROCESS_DEF, toVariables(journal));
    }

    public List<JournalTask> findTask(Integer offset, Integer limit) {
        TaskQuery taskQuery = taskService.createTaskQuery();
        taskQuery.taskNameLike(CfJournal.class.getCanonicalName() + "%");
        taskQuery.orderByTaskCreateTime();
        taskQuery.desc();
        List<Task> list = taskQuery.listPage(offset, limit);
        return toDocumentTasks(list);
    }

    private List<JournalTask> toDocumentTasks(List<Task> list) {
        List<JournalTask> tasks = new ArrayList<JournalTask>();
        for (Task task : list) {
            JournalTask journalTask = new JournalTask(task, toJournal(task));
            tasks.add(journalTask);
        }
        return tasks;
    }

    private Map<String, Object> toVariables(CfJournal registration) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("creator", "currentUser");
        map.put("referenceNo", registration.getReferenceNo());
        map.put(DOCUMENT_ID, registration.getId());
        return map;
    }

    private CfJournal toJournal(Task task) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) runtimeService.getVariable(task.getId(), DOCUMENT_ID);
        return (CfJournal) session.get(CfDocumentImpl.class, id);
    }
}
