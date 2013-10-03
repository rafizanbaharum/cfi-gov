package net.canang.cfi.biz.jm.manager.workflow;

import net.canang.cfi.core.jm.model.CfJournal;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.task.Task;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public class JournalTask extends TaskEntity {

    private Task task;
    private CfJournal journal;

    public JournalTask(Task task) {
        this.task = task;
    }

    public CfJournal getJournal() {
        return journal;
    }

    public void setJournal(CfJournal journal) {
        this.journal = journal;
    }
}
