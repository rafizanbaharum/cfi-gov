package net.canang.cfi.biz.jm.manager.workflow.task;

import net.canang.cfi.biz.Util;
import net.canang.cfi.core.jm.model.CfJournal;
import net.canang.cfi.core.so.model.CfFlowState;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 *
 */
@Component("manualJournal_draft_ST")
public class ManualJournalDraftTask extends JournalTaskSupport {

    private static final Logger log = Logger.getLogger(ManualJournalDraftTask.class);

    public void execute(ActivityExecution execution) throws Exception {
        log.debug("drafting journal");

        // retrieve journal from variable
        Long journalId = (Long) execution.getVariable("documentId");
        CfJournal journal = jmFinder.findJournalById(journalId);

        // update flow state
        journal.getFlowdata().setState(CfFlowState.DRAFTED);
        journal.getFlowdata().setDraftedDate(new Timestamp(System.currentTimeMillis()));
        journal.getFlowdata().setDrafter(Util.getCurrentUser().getId());
        jmManager.updateJournal(journal);

    }
}
