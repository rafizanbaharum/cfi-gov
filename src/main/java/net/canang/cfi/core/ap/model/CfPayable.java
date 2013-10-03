package net.canang.cfi.core.ap.model;

import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.dm.model.CfVoucher;
import net.canang.cfi.core.so.model.CfDocument;

import java.math.BigDecimal;


public interface CfPayable<I extends CfPayableItem> extends CfDocument {

    BigDecimal getTotalAmount();

    void setTotalAmount(BigDecimal totalAmount);

    CfPayableType getPayableType();

    void setPayableType(CfPayableType type);

    CfPayableMultiplicity getPayableMultiplicity();

    void setPayableMultiplicity(CfPayableMultiplicity multiplicity);

    CfCostCenter getRequester();

    void setRequester(CfCostCenter requester);

    CfPeriod getPeriod();

    void setPeriod(CfPeriod period);

    CfVoucher getVoucher();

    void setVoucher(CfVoucher voucher);

}
