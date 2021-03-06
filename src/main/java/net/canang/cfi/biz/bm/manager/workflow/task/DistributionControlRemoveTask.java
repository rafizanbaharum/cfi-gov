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
@Component("distributionControl_remove_ST")
public class DistributionControlRemoveTask extends ControlTaskSupport{

    private static final Logger log = Logger.getLogger(DistributionControlRemoveTask.class);

    public void execute(ActivityExecution execution) throws Exception {
        log.debug("removing budget control");
        Long controlId = (Long) execution.getVariable("documentId");
        CfControl control = bmFinder.findControlById(controlId);

        // serialize remove comment
        String removeComment = (String) execution.getVariable("removeComment");
         control.setCancelComment(removeComment);

        // update metadata and flowdata
        control.getFlowdata().setState(CfFlowState.REMOVED);
        control.getFlowdata().setRemovedDate(new Timestamp(System.currentTimeMillis()));
        control.getFlowdata().setRemover(Util.getCurrentUser().getId());
        bmManager.updateControl(control);

    }
}
