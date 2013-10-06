package net.canang.cfi.biz.config;

import net.canang.cfi.biz.integration.springacl.CfLookupStrategy;
import net.canang.cfi.biz.integration.springacl.CfPermissionFactory;
import net.canang.cfi.biz.integration.springacl.CfPostgresqlMutableAclService;
import net.canang.cfi.biz.integration.springacl.CfSidRetrievalStrategy;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.cache.interceptor.DefaultKeyGenerator;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.acls.AclPermissionEvaluator;
import org.springframework.security.acls.domain.*;
import org.springframework.security.acls.jdbc.LookupStrategy;
import org.springframework.security.acls.model.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * http://spring.io/blog/2013/07/03/spring-security-java-config-preview-web-security/
 *
 * @author rafizan.baharum
 * @since 10/2/13
 */
@Configuration
@EnableCaching
public class CfBizAccessConfig implements CachingConfigurer {

    @Bean
    public MutableAclService mutableAclService(DataSource dataSource, LookupStrategy lookupStrategy,  AclCache cache) {
        return new CfPostgresqlMutableAclService(dataSource, lookupStrategy, cache);
    }

    @Bean
    public AclCache aclCache(Cache cache, PermissionGrantingStrategy permissionStrategy, AclAuthorizationStrategy authorizationStrategy) {
        return new SpringCacheBasedAclCache(cache, permissionStrategy, authorizationStrategy);
    }

    @Bean
    public PermissionEvaluator permissionEvaluator(AclService aclService) {
        return new AclPermissionEvaluator(aclService);
    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        net.sf.ehcache.Cache aDefault1 = new net.sf.ehcache.Cache("default", 10000, true, false, 1000, 1000, false, 1000);
        aDefault1.initialise();
        EhCacheCache aDefault = new EhCacheCache(aDefault1);
        simpleCacheManager.setCaches(Arrays.asList(aDefault));
        return simpleCacheManager;
    }

    @Bean
    public Cache cache(CacheManager cacheManager) {
        return cacheManager.getCache("default");
    }

    @Override
    public KeyGenerator keyGenerator() {
        return new DefaultKeyGenerator();
    }

    @Bean
    public PermissionFactory permissionFactory() {
        return new CfPermissionFactory();
    }

    @Bean
    public LookupStrategy lookupStrategy(DataSource dataSource, AclCache aclCache, AclAuthorizationStrategy authorizationStrategy, PermissionGrantingStrategy permissionGrantingStrategy) {
        return new CfLookupStrategy(dataSource, aclCache, authorizationStrategy, permissionGrantingStrategy);

    }

    @Bean
    public PermissionGrantingStrategy permissionGrantingStrategy() {
        return new DefaultPermissionGrantingStrategy(new ConsoleAuditLogger());
    }

    @Bean
    public AclAuthorizationStrategy aclAuthorizationStrategy(SidRetrievalStrategy sidRetrievalStrategy){
        AclAuthorizationStrategyImpl aclAuthorizationStrategy = new AclAuthorizationStrategyImpl(
                new SimpleGrantedAuthority("ROLE_ADMINISTRATOR"),
                new SimpleGrantedAuthority("ROLE_ADMINISTRATOR"),
                new SimpleGrantedAuthority("ROLE_ADMINISTRATOR")
        );
         aclAuthorizationStrategy.setSidRetrievalStrategy(sidRetrievalStrategy);
        return aclAuthorizationStrategy;
    }

    @Bean
    public SidRetrievalStrategy sidRetrievalStrategy() {
        return new CfSidRetrievalStrategy();
    }
}


