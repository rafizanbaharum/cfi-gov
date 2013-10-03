package net.canang.cfi.biz.jm.manager.workflow.task;

import net.canang.cfi.biz.Util;
import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.dd.model.CfReferenceNoConstants;
import net.canang.cfi.core.jm.model.CfJournal;
import net.canang.cfi.core.so.model.CfFlowState;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 *
 */
@Component("manualJournal_approve_ST")
@Transactional
public class ManualJournalApproveTask extends JournalTaskSupport {

    private static final Logger log = Logger.getLogger(ManualJournalApproveTask.class);

    public void execute(ActivityExecution execution) throws Exception {
        log.debug("approving journal");

        // retrieve journal from variable
        Long journalId = (Long) execution.getVariable("documentId");
        CfJournal journal = jmFinder.findJournalById(journalId);

        // update flow state
        journal.getFlowdata().setState(CfFlowState.APPROVED);
        journal.getFlowdata().setApprovedDate(new Timestamp(System.currentTimeMillis()));
        journal.getFlowdata().setApprover(Util.getCurrentUser().getId());

        CfCostCenter requester = journal.getRequester();
        journal.setJournalNo(jmManager.generateReferenceNo(CfReferenceNoConstants.JOURNAL_JOURNAL_NO, requester));
        vmManager.post(journal);
        glManager.post(journal);
        jmManager.updateJournal(journal);
    }
}
