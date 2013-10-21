package net.canang.cfi.core.so.dao.impl;

import net.canang.cfi.core.dd.dao.CfReferenceNoDao;
import net.canang.cfi.core.dd.model.CfReferenceNo;
import net.canang.cfi.core.so.model.impl.CfReferenceNoImpl;
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
@Repository("referenceNoDao")
public class CfReferenceNoDaoImpl extends DaoSupport<Long, CfReferenceNo, CfReferenceNoImpl> implements CfReferenceNoDao {

    private static final Logger log = Logger.getLogger(CfReferenceNoDaoImpl.class);

    // =============================================================================
    // HELPER METHODS
    // =============================================================================

    @Override
    public CfReferenceNo newInstance() {
        return new CfReferenceNoImpl();
    }


    // =============================================================================
    // FINDER METHODS
    // =============================================================================

    @Override
    public CfReferenceNo findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CfReferenceNo) session.get(CfReferenceNoImpl.class, id);
    }

    @Override
    public CfReferenceNo findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select rn from CfReferenceNo rn where rn.code = :code");
        query.setString("code", code);
        query.setCacheable(true);
        return (CfReferenceNo) query.uniqueResult();
    }

    @Override
    public List<CfReferenceNo> find(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select rn from CfReferenceNo rn where " +
                "rn.metadata.state = :state");
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return query.list();
    }

    @Override
    public List<CfReferenceNo> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select rn from CfReferenceNo rn where " +
                "(rn.code like upper(:filter) " +
                "or upper(rn.description) like upper(:filter)) " +
                "and rn.metadata.state = :state");
        query.setString("filter", filter);
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(rn) from CfReferenceNo rn where " +
                "rn.metadata.state = :state");
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }


    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(rn) from CfReferenceNo rn where " +
                "(rn.code like upper(:filter) " +
                "or upper(rn.description) like upper(:filter)) " +
                "and rn.metadata.state = :state");
        query.setString("filter", filter);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }
}
