package net.canang.cfi.biz.config;

import net.canang.cfi.biz.integration.activiti.CfActivitiGroupManagerFactory;
import net.canang.cfi.biz.integration.activiti.CfActivitiUserManagerFactory;
import org.activiti.engine.*;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author rafizan.baharum
 * @since 10/2/13
 */
@Configuration
public class CfBizWorkflowConfig {

    @Autowired
    private CfBizConfig mainConfig;

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(
            CfActivitiUserManagerFactory userManagerFactory, CfActivitiGroupManagerFactory groupManagerFactory) {
        SpringProcessEngineConfiguration springConfiguration = new SpringProcessEngineConfiguration();
        springConfiguration.setProcessEngineName("CFI Workflow Engine");
        springConfiguration.setDatabaseType("postgresql");
        springConfiguration.setDataSource(mainConfig.dataSource());
        springConfiguration.setTransactionManager(mainConfig.transactionManager());
        springConfiguration.setDatabaseSchemaUpdate("false");
        springConfiguration.setJobExecutorActivate(false);
        springConfiguration.setHistory("full");
        springConfiguration.getSessionFactories().put(UserEntityManager.class, userManagerFactory);
        springConfiguration.getSessionFactories().put(GroupEntityManager.class, groupManagerFactory);
        return springConfiguration;
    }

    @Bean
    public ProcessEngineFactoryBean processEngineFactoryBean(SpringProcessEngineConfiguration spec) {
        ProcessEngineFactoryBean engine = new ProcessEngineFactoryBean();
        engine.setProcessEngineConfiguration(spec);
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
    public FormService formService(ProcessEngineFactoryBean pefb) throws Exception {
        return pefb.getObject().getFormService();
    }

    @Bean
    public TaskService taskService(ProcessEngineFactoryBean pefb) throws Exception {
        return pefb.getObject().getTaskService();
    }
}
