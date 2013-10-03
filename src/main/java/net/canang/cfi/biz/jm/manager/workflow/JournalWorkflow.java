package net.canang.cfi.biz.jm.manager.workflow;

import net.canang.cfi.core.jm.model.CfJournal;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public interface JournalWorkflow {

    void process(CfJournal journal);

}
