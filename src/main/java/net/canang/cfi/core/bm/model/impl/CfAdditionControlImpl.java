package net.canang.cfi.core.bm.model.impl;

import net.canang.cfi.core.bm.model.CfControlType;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author rafizan.baharum
 * @since 10/2/13
 */
@Table(name = "CF_ADTN_CTRL")
@Entity(name = "CtAdditionControl")
public class CfAdditionControlImpl extends CfControlImpl {

    private static final long serialVersionUID = 1L;

    public CfAdditionControlImpl() {
        super();
        setControlType(CfControlType.ADDITION);
    }
}
