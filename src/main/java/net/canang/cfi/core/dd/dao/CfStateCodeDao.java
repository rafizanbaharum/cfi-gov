package net.canang.cfi.core.dd.dao;

import net.canang.cfi.core.dd.model.CfCountryCode;
import net.canang.cfi.core.dd.model.CfStateCode;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfStateCodeDao {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    CfStateCode findByCode(String code);

    CfStateCode findById(Long id);

    List<CfStateCode> find();

    List<CfStateCode> find(Integer offset, Integer limit);

    List<CfStateCode> find(String filter, Integer offset, Integer limit);

    List<CfStateCode> find(String filter, Integer offset, Integer limit, CfCountryCode countryCode);

    Integer count();

    Integer count(String filter);

    Integer count(String filter, CfCountryCode countryCode);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    CfStateCode save(CfStateCode stateCode, CfUser user);

    CfStateCode update(CfStateCode stateCode, CfUser user);

    CfStateCode deactivate(CfStateCode stateCode, CfUser user);

    void remove(CfStateCode stateCode, CfUser user);

}
