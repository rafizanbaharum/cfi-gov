package net.canang.cfi.core.ap.model;

import net.canang.cfi.core.dd.model.CfActorInfo;
import net.canang.cfi.core.so.model.CfActor;
import net.canang.cfi.core.so.model.CfMetaObject;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public interface CfBatchPayableRecipient extends CfMetaObject {

    CfActor getActor();

    void setActor(CfActor actor);

    CfActorInfo getActorInfo();

    void setActorInfo(CfActorInfo recipient);

    List<CfBatchPayableItem> getItems();

}
