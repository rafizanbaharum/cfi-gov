package net.canang.cfi.core.bm.dao.impl;

import net.canang.cfi.core.bm.dao.CfControlDao;
import net.canang.cfi.core.bm.model.CfControl;
import net.canang.cfi.core.bm.model.CfControlItem;
import net.canang.cfi.core.bm.model.CfControlType;
import net.canang.cfi.core.bm.model.impl.CfAdditionControlImpl;
import net.canang.cfi.core.bm.model.impl.CfControlImpl;
import net.canang.cfi.core.bm.model.impl.CfControlItemImpl;
import net.canang.cfi.core.bm.model.impl.CfDistributionControlImpl;
import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.so.dao.DaoSupport;
import net.canang.cfi.core.so.model.CfFlowState;
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
 * @since 10/2/13
 */
@SuppressWarnings({"unchecked"})
@Repository("controlDao")
public class CfControlDaoImpl extends DaoSupport<Long, CfControl, CfControlImpl> implements CfControlDao {

    private static final Logger log = Logger.getLogger(CfControlDaoImpl.class);

    @Override
    public CfControl newInstance(CfControlType type) {
        CfControl control = null;
        switch (type) {
            case DISTRIBUTION:
                control = new CfDistributionControlImpl();
                break;
            case ADDITION:
                control = new CfAdditionControlImpl();
                break;
        }
        return control;
    }

    // =============================================================================
    // FINDER METHODS
    // =============================================================================


    @Override
    public CfControlItem findItemById(Long id) {
        return (CfControlItem) sessionFactory.getCurrentSession().get(CfControlItemImpl.class, id);
    }

