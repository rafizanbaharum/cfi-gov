package net.canang.cfi.biz.ar.manager.workflow.task;

import net.canang.cfi.biz.ar.manager.ArFinder;
import net.canang.cfi.biz.ar.manager.ArManager;
import net.canang.cfi.biz.dd.manager.DdManager;
import net.canang.cfi.biz.gl.manager.GlManager;
import net.canang.cfi.biz.vm.manager.VmManager;
import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public abstract class ReceiptTaskSupport  extends BpmnActivityBehavior
        implements ActivityBehavior {

    @Autowired
    protected ArManager arManager;

    @Autowired
    protected ArFinder arFinder;

    @Autowired
    protected DdManager ddManager;

    @Autowired
    protected VmManager vmManager;

    @Autowired
    protected GlManager glManager;

}
