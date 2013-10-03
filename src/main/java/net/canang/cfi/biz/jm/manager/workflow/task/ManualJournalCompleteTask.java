package net.canang.cfi.biz.jm.manager.workflow.task;

import net.canang.cfi.core.jm.model.CfJournal;
import net.canang.cfi.core.so.model.CfFlowState;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Component("manualJournal_complete_ST")
@Transactional
public class ManualJournalCompleteTask extends JournalTaskSupport {

    private static final Logger log = Logger.getLogger(ManualJournalCompleteTask.class);

    public void execute(ActivityExecution execution) throws Exception {

        log.debug("completing journal");

        // retrieve journal from variable
        Long journalId = (Long) execution.getVariable("documentId");
        CfJournal journal = jmFinder.findJournalById(journalId);

        // update flow state
        journal.getFlowdata().setState(CfFlowState.COMPLETED);
        jmManager.updateJournal(journal);

    }
}
