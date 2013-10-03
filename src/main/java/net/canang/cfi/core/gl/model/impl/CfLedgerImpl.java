package net.canang.cfi.core.gl.model.impl;

import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.dd.model.impl.CfCostCenterImpl;
import net.canang.cfi.core.gl.model.CfLedger;
import net.canang.cfi.core.gl.model.CfLedgerTransaction;
import net.canang.cfi.core.gl.model.CfLedgerTransactionType;
import net.canang.cfi.core.so.model.CfMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * DEBIT = +AMOUNT
 * CREDIT = -AMOUNT
 *
 * @author rafizan.baharum
 * @since 10/3/13
 */
@Entity(name = "CfLedger")
@Table(name = "CF_LDGR")
public class CfLedgerImpl implements CfLedger, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_FS_LDGR")
    @SequenceGenerator(name = "SEQ_FS_LDGR", sequenceName = "SEQ_FS_LDGR", allocationSize = 1)
    private Long id;

    @Column(name = "REFERENCE_NO", nullable = false)
    private String referenceNo;

    @Column(name = "SOURCE_NO", nullable = false)
    private String sourceNo;

    @Size(min = 3, max = 100)
    @NotNull
    @Column(name = "DESCRIPTION", length = 100)
    private String description;

    @NotNull
    @Column(name = "EXTERNAL")
    private boolean external;

    @NotNull
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "TRANSACTION_TYPE")
    private CfLedgerTransactionType transactionType;

    @ManyToOne(targetEntity = CfCostCenterImpl.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "REQUESTER_ID")
    private CfCostCenter requester;

    @OneToMany(targetEntity = CfLedgerTransactionImpl.class, mappedBy = "ledger")
    private List<CfLedgerTransaction> transactions;

    @Embedded
    private CfMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getSourceNo() {
        return sourceNo;
    }

    public void setSourceNo(String sourceNo) {
        this.sourceNo = sourceNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isExternal() {
        return external;
    }

    public void setExternal(boolean external) {
        this.external = external;
    }

    public CfLedgerTransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(CfLedgerTransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public CfCostCenter getRequester() {
        return requester;
    }

    public void setRequester(CfCostCenter requester) {
        this.requester = requester;
    }

    public List<CfLedgerTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<CfLedgerTransaction> transactions) {
        this.transactions = transactions;
    }

    public CfMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CfMetadata metadata) {
        this.metadata = metadata;
    }

}
