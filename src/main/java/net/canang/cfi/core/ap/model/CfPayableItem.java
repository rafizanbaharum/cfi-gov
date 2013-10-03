package net.canang.cfi.core.ap.model;

import net.canang.cfi.core.dd.model.CfSodoCode;
import net.canang.cfi.core.so.model.CfMetaObject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public interface CfPayableItem extends CfMetaObject {

    String getDescription();

    void setDescription(String description);

    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    CfSodoCode getSodoCode();

    void setSodoCode(CfSodoCode sodoCode);

}
