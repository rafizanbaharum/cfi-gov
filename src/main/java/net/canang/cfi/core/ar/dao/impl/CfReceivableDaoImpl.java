package net.canang.cfi.core.ar.dao.impl;

import net.canang.cfi.core.ar.dao.CfReceivableDao;
import net.canang.cfi.core.ar.model.CfReceivable;
import net.canang.cfi.core.ar.model.CfReceivableTransaction;
import net.canang.cfi.core.ar.model.impl.CfReceivableImpl;
import net.canang.cfi.core.ar.model.impl.CfReceivableTransactionImpl;
import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.so.dao.DaoSupport;
import net.canang.cfi.core.so.model.CfMetaState;
import net.canang.cfi.core.so.model.CfMetadata;
import net.canang.cfi.core.so.model.CfUser;
import org.apache.commons.lang.Validate;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public class CfReceivableDaoImpl extends DaoSupport<Long, CfReceivable, CfReceivableImpl> implements CfReceivableDao {
    @Override
    public CfReceivable findReceivableById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CfReceivable) session.get(CfReceivableImpl.class, id);
    }

    @Override
    public CfReceivable findReceivableByReferenceNo(String referenceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from CfReceivable r where " +
                "r.referenceNo = :referenceNo");
        query.setString("referenceNo", referenceNo);
        query.setCacheable(true);
        return (CfReceivable) query.uniqueResult();
    }

    @Override
    public CfReceivableTransaction findTransactionById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CfReceivableTransaction) session.get(CfReceivableTransactionImpl.class, id);
    }

    @Override
    public List<CfReceivable> findReceivables(CfCostCenter request) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from CfReceivable r  where (" +
                "and r.requester = :requester " +
                "and i.metadata.state = :state " +
                "order by i.id desc");
        query.setEntity("requester", request);
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        return (List<CfReceivable>) query.list();
    }

    @Override
    public List<CfReceivable> findReceivables(CfCostCenter request, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from CfReceivable r  where " +
                "r.requester = :requester " +
                "and r.metadata.state = :state " +
                "order by r.id desc");
        query.setEntity("requester", request);
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfReceivable>) query.list();
    }

    @Override
    public List<CfReceivableTransaction> findTransactions(CfReceivable receivable) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ri from FsPaymentTransaction ri where " +
                "ri.payment = :invoice " +
                "and ri.metadata.state = :metaState " +
                "order by ri.amount desc");   // desc - debit first
        query.setEntity("receivable", receivable);
        query.setInteger("metaState", CfMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<CfReceivableTransaction>) query.list();
    }

    @Override
    public List<CfReceivableTransaction> findTransactions(CfReceivable receivable, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ri from FsPaymentTransaction ri where " +
                "ri.payment = :invoice " +
                "and ri.metadata.state = :metaState " +
                "order by ri.amount desc");   // desc - debit first
        query.setEntity("receivable", receivable);
        query.setInteger("metaState", CfMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfReceivableTransaction>) query.list();
    }

    @Override
    public Integer countReceivable(CfCostCenter request) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(r) from CfReceivable r where " +
                "r.metadata.state = :state"
        );
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countTransaction(CfReceivable receivable) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(rt) from CfReceivableTransaction rt where " +
                "rt.receivable = :receivable " +
                "and rt.metadata.state = :state"
        );
        query.setEntity("receivable", receivable);
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    public void addTransaction(CfReceivable receivable, CfReceivableTransaction transaction, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        Session session = sessionFactory.getCurrentSession();
        transaction.setReceivable(receivable);

        // prepare metadata
        CfMetadata metadata = new CfMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreator(user.getId());
        metadata.setState(CfMetaState.ACTIVE);
        transaction.setMetadata(metadata);
        session.merge(transaction);

    }

    public void updateTransaction(CfReceivable receivable, CfReceivableTransaction transaction, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        Session session = sessionFactory.getCurrentSession();
        transaction.setReceivable(receivable);

        // prepare metadata
        CfMetadata metadata = transaction.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifier(user.getId());
        transaction.setMetadata(metadata);
        session.update(transaction);
    }

    public void removeTransaction(CfReceivable receivable, CfReceivableTransaction transaction, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        Session session = sessionFactory.getCurrentSession();
        transaction.setReceivable(receivable);

        // prepare metadata
        CfMetadata metadata = transaction.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifier(user.getId());
        metadata.setState(CfMetaState.INACTIVE);
        transaction.setMetadata(metadata);
        session.update(transaction);

    }

    public void addTransactions(CfReceivable receivable, List<CfReceivableTransaction> transactions, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        for (CfReceivableTransaction transaction : transactions) {
            addTransaction(receivable, transaction, user);
        }
    }

    public void updateTransactions(CfReceivable receivable, List<CfReceivableTransaction> transactions, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        for (CfReceivableTransaction transaction : transactions) {
            updateTransaction(receivable, transaction, user);
        }
    }

    public void removeTransactions(CfReceivable receivable, List<CfReceivableTransaction> transactions, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        for (CfReceivableTransaction transaction : transactions) {
            removeTransaction(receivable, transaction, user);
        }
    }
}
