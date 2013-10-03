package net.canang.cfi.biz.ar.manager.workflow.task;

import net.canang.cfi.biz.ar.manager.workflow.task.ReceiptTaskSupport;
import net.canang.cfi.core.ar.model.CfReceipt;
import net.canang.cfi.core.so.model.CfFlowState;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Component("counterReceipt_complete_ST")
@Transactional
public class CounterReceiptCompleteTask extends ReceiptTaskSupport {

    private static final Logger log = Logger.getLogger(CounterReceiptCompleteTask.class);

    public void execute(ActivityExecution execution) throws Exception {

        log.debug("completing receipt");

        // retrieve receipt from variable
        Long receiptId = (Long) execution.getVariable("documentId");
        CfReceipt receipt = arFinder.findReceiptById(receiptId);

        // update flow state
        receipt.getFlowdata().setState(CfFlowState.COMPLETED);
        arManager.updateReceipt(receipt);

    }
}
