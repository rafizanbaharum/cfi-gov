package net.canang.cfi.biz.jm.manager;

import net.canang.cfi.biz.jm.manager.history.JournalRecord;
import net.canang.cfi.biz.jm.manager.workflow.JournalTask;
import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.jm.model.CfJournal;
import net.canang.cfi.core.jm.model.CfJournalTransaction;
import net.canang.cfi.core.jm.model.CfJournalType;
import net.canang.cfi.core.so.model.CfFlowState;

import java.util.Date;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public interface JmManager {

    // admin group
    public static final String GRP_ADM = "GRP_ADM_JRL";

    List<JournalTask> findAssignedTasks(CfJournalType type, Integer offset, Integer limit);

    List<JournalTask> findAssignedTasks(CfJournalType type, CfFlowState flowState, Integer offset, Integer limit);

    List<JournalTask> findCandidateTasks(CfJournalType type, Integer offset, Integer limit);

    List<JournalTask> findCandidateTasks(CfJournalType type, CfFlowState flowState, Integer offset, Integer limit);

    List<JournalRecord> findAuthorizedRecords(String filter, CfFlowState flowType, Date startDate, Date endDate, CfJournalType type, Integer offset, Integer limit);

    Integer countAssignedTask(CfJournalType type);

    Integer countAssignedTask(CfJournalType type, CfFlowState flowState);

    Integer countCandidateTask(CfJournalType type);

    Integer countCandidateTask(CfJournalType type, CfFlowState flowState);

    Integer countAuthorizedRecord(String filter, CfFlowState flowType, Date startDate, Date endDate, CfJournalType type);

    // ==================================================================================================== //
    // JOURNAL WORKFLOW METHODS
    // ==================================================================================================== //

    void processJournal(CfJournal journal, List<CfJournalTransaction> transactions);

    String cancelJournal(CfJournal journal);

    CfJournal findJournalByTaskId(String taskId);

    CfJournal findJournalByRecordId(Long recordId);


    // ==================================================================================================== //
    // HELPER METHODS
    // ==================================================================================================== //

    CfJournal saveJournal(CfJournal journal);

    CfJournal updateJournal(CfJournal journal);

    void addJournalTransaction(CfJournal journal, CfJournalTransaction transactions);

    void addJournalTransactions(CfJournal journal, List<CfJournalTransaction> transactions);

    void updateJournalTransaction(CfJournal journal, CfJournalTransaction transaction);

    void updateJournalTransactions(CfJournal journal, List<CfJournalTransaction> transactions);

    void removeJournalTransaction(CfJournal journal, CfJournalTransaction transaction);

    void removeJournalTransactions(CfJournal journal, List<CfJournalTransaction> transactions);

    String generateReferenceNo(String code, CfCostCenter costCenter);

}
