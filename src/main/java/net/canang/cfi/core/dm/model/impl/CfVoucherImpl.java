package net.canang.cfi.core.dm.model.impl;

import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.dd.model.impl.CfCostCenterImpl;
import net.canang.cfi.core.dm.model.CfVoucher;
import net.canang.cfi.core.dm.model.CfVoucherRecipient;
import net.canang.cfi.core.dm.model.CfVoucherType;
import net.canang.cfi.core.so.model.impl.CfDocumentImpl;

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
@Table(name = "CF_VCHR")
@Entity(name = "CfVoucher")
public class CfVoucherImpl extends CfDocumentImpl implements CfVoucher, Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;

    @NotNull
    @Column(name = "DISBURSED")
    private boolean disbursed;

    @NotNull
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "VOUCHER_TYPE")
    private CfVoucherType voucherType;

    @ManyToOne(targetEntity = CfCostCenterImpl.class)
    @JoinColumn(name = "REQUESTER_ID")
    private CfCostCenter requester;

    @OneToMany(targetEntity = CfVoucherRecipientImpl.class, mappedBy = "voucher")
    private List<CfVoucherRecipient> recipients;

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isDisbursed() {
        return disbursed;
    }

    public void setDisbursed(boolean disbursed) {
        this.disbursed = disbursed;
    }

    public CfVoucherType getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(CfVoucherType voucherType) {
        this.voucherType = voucherType;
    }

    public CfCostCenter getRequester() {
        return requester;
    }

    public void setRequester(CfCostCenter requester) {
        this.requester = requester;
    }

    public List<CfVoucherRecipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<CfVoucherRecipient> recipients) {
        this.recipients = recipients;
    }
}
