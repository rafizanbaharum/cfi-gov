package net.canang.cfi.core.dd.dao.impl;

import net.canang.cfi.core.dd.dao.CfFundCodeDao;
import net.canang.cfi.core.dd.model.CfFundCode;
import net.canang.cfi.core.dd.model.impl.CfFundCodeImpl;
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
@Repository("fundCodeDao")
public class CfFundCodeDaoImpl extends DaoSupport<Long, CfFundCode, CfFundCodeImpl> implements CfFundCodeDao {

    private static final Logger log = Logger.getLogger(CfFundCodeDaoImpl.class);

    // =============================================================================
    // FINDER METHODS
    // =============================================================================

    @Override
    public CfFundCode findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select f from CfFundCode f where f.code = :code");
        query.setString("code", code);
        query.setCacheable(true);
        return (CfFundCode) query.uniqueResult();
    }

    @Override
    public List<CfFundCode> find(Integer offset, Integer limit) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select f from CfFundCode f where " + "f.metadata.state = :state");
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfFundCode>) query.list();
    }

    @Override
    public List<CfFundCode> find(String filter, String[] funds, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select f from CfFundCode f where " + "(f.code like upper(:filter) " +
                "or upper(f.description) like upper(:filter)) " + "and f.code in (:funds) " +
                "and f.metadata.state = :state " + "order by f.code");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setParameterList("funds", funds);
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfFundCode>) query.list();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(f) from CfFundCode f where " + "f.metadata.state = :state");
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(f) from CfFundCode f where " + "(f.code like upper(:filter) " +
                "or upper(f.description) like upper(:filter)) " + "and f.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }
}
