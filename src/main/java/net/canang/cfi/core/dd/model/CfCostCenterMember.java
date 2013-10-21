package net.canang.cfi.core.dd.model;

import net.canang.cfi.core.so.model.CfMetaObject;
import net.canang.cfi.core.so.model.CfPrincipal;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
public interface CfCostCenterMember extends CfMetaObject {

    CfPrincipal getPrincipal();

    void setPrincipal(CfPrincipal principal);

    CfCostCenter getCostCenter();

    void setCostCenter(CfCostCenter costCenter);
}