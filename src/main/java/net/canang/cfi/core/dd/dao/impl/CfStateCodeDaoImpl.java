package net.canang.cfi.core.dd.dao.impl;

import net.canang.cfi.core.dd.dao.CfStateCodeDao;
import net.canang.cfi.core.dd.model.CfCountryCode;
import net.canang.cfi.core.dd.model.CfStateCode;
import net.canang.cfi.core.dd.model.impl.CfStateCodeImpl;
import net.canang.cfi.core.so.dao.DaoSupport;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static net.canang.cfi.core.so.model.CfMetaState.ACTIVE;

/**
 * @author : alif haikal razak
 */
@Repository("stateCodeDao")
public class CfStateCodeDaoImpl extends DaoSupport<Long, CfStateCode, CfStateCodeImpl> implements CfStateCodeDao {

    private static final Logger log = Logger.getLogger(CfStateCodeDaoImpl.class);

    @Override
    public CfStateCode findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from  CfStateCode c where c.code = :code and " +
                "c.metadata.state = :state");
        query.setString("code", code);
        query.setInteger("state", ACTIVE.ordinal());
        return (CfStateCode) query.uniqueResult();
    }

    @Override
    public CfStateCode findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from  CfStateCode c where " +
                "c.id = :id " +
                "and c.metadata.state = :state");
        query.setLong("id", id);
        query.setInteger("state", ACTIVE.ordinal());
        return (CfStateCode) query.uniqueResult();
    }

    @Override
    public CfStateCode findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from  CfStateCode c where " +
                "c.name = :name " +
                "and c.metadata.state = :state");
        query.setString("name", name);
        query.setInteger("state", ACTIVE.ordinal());
        return (CfStateCode) query.uniqueResult();
    }

    @Override
    public List<CfStateCode> find(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from  CfStateCode c where " +
                "c.metadata.state = :state");
        query.setInteger("state", ACTIVE.ordinal());
        return (List<CfStateCode>) query.list();
    }

    @Override
    public List<CfStateCode> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from  CfStateCode c where " +
                "(upper(c.name) like upper(:filter) " +
                "or upper(c.code) like upper(:filter)) " +
                "and c.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        return (List<CfStateCode>) query.list();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(c) from CfStateCode c where " +
                "c.metadata.state = :state");
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(c) from CfStateCode c where " +
                "(upper(c.code) like upper(:filter) " +
                "or upper(c.name) like upper(:filter)) " +
                "and c.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter, CfCountryCode countryCode) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(c) from CfStateCode c where " +
                "(upper(c.code) like upper(:filter) " +
                "or upper(c.name) like upper(:filter)) " +
                "and c.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }
}
