package net.canang.cfi.core.am.dao;

import net.canang.cfi.core.am.model.CfModule;
import net.canang.cfi.core.am.model.CfSubModule;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
public interface CfModuleDao {

    // finders

    CfModule findById(Long id);

    CfModule findByCode(String code);

    CfSubModule findSubModuleById(Long id);

    List<CfModule> findAuthorized(List<String> sid);

    Map<Integer, Set<CfSubModule>> findAuthorizedSubModule(List<String> sid);

    List<CfModule> find();

    List<CfModule> find(Integer offset, Integer limit);

    List<CfModule> find(String filter, Integer offset, Integer limit);

    List<CfSubModule> findSubModules(CfModule module);

    List<CfSubModule> findSubModules(CfModule module, Integer offset, Integer limit);

    CfSubModule findSubModuleByCode(String code);

    CfSubModule findSubModuleByDescription(String description);

    Integer count();

    Integer count(String filter);

    boolean isExist(String code);

    // cruds

    void save(CfModule module, CfUser user);

    void update(CfModule module, CfUser user);

    void deactivate(CfModule module, CfUser user);

    void remove(CfModule module, CfUser user);

    void addSubModule(CfModule module, CfSubModule subModule, CfUser user);

    void addSubModules(CfModule module, List<CfSubModule> subModules, CfUser user);

    void removeSubModule(CfModule module, CfSubModule subModule, CfUser user);

    void removeSubModules(CfModule module, List<CfSubModule> subModules, CfUser user);

}

