package net.canang.cfi.biz.integration.springsecurity;

import net.canang.cfi.core.so.model.CfPrincipal;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public class CfAutoLoginToken extends AbstractAuthenticationToken {

    private final CfPrincipal principal;
    private Object credentials;

    public CfAutoLoginToken(CfPrincipal principal) {
        super(null);
        this.principal = principal;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal.getName();
    }
}
