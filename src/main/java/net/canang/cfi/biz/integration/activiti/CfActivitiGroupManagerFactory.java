package net.canang.cfi.biz.integration.activiti;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author rafizan.baharum
 * @since 10/2/13
 */
@Component("activityGroupManagerFactory")
public class CfActivitiGroupManagerFactory implements SessionFactory {

    @Autowired
    private CfActivitiGroupManager groupManager;

    @Override
    public Class<?> getSessionType() {
        return GroupIdentityManager.class;

    }

    @Override
    public Session openSession() {
        return groupManager;
    }
}
