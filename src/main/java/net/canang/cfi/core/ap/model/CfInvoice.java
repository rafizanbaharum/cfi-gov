package net.canang.cfi.core.ap.model;

import net.canang.cfi.core.dd.model.CfPaymentInfo;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfInvoice extends CfSinglePayable<CfInvoiceItem> {

    CfInvoiceType getInvoiceType();

    void setInvoiceType(CfInvoiceType type);

    CfPaymentInfo getPaymentInfo();

    void setPaymentInfo(CfPaymentInfo paymentInfo);
}
