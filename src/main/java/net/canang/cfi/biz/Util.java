package net.canang.cfi.biz;

import net.canang.cfi.biz.integration.springsecurity.CfUserDetails;
import net.canang.cfi.core.so.model.CfUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public class Util {
    
    /**
     * get logged in user
     *
     * @return current user
     */
    public static CfUser getCurrentUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof UserDetails) {
            return ((CfUserDetails) auth.getPrincipal()).getUser();
        } else {
            return null;
        }
    }
    
}
