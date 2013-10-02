package net.canang.cfi.core.dm.model;

import net.canang.cfi.core.so.model.CfMetaObject;

import java.util.List;


public interface CfDisbursement extends CfMetaObject {

    String getReferenceNo();

    void setReferenceNo(String referenceNo);

    boolean isProcessed();

    void setProcessed(boolean processed);

    CfDisbursementMethod getMethod();

    void setMethod(CfDisbursementMethod method);

    List<CfDisbursementItem> getItems();

    void setItems(List<CfDisbursementItem> items);

}
