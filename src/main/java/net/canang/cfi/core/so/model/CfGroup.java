package net.canang.cfi.core.so.model;

import java.util.Set;

/**
 * @author canang.technologies
 * @since 7/10/13
 */
public interface CfGroup extends CfPrincipal {

    public static final String ROOT_GROUP = "GRP_USR";


    Set<CfGroupMember> getMembers();

    void setMembers(Set<CfGroupMember> members);

}
