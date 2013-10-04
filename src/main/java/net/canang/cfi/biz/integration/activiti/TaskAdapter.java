package net.canang.cfi.biz.integration.activiti;

import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.Task;

import java.util.Date;
import java.util.Map;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public class TaskAdapter extends TaskEntity implements Task {

    protected Task task;

    public TaskAdapter(Task task) {
        this.task = task;
    }

    @Override
    public String getId() {
        return task.getId();
    }

    @Override
    public String getName() {
        return task.getName();
    }


    @Override
    public String getDescription() {
        return task.getDescription();
    }

    @Override
    public int getPriority() {
        return task.getPriority();

    }


    @Override
    public String getOwner() {
        return task.getOwner();
    }

    @Override
    public String getAssignee() {
        return task.getAssignee();
    }

    @Override
    public DelegationState getDelegationState() {
        return task.getDelegationState();
    }

    @Override
    public String getProcessInstanceId() {
        return task.getProcessInstanceId();
    }

    @Override
    public String getExecutionId() {
        return task.getProcessInstanceId();
    }

    @Override
    public String getProcessDefinitionId() {
        return task.getProcessDefinitionId();
    }

    @Override
    public Date getCreateTime() {
        return task.getCreateTime();
    }

    @Override
    public String getTaskDefinitionKey() {
        return task.getTaskDefinitionKey();

    }

    @Override
    public Date getDueDate() {
        return task.getDueDate();

    }

    @Override
    public String getParentTaskId() {
        return task.getParentTaskId();
    }

    @Override
    public boolean isSuspended() {
        return task.isSuspended();
    }

    @Override
    public Map<String, Object> getTaskLocalVariables() {
        return task.getTaskLocalVariables();
    }

    @Override
    public Map<String, Object> getProcessVariables() {
        return task.getProcessVariables();
    }
}
