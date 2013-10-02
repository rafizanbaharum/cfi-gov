package net.canang.cfi.core.vm.model.impl;

import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.dd.model.CfSodoCode;
import net.canang.cfi.core.dd.model.impl.CfPeriodImpl;
import net.canang.cfi.core.dd.model.impl.CfSodoCodeImpl;
import net.canang.cfi.core.so.model.CfMetadata;
import net.canang.cfi.core.vm.model.CfVote;
import net.canang.cfi.core.vm.model.CfVoteTransaction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Table(name = "CF_VOTE")
@Entity(name = "CfVote")
public class CfVoteImpl implements CfVote, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_CF_VOTE")
    @SequenceGenerator(name = "SEQ_CF_VOTE", sequenceName = "SEQ_CF_VOTE", allocationSize = 1)
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "APPROVED_AMOUNT")
    private BigDecimal approvedAmount;

    @NotNull
    @Column(name = "CAPPED_AMOUNT")
    private BigDecimal cappedAmount;

    @NotNull
    @Column(name = "ALLOW_NEGATIVE")
    private boolean allowNegative;

    @ManyToOne(targetEntity =  CfPeriodImpl.class)
    @JoinColumn(name = "PERIOD_ID")
    private CfPeriod period;

    @ManyToOne(targetEntity = CfSodoCodeImpl.class)
    @JoinColumn(name = "SODO_ID")
    private CfSodoCode sodoCode;

    @OneToMany(targetEntity = CfVoteTransactionImpl.class, mappedBy = "vote", fetch = FetchType.LAZY)
    private List<CfVoteTransaction> transactions;

    @Embedded
    private CfMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(BigDecimal approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public BigDecimal getCappedAmount() {
        return cappedAmount;
    }

    public void setCappedAmount(BigDecimal cappedAmount) {
        this.cappedAmount = cappedAmount;
    }

    public boolean isAllowNegative() {
        return allowNegative;
    }

    public void setAllowNegative(boolean allowNegative) {
        this.allowNegative = allowNegative;
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

    public List<CfVoteTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<CfVoteTransaction> transactions) {
        this.transactions = transactions;
    }

    public CfMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CfMetadata metadata) {
        this.metadata = metadata;
    }

    @Transient
    public String getSummary() {
        return code + " - " + description;
    }
}
