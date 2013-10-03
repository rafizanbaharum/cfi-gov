package net.canang.cfi.biz.ar.manager.workflow;

import net.canang.cfi.biz.ar.manager.ArFinder;
import net.canang.cfi.biz.event.AccessEvent;
import net.canang.cfi.biz.integration.springacl.CfAclPermission;
import net.canang.cfi.core.ar.model.CfReceipt;
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
@Component("receiptRouter")
public class ReceiptRouter {

    private static final Logger log = Logger.getLogger(ReceiptRouter.class);

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ArFinder arFinder;

    @Autowired
    private CfGroupDao groupDao;

    private static final String DEFAULT_CANDIDATE = "GRP_ADM";

    /**
     * @param receiptId requisition id
     * @return candidate group
     */
    public List<String> findRegistererCandidates(Long receiptId) {
        Validate.notNull(receiptId, "Id must not be null");

        String candidate = "GRP_BDH_PGW";
        CfReceipt receipt = arFinder.findReceiptById(receiptId);
        context.publishEvent(new AccessEvent(receipt, groupDao.findByName(candidate), CfAclPermission.VIEW));
        return Arrays.asList("GRP_PDH_PGW", DEFAULT_CANDIDATE);
    }
}
