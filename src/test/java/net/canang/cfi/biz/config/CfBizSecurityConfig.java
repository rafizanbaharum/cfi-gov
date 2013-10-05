package net.canang.cfi.biz.config;

import net.canang.cfi.biz.integration.springsecurity.CfAutoLoginAuthenticationProvider;
import net.canang.cfi.biz.integration.springsecurity.CfUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * http://spring.io/blog/2013/07/03/spring-security-java-config-preview-web-security/
 *
 * @author rafizan.baharum
 * @since 10/2/13
 */
@Configuration
@EnableGlobalMethodSecurity
public class CfBizSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Autowired
    private CfUserDetailService userDetailService;

    @Autowired
    private CfAutoLoginAuthenticationProvider autoLoginAuthenticationProvider;

    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return new AuthenticationManagerBuilder(ObjectPostProcessor.QUIESCENT_POSTPROCESSOR)
                .authenticationProvider(autoLoginAuthenticationProvider)  // auto login
                .userDetailsService(userDetailService)
                .passwordEncoder(new PlaintextPasswordEncoder())
                .and().build();  // user detail
    }
}

