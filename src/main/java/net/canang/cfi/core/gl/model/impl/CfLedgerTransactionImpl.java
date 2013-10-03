package net.canang.cfi.core.gl.model.impl;

import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.dd.model.CfSodoCode;
import net.canang.cfi.core.dd.model.impl.CfPeriodImpl;
import net.canang.cfi.core.dd.model.impl.CfSodoCodeImpl;
import net.canang.cfi.core.gl.model.CfLedger;
import net.canang.cfi.core.gl.model.CfLedgerTransaction;
import net.canang.cfi.core.so.model.CfMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
@Entity(name = "CfLedgerTransaction")
@Table(name = "CF_LDGR_TRSN")
public class CfLedgerTransactionImpl implements CfLedgerTransaction, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_CF_LDGR_TRSN")
    @SequenceGenerator(name = "SEQ_CF_LDGR_TRSN", sequenceName = "SEQ_CF_LDGR_TRSN", allocationSize = 1)
    private Long id;

    @NotNull
    @Size(min = 20, max = 100)
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "POSTED_DATE")
    private Date postedDate;

    @ManyToOne(targetEntity = CfLedgerImpl.class)
    @JoinColumn(name = "LEDGER_ID")
    private CfLedger ledger;

    @ManyToOne(targetEntity = CfPeriodImpl.class)
    @JoinColumn(name = "PERIOD_ID")
    private CfPeriod period;

    @ManyToOne(targetEntity = CfSodoCodeImpl.class)
    @JoinColumn(name = "SODO_ID")
    private CfSodoCode sodoCode;

    @Embedded
    private CfMetadata metadata;

    public CfLedgerTransactionImpl() {
    }

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

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
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

    public void setSodoCode(CfSodoCode sodo) {
        this.sodoCode = sodo;
    }

    public CfLedger getLedger() {
        return ledger;
    }

    public void setLedger(CfLedger ledger) {
        this.ledger = ledger;
    }

    public CfMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CfMetadata metadata) {
        this.metadata = metadata;
    }

}
