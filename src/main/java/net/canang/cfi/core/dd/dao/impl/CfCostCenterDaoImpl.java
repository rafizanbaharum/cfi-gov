package net.canang.cfi.core.dd.dao.impl;

import net.canang.cfi.core.dd.dao.CfCostCenterDao;
import net.canang.cfi.core.dd.model.*;
import net.canang.cfi.core.dd.model.impl.CfCostCenterImpl;
import net.canang.cfi.core.dd.model.impl.CfCostCenterMemberImpl;
import net.canang.cfi.core.so.dao.DaoSupport;
import net.canang.cfi.core.so.model.CfMetaState;
import net.canang.cfi.core.so.model.CfMetadata;
import net.canang.cfi.core.so.model.CfPrincipal;
import net.canang.cfi.core.so.model.CfUser;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import static net.canang.cfi.core.so.model.CfMetaState.ACTIVE;

/**
 * @author : alif haikal razak
 */
@Repository("costCenterDao")
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
    public CfCostCenterMember findMemberById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CfCostCenterMember) session.get(CfCostCenterMemberImpl.class, id);
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
    public List<CfCostCenter> find(Integer ofCfet, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfCostCenter c where " +
                "c.metadata.state = :state " +
                "order by c.code");
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(ofCfet);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public List<CfCostCenter> find(CfFundCode fund, Integer ofCfet, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfCostCenter c where c.fund = :fund");
        query.setEntity("fund", fund);
        query.setFirstResult(ofCfet);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public List<CfCostCenter> find(CfDepartmentCode responsibilityCenter, Integer ofCfet, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfCostCenter c where c.responsibilityCenter = :responsibilityCenter");
        query.setEntity("responsibilityCenter", responsibilityCenter);
        query.setFirstResult(ofCfet);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public List<CfCostCenter> find(CfProjectCode project, Integer ofCfet, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfCostCenter c where c.project = :project");
        query.setEntity("project", project);
        query.setFirstResult(ofCfet);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public List<CfCostCenter> find(CfSubProjectCode subProject, Integer ofCfet, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfCostCenter c where c.subProject = :subProject");
        query.setEntity("subProject", subProject);
        query.setFirstResult(ofCfet);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public List<CfCostCenter> find(CfFundCode fund, CfDepartmentCode responsibilityCenter, Integer ofCfet, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfCostCenter c where c.fund = :fund and c.responsibilityCenter = :responsibilityCenter");
        query.setEntity("fund", fund);
        query.setEntity("responsibilityCenter", responsibilityCenter);
        query.setFirstResult(ofCfet);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public List<CfCostCenter> find(CfFundCode fund, CfDepartmentCode responsibilityCenter, CfProjectCode project, Integer ofCfet, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfCostCenter c where c.fund = :fund and c.responsibilityCenter = :resposibilityCenter and c.project = :project");
        query.setEntity("fund", fund);
        query.setEntity("resposibilityCenter", responsibilityCenter);
        query.setEntity("project", project);
        query.setFirstResult(ofCfet);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public List<CfCostCenter> find(String filter, Integer ofCfet, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select cc from CfCostCenter cc where " +
                "(cc.code like upper(:filter) " +
                "or upper(cc.description) like upper(:filter)) " +
                "and cc.metadata.state = :state " +
                "order by cc.code");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(ofCfet);
        query.setMaxResults(limit);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public List<CfCostCenter> find(String filter, CfFundCode fund, Integer ofCfet, Integer limit) {
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
        query.setFirstResult(ofCfet);
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
    public List<CfCostCenter> find(List<CfPrincipal> principals, Integer ofCfet, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct(cc) from CfCostCenter cc inner join cc.members a " +
                "where a.principal in (:principals) " +
                "order by cc.code");
        query.setParameterList("principals", principals);
        query.setFirstResult(ofCfet);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public List<CfCostCenter> find(List<CfPrincipal> principals, String filter, Integer ofCfet, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct(cc) from CfCostCenter cc " +
                "inner join cc.members a " +
                "where a.principal in (:principals) " +
                "and (cc.code like upper(:filter) " +
                "or upper(cc.description) like upper(:filter)) " +
                "order by cc.code");
        query.setParameterList("principals", principals);
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setFirstResult(ofCfet);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public List<CfCostCenter> find(List<CfPrincipal> principals, String filter, String[] funds, Integer ofCfet, Integer limit) {
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
        query.setFirstResult(ofCfet);
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
    public List<CfCostCenter> find(Set<String> principals, Integer ofCfet, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct(cc) from CfCostCenter cc " +
                "inner join cc.members a " +
                "where a.principal.name in (:principals) " +
                "order by cc.code");
        query.setParameterList("principals", principals);
        query.setFirstResult(ofCfet);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<CfCostCenter>) query.list();
    }

    @Override
    public List<CfCostCenterMember> findMembers(CfCostCenter costCenter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select cca from CfCostCenterMember cca where cca.costCenter = :costCenter");
        query.setEntity("costCenter", costCenter);
        return (List<CfCostCenterMember>) query.list();
    }

    @Override
    public List<CfCostCenterMember> findMembers(CfCostCenter costCenter, Integer ofCfet, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select cca from CfCostCenterMember cca where cca.costCenter = :costCenter");
        query.setEntity("costCenter", costCenter);
        query.setFirstResult(ofCfet);
        query.setMaxResults(limit);
        return (List<CfCostCenterMember>) query.list();
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
    public Integer countMember(CfCostCenter costCenter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(cca) from CfCostCenterMember cca where " +
                "cca.costCenter = :costCenter ");
        query.setEntity("costCenter", costCenter);
        return ((Long) query.uniqueResult()).intValue();
    }
    
    @Override
    public boolean isExist(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select cc from CfCostCenter cc where cc.code = :code");
        query.setString("code", code);
        return query.uniqueResult() != null;
    }
    
    
    public void addMember(CfCostCenter costCenter, CfPrincipal principal, CfUser user) {
        Validate.notNull(principal);
        Session session = sessionFactory.getCurrentSession();
        CfCostCenterMember member = new CfCostCenterMemberImpl();
        member.setCostCenter(costCenter);
        member.setPrincipal(principal);

        // prepare metadata
        CfMetadata metadata = new CfMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreator(user.getId());
        metadata.setState(CfMetaState.ACTIVE);
        member.setMetadata(metadata);
        session.save(member);
    }

    @Override
    public void removeMember(CfCostCenter costCenter, CfPrincipal principal, CfUser user) {
        throw new UnsupportedOperationException();
    }

    public void addMembers(CfCostCenter costCenter, List<CfPrincipal> principals, CfUser user) {
        for (CfPrincipal principal : principals) {
            addMember(costCenter, principal, user);
        }
    }

    @Override
    public void removeMembers(CfCostCenter costCenter, List<CfPrincipal> principals, CfUser user) {
        throw new UnsupportedOperationException();
    }
}
