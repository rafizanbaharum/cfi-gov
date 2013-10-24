package net.canang.cfi.biz.so.manager;

import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.dd.model.CfDepartmentCode;
import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.dd.model.CfReferenceNo;
import net.canang.cfi.core.exception.LockedGroupException;
import net.canang.cfi.core.exception.RecursiveGroupException;
import net.canang.cfi.core.so.model.*;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
public interface SoManager {

    // ====================================================================================================
    // USER
    // ====================================================================================================

    void saveUser(CfUser user);

    void updateUser(CfUser user);

    // ====================================================================================================
    // GROUP
    // ====================================================================================================

    void saveGroup(CfGroup group);

    void updateGroup(CfGroup grop);

    void removeGroupMember(CfGroup group, CfPrincipal principal) throws LockedGroupException;

    void addGroupMember(CfGroup group, CfPrincipal principal) throws RecursiveGroupException, LockedGroupException;

    void updateGroupMembers(CfPrincipal principal, List<CfGroup> groups) throws Exception;

    void updateGroupMembers(CfPrincipal principal, String[] groups) throws Exception;

    void updateGroupMembers(CfGroup group, String[] principals) throws Exception;

    // ====================================================================================================
    // ACTOR
    // ====================================================================================================

    void saveActor(CfActor actor);

    void updateActor(CfActor actor);

    void deactivateActor(CfActor actor);

    // ====================================================================================================
    // REFERENCE NO
    // ====================================================================================================

    void saveReferenceNo(CfReferenceNo referenceNo);

    void updateReferenceNo(CfReferenceNo referenceNo);

    void deactivateReferenceNo(CfReferenceNo referenceNo);

    String generatePlainReferenceNo(String code);

    String generateReferenceNo(String code);

    String generateFormattedReferenceNo(String code, CfCostCenter costCenter,
                                        CfCostCenter requester, CfPeriod period,
                                        CfDepartmentCode department);

    // ====================================================================================================
    // CONFIGURATION
    // ====================================================================================================

    void saveConfiguration(CfConfiguration configuration);

    void updateConfiguration(CfConfiguration configuration);

    void deactivateConfiguration(CfConfiguration configuration);

}
