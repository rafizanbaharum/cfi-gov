package net.canang.cfi.core.dm.model.impl;

import net.canang.cfi.core.dd.model.CfAddressInfo;
import net.canang.cfi.core.dd.model.CfConsumerInfo;
import net.canang.cfi.core.dd.model.CfPaymentInfo;
import net.canang.cfi.core.dm.model.*;
import net.canang.cfi.core.so.model.CfActor;
import net.canang.cfi.core.so.model.CfMetadata;
import net.canang.cfi.core.so.model.impl.CfActorImpl;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Table(name = "CF_DBSM_ITEM")
@Entity(name = "CfDisbursementItem")
public class CfDisbursementItemImpl implements CfDisbursementItem, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_CF_DBSM_ITEM")
    @SequenceGenerator(name = "SEQ_CF_DBSM_ITEM", sequenceName = "SEQ_CF_DBSM_ITEM", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "REFERENCE_NO")
    private String referenceNo;

    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @NotNull
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "STATUS")
    private CfDisbursementStatus status;

    @ManyToOne(targetEntity = CfActorImpl.class)
    @JoinColumn(name = "ACTOR_ID")
    private CfActor actor;

    @ManyToOne(targetEntity = CfDisbursementImpl.class)
    @JoinColumn(name = "DISBURSEMENT_ID")
    private CfDisbursement disbursement;

    @ManyToOne(targetEntity = CfVoucherImpl.class)
    @JoinColumn(name = "VOUCHER_ID")
    private CfVoucher voucher;

    @ManyToOne(targetEntity = CfVoucherRecipientImpl.class)
    @JoinColumn(name = "VOUCHER_RECIPIENT_ID")
    private CfVoucherRecipient voucherRecipient;

    @Embedded
    private CfConsumerInfo consumerInfo;

    @Embedded
    private CfAddressInfo addressInfo;

    @Embedded
    private CfPaymentInfo paymentInfo;

    @Embedded
    private CfMetadata metadata;

    public CfDisbursementItemImpl() {
        setStatus(CfDisbursementStatus.NEW);
    }

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

    public CfDisbursementStatus getStatus() {
        return status;
    }

    public void setStatus(CfDisbursementStatus status) {
        this.status = status;
    }

    public CfActor getActor() {
        return actor;
    }

    public void setActor(CfActor actor) {
        this.actor = actor;
    }

    public CfDisbursement getDisbursement() {
        return disbursement;
    }

    public void setDisbursement(CfDisbursement disbursement) {
        this.disbursement = disbursement;
    }

    public CfVoucher getVoucher() {
        return voucher;
    }

    public void setVoucher(CfVoucher voucher) {
        this.voucher = voucher;
    }

    public CfVoucherRecipient getVoucherRecipient() {
        return voucherRecipient;
    }

    public void setVoucherRecipient(CfVoucherRecipient voucherRecipient) {
        this.voucherRecipient = voucherRecipient;
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
