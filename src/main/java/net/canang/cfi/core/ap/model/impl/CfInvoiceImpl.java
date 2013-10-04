package net.canang.cfi.core.ap.model.impl;

import net.canang.cfi.core.ap.model.CfInvoice;
import net.canang.cfi.core.ap.model.CfInvoiceType;
import net.canang.cfi.core.ap.model.CfPayableType;

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


    public CfInvoiceImpl() {
        setPayableType(CfPayableType.INVOICE);
    }

    public CfInvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(CfInvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    @Override
    public void setItems(List items) {
        // TODO:

    }
}
