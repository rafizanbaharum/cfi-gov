package net.canang.cfi.core.jm.model;

import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.dd.model.CfSodoCode;
import net.canang.cfi.core.so.model.CfMetaObject;

import java.math.BigDecimal;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfJournalTransaction extends CfMetaObject {

    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    CfJournal getJournal();

    void setJournal(CfJournal journal);

    CfPeriod getPeriod();

    void setPeriod(CfPeriod period);

    CfSodoCode getSodoCode();

    void setSodoCode(CfSodoCode sodoCode);

}
