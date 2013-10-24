package net.canang.cfi.web.am.client;

import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import net.canang.cfi.web.am.client.model.AuthorizedUserModel;
import net.canang.cfi.web.am.client.model.ModuleModel;
import net.canang.cfi.web.dd.client.model.CostCenterModel;
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
public interface AmDelegateAsync {

    // ==================================================================================================== //
    // PRINCIPAL
    // ==================================================================================================== //

    void findAllPrincipals(AsyncCallback<List<PrincipalModel>> async);

    void findAllPrincipals(String filter, AsyncCallback<List<PrincipalModel>> async);

    void findPrincipalRoots(AsyncCallback<List<PrincipalModel>> async);

    void findPrincipalChildren(PrincipalModel principalModel, AsyncCallback<List<PrincipalModel>> async);

    void updateMemberships(PrincipalModel principal, List<GroupModel> groups, AsyncCallback<Void> async);

    // ==================================================================================================== //
    // USER
    // ==================================================================================================== //

    void getAuthorizedUser(AsyncCallback<AuthorizedUserModel> async);

    void findUserById(Long id, AsyncCallback<UserModel> async);

    void findUserByUsername(String username, AsyncCallback<UserModel> async);

    void findUsers(Integer offset, Integer limit, AsyncCallback<PagingLoadResult<UserModel>> async);

    void findUsers(String filter, Integer offset, Integer limit, AsyncCallback<PagingLoadResult<UserModel>> async);


    // ==================================================================================================== //
    // GROUP
    // ==================================================================================================== //

    void findGroupById(Long id, AsyncCallback<GroupModel> async);

    void findGroupByName(String name, AsyncCallback<GroupModel> async);

    void updateGroupMembers(GroupModel groupModel, List<PrincipalModel> membersInList, AsyncCallback<Void> async);

    void updateGroupMembers(UserModel userModel, List<GroupModel> groupModels, AsyncCallback<Void> async);

    void findGroups(AsyncCallback<List<GroupModel>> async);

    void findGroups(String filter, AsyncCallback<List<GroupModel>> async);

    void findPrincipalGroups(PrincipalModel principalModel, AsyncCallback<List<GroupModel>> async);

    void findGroupMembers(GroupModel groupModel, AsyncCallback<List<GroupModel>> async);

    void findGroups(Integer offset, Integer limit, AsyncCallback<PagingLoadResult<GroupModel>> async);

    void findGroups(String filter, Integer offset, Integer limit, AsyncCallback<PagingLoadResult<GroupModel>> async);


    // ==================================================================================================== //
    // COST CENTER
    // ==================================================================================================== //

    void findCostCenters(Integer offset, Integer limit, AsyncCallback async);

    void findCostCenters(String filter, Integer offset, Integer limit, AsyncCallback async);

    void findCostCenterMembers(CostCenterModel costCenterModel, AsyncCallback async);

    void findCostCenterMembersAsList(CostCenterModel costCenterModel, AsyncCallback async);

    void updateCostCenterMembers(CostCenterModel costCenterModel, List<PrincipalModel> members, AsyncCallback async);

    // ==================================================================================================== //
    // MODULE
    // ==================================================================================================== //

    void findModuleById(Long id, AsyncCallback<ModuleModel> async);

    void findModuleByCode(String code, AsyncCallback<ModuleModel> async);

    void findAuthorizedModules(AsyncCallback<Set<ModuleModel>> async);

    void findAuthorizedSubModule(AsyncCallback<Map<String, Boolean>> async);

    void findModules(Integer offset, Integer limit, AsyncCallback<PagingLoadResult<ModuleModel>> async);

    void findModules(String filter, Integer offset, Integer limit, AsyncCallback<PagingLoadResult<ModuleModel>> async);

    void saveModule(ModuleModel moduleModel, AsyncCallback<Void> async);

    void updateModule(ModuleModel moduleModel, AsyncCallback<Void> async);

}
