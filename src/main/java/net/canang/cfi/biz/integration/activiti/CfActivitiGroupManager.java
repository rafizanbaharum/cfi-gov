package net.canang.cfi.biz.integration.activiti;

import net.canang.cfi.core.so.dao.CfGroupDao;
import net.canang.cfi.core.so.dao.CfUserDao;
import net.canang.cfi.core.so.model.CfGroup;
import net.canang.cfi.core.so.model.CfUser;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/2/13
 */
@Component("activitiGroupManager")
public class CfActivitiGroupManager extends GroupEntityManager {

    private static final Logger log = Logger.getLogger(CfActivitiGroupManager.class);

    @Autowired
    private CfGroupDao groupDao;

    @Autowired
    private CfUserDao userDao;


    @Override
    public Group createNewGroup(String groupId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insertGroup(Group group) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteGroup(String groupId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GroupQuery createNewGroupQuery() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Group> findGroupsByUser(String userId) {
        CfUser user = userDao.findByUsername(userId);
        List<Group> groups = new ArrayList<Group>();
        try {
            List<CfGroup> principalGroups = groupDao.findPrincipalGroups(user);
            for (CfGroup group : principalGroups) {
                CfActivitiGroup g = new CfActivitiGroup(group);
                groups.add(g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return groups;
    }
}
