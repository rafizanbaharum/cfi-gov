package net.canang.cfi.web.am.server;

import com.extjs.gxt.ui.client.data.BaseListLoadResult;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import net.canang.cfi.biz.am.manager.AmFinder;
import net.canang.cfi.biz.am.manager.AmManager;
import net.canang.cfi.biz.dd.manager.DdFinder;
import net.canang.cfi.biz.dd.manager.DdManager;
import net.canang.cfi.biz.integration.springacl.CfAclPermission;
import net.canang.cfi.biz.so.manager.SoFinder;
import net.canang.cfi.biz.so.manager.SoManager;
import net.canang.cfi.core.am.model.CfModule;
import net.canang.cfi.core.am.model.CfSubModule;
import net.canang.cfi.core.am.model.impl.CfModuleImpl;
import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.dd.model.CfCostCenterMember;
import net.canang.cfi.core.exception.LockedGroupException;
import net.canang.cfi.core.exception.RecursiveGroupException;
import net.canang.cfi.core.so.model.*;
import net.canang.cfi.web.am.client.AmDelegate;
import net.canang.cfi.web.am.client.exception.UserNotInSessionException;
import net.canang.cfi.web.am.client.model.AuthorizedUserModel;
import net.canang.cfi.web.am.client.model.ModuleModel;
import net.canang.cfi.web.am.client.model.ModulePermissionModel;
import net.canang.cfi.web.am.client.model.SubModulePermissionModel;
import net.canang.cfi.web.dd.client.model.CostCenterModel;
import net.canang.cfi.web.dd.server.DdConverter;
import net.canang.cfi.web.server.common.AutoInjectingRemoteServiceServlet;
import net.canang.cfi.web.so.client.exception.GroupException;
import net.canang.cfi.web.so.client.model.GroupModel;
import net.canang.cfi.web.so.client.model.PrincipalModel;
import net.canang.cfi.web.so.client.model.PrincipalType;
import net.canang.cfi.web.so.client.model.UserModel;
import net.canang.cfi.web.so.server.SoConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import java.text.DateFormat;
import java.util.*;

import static net.canang.cfi.biz.integration.springacl.CfAclPermission.*;

/**
 * @author rafizan.baharum
 * @since 10/20/13
 */
public class AmDelegateImpl extends AutoInjectingRemoteServiceServlet implements AmDelegate {

    private static final Logger log = Logger.getLogger(AmDelegateImpl.class);

    @Autowired
    private AmManager amManager;

    @Autowired
    private AmFinder amFinder;

    @Autowired
    private AmConverter amConverter;

    @Autowired
    private DdManager ddManager;

    @Autowired
    private DdFinder ddFinder;

    @Autowired
    private DdConverter ddConverter;

    @Autowired
    private SoManager soManager;

    @Autowired
    private SoFinder soFinder;

    @Autowired
    private SoConverter soConverter;

    @Autowired(required = false) // todo
    private AuthenticationManager authenticationManager;

