package net.canang.cfi.core.ar.model.impl;

import net.canang.cfi.core.ar.model.CfReceivable;
import net.canang.cfi.core.ar.model.CfReceivableTransaction;
import net.canang.cfi.core.ar.model.CfReceivableType;
import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.dd.model.impl.CfCostCenterImpl;
import net.canang.cfi.core.so.model.impl.CfDocumentImpl;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Table(name = "CF_RVBL")
@Entity(name = "CfReceivable")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CfReceivableImpl extends CfDocumentImpl implements CfReceivable {

    private static final long serialVersionUID = 1L;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "RECEIVABLE_TYPE")
    private CfReceivableType receivableType;

    @NotNull
    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount = new BigDecimal(0.00);

    @ManyToOne(targetEntity = CfCostCenterImpl.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "REQUESTER_ID")
    private CfCostCenter requester;

    @OneToMany(targetEntity = CfReceivableTransactionImpl.class, mappedBy = "receivable", fetch = FetchType.LAZY)
    private List<CfReceivableTransaction> transactions;

    public CfReceivableType getReceivableType() {
        return receivableType;
    }

    public void setReceivableType(CfReceivableType receivableType) {
        this.receivableType = receivableType;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public CfCostCenter getRequester() {
        return requester;
    }

    public void setRequester(CfCostCenter requester) {
        this.requester = requester;
    }

    public List<CfReceivableTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<CfReceivableTransaction> transactions) {
        this.transactions = transactions;
    }

}
