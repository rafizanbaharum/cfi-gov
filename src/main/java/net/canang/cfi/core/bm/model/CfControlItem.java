package net.canang.cfi.core.bm.model;

import net.canang.cfi.core.dd.model.CfSodoCode;
import net.canang.cfi.core.so.model.CfMetaObject;

import java.math.BigDecimal;


public interface CfControlItem extends CfMetaObject {

    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    CfSodoCode getSodoCode();

    void setSodoCode(CfSodoCode sodoCode);

    CfControl getControl();

    void setControl(CfControl control);

    CfControlItem getParent();

    void setParent(CfControlItem parent);
}
