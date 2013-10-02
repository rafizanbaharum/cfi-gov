package net.canang.cfi.biz.integration.springacl;

import net.canang.cfi.core.exception.RecursiveGroupException;
import net.canang.cfi.core.so.dao.CfGroupDao;
import net.canang.cfi.core.so.dao.CfPrincipalDao;
import net.canang.cfi.core.so.model.CfGroup;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.provisioning.GroupManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Readony implementation
 *
 * @author rafizan.baharum
 * @since 10/2/13
 */
public class CfAclGroupManager implements GroupManager {

    private static final Logger log = Logger.getLogger(CfAclGroupManager.class);

    @Autowired
    private CfPrincipalDao principalDao;

    @Autowired
    private CfGroupDao groupDao;

    @Override
    public List<String> findAllGroups() {
        return groupDao.findAllGroupName();
    }

    @Override
    public List<String> findUsersInGroup(String groupName) {
        List<String> names = new ArrayList<String>();
        CfGroup group = groupDao.findByName(groupName);
        throw new UnsupportedOperationException("TODO");
//        Set<CfPrincipal> principals = groupDao.loadEffectiveMembers(group);
//        for (CfPrincipal principal : principals) {
//            names.add(principal.getName());
//        }
//        return names;
    }

    @Override
    public void createGroup(String groupName, List<GrantedAuthority> authorities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteGroup(String groupName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void renameGroup(String oldName, String newName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addUserToGroup(String username, String group) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeUserFromGroup(String username, String groupName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<GrantedAuthority> findGroupAuthorities(String groupName) {
        Set<GrantedAuthority> grantedAuthorities;
        try {
            grantedAuthorities = principalDao.loadEffectiveAuthorities(groupDao.findByName(groupName));
        } catch (RecursiveGroupException e) {
            return new ArrayList<GrantedAuthority>();
        }

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
        grantedAuthorityList.addAll(grantedAuthorities);
        return grantedAuthorityList;
    }

    @Override
    public void addGroupAuthority(String groupName, GrantedAuthority authority) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeGroupAuthority(String groupName, GrantedAuthority authority) {
        throw new UnsupportedOperationException();
    }
}
