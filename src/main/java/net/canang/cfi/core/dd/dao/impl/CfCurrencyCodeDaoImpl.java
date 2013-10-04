package net.canang.cfi.core.dd.dao.impl;

import net.canang.cfi.core.dd.dao.CfCurrencyCodeDao;
import net.canang.cfi.core.dd.model.CfCurrencyCode;
import net.canang.cfi.core.dd.model.impl.CfCurrencyCodeImpl;
import net.canang.cfi.core.so.dao.DaoSupport;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static net.canang.cfi.core.so.model.CfMetaState.ACTIVE;

/**
 * @author : alif haikal razak
 */
@Repository("currencyCodeDao")
public class CfCurrencyCodeDaoImpl extends DaoSupport<Long, CfCurrencyCode, CfCurrencyCodeImpl> implements CfCurrencyCodeDao {

    private static final Logger log = Logger.getLogger(CfCurrencyCodeDaoImpl.class);

    // =============================================================================
    // FINDER METHODS
    // =============================================================================

    @Override
    public CfCurrencyCode findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CfCurrencyCode) session.get(CfCurrencyCodeImpl.class, id);
    }

    @Override
    public CfCurrencyCode findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from CfCurrencyCode p where p.code = :code and p.metadata.state = :state ");
        query.setInteger("state", ACTIVE.ordinal());
        query.setString("code", code);
        return (CfCurrencyCode) query.uniqueResult();
    }

    @Override
    public List<CfCurrencyCode> find(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfCurrencyCode c where c.metadata.state = :state order by c.description");
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfCurrencyCode>) query.list();
    }

    @Override
    public List<CfCurrencyCode> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfCurrencyCode c where " +
                "(c.code like upper(:filter) " +
                "or upper(c.description) like upper(:filter)) " +
                "and c.metadata.state = :state " +
                "order by c.description");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfCurrencyCode>) query.list();
    }


    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(c) from CfCurrencyCode c where " +
                "c.metadata.state = :state");
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(c) from CfCurrencyCode c where " +
                "(c.code like upper(:filter) " +
                "or upper(c.description) like upper(:filter)) " +
                "and c.metadata.state = :state " +
                "order by c.code");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }
}
