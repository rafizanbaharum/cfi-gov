package net.canang.cfi.web.so.client;

import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import net.canang.cfi.web.so.client.exception.GroupException;
import net.canang.cfi.web.so.client.model.GroupModel;
import net.canang.cfi.web.so.client.model.PrincipalModel;
import net.canang.cfi.web.so.client.model.UserModel;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
public interface SoDelegate extends RemoteService {


    List<PrincipalModel> findAllPrincipals();

    List<PrincipalModel> findAllPrincipals(String filter);

    List<GroupModel> findPrincipalGroups(PrincipalModel principalModel) throws Exception;

    PrincipalModel updatePrincipalGroups(PrincipalModel principalModel, List<GroupModel> groupModels) throws Exception;

    // ==================================================================================================== //
    // USER
    // ==================================================================================================== //

    UserModel findUserById(Long id);

    UserModel findUserByUsername(String username);

    PagingLoadResult<UserModel> findUsers(Integer offset, Integer limit);

    PagingLoadResult<UserModel> findUsers(String filter, Integer offset, Integer limit);

    void saveUser(UserModel userModel);

    void updateUser(UserModel userModel) throws Exception;

    // ==================================================================================================== //
    // GROUP
    // ==================================================================================================== //

    GroupModel findGroupById(Long id) throws Exception;

    GroupModel findGroupByName(String name) throws Exception;

    void updateGroupMembers(GroupModel groupModel, List<PrincipalModel> members) throws GroupException;

    void updateGroupMembers(UserModel userModel, List<GroupModel> groupModels);

    List<GroupModel> findGroups() throws Exception;

    List<GroupModel> findGroups(String filter) throws Exception;

    List<PrincipalModel> findGroupMembers(GroupModel groupModel) throws Exception;

    PagingLoadResult<GroupModel> findGroups(Integer offset, Integer limit) throws Exception;

    PagingLoadResult<GroupModel> findGroups(String filter, Integer offset, Integer limit) throws Exception;

    void saveGroup(GroupModel groupModel) throws Exception;

    void updateGroup(GroupModel groupModel) throws Exception;

}