package net.canang.cfi.core.ap.model.impl;

import net.canang.cfi.core.ap.model.CfPayableMultiplicity;
import net.canang.cfi.core.ap.model.CfSinglePayable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author rafizan.baharum
 * @since 10/2/13
 */
@Table(name = "CF_BTCH_PYBL")
@Entity(name = "CfBatchPayable")
public abstract class CfBatchPayableImpl extends CfPayableImpl implements CfSinglePayable{

    public CfBatchPayableImpl() {
        setPayableMultiplicity(CfPayableMultiplicity.SINGLE);
    }
}
