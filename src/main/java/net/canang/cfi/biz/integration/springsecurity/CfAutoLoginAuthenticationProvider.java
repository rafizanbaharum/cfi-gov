package net.canang.cfi.biz.integration.springsecurity;

import net.canang.cfi.core.so.dao.CfPrincipalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author rafizan.baharum
 * @since 10/4/13
 */
@Service("autoLoginAuthenticationProvider")
public class CfAutoLoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CfPrincipalDao principalDao;

    @Autowired
    @Qualifier("userDetailService")
    private UserDetailsService userDetailService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = String.valueOf(authentication.getPrincipal());
        UserDetails userDetail = userDetailService.loadUserByUsername(username);
        if (null == userDetail)
            throw new BadCredentialsException("Bad credentials");
        CfAutoLoginToken autoLoginToken = new CfAutoLoginToken(principalDao.findByName(userDetail.getUsername()));
        return autoLoginToken;
    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return (CfAutoLoginToken.class.isAssignableFrom(authentication));
    }
}
