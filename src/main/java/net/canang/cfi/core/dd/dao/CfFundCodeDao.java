package net.canang.cfi.core.dd.dao;

import net.canang.cfi.core.dd.model.CfFundCode;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfFundCodeDao {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    CfFundCode findById(Long id);

    CfFundCode findByCode(String code);

    List<CfFundCode> find(Integer offset, Integer limit);

    List<CfFundCode> find(String filter, String[] funds, Integer offset, Integer limit);

    Integer count();

    Integer count(String filter);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    void save(CfFundCode fund, CfUser user);

    void update(CfFundCode fund, CfUser user);

    void deactivate(CfFundCode fund, CfUser user);

    void remove(CfFundCode fund, CfUser user);

}
