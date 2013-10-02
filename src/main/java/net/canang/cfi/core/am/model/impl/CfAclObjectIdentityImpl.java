package net.canang.cfi.core.am.model.impl;

import net.canang.cfi.core.am.model.CfAclClass;
import net.canang.cfi.core.am.model.CfAclEntry;
import net.canang.cfi.core.am.model.CfAclObjectIdentity;
import net.canang.cfi.core.am.model.CfAclSid;

import javax.persistence.*;
import java.util.Set;

/**
 * @author canang.technologies
 *         Date: 5/13/11
 */
@Entity(name = "AclObjectIdentity")
@Table(name = "ACL_OBJECT_IDENTITY")
public class CfAclObjectIdentityImpl implements CfAclObjectIdentity {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "OBJECT_ID_IDENTITY", nullable = false)
    private Long objectIdIdentity;

    @Column(name = "PARENT_OBJECT", nullable = false)
    private Long parentObject;

    @Column(name = "ENTRIES_INHERITING", nullable = false)
    private Boolean entriesInheriting;

    @ManyToOne(targetEntity = CfAclSidImpl.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "OWNER_SID")
    private CfAclSid ownerSid;

    @ManyToOne(targetEntity = CfAclClassImpl.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "OBJECT_ID_CLASS")
    private CfAclClass objectClass;

    @OneToMany(targetEntity = CfAclEntryImpl.class, mappedBy = "objectIdentity", fetch = FetchType.EAGER)
    private Set<CfAclEntry> entries;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getObjectIdIdentity() {
        return objectIdIdentity;
    }

    public void setObjectIdIdentity(Long objectIdIdentity) {
        this.objectIdIdentity = objectIdIdentity;
    }

    public Long getParentObject() {
        return parentObject;
    }

    public void setParentObject(Long parentObject) {
        this.parentObject = parentObject;
    }


    public Boolean getEntriesInheriting() {
        return entriesInheriting;
    }

    public void setEntriesInheriting(Boolean entriesInheriting) {
        this.entriesInheriting = entriesInheriting;
    }


    public CfAclSid getOwnerSid() {
        return ownerSid;
    }


    public void setOwnerSid(CfAclSid ownerSid) {
        this.ownerSid = ownerSid;
    }

    public CfAclClass getObjectClass() {
        return objectClass;
    }

    public void setObjectClass(CfAclClass objectClass) {
        this.objectClass = objectClass;
    }

    public Set<CfAclEntry> getEntries() {
        return entries;
    }

    public void setEntries(Set<CfAclEntry> entries) {
        this.entries = entries;
    }
}

