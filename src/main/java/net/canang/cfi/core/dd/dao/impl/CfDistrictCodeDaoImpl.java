package net.canang.cfi.core.dd.dao.impl;

import net.canang.cfi.core.dd.dao.CfDistrictCodeDao;
import net.canang.cfi.core.dd.model.CfDistrictCode;
import net.canang.cfi.core.dd.model.CfStateCode;
import net.canang.cfi.core.dd.model.impl.CfDistrictCodeImpl;
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
@Repository("districtCodeDao")
public class CfDistrictCodeDaoImpl extends DaoSupport<Long, CfDistrictCode, CfDistrictCodeImpl> implements CfDistrictCodeDao {

    private static final Logger log = Logger.getLogger(CfDistrictCodeDaoImpl.class);

    @Override
    public CfDistrictCode findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from  CfDistrictCode c where c.id = :id and " +
                "c.metadata.state = :state");
        query.setLong("id", id);
        query.setInteger("state", ACTIVE.ordinal());
        return (CfDistrictCode) query.uniqueResult();
    }

    @Override
    public CfDistrictCode findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from  CfDistrictCode c where c.code = :code and " +
                "c.metadata.state = :state");
        query.setString("code", code);
        query.setInteger("state", ACTIVE.ordinal());
        return (CfDistrictCode) query.uniqueResult();
    }

    @Override
    public CfDistrictCode findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from  CfDistrictCode c where c.name = :name and " +
                "c.metadata.state = :state");
        query.setString("name", name);
        query.setInteger("state", ACTIVE.ordinal());
        return (CfDistrictCode) query.uniqueResult();
    }

    @Override
    public List<CfDistrictCode> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from  CfDistrictCode c where (c.name like :filter or c.code like :filter) and " +
                "c.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        return (List<CfDistrictCode>) query.list();
    }

    @Override
    public List<CfDistrictCode> find(CfStateCode stateCode, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from  CfDistrictCode c where " +
                "c.metadata.state = :state and c.stateCode = :stateCode ");
        query.setInteger("state", ACTIVE.ordinal());
        query.setEntity("stateCode", stateCode);
        return (List<CfDistrictCode>) query.list();
    }

    @Override
    public List<CfDistrictCode> find(CfStateCode stateCode, String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from  CfDistrictCode c where (c.name like :filter or c.code like :filter) and " +
                "c.metadata.state = :state and c.stateCode = :stateCode");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        query.setEntity("stateCode", stateCode);
        return (List<CfDistrictCode>) query.list();
    }
}
