package net.canang.cfi.biz.bm.manager.workflow.task;

import net.canang.cfi.core.bm.model.CfControl;
import net.canang.cfi.core.so.model.CfFlowState;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


/**
 *
 */
@Component("additionControl_complete_ST")
public class AdditionControlCompleteTask extends ControlTaskSupport {

    private static final Logger log = Logger.getLogger(AdditionControlCompleteTask.class);

    public void execute(ActivityExecution execution) throws Exception {

        log.debug("completing addition budget control");

        // this is distribution
        // so only source matters
        Long controlId = (Long) execution.getVariable("documentId");
        CfControl control = bmFinder.findControlById(controlId);

        // update metadata and flowdata
        control.getFlowdata().setState(CfFlowState.COMPLETED);
        bmManager.updateControl(control);

    }
}
