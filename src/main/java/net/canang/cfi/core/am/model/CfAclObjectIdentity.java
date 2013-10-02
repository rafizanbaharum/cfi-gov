package net.canang.cfi.core.am.model;

import java.util.Set;

/**
 * User: Faizal Abdul Manan
 * Date: 5/13/11
 */
public interface CfAclObjectIdentity {

    Long getId();

    Long getObjectIdIdentity();

    Long getParentObject();

    Boolean getEntriesInheriting();

    CfAclClass getObjectClass();

    CfAclSid getOwnerSid();

    Set<CfAclEntry> getEntries();

}
