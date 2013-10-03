package net.canang.cfi.biz.ar.manager.workflow.task;

import net.canang.cfi.biz.Util;
import net.canang.cfi.biz.ar.manager.workflow.task.ReceiptTaskSupport;
import net.canang.cfi.core.ar.model.CfReceipt;
import net.canang.cfi.core.dd.model.CfReferenceNoConstants;
import net.canang.cfi.core.so.model.CfFlowState;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 *
 */
@Component("counterReceipt_register_ST")
public class CounterReceiptRegisterTask extends ReceiptTaskSupport {

    private static final Logger log = Logger.getLogger(CounterReceiptRegisterTask.class);

    public void execute(ActivityExecution execution) throws Exception {

        log.debug("register receipt");
        Long receiptId = (Long) execution.getVariable("documentId");
        CfReceipt receipt = arFinder.findReceiptById(receiptId);

        // create receipt no
        receipt.setReceiptNo(ddManager.generateReferenceNo(CfReferenceNoConstants.RECEIPT_RECEIPT_NO));

        // update flow state
        receipt.getFlowdata().setState(CfFlowState.REGISTERED);
        receipt.getFlowdata().setRegisteredDate(new Timestamp(System.currentTimeMillis()));
        receipt.getFlowdata().setRegisterer(Util.getCurrentUser().getId());
        arManager.updateReceipt(receipt);
    }
}
