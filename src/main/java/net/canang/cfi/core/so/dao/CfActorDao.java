package net.canang.cfi.core.so.dao;


import net.canang.cfi.core.so.model.CfActor;
import net.canang.cfi.core.so.model.CfActorType;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * @author canang.technologies
 * @since 7/20/13
 */
public interface CfActorDao {

    CfActor findById(Long id);

    CfActor findByIdentityNo(String identityNo);

    CfActor findByNricNo(String nricNo);

    List<CfActor> find(Integer offset, Integer limit);

    List<CfActor> find(String filter, Integer offset, Integer limit);

    List<CfActor> find(CfActorType type, Integer offset, Integer limit);

    List<CfActor> find(CfActorType type, String filter, Integer offset, Integer limit);

    Integer count();

    Integer count(String filter);

    Integer count(CfActorType type);

    void save(CfActor actor, CfUser user);

    void update(CfActor actor, CfUser user);

    void deactivate(CfActor actor, CfUser user);
}
