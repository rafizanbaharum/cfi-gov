package net.canang.cfi.biz.config;

import net.canang.cfi.biz.integration.activiti.CfActivitiGroupManagerFactory;
import net.canang.cfi.biz.integration.activiti.CfActivitiUserManagerFactory;
import org.activiti.engine.*;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/2/13
 */
@Configuration
public class CfBizWorkflowConfig {

    @Autowired
    private CfBizConfig mainConfig;

    @Autowired
    private CfActivitiUserManagerFactory userManagerFactory;
    @Autowired
    private CfActivitiGroupManagerFactory groupManagerFactory;

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration() {
        SpringProcessEngineConfiguration springConfiguration = new SpringProcessEngineConfiguration();
        springConfiguration.setProcessEngineName("CFI Workflow Engine");
        springConfiguration.setDatabaseType("postgresql");
        springConfiguration.setDataSource(mainConfig.dataSource());
        springConfiguration.setTransactionManager(mainConfig.transactionManager());
        springConfiguration.setDatabaseSchemaUpdate("true");
        springConfiguration.setJobExecutorActivate(false);
        springConfiguration.setHistory("full");
        List<SessionFactory> sf = new ArrayList<SessionFactory>();
        sf.add(userManagerFactory);
        sf.add(groupManagerFactory);
        springConfiguration.setCustomSessionFactories(sf);
        return springConfiguration;
    }

    @Bean
    public ProcessEngineFactoryBean processEngineFactoryBean() {
        ProcessEngineFactoryBean engine = new ProcessEngineFactoryBean();
        engine.setProcessEngineConfiguration(springProcessEngineConfiguration());
        return engine;

    }

    @Bean
    public RepositoryService repositoryService(ProcessEngineFactoryBean pefb) throws Exception {
        return pefb.getObject().getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService(ProcessEngineFactoryBean pefb) throws Exception {
        return pefb.getObject().getRuntimeService();
    }

    @Bean
    public HistoryService historyService(ProcessEngineFactoryBean pefb) throws Exception {
        return pefb.getObject().getHistoryService();
    }

    @Bean
    public ManagementService managementService(ProcessEngineFactoryBean pefb) throws Exception {
        return pefb.getObject().getManagementService();
    }

    @Bean
    public IdentityService identityService(ProcessEngineFactoryBean pefb) throws Exception {
        return pefb.getObject().getIdentityService();
    }

    @Bean
    public TaskService taskService(ProcessEngineFactoryBean pefb) throws Exception {
        return pefb.getObject().getTaskService();
    }
}
