package net.canang.cfi.core.am.model.impl;

import net.canang.cfi.core.am.model.CfAclEntry;
import net.canang.cfi.core.am.model.CfAclObjectIdentity;
import net.canang.cfi.core.am.model.CfAclSid;

import javax.persistence.*;

/**
 * User: Faizal Abdul Manan
 * Date: 5/13/11
 */
@Entity(name = "AclEntry")
@Table(name = "ACL_ENTRY")
public class CfAclEntryImpl implements CfAclEntry {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "ACE_ORDER", nullable = false)
    private Integer aclOrder;

    @Column(name = "MASK", nullable = false)
    private Integer mask;

    @Column(name = "GRANTING", nullable = false)
    private Boolean granting;

    @Column(name = "AUDIT_SUCCESS", nullable = false)
    private Boolean auditSuccess;

    @Column(name = "AUDIT_FAILURE", nullable = false)
    private Boolean auditFailure;

    @ManyToOne(targetEntity = CfAclSidImpl.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "SID")
    private CfAclSid sid;

    @ManyToOne(targetEntity = CfAclObjectIdentityImpl.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ACL_OBJECT_IDENTITY")
    private CfAclObjectIdentity objectIdentity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAclOrder() {
        return aclOrder;
    }

    public void setAclOrder(Integer aclOrder) {
        this.aclOrder = aclOrder;
    }

    public Integer getMask() {
        return mask;
    }

    public void setMask(Integer mask) {
        this.mask = mask;
    }

    public Boolean getGranting() {
        return granting;
    }

    public void setGranting(Boolean granting) {
        this.granting = granting;
    }

    public Boolean getAuditSuccess() {
        return auditSuccess;
    }

    public void setAuditSuccess(Boolean auditSuccess) {
        this.auditSuccess = auditSuccess;
    }

    public Boolean getAuditFailure() {
        return auditFailure;
    }

    public void setAuditFailure(Boolean auditFailure) {
        this.auditFailure = auditFailure;
    }

    public CfAclSid getSid() {
        return sid;
    }

    public void setSid(CfAclSid sid) {
        this.sid = sid;
    }

    public CfAclObjectIdentity getObjectIdentity() {
        return objectIdentity;
    }

    public void setObjectIdentity(CfAclObjectIdentity objectIdentity) {
        this.objectIdentity = objectIdentity;
    }
}
