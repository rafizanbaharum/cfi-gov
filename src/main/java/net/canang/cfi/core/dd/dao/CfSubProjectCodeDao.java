package net.canang.cfi.core.dd.dao;

import net.canang.cfi.core.dd.model.CfSubProjectCode;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfSubProjectCodeDao {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    CfSubProjectCode findById(Long id);

    CfSubProjectCode findByCode(String code);

    List<CfSubProjectCode> find();

    List<CfSubProjectCode> find(Integer offset, Integer limit);

    List<CfSubProjectCode> find(String filter, Integer offset, Integer limit);

    Integer count();

    Integer count(String filter);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    void save(CfSubProjectCode subProject, CfUser user);

    void update(CfSubProjectCode subProject, CfUser user);

    void deactivate(CfSubProjectCode subProject, CfUser user);

    void remove(CfSubProjectCode subProject, CfUser user);

}
