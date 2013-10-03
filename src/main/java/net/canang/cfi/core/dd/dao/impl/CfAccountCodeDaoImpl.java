package net.canang.cfi.core.dd.dao.impl;

import net.canang.cfi.core.dd.dao.CfAccountCodeDao;
import net.canang.cfi.core.dd.model.CfAccountCode;
import net.canang.cfi.core.dd.model.impl.CfAccountCodeImpl;
import net.canang.cfi.core.so.dao.DaoSupport;
import net.canang.cfi.core.so.model.CfMetaState;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * @author : alif haikal razak
 */
public class CfAccountCodeDaoImpl extends DaoSupport<Long, CfAccountCode, CfAccountCodeImpl> implements CfAccountCodeDao {

    // =============================================================================
    // FINDER METHODS
    // =============================================================================

    @Override
    public CfAccountCode findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CfAccountCode) session.get(CfAccountCodeImpl.class, id);
    }

    @Override
    public CfAccountCode findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from CfAccountCode a where a.code = :code");
        query.setString("code", code);
        return (CfAccountCode) query.uniqueResult();
    }

    @Override
    public List<CfAccountCode> find(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from CfAccountCode a where " +
                "a.metadata.state = :state " +
                "order by a.code");
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    @Override
    public List<CfAccountCode> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from CfAccountCode a where " +
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
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(a) from CfAccountCode a where " +
                "a.metadata.state = :state");
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(a) from CfAccountCode a where " +
                "(a.code like upper(:filter) " +
                "or upper(a.description) like upper(:filter)) " +
                "and a.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }
}
