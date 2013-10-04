package net.canang.cfi.core.ap.model.impl;

import net.canang.cfi.core.ap.model.CfClaim;
import net.canang.cfi.core.ap.model.CfPayableType;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/2/13
 */
public class CfClaimImpl extends CfSinglePayableImpl implements CfClaim {

    public CfClaimImpl() {
        super();
        setPayableType(CfPayableType.CLAIM);
    }

    @Override
    public void setItems(List items) {
        // TODO:

    }
}
