package net.canang.cfi.biz.am.manager;

import net.canang.cfi.core.am.model.CfModule;
import net.canang.cfi.core.am.model.CfSubModule;
import net.canang.cfi.core.so.model.CfUser;

import java.util.HashMap;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/20/13
 */
public interface AmFinder {

    // ====================================================================================================
    // MODULE
    // ====================================================================================================
    CfModule findModuleById(Long id);

    CfSubModule findSubModuleById(Long id);

    CfModule findModuleByCode(String code);

    CfSubModule findSubModuleByCode(String code);

    List<CfSubModule> findSubModules(CfModule module);

    List<CfSubModule> findSubModules(CfModule module, Integer offset, Integer limit);

    List<CfModule> findModules();

    List<CfModule> findModules(Integer offset, Integer limit);

    List<CfModule> findModules(String filter, Integer offset, Integer limit);

    Integer countModule();

    Integer countModule(String filter);


    // ====================================================================================================
    // AUTHORIZED MODULE/SUBMODULES
    // ====================================================================================================

    CfUser findAuthorizedUser();

    List<CfModule> findAuthorizedModules();

    HashMap<String, Boolean> findAuthorizedSubModules();

}
