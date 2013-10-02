package net.canang.cfi.core.so.model.impl;

import net.canang.cfi.core.so.model.CfGroup;
import net.canang.cfi.core.so.model.CfGroupMember;
import net.canang.cfi.core.so.model.CfPrincipalType;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * @author canang.technologies
 * @since 7/10/13
 */
@Table(name = "CF_GROP")
@Entity(name = "CfGroup")
public class CfGroupImpl extends CfPrincipalImpl implements CfGroup {

    @OneToMany(targetEntity = CfGroupMemberImpl.class, mappedBy = "group")
    Set<CfGroupMember> members;

    public CfGroupImpl() {
        setPrincipalType(CfPrincipalType.GROUP);
    }

    public Set<CfGroupMember> getMembers() {
        return members;
    }

    public void setMembers(Set<CfGroupMember> members) {
        this.members = members;
    }
}
