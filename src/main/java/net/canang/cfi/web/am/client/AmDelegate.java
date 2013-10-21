package net.canang.cfi.web.am.client;

import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import net.canang.cfi.web.am.client.exception.UserNotInSessionException;
import net.canang.cfi.web.am.client.model.AuthorizedUserModel;
import net.canang.cfi.web.am.client.model.ModuleModel;
import net.canang.cfi.web.dd.client.model.CostCenterModel;
import net.canang.cfi.web.so.client.exception.GroupException;
import net.canang.cfi.web.so.client.model.GroupModel;
import net.canang.cfi.web.so.client.model.PrincipalModel;
import net.canang.cfi.web.so.client.model.UserModel;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author rafizan.baharum
 * @since 10/20/13
 */
@SuppressWarnings({"NonSerializableServiceParameters"})
public interface AmDelegate extends RemoteService {

    // ==================================================================================================== //
    // PRINCIPAL
    // ==================================================================================================== //

    List<PrincipalModel> findAllPrincipals();

    List<PrincipalModel> findAllPrincipals(String filter);

    List<PrincipalModel> findPrincipalRoots();

    List<PrincipalModel> findPrincipalChildren(PrincipalModel principalModel);

    void updateMemberships(PrincipalModel principal, List<GroupModel> groups) throws Exception;

    // ==================================================================================================== //
    // USER
    // ==================================================================================================== //

    AuthorizedUserModel getAuthorizedUser() throws UserNotInSessionException;

    UserModel findUserById(Long id);

    UserModel findUserByUsername(String username);

    PagingLoadResult<UserModel> findUsers(Integer offset, Integer limit);

    PagingLoadResult<UserModel> findUsers(String filter, Integer offset, Integer limit);

    // ==================================================================================================== //
    // GROUPS
    // ==================================================================================================== //

    GroupModel findGroupById(Long id) throws Exception;

    GroupModel findGroupByName(String name) throws Exception;

    void updateGroupMembers(GroupModel groupModel, List<PrincipalModel> membersInList) throws GroupException;

    void updateGroupMembers(UserModel userModel, List<GroupModel> groupModels);

    List<GroupModel> findGroups() throws Exception;

    List<GroupModel> findGroups(String filter) throws Exception;

    List<GroupModel> findPrincipalGroups(PrincipalModel principalModel) throws Exception;

    List<PrincipalModel> findGroupMembers(GroupModel groupModel) throws Exception;

    PagingLoadResult<GroupModel> findGroups(Integer offset, Integer limit) throws Exception;

    PagingLoadResult<GroupModel> findGroups(String filter, Integer offset, Integer limit) throws Exception;

    // ==================================================================================================== //
    // COST CENTER GROUPS
    // ==================================================================================================== //

    PagingLoadResult<CostCenterModel> findCostCenters(Integer offset, Integer limit) throws Exception;

    PagingLoadResult<CostCenterModel> findCostCenters(String filter, Integer offset, Integer limit) throws Exception;

    ListLoadResult<PrincipalModel> findCostCenterMembers(CostCenterModel costCenterModel) throws Exception;

    List<PrincipalModel> findCostCenterMembersAsList(CostCenterModel costCenterModel) throws Exception;

    void updateMembers(CostCenterModel costCenterModel) throws Exception;

    // ==================================================================================================== //
    // MODULE SUBMODULE
    // ==================================================================================================== //

    ModuleModel findModuleById(Long id);

    ModuleModel findModuleByCode(String code);

    Set<ModuleModel> findAuthorizedModules();

    Map<String, Boolean> findAuthorizedSubModule();

    PagingLoadResult<ModuleModel> findModules(Integer offset, Integer limit);

    PagingLoadResult<ModuleModel> findModules(String filter, Integer offset, Integer limit);

    void saveModule(ModuleModel moduleModel);

    void updateModule(ModuleModel moduleModel);

    // ==================================================================================================== //
    // PERMISSION
    // ==================================================================================================== //

    // ==================================================================================================== //
    // LOG
    // ==================================================================================================== //

    // ==================================================================================================== //
    // UTILITY
    // ==================================================================================================== //
}
