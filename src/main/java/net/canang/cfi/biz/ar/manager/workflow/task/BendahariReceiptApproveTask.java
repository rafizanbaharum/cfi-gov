package my.utm.fs.biz.pnm.workflow;

import my.utm.fs.biz.das.manager.DasManager;
import my.utm.fs.biz.lja.manager.LjaManager;
import my.utm.fs.biz.pnm.manager.PnmFinder;
import my.utm.fs.biz.pnm.manager.PnmManager;
import my.utm.fs.biz.util.Util;
import my.utm.fs.biz.vbk.manager.VbkManager;
import my.utm.fs.core.das.model.FsFlowStateType;
import my.utm.fs.core.pnm.model.FsReceipt;
import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

import static my.utm.fs.biz.WorkflowConstants.APPROVE2_DECISION;

/**
 *
 */
@Component("bendahariReceipt_approve_ST")
@Transactional
public class
        BendahariReceiptApproveTask extends BpmnActivityBehavior
        implements ActivityBehavior {

    // logger
    private static final Logger log = Logger.getLogger(BendahariReceiptApproveTask.class);

    @Autowired
    private PnmManager pnmManager;

    @Autowired
    private PnmFinder pnmFinder;

    @Autowired
    private DasManager dasManager;

    @Autowired
    private VbkManager vbkManager;

    @Autowired
    private LjaManager ljaManager;

    public void execute(ActivityExecution execution) throws Exception {

        log.debug("approving receipt");

        // retrieve receipt from variable
        Long receiptId = (Long) execution.getVariable(PnmWorkflowConstants.RECEIPT_ID);
        FsReceipt receipt = pnmFinder.findReceiptById(receiptId);

        // update flow state
        receipt.getFlowdata().setState(FsFlowStateType.APPROVED);
        receipt.getFlowdata().setApprovedDate(new Timestamp(System.currentTimeMillis()));
        receipt.getFlowdata().setApproverId(Util.getCurrentUser().getId());
        pnmManager.updateReceipt(receipt);

        execution.setVariable(APPROVE2_DECISION, false);

        // NOTE: receipt workflow has no upper approve?
        vbkManager.post(receipt);
        ljaManager.post(receipt);
    }
}
