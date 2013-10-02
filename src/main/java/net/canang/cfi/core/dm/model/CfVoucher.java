package net.canang.cfi.core.dm.model;

import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.so.model.CfDocument;

import java.math.BigDecimal;
import java.util.List;


public interface CfVoucher extends CfDocument {


    BigDecimal getTotalAmount();

    void setTotalAmount(BigDecimal totalAmount);

    CfCostCenter getRequester();

    void setRequester(CfCostCenter requester);

    CfVoucherType getVoucherType();

    void setVoucherType(CfVoucherType voucherType);

    List<CfVoucherRecipient> getRecipients();

    void setRecipients(List<CfVoucherRecipient> recipients);

    boolean isDisbursed();

}
