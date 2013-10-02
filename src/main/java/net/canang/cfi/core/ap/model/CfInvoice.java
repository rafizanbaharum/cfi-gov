package net.canang.cfi.core.ap.model;

import net.canang.cfi.core.dd.model.CfConsumerInfo;
import net.canang.cfi.core.dd.model.CfPaymentInfo;
import net.canang.cfi.core.so.model.CfActor;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfInvoice extends CfPayable {

    CfInvoiceType getInvoiceType();

    void setInvoiceType(CfInvoiceType type);

    CfConsumerInfo getConsumerInfo();

    void setConsumerInfo(CfConsumerInfo consumerInfo);

    CfPaymentInfo getPaymentInfo();

    void setPaymentInfo(CfPaymentInfo paymentInfo);

    CfActor getActor();

    void setActor(CfActor actor);

    List<CfInvoiceItem> getItems();

    void setItems(List<CfInvoiceItem> items);

}
