package net.canang.cfi.core.ar.dao.impl;

import net.canang.cfi.core.ar.dao.CfReceiptDao;
import net.canang.cfi.core.ar.dao.CfReceivableDao;
import net.canang.cfi.core.ar.model.CfReceipt;
import net.canang.cfi.core.ar.model.CfReceiptItem;
import net.canang.cfi.core.ar.model.CfReceiptPayment;
import net.canang.cfi.core.ar.model.CfReceiptType;
import net.canang.cfi.core.ar.model.impl.CfCounterReceiptImpl;
import net.canang.cfi.core.ar.model.impl.CfReceiptImpl;
import net.canang.cfi.core.ar.model.impl.CfReceiptItemImpl;
import net.canang.cfi.core.ar.model.impl.CfReceiptPaymentImpl;
import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.so.dao.DaoSupport;
import net.canang.cfi.core.so.model.CfMetaState;
import net.canang.cfi.core.so.model.CfMetadata;
import net.canang.cfi.core.so.model.CfUser;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
@Repository("receiptDao")
public class CfReceiptDaoImpl extends DaoSupport<Long, CfReceipt, CfReceiptImpl> implements CfReceiptDao {

    private static final Logger log = Logger.getLogger(CfReceiptDaoImpl.class);

    @Autowired
    private CfReceivableDao receivableDao;

    // =============================================================================
    // HELPER METHODS
    // =============================================================================

    public CfReceipt newInstance(CfReceiptType type) {
        CfReceipt receipt = null;
        switch (type) {
            case COUNTER:
                receipt = new CfCounterReceiptImpl();
                break;
            case EPAYMENT:
                break;
        }
        return receipt;
    }

    public CfReceiptItem newItemInstance() {
        return new CfReceiptItemImpl();
    }

    // =============================================================================
    // FINDER METHODS
    // =============================================================================


