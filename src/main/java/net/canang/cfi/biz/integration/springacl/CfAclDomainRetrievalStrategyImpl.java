package net.canang.cfi.biz.integration.springacl;

import net.canang.cfi.core.so.model.CfMetaObject;
import org.apache.log4j.Logger;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.ObjectIdentityRetrievalStrategy;

/**
 * @author rafizan.baharum
 * @since 10/2/13
 */
public class CfAclDomainRetrievalStrategyImpl implements ObjectIdentityRetrievalStrategy {

    private static final Logger log = Logger.getLogger(CfAclDomainRetrievalStrategyImpl.class);

    // return the first interface
    // NOTE: hibernate injects classes at runtime
    public ObjectIdentity getObjectIdentity(Object domainObject) {
        Class<?>[] interfaces = domainObject.getClass().getInterfaces();
        log.debug("domain interface: " + interfaces[0].getCanonicalName());
        return new ObjectIdentityImpl(interfaces[0], ((CfMetaObject) domainObject).getId());
    }
}
