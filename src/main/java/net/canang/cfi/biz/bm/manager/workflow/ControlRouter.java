package net.canang.cfi.biz.bm.manager.workflow;

import net.canang.cfi.biz.bm.manager.BmFinder;
import net.canang.cfi.biz.event.AccessEvent;
import net.canang.cfi.biz.integration.springacl.CfAclPermission;
import net.canang.cfi.core.bm.model.CfControl;
import net.canang.cfi.core.so.dao.CfGroupDao;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
@Component("controlRouter")
public class ControlRouter {

    private static final Logger log = Logger.getLogger(ControlRouter.class);

    @Autowired
    private ApplicationContext context;

    @Autowired
    private BmFinder bmFinder;

    @Autowired
    private CfGroupDao groupDao;

    private static final String DEFAULT_CANDIDATE = "GRP_ADM";

    public List<String> findDrafterCandidates(Long controlId) {
        Validate.notNull(controlId, "Id must not be null");

        String candidate = "GRP_BDH_KRN";
        CfControl control = bmFinder.findControlById(controlId);
        context.publishEvent(new AccessEvent(control, groupDao.findByName(candidate), CfAclPermission.VIEW));
        return Arrays.asList("GRP_BDH_KRN", DEFAULT_CANDIDATE);
    }

    public List<String> findRegistererCandidates(Long controlId) {
        Validate.notNull(controlId, "Id must not be null");

        String candidate = "GRP_BDH_PGW";
        CfControl control = bmFinder.findControlById(controlId);
        context.publishEvent(new AccessEvent(control, groupDao.findByName(candidate), CfAclPermission.VIEW));
        return Arrays.asList("GRP_BDH_PEG", DEFAULT_CANDIDATE);
    }

    public List<String> findVerifierCandidates(Long controlId) {
        Validate.notNull(controlId, "Id must not be null");

        String candidate = "GRP_PTJ_KRN";
        CfControl control = bmFinder.findControlById(controlId);
        context.publishEvent(new AccessEvent(control, groupDao.findByName(candidate), CfAclPermission.VIEW));
        return Arrays.asList("GRP_PTJ_KRN", DEFAULT_CANDIDATE);
    }

    public List<String> findPreparerCandidates(Long controlId) {
        Validate.notNull(controlId, "Id must not be null");

        String candidate = "GRP_PTJ_PGW";
        CfControl control = bmFinder.findControlById(controlId);
        context.publishEvent(new AccessEvent(control, groupDao.findByName(candidate), CfAclPermission.VIEW));
        return Arrays.asList("GRP_PTJ_PGW", DEFAULT_CANDIDATE);
    }
}
