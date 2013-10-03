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
@Component("additionControl_verify_ST")
public class AdditionControlVerifyTask extends ControlTaskSupport {

    private static final Logger log = Logger.getLogger(AdditionControlVerifyTask.class);

    public void execute(ActivityExecution execution) throws Exception {
        log.debug("verifying addition budget control");
        Long controlId = (Long) execution.getVariable("documentId");
        CfControl control = bmFinder.findControlById(controlId);

        // update metadata and flowdata
        control.getFlowdata().setState(CfFlowState.VERIFIED);
        control.getFlowdata().setVerifiedDate(new Timestamp(System.currentTimeMillis()));
        control.getFlowdata().setVerifier(Util.getCurrentUser().getId());
        bmManager.updateControl(control);

        // populate child item and grand child item
        bmManager.initializeItems(control);
    }
}
