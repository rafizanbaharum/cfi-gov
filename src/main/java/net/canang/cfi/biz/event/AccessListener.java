package net.canang.cfi.biz.event;

import net.canang.cfi.biz.integration.springacl.CfAclPermission;
import net.canang.cfi.biz.integration.springsecurity.CfAutoLoginToken;
import net.canang.cfi.core.so.dao.CfPrincipalDao;
import net.canang.cfi.core.so.model.CfDocument;
import net.canang.cfi.core.so.model.CfMetaObject;
import net.canang.cfi.core.so.model.CfPrincipal;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
@Component("accessListener")
public class AccessListener implements ApplicationListener<AccessEvent> {

    private static final Logger log = Logger.getLogger(AccessListener.class);

    private static final String ROOT = "root";

    @Autowired(required = false) // TODO:
    @Qualifier("org.springframework.security.authenticationManager")
    private AuthenticationManager authenticationManager;

    @Autowired
    protected CfPrincipalDao principalDao;

    @Autowired(required = false) // TODO:
    protected MutableAclService mutableAclService;

    @Autowired(required = false) // TODO:
    protected PermissionEvaluator permissionEvaluator;

    @Override
    public void onApplicationEvent(AccessEvent event) {
        log.debug("Access event triggered");
        log.debug("object: " + event.getObject().getId());
        log.debug("principal: " + event.getPrincipal().getName());
        log.debug("permission: " + event.getPermission());
        log.debug("command: " + event.getCommand());

        if (event.getObject() instanceof CfDocument)
            log.debug("object: " + ((CfDocument) event.getObject()).getReferenceNo());

        // saved current context
        SecurityContext savedCtx = SecurityContextHolder.getContext();
        try {

            // temporarily log in as system
            // NOTE: http://issues.hudson-ci.org/browse/HUDSON-7256
            CfAutoLoginToken authenticationToken = new CfAutoLoginToken(principalDao.findByName(ROOT));
            Authentication authed = authenticationManager.authenticate(authenticationToken);
            SecurityContext system = new NonSerializableSecurityContext();
            system.setAuthentication(authed);
            SecurityContextHolder.setContext(system);

            // grab parameter from event
            CfMetaObject object = event.getObject();
            CfAclPermission permission = event.getPermission();
            CfPrincipal principal = event.getPrincipal();
            PrincipalSid sid = new PrincipalSid(principal.getName());

            // creating oid
            MutableAcl acl;
            ObjectIdentity oid = new ObjectIdentityImpl(
                    object.getClass().getInterfaces()[0].getCanonicalName(),
                    object.getId());

            log.debug("==================================================");
            log.debug("oid: " + oid.getType() + "#" + oid.getIdentifier());
            log.debug("sid: " + sid);
            log.debug("mask: " + permission.getMask());
            log.debug("pattern: " + permission.getPattern());
            log.debug("==================================================");

            switch (event.getCommand()) {
                case GRANT:
                    createAce(permission, sid, oid);
                    break;
                case REVOKE:
                    deleteAce(permission, sid, oid);
                    break;
            }
        } finally {
            // revert to original context
            SecurityContextHolder.setContext(savedCtx);
        }
    }

    private void createAce(CfAclPermission permission, PrincipalSid sid, ObjectIdentity oid) {
        MutableAcl acl;// read acl, if fail, create
        try {
            acl = (MutableAcl) mutableAclService.readAclById(oid);
        } catch (NotFoundException nfe) {
            log.debug("ACL Not found! Creating new oid:" + oid);
            acl = mutableAclService.createAcl(oid);
        }

        // insert new ace and update
        acl.insertAce(acl.getEntries().size(), permission, sid, true);
        mutableAclService.updateAcl(acl);
    }

    private void deleteAce(CfAclPermission permission, PrincipalSid sid, ObjectIdentity oid) {
        MutableAcl acl;// read acl, if fail, create
        try {
            acl = (MutableAcl) mutableAclService.readAclById(oid);
            final List<AccessControlEntry> entries = acl.getEntries();
            for (int i = 0, entriesSize = entries.size(); i < entriesSize; i++) {
                AccessControlEntry entry = entries.get(i);
                if (entry.getAcl().equals(acl))
                    acl.deleteAce(i);
            }
            mutableAclService.updateAcl(acl);
        } catch (NotFoundException nfe) {

        }
    }


    /**
     * non serializing security context
     */
    class NonSerializableSecurityContext implements SecurityContext {

        private transient Authentication authentication;

        public boolean equals(Object obj) {
            if (obj instanceof SecurityContextImpl) {
                SecurityContextImpl test = (SecurityContextImpl) obj;

                if ((this.getAuthentication() == null) && (test.getAuthentication() == null)) {
                    return true;
                }

                if ((this.getAuthentication() != null) && (test.getAuthentication() != null)
                        && this.getAuthentication().equals(test.getAuthentication())) {
                    return true;
                }
            }

            return false;
        }

        public Authentication getAuthentication() {
            return authentication;
        }

        public int hashCode() {
            if (this.authentication == null) {
                return -1;
            } else {
                return this.authentication.hashCode();
            }
        }

        public void setAuthentication(Authentication authentication) {
            this.authentication = authentication;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(super.toString());
            if (this.authentication == null) {
                sb.append(": Null authentication");
            } else {
                sb.append(": Authentication: ").append(this.authentication);
            }
            return sb.toString();
        }
    }
}
