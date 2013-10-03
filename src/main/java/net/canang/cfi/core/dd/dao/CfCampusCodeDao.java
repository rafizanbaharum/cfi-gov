package net.canang.cfi.core.dd.dao;

import net.canang.cfi.core.dd.model.CfCampusCode;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfCampusCodeDao {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    CfCampusCode findById(Long id);

    CfCampusCode findByCode(String code);

    List<CfCampusCode> find(Integer offset, Integer limit);

    List<CfCampusCode> find(String filter, Integer offset, Integer limit);

    Integer count();

    Integer count(String filter);


    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    void save(CfCampusCode campus, CfUser user);

    void update(CfCampusCode campus, CfUser user);

    void deactivate(CfCampusCode campus, CfUser user);

    void remove(CfCampusCode campus, CfUser user);

}
