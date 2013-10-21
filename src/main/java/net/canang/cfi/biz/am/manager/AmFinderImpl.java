package net.canang.cfi.biz.am.manager;

import net.canang.cfi.biz.integration.springsecurity.CfUserDetails;
import net.canang.cfi.core.am.dao.CfModuleDao;
import net.canang.cfi.core.am.model.CfModule;
import net.canang.cfi.core.am.model.CfSubModule;
import net.canang.cfi.core.so.model.CfUser;
import org.apache.log4j.Logger;
import org.perf4j.aop.Profiled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.acls.model.SidRetrievalStrategy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.lang.Boolean.TRUE;

/**
 * @author rafizan.baharum
 * @since 10/20/13
 */
@Component("amFinder")
public class AmFinderImpl implements AmFinder {

    private static final Logger log = Logger.getLogger(AmFinderImpl.class);

    @Autowired
    private SidRetrievalStrategy sidRetrievalStrategy;

    @Autowired
    private CfModuleDao moduleDao;


    // ====================================================================================================
    // MODULE METHODS
    // ====================================================================================================


    @Override
    public CfModule findModuleById(Long id) {
        return moduleDao.findById(id);
    }

    @Override
    public CfSubModule findSubModuleById(Long id) {
        return moduleDao.findSubModuleById(id);
    }

    @Override
    public CfModule findModuleByCode(String code) {
        return moduleDao.findByCode(code);
    }

    @Override
    public CfSubModule findSubModuleByCode(String code) {
        return moduleDao.findSubModuleByCode(code);
    }

    @Override
    public List<CfModule> findModules() {
        return moduleDao.find();
    }

    @Override
    public List<CfModule> findModules(Integer offset, Integer limit) {
        return moduleDao.find(offset, limit);
    }

    @Override
    public List<CfModule> findModules(String filter, Integer offset, Integer limit) {
        return moduleDao.find(filter, offset, limit);
    }

    @Override
    public Integer countModule() {
        return moduleDao.count();
    }

    @Override
    public Integer countModule(String filter) {
        return moduleDao.count(filter);
    }

    @Override
    public List<CfSubModule> findSubModules(CfModule module) {
        return moduleDao.findSubModules(module);
    }

    @Override
    public List<CfSubModule> findSubModules(CfModule module, Integer offset, Integer limit) {
        return moduleDao.findSubModules(module, offset, limit);
    }


    // ====================================================================================================
    // AUTHORIZED MODULE/SUB MODULE
    // ====================================================================================================

    @Override
    public CfUser findAuthorizedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof UserDetails) {
            return ((CfUserDetails) auth.getPrincipal()).getUser();
        } else {
            return null;
        }
    }

    @Override
    public List<CfModule> findAuthorizedModules() {
        List<Sid> sids = sidRetrievalStrategy.getSids(SecurityContextHolder.getContext().getAuthentication());
        List<String> names = new ArrayList<String>();
        for (Sid sid : sids) {
            if (sid instanceof PrincipalSid)
                names.add(((PrincipalSid) sid).getPrincipal());
        }
        log.debug("==============================");
        for (String name : names) {
            log.debug("Group: " + name);
        }
        log.debug("==============================");
        List<CfModule> authorized = moduleDao.findAuthorized(names);
        log.debug("==============================");
        for (CfModule fsModule : authorized) {
            log.debug("authorized module: " + fsModule.getCode());
        }
        log.debug("==============================");
        return authorized;
    }

    @Profiled
    @Override
    public HashMap<String, Boolean> findAuthorizedSubModules() {
        HashMap<String, Boolean> subModules = new HashMap<String, Boolean>();
        List<Sid> sids = sidRetrievalStrategy.getSids(SecurityContextHolder.getContext().getAuthentication());
        List<String> names = new ArrayList<String>();
        // iterate sids
        for (Sid sid : sids) {
            if (sid instanceof PrincipalSid)
                names.add(((PrincipalSid) sid).getPrincipal());
        }

        // get map of authorized submodules
        Map<Integer, Set<CfSubModule>> authorizedSubModule = moduleDao.findAuthorizedSubModule(names);
        Set<CfSubModule> view = authorizedSubModule.get(1);
        Set<CfSubModule> create = authorizedSubModule.get(2);
        Set<CfSubModule> update = authorizedSubModule.get(3);
        Set<CfSubModule> delete = authorizedSubModule.get(4);
        Set<CfSubModule> cancel = authorizedSubModule.get(5);
        Set<CfSubModule> print = authorizedSubModule.get(6);
        Set<CfSubModule> admin = authorizedSubModule.get(7);

        for (CfSubModule subModule : view) {
            if (null != subModule)
                subModules.put(subModule.getModule() + ":" + subModule.getCode() + ":VIEW", TRUE);
        }
        for (CfSubModule subModule : create) {
            if (null != subModule)
                subModules.put(subModule.getModule() + ":" + subModule.getCode() + ":CREATE", TRUE);
        }
        for (CfSubModule subModule : update) {
            if (null != subModule)
                subModules.put(subModule.getModule() + ":" + subModule.getCode() + ":UPDATE", TRUE);
        }
        for (CfSubModule subModule : delete) {
            if (null != subModule)
                subModules.put(subModule.getModule() + ":" + subModule.getCode() + ":DELETE", TRUE);
        }
        for (CfSubModule subModule : cancel) {
            if (null != subModule)
                subModules.put(subModule.getModule() + ":" + subModule.getCode() + ":CANCEL", TRUE);
        }
        for (CfSubModule subModule : print) {
            if (null != subModule)
                subModules.put(subModule.getModule() + ":" + subModule.getCode() + ":PRINT", TRUE);
        }
        for (CfSubModule subModule : admin) {
            if (null != subModule)
                subModules.put(subModule.getModule() + ":" + subModule.getCode() + ":ADMIN", TRUE);
        }
        return subModules;
    }
}
