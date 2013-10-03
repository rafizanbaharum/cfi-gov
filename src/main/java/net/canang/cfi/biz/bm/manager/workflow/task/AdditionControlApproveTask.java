package net.canang.cfi.biz.bm.manager.workflow.task;

import net.canang.cfi.biz.Util;
import net.canang.cfi.core.bm.model.CfControl;
import net.canang.cfi.core.so.model.CfFlowState;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;


/**
 *
 */
@Component("additionControl_approve_ST")
public class AdditionControlApproveTask extends ControlTaskSupport {

    private static final Logger log = Logger.getLogger(AdditionControlApproveTask.class);

    public void execute(ActivityExecution execution) throws Exception {
        log.debug("upper verifying addition budget control");

        Long controlId = (Long) execution.getVariable("documentId");
        CfControl control = bmFinder.findControlById(controlId);

        // update metadata and flowdata
        control.getFlowdata().setState(CfFlowState.APPROVED);
        control.getFlowdata().setApprovedDate(new Timestamp(System.currentTimeMillis()));
        control.getFlowdata().setApprover(Util.getCurrentUser().getId());

        // post to vbk
        vmManager.post(control);
    }
}
