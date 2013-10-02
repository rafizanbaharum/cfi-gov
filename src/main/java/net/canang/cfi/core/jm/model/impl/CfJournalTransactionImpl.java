package net.canang.cfi.core.jm.model.impl;

import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.dd.model.CfSodoCode;
import net.canang.cfi.core.dd.model.impl.CfPeriodImpl;
import net.canang.cfi.core.dd.model.impl.CfSodoCodeImpl;
import net.canang.cfi.core.jm.model.CfJournal;
import net.canang.cfi.core.jm.model.CfJournalTransaction;
import net.canang.cfi.core.so.model.CfMetadata;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Table(name = "CF_JRNL_TRSN")
@Entity(name = "CfJournalTransaction")
public class CfJournalTransactionImpl implements CfJournalTransaction, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_CF_JRNL_TRSN")
    @SequenceGenerator(name = "SEQ_CF_JRNL_TRSN", sequenceName = "SEQ_CF_JRNL_TRSN", allocationSize = 1)
    private Long id;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @ManyToOne(targetEntity =CfJournalImpl.class)
    @JoinColumn(name = "JOURNAL_ID")
    private CfJournal journal;

    @ManyToOne(targetEntity = CfPeriodImpl.class)
    @JoinColumn(name = "PERIOD_ID")
    private CfPeriod period;

    @ManyToOne(targetEntity = CfSodoCodeImpl.class)
    @JoinColumn(name = "SODO_ID")
    private CfSodoCode sodoCode;

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

    public CfJournal getJournal() {
        return journal;
    }

    public void setJournal(CfJournal journal) {
        this.journal = journal;
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

    public CfMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CfMetadata metadata) {
        this.metadata = metadata;
    }
}
