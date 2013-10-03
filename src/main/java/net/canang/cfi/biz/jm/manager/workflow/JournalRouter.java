package net.canang.cfi.biz.jm.manager.workflow;

import net.canang.cfi.biz.event.AccessEvent;
import net.canang.cfi.biz.integration.springacl.CfAclPermission;
import net.canang.cfi.biz.jm.manager.JmFinder;
import net.canang.cfi.core.jm.model.CfJournal;
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
@Component("journalRouter")
public class JournalRouter {

    private static final Logger log = Logger.getLogger(JournalRouter.class);

    @Autowired
    private ApplicationContext context;

    @Autowired
    private JmFinder jmFinder;

    @Autowired
    private CfGroupDao groupDao;

    private static final String DEFAULT_CANDIDATE = "GRP_ADM";

    public List<String> findDrafterCandidates(Long journalId) {
        Validate.notNull(journalId, "Id must not be null");

        String candidate = "GRP_BDH_KRN";
        CfJournal journal = jmFinder.findJournalById(journalId);
        context.publishEvent(new AccessEvent(journal, groupDao.findByName(candidate), CfAclPermission.VIEW));
        return Arrays.asList("GRP_BDH_KRN", DEFAULT_CANDIDATE);
    }

    public List<String> findRegistererCandidates(Long journalId) {
        Validate.notNull(journalId, "Id must not be null");

        String candidate = "GRP_BDH_PGW";
        CfJournal journal = jmFinder.findJournalById(journalId);
        context.publishEvent(new AccessEvent(journal, groupDao.findByName(candidate), CfAclPermission.VIEW));
        return Arrays.asList("GRP_BDH_PGW", DEFAULT_CANDIDATE);
    }
}
