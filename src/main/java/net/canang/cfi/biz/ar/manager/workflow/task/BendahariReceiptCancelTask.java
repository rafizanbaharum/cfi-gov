package my.utm.fs.biz.pnm.workflow;

import my.utm.fs.biz.pnm.manager.PnmFinder;
import my.utm.fs.biz.pnm.manager.PnmManager;
import my.utm.fs.biz.util.Util;
import my.utm.fs.core.das.model.FsFlowStateType;
import my.utm.fs.core.pnm.model.FsReceipt;
import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 *
 */
@Component("bendahariReceipt_cancel_ST")
public class BendahariReceiptCancelTask extends BpmnActivityBehavior
        implements ActivityBehavior {

    // logger
    private static final Logger log = Logger.getLogger(BendahariReceiptCancelTask.class);

    @Autowired
    private PnmManager pnmManager;

    @Autowired
    private PnmFinder pnmFinder;

    /**
     * @param execution
     * @throws Exception
     */
    public void execute(ActivityExecution execution) throws Exception {

        // retrieve receipt from variable
        Long receiptId = (Long) execution.getVariable(PnmWorkflowConstants.RECEIPT_ID);

        log.debug("removing receipt with id: " + receiptId);
        FsReceipt receipt = pnmFinder.findReceiptById(receiptId);

        // update flow state
        receipt.getFlowdata().setState(FsFlowStateType.CANCELLED);
        receipt.getFlowdata().setCanceledDate(new Timestamp(System.currentTimeMillis()));
        receipt.getFlowdata().setCancelerId(Util.getCurrentUser().getId());
        pnmManager.updateReceipt(receipt);

    }
}
