package net.canang.cfi.core.so.dao.impl;

/**
 * @author canang.technologies
 * @since 7/13/13
 */

import net.canang.cfi.core.so.dao.CfActorDao;
import net.canang.cfi.core.so.dao.DaoSupport;
import net.canang.cfi.core.so.model.CfActor;
import net.canang.cfi.core.so.model.CfActorType;
import net.canang.cfi.core.so.model.CfMetaState;
import net.canang.cfi.core.so.model.impl.CfActorImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
@Repository("actorDao")
public class CfActorDaoImpl extends DaoSupport<Long, CfActor, CfActorImpl> implements CfActorDao {

    // =============================================================================
    // FINDER METHODS
    // =============================================================================

    @Override
    public CfActor findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CfActor) session.get(CfActorImpl.class, id);
    }

    @Override
    public CfActor findByIdentityNo(String identityNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from CfActor a where " +
                "a.identityNo = :identityNo " +
                "and a.metadata.state = :state ");
        query.setString("identityNo", identityNo);
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        return (CfActor) query.uniqueResult();
    }

    @Override
    public CfActor findByNricNo(String nricNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from CfActor a where " +
                "a.nricNo = :nricNo " +
                "and a.metadata.state = :state ");
        query.setString("nricNo", nricNo);
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        return (CfActor) query.uniqueResult();
    }

    @Override
    public List<CfActor> find(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from CfActor a where " +
                "a.metadata.state = :state " +
                "order by a.code");
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    @Override
    public List<CfActor> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from CfActor a where " +
                "(a.code like upper(:filter) " +
                "or upper(a.description) like upper(:filter)) " +
                "and a.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    @Override
    public List<CfActor> find(CfActorType type, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from CfActor a where " +
                "a.actorType = :actorType " +
                "and a.metadata.state = :state");
        query.setInteger("actorType", type.ordinal());
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    @Override
    public List<CfActor> find(CfActorType type, String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from CfActor a where " +
                "(a.identityNo like upper(:filter) " +
                "or upper(a.name) like upper(:filter)) " +
                "and a.actorType = :actorType " +
                "and a.metadata.state = :state");
        query.setInteger("actorType", type.ordinal());
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(a) from CfActor a where " +
                "a.metadata.state = :state");
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(a) from CfActor a where " +
                "(a.name like upper(:filter) " +
                "or upper(a.identityNo) like upper(:filter)) " +
                "and a.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(CfActorType type) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(a) from CfActor a where " +
                "a.actorType = :actorType " +
                "and a.metadata.state = :state");
        query.setInteger("actorType", type.ordinal());
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    // =============================================================================
    // CRUD METHODS
    // =============================================================================

}
