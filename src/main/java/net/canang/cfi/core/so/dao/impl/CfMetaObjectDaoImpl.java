package net.canang.cfi.core.so.dao.impl;

import net.canang.cfi.core.so.dao.CfMetaObjectDao;
import net.canang.cfi.core.so.model.CfMetaObject;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
@SuppressWarnings({"unchecked"})
@Repository("objectDao")
public class CfMetaObjectDaoImpl implements CfMetaObjectDao{

    private static final Logger log = Logger.getLogger(CfMetaObjectDaoImpl.class);

    @Autowired(required = true)
    protected SessionFactory sessionFactory;

    @Override
    public CfMetaObject findObjectById(String entityName, Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (CfMetaObject) session.get(entityName, id);
    }
}
