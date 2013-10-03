package net.canang.cfi.core.jm.dao;

import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.jm.model.CfJournal;
import net.canang.cfi.core.jm.model.CfJournalTransaction;
import net.canang.cfi.core.jm.model.CfJournalType;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfJournalDao {

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    CfJournal newInstance(CfJournalType type);

    CfJournalTransaction newTransactionInstance();

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    CfJournal findById(Long id);

    CfJournalTransaction findTransactionById(Long id);

    CfJournal findByReferenceNo(String referenceNo);

    CfJournal findByJournalNo(String journalNo);

    CfJournal findBySourceNo(String sourceNo);

    List<CfJournal> findManyBySourceNo(String sourceNo);

    List<CfJournal> find(CfJournalType type, CfCostCenter requester);

    List<CfJournal> find(CfJournalType type, CfCostCenter requester, Integer offset, Integer limit);

    List<CfJournal> find(CfJournalType type, Integer offset, Integer limit);

    List<CfJournal> find(CfJournalType type, String filter, Integer offset, Integer limit);

    List<CfJournalTransaction> findTransactions(CfJournal journal);

    List<CfJournalTransaction> findTransactions(CfJournal journal, Integer offset, Integer limit);

    Integer count(CfJournalType type);

    Integer count(CfJournalType type, String filter);

    Integer count(CfJournalType type, CfCostCenter requester);

    Integer countTransaction(CfJournal journal);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    void save(CfJournal journal, CfUser user);

    void update(CfJournal journal, CfUser user);

    void deactivate(CfJournal journal, CfUser user);

    void remove(CfJournal journal, CfUser user);

    void addTransaction(CfJournal journal, CfJournalTransaction item, CfUser user);

    void addTransactions(CfJournal journal, List<CfJournalTransaction> items, CfUser user);

    void updateTransaction(CfJournal journal, CfJournalTransaction item, CfUser user);

    void updateTransactions(CfJournal journal, List<CfJournalTransaction> items, CfUser user);

    void removeTransaction(CfJournal journal, CfJournalTransaction item, CfUser user);

    void removeTransactions(CfJournal journal, List<CfJournalTransaction> items, CfUser user);

}
