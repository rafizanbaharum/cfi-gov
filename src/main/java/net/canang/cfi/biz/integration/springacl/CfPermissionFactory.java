package net.canang.cfi.biz.integration.springacl;

import org.apache.log4j.Logger;
import org.springframework.security.acls.domain.DefaultPermissionFactory;

/**
 * @author rafizan.baharum
 * @since 10/4/13
 */
public class CfPermissionFactory extends DefaultPermissionFactory {

    private static final Logger log = Logger.getLogger(CfPermissionFactory.class);

    public CfPermissionFactory() {
        super(CfAclPermission.class);
    }
}
