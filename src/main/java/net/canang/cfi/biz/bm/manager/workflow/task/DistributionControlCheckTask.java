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
@Component("distributionControl_check_ST")
public class DistributionControlCheckTask extends ControlTaskSupport {

    private static final Logger log = Logger.getLogger(DistributionControlCheckTask.class);

    public void execute(ActivityExecution execution) throws Exception {
        log.debug("check agihan PTJ");
        Long controlId = (Long) execution.getVariable("documentId");
        CfControl control = bmFinder.findControlById(controlId);

        // update metadata and flowdata
        control.getFlowdata().setState(CfFlowState.CHECKED);
        control.getFlowdata().setCheckedDate(new Timestamp(System.currentTimeMillis()));
        control.getFlowdata().setChecker(Util.getCurrentUser().getId());
        bmManager.updateControl(control);
    }
}
