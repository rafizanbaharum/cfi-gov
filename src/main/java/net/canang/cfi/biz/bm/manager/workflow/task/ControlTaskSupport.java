package net.canang.cfi.biz.bm.manager.workflow.task;

import net.canang.cfi.biz.bm.manager.BmFinder;
import net.canang.cfi.biz.bm.manager.BmManager;
import net.canang.cfi.biz.dd.manager.DdFinder;
import net.canang.cfi.biz.vm.manager.VmManager;
import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public abstract class ControlTaskSupport extends BpmnActivityBehavior
        implements ActivityBehavior {


    @Autowired
    protected DdFinder ddFinder;

    @Autowired
    protected BmManager bmManager;

    @Autowired
    protected BmFinder bmFinder;

    @Autowired
    protected VmManager vmManager;

}
