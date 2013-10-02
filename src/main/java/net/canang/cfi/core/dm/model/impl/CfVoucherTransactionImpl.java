package net.canang.cfi.core.dm.model.impl;

import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.dd.model.CfSodoCode;
import net.canang.cfi.core.dd.model.impl.CfPeriodImpl;
import net.canang.cfi.core.dd.model.impl.CfSodoCodeImpl;
import net.canang.cfi.core.dm.model.CfVoucherRecipient;
import net.canang.cfi.core.dm.model.CfVoucherTransaction;
import net.canang.cfi.core.so.model.CfMetadata;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Entity(name = "CfVoucherTransaction")
@Table(name = "CF_VCHR_TRSN")
public class CfVoucherTransactionImpl implements CfVoucherTransaction, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_CF_VCHR_TRSN")
    @SequenceGenerator(name = "SEQ_CF_VCHR_TRSN", sequenceName = "SEQ_CF_VCHR_TRSN", allocationSize = 1)
    private Long id;

    @Column(name = "AMOUNT", precision = 17, scale = 17)
    private BigDecimal amount;

    @ManyToOne(targetEntity = CfVoucherRecipientImpl.class)
    @JoinColumn(name = "RECIPIENT_ID")
    private CfVoucherRecipient recipient;

    @ManyToOne(targetEntity = CfSodoCodeImpl.class)
    @JoinColumn(name = "SODO_ID")
    private CfSodoCode sodoCode;

    @ManyToOne(targetEntity = CfPeriodImpl.class)
    @JoinColumn(name = "PERIOD_ID")
    private CfPeriod period;

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

    public CfVoucherRecipient getRecipient() {
        return recipient;
    }

    public void setRecipient(CfVoucherRecipient recipient) {
        this.recipient = recipient;
    }

    public CfSodoCode getSodoCode() {
        return sodoCode;
    }

    public void setSodoCode(CfSodoCode sodoCode) {
        this.sodoCode = sodoCode;
    }

    public CfPeriod getPeriod() {
        return period;
    }

    public void setPeriod(CfPeriod period) {
        this.period = period;
    }

    public CfMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CfMetadata metadata) {
        this.metadata = metadata;
    }
}
