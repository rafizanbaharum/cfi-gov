package net.canang.cfi.core.ap.model.impl;

import net.canang.cfi.core.ap.model.CfInvoice;
import net.canang.cfi.core.ap.model.CfInvoiceItem;
import net.canang.cfi.core.ap.model.CfInvoiceType;
import net.canang.cfi.core.ap.model.CfPayableType;
import net.canang.cfi.core.dd.model.CfAddressInfo;
import net.canang.cfi.core.dd.model.CfActorInfo;
import net.canang.cfi.core.dd.model.CfPaymentInfo;
import net.canang.cfi.core.so.model.CfActor;
import net.canang.cfi.core.so.model.impl.CfActorImpl;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Table(name = "CF_INVC")
@Entity(name = "CfInvoice")
public class CfInvoiceImpl extends CfSinglePayableImpl implements CfInvoice, Serializable {

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "INVOICE_TYPE")
    private CfInvoiceType invoiceType;

    @OneToMany(targetEntity = CfInvoiceItemImpl.class, mappedBy = "invoice", fetch = FetchType.LAZY)
    private List<CfInvoiceItem> items;

    @Embedded
    private CfActorInfo consumerInfo;

    @Embedded
    private CfAddressInfo addressInfo;

    @Embedded
    private CfPaymentInfo paymentInfo;

    public CfInvoiceImpl() {
        setPayableType(CfPayableType.INVOICE);
    }

    public CfInvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(CfInvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    public List<CfInvoiceItem> getItems() {
        return items;
    }

    public void setItems(List<CfInvoiceItem> items) {
        this.items = items;
    }

    public CfActorInfo getActorInfo() {
        return consumerInfo;
    }

    public void setActorInfo(CfActorInfo consumerInfo) {
        this.consumerInfo = consumerInfo;
    }

    public CfAddressInfo getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(CfAddressInfo addressInfo) {
        this.addressInfo = addressInfo;
    }

    public CfPaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(CfPaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }
}
