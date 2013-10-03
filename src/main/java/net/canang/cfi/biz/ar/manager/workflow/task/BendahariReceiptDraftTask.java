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
@Component("bendahariReceipt_draft_ST")
public class BendahariReceiptDraftTask extends BpmnActivityBehavior
        implements ActivityBehavior {

    // logger
    private static final Logger log = Logger.getLogger(BendahariReceiptDraftTask.class);

    @Autowired
    private PnmManager pnmManager;

    @Autowired
    private PnmFinder pnmFinder;

    /**
     * @param execution
     * @throws Exception
     */
    public void execute(ActivityExecution execution) throws Exception {

        log.debug("drafting receipt");

        // retrieve receipt from variable
        Long receiptId = (Long) execution.getVariable(PnmWorkflowConstants.RECEIPT_ID);
        FsReceipt receipt = pnmFinder.findReceiptById(receiptId);

        // update flow state
        receipt.getFlowdata().setState(FsFlowStateType.DRAFTED);
        receipt.getFlowdata().setDraftedDate(new Timestamp(System.currentTimeMillis()));
        receipt.getFlowdata().setDrafterId(Util.getCurrentUser().getId());
        pnmManager.updateReceipt(receipt);

    }
}
