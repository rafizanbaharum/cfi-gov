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
@Component("manualJournal_remove_ST")
public class ManualJournalRemoveTask extends JournalTaskSupport {

    private static final Logger log = Logger.getLogger(ManualJournalRemoveTask.class);

    public void execute(ActivityExecution execution) throws Exception {

        // retrieve journal from variable
        Long journalId = (Long) execution.getVariable("documentId");

        log.debug("removing journal with id: " + journalId);
        CfJournal journal = jmFinder.findJournalById(journalId);

        // serialize remove comment
        String removeComment = (String) execution.getVariable("removeComment");
        journal.setRemoveComment(removeComment);

        // update flow state
        journal.getFlowdata().setState(CfFlowState.REMOVED);
        journal.getFlowdata().setRemovedDate(new Timestamp(System.currentTimeMillis()));
        journal.getFlowdata().setRemover(Util.getCurrentUser().getId());
        jmManager.updateJournal(journal);

    }
}
