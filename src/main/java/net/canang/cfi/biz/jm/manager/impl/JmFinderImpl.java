package net.canang.cfi.biz.jm.manager.impl;

import net.canang.cfi.biz.jm.manager.JmFinder;
import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.jm.dao.CfJournalDao;
import net.canang.cfi.core.jm.model.CfJournal;
import net.canang.cfi.core.jm.model.CfJournalTransaction;
import net.canang.cfi.core.jm.model.CfJournalType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
@Service("jmFinder")
public class JmFinderImpl implements JmFinder {

    private static final Logger log = Logger.getLogger(JmFinderImpl.class);

    @Autowired
    private CfJournalDao journalDao;


    @Override
    public CfJournal findJournalById(Long id) {
        return journalDao.findById(id);
    }

    @Override
    public CfJournalTransaction findJournalTransactionById(Long id) {
        return journalDao.findTransactionById(id);
    }

    @Override
    public CfJournal findJournalByReferenceNo(String referenceNo) {
        return journalDao.findByReferenceNo(referenceNo);
    }

    @Override
    public CfJournal findJournalByJournalNo(String journalNo) {
        return journalDao.findByJournalNo(journalNo);
    }

    @Override
    public CfJournal findJournalBySourceNo(String sourceNo) {
        return journalDao.findBySourceNo(sourceNo);
    }

    @Override
    public List<CfJournal> findJournalsBySourceNo(String sourceNo) {
        return journalDao.findManyBySourceNo(sourceNo);
    }

    @Override
    public List<CfJournal> findJournals(CfJournalType type, Integer offset, Integer limit) {
        return journalDao.find(type, offset, limit);
    }

    @Override
    public List<CfJournal> findJournals(CfJournalType type, CfCostCenter costCenter) {
        return journalDao.find(type, costCenter);
    }

    @Override
    public List<CfJournal> findJournals(CfJournalType type, CfCostCenter costCenter, Integer offset, Integer limit) {
        return journalDao.find(type, costCenter);
    }

    @Override
    public List<CfJournalTransaction> findJournalTransactions(CfJournal journal, Integer offset, Integer limit) {
        return journalDao.findTransactions(journal, offset, limit);
    }

    @Override
    public List<CfJournalTransaction> findJournalTransactions(CfJournal journal) {
        return journalDao.findTransactions(journal);
    }

    @Override
    public List<CfJournalTransaction> findJournalTransactions(CfJournal journal, boolean voteDetailed) {
        return decorate(voteDetailed, journalDao.findTransactions(journal));
    }

    @Override
    public Integer countJournal(CfJournalType type) {
        return journalDao.count(type);
    }

    @Override
    public Integer countJournal(CfJournalType type, String filter) {
        return journalDao.count(type, filter);
    }

    @Override
    public Integer countJournal(CfJournalType type, CfCostCenter costCenter) {
        return journalDao.count(type, costCenter);
    }

    @Override
    public Integer countJournalTransaction(CfJournal journal) {
        return journalDao.countTransaction(journal);
    }

    private List<CfJournalTransaction> decorate(boolean voteDetailed, List<CfJournalTransaction> transactions) {
            return transactions;
    }

}
