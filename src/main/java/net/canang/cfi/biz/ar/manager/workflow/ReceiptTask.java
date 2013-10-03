package net.canang.cfi.biz.ar.manager.workflow;

import net.canang.cfi.biz.integration.activiti.TaskAdapter;
import net.canang.cfi.core.ar.model.CfReceipt;
import org.activiti.engine.task.Task;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public class ReceiptTask extends TaskAdapter {

    private CfReceipt receipt;

    public ReceiptTask(Task task, CfReceipt receipt) {
        super(task);
        this.receipt = receipt;
    }

    public CfReceipt getReceipt() {
        return receipt;
    }
}
