package net.canang.cfi.web.so.client;

import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import net.canang.cfi.web.so.client.model.GroupModel;
import net.canang.cfi.web.so.client.model.PrincipalModel;
import net.canang.cfi.web.so.client.model.UserModel;

import java.util.List;

public interface SoDelegateAsync {


    // ==================================================================================================== //
    // PRINCIPAL
    // ==================================================================================================== //

    void findAllPrincipals(AsyncCallback<List<PrincipalModel>> async);

    void findAllPrincipals(String filter, AsyncCallback<List<PrincipalModel>> async);

    void findPrincipalGroups(PrincipalModel principalModel, AsyncCallback<List<GroupModel>> async);

    void updatePrincipalGroups(PrincipalModel principalModel, List<GroupModel> groupModels, AsyncCallback<PrincipalModel> async);

    // ==================================================================================================== //
    // USER
    // ==================================================================================================== //

    void findUserById(Long id, AsyncCallback<UserModel> async);

    void findUsers(Integer offset, Integer limit, AsyncCallback<PagingLoadResult<UserModel>> async);

    void findUsers(String filter, Integer offset, Integer limit, AsyncCallback<PagingLoadResult<UserModel>> async);

    void findUserByUsername(String username, AsyncCallback<UserModel> async);

    void saveUser(UserModel userModel, AsyncCallback<Void> async);

    void updateUser(UserModel userModel, AsyncCallback<Void> async);

    // ==================================================================================================== //
    // GROUP
    // ==================================================================================================== //

    void findGroupById(Long id, AsyncCallback<GroupModel> async);

    void findGroupByName(String groupname, AsyncCallback<GroupModel> async);

    void findGroups(AsyncCallback<List<GroupModel>> async);

    void findGroups(String filter, AsyncCallback<List<GroupModel>> async);

    void findGroups(Integer offset, Integer limit, AsyncCallback<PagingLoadResult<GroupModel>> async);

    void findGroups(String filter, Integer offset, Integer limit, AsyncCallback<PagingLoadResult<GroupModel>> async);

    void findGroupMembers(GroupModel groupModel, AsyncCallback<List<PrincipalModel>> async);

    void saveGroup(GroupModel groupModel, AsyncCallback<Void> async);

    void updateGroup(GroupModel groupModel, AsyncCallback<Void> async);

    void updateGroupMembers(GroupModel groupModel, List<PrincipalModel> members, AsyncCallback<Void> async);

    void updateGroupMembers(UserModel userModel, List<GroupModel> groupModels, AsyncCallback<Void> async);

    void updateMembers(GroupModel groupModel, List<PrincipalModel> members, AsyncCallback<GroupModel> async);
}
