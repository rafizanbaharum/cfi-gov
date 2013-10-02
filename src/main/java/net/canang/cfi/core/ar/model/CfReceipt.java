package net.canang.cfi.core.ar.model;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfReceipt extends CfReceivable {

    String getReceiptNo();

    void setReceiptNo(String receiptNo);

    CfReceiptType getReceiptType();

    void setReceiptType(CfReceiptType receiptType);

    CfPayType getPayType();

    void setPayType(CfPayType type);

    List<CfReceiptPayment> getPayments();

    void setPayments(List<CfReceiptPayment> payments);

}
