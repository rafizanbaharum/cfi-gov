package net.canang.cfi.core.dm.model;

import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.dd.model.CfSodoCode;
import net.canang.cfi.core.so.model.CfMetaObject;

import java.math.BigDecimal;


public interface CfVoucherTransaction extends CfMetaObject {

    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    CfVoucherRecipient getRecipient();

    void setRecipient(CfVoucherRecipient recipient);

    CfPeriod getPeriod();

    void setPeriod(CfPeriod period);

    CfSodoCode getSodoCode();

    void setSodoCode(CfSodoCode sodo);
}
