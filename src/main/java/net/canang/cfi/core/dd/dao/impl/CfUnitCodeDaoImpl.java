package net.canang.cfi.core.dd.dao.impl;

import net.canang.cfi.core.dd.dao.CfUnitCodeDao;
import net.canang.cfi.core.dd.model.CfUnitCode;
import net.canang.cfi.core.dd.model.impl.CfUnitCodeImpl;
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
@SuppressWarnings({"unchecked"})
@Repository("unitCodeDao")
public class CfUnitCodeDaoImpl extends DaoSupport<Long, CfUnitCode, CfUnitCodeImpl> implements CfUnitCodeDao {

    private static final Logger log = Logger.getLogger(CfUnitCodeDaoImpl.class);

    @Override
    public CfUnitCode findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfUnitCode c where c.code = :code" +
                " and c.metadata.state = :state");
        query.setString("code", code);
        query.setInteger("state", ACTIVE.ordinal());
        query.setCacheable(true);
        return (CfUnitCode) query.uniqueResult();
    }

    @Override
    public List<CfUnitCode> find(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfUnitCode c where " +
                "c.metadata.state = :state");
        query.setInteger("state", ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<CfUnitCode>) query.list();
    }

    @Override
    public List<CfUnitCode> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfUnitCode c where " +
                " upper(c.code) like upper(:filter) " +
                " or upper(c.description) like upper(:filter) " +
                " or upper(c.alias) like upper(:filter) " +
                " and c.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<CfUnitCode>) query.list();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count (s) from CfUnitCode s where " +
                "s.metadata.state = :state");
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }


    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count (s) from CfUnitCode s where s.code like :filter" +
                " and s.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

}
