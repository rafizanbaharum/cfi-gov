package net.canang.cfi.core.ar.model.impl;

import net.canang.cfi.core.ar.model.CfPayType;
import net.canang.cfi.core.ar.model.CfReceipt;
import net.canang.cfi.core.ar.model.CfReceiptPayment;
import net.canang.cfi.core.ar.model.CfReceiptType;
import net.canang.cfi.core.dd.model.CfCurrencyCode;
import net.canang.cfi.core.dd.model.impl.CfCurrencyCodeImpl;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Entity(name = "CfReceipt")
@Table(name = "CF_RCPT")
public class CfReceiptImpl extends CfReceivableImpl implements CfReceipt, Serializable {

    @Column(name = "RECEIPT_NO")
    private String receiptNo;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "RECEIPT_TYPE")
    private CfReceiptType receiptType;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "PAY_TYPE")
    private CfPayType payType;

    @ManyToOne(targetEntity = CfCurrencyCodeImpl.class)
    @JoinColumn(name = "CURRENCY_ID")
    private CfCurrencyCode currencyCode;

    @NotNull
    @Column(name = "TOTAL_AMOUNT_IN_CURRENCY")
    private BigDecimal totalAmountInCurrency = new BigDecimal(0.00);

    @OneToMany(targetEntity = CfReceiptPaymentImpl.class, mappedBy = "receipt", fetch = FetchType.LAZY)
    private List<CfReceiptPayment> payments;


    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public CfReceiptType getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(CfReceiptType receiptType) {
        this.receiptType = receiptType;
    }

    public CfPayType getPayType() {
        return payType;
    }

    public void setPayType(CfPayType payType) {
        this.payType = payType;
    }

    public CfCurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(CfCurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getTotalAmountInCurrency() {
        return totalAmountInCurrency;
    }

    public void setTotalAmountInCurrency(BigDecimal totalAmountInCurrency) {
        this.totalAmountInCurrency = totalAmountInCurrency;
    }

    public List<CfReceiptPayment> getPayments() {
        return payments;
    }

    public void setPayments(List<CfReceiptPayment> payments) {
        this.payments = payments;
    }
}
