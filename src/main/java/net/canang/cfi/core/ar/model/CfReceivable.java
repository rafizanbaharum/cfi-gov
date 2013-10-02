package net.canang.cfi.core.ar.model;

import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.so.model.CfDocument;

import java.math.BigDecimal;
import java.util.List;


public interface CfReceivable extends CfDocument {

    BigDecimal getTotalAmount();

    void setTotalAmount(BigDecimal totalAmount);

    CfReceivableType getReceivableType();

    void setReceivableType(CfReceivableType type);

    CfCostCenter getRequester();

    void setRequester(CfCostCenter requester);

    List<CfReceivableTransaction> getTransactions();

    void setTransactions(List<CfReceivableTransaction> transactions);

}
