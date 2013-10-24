package net.canang.cfi.biz.am.manager;

import net.canang.cfi.biz.Util;
import net.canang.cfi.biz.event.AccessEvent;
import net.canang.cfi.biz.integration.springacl.CfAclPermission;
import net.canang.cfi.core.am.dao.CfModuleDao;
import net.canang.cfi.core.am.model.CfModule;
import net.canang.cfi.core.so.dao.CfGroupDao;
import net.canang.cfi.core.so.dao.CfMetaObjectDao;
import net.canang.cfi.core.so.dao.CfPrincipalDao;
import net.canang.cfi.core.so.model.CfMetaObject;
import net.canang.cfi.core.so.model.CfPrincipal;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

import static net.canang.cfi.biz.event.AccessEvent.Command.REVOKE;

/**
 * @author rafizan.baharum
 * @since 10/20/13
 */
@Component("amManager")
public class AmManagerImpl implements AmManager {

    private static final Logger log = Logger.getLogger(AmManagerImpl.class);

    @Autowired
    private CfMetaObjectDao metaObjectDao;

    @Autowired
    private CfPrincipalDao principalDao;

    @Autowired
    private CfGroupDao groupDao;

    @Autowired
    private CfModuleDao moduleDao;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ApplicationContext applicationContext;


    // =============================================================================
    // PERMISSION METHODS
    // =============================================================================

    @Override
    public void saveModule(CfModule module) {
        moduleDao.save(module, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateModule(CfModule module) {
        moduleDao.update(module, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    // =============================================================================
    // PERMISSION METHODS
    // =============================================================================

    public void grantPermission(String entityName, Long id, CfPrincipal principal, CfAclPermission permission) {
        try {
            CfMetaObject object = metaObjectDao.findObjectById(entityName, id);
            applicationContext.publishEvent(new AccessEvent(object, principal, permission));
        } catch (Exception e) {
            log.error("error occured", e);
        }
    }

    public void grantPermission(String entityName, Long id, List<CfPrincipal> principals, CfAclPermission permission) {
        try {
            CfMetaObject object = metaObjectDao.findObjectById(entityName, id);
            for (CfPrincipal principal : principals) {
                applicationContext.publishEvent(new AccessEvent(object, principal, permission));
            }
        } catch (Exception e) {
            log.error("error occured", e);
        }
    }

    @Override
    public void addPermission(CfMetaObject object, CfAclPermission permission) {
        applicationContext.publishEvent(new AccessEvent(object, Util.getCurrentUser(), permission));
    }

    @Override
    public void deletePermission(CfMetaObject object, CfAclPermission permission) {
        applicationContext.publishEvent(new AccessEvent(object, Util.getCurrentUser(), permission, REVOKE));
    }

    @Override
    public boolean hasPermission(CfMetaObject object, CfAclPermission permission) {
        throw new UnsupportedOperationException();// TODO
    }

    @Override
    public boolean hasPermission(CfMetaObject object, CfPrincipal principal, CfAclPermission permission) {
        throw new UnsupportedOperationException();// TODO
    }

    @Override
    public void addPermission(CfMetaObject object, CfPrincipal principal, CfAclPermission permission) {
        throw new UnsupportedOperationException();// TODO
    }

    @Override
    public void deletePermission(CfMetaObject object, CfPrincipal principal, CfAclPermission permission) {
        throw new UnsupportedOperationException();// TODO
    }

    @Override
    public boolean checkPermission(CfMetaObject object, CfPrincipal principal, CfAclPermission permission) {
        throw new UnsupportedOperationException(); // TODO
    }

    @Override
    public boolean checkPermissionByProxy(CfMetaObject object, CfPrincipal principal, CfAclPermission permission) {
        throw new UnsupportedOperationException(); // TODO
    }

}
