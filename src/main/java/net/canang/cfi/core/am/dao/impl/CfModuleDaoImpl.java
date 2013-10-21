package net.canang.cfi.core.am.dao.impl;

import net.canang.cfi.core.am.dao.CfModuleDao;
import net.canang.cfi.core.am.model.CfModule;
import net.canang.cfi.core.am.model.CfSubModule;
import net.canang.cfi.core.am.model.impl.CfModuleImpl;
import net.canang.cfi.core.am.model.impl.CfSubModuleImpl;
import net.canang.cfi.core.so.dao.DaoSupport;
import net.canang.cfi.core.so.model.*;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
@SuppressWarnings({"unchecked"})
@Repository("moduleDao")
public class CfModuleDaoImpl extends DaoSupport<Long, CfModule, CfModuleImpl> implements CfModuleDao {

    private static final Logger log = Logger.getLogger(CfModuleDaoImpl.class);

    // =============================================================================
    // FINDER METHODS
    // =============================================================================

    @Override
    public CfModule findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CfModule) session.get(CfModuleImpl.class, id);
    }

    @Override
    public CfModule findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select m from CfModule m where m.code = :code");
        query.setString("code", code);
        return (CfModule) query.uniqueResult();
    }

    @Override
    public CfSubModule findSubModuleById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CfSubModule) session.get(CfSubModuleImpl.class, id);
    }

    @Override
    public List<CfModule> findAuthorized(List<String> sid) {
        Session session = sessionFactory.getCurrentSession();
        List<CfModule> result = null;

        int j = 0;
        int k = 0;
        final int MAX_SIZE = 999;
        for (; ; ) {

            if (j > sid.size()) break;
            Query query = session.createQuery("select m from CfModule m where m.id in ( " +
                    " select oid.objectIdIdentity from AclObjectIdentity oid join oid.entries e" +
                    " where oid.objectClass.aclClass = :class and e.mask = :mask" +
                    " and e.sid.sid in (:sid)" +
                    ")");

            k = j + MAX_SIZE;
            if (k >= sid.size()) k = sid.size();
            query.setString("class", CfModule.class.getSimpleName());
            query.setInteger("mask", 1);
            query.setParameterList("sid", sid.subList(j, k));

            j = j + MAX_SIZE + 1;

            if (null == result) result = (List<CfModule>) query.list();
            else
                result.addAll((List<CfModule>) query.list());
        }

        return result;
    }

    @Override
    public Map<Integer, Set<CfSubModule>> findAuthorizedSubModule(List<String> sid) {
        Session session = sessionFactory.getCurrentSession();
        Map<Integer, Set<CfSubModule>> result = new HashMap<Integer, Set<CfSubModule>>();

        Set<CfSubModule> view = new HashSet<CfSubModule>();
        Set<CfSubModule> create = new HashSet<CfSubModule>();
        Set<CfSubModule> update = new HashSet<CfSubModule>();
        Set<CfSubModule> delete = new HashSet<CfSubModule>();
        Set<CfSubModule> cancel = new HashSet<CfSubModule>();
        Set<CfSubModule> print = new HashSet<CfSubModule>();
        Set<CfSubModule> admin = new HashSet<CfSubModule>();

        List sqlResults = null;
        int j = 0;
        int k = 0;
        final int MAX_SIZE = 999;
        log.debug("sid size:" + sid.size());
        for (; ; ) {
            if (j > sid.size()) break;
            SQLQuery query = session.createSQLQuery("select " +
                    "distinct o.object_id_identity,e.mask from acl_entry e, acl_object_identity o, acl_class c, acl_sid s " +
                    "where o.id = e.acl_object_identity " +
                    "and o.object_id_class = c.id " +
                    "and s.id = e.sid " +
                    "and s.sid in (:sid) " +
                    "and c.class = :class ");

            k = j + MAX_SIZE;
            if (k >= sid.size()) k = sid.size();
            log.debug("query acl with sid list: " + sid.subList(j, k).size());
            query.setParameterList("sid", sid.subList(j, k));
            query.setString("class", CfSubModule.class.getSimpleName());
            j = j + MAX_SIZE + 1;
            if (null == sqlResults) sqlResults = query.list();
            else sqlResults.addAll(query.list());
        }

        for (Object sqlResult : sqlResults) {
            Object[] r = (Object[]) sqlResult;
            BigDecimal oid = (BigDecimal) r[0];
            BigDecimal mask = (BigDecimal) r[1];
            CfSubModule subModule = (CfSubModule) session.get(CfSubModuleImpl.class, Long.valueOf(oid.longValue()));
            switch (mask.intValue()) {
                case 1:
                    view.add(subModule);
                    break;
                case 2:
                    create.add(subModule);
                    break;
                case 4:
                    update.add(subModule);
                    break;
                case 8:
                    delete.add(subModule);
                    break;
                case 16:
                    cancel.add(subModule);
                    break;
                case 32:
                    print.add(subModule);
                    break;
                case 64:
                    admin.add(subModule);
                    break;
            }
        }

        result.put(1, view);
        result.put(2, create);
        result.put(3, update);
        result.put(4, delete);
        result.put(5, cancel);
        result.put(6, print);
        result.put(7, admin);
        return result;
    }

    @Override
    public List<CfModule> find() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select m from CfModule m");
        return (List<CfModule>) query.list();
    }

    @Override
    public List<CfModule> find(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select m from CfModule m");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfModule>) query.list();
    }

    @Override
    public List<CfModule> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select m from CfModule m where " +
                "m.code like upper(:filter) " +
                "or upper(m.description) like upper(:filter)");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfModule>) query.list();
    }

    @Override
    public List<CfSubModule> findSubModules(CfModule module) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select m from CfSubModule m where " +
                "m.module=:module ");
        query.setEntity("module", module);
        return (List<CfSubModule>) query.list();
    }

    @Override
    public List<CfSubModule> findSubModules(CfModule module, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select m from CfSubModule m where " +
                "m.module=:module ");
        query.setEntity("module", module);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfSubModule>) query.list();
    }

    @Override
    public CfSubModule findSubModuleByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select m from CfSubModule m where " +
                "m.code=:code ");
        query.setString("code", code);
        return (CfSubModule) query.uniqueResult();
    }

    @Override
    public CfSubModule findSubModuleByDescription(String description) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select m from CfSubModule m where " +
                "m.description=:description ");
        query.setString("description", description);
        return (CfSubModule) query.uniqueResult();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(m) from CfModule m");
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(m) from CfModule m where m.code like upper(:filter) or m.description like upper(:filter) order by m.code");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean isExist(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from CfModule m where m.code = :code");
        query.setString("code", code);
        return 0 < ((Long) query.uniqueResult()).intValue();

    }


    // =============================================================================
    // CRUD METHODS
    // =============================================================================


    @Override
    public void addSubModule(CfModule module, CfSubModule subModule, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        Session session = sessionFactory.getCurrentSession();

        CfMetadata metadata = new CfMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreator(user.getId());
        metadata.setState(CfMetaState.ACTIVE);
        subModule.setMetadata(metadata);
        session.save(subModule);
    }

    @Override
    public void addSubModules(CfModule module, List<CfSubModule> subModules, CfUser user) {
        for (CfSubModule subModule : subModules) {
            addSubModule(module, subModule, user);
        }
    }

    @Override
    public void removeSubModule(CfModule module, CfSubModule subModule, CfUser user) {
        Validate.notNull(user, "User cannot be null");
        Session session = sessionFactory.getCurrentSession();
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeSubModules(CfModule module, List<CfSubModule> subModules, CfUser user) {
        for (CfSubModule subModule : subModules) {
            removeSubModule(module, subModule, user);
        }
    }
}
