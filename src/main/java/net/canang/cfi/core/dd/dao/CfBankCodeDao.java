package net.canang.cfi.core.dd.dao;

import net.canang.cfi.core.dd.model.CfBankCode;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfBankCodeDao {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    CfBankCode findById(Long id);

    CfBankCode findByCode(String code);

    List<CfBankCode> find();

    List<CfBankCode> find(Integer offset, Integer limit);

    List<CfBankCode> find(String filter, Integer offset, Integer limit);

    Integer count();

    Integer count(String filter);


    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    CfBankCode save(CfBankCode bank, CfUser user);

    CfBankCode update(CfBankCode bank, CfUser user);

    CfBankCode deactivate(CfBankCode bank, CfUser user);

    void remove(CfBankCode bank, CfUser user);

}