    @Override
    public CfReceipt findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CfReceipt) session.get(CfReceiptImpl.class, id);
    }

    @Override
    public CfReceiptItem findItemById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CfReceiptItem) session.get(CfReceiptItemImpl.class, id);
    }

    @Override
    public CfReceiptPayment findPaymentById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CfReceiptPayment) session.get(CfReceiptPaymentImpl.class, id);
    }

    @Override
    public CfReceipt findByReferenceNo(String referenceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from CfReceipt r where r.referenceNo = :referenceNo");
        query.setString("referenceNo", referenceNo);
        query.setCacheable(true);
        return (CfReceipt) query.uniqueResult();
    }

    @Override
    public CfReceipt findBySourceNo(String sourceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from CfReceipt r where r.sourceNo = :sourceNo");
        query.setString("sourceNo", sourceNo);
        return (CfReceipt) query.uniqueResult();
    }

    @Override
    public List<CfReceipt> findManyBySourceNo(String sourceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from CfReceipt r where r.sourceNo = :sourceNo");
        query.setString("sourceNo", sourceNo);
        return (List<CfReceipt>) query.list();
    }

    @Override
    public CfReceipt findByReceiptNo(String receiptNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from CfReceipt r where r.receiptNo = :receiptNo");
        query.setString("receiptNo", receiptNo);
        return (CfReceipt) query.uniqueResult();
    }

    @Override
    public List<CfReceipt> find(CfReceiptType type, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from CfReceipt r where " +
                "r.receiptType = :type " +
                "and r.metadata.state = :state " +
                "order by r.id desc");
        query.setInteger("type", type.ordinal());
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfReceipt>) query.list();
    }


    @Override
    public List<CfReceipt> find(CfReceiptType type, CfCostCenter costCenter) {
        // session
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from CfReceipt r where " +
                "r.receiptType = :type " +
                "and r.requester = :costCenter " +
                "order by r.id desc");
        query.setInteger("type", type.ordinal());
        query.setEntity("costCenter", costCenter);
        query.setCacheable(true);
        return (List<CfReceipt>) query.list();
    }

    @Override
    public List<CfReceipt> find(CfReceiptType type, CfCostCenter costCenter, Integer offset, Integer limit) {
        // session
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from CfReceipt r where " +
                "r.receiptType = :type " +
                "and r.requester = :costCenter " +
                "order by r.id desc");
        query.setInteger("type", type.ordinal());
        query.setEntity("costCenter", costCenter);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfReceipt>) query.list();
    }

    @Override
    public List<CfReceipt> find(CfReceiptType type, String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from CfReceipt r where " +
                "(upper(r.referenceNo) like upper(:filter) " +
                "or upper(r.sourceNo) like upper(:filter)) " +
                "and r.receiptType = :type " +
                "and r.metadata.state = :metaState " +
                "order by r.id desc");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("type", type.ordinal());
        query.setInteger("metaState", CfMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfReceipt>) query.list();
    }


    @Override
    public List<CfReceiptItem> findItems(CfReceiptPayment payment) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ri from CfReceiptItem ri where " +
                "ri.payment = :payment " +
                "and ri.metadata.state = :metaState " +
                "order by ri.amount desc");
        query.setEntity("payment", payment);
        query.setInteger("metaState", CfMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<CfReceiptItem>) query.list();
    }

    @Override
    public List<CfReceiptItem> findItems(CfReceiptPayment payment, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ri from CfReceiptItem ri where " +
                "ri.payment = :payment " +
                "and ri.metadata.state = :metaState " +
                "order by ri.id desc");
        query.setEntity("payment", payment);
        query.setInteger("metaState", CfMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfReceiptItem>) query.list();
    }

    @Override
    public List<CfReceiptPayment> findPayments(CfReceipt receipt) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ri from CfReceiptPayment ri where " +
                "ri.receipt = :receipt " +
                "and ri.metadata.state = :metaState " +
                "order by ri.amount desc");
        query.setEntity("receipt", receipt);
        query.setInteger("metaState", CfMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<CfReceiptPayment>) query.list();
    }

    @Override
    public List<CfReceiptPayment> findPayments(CfReceipt receipt, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ri from CfReceiptPayment ri where " +
                "ri.receipt = :receipt " +
                "and ri.metadata.state = :metaState " +
                "order by ri.id desc");
        query.setEntity("receipt", receipt);
        query.setInteger("metaState", CfMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfReceiptPayment>) query.list();
    }

    @Override
    public Integer count(CfReceiptType type) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(r) from CfReceipt r where " +
                "r.receiptType = :type " +
                "and r.metadata.state = :state");
        query.setInteger("type", type.ordinal());
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(CfReceiptType type, CfCostCenter costCenter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(r) from CfReceipt r where " +
                "r.receiptType = :type " +
                "and r.requester = :costCenter ");
        query.setInteger("type", type.ordinal());
        query.setEntity("costCenter", costCenter);
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(CfReceiptType type, String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(r) from CfReceipt r where " +
                "(upper(r.referenceNo) like upper(:filter) " +
                "or upper(r.sourceNo) like upper(:filter))" +
                "and r.receiptType = :type " +
                "and r.metadata.state = :metaState");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("type", type.ordinal());
        query.setInteger("metaState", CfMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countPayment(CfReceipt receipt) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(ri) from CfReceiptPayment ri where " +
                "ri.receipt = :receipt");
        query.setEntity("receipt", receipt);
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countItem(CfReceiptPayment payment) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(ri) from CfReceiptItem ri where " +
                "ri.payment = :payment");
        query.setEntity("payment", payment);
        return ((Long) query.uniqueResult()).intValue();
    }

    public void addPayment(CfReceipt receipt, CfReceiptPayment payment, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        Session session = sessionFactory.getCurrentSession();
        payment.setReceipt(receipt);

        // prepare metadata
        CfMetadata metadata = new CfMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreator(user.getId());
        metadata.setState(CfMetaState.ACTIVE);
        payment.setMetadata(metadata);
        session.save(payment);

    }

    public void updatePayment(CfReceipt receipt, CfReceiptPayment payment, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        Session session = sessionFactory.getCurrentSession();
        payment.setReceipt(receipt);

        // prepare metadata
        CfMetadata metadata = payment.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifier(user.getId());
        payment.setMetadata(metadata);
        session.update(payment);

    }

    public void removePayment(CfReceipt receipt, CfReceiptPayment payment, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        Session session = sessionFactory.getCurrentSession();
        payment.setReceipt(receipt);

        // prepare metadata
        CfMetadata metadata = payment.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifier(user.getId());
        metadata.setState(CfMetaState.INACTIVE);
        payment.setMetadata(metadata);
        session.update(payment);

    }

    public void addPayments(CfReceipt receipt, List<CfReceiptPayment> payments, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        for (CfReceiptPayment payment : payments) {
            addPayment(receipt, payment, user);
        }
    }

    public void updatePayments(CfReceipt receipt, List<CfReceiptPayment> payments, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        for (CfReceiptPayment payment : payments) {
            updatePayment(receipt, payment, user);
        }
    }

    public void removePayments(CfReceipt receipt, List<CfReceiptPayment> payments, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        for (CfReceiptPayment payment : payments) {
            removePayment(receipt, payment, user);
        }
    }

    @Override
    public void addItem(CfReceiptPayment payment, CfReceiptItem item, CfUser user) {
        Session session = sessionFactory.getCurrentSession();
        item.setPayment(payment);

        // prepare metadata
        CfMetadata metadata = new CfMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreator(user.getId());
        metadata.setState(CfMetaState.ACTIVE);
        item.setMetadata(metadata);
        session.save(item);

    }

    public void updateItem(CfReceiptPayment payment, CfReceiptItem item, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        Session session = sessionFactory.getCurrentSession();
        item.setPayment(payment);

        // prepare metadata
        CfMetadata metadata = item.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifier(user.getId());
        item.setMetadata(metadata);
        session.update(item);

    }

    public void removeItem(CfReceiptPayment payment, CfReceiptItem item, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        Session session = sessionFactory.getCurrentSession();
        item.setPayment(payment);

        // prepare metadata
        CfMetadata metadata = item.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifier(user.getId());
        metadata.setState(CfMetaState.INACTIVE);
        item.setMetadata(metadata);
        session.update(item);

    }

    public void addItems(CfReceiptPayment payment, List<CfReceiptItem> items, CfUser user) {
        for (CfReceiptItem item : items) {
            addItem(payment, item, user);
        }
    }

    public void updateItems(CfReceiptPayment payment, List<CfReceiptItem> items, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        for (CfReceiptItem item : items) {
            updateItem(payment, item, user);
        }
    }

    public void removeItems(CfReceiptPayment payment, List<CfReceiptItem> items, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        for (CfReceiptItem item : items) {
            removeItem(payment, item, user);
        }
    }
}
