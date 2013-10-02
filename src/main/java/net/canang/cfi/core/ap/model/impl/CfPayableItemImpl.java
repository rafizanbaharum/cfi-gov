package net.canang.cfi.core.ap.model.impl;

import net.canang.cfi.core.ap.model.CfInvoice;
import net.canang.cfi.core.ap.model.CfInvoiceItem;
import net.canang.cfi.core.dd.model.CfSodoCode;
import net.canang.cfi.core.dd.model.impl.CfSodoCodeImpl;
import net.canang.cfi.core.so.model.CfMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Table(name = "CF_PYBL_ITEM")
@Entity(name = "CfPayable")
public class CfPayableItemImpl implements CfInvoiceItem, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_CT08_INVOICE_ITEM")
    @SequenceGenerator(name = "SEQ_CT08_INVOICE_ITEM", sequenceName = "SEQ_CT08_INVOICE_ITEM", allocationSize = 1)
    private Long id;

    @Column(name = "INVOICE_NO")
    private String invoiceNo;

    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "INVOICED_DATE")
    private Date invoicedDate;

    @Column(name = "RECEIVED_DATE")
    private Date receivedDate;

    @ManyToOne(targetEntity = CfInvoiceImpl.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "INVOICE_ID")
    private CfInvoice invoice;

    @ManyToOne(targetEntity = CfSodoCodeImpl.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "SODO_ID")
    private CfSodoCode sodoCode;

    @Embedded
    private CfMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getInvoicedDate() {
        return invoicedDate;
    }

    public void setInvoicedDate(Date invoicedDate) {
        this.invoicedDate = invoicedDate;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public CfInvoice getInvoice() {
        return invoice;
    }

    public void setInvoice(CfInvoice invoice) {
        this.invoice = invoice;
    }

    public CfSodoCode getSodoCode() {
        return sodoCode;
    }

    public void setSodoCode(CfSodoCode sodoCode) {
        this.sodoCode = sodoCode;
    }

    public CfMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CfMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CfPayableItemImpl)) return false;

        CfPayableItemImpl that = (CfPayableItemImpl) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
