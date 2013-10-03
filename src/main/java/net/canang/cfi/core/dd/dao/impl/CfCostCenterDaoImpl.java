package net.canang.cfi.core.dd.dao.impl;

import net.canang.cfi.core.dd.dao.CfCostCenterDao;
import net.canang.cfi.core.dd.model.*;
import net.canang.cfi.core.dd.model.impl.CfCostCenterImpl;
import net.canang.cfi.core.so.dao.DaoSupport;
import net.canang.cfi.core.so.model.CfPrincipal;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;
import java.util.Set;

import static net.canang.cfi.core.so.model.CfMetaState.ACTIVE;

/**
 * @author : alif haikal razak
 */
public class CfCostCenterDaoImpl extends DaoSupport<Long, CfCostCenter, CfCostCenterImpl> implements CfCostCenterDao {


    private static final Logger log = Logger.getLogger(CfCostCenterDaoImpl.class);

    // =============================================================================
    // FINDER METHODS
    // =============================================================================

    @Override
    public CfCostCenter findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CfCostCenter) session.get(CfCostCenterImpl.class, id);
    }

    @Override
    public CfCostCenter findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from CfCostCenter s where s.code = :code");
        query.setString("code", code);
        query.setCacheable(true);
        return (CfCostCenter) query.uniqueResult();
    }

    @Override
    public List<CfCostCenter> find(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfCostCenter c where " +
                "c.metadata.state = :state " +
                "order by c.code");
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public List<CfCostCenter> find(CfFundCode fund, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfCostCenter c where c.fund = :fund");
        query.setEntity("fund", fund);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public List<CfCostCenter> find(CfDepartmentCode responsibilityCenter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfCostCenter c where c.responsibilityCenter = :responsibilityCenter");
        query.setEntity("responsibilityCenter", responsibilityCenter);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public List<CfCostCenter> find(CfProjectCode project, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfCostCenter c where c.project = :project");
        query.setEntity("project", project);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public List<CfCostCenter> find(CfSubProjectCode subProject, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfCostCenter c where c.subProject = :subProject");
        query.setEntity("subProject", subProject);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public List<CfCostCenter> find(CfFundCode fund, CfDepartmentCode responsibilityCenter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfCostCenter c where c.fund = :fund and c.responsibilityCenter = :responsibilityCenter");
        query.setEntity("fund", fund);
        query.setEntity("responsibilityCenter", responsibilityCenter);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public List<CfCostCenter> find(CfFundCode fund, CfDepartmentCode responsibilityCenter, CfProjectCode project, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfCostCenter c where c.fund = :fund and c.responsibilityCenter = :resposibilityCenter and c.project = :project");
        query.setEntity("fund", fund);
        query.setEntity("resposibilityCenter", responsibilityCenter);
        query.setEntity("project", project);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public List<CfCostCenter> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select cc from CfCostCenter cc where " +
                "(cc.code like upper(:filter) " +
                "or upper(cc.description) like upper(:filter)) " +
                "and cc.metadata.state = :state " +
                "order by cc.code");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public List<CfCostCenter> find(String filter, CfFundCode fund, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select cc from CfCostCenter cc where " +
                "(cc.code like upper(:filter) " +
                "or upper(cc.description) like upper(:filter)) " +
                "and cc.fund = :fund " +
                "and cc.metadata.state = :state " +
                "order by cc.code");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setEntity("fund", fund);
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public List<CfCostCenter> find(List<CfPrincipal> principals) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct(cc) from CfCostCenter cc " +
                "inner join cc.members a " +
                "where a.principal in (:principals) " +
                "order by cc.code");
        query.setParameterList("principals", principals);
        query.setCacheable(true);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public List<CfCostCenter> find(List<CfPrincipal> principals, String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct(cc) from CfCostCenter cc " +
                "inner join cc.members a " +
                "where a.principal in (:principals) " +
                "and (cc.code like upper(:filter) " +
                "or upper(cc.description) like upper(:filter)) " +
                "order by cc.code");
        query.setParameterList("principals", principals);
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setCacheable(true);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public List<CfCostCenter> find(List<CfPrincipal> principals, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct(cc) from CfCostCenter cc inner join cc.members a " +
                "where a.principal in (:principals) " +
                "order by cc.code");
        query.setParameterList("principals", principals);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public List<CfCostCenter> find(List<CfPrincipal> principals, String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct(cc) from CfCostCenter cc " +
                "inner join cc.members a " +
                "where a.principal in (:principals) " +
                "and (cc.code like upper(:filter) " +
                "or upper(cc.description) like upper(:filter)) " +
                "order by cc.code");
        query.setParameterList("principals", principals);
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public List<CfCostCenter> find(List<CfPrincipal> principals, String filter, String[] funds, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct(cc) from CfCostCenter cc " +
                "inner join cc.members a " +
                "where a.principal in (:principals) " +
                "and cc.fund.code in (:funds) " +
                "and (cc.code like upper(:filter) " +
                "or upper(cc.description) like upper(:filter)) " +
                "order by cc.code");
        query.setParameterList("principals", principals);
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setParameterList("funds", funds);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public List<CfCostCenter> find(Set<String> principals) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct(cc) from CfCostCenter cc " +
                "inner join cc.members a " +
                "where a.principal.name in (:principals) " +
                "order by cc.code");
        query.setParameterList("principals", principals);
        query.setCacheable(true);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public List<CfCostCenter> find(Set<String> principals, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct(cc) from CfCostCenter cc " +
                "inner join cc.members a " +
                "where a.principal.name in (:principals) " +
                "order by cc.code");
        query.setParameterList("principals", principals);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(cc) from CfCostCenter cc where " +
                "cc.metadata.state = :state");
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(cc) from CfCostCenter cc where " +
                "(cc.code like upper(:filter) " +
                "or upper(cc.description) like upper(:filter)) " +
                "and cc.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter, CfFundCode fund) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(cc) from CfCostCenter cc where " +
                "(cc.code like upper(:filter) " +
                "or upper(cc.description) like upper(:filter))" +
                "and cc.fund = :fund " +
                "and cc.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setEntity("fund", fund);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(List<CfPrincipal> principals) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(cc) from CfCostCenter cc inner join cc.members a" +
                " where a.principal in (:principals)");
        query.setParameterList("principals", principals);
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(Set<String> principals) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(cc) from CfCostCenter cc inner join cc.members a" +
                " where a.principal.name in (:principals)");
        query.setParameterList("principals", principals);
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean isExist(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select cc from CfCostCenter cc where cc.code = :code");
        query.setString("code", code);
        return query.uniqueResult() != null;
    }
}
