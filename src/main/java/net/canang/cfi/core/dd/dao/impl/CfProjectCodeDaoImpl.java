package net.canang.cfi.core.dd.dao.impl;

import net.canang.cfi.core.dd.dao.CfProjectCodeDao;
import net.canang.cfi.core.dd.model.CfProjectCode;
import net.canang.cfi.core.dd.model.impl.CfProjectCodeImpl;
import net.canang.cfi.core.so.dao.DaoSupport;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

import static net.canang.cfi.core.so.model.CfMetaState.ACTIVE;

/**
 * @author : alif haikal razak
 */
public class CfProjectCodeDaoImpl extends DaoSupport<Long, CfProjectCode, CfProjectCodeImpl> implements CfProjectCodeDao {

    private static final Logger log = Logger.getLogger(CfProjectCodeDaoImpl.class);

    // =============================================================================
    // FINDER METHODS
    // =============================================================================


    @Override
    public CfProjectCode findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CfProjectCode) session.get(CfProjectCodeImpl.class, id);
    }

    @Override
    public CfProjectCode findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from CfProjectCode p where p.code = :code");
        query.setString("code", code);
        return (CfProjectCode) query.uniqueResult();
    }

    @Override
    public List<CfProjectCode> find(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from CfProjectCode p where " +
                "p.metadata.state = :state " +
                "order by p.code asc");
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfProjectCode>) query.list();
    }

    @Override
    public List<CfProjectCode> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from CfProjectCode p where " +
                "(p.code like upper(:filter) " +
                "or upper(p.description) like upper(:filter)) " +
                "and p.metadata.state = :state " +
                "order by p.code asc");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfProjectCode>) query.list();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(p) from CfProjectCode p where " +
                "p.metadata.state = :state");
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(p) from CfProjectCode p where " +
                "(p.code like upper(:filter) " +
                "or upper(p.description) like upper(:filter)) " +
                "and p.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }
}
