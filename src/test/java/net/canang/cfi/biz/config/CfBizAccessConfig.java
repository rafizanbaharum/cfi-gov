package net.canang.cfi.biz.config;

import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.acls.AclPermissionEvaluator;
import org.springframework.security.acls.domain.EhCacheBasedAclCache;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.model.AclCache;
import org.springframework.security.acls.model.AclService;

import javax.sql.DataSource;

/**
 * http://spring.io/blog/2013/07/03/spring-security-java-config-preview-web-security/
 *
 * @author rafizan.baharum
 * @since 10/2/13
 */
@Configuration
public class CfBizAccessConfig {

    @Bean
    public AclService mutableAclService(DataSource dataSource, AclCache cache) {
        return new JdbcMutableAclService(dataSource, null, cache);
    }

    @Bean
    public PermissionEvaluator permissionEvaluator(AclService aclService) {
        return new AclPermissionEvaluator(aclService);
    }

    @Bean
    public AclCache aclCache() {
        EhCacheFactoryBean factory = new EhCacheFactoryBean();
        factory.setCacheManager(new EhCacheManagerFactoryBean().getObject());
        return new EhCacheBasedAclCache(factory.getObject());
    }
}

