package net.canang.cfi.core.dd.dao.impl;

import net.canang.cfi.core.dd.dao.CfCityCodeDao;
import net.canang.cfi.core.dd.model.CfCityCode;
import net.canang.cfi.core.dd.model.CfStateCode;
import net.canang.cfi.core.dd.model.impl.CfCityCodeImpl;
import net.canang.cfi.core.so.dao.DaoSupport;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

import static net.canang.cfi.core.so.model.CfMetaState.ACTIVE;

/**
 * @author : alif haikal razak
 */
public class CfCityCodeDaoImpl extends DaoSupport<Long, CfCityCode, CfCityCodeImpl> implements CfCityCodeDao {

    private static final Logger log = Logger.getLogger(CfCityCodeDaoImpl.class);

    @Override
    public CfCityCode findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from  CfCityCode c where c.id = :id and " +
                "c.metadata.state = :state");
        query.setLong("id", id);
        query.setInteger("state", ACTIVE.ordinal());
        return (CfCityCode) query.uniqueResult();
    }

    @Override
    public CfCityCode findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from  CfCityCode c where c.code = :code and " +
                "c.metadata.state = :state");
        query.setString("code", code);
        query.setInteger("state", ACTIVE.ordinal());
        return (CfCityCode) query.uniqueResult();
    }

    @Override
    public CfCityCode findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from  CfCityCode c where c.name = :name and " +
                "c.metadata.state = :state");
        query.setString("name", name);
        query.setInteger("state", ACTIVE.ordinal());
        return (CfCityCode) query.uniqueResult();
    }

    @Override
    public CfCityCode findByNameAndState(String name, CfStateCode stateCode) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from  CfCityCode c where c.name = :name and c.stateCode.id = :id  and " +
                "c.metadata.state = :state");
        query.setString("name", name);
        query.setLong("id", stateCode.getId());
        query.setInteger("state", ACTIVE.ordinal());
        return (CfCityCode) query.uniqueResult();
    }

    @Override
    public List<CfCityCode> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from  CfCityCode c " +
                "where (upper(c.name) like upper(:filter) " +
                "or upper(c.code) like upper(:filter)) and " +
                "c.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        return (List<CfCityCode>) query.list();
    }

    @Override
    public List<CfCityCode> find(CfStateCode stateCode, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from  CfCityCode c where " +
                "c.metadata.state = :state and c.stateCode = :stateCode ");
        query.setInteger("state", ACTIVE.ordinal());
        query.setEntity("stateCode", stateCode);
        return (List<CfCityCode>) query.list();
    }

    @Override
    public List<CfCityCode> find(CfStateCode stateCode, String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from  CfCityCode c where " +
                "(upper(c.name) like upper(:filter) " +
                "or upper(c.code) like upper(:filter)) and " +
                "c.metadata.state = :state and c.stateCode = :stateCode");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        query.setEntity("stateCode", stateCode);
        return (List<CfCityCode>) query.list();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(c) from CfCityCode c where " +
                "c.metadata.state = :state");
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(c) from CfCityCode c where " +
                "(upper(c.code) like upper(:filter) " +
                "or upper(c.name) like upper(:filter)) " +
                "and c.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(CfStateCode stateCode, String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(c) from CfCityCode c where " +
                "(upper(c.code) like upper(:filter) " +
                "or upper(c.name) like upper(:filter)) " +
                "and c.stateCode.id = :stateCodeId " +
                "and c.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        query.setLong("stateCodeId", stateCode.getId());
        return ((Long) query.uniqueResult()).intValue();
    }
}
