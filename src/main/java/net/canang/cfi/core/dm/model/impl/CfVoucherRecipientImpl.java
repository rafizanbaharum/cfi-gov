package net.canang.cfi.core.dm.model.impl;

import net.canang.cfi.core.dd.model.CfAddressInfo;
import net.canang.cfi.core.dd.model.CfConsumerInfo;
import net.canang.cfi.core.dd.model.CfPaymentInfo;
import net.canang.cfi.core.dm.model.CfVoucher;
import net.canang.cfi.core.dm.model.CfVoucherRecipient;
import net.canang.cfi.core.dm.model.CfVoucherTransaction;
import net.canang.cfi.core.so.model.CfActor;
import net.canang.cfi.core.so.model.CfMetadata;
import net.canang.cfi.core.so.model.impl.CfActorImpl;

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
@Table(name = "CF_VCHR_RCPN")
@Entity(name = "CfVoucherRecipient")
public class CfVoucherRecipientImpl implements CfVoucherRecipient, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_CF_VCHR_RCPN")
    @SequenceGenerator(name = "SEQ_CT10_VOUCHER_RECIPIENT", sequenceName = "SEQ_CT10_VOUCHER_RECIPIENT", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "AMOUNT", precision = 17, scale = 17)
    private BigDecimal amount;

    @Column(name = "DISBURSED")
    private boolean disbursed;

    @ManyToOne(targetEntity = CfVoucherImpl.class)
    @JoinColumn(name = "VOUCHER_ID")
    private CfVoucher voucher;

    @ManyToOne(targetEntity = CfActorImpl.class)
    @JoinColumn(name = "ACTOR_ID")
    private CfActor actor;

    @OneToMany(targetEntity = CfVoucherTransactionImpl.class, mappedBy = "recipient")
    private List<CfVoucherTransaction> transactions;

    @Embedded
    private CfConsumerInfo consumerInfo;

    @Embedded
    private CfAddressInfo addressInfo;

    @Embedded
    private CfPaymentInfo paymentInfo;

    @Embedded
    private CfMetadata metadata;

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

    public boolean isDisbursed() {
        return disbursed;
    }

    public void setDisbursed(boolean disbursed) {
        this.disbursed = disbursed;
    }

    public CfVoucher getVoucher() {
        return voucher;
    }

    public void setVoucher(CfVoucher voucher) {
        this.voucher = voucher;
    }

    public List<CfVoucherTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<CfVoucherTransaction> transactions) {
        this.transactions = transactions;
    }

    public CfActor getActor() {
        return actor;
    }

    public void setActor(CfActor actor) {
        this.actor = actor;
    }

    public CfConsumerInfo getConsumerInfo() {
        return consumerInfo;
    }

    public void setConsumerInfo(CfConsumerInfo consumerInfo) {
        this.consumerInfo = consumerInfo;
    }

    public CfAddressInfo getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(CfAddressInfo addressInfo) {
        this.addressInfo = addressInfo;
    }

    public CfPaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(CfPaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public CfMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CfMetadata metadata) {
        this.metadata = metadata;
    }
}
