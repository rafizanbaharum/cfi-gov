package net.canang.cfi.core.bm.model.impl;

import net.canang.cfi.core.bm.model.CfControlType;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author rafizan.baharum
 * @since 10/2/13
 */
@Table(name = "CF_DSBN_CTRL")
@Entity(name = "CtDistributionControl")
public class CfDistributionControlImpl extends CfControlImpl {

    private static final long serialVersionUID = 1L;

    public CfDistributionControlImpl() {
        super();
        setControlType(CfControlType.DISTRIBUTION);
    }
}
