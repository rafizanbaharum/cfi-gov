package net.canang.cfi.core.ar.model.impl;

import net.canang.cfi.core.ar.model.CfPayType;
import net.canang.cfi.core.ar.model.CfReceipt;
import net.canang.cfi.core.ar.model.CfReceiptItem;
import net.canang.cfi.core.ar.model.CfReceiptPayment;
import net.canang.cfi.core.dd.model.CfBankCode;
import net.canang.cfi.core.dd.model.CfStateCode;
import net.canang.cfi.core.dd.model.impl.CfBankCodeImpl;
import net.canang.cfi.core.dd.model.impl.CfStateCodeImpl;
import net.canang.cfi.core.so.model.CfMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Entity(name = "CfReceiptPayment")
@Table(name = "CF_RCPT_PYMT")
public class CfReceiptPaymentImpl implements CfReceiptPayment, Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_CF_RCPT_PYMT")
    @SequenceGenerator(name = "SEQ_CF_RCPT_PYMT", sequenceName = "SEQ_CF_RCPT_PYMT", allocationSize = 1)
    private Long id;

    @Column(name = "SOURCE_NO")
    private String sourceNo;

    @Column(name = "LOCATION")
    private String location;

    @NotNull
    @Column(name = "AMOUNT")
    private BigDecimal amount = new BigDecimal(0.00);

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "PAY_TYPE")
    private CfPayType payType;

    @ManyToOne(targetEntity = CfBankCodeImpl.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "BANK_CODE_ID")
    private CfBankCode bankCode;

    @ManyToOne(targetEntity = CfStateCodeImpl.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "STATE_CODE_ID")
    private CfStateCode stateCode;

    @Column(name = "RECEIVED_DATE")
    @Temporal(value = TemporalType.DATE)
    private Date receivedDate;

    @ManyToOne(targetEntity = CfReceiptImpl.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "RECEIPT_ID")
    private CfReceipt receipt;

    @OneToMany(targetEntity = CfReceiptItemImpl.class, mappedBy = "payment", fetch = FetchType.LAZY)
    private List<CfReceiptItem> items;

    @Embedded
    private CfMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSourceNo() {
        return sourceNo;
    }

    public void setSourceNo(String sourceNo) {
        this.sourceNo = sourceNo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CfPayType getPayType() {
        return payType;
    }

    public void setPayType(CfPayType payType) {
        this.payType = payType;
    }


    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public CfBankCode getBankCode() {
        return bankCode;
    }

    public void setBankCode(CfBankCode bankCode) {
        this.bankCode = bankCode;
    }

    public CfStateCode getStateCode() {
        return stateCode;
    }

    public void setStateCode(CfStateCode stateCode) {
        this.stateCode = stateCode;
    }

    public CfReceipt getReceipt() {
        return receipt;
    }

    public void setReceipt(CfReceipt receipt) {
        this.receipt = receipt;
    }

    public List<CfReceiptItem> getItems() {
        return items;
    }

    public void setItems(List<CfReceiptItem> items) {
        this.items = items;
    }

    public CfMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CfMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o instanceof CfReceiptPaymentImpl) {
            CfReceiptPayment that = (CfReceiptPayment) o;

            return null != this.getId() && null != that.getId() && this.getId().equals(that.getId());
        } else {
            return false;
        }
    }
}
