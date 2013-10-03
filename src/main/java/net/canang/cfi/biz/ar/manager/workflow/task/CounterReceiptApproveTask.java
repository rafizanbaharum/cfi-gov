package net.canang.cfi.biz.ar.manager.workflow.task;

import net.canang.cfi.biz.Util;
import net.canang.cfi.core.ar.model.CfReceipt;
import net.canang.cfi.core.so.model.CfFlowState;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 *
 */
@Component("counterReceipt_approve_ST")
@Transactional
public class CounterReceiptApproveTask extends ReceiptTaskSupport {

    private static final Logger log = Logger.getLogger(CounterReceiptApproveTask.class);


    public void execute(ActivityExecution execution) throws Exception {

        log.debug("approving receipt");

        // retrieve receipt from variable
        Long receiptId = (Long) execution.getVariable("documentId");
        CfReceipt receipt = arFinder.findReceiptById(receiptId);

        // update flow state
        receipt.getFlowdata().setState(CfFlowState.APPROVED);
        receipt.getFlowdata().setApprovedDate(new Timestamp(System.currentTimeMillis()));
        receipt.getFlowdata().setApprover(Util.getCurrentUser().getId());
        arManager.updateReceipt(receipt);

        vmManager.post(receipt);
        glManager.post(receipt);
    }
}
