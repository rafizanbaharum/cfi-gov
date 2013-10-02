package net.canang.cfi.core.dd.dao;

import net.canang.cfi.core.dd.model.CfDistrictCode;
import net.canang.cfi.core.dd.model.CfStateCode;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfDistrictCodeDao {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    CfDistrictCode findByCode(String code);

    CfDistrictCode findById(Long id);

    List<CfDistrictCode> find();

    List<CfDistrictCode> find(Integer offset, Integer limit);

    List<CfDistrictCode> find(String filter, Integer offset, Integer limit);

    List<CfDistrictCode> find(CfStateCode stateCode, Integer offset, Integer limit);

    List<CfDistrictCode> find(CfStateCode stateCode, String filter, Integer offset, Integer limit);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    CfDistrictCode save(CfDistrictCode districtCode, CfUser user);

    CfDistrictCode update(CfDistrictCode districtCode, CfUser user);

    CfDistrictCode deactivate(CfDistrictCode districtCode, CfUser user);

    void remove(CfDistrictCode districtCode, CfUser user);

}
