package net.canang.cfi.core.dd.dao.impl;

import net.canang.cfi.core.dd.dao.CfConfigurationDao;
import net.canang.cfi.core.dd.model.CfConfiguration;
import net.canang.cfi.core.dd.model.impl.CfConfigurationImpl;
import net.canang.cfi.core.so.dao.DaoSupport;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

import static net.canang.cfi.core.dd.model.CfConfigurationConstants.CONFIGURATION_ENVIRONMENT;
import static net.canang.cfi.core.dd.model.CfConfigurationConstants.CONFIGURATION_LOCAL;
import static net.canang.cfi.core.so.model.CfMetaState.ACTIVE;

/**
 * @author : alif haikal razak
 */
public class CfConfigurationDaoImpl extends DaoSupport<Long, CfConfiguration, CfConfigurationImpl> implements CfConfigurationDao {
    // =============================================================================
    // HELPER METHODS
    // =============================================================================

    @Override
    public CfConfiguration newInstance() {
        return new CfConfigurationImpl();
    }


    // =============================================================================
    // FINDER METHODS
    // =============================================================================

    @Override
    public CfConfiguration findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CfConfiguration) session.get(CfConfigurationImpl.class, id);
    }

    @Override
    public CfConfiguration findByKey(String key) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select rn from CfConfiguration rn where rn.key = :key");
        query.setString("key", key);
        query.setCacheable(true);
        return (CfConfiguration) query.uniqueResult();
    }

    @Override
    public List<CfConfiguration> find(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select rn from CfConfiguration rn where " +
                "rn.metadata.state = :state");
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return query.list();
    }

    @Override
    public List<CfConfiguration> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select rn from CfConfiguration rn where " +
                "(rn.key like upper(:filter) " +
                "or upper(rn.value) like upper(:filter)) " +
                "and rn.metadata.state = :state");
        query.setString("filter", filter);
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(rn) from CfConfiguration rn where " +
                "rn.metadata.state = :state");
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(rn) from CfConfiguration rn where " +
                "(rn.key like upper(:filter) " +
                "or upper(rn.value) like upper(:filter)) " +
                "and rn.metadata.state = :state");
        query.setString("filter", filter);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean isLocal() {
        return CONFIGURATION_LOCAL.equals(findByKey(CONFIGURATION_ENVIRONMENT).getValue());
    }
}
