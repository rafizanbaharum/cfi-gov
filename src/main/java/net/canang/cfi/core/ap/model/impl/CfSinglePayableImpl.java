package net.canang.cfi.core.ap.model.impl;

import net.canang.cfi.core.ap.model.CfPayableMultiplicity;
import net.canang.cfi.core.ap.model.CfSinglePayable;
import net.canang.cfi.core.ap.model.CfSinglePayableItem;
import net.canang.cfi.core.dd.model.CfActorInfo;
import net.canang.cfi.core.dd.model.CfAddressInfo;
import net.canang.cfi.core.dd.model.CfPaymentInfo;
import net.canang.cfi.core.so.model.CfActor;
import net.canang.cfi.core.so.model.impl.CfActorImpl;

import javax.persistence.*;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/2/13
 */
@Table(name = "CF_SNGL_PYBL")
@Entity(name = "CfSinglePayable")
public abstract class CfSinglePayableImpl extends CfPayableImpl implements CfSinglePayable {

    @ManyToOne(targetEntity = CfActorImpl.class)
    @JoinColumn(name = "ACTOR_ID")
    private CfActor actor;

    @Embedded
    private CfActorInfo actorInfo;

    @Embedded
    private CfAddressInfo addressInfo;

    @Embedded
    private CfPaymentInfo paymentInfo;

    @OneToMany(targetEntity = CfSinglePayableItem.class, mappedBy = "invoice", fetch = FetchType.LAZY)
    private List<CfSinglePayableItem> items;

    public CfSinglePayableImpl() {
        setPayableMultiplicity(CfPayableMultiplicity.SINGLE);
    }

    public CfActor getActor() {
        return actor;
    }

    public void setActor(CfActor actor) {
        this.actor = actor;
    }

    public CfActorInfo getActorInfo() {
        return actorInfo;
    }

    public void setActorInfo(CfActorInfo actorInfo) {
        this.actorInfo = actorInfo;
    }

    public CfAddressInfo getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(CfAddressInfo addressInfo) {
        this.addressInfo = addressInfo;
    }

    public CfPaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(CfPaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public List<CfSinglePayableItem> getItems() {
        return items;
    }

    public void setItems(List<CfSinglePayableItem> items) {
        this.items = items;
    }
}
