package net.canang.cfi.core.ar.model.impl;

import net.canang.cfi.core.ar.model.CfReceivable;
import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.dd.model.CfSodoCode;
import net.canang.cfi.core.dd.model.impl.CfPeriodImpl;
import net.canang.cfi.core.dd.model.impl.CfSodoCodeImpl;
import net.canang.cfi.core.so.model.CfMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Table(name = "CF_RCVB_TRSN")
@Entity(name = "CFReceivableTransaction")
public class CfReceivableTransactionImpl {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_CT_RCVB_TRSN")
    @SequenceGenerator(name = "SEQ_CT_RCVB_TRSN", sequenceName = "SEQ_CT_RCVB_TRSN", allocationSize = 1)
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

    @ManyToOne(targetEntity = CfReceivableImpl.class)
    @JoinColumn(name = "RECEIVABLE_ID")
    private CfReceivable receivable;

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

    public CfReceivable getReceivable() {
        return receivable;
    }

    public void setReceivable(CfReceivable receivable) {
        this.receivable = receivable;
    }

    public CfMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CfMetadata metadata) {
        this.metadata = metadata;
    }
}
