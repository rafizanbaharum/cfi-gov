package net.canang.cfi.core.dd.dao;

import net.canang.cfi.core.dd.model.CfCurrencyCode;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfCurrencyCodeDao {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    CfCurrencyCode findById(Long id);

    CfCurrencyCode findByCode(String code);

    List<CfCurrencyCode> find(Integer offset, Integer limit);

    List<CfCurrencyCode> find(String filter, Integer offset, Integer limit);

    Integer count();

    Integer count(String filter);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    void save(CfCurrencyCode currency, CfUser user);

    void update(CfCurrencyCode currency, CfUser user);

    void deactivate(CfCurrencyCode currency, CfUser user);

    void remove(CfCurrencyCode currency, CfUser user);

}
