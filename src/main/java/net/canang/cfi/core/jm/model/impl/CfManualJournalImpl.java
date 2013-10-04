package net.canang.cfi.core.jm.model.impl;

import net.canang.cfi.core.jm.model.CfJournalType;
import net.canang.cfi.core.jm.model.CfManualJournal;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
@Table(name = "CF_MANL_JRNL")
@Entity(name = "CfManualJournal")
public class CfManualJournalImpl extends CfJournalImpl implements CfManualJournal{

    private static final long serialVersionUID = 1L;

    public CfManualJournalImpl() {
        super();
        setJournalType(CfJournalType.MANUAL);
    }
}
