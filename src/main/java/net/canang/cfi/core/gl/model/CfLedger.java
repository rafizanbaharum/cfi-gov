package net.canang.cfi.core.gl.model;

import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.so.model.CfMetaObject;

import java.util.List;


public interface CfLedger extends CfMetaObject {

    String getReferenceNo();

    void setReferenceNo(String referenceNo);

    String getSourceNo();

    void setSourceNo(String sourceNo);

    String getDescription();

    void setDescription(String description);

    CfLedgerTransactionType getTransactionType();

    void setTransactionType(CfLedgerTransactionType transactionType);

    CfCostCenter getRequester();

    void setRequester(CfCostCenter requester);

    List<CfLedgerTransaction> getTransactions();

    void setTransactions(List<CfLedgerTransaction> transactions);

}
