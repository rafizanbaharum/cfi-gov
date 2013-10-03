package net.canang.cfi.core.ap.model;

import java.util.List;

/**
 * @author canang.technologies
 * @since 10/2/13
 */
public interface CfBatchPayable extends CfPayable {

    List<CfBatchPayableRecipient> getRecipients();

}
