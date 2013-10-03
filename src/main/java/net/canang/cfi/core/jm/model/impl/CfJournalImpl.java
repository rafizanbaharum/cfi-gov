package net.canang.cfi.core.jm.model.impl;

import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.dd.model.impl.CfCostCenterImpl;
import net.canang.cfi.core.jm.model.CfJournal;
import net.canang.cfi.core.jm.model.CfJournalTransaction;
import net.canang.cfi.core.jm.model.CfJournalType;
import net.canang.cfi.core.so.model.impl.CfDocumentImpl;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Table(name = "CF_JRNL")
@Entity(name = "CfJournal")
@Inheritance(strategy = InheritanceType.JOINED)
public class CfJournalImpl extends CfDocumentImpl implements CfJournal, Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "JOURNAL_NO")
    private String journalNo;

    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "JOURNAL_TYPE")
    private CfJournalType journalType;

    @ManyToOne(targetEntity = CfCostCenterImpl.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "REQUESTER_ID")
    private CfCostCenter requester;

    @OneToMany(targetEntity = CfJournalTransactionImpl.class, mappedBy = "journal", fetch = FetchType.LAZY)
    private List<CfJournalTransaction> transactions;

    public String getJournalNo() {
        return journalNo;
    }

    public void setJournalNo(String journalNo) {
        this.journalNo = journalNo;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public CfJournalType getJournalType() {
        return journalType;
    }

    public void setJournalType(CfJournalType journalType) {
        this.journalType = journalType;
    }

    public CfCostCenter getRequester() {
        return requester;
    }

    public void setRequester(CfCostCenter requester) {
        this.requester = requester;
    }

    public List<CfJournalTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<CfJournalTransaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String getPath() {
        return File.pathSeparator + CfJournal.class.getName();
    }
}
