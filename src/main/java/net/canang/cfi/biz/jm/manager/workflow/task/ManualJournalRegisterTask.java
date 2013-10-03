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
@Component("manualJournal_register_ST")
public class ManualJournalRegisterTask extends JournalTaskSupport {

    private static final Logger log = Logger.getLogger(ManualJournalRegisterTask.class);

    public void execute(ActivityExecution execution) throws Exception {
        log.debug("registering journal");

        // retrieve journal from variable
        Long journalId = (Long) execution.getVariable("documentId");
        CfJournal journal = jmFinder.findJournalById(journalId);
        log.debug("registering journal with id: " + journalId);

        // update flow state
        journal.getFlowdata().setState(CfFlowState.REGISTERED);
        journal.getFlowdata().setRegisteredDate(new Timestamp(System.currentTimeMillis()));
        journal.getFlowdata().setRegisterer(Util.getCurrentUser().getId());
        jmManager.updateJournal(journal);
    }
}
