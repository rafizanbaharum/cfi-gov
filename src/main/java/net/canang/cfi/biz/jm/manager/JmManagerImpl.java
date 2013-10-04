package net.canang.cfi.biz.jm.manager;

import net.canang.cfi.biz.Util;
import net.canang.cfi.biz.jm.manager.history.JournalRecord;
import net.canang.cfi.biz.jm.manager.workflow.JournalTask;
import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.jm.dao.CfJournalDao;
import net.canang.cfi.core.jm.model.CfJournal;
import net.canang.cfi.core.jm.model.CfJournalTransaction;
import net.canang.cfi.core.jm.model.CfJournalType;
import net.canang.cfi.core.so.model.CfFlowState;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/4/13
 */
@Transactional
@Service("jmManager")
public class JmManagerImpl implements JmManager {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CfJournalDao journalDao;

    @Override
    public List<JournalTask> findAssignedTasks(CfJournalType type, Integer offset, Integer limit) {
        return null;  // TODO:

    }

    @Override
    public List<JournalTask> findAssignedTasks(CfJournalType type, CfFlowState flowState, Integer offset, Integer limit) {
        return null;  // TODO:

    }

    @Override
    public List<JournalTask> findCandidateTasks(CfJournalType type, Integer offset, Integer limit) {
        return null;  // TODO:

    }

    @Override
    public List<JournalTask> findCandidateTasks(CfJournalType type, CfFlowState flowState, Integer offset, Integer limit) {
        return null;  // TODO:

    }

    @Override
    public List<JournalRecord> findAuthorizedRecords(String filter, CfFlowState flowType, Date startDate, Date endDate, CfJournalType type, Integer offset, Integer limit) {
        return null;  // TODO:

    }

    @Override
    public Integer countAssignedTask(CfJournalType type) {
        return null;  // TODO:

    }

    @Override
    public Integer countAssignedTask(CfJournalType type, CfFlowState flowState) {
        return null;  // TODO:

    }

    @Override
    public Integer countCandidateTask(CfJournalType type) {
        return null;  // TODO:

    }

    @Override
    public Integer countCandidateTask(CfJournalType type, CfFlowState flowState) {
        return null;  // TODO:

    }

    @Override
    public Integer countAuthorizedRecord(String filter, CfFlowState flowType, Date startDate, Date endDate, CfJournalType type) {
        return null;  // TODO:

    }

    @Override
    public void processJournal(CfJournal journal, List<CfJournalTransaction> transactions) {
        // TODO:

    }

    @Override
    public String cancelJournal(CfJournal journal) {
        return null;  // TODO:

    }

    @Override
    public CfJournal findJournalByTaskId(String taskId) {
        return null;  // TODO:

    }

    @Override
    public CfJournal findJournalByRecordId(Long recordId) {
        return null;  // TODO:

    }

    @Override
    public void saveJournal(CfJournal journal) {
        journalDao.save(journal, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateJournal(CfJournal journal) {
        journalDao.update(journal, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void addJournalTransaction(CfJournal journal, CfJournalTransaction transaction) {
        journalDao.addTransaction(journal, transaction, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void addJournalTransactions(CfJournal journal, List<CfJournalTransaction> transactions) {
        journalDao.addTransactions(journal, transactions, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateJournalTransaction(CfJournal journal, CfJournalTransaction transaction) {
        journalDao.updateTransaction(journal, transaction, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateJournalTransactions(CfJournal journal, List<CfJournalTransaction> transactions) {
        journalDao.updateTransactions(journal, transactions, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void removeJournalTransaction(CfJournal journal, CfJournalTransaction transaction) {
        journalDao.removeTransaction(journal, transaction, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void removeJournalTransactions(CfJournal journal, List<CfJournalTransaction> transactions) {
        journalDao.removeTransactions(journal, transactions, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public String generateReferenceNo(String code, CfCostCenter costCenter) {
        return null;  // TODO:
    }
}
