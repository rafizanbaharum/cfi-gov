package net.canang.cfi.core.dd.dao;

import net.canang.cfi.core.dd.model.CfCountryCode;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfCountryCodeDao {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    CfCountryCode findByCode(String code);

    CfCountryCode findById(Long id);

    List<CfCountryCode> find();

    List<CfCountryCode> find(Integer offset, Integer limit);

    List<CfCountryCode> find(String filter, Integer offset, Integer limit);

    Integer count();

    Integer count(String filter);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    CfCountryCode save(CfCountryCode countryCode, CfUser user);

    CfCountryCode update(CfCountryCode countryCode, CfUser user);

    CfCountryCode deactivate(CfCountryCode countryCode, CfUser user);

    void remove(CfCountryCode countryCode, CfUser user);

}
