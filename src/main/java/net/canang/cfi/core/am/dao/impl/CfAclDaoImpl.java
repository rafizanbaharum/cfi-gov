package net.canang.cfi.core.am.dao.impl;

import net.canang.cfi.core.am.dao.CfAclDao;
import net.canang.cfi.core.am.model.CfAclObjectIdentity;
import net.canang.cfi.core.so.model.CfFlowState;
import org.apache.log4j.Logger;
import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * ACL Dao implementation
 */
@SuppressWarnings({"unchecked"})
@Repository("aclDao")
public class CfAclDaoImpl implements CfAclDao {

    private static final Logger log = Logger.getLogger(CfAclDaoImpl.class);

    @Autowired(required = true)
    protected SessionFactory sessionFactory;

    @Override
    public List<CfAclObjectIdentity> find(String filter, Date startDate, Date endDate, Class clazz, Set<String> principal, int mask, Integer offset, Integer limit) {

        // switch on filter
        Session session = sessionFactory.getCurrentSession();
        session.clear();

        log.debug("startDate = " + startDate);
        log.debug("endDate = " + endDate);

        // set default filter
        if (switchFilter(filter, null, startDate, endDate, clazz, session))
            return new ArrayList<CfAclObjectIdentity>();

        // query
        Query query = session.createQuery("select distinct aoi from AclObjectIdentity aoi join aoi.entries e where " +
                "aoi.objectClass.aclClass = :clazz " +
                "and e.sid.sid in (:name) ");

        // DISTINCT + ORDER BY ISSUE
        // http://stackoverflow.com/questions/968438/hql-order-by-query-giving-problem
        // "order by e.aclOrder desc");
        query.setString("clazz", clazz.getCanonicalName());
        query.setParameterList("name", principal);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfAclObjectIdentity>) query.list();
    }

    @Override
    public List<CfAclObjectIdentity> find(String filter, CfFlowState flowType, Date startDate, Date endDate, Class clazz, Set<String> principal, int mask, Integer offset, Integer limit) {

        // switch on filter
        Session session = sessionFactory.getCurrentSession();
        session.clear();

        log.debug("startDate = " + startDate);
        log.debug("endDate = " + endDate);

        // set default filter
        if (switchFilter(filter, flowType, startDate, endDate, clazz, session))
            return new ArrayList<CfAclObjectIdentity>();

        // query
        Query query = session.createQuery("select distinct aoi from AclObjectIdentity aoi join aoi.entries e where " +
                "aoi.objectClass.aclClass = :clazz " +
                "and e.sid.sid in (:name) ");

        // DISTINCT + ORDER BY ISSUE
        // http://stackoverflow.com/questions/968438/hql-order-by-query-giving-problem
        // "order by e.aclOrder desc");
        query.setString("clazz", clazz.getCanonicalName());
        query.setParameterList("name", principal);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfAclObjectIdentity>) query.list();
    }

    @Override
    public Integer count(String filter, CfFlowState flowType, Date startDate, Date endDate, Class clazz, Set<String> principal, int mask) {
        Session session = sessionFactory.getCurrentSession();
        session.clear();

        // switch filter
        if (switchFilter(filter, flowType, startDate, endDate, clazz, session))
            return 0;

        // query
        Query query = session.createQuery("select count(distinct aoi.id) from AclObjectIdentity aoi join aoi.entries e where " +
                "aoi.objectClass.aclClass = :clazz " +
                "and e.sid.sid in (:name) " +
                "order by e.aclOrder desc");
        query.setString("clazz", clazz.getCanonicalName());
        query.setParameterList("name", principal);
        query.setCacheable(true);
        return ((Long) query.uniqueResult()).intValue();
    }

    private boolean switchFilter(String filter, CfFlowState flowType, Date startDate, Date endDate, Class clazz, Session session) {
        log.debug("filter:" + filter);
        log.debug("clazz:" + clazz);

        Filter hqlFilter;
        try {
            if (filter == null) filter = "";
            if (null == flowType) {
                hqlFilter = session.enableFilter("filter3_" + clazz.getSimpleName());
                hqlFilter.setParameter("filter", "%" + filter + "%");
                hqlFilter.setParameter("startDate", startDate);
                hqlFilter.setParameter("endDate", endDate);
            } else {
                hqlFilter = session.enableFilter("filter4_" + clazz.getSimpleName());
                hqlFilter.setParameter("filter", "%" + filter + "%");
                hqlFilter.setParameter("flowState", flowType.ordinal());
                hqlFilter.setParameter("startDate", startDate);
                hqlFilter.setParameter("endDate", endDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }
}
