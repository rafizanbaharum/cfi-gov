package net.canang.cfi.biz.jm.manager.workflow;

import net.canang.cfi.biz.integration.activiti.TaskAdapter;
import net.canang.cfi.core.jm.model.CfJournal;
import org.activiti.engine.task.Task;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public class JournalTask extends TaskAdapter {

    private CfJournal journal;

    public JournalTask(Task task, CfJournal journal) {
        super(task);
        this.journal = journal;
    }

    public CfJournal getJournal() {
        return journal;
    }
}
