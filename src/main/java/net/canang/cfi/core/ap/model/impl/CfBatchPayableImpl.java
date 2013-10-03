package net.canang.cfi.core.ap.model.impl;

import net.canang.cfi.core.ap.model.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * CF_DOCM > CF_PYBL > CF_BTCH_PYBL > CF_RFRL
 *
 * @author rafizan.baharum
 * @since 10/2/13
 */
@Table(name = "CF_BTCH_PYBL")
@Entity(name = "CfBatchPayable")
public abstract class CfBatchPayableImpl extends CfPayableImpl implements CfBatchPayable {

    private List<CfBatchPayableRecipient> recipients;

    public CfBatchPayableImpl() {
        setPayableMultiplicity(CfPayableMultiplicity.SINGLE);
    }

    public void test() {
        List<CfPayable> payables = new ArrayList<CfPayable>();
        for (CfPayable payable : payables) {

            if (payable instanceof CfSinglePayable) {
                CfSinglePayable p = (CfSinglePayable) payable;
                p.getActor();
                p.getActorInfo();
                p.getPaymentInfo();
                p.getAddressInfo();
                p.getItems();
            } else if (payable instanceof CfBatchPayable) {
                CfBatchPayable p = (CfBatchPayable) payable;
                List<CfBatchPayableRecipient> recipients = p.getRecipients();
                for (CfBatchPayableRecipient recipient : recipients) {
                    recipient.getActor();
                    recipient.getActorInfo();
                    recipient.getAddressInfo();
                    recipient.getPaymentInfo();
                }
            }
        }
    }


}
