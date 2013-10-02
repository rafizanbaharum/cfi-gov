package net.canang.cfi.core.so.dao.impl;

import net.canang.cfi.core.exception.RecursiveGroupException;
import net.canang.cfi.core.so.dao.CfPrincipalDao;
import net.canang.cfi.core.so.dao.CfRoleDao;
import net.canang.cfi.core.so.dao.DaoSupport;
import net.canang.cfi.core.so.model.CfMetaState;
import net.canang.cfi.core.so.model.CfPrincipal;
import net.canang.cfi.core.so.model.CfPrincipalType;
import net.canang.cfi.core.so.model.impl.CfPrincipalImpl;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author canang.technologies
 * @since 7/12/13
 */
@Repository("principalDao")
public class CfPrincipalDaoImpl extends DaoSupport<Long, CfPrincipal, CfPrincipalImpl> implements CfPrincipalDao {

    private static final Logger log = Logger.getLogger(CfPrincipalDaoImpl.class);

    private CfRoleDao roleDao;

    private boolean allowRecursiveGroup = false;

    private Set<String> groupName = null;


    @Override
    public List<CfPrincipal> findAllPrincipals() {
        Session session = sessionFactory.getCurrentSession();
        List<CfPrincipal> results = new ArrayList<CfPrincipal>();
        Query query = session.createQuery("select p from CfUser p where p.metadata.state = :state order by p.name");
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        results.addAll((List<CfPrincipal>) query.list());

        Query queryGroup = session.createQuery("select p from CfGroup p where p.metadata.state = :state order by p.name ");
        queryGroup.setInteger("state", CfMetaState.ACTIVE.ordinal());
        results.addAll((List<CfPrincipal>) queryGroup.list());

        return results;
    }

    @Override
    public List<CfPrincipal> findPrincipals(String filter) {
        Session session = sessionFactory.getCurrentSession();
        List<CfPrincipal> results = new ArrayList<CfPrincipal>();
        Query query = session.createQuery("select p from CfPrincipal p where p.metadata.state = :state and p.name like :filter order by p.name");
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        query.setString("filter", WILDCARD + filter + WILDCARD);
        results.addAll((List<CfPrincipal>) query.list());
        return results;
    }

    @Override
    public List<CfPrincipal> findPrincipals(String filter, CfPrincipalType type) {
        Session session = sessionFactory.getCurrentSession();
        List<CfPrincipal> results = new ArrayList<CfPrincipal>();
        Query query = session.createQuery("select p from CfPrincipal p where " +
                "p.metadata.state = :state " +
                "and p.name like :filter " +
                "and p.principalType = :principalType " +
                "order by p.name");
        query.setInteger("state", CfMetaState.ACTIVE.ordinal());
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("principalType", type.ordinal());
        results.addAll((List<CfPrincipal>) query.list());
        return results;
    }

    /**
     * find principal
     *
     * @param offset offset
     * @param limit  limit
     * @return list of principals
     */
    @Override
    public List<CfPrincipal> findPrincipals(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from CfPrincipal p");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<CfPrincipal>) query.list();
    }

    public Set<GrantedAuthority> loadEffectiveAuthorities(CfPrincipal principal) throws RecursiveGroupException {
        return new HashSet<GrantedAuthority>(); // todo
    }

    @Override
    public CfPrincipal findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        log.debug("Searching for principal " + name);
        Query query = session.createQuery("select p from CfPrincipal p where p.name = :name");
        query.setString("name", name);
        return (CfPrincipal) query.uniqueResult();
    }
}
