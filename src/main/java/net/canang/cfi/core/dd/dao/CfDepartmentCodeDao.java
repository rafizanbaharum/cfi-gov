package net.canang.cfi.core.dd.dao;

import net.canang.cfi.core.dd.model.CfDepartmentCode;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfDepartmentCodeDao {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    CfDepartmentCode findById(Long id);

    CfDepartmentCode findByCode(String code);

    List<CfDepartmentCode> findRoots();

    List<CfDepartmentCode> find(Integer offset, Integer limit);

    List<CfDepartmentCode> find(String filter, Integer offset, Integer limit);

    List<CfDepartmentCode> find(CfDepartmentCode parent, Integer offset, Integer limit);

    Integer count();

    Integer count(String filter);

    boolean isExist(String code);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    CfDepartmentCode save(CfDepartmentCode responsibilityCenter, CfUser user);

    CfDepartmentCode update(CfDepartmentCode responsibilityCenter, CfUser user);

    CfDepartmentCode deactivate(CfDepartmentCode responsibilityCenter, CfUser user);

    void remove(CfDepartmentCode responsibilityCenter, CfUser user);

}
