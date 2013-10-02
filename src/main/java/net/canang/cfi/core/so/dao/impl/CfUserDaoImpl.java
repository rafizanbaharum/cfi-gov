package net.canang.cfi.core.so.dao.impl;

import net.canang.cfi.core.so.dao.CfPrincipalDao;
import net.canang.cfi.core.so.dao.CfUserDao;
import net.canang.cfi.core.so.dao.DaoSupport;
import net.canang.cfi.core.so.model.CfActor;
import net.canang.cfi.core.so.model.CfGroup;
import net.canang.cfi.core.so.model.CfMetaState;
import net.canang.cfi.core.so.model.CfUser;
import net.canang.cfi.core.so.model.impl.CfUserImpl;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author canang.technologies
 * @since 7/12/13
 */
@Repository("userDao")
public class CfUserDaoImpl extends DaoSupport<Long, CfUser, CfUserImpl> implements CfUserDao {

    private static final Logger log = Logger.getLogger(CfUserDaoImpl.class);

    @Autowired
    private CfPrincipalDao principalDao;


    // =============================================================================
    // FINDER METHODS
    // =============================================================================

    @Override
    public List<CfGroup> findUserGroups(CfUser user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from CfGroup r inner join r.groupMembers m where m.principal = :user");
        query.setEntity("user", user);
        return (List<CfGroup>) query.list();
    }

    @Override
    public boolean isExists(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from CfUser u where " +
                "u.name = :username");
        query.setString("username", username);
        return 0 < ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public CfUser findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CfUser) session.get(CfUserImpl.class, id);
    }

    @Override
    public CfUser findByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select u from CfUser u where u.name = :username and u.metadata.state = :state");
        query.setString("username", username);
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        return (CfUser) query.uniqueResult();
    }

    @Override
    public CfUser findByActor(CfActor actor) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select u from CfUser u where u.actor  = :actor");
        query.setEntity("actor", actor);
        return (CfUser) query.uniqueResult();
    }

    public CfUser findByRealName(String realname) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select u from CfUser u where u.realname = :realname");
        query.setString("realname", realname);
        return (CfUser) query.uniqueResult();
    }

    @Override
    public List<CfUser> find(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from CfUser s");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfUser>) query.list();
    }

    @Override
    public List<CfUser> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from CfUser s where (s.name like :filter or s.realname like upper(:filter))");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfUser>) query.list();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(u) from CfUser u");
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from CfUser s where s.name like :filter " +
                "or s.realname like upper(:filter)");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        return ((Long) query.uniqueResult()).intValue();
    }
}
