package net.canang.cfi.core.ap.dao.impl;

import net.canang.cfi.core.ap.dao.CfPayableDao;
import net.canang.cfi.core.ap.model.CfPayable;
import net.canang.cfi.core.ap.model.CfPayableTransaction;
import net.canang.cfi.core.ap.model.impl.CfPayableImpl;
import net.canang.cfi.core.ap.model.impl.CfPayableTransactionImpl;
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
 * @author rafizan.bahapum
 * @since 10/3/13
 */
public class CfPayableDaoImpl extends DaoSupport<Long, CfPayable, CfPayableImpl> implements CfPayableDao {

    @Override
    public CfPayable findPayableById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CfPayable) session.get(CfPayableImpl.class, id);
    }

    @Override
    public CfPayable findPayableByReferenceNo(String referenceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from CfPayable r where " +
                "r.referenceNo = :referenceNo");
        query.setString("referenceNo", referenceNo);
        query.setCacheable(true);
        return (CfPayable) query.uniqueResult();
    }

    @Override
    public CfPayableTransaction findTransactionById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CfPayableTransaction) session.get(CfPayableTransactionImpl.class, id);
    }

    @Override
    public List<CfPayable> findPayables(CfCostCenter request) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from CfPayable r  where (" +
                "and r.requester = :requester " +
                "and i.metadata.state = :state " +
                "order by i.id desc");
        query.setEntity("requester", request);
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        return (List<CfPayable>) query.list();
    }

    @Override
    public List<CfPayable> findPayables(CfCostCenter request, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from CfPayable r  where " +
                "r.requester = :requester " +
                "and r.metadata.state = :state " +
                "order by r.id desc");
        query.setEntity("requester", request);
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfPayable>) query.list();
    }

    @Override
    public List<CfPayableTransaction> findTransactions(CfPayable receivable) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ri from FsPaymentTransaction ri where " +
                "ri.payment = :invoice " +
                "and ri.metadata.state = :metaState " +
                "order by ri.amount desc");   // desc - debit first
        query.setEntity("receivable", receivable);
        query.setInteger("metaState", CfMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<CfPayableTransaction>) query.list();
    }

    @Override
    public List<CfPayableTransaction> findTransactions(CfPayable receivable, Integer offset, Integer limit) {
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
        return (List<CfPayableTransaction>) query.list();
    }

    @Override
    public Integer countPayable(CfCostCenter request) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(r) from CfPayable r where " +
                "r.metadata.state = :state"
        );
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countTransaction(CfPayable receivable) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(rt) from CfPayableTransaction rt where " +
                "rt.receivable = :receivable " +
                "and rt.metadata.state = :state"
        );
        query.setEntity("receivable", receivable);
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    public void addTransaction(CfPayable receivable, CfPayableTransaction transaction, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        Session session = sessionFactory.getCurrentSession();
        transaction.setPayable(receivable);

        // prepape metadata
        CfMetadata metadata = new CfMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreator(user.getId());
        metadata.setState(CfMetaState.ACTIVE);
        transaction.setMetadata(metadata);
        session.merge(transaction);

    }

    public void updateTransaction(CfPayable receivable, CfPayableTransaction transaction, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        Session session = sessionFactory.getCurrentSession();
        transaction.setPayable(receivable);

        // prepape metadata
        CfMetadata metadata = transaction.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifier(user.getId());
        transaction.setMetadata(metadata);
        session.update(transaction);
    }

    public void removeTransaction(CfPayable receivable, CfPayableTransaction transaction, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        Session session = sessionFactory.getCurrentSession();
        transaction.setPayable(receivable);

        // prepape metadata
        CfMetadata metadata = transaction.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifier(user.getId());
        metadata.setState(CfMetaState.INACTIVE);
        transaction.setMetadata(metadata);
        session.update(transaction);

    }

    public void addTransactions(CfPayable receivable, List<CfPayableTransaction> transactions, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        for (CfPayableTransaction transaction : transactions) {
            addTransaction(receivable, transaction, user);
        }
    }

    public void updateTransactions(CfPayable receivable, List<CfPayableTransaction> transactions, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        for (CfPayableTransaction transaction : transactions) {
            updateTransaction(receivable, transaction, user);
        }
    }

    public void removeTransactions(CfPayable receivable, List<CfPayableTransaction> transactions, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        for (CfPayableTransaction transaction : transactions) {
            removeTransaction(receivable, transaction, user);
        }
    }
}
