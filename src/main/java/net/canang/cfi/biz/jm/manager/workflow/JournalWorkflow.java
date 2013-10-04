package net.canang.cfi.biz.jm.manager.workflow;

import net.canang.cfi.core.jm.model.CfJournal;
import net.canang.cfi.core.jm.model.CfJournalType;

import java.util.List;
import java.util.Map;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public interface JournalWorkflow {

    void process(CfJournal journal);

    List<JournalTask> findTasks(CfJournalType type, Integer limit, Integer offset);

    void assignTask(JournalTask task);

    void assignTask(JournalTask task, String username);

    void claimTask(JournalTask task);

    void releaseTask(JournalTask task);

    void stealTask(JournalTask task);

    void completeTask(JournalTask task);

    void completeTask(JournalTask task, Map<String, Object> variables);

}
