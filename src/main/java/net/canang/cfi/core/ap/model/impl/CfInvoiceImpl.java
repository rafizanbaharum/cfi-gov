package net.canang.cfi.core.ap.model.impl;

import net.canang.cfi.core.ap.model.*;
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
public class CfInvoiceImpl extends CfSinglePayableImpl<CfInvoiceItem> implements CfInvoice, Serializable {

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "INVOICE_TYPE")
    private CfInvoiceType invoiceType;



    public CfInvoiceImpl() {
        setPayableType(CfPayableType.INVOICE);
    }

    public CfInvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(CfInvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }
}
