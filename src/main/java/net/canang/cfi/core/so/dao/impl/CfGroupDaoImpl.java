package net.canang.cfi.core.so.dao.impl;

import net.canang.cfi.core.exception.LockedGroupException;
import net.canang.cfi.core.exception.RecursiveGroupException;
import net.canang.cfi.core.so.dao.CfGroupDao;
import net.canang.cfi.core.so.dao.DaoSupport;
import net.canang.cfi.core.so.model.*;
import net.canang.cfi.core.so.model.impl.CfGroupImpl;
import net.canang.cfi.core.so.model.impl.CfGroupMemberImpl;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author canang.technologies
 * @since 7/12/13
 */
@Repository("groupDao")
public class CfGroupDaoImpl extends DaoSupport<Long, CfGroup, CfGroupImpl> implements CfGroupDao {

    private static final Logger log = Logger.getLogger(CfGroupDaoImpl.class);

    // =============================================================================
    // FINDER METHODS
    // =============================================================================

    @Override
    public CfGroup findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CfGroup) session.get(CfGroupImpl.class, id);
    }

    @Override
    public List<CfGroup> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select g from CfGroup g order by g.name");
        return (List<CfGroup>) query.list();
    }

    @Override
    public List<String> findAllGroupName() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select g.name from CfGroup g");
        return (List<String>) query.list();
    }

    @Override
    public CfGroup findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select g from CfGroup g where g.name = :name");
        query.setString("name", name);
        return (CfGroup) query.uniqueResult();
    }

    @Override
    public List<CfPrincipal> findMembers(CfGroup group) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select gm.principal from CfGroupMember gm where " +
                "gm.group = :group " +
                "order by gm.principal.name");
        query.setEntity("group", group);
        return (List<CfPrincipal>) query.list();
    }

    /**
     * XXX: ClassCastException issues
     * XXX: select gm.principal wil get you abstract CfPrincipal
     * XXX: not extension classes
     *
     * @param group
     * @param type
     * @return
     */
    @Override
    public List<CfPrincipal> findMembers(CfGroup group, CfPrincipalType type) {
        Session session = sessionFactory.getCurrentSession();
        Query query = null;

        String selectGroup = "select g from CfGroup g where " +
                "id in (select gm.principal.id from CfGroupMember gm where " +
                "gm.group = :group " +
                "and gm.principal.principalType = :type )" +
                "order by g.name";
        String selectUser = "select u from CfUser u where " +
                "id in (select gm.principal.id from CfGroupMember gm where " +
                "gm.group = :group " +
                "and gm.principal.principalType = :type )" +
                "order by u.name";
        switch (type) {
            case USER:
                query = session.createQuery(selectUser);
                break;
            case GROUP:
                query = session.createQuery(selectGroup);
                break;
        }
        query.setEntity("group", group);
        query.setInteger("type", type.ordinal());
        return (List<CfPrincipal>) query.list();
    }

    @Override
    public List<CfGroup> findMemberships(CfPrincipal principal) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select gm.group from CfGroupMember gm inner join gm.principal where " +
                "gm.principal = :principal");
        query.setEntity("principal", principal);
        return (List<CfGroup>) query.list();
    }

    @Override
    public List<CfPrincipal> findMembers(CfGroup group, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select gm.principal from CfGroupMember gm where " +
                "gm.group = :group " +
                "order by gm.principal.name");
        query.setEntity("group", group);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfPrincipal>) query.list();
    }

    @Override
    public List<CfGroup> find(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select g from CfGroup g order by g.name");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfGroup>) query.list();
    }


    @Override
    public List<CfGroup> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct g from CfGroup g where " +
                "g.name like upper(:filter) " +
                "and g.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfGroup>) query.list();
    }

    @Override
    public List<CfGroup> findParentGroup(CfGroup group) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select g from CfGroup g inner join g.groupMembers m where m.principal = :group");
        query.setEntity("group", group);
        return (List<CfGroup>) query.list();
    }

    @Override
    public Set<CfGroup> findHierarchicalGroupAsNative(CfPrincipal principal) {
        Session session = sessionFactory.getCurrentSession();
        String sqlQuery = "SELECT CONNECT_BY_ROOT p.id parent " +
                "FROM fs_principal p, fs_group g, fs_group_member m, fs_principal u " +
                "WHERE p.id = g.id " +
                "AND m.group_id = g.id " +
                "AND m.principal_id = u.id " +
                "and u.name = '" + principal.getName() + "' " +
                "connect by prior m.principal_id = m.group_id";
        sqlQuery = "SELECT DISTINCT parent FROM ( " + sqlQuery + ")";
        SQLQuery query = session.createSQLQuery(sqlQuery);
        query.addScalar("parent", LongType.INSTANCE);
        List<Long> results = (List<Long>) query.list();
        Set<CfGroup> groups = new HashSet<CfGroup>();
        for (Long result : results) {
            groups.add(findById(result));
        }
        return groups;
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(g) from CfGroup g where " +
                "g.metadata.state = :state");
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(g) from CfGroup g where " +
                "g.name like upper(:filter) " +
                "and g.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean isMemberOf(CfGroup group, CfPrincipal principal) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(g) from CfGroupMember g where " +
                "g.group = :group " +
                "and g.principal = :principal");
        query.setEntity("group", group);
        query.setEntity("principal", principal);
        return ((Long) query.uniqueResult()).intValue() >= 1;
    }

    @Override
    public CfGroupMember findGroupMember(CfGroup group, CfPrincipal principal) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select g from CfGroupMember g where " +
                "g.group = :group " +
                "and g.principal = :principal");
        query.setEntity("group", group);
        query.setEntity("principal", principal);
        return (CfGroupMember) query.uniqueResult();
    }

