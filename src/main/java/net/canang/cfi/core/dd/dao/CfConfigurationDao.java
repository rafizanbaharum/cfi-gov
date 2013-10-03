package net.canang.cfi.core.dd.dao;

import net.canang.cfi.core.dd.model.CfConfiguration;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfConfigurationDao {

    // ====================================================================================================
    // HELPERS
    // ====================================================================================================

    CfConfiguration newInstance();

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    CfConfiguration findById(Long id);

    CfConfiguration findByKey(String key);

    List<CfConfiguration> find(Integer offset, Integer limit);

    List<CfConfiguration> find(String filter, Integer offset, Integer limit);

    Integer count();

    Integer count(String filter);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    void save(CfConfiguration configuration, CfUser user);

    void update(CfConfiguration configuration, CfUser user);

    void deactivate(CfConfiguration configuration, CfUser user);

    void remove(CfConfiguration configuration, CfUser user);

    boolean isLocal();
}
