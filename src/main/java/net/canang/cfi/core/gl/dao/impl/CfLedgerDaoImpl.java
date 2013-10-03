package net.canang.cfi.core.gl.dao.impl;

import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.gl.dao.CfLedgerDao;
import net.canang.cfi.core.gl.model.CfLedger;
import net.canang.cfi.core.gl.model.CfLedgerTransaction;
import net.canang.cfi.core.gl.model.impl.CfLedgerImpl;
import net.canang.cfi.core.gl.model.impl.CfLedgerTransactionImpl;
import net.canang.cfi.core.so.dao.DaoSupport;
import net.canang.cfi.core.so.model.CfMetaState;
import net.canang.cfi.core.so.model.CfMetadata;
import net.canang.cfi.core.so.model.CfUser;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
@Repository("ledgerDao")
public class CfLedgerDaoImpl extends DaoSupport<Long, CfLedger, CfLedgerImpl> implements CfLedgerDao {

    private static final Logger log = Logger.getLogger(CfLedgerDaoImpl.class);
    // =============================================================================
    // HELPER METHODS
    // =============================================================================

    public CfLedger newInstance() {
        CfLedger ledger = new CfLedgerImpl();
        return ledger;
    }

    public CfLedgerTransaction newTransactionInstance() {
        CfLedgerTransactionImpl ledgerTransaction = new CfLedgerTransactionImpl();
        return ledgerTransaction;
    }


    private Object getDomainById(Class clazz, Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(clazz, id);
    }

// =============================================================================
    // FINDER METHODS
    // =============================================================================

    @Override
    public CfLedger findById(Long id) {
        // session
        Session session = sessionFactory.getCurrentSession();
        return (CfLedger) session.get(CfLedgerImpl.class, id);
    }

    @Override
    public CfLedger findByReferenceNo(String referenceNo) {
        // session
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select l from CfLedger l where l.referenceNo = :referenceNo");
        query.setString("referenceNo", referenceNo);
        return (CfLedger) query.uniqueResult();

    }

    @Override
    public List<CfLedger> findByCostCenter(CfCostCenter costCenter) {

        // session
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select l from CfLedger l where l.requester = :costCenter");
        query.setEntity("costCenter", costCenter);
        return (List<CfLedger>) query.list();
    }

    @Override
    public CfLedgerTransaction findTransactionById(Long id) {
        // session
        Session session = sessionFactory.getCurrentSession();
        return (CfLedgerTransaction) session.get(CfLedgerTransactionImpl.class, id);
    }


    @Override
    public List<CfLedger> find(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select l from CfLedger l");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfLedger>) query.list();
    }


    @Override
    public List<CfLedger> find(String filter, Integer offset, Integer limit) {

        List<CfLedger> list;
        Session session = sessionFactory.getCurrentSession();
        if (filter != null) {
            Query query = session.createQuery("select l from CfLedger l where " +
                    "(l.referenceNo like upper(:filter) " +
                    "or l.sourceNo like upper(:filter) " +
                    "or l.description like upper(:filter)) " +
                    "and l.metadata.state = :state");
            query.setString("filter", WILDCARD + filter + WILDCARD);
            query.setInteger("state", CfMetaState.ACTIVE.ordinal());
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            list = (List<CfLedger>) query.list();
        } else {
            Query query = session.createQuery("select l from CfLedger l where l.metadata.state = :state");
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            query.setInteger("state", CfMetaState.ACTIVE.ordinal());
            list = (List<CfLedger>) query.list();
        }
        return list;
    }

    @Override
    public List<CfLedgerTransaction> findTransactions(CfLedger ledger, String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select lt from CfLedgerTransaction lt where " +
                "lt.ledger = :ledger " +
                "and lt.description like upper(:filter)");
        query.setEntity("ledger", ledger);
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfLedgerTransaction>) query.list();
    }

    @Override
    public List<CfLedgerTransaction> findTransactions(CfLedger ledger) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select lt from CfLedgerTransaction lt where " +
                "lt.ledger = :ledger");
        query.setEntity("ledger", ledger);
        return (List<CfLedgerTransaction>) query.list();
    }

    @Override
    public List<CfLedgerTransaction> findTransactions(CfLedger ledger, Integer offset, Integer limit) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select lt from CfLedgerTransaction lt where " +
                "lt.ledger = :ledger");
        query.setEntity("ledger", ledger);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfLedgerTransaction>) query.list();
    }

    @Override
    public List<CfLedgerTransaction> findTransactions(String filter, Integer offset, Integer limit) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select lt from CfLedgerTransaction lt where " +
                "(lt.ledger.referenceNo like upper(:filter) " +
                "or lt.description like upper(:filter)) " +
                "and lt.metadata.state = :state " +
                "order by lt.id");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfLedgerTransaction>) query.list();
    }

    @Override
    public List<CfLedgerTransaction> findTransactions(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select lt from CfLedgerTransaction lt ");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfLedgerTransaction>) query.list();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(l) from CfLedger l");
        return ((Long) query.uniqueResult()).intValue();
    }


    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(l) from CfLedger l where " +
                "l.referenceNo like upper(:filter) " +
                "and l.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countTransaction(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(lt) from CfLedgerTransaction lt where " +
                "(lt.ledger.referenceNo like upper(:filter) " +
                "or lt.description like upper(:filter)) " +
                "and lt.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countTransaction(CfLedger ledger) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(lt) from CfLedgerTransaction lt where " +
                "lt.ledger = :ledger");
        query.setEntity("ledger", ledger);
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countTransaction(CfLedger ledger, String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(lt) from CfLedgerTransaction lt where " +
                "(lt.ledger = :ledger " +
                "or lt.description like upper(:filter)) " +
                "and lt.metadata.state = :state");
        query.setEntity("ledger", ledger);
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }


    // =============================================================================
    // CRUD METHODS
    // =============================================================================

    public void addTransaction(CfLedger ledger, CfLedgerTransaction ltx, CfUser user) {

        Session session = sessionFactory.getCurrentSession();
        ((CfLedgerTransactionImpl) ltx).setLedger(ledger);

        // prepare metadata
        CfMetadata metadata = new CfMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreator(user.getId());
        metadata.setState(CfMetaState.ACTIVE);
        ltx.setMetadata(metadata);
        session.save(ltx);


    }

    public void addTransactions(CfLedger ledger, List<CfLedgerTransaction> ltxs, CfUser user) {

        Session session = sessionFactory.getCurrentSession();
        for (CfLedgerTransaction ltx : ltxs) {
            ((CfLedgerTransactionImpl) ltx).setLedger(ledger);

            // prepare metadata
            CfMetadata metadata = new CfMetadata();
            metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            metadata.setCreator(user.getId());
            metadata.setState(CfMetaState.ACTIVE);
            ltx.setMetadata(metadata);

            session.save(ltx);
        }

    }
}

