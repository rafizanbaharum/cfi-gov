package net.canang.cfi.biz.integration.springacl;

import org.apache.log4j.Logger;
import org.springframework.security.acls.domain.DefaultPermissionFactory;

/**
 * @author rafizan.baharum
 * @since 10/2/13
 */
public class CfAclPermissionFactory extends DefaultPermissionFactory {

    private static final Logger log = Logger.getLogger(CfAclPermissionFactory.class);

    public CfAclPermissionFactory() {
        super(CfAclPermission.class);
    }
}
