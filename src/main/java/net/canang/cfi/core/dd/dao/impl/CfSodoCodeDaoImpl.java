package net.canang.cfi.core.dd.dao.impl;

import net.canang.cfi.core.dd.dao.CfSodoCodeDao;
import net.canang.cfi.core.dd.model.CfAccountCode;
import net.canang.cfi.core.dd.model.CfSodoCode;
import net.canang.cfi.core.dd.model.impl.CfSodoCodeImpl;
import net.canang.cfi.core.so.dao.DaoSupport;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

import static net.canang.cfi.core.so.model.CfMetaState.ACTIVE;

/**
 * @author : alif haikal razak
 */
public class CfSodoCodeDaoImpl extends DaoSupport<Long, CfSodoCode, CfSodoCodeImpl> implements CfSodoCodeDao {

    // =============================================================================
    // FINDER METHODS
    // =============================================================================

    @Override
    public CfSodoCode findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CfSodoCode) session.get(CfSodoCodeImpl.class, id);
    }

    @Override
    public CfSodoCode findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from CfSodoCode s where s.code = :code");
        query.setString("code", code);
        query.setCacheable(true);
        return (CfSodoCode) query.uniqueResult();
    }

    @Override
    public List<CfSodoCode> findByParent(CfSodoCode parent) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from CfSodoCode s where " +
                "s.parent = :parent " +
                "and s.metadata.state = :metaState");
        query.setEntity("parent", parent);
        query.setInteger("metaState", ACTIVE.ordinal());
        return (List<CfSodoCode>) query.list();
    }

    @Override
    public List<CfSodoCode> findRoots() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from CfSodoCode s where " +
                "s.parent is null " +
                "and s.metadata.state = :metaState");
        query.setInteger("metaState", ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<CfSodoCode>) query.list();
    }

    @Override
    public List<CfSodoCode> findRoots(CfAccountCode accountCode) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from CfSodoCode s where " +
                "s.parent is null " +
                "and s.account  = :accountCode " +
                "and s.metadata.state = :metaState");
        query.setInteger("metaState", ACTIVE.ordinal());
        query.setEntity("accountCode", accountCode);
        query.setCacheable(true);
        return (List<CfSodoCode>) query.list();
    }

    @Override
    public List<CfSodoCode> find(CfSodoCode parent, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from CfSodoCode s where " +
                "s.parent = :parent " +
                "and s.metadata.state = :metaState");
        query.setEntity("parent", parent);
        query.setInteger("metaState", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfSodoCode>) query.list();
    }

    @Override
    public List<CfSodoCode> find(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from CfSodoCode s where " +
                "s.metadata.state = :metaState");
        query.setInteger("metaState", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfSodoCode>) query.list();
    }


    @Override
    public List<CfSodoCode> findLevel23() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from CfSodoCode s where " +
                "s.parent is not null " +
                "and s.metadata.state = :metaState");
        query.setInteger("metaState", ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<CfSodoCode>) query.list();
    }


    public List<CfSodoCode> findLevel12() {
        Session session = sessionFactory.getCurrentSession();
        String query1 = "select s from CfSodoCode s where s.parent is null and s.metadata.state = :metaState ";
        String query2 = "select s from CfSodoCode s inner join s.parent p where s.parent is not null and p.parent is null and s.metadata.state = :metaState ";
        String query3 = "select s from CfSodoCode s where ";
        Query query = session.createQuery(query3 + " s in (" + query1 + ") or s in (" + query2 + ")");
        query.setInteger("metaState", ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<CfSodoCode>) query.list();
    }


    @Override
    public List<CfSodoCode> find(Integer level, Integer offset, Integer limit) {

        // switch query based on level
        Query query = null;
        String innerClause = "";
        String whereClause = "";
        if (level.equals(1))
            whereClause = " s.parent is null and ";
        else if (level.equals(2)) {
            innerClause = " inner join s.parent t ";
            whereClause = " t.parent is null and ";
        } else
            innerClause = " inner join s.parent t inner join t.parent ";

        Session session = sessionFactory.getCurrentSession();
        query = session.createQuery("select s from CfSodoCode s " + innerClause + " where " + whereClause +
                "s.metadata.state = :metaState");
        query.setInteger("metaState", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfSodoCode>) query.list();
    }

    @Override
    public List<CfSodoCode> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from CfSodoCode s where " +
                "(s.code like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :metaState  " +
                "order by s.code");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("metaState", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfSodoCode>) query.list();
    }

    /**
     * level 1, 2, 3, -1, -2, -3
     * 1: 1 only
     * 2: 2 only
     * 3: 3 only
     * -1: 1 & 2
     * -2: 2 & 3
     *
     * @param level
     * @param filter
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public List<CfSodoCode> find(Integer level, String filter, Integer offset, Integer limit) {

        Query query = null;
        String innerClause = "";
        String whereClause = "";

        switch (level) {
            case -1:
                innerClause = " inner join s.parent t ";
                whereClause = " s.parent is null and t.parent is null ";
                break;
            case -2:
                whereClause = " s.parent is not null and ";
                break;
            case 1:
                whereClause = " s.parent is null and ";
                break;
            case 2:
                innerClause = " inner join s.parent t ";
                whereClause = " t.parent is null and ";
                break;
            case 3:
                innerClause = " inner join s.parent t inner join t.parent ";
                break;
        }

        // query
        Session session = sessionFactory.getCurrentSession();
        query = session.createQuery("select s from CfSodoCode s " + innerClause + "where " + whereClause +
                "(s.code like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :metaState  " +
                "order by s.code");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("metaState", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfSodoCode>) query.list();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from CfSodoCode s where " +
                "s.metadata.state = :metaState");
        query.setInteger("metaState", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(CfSodoCode parent) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from CfSodoCode s where " +
                "s.parent = (:parent) " +
                "and s.metadata.state = :metaState");
        query.setEntity("parent", parent);
        query.setInteger("metaState", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from CfSodoCode s where " +
                "(s.code like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :metaState " +
                "order by s.code");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("metaState", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(Integer level, String filter) {

        Query query = null;
        String innerClause = "";
        String whereClause = "";

        // switch
        if (level.equals(1))
            whereClause = " s.parent is null and ";
        else if (level.equals(2)) {
            innerClause = " inner join s.parent t ";
            whereClause = " t.parent is null and ";
        } else
            innerClause = " inner join s.parent t inner join t.parent ";

        Session session = sessionFactory.getCurrentSession();
        query = session.createQuery("select count(s) from CfSodoCode s " + innerClause + "where " + whereClause +
                "(s.code like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :metaState  " +
                "order by s.code");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("metaState", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }


    @Override
    public boolean hasChildren(CfSodoCode parent) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from CfSodoCode s where " +
                "s.parent = :parent");
        query.setEntity("parent", parent);
        return ((Long) query.uniqueResult()).intValue() > 0;
    }
}