    @Override
    public CfControlItem findItemByCode(String code, CfControl control) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ci from CfControlItem ci where " +
                "ci.sodo.code = :code and ci.control = :control");
        query.setString("code", code);
        query.setEntity("control", control);
        return (CfControlItem) query.uniqueResult();
    }

    @Override
    public CfControl findByReferenceNo(String referenceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfControl c where " +
                "c.referenceNo = :referenceNo");
        query.setString("referenceNo", referenceNo);
        return (CfControl) query.uniqueResult();
    }

    @Override
    public CfControl findBySourceNo(String sourceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfControl c where " +
                "c.sourceNo = :sourceNo");
        query.setString("sourceNo", sourceNo);
        return (CfControl) query.uniqueResult();
    }

    @Override
    public List<CfControl> findManyBySourceNo(String sourceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfControl c where " +
                "c.sourceNo = :sourceNo");
        query.setString("sourceNo", sourceNo);
        return (List<CfControl>) query.list();
    }

    @Override
    public List<CfControlItem> findItems(CfControl control) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ci from CfControlItem ci where " +
                "ci.control = :control " +
                "and ci.metadata.state = :metaState");
        query.setEntity("control", control);
        query.setInteger("metaState", CfMetaState.ACTIVE.ordinal());
        return query.list();
    }

    @Override
    public List<CfControlItem> findItems(CfControl control, Integer level) {
        // switch query based on level
        Query query = null;
        String innerClause = "";
        String whereClause = "";

        if (level.equals(1))
            whereClause = " ci.parent is null and ";
        else if (level.equals(2)) {
            innerClause = " inner join ci.parent bciParent ";
            whereClause = " bciParent.parent is null and ";
        } else
            innerClause = " inner join ci.parent bciParent inner join bciParent.parent ";

        Session session = sessionFactory.getCurrentSession();
        query = session.createQuery("select ci from CfControlItem ci " + innerClause + " where " + whereClause +
                " ci.control = :control " +
                "and ci.metadata.state = :metaState order by ci.sodo.code asc");
        query.setEntity("control", control);
        query.setInteger("metaState", CfMetaState.ACTIVE.ordinal());
        return query.list();
    }

    @Override
    public List<CfControlItem> findItems(CfControl control, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ci from CfControlItem ci " +
                "where ci.control = :control " +
                "and ci.metadata.state = :metaState");
        query.setEntity("control", control);
        query.setInteger("metaState", CfMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    @Override
    public List<CfControlItem> findItems(CfControl control, Integer level, Integer offset, Integer limit) {
        // switch query based on level
        Query query = null;
        String innerClause = "";
        String whereClause = "";

        if (level.equals(1))
            whereClause = " ci.parent is null and ";
        else if (level.equals(2)) {
            innerClause = " inner join ci.parent bciParent ";
            whereClause = " bciParent.parent is null and ";
        } else
            innerClause = " inner join ci.parent bciParent inner join bciParent.parent ";

        Session session = sessionFactory.getCurrentSession();
        query = session.createQuery("select ci from CfControlItem ci " + innerClause + " where " + whereClause +
                " ci.control = :control and ci.metadata.state = :metaState");
        query.setEntity("control", control);
        query.setInteger("metaState", CfMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }


    @Override
    public List<CfControlItem> findItemChildren(CfControlItem parent) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ci from CfControlItem ci where ci.parent = :parent order by ci.sodo.code asc");
        query.setEntity("parent", parent);
        return query.list();
    }


    @Override
    public List<CfControlItem> findItemChildren(CfControlItem parent, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ci from CfControlItem ci where ci.parent = :parent order by ci.sodo.code asc");
        query.setEntity("parent", parent);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(c) from CfControl c ");
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(CfPeriod source, CfPeriod sink, CfControlType type) {
        Session session = sessionFactory.getCurrentSession();
        Query query = null;
        if (CfControlType.DISTRIBUTION.equals(type)) {
            query = session.createQuery("select count(c) from CfControl c where c.source = :source");
            query.setEntity("source", source);
        } else if (CfControlType.ADDITION.equals(type)) {
            query = session.createQuery("select count(c) from CfControl c where c.source = :source");
            query.setEntity("source", source);
        } else {
            throw new IllegalArgumentException("budget control type not recognized");
        }
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(CfPeriod source, CfPeriod sink, CfControlType type, String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = null;
        if (CfControlType.DISTRIBUTION.equals(type)) {
            query = session.createQuery("select count(c) from CfControl c where c.source = :source");
            query.setEntity("source", source);
        } else if (CfControlType.ADDITION.equals(type)) {
            query = session.createQuery("select count(c) from CfControl c where c.source = :source");
            query.setEntity("source", source);
        } else if (null == type) { // all
            query = session.createQuery("select c from CfControl c where c.source = :source");
            query.setEntity("source", source);
        } else {
            throw new IllegalArgumentException("budget control type not recognized");
        }
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countItem(CfControl control) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(ci) from CfControlItem ci where ci.control = :control");
        query.setEntity("control", control);
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countChildren(CfControlItem controlItem) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(ci) from CfControlItem ci where ci.parent = :parent");
        query.setEntity("parent", controlItem);
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean isExists(CfPeriod budget, CfControlType type) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(c) from CfControl c where " +
                "c.source = :source " +
                "and c.controlType = :type " +
                "and c.flowdata.state != :removedState " +
                "and c.flowdata.state != :cancelledState");
        query.setEntity("source", budget);
        query.setInteger("type", type.ordinal());
        query.setInteger("removedState", CfFlowState.REMOVED.ordinal());
        query.setInteger("cancelledState", CfFlowState.CANCELLED.ordinal());
        return 0 < ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean isExists(CfControl control, CfControlType type) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(c) from CfControl c where " +
                "c.source = :source " +
                "and c != :control " +
                "and c.controlType = :type " +
                "and c.flowdata.state != :removedState " +
                "and c.flowdata.state != :cancelledState");
        query.setEntity("source", control.getSource());
        query.setEntity("control", control);
        query.setInteger("type", type.ordinal());
        query.setInteger("removedState", CfFlowState.REMOVED.ordinal());
        query.setInteger("cancelledState", CfFlowState.CANCELLED.ordinal());
        return 0 < ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean isExists(CfControl control, CfControlType type, CfMetaState metaState) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(c) from CfControl c where " +
                "c.source = :source " +
                "and c != :control " +
                "and c.controlType = :type " +
                "and c.metadata.state = :metaState " +
                "and c.flowdata.state != :removedState " +
                "and c.flowdata.state != :cancelledState");
        query.setEntity("source", control.getSource());
        query.setEntity("control", control);
        query.setInteger("type", type.ordinal());
        query.setInteger("metaState", metaState.ordinal());
        query.setInteger("removedState", CfFlowState.REMOVED.ordinal());
        query.setInteger("cancelledState", CfFlowState.CANCELLED.ordinal());
        return 0 < ((Long) query.uniqueResult()).intValue();
    }

    // =============================================================================
    // CRUD METHODS
    // =============================================================================

    @Override
    public void addItem(CfPeriod budget, CfControl control, CfControlItem item, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        Session session = sessionFactory.getCurrentSession();
        item.setControl(control);

        // prepare metadata
        CfMetadata metadata = new CfMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreator(user.getId());
        metadata.setState(CfMetaState.ACTIVE);
        item.setMetadata(metadata);

        session.save(item);

    }

    @Override
    public void addItems(CfPeriod budget, CfControl control, List<CfControlItem> items, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        Session session = sessionFactory.getCurrentSession();
        for (CfControlItem item : items) {
            item.setControl(control);

            // prepare metadata
            CfMetadata metadata = new CfMetadata();
            metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            metadata.setCreator(user.getId());
            metadata.setState(CfMetaState.ACTIVE);
            item.setMetadata(metadata);

            session.save(item);
        }

    }

    @Override
    public void updateItem(CfControl control, CfControlItem item, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        Session session = sessionFactory.getCurrentSession();
        item.setControl(control);

        // prepare metadata
        CfMetadata metadata = item.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifier(user.getId());
        item.setMetadata(metadata);

        // update
        session.update(item);
    }

    @Override
    public void updateItems(CfControl control, List<CfControlItem> items, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        Session session = sessionFactory.getCurrentSession();
        for (CfControlItem item : items) {
            item.setControl(control);

            // prepare metadata
            CfMetadata metadata = item.getMetadata();
            metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
            metadata.setModifier(user.getId());
            item.setMetadata(metadata);

            // update
            session.update(item);
        }
    }

    @Override
    public void removeItem(CfControl control, CfControlItem item, CfUser user) {
        if (null == user)
            throw new IllegalArgumentException("User cannot be null");

        // session
        Session session = sessionFactory.getCurrentSession();
        CfMetadata metadata = item.getMetadata();
        metadata.setState(CfMetaState.INACTIVE);
        metadata.setDeletedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setDeleter(user.getId());
        item.setMetadata(metadata);

        // update
        session.update(item);
    }

    @Override
    public void removeItems(CfControl control, List<CfControlItem> items, CfUser user) {
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();

        for (CfControlItem item : items) {

            // prepare metadata
            CfMetadata metadata = item.getMetadata();
            metadata.setState(CfMetaState.INACTIVE);
            metadata.setDeletedDate(new Timestamp(System.currentTimeMillis()));
            metadata.setDeleter(user.getId());
            item.setMetadata(metadata);

            // update
            session.update(item);
        }
    }
}