// =============================================================================
    // CRUD METHODS
    // =============================================================================

    @Override
    public void addMember(CfGroup group, CfPrincipal member, CfUser user) throws RecursiveGroupException, LockedGroupException {

        // validate
        Validate.notNull(group, "Group should not be null");
        Validate.notNull(member, "Group member should not be null");

        // check locked group
        if (group.isLocked()) {
            log.error("Group is locked");
            throw new LockedGroupException("Locked group");
        }

        // check recursive add
//        if (member instanceof CfGroup) {
//            if (isInRecursive(group, (CfGroup) member))
//                throw new RecursiveGroupException("Recursive user group detected");
//        }

        // session
        Session session = sessionFactory.getCurrentSession();

        // populate
        CfGroupMember groupMember = new CfGroupMemberImpl();
        groupMember.setGroup(group);
        groupMember.setMember(member);

        // prepare metadata
        CfMetadata metadata = new CfMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreator(user.getId());
        groupMember.setMetadata(metadata);
        session.save(groupMember);
    }

    @Override
    public void addMembers(CfGroup group, List<CfPrincipal> principals, CfUser user) throws RecursiveGroupException, LockedGroupException {
        List<CfPrincipal> principalGroups = findMembers(group);
        List<CfPrincipal> newPrincipals = new ArrayList<CfPrincipal>();

        for (CfPrincipal principal : principals) {
            newPrincipals.add(principal);
        }

        for (CfPrincipal principalGroup : principalGroups) {
            if (!newPrincipals.contains(principalGroup)) {
                removeMember(group, principalGroup);
            }
        }

        for (CfPrincipal newGroup : newPrincipals) {
            if (!principalGroups.contains(newGroup)) {
                addMember(group, newGroup, user);
            }
        }
    }

    @Override
    public void removeMember(CfGroup group, CfPrincipal principal) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select g from CfGroupMember g where g.group = :group and g.principal = :principal");
        query.setEntity("group", group);
        query.setEntity("principal", principal);
        CfGroupMember groupMember = (CfGroupMember) query.uniqueResult();
        session.delete(groupMember);
    }

    private boolean isInRecursive(CfGroup parent, CfGroup child) {
        Set<CfGroup> hierarchicalGroup = findHierarchicalGroupAsNative(parent);
        return !hierarchicalGroup.add(child);
    }

}
