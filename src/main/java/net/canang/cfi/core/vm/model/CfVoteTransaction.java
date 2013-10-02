package net.canang.cfi.core.vm.model;

import net.canang.cfi.core.dd.model.CfSodoCode;
import net.canang.cfi.core.so.model.CfMetaObject;

import java.math.BigDecimal;

public interface CfVoteTransaction extends CfMetaObject {

    String getSourceNo();

    void setSourceNo(String sourceNo);

    String getBundleNo();

    void setBundleNo(String bundleNo);

    String getDescription();

    void setDescription(String description);

    String getNarration();

    void setNarration(String narration);

    CfVoteTransactionType getTransactionType();

    void setTransactionType(CfVoteTransactionType transactionType);

    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    CfSodoCode getSodoCode();

    void setSodoCode(CfSodoCode sodoCode);

    CfVote getVote();

    void setVote(CfVote vote);
}
