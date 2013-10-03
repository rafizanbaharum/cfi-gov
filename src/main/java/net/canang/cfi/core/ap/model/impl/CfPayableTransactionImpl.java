package net.canang.cfi.core.ap.model.impl;

import net.canang.cfi.core.ap.model.CfPayable;
import net.canang.cfi.core.ap.model.CfPayableTransaction;
import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.dd.model.CfSodoCode;
import net.canang.cfi.core.dd.model.impl.CfPeriodImpl;
import net.canang.cfi.core.dd.model.impl.CfSodoCodeImpl;
import net.canang.cfi.core.so.model.CfMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Table(name = "CF_PYBL_TRSN")
@Entity(name = "CfPayableTransaction")
public class CfPayableTransactionImpl implements CfPayableTransaction, Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_CT_PAYABLE_TRANSACTION")
    @SequenceGenerator(name = "SEQ_CT_PAYABLE_TRANSACTION", sequenceName = "SEQ_CT_PAYABLE_TRANSACTION", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @ManyToOne(targetEntity = CfPeriodImpl.class)
    @JoinColumn(name = "PERIOD_ID")
    private CfPeriod period;

    @ManyToOne(targetEntity = CfSodoCodeImpl.class)
    @JoinColumn(name = "SODO_CODE_ID")
    private CfSodoCode sodoCode;

    @ManyToOne(targetEntity = CfPayableImpl.class)
    @JoinColumn(name = "PAYABLE_ID")
    private CfPayable payable;

    @Embedded
    private CfMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public CfPayable getPayable() {
        return payable;
    }

    public void setPayable(CfPayable payable) {
        this.payable = payable;
    }

    public CfMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CfMetadata metadata) {
        this.metadata = metadata;
    }
}
