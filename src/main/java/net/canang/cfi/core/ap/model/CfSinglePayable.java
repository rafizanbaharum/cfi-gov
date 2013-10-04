package net.canang.cfi.core.ap.model;

import net.canang.cfi.core.dd.model.CfActorInfo;
import net.canang.cfi.core.dd.model.CfAddressInfo;
import net.canang.cfi.core.dd.model.CfPaymentInfo;
import net.canang.cfi.core.so.model.CfActor;

import java.util.List;

/**
 * @author canang.technologies
 * @since 10/2/13
 */
public interface CfSinglePayable<I extends CfPayableItem> extends CfPayable {

    CfActor getActor();

    void setActor(CfActor actor);

    CfActorInfo getActorInfo();

    void setActorInfo(CfActorInfo recipient);

    CfAddressInfo getAddressInfo();

    void setAddressInfo(CfAddressInfo addressInfo);

    CfPaymentInfo getPaymentInfo();

    void setPaymentInfo(CfPaymentInfo paymentInfo);

    List<I> getItems();

    void setItems(List<I> items);

}
