package net.canang.cfi.core.dm.model;

import net.canang.cfi.core.dd.model.CfActorInfo;
import net.canang.cfi.core.dd.model.CfPaymentInfo;
import net.canang.cfi.core.so.model.CfActor;
import net.canang.cfi.core.so.model.CfMetaObject;

import java.math.BigDecimal;
import java.util.List;


public interface CfVoucherRecipient extends CfMetaObject {

    String getDescription();

    void setDescription(String description);

    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    CfVoucher getVoucher();

    void setVoucher(CfVoucher voucher);

    List<CfVoucherTransaction> getTransactions();

    void setTransactions(List<CfVoucherTransaction> transactions);

    CfActor getActor();

    void setActor(CfActor actor);

    CfActorInfo getConsumerInfo();

    void setConsumerInfo(CfActorInfo consumerInfo);

    CfPaymentInfo getPaymentInfo();

    void setPaymentInfo(CfPaymentInfo paymentInfo);

    boolean isDisbursed();

    void setDisbursed(boolean disbursed);

}
