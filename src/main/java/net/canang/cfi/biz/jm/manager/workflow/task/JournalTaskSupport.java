package net.canang.cfi.biz.jm.manager.workflow.task;

import net.canang.cfi.biz.dd.manager.DdManager;
import net.canang.cfi.biz.gl.manager.GlManager;
import net.canang.cfi.biz.jm.manager.JmFinder;
import net.canang.cfi.biz.jm.manager.JmManager;
import net.canang.cfi.biz.vm.manager.VmManager;
import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public abstract class JournalTaskSupport extends BpmnActivityBehavior
        implements ActivityBehavior {
    
    @Autowired
    protected JmManager jmManager;

    @Autowired
    protected JmFinder jmFinder;

    @Autowired
    protected DdManager ddManager;

    @Autowired
    protected VmManager vmManager;

    @Autowired
    protected GlManager glManager;
    
}
