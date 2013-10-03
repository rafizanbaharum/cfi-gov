package net.canang.cfi.core.dd.dao;

import net.canang.cfi.core.dd.model.*;
import net.canang.cfi.core.so.model.CfPrincipal;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;
import java.util.Set;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfCostCenterDao {

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    CfCostCenter findById(Long id);

    CfCostCenter findByCode(String code);

    List<CfCostCenter> find(Integer offset, Integer limit);

    List<CfCostCenter> find(CfFundCode fund, Integer offset, Integer limit);

    List<CfCostCenter> find(String filter, CfFundCode fund, Integer offset, Integer limit);

    List<CfCostCenter> find(CfDepartmentCode responsibilityCenter, Integer offset, Integer limit);

    List<CfCostCenter> find(CfProjectCode project, Integer offset, Integer limit);

    List<CfCostCenter> find(CfSubProjectCode subProject, Integer offset, Integer limit);

    List<CfCostCenter> find(CfFundCode fund, CfDepartmentCode responsibilityCenter, Integer offset, Integer limit);

    List<CfCostCenter> find(CfFundCode fund, CfDepartmentCode responsibilityCenter, CfProjectCode project, Integer offset, Integer limit);

    List<CfCostCenter> find();

    List<CfCostCenter> find(String filter, Integer offset, Integer limit);

    List<CfCostCenter> find(List<CfPrincipal> principal);

    List<CfCostCenter> find(List<CfPrincipal> principal, String filter);

    List<CfCostCenter> find(List<CfPrincipal> principal, Integer offset, Integer limit);

    List<CfCostCenter> find(List<CfPrincipal> principal, String filter, Integer offset, Integer limit);

    List<CfCostCenter> find(List<CfPrincipal> principal, String filter, String[] funds, Integer offset, Integer limit);

    List<CfCostCenter> find(Set<String> principals);

    List<CfCostCenter> find(Set<String> principals, Integer offset, Integer limit);

    Integer count();

    Integer count(String filter);

    Integer count(String filter, CfFundCode fund);

    Integer count(List<CfPrincipal> principals);

    Integer count(Set<String> principals);

    boolean isExist(String code);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    void save(CfCostCenter costCenter, CfUser user);

    void update(CfCostCenter costCenter, CfUser user);

    void deactivate(CfCostCenter costCenter, CfUser user);

    void remove(CfCostCenter costCenter, CfUser user);
}
