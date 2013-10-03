package net.canang.cfi.biz.bm.manager.workflow.task;

import net.canang.cfi.core.bm.model.CfControl;
import net.canang.cfi.core.so.model.CfFlowState;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


/**
 *
 */
@Component("distributionControl_complete_ST")
public class DistributionControlCompleteTask extends ControlTaskSupport{

    private static final Logger log = Logger.getLogger(DistributionControlCompleteTask.class);

    public void execute(ActivityExecution execution) throws Exception {
        log.debug("completing budget control");
        Long controlId = (Long) execution.getVariable("documentId");
        CfControl control = bmFinder.findControlById(controlId);

        // update metadata and flowdata
        control.getFlowdata().setState(CfFlowState.COMPLETED);
        bmManager.updateControl(control);
    }
}
