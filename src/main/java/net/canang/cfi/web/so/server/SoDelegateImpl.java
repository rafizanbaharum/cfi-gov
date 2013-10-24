package net.canang.cfi.web.so.server;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import net.canang.cfi.biz.dd.manager.DdFinder;
import net.canang.cfi.biz.dd.manager.DdManager;
import net.canang.cfi.biz.so.manager.SoFinder;
import net.canang.cfi.biz.so.manager.SoManager;
import net.canang.cfi.core.exception.LockedGroupException;
import net.canang.cfi.core.exception.RecursiveGroupException;
import net.canang.cfi.core.so.model.CfGroup;
import net.canang.cfi.core.so.model.CfPrincipal;
import net.canang.cfi.core.so.model.CfUser;
import net.canang.cfi.core.so.model.impl.CfGroupImpl;
import net.canang.cfi.core.so.model.impl.CfUserImpl;
import net.canang.cfi.web.am.server.AmDelegateImpl;
import net.canang.cfi.web.dd.server.DdConverter;
import net.canang.cfi.web.server.common.AutoInjectingRemoteServiceServlet;
import net.canang.cfi.web.so.client.SoDelegate;
import net.canang.cfi.web.so.client.exception.GroupException;
import net.canang.cfi.web.so.client.model.GroupModel;
import net.canang.cfi.web.so.client.model.PrincipalModel;
import net.canang.cfi.web.so.client.model.PrincipalType;
import net.canang.cfi.web.so.client.model.UserModel;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
public class SoDelegateImpl extends AutoInjectingRemoteServiceServlet implements SoDelegate {

    private static final Logger log = Logger.getLogger(AmDelegateImpl.class);

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

    // ==================================================================================================== //
    // PRINCIPAL
    // ==================================================================================================== //

    @Override
    public PrincipalModel updatePrincipalGroups(PrincipalModel principalModel, List<GroupModel> groupModels) throws Exception {
        try {
            List<GroupModel> principalModelInDb = findPrincipalGroups(principalModel);
            for (GroupModel groupInDb : principalModelInDb) {
                if (!groupModels.contains(groupInDb)) {
                    log.debug("deleting = " + groupInDb.getName());
                    CfGroup group = soFinder.findGroupById(groupInDb.getId());
                    CfPrincipal principal = soFinder.findPrincipalById(principalModel.getId());
                    soManager.removeGroupMember(group, principal);
                }
            }

            for (GroupModel groupModel : groupModels) {
                if (!principalModelInDb.contains(groupModel)) {
                    log.debug("Adding = " + groupModel.getName());
                    CfGroup group = soFinder.findGroupById(groupModel.getId());
                    CfPrincipal principal = soFinder.findPrincipalById(principalModel.getId());
                    soManager.addGroupMember(group, principal);
                }
            }
            //reset set roles value
        } catch (Exception e) {
            log.info("error occurred", e);
            throw new Exception(e.getMessage());
        }

        return principalModel;
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
        return soConverter.convert(user);
    }

    @Override
    public UserModel findUserByUsername(String username) {
        CfUser user = soFinder.findUserByUsername(username);
        return soConverter.convert(user);
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

    @Override
    public void saveUser(UserModel userModel) {
        CfUser user = new CfUserImpl();
        BeanUtils.copyProperties(userModel, user);
        soManager.saveUser(user);
    }

    @Override
    public void updateUser(UserModel userModel) throws Exception {
        CfUser user = soFinder.findUserById(userModel.getId());
        BeanUtils.copyProperties(userModel, user);
        soManager.updateUser(user);
    }


    // ==================================================================================================== //
    // GROUP
    // ==================================================================================================== //

    @Override
    public GroupModel findGroupById(Long id) {
        return soConverter.convert(soFinder.findGroupById(id));
    }

    @Override
    public GroupModel findGroupByName(String name) {
        return soConverter.convert(soFinder.findGroupByName(name));
    }


    @Override
    public void updateGroupMembers(UserModel userModel, List<GroupModel> groupModels) {
        try {
            CfUser user = soFinder.findUserById(userModel.getId());
            List<CfGroup> groups = new ArrayList<CfGroup>();
            for (GroupModel groupModel : groupModels) {
                groups.add(soFinder.findGroupById(groupModel.getId()));
            }
            soManager.updateGroupMembers(user, groups);
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
                    soManager.removeGroupMember(group, principal);
                }
            }

            for (PrincipalModel memberInList : membersInList) {
                if (!membersInDB.contains(memberInList)) {
                    log.debug("Adding : " + memberInList.getId() + " : " + memberInList.getName());
                    CfGroup group = soFinder.findGroupById(groupModel.getId());
                    CfPrincipal principal = soFinder.findPrincipalById(memberInList.getId());
                    soManager.addGroupMember(group, principal);
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

    @Override
    public void saveGroup(GroupModel groupModel) {
        CfGroup group = new CfGroupImpl();
        BeanUtils.copyProperties(groupModel, group);
        soManager.saveGroup(group);
    }

    @Override
    public void updateGroup(GroupModel groupModel) {
        CfGroup group = soFinder.findGroupById(groupModel.getId());
        BeanUtils.copyProperties(groupModel, group);
        soManager.updateGroup(group);
    }
}
