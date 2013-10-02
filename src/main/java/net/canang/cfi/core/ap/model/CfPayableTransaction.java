package net.canang.cfi.core.ap.model;

import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.dd.model.CfSodoCode;
import net.canang.cfi.core.so.model.CfMetaObject;

import java.math.BigDecimal;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfPayableTransaction extends CfMetaObject {

    CfPayable getPayable();

    void setPayable(CfPayable payable);

    CfPeriod getPeriod();

    void setPeriod(CfPeriod period);

    CfSodoCode getSodoCode();

    void setSodoCode(CfSodoCode sodoCode);

    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

}
