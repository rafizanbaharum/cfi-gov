package net.canang.cfi.core.so.dao;

import net.canang.cfi.core.so.model.CfPrincipal;
import net.canang.cfi.core.so.model.CfRoleType;
import net.canang.cfi.core.so.model.CfUser;

/**
 * @author canang.technologies
 * @since 7/12/13
 */
public interface CfRoleDao {

    void grant(CfPrincipal principal, CfRoleType roleType, CfUser user);

    void grant(CfPrincipal principal, CfRoleType[] roleTypes, CfUser user);

    void revoke(CfPrincipal principal, CfRoleType roleType, CfUser user);

    void revoke(CfPrincipal principal, CfRoleType[] roleTypes, CfUser user);

    void revokeAll(CfPrincipal principal, CfUser user);

    void overwrite(CfPrincipal principal, CfRoleType roleType, CfUser user);

    void overwrite(CfPrincipal principal, CfRoleType[] roleTypes, CfUser user);

    void update(CfPrincipal principal, CfRoleType[] roleTypes, CfUser user);

}
