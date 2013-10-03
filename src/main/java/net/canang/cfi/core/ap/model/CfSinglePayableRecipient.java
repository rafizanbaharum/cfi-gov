package net.canang.cfi.core.ap.model;

import net.canang.cfi.core.so.model.CfActor;
import net.canang.cfi.core.so.model.CfMetaObject;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public interface CfSinglePayableRecipient extends CfMetaObject {

    CfActor getActor();

    void setActor(CfActor actor);

    CfPayableRecipientInfo getRecipient();

    void setRecipient(CfPayableRecipientInfo recipient);

}
