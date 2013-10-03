package net.canang.cfi.biz.ar.manager.workflow.task;

import net.canang.cfi.biz.Util;
import net.canang.cfi.core.ar.model.CfReceipt;
import net.canang.cfi.core.so.model.CfFlowState;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 *
 */
@Component("counterReceipt_remove_ST")
public class CounterReceiptRemoveTask extends ReceiptTaskSupport {

    private static final Logger log = Logger.getLogger(CounterReceiptRemoveTask.class);

    /**
     * @param execution
     * @throws Exception
     */
    public void execute(ActivityExecution execution) throws Exception {

        // retrieve receipt from variable
        Long receiptId = (Long) execution.getVariable("documentId");
        CfReceipt receipt = arFinder.findReceiptById(receiptId);

        // serialize remove comment
        String removeComment = (String) execution.getVariable("removeComment");
        receipt.setRemoveComment(removeComment);

        // update flow state
        receipt.getFlowdata().setState(CfFlowState.REMOVED);
        receipt.getFlowdata().setRemovedDate(new Timestamp(System.currentTimeMillis()));
        receipt.getFlowdata().setRemover(Util.getCurrentUser().getId());
        arManager.updateReceipt(receipt);

    }
}
