package net.canang.cfi.core.vm.model;

import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.dd.model.CfSodoCode;
import net.canang.cfi.core.so.model.CfMetaObject;

import java.math.BigDecimal;
import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfVote extends CfMetaObject {

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

    BigDecimal getApprovedAmount();

    void setApprovedAmount(BigDecimal approvedAmount);

    CfPeriod getPeriod();

    void setPeriod(CfPeriod period);

    CfSodoCode getSodoCode();

    void setSodoCode(CfSodoCode sodoCode);

    List<CfVoteTransaction> getTransactions();

    void setTransactions(List<CfVoteTransaction> transactions);

    String getSummary();
}
