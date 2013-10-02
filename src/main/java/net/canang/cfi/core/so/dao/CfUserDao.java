package net.canang.cfi.core.so.dao;

import net.canang.cfi.core.so.model.CfActor;
import net.canang.cfi.core.so.model.CfGroup;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * @author canang.technologies
 * @since 7/12/13
 */
public interface CfUserDao {

    CfUser findById(Long id);

    CfUser findByUsername(String username);

    CfUser findByActor(CfActor actor);

    CfUser findByRealName(String realname);

    List<CfUser> find(Integer offset, Integer limit);

    List<CfUser> find(String filter, Integer offset, Integer limit);

    List<CfGroup> findUserGroups(CfUser user);

    Integer count();

    Integer count(String filter);

    boolean isExists(String username);

    // cruds

    void save(CfUser suser, CfUser user);

    void update(CfUser suser, CfUser user);

    void deactivate(CfUser suser, CfUser user);

    void remove(CfUser suser, CfUser user);


}
