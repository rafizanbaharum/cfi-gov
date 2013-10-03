package net.canang.cfi.biz.bm.manager;

import net.canang.cfi.biz.bm.manager.history.ControlRecord;
import net.canang.cfi.biz.bm.manager.workflow.ControlTask;
import net.canang.cfi.core.bm.model.CfControl;
import net.canang.cfi.core.bm.model.CfControlItem;
import net.canang.cfi.core.bm.model.CfControlType;
import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.so.model.CfFlowState;

import java.util.Date;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public interface BmManager {

    List<ControlTask> findAssignedTasks(CfControlType type, Integer offset, Integer limit);

    List<ControlTask> findAssignedTasks(CfControlType type, CfFlowState flowState, Integer offset, Integer limit);

    List<ControlTask> findAssignedBatchControlTasks(Integer offset, Integer limit);

    List<ControlTask> findCandidateTasks(CfControlType type, Integer offset, Integer limit);

    List<ControlTask> findCandidateTasks(CfControlType type, CfFlowState flowState, Integer offset, Integer limit);

    List<ControlTask> findCandidateBatchControlTasks(Integer offset, Integer limit);

    List<ControlRecord> findAuthorizedRecords(String filter, CfFlowState flowType, Date startDate, Date endDate, CfControlType type, Integer offset, Integer limit);

    List<ControlRecord> findAuthorizedBatchControlRecords(String filter, CfFlowState flowType, Date startDate, Date endDate, Integer offset, Integer limit);

    Integer countAssignedTask(CfControlType type);

    Integer countAssignedTask(CfControlType type, CfFlowState flowState);

    Integer countAssignedBatchControlTask();

    Integer countCandidateTask(CfControlType type);

    Integer countCandidateTask(CfControlType type, CfFlowState flowState);

    Integer countAuthorizedRecord(String filter, CfFlowState flowType, Date startDate, Date endDate, CfControlType type);

    Integer countAuthorizedBatchControlRecord(String filter, CfFlowState flowType, Date startDate, Date endDate);

    Integer countCandidateBatchControlTask();

    String processControl(CfControl control);

    String cancelControl(CfControl control);

    CfControl findControlByTaskId(String taskId);

    CfControl findControlByRecordId(Long recordId);

    // ==================================================================================================== //
    // Control
    // ==================================================================================================== //

    void updateControl(CfControl control);

    void addControl(CfPeriod period, CfControl control, List<CfControlItem> items);

    // ==================================================================================================== //
    // Control ITEM
    // ==================================================================================================== //

    void addControlItem(CfPeriod period, CfControl control, CfControlItem items);

    void addControlItems(CfPeriod period, CfControl control, List<CfControlItem> items);

    void updateControlItem(CfControl control, CfControlItem item);

    void updateControlItems(CfControl control, List<CfControlItem> items);

    void removeControlItem(CfControl control, CfControlItem item);

    void removeControlItems(CfControl control, List<CfControlItem> items);

    // ==================================================================================================== //
    // HELPER
    // ==================================================================================================== //

    boolean hasVoteTransaction(CfControl control);

    void initializeItems(CfControl control);

    void initializeItemsFromRoot(CfControl control);

    // ==================================================================================================== //
    // REFERENCE NO
    // ==================================================================================================== //

    String generateReferenceNo(String code, CfCostCenter costCenter);

    String generateReferenceNo(String code, CfCostCenter requester, CfPeriod period);
}
