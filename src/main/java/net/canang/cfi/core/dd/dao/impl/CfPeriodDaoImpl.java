package net.canang.cfi.core.dd.dao.impl;

import net.canang.cfi.core.dd.dao.CfPeriodDao;
import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.dd.model.impl.CfPeriodImpl;
import net.canang.cfi.core.so.dao.DaoSupport;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

import static net.canang.cfi.core.so.model.CfMetaState.ACTIVE;

/**
 * @author : alif haikal razak
 */
public class CfPeriodDaoImpl extends DaoSupport<Long, CfPeriod, CfPeriodImpl> implements CfPeriodDao {

    private static final Logger log = Logger.getLogger(CfPeriodDaoImpl.class);

    @Override
    public CfPeriod newInstance() {
        return new CfPeriodImpl();
    }

    // =============================================================================
    // FINDER METHODS
    // =============================================================================


    @Override
    public CfPeriod findCurrent(CfCostCenter costCenter) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select b from CfPeriod b where " +
                "b.costCenter = :costCenter " +
                "and b.current = true");
        query.setEntity("costCenter", costCenter);
        return (CfPeriod) query.uniqueResult();
    }

    @Override
    public List<CfPeriod> find(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select b from CfPeriod b where " +
                "b.metadata.state = :state");
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfPeriod>) query.list();
    }

    @Override
    public List<CfPeriod> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select b from CfPeriod b where " +
                "(upper(b.description) like upper(:filter) " +
                "or b.costCenter.code like upper(:filter)) " +
                "and b.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfPeriod>) query.list();
    }

    @Override
    public List<CfPeriod> find(String filter, String[] funds, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select b from CfPeriod b where " +
                "(upper(b.description) like upper(:filter) " +
                " or b.costCenter.code like upper(:filter)) " +
                "and b.costCenter.fund.code in (:funds) " +
                "and b.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setParameterList("funds", funds);
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfPeriod>) query.list();
    }

    @Override
    public List<CfPeriod> find(CfCostCenter costCenter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select b from CfPeriod b where b.costCenter = :costCenter");
        query.setEntity("costCenter", costCenter);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfPeriod>) query.list();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(b) from CfPeriod b where " +
                "b.metadata.state = :state");
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(b) from CfPeriod b where " +
                "(b.description like upper(:filter) " +
                "or b.costCenter.code like upper(:filter)) " +
                "and b.metadata.state = :state");
        query.setString("filter", filter);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter, String[] funds) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(b) from CfPeriod b where " +
                "(b.description like upper(:filter) " +
                "or b.costCenter.code like upper(:filter)) " +
                "and b.costCenter.fund.code in (:funds) " +
                "and b.metadata.state = :state");
        query.setString("filter", filter);
        query.setParameterList("funds", funds);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(CfCostCenter costCenter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(b) from CfPeriod b  where b.costCenter = :costCenter");
        query.setEntity("costCenter", costCenter);
        return ((Long) query.uniqueResult()).intValue();
    }
}
