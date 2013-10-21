package net.canang.cfi.core.dd.dao.impl;

import net.canang.cfi.core.dd.dao.CfDepartmentCodeDao;
import net.canang.cfi.core.dd.model.CfDepartmentCode;
import net.canang.cfi.core.dd.model.impl.CfDepartmentCodeImpl;
import net.canang.cfi.core.so.dao.DaoSupport;
import net.canang.cfi.core.so.model.CfMetaState;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
@SuppressWarnings({"unchecked"})
@Repository("departmentCodeDao")
public class CfDepartmentCodeDaoImpl extends DaoSupport<Long, CfDepartmentCode, CfDepartmentCodeImpl> implements CfDepartmentCodeDao {

    private static final Logger log = Logger.getLogger(CfDepartmentCodeDaoImpl.class);

    // =============================================================================
    // FINDER METHODS
    // =============================================================================

    @Override
    public CfDepartmentCode findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CfDepartmentCode) session.get(CfDepartmentCodeImpl.class, id);
    }

    @Override
    public CfDepartmentCode findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select rc from CfDepartmentCode rc where rc.code = :code");
        query.setString("code", code);
        return (CfDepartmentCode) query.uniqueResult();
    }

    @Override
    public List<CfDepartmentCode> findRoots() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select rc from CfDepartmentCode rc where rc.code like '%0000' order by rc.code");
        query.setCacheable(true);
        return (List<CfDepartmentCode>) query.list();
    }

    @Override
    public List<CfDepartmentCode> find(Integer ofCfet, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select rc from CfDepartmentCode rc where " +
                "rc.metadata.state = :state order by rc.code");
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        query.setFirstResult(ofCfet);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfDepartmentCode>) query.list();
    }

    @Override
    public List<CfDepartmentCode> find(String filter, Integer ofCfet, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select rc from CfDepartmentCode rc where " +
                "(rc.code like upper(:filter) " +
                "or upper(rc.description) like upper(:filter)) " +
                "and rc.metadata.state = :state " +
                "order by rc.code");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        query.setFirstResult(ofCfet);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfDepartmentCode>) query.list();
    }

    @Override
    public List<CfDepartmentCode> find(CfDepartmentCode parent, Integer ofCfet, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select rc from CfDepartmentCode rc where rc.parent = :parent ");
        query.setEntity("parent", parent);
        query.setFirstResult(ofCfet);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfDepartmentCode>) query.list();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(rc) from CfDepartmentCode rc where " +
                "rc.metadata.state = :state");
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(rc) from CfDepartmentCode rc where " +
                "(rc.code like upper(:filter) " +
                "or upper(rc.description) like upper(:filter)) " +
                "and rc.metadata.state = :state " +
                "order by rc.code");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean isExist(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(rc) from CfDepartmentCode rc where rc.code = :code");
        query.setString("code", code);
        return 0 < ((Long) query.uniqueResult()).intValue();
    }


    // =============================================================================
    // CRUD METHODS
    // =============================================================================

}
