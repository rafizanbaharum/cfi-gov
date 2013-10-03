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
@Component("counterReceipt_draft_ST")
public class CounterReceiptDraftTask extends ReceiptTaskSupport {

    private static final Logger log = Logger.getLogger(CounterReceiptDraftTask.class);

    public void execute(ActivityExecution execution) throws Exception {

        log.debug("drafting receipt");

        // retrieve receipt from variable
        Long receiptId = (Long) execution.getVariable("documentId");
        CfReceipt receipt = arFinder.findReceiptById(receiptId);

        // update flow state
        receipt.getFlowdata().setState(CfFlowState.DRAFTED);
        receipt.getFlowdata().setDraftedDate(new Timestamp(System.currentTimeMillis()));
        receipt.getFlowdata().setDrafter(Util.getCurrentUser().getId());
        arManager.updateReceipt(receipt);

    }
}
