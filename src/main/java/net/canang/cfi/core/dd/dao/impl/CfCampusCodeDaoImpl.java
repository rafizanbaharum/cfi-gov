package net.canang.cfi.core.dd.dao.impl;

import net.canang.cfi.core.dd.dao.CfCampusCodeDao;
import net.canang.cfi.core.dd.model.CfCampusCode;
import net.canang.cfi.core.dd.model.impl.CfCampusCodeImpl;
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
@Repository("campusCodeDao")
public class CfCampusCodeDaoImpl extends DaoSupport<Long, CfCampusCode, CfCampusCodeImpl> implements CfCampusCodeDao {

    private static final Logger log = Logger.getLogger(CfCampusCodeDaoImpl.class);

    // =============================================================================
    // FINDER METHODS
    // =============================================================================

    @Override
    public CfCampusCode findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from CfCampusCode s where s.code = :code");
        query.setString("code", code);
        query.setCacheable(true);
        return (CfCampusCode) query.uniqueResult();
    }

    @Override
    public List<CfCampusCode> find(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfCampusCode c where " +
                "c.metadata.state = :state " +
                "order by c.code");
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfCampusCode>) query.list();
    }

    @Override
    public List<CfCampusCode> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfCampusCode c where " +
                "(c.code like upper(:filter) " +
                "or upper(c.description) like upper(:filter))" +
                "and c.metadata.state = :state " +
                "order by c.code");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfCampusCode>) query.list();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(c) from CfCampusCode c where " +
                "c.metadata.state = :state");
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(c) from CfCampusCode c where " +
                "(c.code like upper(:filter) " +
                "or upper(c.description) like upper(:filter)) " +
                "and c.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }
}
