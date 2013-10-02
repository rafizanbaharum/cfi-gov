package net.canang.cfi.core.am.model;

/**
 * User: Faizal Abdul Manan
 * Date: 5/13/11
 */
public interface CfAclEntry {

    Long getId();

    Integer getAclOrder();

    Integer getMask();

    Boolean getGranting();

    Boolean getAuditSuccess();

    Boolean getAuditFailure();

    CfAclObjectIdentity getObjectIdentity();

    CfAclSid getSid();

}
