package net.canang.cfi.core.dd.dao.impl;

import net.canang.cfi.core.dd.dao.CfBankCodeDao;
import net.canang.cfi.core.dd.model.CfBankCode;
import net.canang.cfi.core.dd.model.impl.CfBankCodeImpl;
import net.canang.cfi.core.so.dao.DaoSupport;
import net.canang.cfi.core.so.model.CfMetaState;
import net.canang.cfi.core.so.model.CfUser;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
@Repository("bankDao")
public class CfBankCodeDaoImpl extends DaoSupport<Long, CfBankCode, CfBankCodeImpl> implements CfBankCodeDao {

    private static final Logger log = Logger.getLogger(CfBankCodeDaoImpl.class);

    // =============================================================================
    // FINDER METHODS
    // =============================================================================

    @Override
    public CfBankCode findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CfBankCode) session.get(CfBankCodeImpl.class, id);
    }

    @Override
    public CfBankCode findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from CfBankCode p where p.code = :code");
        query.setString("code", code);
        query.setCacheable(true);
        return (CfBankCode) query.uniqueResult();
    }

    @Override
    public List<CfBankCode> find() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select b from CfBankCode b where " +
                "b.metadata.state = :state " +
                "order by b.name");
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<CfBankCode>) query.list();
    }

    @Override
    public List<CfBankCode> find(Integer ofCfet, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select b from CfBankCode b where " +
                "b.metadata.state = :state " +
                "order by b.name");
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        query.setFirstResult(ofCfet);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfBankCode>) query.list();
    }

    @Override
    public List<CfBankCode> find(String filter, Integer ofCfet, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select b from CfBankCode b where " +
                "(b.code like upper(:filter) " +
                "or b.name like upper(:filter)) " +
                "and b.metadata.state = :state " +
                "order by b.name");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        query.setFirstResult(ofCfet);
        query.setMaxResults(limit);
        return (List<CfBankCode>) query.list();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(b) from CfBankCode b where " +
                "b.metadata.state = :state");
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(b) from CfBankCode b where " +
                "(b.code like upper(:filter) " +
                "or b.name like upper(:filter)) " +
                "and b.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    // =============================================================================
    // CRUD METHODS
    // =============================================================================

}
