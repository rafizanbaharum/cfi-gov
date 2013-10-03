package net.canang.cfi.core.jm.model.impl;

import net.canang.cfi.core.jm.model.CfJournalType;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public class CfManualJournalImpl extends CfJournalImpl {

    private static final long serialVersionUID = 1L;

    public CfManualJournalImpl() {
        super();
        setJournalType(CfJournalType.MANUAL);
    }
}
