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

    CfCountryCode findById(Long id);

    CfCountryCode findByCode(String code);

    CfCountryCode findByName(String name);

    List<CfCountryCode> find();

    List<CfCountryCode> find(Integer offset, Integer limit);

    List<CfCountryCode> find(String filter, Integer offset, Integer limit);

    Integer count();

    Integer count(String filter);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    void save(CfCountryCode countryCode, CfUser user);

    void update(CfCountryCode countryCode, CfUser user);

    void deactivate(CfCountryCode countryCode, CfUser user);

    void remove(CfCountryCode countryCode, CfUser user);
}
