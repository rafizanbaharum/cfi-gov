package net.canang.cfi.core.ap.model;

import net.canang.cfi.core.dd.model.CfSodoCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfInvoiceItem extends CfSinglePayableItem {

    String getInvoiceNo();

    void setInvoiceNo(String invoiceNo);

    String getDescription();

    void setDescription(String description);

    Date getInvoicedDate();

    void setInvoicedDate(Date invoicedDate);

    Date getReceivedDate();

    void setReceivedDate(Date receivedDate);

    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    CfSodoCode getSodoCode();

    void setSodoCode(CfSodoCode sodoCode);

    CfInvoice getInvoice();

    void setInvoice(CfInvoice invoice);

}
