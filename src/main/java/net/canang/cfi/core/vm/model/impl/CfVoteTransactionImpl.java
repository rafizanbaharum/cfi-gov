package net.canang.cfi.core.vm.model.impl;

import net.canang.cfi.core.dd.model.CfSodoCode;
import net.canang.cfi.core.dd.model.impl.CfSodoCodeImpl;
import net.canang.cfi.core.so.model.CfMetadata;
import net.canang.cfi.core.vm.model.CfVote;
import net.canang.cfi.core.vm.model.CfVoteTransaction;
import net.canang.cfi.core.vm.model.CfVoteTransactionType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Entity(name = "CfVoteTransaction")
@Table(name = "CF_VOTE_TRSN")
public class CfVoteTransactionImpl implements CfVoteTransaction, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_CF_VOTE_TRSN")
    @SequenceGenerator(name = "SEQ_CF_VOTE_TRSN", sequenceName = "SEQ_CF_VOTE_TRSN", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "SOURCE_NO")
    private String sourceNo;

    @NotNull
    @Column(name = "BUNDLE_NO")
    private String bundleNo;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "NARRATION")
    private String narration;

    @NotNull
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "TRANSACTION_CODE")
    private CfVoteTransactionType transactionCode;

    @NotNull
    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @NotNull
    @ManyToOne(targetEntity = CfSodoCodeImpl.class)
    @JoinColumn(name = "SODO_ID")
    private CfSodoCode sodoCode;

    @NotNull
    @ManyToOne(targetEntity = CfVoteImpl.class)
    @JoinColumn(name = "VOTE_ID")
    private CfVote vote;

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

    public String getBundleNo() {
        return bundleNo;
    }

    public void setBundleNo(String bundleNo) {
        this.bundleNo = bundleNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public CfVoteTransactionType getTransactionType() {
        return transactionCode;
    }

    public void setTransactionType(CfVoteTransactionType transactionType) {
        this.transactionCode = transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CfSodoCode getSodoCode() {
        return sodoCode;
    }

    public void setSodoCode(CfSodoCode sodoCode) {
        this.sodoCode = sodoCode;
    }

    public CfVote getVote() {
        return vote;
    }

    public void setVote(CfVote vote) {
        this.vote = vote;
    }

    public CfMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CfMetadata metadata) {
        this.metadata = metadata;
    }
}
