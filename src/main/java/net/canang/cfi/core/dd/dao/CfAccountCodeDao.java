package net.canang.cfi.core.dd.dao;

import net.canang.cfi.core.dd.model.CfAccountCode;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfAccountCodeDao {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    CfAccountCode findById(Long id);

    CfAccountCode findByCode(String code);

    List<CfAccountCode> find(Integer offset, Integer limit);

    List<CfAccountCode> find(String filter, Integer offset, Integer limit);

    Integer count();

    Integer count(String filter);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    void save(CfAccountCode account, CfUser user);

    void update(CfAccountCode account, CfUser user);

    void deactivate(CfAccountCode account, CfUser user);

    void remove(CfAccountCode account, CfUser user);
}
