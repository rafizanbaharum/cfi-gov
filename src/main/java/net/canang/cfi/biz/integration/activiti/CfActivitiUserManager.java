package net.canang.cfi.biz.integration.activiti;

import net.canang.cfi.core.so.dao.CfGroupDao;
import net.canang.cfi.core.so.dao.CfUserDao;
import net.canang.cfi.core.so.model.CfGroup;
import net.canang.cfi.core.so.model.CfUser;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.persistence.entity.IdentityInfoEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom User Manager
 *
 * @author rafizan.baharum
 * @since 10/2/13
 */
@Component("activityUserManager")
public class CfActivitiUserManager extends UserEntityManager {

    private static final Logger log = Logger.getLogger(CfActivitiUserManager.class);

    @Autowired
    private CfGroupDao groupDao;

    @Autowired
    private CfUserDao userDao;

    @Override
    public User createNewUser(String userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insertUser(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserEntity findUserById(String userId) {
        return new CfActivitiUser(userDao.findByUsername(userId));
    }

    @Override
    public void deleteUser(String userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Group> findGroupsByUser(String userId) {
        log.debug("username:" + userId);
        CfUser byUsername = userDao.findByUsername(userId);
        List<CfGroup> principalGroups = groupDao.findPrincipalGroups(byUsername);
        List<Group> groups = new ArrayList<Group>();
        for (CfGroup group : principalGroups) {
            log.debug("group:" + group);
            CfActivitiGroup g = new CfActivitiGroup(group);
            groups.add(g);
        }
        return groups;
    }

    @Override
    public UserQuery createNewUserQuery() {
        throw new UnsupportedOperationException();
    }

    @Override
    public IdentityInfoEntity findUserInfoByUserIdAndKey(String userId, String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> findUserInfoKeysByUserIdAndType(String userId, String type) {
        throw new UnsupportedOperationException();
    }
}

