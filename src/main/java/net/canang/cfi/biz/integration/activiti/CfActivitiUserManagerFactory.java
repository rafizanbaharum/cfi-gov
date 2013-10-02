package net.canang.cfi.biz.integration.activiti;

import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.UserIdentityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author rafizan.baharum
 * @since 10/2/13
 */
@Component("activityUserManagerFactory")
public class CfActivitiUserManagerFactory implements SessionFactory {

    @Autowired
    private CfActivitiUserManager userManager;

    @Override
    public Class<?> getSessionType() {
        return UserIdentityManager.class;

    }

    @Override
    public org.activiti.engine.impl.interceptor.Session openSession() {
        return userManager;

    }
}
