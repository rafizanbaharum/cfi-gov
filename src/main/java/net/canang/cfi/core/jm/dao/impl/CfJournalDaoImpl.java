package net.canang.cfi.core.jm.dao.impl;

import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.jm.dao.CfJournalDao;
import net.canang.cfi.core.jm.model.CfJournal;
import net.canang.cfi.core.jm.model.CfJournalTransaction;
import net.canang.cfi.core.jm.model.CfJournalType;
import net.canang.cfi.core.jm.model.impl.CfJournalImpl;
import net.canang.cfi.core.jm.model.impl.CfJournalTransactionImpl;
import net.canang.cfi.core.jm.model.impl.CfManualJournalImpl;
import net.canang.cfi.core.so.dao.DaoSupport;
import net.canang.cfi.core.so.model.CfMetaState;
import net.canang.cfi.core.so.model.CfMetadata;
import net.canang.cfi.core.so.model.CfUser;
import org.apache.commons.lang.Validate;
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
@SuppressWarnings({"unchecked"})
@Repository("journalDao")
public class CfJournalDaoImpl extends DaoSupport<Long, CfJournal, CfJournalImpl> implements CfJournalDao {

    private static final Logger log = Logger.getLogger(CfJournalDaoImpl.class);

    // =============================================================================
    // HELPER METHODS
    // =============================================================================

    public CfJournal newInstance(CfJournalType type) {
        CfJournal journal = null;
        switch (type) {
            case AUTO:
                break;
            case MANUAL:
                journal = new CfManualJournalImpl();
                break;
        }
        return journal;
    }

    public CfJournalTransaction newTransactionInstance() {
        return new CfJournalTransactionImpl();
    }

    // =============================================================================
    // FINDER METHODS
    // =============================================================================

