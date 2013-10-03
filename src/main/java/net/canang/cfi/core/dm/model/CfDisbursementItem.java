package net.canang.cfi.core.dm.model;

import net.canang.cfi.core.dd.model.CfAddressInfo;
import net.canang.cfi.core.dd.model.CfActorInfo;
import net.canang.cfi.core.dd.model.CfPaymentInfo;
import net.canang.cfi.core.so.model.CfActor;
import net.canang.cfi.core.so.model.CfMetaObject;

import java.math.BigDecimal;


public interface CfDisbursementItem extends CfMetaObject {

    String getReferenceNo();

    void setReferenceNo(String referenceNo);

    String getDescription();

    void setDescription(String description);

    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    void setConsumerInfo(CfActorInfo consumerInfo);

    CfPaymentInfo getPaymentInfo();

    void setPaymentInfo(CfPaymentInfo paymentInfo);

    CfAddressInfo getAddressInfo();

    void setAddressInfo(CfAddressInfo addressInfo);

    CfActor getActor();

    void setActor(CfActor actor);

    CfActorInfo getConsumerInfo();

    CfDisbursement getDisbursement();

    void setDisbursement(CfDisbursement disbursement);

    CfVoucher getVoucher();

    CfVoucherRecipient getVoucherRecipient();
}
