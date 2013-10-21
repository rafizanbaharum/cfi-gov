package net.canang.cfi.core.dd.dao.impl;

import net.canang.cfi.core.dd.dao.CfPositionCodeDao;
import net.canang.cfi.core.dd.model.CfPositionCode;
import net.canang.cfi.core.dd.model.impl.CfPositionCodeImpl;
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
@Repository("positionCodeDao")
public class CfPositionCodeDaoImpl extends DaoSupport<Long, CfPositionCode, CfPositionCodeImpl> implements CfPositionCodeDao {

    private static final Logger log = Logger.getLogger(CfPositionCodeDaoImpl.class);

    @Override
    public CfPositionCode findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfPositionCode c where c.code = :code" +
                " and c.metadata.state = :state");
        query.setString("code", code);
        query.setInteger("state", ACTIVE.ordinal());
        query.setCacheable(true);
        return (CfPositionCode) query.uniqueResult();
    }

    @Override
    public CfPositionCode findByGrade(Integer grade) {
        return findByGrade(grade.toString());
    }

    @Override
    public List<CfPositionCode> find(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfPositionCode c where " +
                " c.metadata.state = :state");
        query.setInteger("state", ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<CfPositionCode>) query.list();
    }

    @Override
    public List<CfPositionCode> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfPositionCode c where " +
                " upper(c.code) like upper(:filter) " +
                " or upper(c.description) like upper(:filter) " +
                " or upper(c.alias) like upper(:filter) " +
                " and c.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<CfPositionCode>) query.list();
    }

    private CfPositionCode findByGrade(String grade) {
        Integer i = 0;
        try {
            i = Integer.valueOf(grade);
        } catch (Exception e) {
            return null;
        }

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from CfPositionCode c inner join c.gradeClass g where :grade between g.gradeFrom and g.gradeTo" +
                " and c.metadata.state = :state");
        query.setString("grade", grade);
        query.setInteger("state", ACTIVE.ordinal());
        query.setCacheable(true);
        return (CfPositionCode) query.uniqueResult();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count (s) from CfPositionCode s where " +
                "s.metadata.state = :state");
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count (s) from CfPositionCode s where s.code like :filter" +
                " and s.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

}
