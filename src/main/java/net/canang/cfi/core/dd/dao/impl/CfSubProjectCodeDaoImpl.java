package net.canang.cfi.core.dd.dao.impl;

import net.canang.cfi.core.dd.dao.CfSubProjectCodeDao;
import net.canang.cfi.core.dd.model.CfSubProjectCode;
import net.canang.cfi.core.dd.model.impl.CfSubProjectCodeImpl;
import net.canang.cfi.core.so.dao.DaoSupport;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

import static net.canang.cfi.core.so.model.CfMetaState.ACTIVE;

/**
 * @author : alif haikal razak
 */
public class CfSubProjectCodeDaoImpl extends DaoSupport<Long, CfSubProjectCode, CfSubProjectCodeImpl> implements CfSubProjectCodeDao {

    private static final Logger log = Logger.getLogger(CfSubProjectCodeDaoImpl.class);


    // =============================================================================
    // FINDER METHODS
    // =============================================================================

    @Override
    public CfSubProjectCode findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CfSubProjectCode) session.get(CfSubProjectCodeImpl.class, id);
    }

    @Override
    public CfSubProjectCode findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sp from CfSubProjectCode sp where sp.code = :code");
        query.setString("code", code);
        query.setCacheable(true);
        return (CfSubProjectCode) query.uniqueResult();
    }

    @Override
    public List<CfSubProjectCode> find(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sp from CfSubProjectCode sp where " +
                "sp.metadata.state = :state " +
                "order by sp.code");
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfSubProjectCode>) query.list();
    }

    @Override
    public List<CfSubProjectCode> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sp from CfSubProjectCode sp where " +
                "(sp.code like upper(:filter) " +
                "or upper(sp.description) like upper(:filter)) " +
                "and sp.metadata.state = :state " +
                "order by sp.code");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfSubProjectCode>) query.list();
    }


    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(sp) from CfSubProjectCode sp where " +
                "sp.metadata.state = :state");
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(sp) from CfSubProjectCode sp where " +
                "(sp.code like upper(:filter) " +
                "or upper(sp.description) like upper(:filter)) " +
                "and sp.metadata.state = :state " +
                "order by sp.code");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }
}
