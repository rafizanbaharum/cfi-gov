package net.canang.cfi.core.gl.model;

import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.dd.model.CfSodoCode;
import net.canang.cfi.core.so.model.CfMetaObject;

import java.math.BigDecimal;
import java.util.Date;


public interface CfLedgerTransaction extends CfMetaObject {

    String getDescription();

    void setDescription(String description);

    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    Date getPostedDate();

    void setPostedDate(Date postedDate);

    CfPeriod getPeriod();

    void setPeriod(CfPeriod period);

    CfSodoCode getSodoCode();

    void setSodoCode(CfSodoCode sodo);

    CfLedger getLedger();

    void setLedger(CfLedger ledger);

}