    @Override
    public CfJournal findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CfJournal) session.get(CfJournalImpl.class, id);
    }

    @Override
    public CfJournalTransaction findTransactionById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CfJournalTransaction) session.get(CfJournalTransactionImpl.class, id);
    }


    @Override
    public CfJournal findByReferenceNo(String referenceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select j from CfJournal j where j.referenceNo = :referenceNo");
        query.setString("referenceNo", referenceNo);
        query.setCacheable(true);
        return (CfJournal) query.uniqueResult();
    }

    @Override
    public CfJournal findByJournalNo(String journalNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select j from CfJournal j where j.journalNo = :journal");
        query.setString("journal", journalNo);
        return (CfJournal) query.uniqueResult();
    }

    @Override
    public CfJournal findBySourceNo(String sourceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select j from CfJournal j where j.sourceNo = :journal");
        query.setString("journal", sourceNo);
        return (CfJournal) query.uniqueResult();
    }

    @Override
    public List<CfJournal> findManyBySourceNo(String sourceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select j from CfJournal j where " +
                "j.sourceNo = :sourceNo " +
                "and j.metadata.state = :metaState " +
                "order by j.id desc");
        query.setString("sourceNo", sourceNo);
        query.setInteger("metaState", CfMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<CfJournal>) query.list();
    }


    @Override
    public List<CfJournal> find(CfJournalType type, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select j from CfJournal j where " +
                "j.journalType = :journalType " +
                "and j.metadata.state = :metaState " +
                "order by j.id desc");
        query.setInteger("metaState", CfMetaState.ACTIVE.ordinal());
        query.setInteger("journalType", type.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfJournal>) query.list();
    }

    @Override
    public List<CfJournal> find(CfJournalType type, String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select j from CfJournal j where " +
                "(upper(j.referenceNo) like upper(:filter) " +
                "or upper(j.journalNo) like upper(:filter) " +
                "or upper(j.description) like upper(:filter)) " +
                "and j.journalType = :journalType " +
                "and j.metadata.state = :metaState " +
                "order by j.id desc");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("journalType", type.ordinal());
        query.setInteger("metaState", CfMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfJournal>) query.list();
    }

    @Override
    public List<CfJournal> find(CfJournalType type, CfCostCenter costCenter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select j from CfJournal j where " +
                "j.journalType = :journalType " +
                "and j.metadata.state = :metaState " +
                "and j.requester = :costCenter " +
                "order by j.id desc");
        query.setInteger("journalType", type.ordinal());
        query.setInteger("metaState", CfMetaState.ACTIVE.ordinal());
        query.setEntity("costCenter", costCenter);
        query.setCacheable(true);
        return (List<CfJournal>) query.list();
    }

    @Override
    public List<CfJournal> find(CfJournalType type, CfCostCenter costCenter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select j from CfJournal j where " +
                "j.journalType = :journalType " +
                "and j.metadata.state = :metaState " +
                "and j.requester = :costCenter " +
                "order by j.id desc");
        query.setInteger("journalType", type.ordinal());
        query.setInteger("metaState", CfMetaState.ACTIVE.ordinal());
        query.setEntity("costCenter", costCenter);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfJournal>) query.list();
    }

    @Override
    public List<CfJournalTransaction> findTransactions(CfJournal journal) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ji from CfJournalTransaction ji where " +
                "ji.journal = :journal " +
                "and ji.metadata.state = :metaState " +
                "order by ji.id asc");
        query.setEntity("journal", journal);
        query.setInteger("metaState", CfMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<CfJournalTransaction>) query.list();
    }

    @Override
    public List<CfJournalTransaction> findTransactions(CfJournal journal, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ji from CfJournalTransaction ji where " +
                "ji.journal = :journal " +
                "and ji.metadata.state = :metaState " +
                "order by ji.id desc");
        query.setEntity("journal", journal);
        query.setInteger("metaState", CfMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfJournalTransaction>) query.list();
    }

    @Override
    public Integer count(CfJournalType type) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(j) from CfJournal j where " +
                "j.journalType = :journalType " +
                "and j.metadata.state = :metaState ");
        query.setInteger("journalType", type.ordinal());
        query.setInteger("metaState", CfMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(CfJournalType type, CfCostCenter costCenter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(j) from CfJournal j where " +
                "j.journalType = :journalType " +
                "and j.metadata.state = :metaState " +
                "and j.requester = :costCenter");
        query.setInteger("journalType", type.ordinal());
        query.setInteger("metaState", CfMetaState.ACTIVE.ordinal());
        query.setEntity("costCenter", costCenter);
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(CfJournalType type, String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(j) from CfJournal j where " +
                "(upper(j.referenceNo) like upper(:filter) " +
                "or upper(j.journalNo) like upper(:filter) " +
                "or upper(j.description) like upper(:filter)) " +
                "and j.journalType = :journalType " +
                "and j.metadata.state = :metaState ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("journalType", type.ordinal());
        query.setInteger("metaState", CfMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countTransaction(CfJournal journal) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(ji) from CfJournalTransaction ji where " +
                "ji.journal = :journal");
        query.setEntity("journal", journal);
        return ((Long) query.uniqueResult()).intValue();
    }

    // =============================================================================
    // CRUD METHODS
    // =============================================================================

    public void addTransaction(CfJournal journal, CfJournalTransaction transaction, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        Session session = sessionFactory.getCurrentSession();
        transaction.setJournal(journal);

        // prepare metadata
        CfMetadata metadata = new CfMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreator(user.getId());
        metadata.setState(CfMetaState.ACTIVE);
        transaction.setMetadata(metadata);
        session.save(transaction);
    }

    public void updateTransaction(CfJournal journal, CfJournalTransaction transaction, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        Session session = sessionFactory.getCurrentSession();
        transaction.setJournal(journal);

        // prepare metadata
        CfMetadata metadata = transaction.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifier(user.getId());
        transaction.setMetadata(metadata);
        session.update(transaction);
    }

    public void removeTransaction(CfJournal journal, CfJournalTransaction transaction, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        Session session = sessionFactory.getCurrentSession();
        transaction.setJournal(journal);

        // prepare metadata
        CfMetadata metadata = transaction.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifier(user.getId());
        metadata.setState(CfMetaState.INACTIVE);
        transaction.setMetadata(metadata);
        session.update(transaction);
    }

    public void addTransactions(CfJournal journal, List<CfJournalTransaction> transactions, CfUser user) {
        for (CfJournalTransaction transaction : transactions) {
            addTransaction(journal, transaction, user);
        }
    }

    public void updateTransactions(CfJournal journal, List<CfJournalTransaction> transactions, CfUser user) {
        for (CfJournalTransaction transaction : transactions) {
            updateTransaction(journal, transaction, user);
        }
    }

    public void removeTransactions(CfJournal journal, List<CfJournalTransaction> transactions, CfUser user) {
        for (CfJournalTransaction transaction : transactions) {
            removeTransaction(journal, transaction, user);
        }
    }
}

