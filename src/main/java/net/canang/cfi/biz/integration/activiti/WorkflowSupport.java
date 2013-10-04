package net.canang.cfi.biz.integration.activiti;

import net.canang.cfi.biz.Util;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author rafizan.baharum
 * @since 10/4/13
 */
public class WorkflowSupport {

    private Logger log = LoggerFactory.getLogger(WorkflowSupport.class);

    @Autowired
    protected TaskService taskService;

    @Autowired
    protected HistoryService historyService;

    @Autowired
    protected RepositoryService repositoryService;


    public void claimTask(Task task) {
        Validate.notNull(task, "Task cannot be null");
        log.debug("claiming for user: " + Util.getCurrentUser().getName());
        taskService.claim(task.getId(), Util.getCurrentUser().getName());
    }

    public void releaseTask(Task task) {
        Validate.notNull(task, "Task cannot be null");
        log.debug("releasing for user: " + Util.getCurrentUser().getName());
        taskService.claim(task.getId(), null);
    }

    public void stealTask(Task task) {
        Validate.notNull(task, "Task cannot be null");
        log.debug("stealing for user: " + Util.getCurrentUser().getName());
        taskService.claim(task.getId(), Util.getCurrentUser().getName());
    }

    public void assignTask(Task task) {
        Validate.notNull(task, "Task cannot be null");
        log.debug("assigning for user: " + Util.getCurrentUser().getName());
        taskService.setAssignee(task.getId(), Util.getCurrentUser().getName());
    }

    public void assignTask(Task task, String username) {
        Validate.notNull(task, "Task cannot be null");
        taskService.setAssignee(task.getId(), username);
    }

    public void completeTask(Task task) {
        Validate.notNull(task, "Task cannot be null");
        taskService.complete(task.getId());
    }

    public void completeTask(Task task, Map<String, Object> variables) {
        Validate.notNull(task, "Task cannot be null");
        taskService.complete(task.getId(), variables);
    }

    protected boolean isProcessDefExists(String processDef) {
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        query.processDefinitionKey(processDef);
        return query.count() > 0;
    }

    protected String deploy(String processDef, String processFile) {
        return repositoryService.createDeployment().name(processDef).addClasspathResource(processFile).deploy().getId();
    }
}
