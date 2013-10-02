package net.canang.cfi.core.dd.dao;

import net.canang.cfi.core.dd.model.CfCityCode;
import net.canang.cfi.core.dd.model.CfStateCode;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfCityCodeDao {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    CfCityCode findByCode(String code);

    CfCityCode findById(Long id);

    List<CfCityCode> find();

    List<CfCityCode> find(Integer offset, Integer limit);

    List<CfCityCode> find(String filter, Integer offset, Integer limit);

    List<CfCityCode> find(CfStateCode stateCode, Integer offset, Integer limit);

    List<CfCityCode> find(CfStateCode stateCode, String filter, Integer offset, Integer limit);

    Integer count();

    Integer count(String filter);

    Integer count(CfStateCode stateCode, String filter);


    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    CfCityCode save(CfCityCode cityCode, CfUser user);

    CfCityCode update(CfCityCode cityCode, CfUser user);

    CfCityCode deactivate(CfCityCode cityCode, CfUser user);

    void remove(CfCityCode cityCode, CfUser user);

}
