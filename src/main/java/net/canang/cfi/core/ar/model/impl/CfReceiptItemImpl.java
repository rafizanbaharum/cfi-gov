package net.canang.cfi.core.ar.model.impl;

import net.canang.cfi.core.ar.model.CfReceiptItem;
import net.canang.cfi.core.ar.model.CfReceiptPayment;
import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.dd.model.CfSodoCode;
import net.canang.cfi.core.dd.model.impl.CfPeriodImpl;
import net.canang.cfi.core.dd.model.impl.CfSodoCodeImpl;
import net.canang.cfi.core.so.model.CfMetadata;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Table(name = "CF_RCPT_ITEM")
@Entity(name = "CfReceiptItem")
public class CfReceiptItemImpl implements CfReceiptItem, Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_CF_RCPT_ITEM")
    @SequenceGenerator(name = "SEQ_CF_RCPT_ITEM", sequenceName = "SEQ_CF_RCPT_ITEM", allocationSize = 1)
    private Long id;

    @Deprecated
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "UNIT")
    private Integer unit;

    @ManyToOne(targetEntity = CfPeriodImpl.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "PERIOD_ID")
    private CfPeriod period;

    @ManyToOne(targetEntity = CfSodoCodeImpl.class)
    @JoinColumn(name = "SODO_CODE_ID")
    private CfSodoCode sodoCode;

    @ManyToOne(targetEntity = CfReceiptPaymentImpl.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "PAYMENT_ID")
    private CfReceiptPayment payment;

    @Embedded
    private CfMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public CfPeriod getPeriod() {
        return period;
    }

    public void setPeriod(CfPeriod period) {
        this.period = period;
    }

    public CfSodoCode getSodoCode() {
        return sodoCode;
    }

    public void setSodoCode(CfSodoCode sodoCode) {
        this.sodoCode = sodoCode;
    }

    public CfReceiptPayment getPayment() {
        return payment;
    }

    public void setPayment(CfReceiptPayment payment) {
        this.payment = payment;
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
        if (o instanceof CfReceiptItem) {
            CfReceiptItem that = (CfReceiptItem) o;
            return null != this.getId() && null != that.getId() && this.getId().equals(that.getId());
        } else {
            return false;
        }
    }

}
