package net.canang.cfi.biz.bm.manager.workflow.task;

import my.utm.fs.biz.bjw.manager.BjwFinder;
import my.utm.fs.biz.bjw.manager.BjwManager;
import my.utm.fs.biz.bjw.workflow.BjwWorkflowConstants;
import my.utm.fs.biz.das.manager.DasFinder;
import my.utm.fs.biz.util.Util;
import my.utm.fs.biz.vbk.manager.VbkManager;
import my.utm.fs.core.bjw.model.CfControl;
import my.utm.fs.core.das.model.CfFlowState;
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
@Component("additionControl_register_ST")
public class AdditionControlRegisterTask extends BpmnActivityBehavior
        implements ActivityBehavior {

    /**
     * logger
     */
    private static final Logger log = Logger.getLogger(AdditionControlRegisterTask.class);

    @Autowired
    private DasFinder dasFinder;

    @Autowired
    private BjwManager bjwManager;

    @Autowired
    private BjwFinder bjwFinder;

    @Autowired
    private VbkManager vbkManager;

    /**
     * @param execution
     * @throws Exception
     */
    public void execute(ActivityExecution execution) throws Exception {

        log.debug("register Am Bendahari");

        Long controlId = (Long) execution.getVariable("documentId");
        CfControl control = bjwFinder.findControlById(controlId);

        // update metadata and flowdata
        control.getFlowdata().setState(CfFlowState.REGISTERED);
        control.getFlowdata().setRegisteredDate(new Timestamp(System.currentTimeMillis()));
        control.getFlowdata().setRegistererId(Util.getCurrentUser().getId());

    }
}
