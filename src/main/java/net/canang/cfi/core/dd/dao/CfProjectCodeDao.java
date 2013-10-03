package net.canang.cfi.core.dd.dao;

import net.canang.cfi.core.dd.model.CfProjectCode;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfProjectCodeDao {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    CfProjectCode findById(Long id);

    CfProjectCode findByCode(String code);

    List<CfProjectCode> find();

    List<CfProjectCode> find(Integer offset, Integer limit);

    List<CfProjectCode> find(String filter, Integer offset, Integer limit);

    Integer count();

    Integer count(String filter);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    void save(CfProjectCode project, CfUser user);

    void update(CfProjectCode project, CfUser user);

    void deactivate(CfProjectCode project, CfUser user);

    void remove(CfProjectCode project, CfUser user);

}
