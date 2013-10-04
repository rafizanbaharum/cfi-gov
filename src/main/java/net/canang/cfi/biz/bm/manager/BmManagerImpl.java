package net.canang.cfi.biz.bm.manager;

import net.canang.cfi.biz.bm.manager.history.ControlRecord;
import net.canang.cfi.biz.bm.manager.workflow.ControlTask;
import net.canang.cfi.core.bm.model.CfControl;
import net.canang.cfi.core.bm.model.CfControlItem;
import net.canang.cfi.core.bm.model.CfControlType;
import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.so.model.CfFlowState;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/4/13
 */
@Service("bmManager")
public class BmManagerImpl implements BmManager {
    @Override
    public List<ControlTask> findAssignedTasks(CfControlType type, Integer offset, Integer limit) {
        return null;  // TODO:

    }

    @Override
    public List<ControlTask> findAssignedTasks(CfControlType type, CfFlowState flowState, Integer offset, Integer limit) {
        return null;  // TODO:

    }

    @Override
    public List<ControlTask> findAssignedBatchControlTasks(Integer offset, Integer limit) {
        return null;  // TODO:

    }

    @Override
    public List<ControlTask> findCandidateTasks(CfControlType type, Integer offset, Integer limit) {
        return null;  // TODO:

    }

    @Override
    public List<ControlTask> findCandidateTasks(CfControlType type, CfFlowState flowState, Integer offset, Integer limit) {
        return null;  // TODO:

    }

    @Override
    public List<ControlTask> findCandidateBatchControlTasks(Integer offset, Integer limit) {
        return null;  // TODO:

    }

    @Override
    public List<ControlRecord> findAuthorizedRecords(String filter, CfFlowState flowType, Date startDate, Date endDate, CfControlType type, Integer offset, Integer limit) {
        return null;  // TODO:

    }

    @Override
    public List<ControlRecord> findAuthorizedBatchControlRecords(String filter, CfFlowState flowType, Date startDate, Date endDate, Integer offset, Integer limit) {
        return null;  // TODO:

    }

    @Override
    public Integer countAssignedTask(CfControlType type) {
        return null;  // TODO:

    }

    @Override
    public Integer countAssignedTask(CfControlType type, CfFlowState flowState) {
        return null;  // TODO:

    }

    @Override
    public Integer countAssignedBatchControlTask() {
        return null;  // TODO:

    }

    @Override
    public Integer countCandidateTask(CfControlType type) {
        return null;  // TODO:

    }

    @Override
    public Integer countCandidateTask(CfControlType type, CfFlowState flowState) {
        return null;  // TODO:

    }

    @Override
    public Integer countAuthorizedRecord(String filter, CfFlowState flowType, Date startDate, Date endDate, CfControlType type) {
        return null;  // TODO:

    }

    @Override
    public Integer countAuthorizedBatchControlRecord(String filter, CfFlowState flowType, Date startDate, Date endDate) {
        return null;  // TODO:

    }

    @Override
    public Integer countCandidateBatchControlTask() {
        return null;  // TODO:

    }

    @Override
    public String processControl(CfControl control) {
        return null;  // TODO:

    }

    @Override
    public String cancelControl(CfControl control) {
        return null;  // TODO:

    }

    @Override
    public CfControl findControlByTaskId(String taskId) {
        return null;  // TODO:

    }

    @Override
    public CfControl findControlByRecordId(Long recordId) {
        return null;  // TODO:

    }

    @Override
    public void updateControl(CfControl control) {
        // TODO:

    }

    @Override
    public void addControl(CfPeriod period, CfControl control, List<CfControlItem> items) {
        // TODO:

    }

    @Override
    public void addControlItem(CfPeriod period, CfControl control, CfControlItem items) {
        // TODO:

    }

    @Override
    public void addControlItems(CfPeriod period, CfControl control, List<CfControlItem> items) {
        // TODO:

    }

    @Override
    public void updateControlItem(CfControl control, CfControlItem item) {
        // TODO:

    }

    @Override
    public void updateControlItems(CfControl control, List<CfControlItem> items) {
        // TODO:

    }

    @Override
    public void removeControlItem(CfControl control, CfControlItem item) {
        // TODO:

    }

    @Override
    public void removeControlItems(CfControl control, List<CfControlItem> items) {
        // TODO:

    }

    @Override
    public boolean hasVoteTransaction(CfControl control) {
        return false;  // TODO:

    }

    @Override
    public void initializeItems(CfControl control) {
        // TODO:

    }

    @Override
    public void initializeItemsFromRoot(CfControl control) {
        // TODO:

    }

    @Override
    public String generateReferenceNo(String code, CfCostCenter costCenter) {
        return null;  // TODO:

    }

    @Override
    public String generateReferenceNo(String code, CfCostCenter requester, CfPeriod period) {
        return null;  // TODO:

    }
}