    private static DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);

    // ==================================================================================================== //
    // ENVIRONMENT
    // ==================================================================================================== //


    // ==================================================================================================== //
    // PRINCIPAL
    // ==================================================================================================== //

    @Override
    public AuthorizedUserModel getAuthorizedUser() throws UserNotInSessionException {
        log.info("loading authorized user");
        CfUser authUser = amFinder.findAuthorizedUser();
        if (null == authUser) {
            log.info("user is null");
            throw new UserNotInSessionException();
        }

        AuthorizedUserModel user = new AuthorizedUserModel();
        user.setUsername(authUser.getName());
        user.setName(authUser.getRealname());
        user.setEmail(authUser.getEmail());
        user.setId(authUser.getId());
        return user;
    }

    @Override
    public void updateMemberships(PrincipalModel principalModel, List<GroupModel> groupModels) throws Exception {
        try {
            List<GroupModel> principalModelInDb = findPrincipalGroups(principalModel);
            for (GroupModel groupInDb : principalModelInDb) {
                if (!groupModels.contains(groupInDb)) {
                    log.debug("deleting = " + groupInDb.getName());
                    CfGroup group = soFinder.findGroupById(groupInDb.getId());
                    CfPrincipal principal = soFinder.findPrincipalById(principalModel.getId());
                    amManager.removeGroupMember(group, principal);
                }
            }

            for (GroupModel groupModel : groupModels) {
                if (!principalModelInDb.contains(groupModel)) {
                    log.debug("Adding = " + groupModel.getName());
                    CfGroup group = soFinder.findGroupById(groupModel.getId());
                    CfPrincipal principal = soFinder.findPrincipalById(principalModel.getId());
                    amManager.addGroupMember(group, principal);
                }
            }
            //reset set roles value
        } catch (Exception e) {
            log.info("error occurred", e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<PrincipalModel> findAllPrincipals() {
        List<PrincipalModel> models = new ArrayList<PrincipalModel>();
        List<CfPrincipal> principals = soFinder.findAllPrincipals();

        for (CfPrincipal principal : principals) {
            models.add(soConverter.convert(principal));
        }
        return models;
    }

    @Override
    public List<PrincipalModel> findAllPrincipals(String filter) {
        List<PrincipalModel> models = new ArrayList<PrincipalModel>();
        List<CfPrincipal> principals = soFinder.findPrincipals(filter);

        for (CfPrincipal principal : principals) {
            models.add(soConverter.convert(principal));
        }
        return models;
    }

    // ==================================================================================================== //
    // USER
    // ==================================================================================================== //

    @Override
    public UserModel findUserById(Long id) {
        CfUser user = soFinder.findUserById(id);
        UserModel model = new UserModel();
        BeanUtils.copyProperties(user, model);
        return model;
    }

    @Override
    public UserModel findUserByUsername(String username) {
        CfUser user = soFinder.findUserByUsername(username);
        UserModel model = new UserModel();
        BeanUtils.copyProperties(user, model);
        return model;
    }

    @Override
    public PagingLoadResult<UserModel> findUsers(Integer offset, Integer limit) {
        Integer count = soFinder.countUser();
        List<UserModel> models = new ArrayList<UserModel>();
        try {
            List<CfUser> list = soFinder.findUsers(offset, limit);
            for (CfUser user : list) {
                models.add(soConverter.convert(user));
            }
        } catch (Exception e) {
            log.error("error occurred", e);
        }
        return new BasePagingLoadResult<UserModel>(models, offset, count);
    }

    @Override
    public PagingLoadResult<UserModel> findUsers(String filter, Integer offset, Integer limit) {
        Integer count = soFinder.countUser(filter);
        List<UserModel> models = new ArrayList<UserModel>();
        try {
            List<CfUser> list = soFinder.findUsers(filter, offset, limit);
            for (CfUser user : list) {
                models.add(soConverter.convert(user));
            }
        } catch (Exception e) {
            log.error("error occurred", e);
        }
        return new BasePagingLoadResult<UserModel>(models, offset, count);
    }

    // ==================================================================================================== //
    // GROUP
    // ==================================================================================================== //

    @Override
    public GroupModel findGroupById(Long id) {
        CfGroup group = soFinder.findGroupById(id);
        GroupModel model = new GroupModel();
        BeanUtils.copyProperties(group, model);
        return model;
    }

    @Override
    public GroupModel findGroupByName(String name) {
        CfGroup group = soFinder.findGroupByName(name);
        GroupModel model = new GroupModel();
        BeanUtils.copyProperties(group, model);
        return model;
    }

    @Override
    public void updateGroupMembers(UserModel userModel, List<GroupModel> groupModels) {
        try {
            CfUser user = soFinder.findUserById(userModel.getId());
            List<CfGroup> groups = new ArrayList<CfGroup>();
            for (GroupModel groupModel : groupModels) {
                groups.add(soFinder.findGroupById(groupModel.getId()));
            }
            amManager.updateGroupMembers(user, groups);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateGroupMembers(GroupModel groupModel, List<PrincipalModel> membersInList) throws GroupException {
        try {
            List<PrincipalModel> membersInDB = findGroupMembers(groupModel);
            for (PrincipalModel memberInDB : membersInDB) {
                if (!membersInList.contains(memberInDB)) {
                    log.debug("Removing : " + memberInDB.getId() + " : " + memberInDB.getName());
                    CfGroup group = soFinder.findGroupById(groupModel.getId());
                    CfPrincipal principal = soFinder.findPrincipalById(memberInDB.getId());
                    amManager.removeGroupMember(group, principal);
                }
            }

            for (PrincipalModel memberInList : membersInList) {
                if (!membersInDB.contains(memberInList)) {
                    log.debug("Adding : " + memberInList.getId() + " : " + memberInList.getName());
                    CfGroup group = soFinder.findGroupById(groupModel.getId());
                    CfPrincipal principal = soFinder.findPrincipalById(memberInList.getId());
                    amManager.addGroupMember(group, principal);
                }
            }

        } catch (RecursiveGroupException e) {
            log.error("error occurred", e);
            throw new GroupException();
        } catch (LockedGroupException e) {
            log.error("error occurred", e);
            throw new GroupException();
        }
    }

    @Override
    public List<GroupModel> findGroups() {
        List<GroupModel> models = new ArrayList<GroupModel>();
        try {
            List<CfGroup> list = soFinder.findGroups();
            for (CfGroup group : list) {
                GroupModel model = new GroupModel();
                BeanUtils.copyProperties(group, model);
                models.add(model);
            }
        } catch (Exception e) {
            log.error("error occurred", e);
        }
        return models;
    }

    @Override
    public List<GroupModel> findGroups(String filter) {

        List<GroupModel> models = new ArrayList<GroupModel>();
        try {
            List<CfGroup> list = soFinder.findGroups(filter, 0, 99999);
            for (CfGroup group : list) {
                GroupModel model = new GroupModel();
                BeanUtils.copyProperties(group, model);
                models.add(model);
            }
        } catch (Exception e) {
            log.error("error occurred", e);
        }
        return models;
    }

    @Override
    public List<GroupModel> findPrincipalGroups(PrincipalModel principalModel) {
        List<GroupModel> models = new ArrayList<GroupModel>();
        try {
            log.info("Loading group list for user#" + principalModel.getId());
            CfPrincipal principal = soFinder.findPrincipalById(principalModel.getId());
            List<CfGroup> list = soFinder.findGroups(principal);
            for (CfGroup group : list) {
                GroupModel model = new GroupModel();
                BeanUtils.copyProperties(group, model);
                models.add(model);
            }
        } catch (Exception e) {
            log.error("error occurred", e);
        }
        return models;
    }

    @Override
    public List<PrincipalModel> findGroupMembers(GroupModel groupModel) {
        List<PrincipalModel> models = new ArrayList<PrincipalModel>();
        try {
            log.info("Loading group list for group#" + groupModel.getId());
            List<CfPrincipal> list = soFinder.findGroupMembers(soFinder.findGroupById(groupModel.getId()));
            for (CfPrincipal principal : list) {
                PrincipalModel model = new PrincipalModel();
                model.setId(principal.getId());
                model.setName(principal.getName());
                model.setType(PrincipalType.get(principal.getPrincipalType().ordinal()));
                models.add(model);
            }
        } catch (Exception e) {
            log.error("error occurred", e);
        }
        return models;
    }

    @Override
    public PagingLoadResult<GroupModel> findGroups(Integer offset, Integer limit) {
        Integer count = soFinder.countGroup();
        List<GroupModel> models = new ArrayList<GroupModel>();
        try {
            List<CfGroup> list = soFinder.findGroups(offset, limit);
            for (CfGroup group : list) {
                GroupModel model = new GroupModel();
                BeanUtils.copyProperties(group, model);
                models.add(model);
            }
        } catch (Exception e) {
            log.error("error occurred", e);
        }
        return new BasePagingLoadResult<GroupModel>(models, offset, count);
    }

    @Override
    public PagingLoadResult<GroupModel> findGroups(String filter, Integer offset, Integer limit) {
        Integer count = soFinder.countGroup(filter);
        List<GroupModel> models = new ArrayList<GroupModel>();
        try {
            List<CfGroup> list = soFinder.findGroups(filter, offset, limit);
            for (CfGroup group : list) {
                GroupModel model = new GroupModel();
                BeanUtils.copyProperties(group, model);
                models.add(model);
            }
        } catch (Exception e) {
            log.error("error occurred", e);
        }
        return new BasePagingLoadResult<GroupModel>(models, offset, count);
    }

    // ==================================================================================================== //
    // COST CENTER
    // ==================================================================================================== //

    @Override
    public PagingLoadResult<CostCenterModel> findCostCenters(Integer offset, Integer limit) throws Exception {
        try {
            Integer count = ddFinder.countCostCenter();
            ArrayList<CostCenterModel> models = new ArrayList<CostCenterModel>();
            List<CfCostCenter> costCenters = ddFinder.findCostCenters(offset, limit);
            for (CfCostCenter costCenter : costCenters) {
                CostCenterModel model = ddConverter.convert(costCenter);
                models.add(model);
            }
            return new BasePagingLoadResult<CostCenterModel>(models, offset, count);
        } catch (Exception e) {
            log.error(e);
            throw new Exception();
        }
    }

    @Override
    public PagingLoadResult<CostCenterModel> findCostCenters(String filter, Integer offset, Integer limit) throws Exception {
        try {
            Integer count = ddFinder.countCostCenter();
            ArrayList<CostCenterModel> models = new ArrayList<CostCenterModel>();
            List<CfCostCenter> costCenters = ddFinder.findCostCenters(filter, offset, limit);
            for (CfCostCenter costCenter : costCenters) {
                CostCenterModel model = ddConverter.convert(costCenter);
                models.add(model);
            }
            return new BasePagingLoadResult<CostCenterModel>(models, offset, count);
        } catch (Exception e) {
            log.error(e);
            throw new Exception();
        }
    }

    @Override
    public ListLoadResult<PrincipalModel> findCostCenterMembers(CostCenterModel costCenterModel) throws Exception {
        try {
            log.debug("costCenterModel = " + costCenterModel.getDescription());
            List<PrincipalModel> models = new ArrayList<PrincipalModel>();
            List<CfCostCenterMember> costCenterMembers = ddFinder.findCostCenterMembers(ddFinder.findCostCenterById(costCenterModel.getId()));
            for (CfCostCenterMember member : costCenterMembers) {
                CfPrincipal principal = member.getPrincipal();
                PrincipalModel model = new PrincipalModel();
                model.setId(principal.getId());
                model.setName(principal.getName());
                model.setType(PrincipalType.get(principal.getPrincipalType().ordinal()));
                models.add(model);
            }
            return new BaseListLoadResult<PrincipalModel>(models);
        } catch (Exception e) {
            log.error(e);
            throw new Exception();
        }
    }

    @Override
    public List<PrincipalModel> findCostCenterMembersAsList(CostCenterModel costCenterModel) throws Exception {
        try {
            log.debug("costCenterModel = " + costCenterModel.getDescription());
            List<PrincipalModel> models = new ArrayList<PrincipalModel>();
            List<CfCostCenterMember> costCenterMembers = ddFinder.findCostCenterMembers(ddFinder.findCostCenterById(costCenterModel.getId()));
            for (CfCostCenterMember member : costCenterMembers) {
                CfPrincipal principal = member.getPrincipal();
                PrincipalModel model = new PrincipalModel();
                model.setId(principal.getId());
                model.setName(principal.getName());
                model.setType(PrincipalType.get(principal.getPrincipalType().ordinal()));
                models.add(model);
            }
            return models;
        } catch (Exception e) {
            log.error(e);
            throw new Exception();
        }
    }

    @Override
    public void updateMembers(CostCenterModel costCenterModel) throws Exception {
        try {
            List<PrincipalModel> membersInList = costCenterModel.getCostCenterMembers();
            List<PrincipalModel> membersInDB = findCostCenterMembersAsList(costCenterModel);

            for (PrincipalModel memberInDB : membersInDB) {
                if (!membersInList.contains(memberInDB)) {
                    log.debug("Removing : " + memberInDB.getId() + " : " + memberInDB.getName());
                    CfCostCenter costCenter = ddFinder.findCostCenterById(costCenterModel.getId());
                    log.debug("cost center.getId() = " + costCenter.getId() + " " + costCenter.getCode());
                    CfPrincipal principal = soFinder.findPrincipalById(memberInDB.getId());
                    ddManager.removeCostCenterMember(costCenter, principal);
                }
            }

            for (PrincipalModel memberInList : membersInList) {
                if (!membersInDB.contains(memberInList)) {
                    log.debug("Adding : " + memberInList.getId() + " : " + memberInList.getName());
                    CfCostCenter costCenter = ddFinder.findCostCenterById(costCenterModel.getId());
                    log.debug("principal.getId() = " + costCenter.getId() + " " + costCenter.getCode());
                    CfPrincipal principal = soFinder.findPrincipalById(memberInList.getId());
                    ddManager.addCostCenterMember(costCenter, principal);
                }
            }
        } catch (Exception e) {
            log.debug("e.getMessage() = " + e.getMessage());
            throw new Exception();
        }
    }

    // ==================================================================================================== //
    // MODULE
    // ==================================================================================================== //

    @Override
    public ModuleModel findModuleById(Long id) {
        CfModule module = amFinder.findModuleById(id);
        ModuleModel model = new ModuleModel();
        BeanUtils.copyProperties(module, model);
        return model;
    }

    @Override
    public ModuleModel findModuleByCode(String code) {
        CfModule module = amFinder.findModuleByCode(code);
        ModuleModel model = new ModuleModel();
        BeanUtils.copyProperties(module, model);
        return model;
    }

    @Override
    public Set<ModuleModel> findAuthorizedModules() {
        Set<ModuleModel> models = new HashSet<ModuleModel>();
        try {
            List<CfModule> list = amFinder.findAuthorizedModules();
            for (CfModule module : list) {
                models.add(amConverter.convert(module));
            }
        } catch (Exception e) {
            log.error("error occurred", e);
        }
        return models;
    }

    @Override
    public Map<String, Boolean> findAuthorizedSubModule() {
        return amFinder.findAuthorizedSubModules();
    }

    @Override
    public PagingLoadResult<ModuleModel> findModules(Integer offset, Integer limit) {
        Integer count = amFinder.countModule();
        List<ModuleModel> models = new ArrayList<ModuleModel>();
        try {
            List<CfModule> list = amFinder.findModules(offset, limit);
            for (CfModule module : list) {
                ModuleModel model = new ModuleModel();
                BeanUtils.copyProperties(module, model);
                models.add(model);
            }
        } catch (Exception e) {
            log.error("error occurred", e);
        }
        return new BasePagingLoadResult<ModuleModel>(models, offset, count);
    }

    @Override
    public PagingLoadResult<ModuleModel> findModules(String filter, Integer offset, Integer limit) {
        Integer count = amFinder.countModule(filter);
        List<ModuleModel> models = new ArrayList<ModuleModel>();
        try {
            List<CfModule> list = amFinder.findModules(filter, offset, limit);
            for (CfModule module : list) {
                ModuleModel model = new ModuleModel();
                BeanUtils.copyProperties(module, model);
                models.add(model);
            }
        } catch (Exception e) {
            log.error("error occurred", e);
        }
        return new BasePagingLoadResult<ModuleModel>(models, offset, count);
    }

    @Override
    public void saveModule(ModuleModel moduleModel) {
        try {
            CfModule module = new CfModuleImpl();
            BeanUtils.copyProperties(moduleModel, module);
            amManager.saveModule(module);
        } catch (Exception e) {
            log.info("error occurred", e);
        }
    }

    @Override
    public void updateModule(ModuleModel moduleModel) {
        try {
            CfModule module = amFinder.findModuleById(moduleModel.getId());
            BeanUtils.copyProperties(moduleModel, module);
            amManager.updateModule(module);
        } catch (Exception e) {
            log.info("error occurred", e);
        }
    }

    @Override
    public List<PrincipalModel> findPrincipalRoots() {
        List<PrincipalModel> principals = null;
        try {
            principals = new ArrayList<PrincipalModel>();
            CfPrincipal root = soFinder.findPrincipalByName(CfGroup.ROOT_GROUP);
            PrincipalModel rootModel = soConverter.convert((CfGroup) root);
            principals.add(rootModel);
        } catch (Exception e) {
            log.error(e);
        }
        return principals;
    }

    @Override
    public List<PrincipalModel> findPrincipalChildren(PrincipalModel parent) {
        List<PrincipalModel> principals = null;
        try {
            principals = new ArrayList<PrincipalModel>();
            CfPrincipal parentModel = soFinder.findPrincipalById(parent.getId());
            if (parentModel.getPrincipalType().equals(PrincipalType.GROUP)) {
                List<CfPrincipal> members = soFinder.findGroupMembers((CfGroup) parentModel, CfPrincipalType.GROUP);
                for (CfPrincipal member : members) {
                    principals.add(soConverter.convert(member));
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return principals;
    }

    // ==================================================================================================== //
    // PERMISSION
    // ==================================================================================================== //

    // ==================================================================================================== //
    // LOG
    // ==================================================================================================== //

    // ==================================================================================================== //
    // HELPER
    // ==================================================================================================== //

    // ==================================================================================================== //
    // PRIVATE METHODS
    // ==================================================================================================== //

    private void updateModulePermissions(PrincipalModel principalModel, List<ModulePermissionModel> permissionModels) {
        try {
            CfPrincipal principal = soFinder.findPrincipalById(principalModel.getId());
            for (ModulePermissionModel permissionModel : permissionModels) {
                CfModule subModule = amFinder.findModuleByCode(permissionModel.getCode());
                log.info("VIEW: " + permissionModel.getView());
                log.info("CREATE: " + permissionModel.getCreate());
                log.info("UPDATE: " + permissionModel.getUpdate());
                log.info("DELETE: " + permissionModel.getDelete());
                log.info("CANCEL: " + permissionModel.getCancel());
                log.info("PRINT: " + permissionModel.getPrint());
                log.info("ADMIN: " + permissionModel.getAdmin());

                // sync permission
                syncPermission(principal, subModule, VIEW, permissionModel.getView());
                syncPermission(principal, subModule, CREATE, permissionModel.getCreate());
                syncPermission(principal, subModule, UPDATE, permissionModel.getUpdate());
                syncPermission(principal, subModule, DELETE, permissionModel.getDelete());
                syncPermission(principal, subModule, CANCEL, permissionModel.getCancel());
                syncPermission(principal, subModule, PRINT, permissionModel.getPrint());
                syncPermission(principal, subModule, ADMIN, permissionModel.getAdmin());
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    private void updateSubModulePermissions(PrincipalModel principalModel, List<SubModulePermissionModel> permissionModels) {
        try {
            CfPrincipal principal = soFinder.findPrincipalById(principalModel.getId());
            for (SubModulePermissionModel permissionModel : permissionModels) {
                CfSubModule subModule = amFinder.findSubModuleByCode(permissionModel.getCode());
                log.info("VIEW: " + permissionModel.getView());
                log.info("CREATE: " + permissionModel.getCreate());
                log.info("UPDATE: " + permissionModel.getUpdate());
                log.info("DELETE: " + permissionModel.getDelete());
                log.info("CANCEL: " + permissionModel.getCancel());
                log.info("PRINT: " + permissionModel.getPrint());
                log.info("ADMIN: " + permissionModel.getAdmin());

                // sync permission
                syncPermission(principal, subModule, VIEW, permissionModel.getView());
                syncPermission(principal, subModule, CREATE, permissionModel.getCreate());
                syncPermission(principal, subModule, UPDATE, permissionModel.getUpdate());
                syncPermission(principal, subModule, DELETE, permissionModel.getDelete());
                syncPermission(principal, subModule, CANCEL, permissionModel.getCancel());
                syncPermission(principal, subModule, PRINT, permissionModel.getPrint());
                syncPermission(principal, subModule, ADMIN, permissionModel.getAdmin());
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    private void syncPermission(CfPrincipal principal, CfMetaObject object, CfAclPermission permission, Boolean updatedPermission) {
        try {
            if (amManager.hasPermission(object, principal, permission) && !updatedPermission) { // TRUE && FALSE
                log.debug("deleting: " + permission + " for " + object.toString());
                amManager.deletePermission(object, principal, permission);
            } else if (!amManager.hasPermission(object, principal, permission) && updatedPermission) {  // FALSE && TRUE
                log.debug("adding: " + permission + " for " + object.toString());
                amManager.addPermission(object, principal, permission);
            }
        } catch (Exception e) {
            log.error(e);
        }
    }
}
