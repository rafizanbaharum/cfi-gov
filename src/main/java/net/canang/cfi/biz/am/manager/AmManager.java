package net.canang.cfi.biz.am.manager;

import net.canang.cfi.biz.integration.springacl.CfAclPermission;
import net.canang.cfi.core.am.model.CfModule;
import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.exception.LockedGroupException;
import net.canang.cfi.core.exception.RecursiveGroupException;
import net.canang.cfi.core.so.model.*;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/20/13
 */
public interface AmManager {

    // =============================================================================
    // GROUP METHODS
    // =============================================================================

    void removeGroupMember(CfGroup group, CfPrincipal principal) throws LockedGroupException;

    void addGroupMember(CfGroup group, CfPrincipal principal) throws RecursiveGroupException, LockedGroupException;

    void updateGroupMembers(CfPrincipal principal, List<CfGroup> groups) throws Exception;

    void updateGroupMembers(CfPrincipal principal, String[] groups) throws Exception;

    void updateGroupMembers(CfGroup group, String[] principals) throws Exception;

    // =============================================================================
    // PERMISSION METHODS
    // =============================================================================

    void grantPermission(String entityName, Long id, CfPrincipal principal, CfAclPermission permission);

    void grantPermission(String entityName, Long id, List<CfPrincipal> principals, CfAclPermission permission);

    void addPermission(CfMetaObject object, CfAclPermission permission);

    void addPermission(CfMetaObject object, CfPrincipal principal, CfAclPermission permission);

    void deletePermission(CfMetaObject object, CfAclPermission permission);

    void deletePermission(CfMetaObject object, CfPrincipal principal, CfAclPermission permission);

    boolean hasPermission(CfMetaObject object, CfAclPermission permission);

    boolean hasPermission(CfMetaObject object, CfPrincipal principal, CfAclPermission permission);

    boolean checkPermission(CfMetaObject object, CfPrincipal principal, CfAclPermission permission);

    boolean checkPermissionByProxy(CfMetaObject object, CfPrincipal principal, CfAclPermission permission);

    // =============================================================================
    // MODULE METHODS
    // =============================================================================

    void saveModule(CfModule module);

    void updateModule(CfModule module);

}
