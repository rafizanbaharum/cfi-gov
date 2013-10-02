package net.canang.cfi.core.so.model;

/**
 * @author canang.technologies
 * @since 7/10/13
 */
public interface CfFlowObject extends CfMetaObject {

    CfFlowdata getFlowdata();

    void setFlowdata(CfFlowdata flowdata);
}
