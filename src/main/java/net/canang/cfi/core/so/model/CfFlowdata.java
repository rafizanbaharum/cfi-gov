package net.canang.cfi.core.so.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

/**
 * @author canang.technologies
 * @since 7/10/13
 */
@Embeddable
public class CfFlowdata {

    @Column(name = "DF_ID")
    private Long drafter;

    @Column(name = "DF_TS")
    private Date draftedDate;

    @Column(name = "RQ_ID")
    private Long requester;

    @Column(name = "RQ_TS")
    private Date requestedDate;

    @Column(name = "RG_ID")
    private Long registerer;

    @Column(name = "RG_TS")
    private Date registeredDate;

    @Column(name = "VF_ID")
    private Long verifier;

    @Column(name = "VF_TS")
    private Date verifiedDate;

    @Column(name = "AV_ID")
    private Long approver;

    @Column(name = "AV_TS")
    private Date approvedDate;

    @Column(name = "RM_ID")
    private Long remover;

    @Column(name = "RM_TS")
    private Date removedDate;

    @Column(name = "CL_ID")
    private Long canceller;

    @Column(name = "CL_TS")
    private Date cancelledDate;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "F_ST")
    private CfFlowState state;

    public Long getDrafter() {
        return drafter;
    }

    public void setDrafter(Long drafter) {
        this.drafter = drafter;
    }

    public Date getDraftedDate() {
        return draftedDate;
    }

    public void setDraftedDate(Date draftedDate) {
        this.draftedDate = draftedDate;
    }

    public Long getRequester() {
        return requester;
    }

    public void setRequester(Long requester) {
        this.requester = requester;
    }

    public Date getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
    }

    public Long getRegisterer() {
        return registerer;
    }

    public void setRegisterer(Long registerer) {
        this.registerer = registerer;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Long getVerifier() {
        return verifier;
    }

    public void setVerifier(Long verifier) {
        this.verifier = verifier;
    }

    public Date getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(Date verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    public Long getApprover() {
        return approver;
    }

    public void setApprover(Long approver) {
        this.approver = approver;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public Long getRemover() {
        return remover;
    }

    public void setRemover(Long remover) {
        this.remover = remover;
    }

    public Date getRemovedDate() {
        return removedDate;
    }

    public void setRemovedDate(Date removedDate) {
        this.removedDate = removedDate;
    }

    public Long getCanceller() {
        return canceller;
    }

    public void setCanceller(Long canceller) {
        this.canceller = canceller;
    }

    public Date getCancelledDate() {
        return cancelledDate;
    }

    public void setCancelledDate(Date cancelledDate) {
        this.cancelledDate = cancelledDate;
    }

    public CfFlowState getState() {
        return state;
    }

    public void setState(CfFlowState state) {
        this.state = state;
    }
}
