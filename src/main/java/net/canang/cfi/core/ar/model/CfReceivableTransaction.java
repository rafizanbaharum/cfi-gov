package net.canang.cfi.core.ar.model;

import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.dd.model.CfSodoCode;
import net.canang.cfi.core.so.model.CfMetaObject;

import java.math.BigDecimal;


public interface CfReceivableTransaction extends CfMetaObject {

    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    CfPeriod getPeriod();

    void setPeriod(CfPeriod period);

    CfSodoCode getSodoCode();

    void setSodoCode(CfSodoCode sodoCode);

    CfReceivable getReceivable();

    void setReceivable(CfReceivable receivable);

}
