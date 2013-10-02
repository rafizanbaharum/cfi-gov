package net.canang.cfi.core.ar.model;

import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.dd.model.CfSodoCode;
import net.canang.cfi.core.so.model.CfMetaObject;

import java.math.BigDecimal;


public interface CfReceiptItem extends CfMetaObject {

    String getDescription();

    void setDescription(String description);

    Integer getUnit();

    void setUnit(Integer unit);

    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    CfPeriod getPeriod();

    void setPeriod(CfPeriod period);

    CfSodoCode getSodoCode();

    void setSodoCode(CfSodoCode sodoCode);

    CfReceiptPayment getPayment();

    void setPayment(CfReceiptPayment payment);
}
