package my.utm.fs.biz.pnm.workflow;

import my.utm.fs.biz.WorkflowConstants;
import my.utm.fs.biz.das.manager.DasManager;
import my.utm.fs.biz.pnm.manager.PnmFinder;
import my.utm.fs.biz.pnm.manager.PnmManager;
import my.utm.fs.biz.util.Util;
import my.utm.fs.core.das.model.FsCampus;
import my.utm.fs.core.das.model.FsFlowStateType;
import my.utm.fs.core.pnm.model.FsReceipt;
import my.utm.fs.core.pnm.model.impl.FsReceiptImpl;
import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

import static my.utm.fs.core.das.model.FsReferenceNoConstants.RECEIPT_RECEIPT_NO;

/**
 *
 */
@Component("bendahariReceipt_register_ST")
public class BendahariReceiptRegisterTask extends BpmnActivityBehavior
        implements ActivityBehavior {

    // logger
    private static final Logger log = Logger.getLogger(BendahariReceiptRegisterTask.class);

    @Autowired
    private PnmManager pnmManager;

    @Autowired
    private PnmFinder pnmFinder;

    @Autowired
    private DasManager dasManager;

    /**
     * @param execution
     * @throws Exception
     */
    public void execute(ActivityExecution execution) throws Exception {

        log.debug("register receipt");
        log.debug("current remove decision: " + execution.getVariable(WorkflowConstants.REMOVE_DECISION));
        log.debug("current query decision: " + execution.getVariable(WorkflowConstants.QUERY_DECISION));

        // retrieve receipt from variable
        Long receiptId = (Long) execution.getVariable(PnmWorkflowConstants.RECEIPT_ID);

        log.debug("registering receipt with id: " + receiptId);
        FsReceipt receipt = pnmFinder.findReceiptById(receiptId);

        // create receipt no
        FsCampus campus = receipt.getRequester().getResponsibilityCenter().getCampus();
        ((FsReceiptImpl) receipt).setReceiptNo(dasManager.generateReferenceNo(RECEIPT_RECEIPT_NO, campus));

        // update flow state
        receipt.getFlowdata().setState(FsFlowStateType.REGISTERED);
        receipt.getFlowdata().setRegisteredDate(new Timestamp(System.currentTimeMillis()));
        receipt.getFlowdata().setRegistererId(Util.getCurrentUser().getId());
        pnmManager.updateReceipt(receipt);

    }
}
