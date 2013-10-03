package net.canang.cfi.biz.bm.manager.workflow;

import net.canang.cfi.biz.integration.activiti.TaskAdapter;
import net.canang.cfi.core.bm.model.CfControl;
import org.activiti.engine.task.Task;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public class ControlTask extends TaskAdapter {

    private CfControl control;

    public ControlTask(Task task, CfControl control) {
        super(task);
        this.control = control;
    }
}
