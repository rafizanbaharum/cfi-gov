package net.canang.cfi.core.ap.model.impl;

import net.canang.cfi.core.ap.model.CfPayable;
import net.canang.cfi.core.ap.model.CfPayableMultiplicity;
import net.canang.cfi.core.ap.model.CfPayableTransaction;
import net.canang.cfi.core.ap.model.CfPayableType;
import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.dd.model.impl.CfCostCenterImpl;
import net.canang.cfi.core.dd.model.impl.CfPeriodImpl;
import net.canang.cfi.core.dm.model.CfVoucher;
import net.canang.cfi.core.dm.model.impl.CfVoucherImpl;
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
@Table(name = "CF_PYBL")
@Entity(name = "CfPayable")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CfPayableImpl extends CfDocumentImpl implements CfPayable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount = new BigDecimal(0.00);

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "PAYABLE_MULTIPLICITY")
    private CfPayableMultiplicity payableMultiplicity;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "PAYABLE_TYPE")
    private CfPayableType payableType;

    @ManyToOne(targetEntity = CfVoucherImpl.class)
    @JoinColumn(name = "VOUCHER_ID")
    private CfVoucher voucher;

    @OneToMany(targetEntity = CfPayableTransactionImpl.class, mappedBy = "payable", fetch = FetchType.LAZY)
    private List<CfPayableTransaction> transactions;

    @NotNull
    @ManyToOne(targetEntity = CfCostCenterImpl.class)
    @JoinColumn(name = "REQUESTER_ID")
    private CfCostCenter requester;

    @ManyToOne(targetEntity = CfPeriodImpl.class)
    @JoinColumn(name = "PERIOD_ID")
    private CfPeriod period;

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public CfPayableMultiplicity getPayableMultiplicity() {
        return payableMultiplicity;
    }

    public void setPayableMultiplicity(CfPayableMultiplicity payableMultiplicity) {
        this.payableMultiplicity = payableMultiplicity;
    }

    public CfPayableType getPayableType() {
        return payableType;
    }

    public void setPayableType(CfPayableType payableType) {
        this.payableType = payableType;
    }

    public CfVoucher getVoucher() {
        return voucher;
    }

    public void setVoucher(CfVoucher voucher) {
        this.voucher = voucher;
    }

    public List<CfPayableTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<CfPayableTransaction> transactions) {
        this.transactions = transactions;
    }

    public CfCostCenter getRequester() {
        return requester;
    }

    public void setRequester(CfCostCenter requester) {
        this.requester = requester;
    }

    public CfPeriod getPeriod() {
        return period;
    }

    public void setPeriod(CfPeriod period) {
        this.period = period;
    }
}
