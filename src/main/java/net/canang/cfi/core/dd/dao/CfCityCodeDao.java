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

    CfCityCode findById(Long id);

    CfCityCode findByCode(String code);

    CfCityCode findByName(String name);

    CfCityCode findByNameAndState(String name, CfStateCode stateCode);

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

    void save(CfCityCode cityCode, CfUser user);

    void update(CfCityCode cityCode, CfUser user);

    void deactivate(CfCityCode cityCode, CfUser user);

    void remove(CfCityCode cityCode, CfUser user);

}
