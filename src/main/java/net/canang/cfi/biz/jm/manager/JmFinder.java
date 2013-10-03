package net.canang.cfi.biz.jm.manager;

import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.jm.model.CfJournal;
import net.canang.cfi.core.jm.model.CfJournalTransaction;
import net.canang.cfi.core.jm.model.CfJournalType;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public interface JmFinder {

    // ==================================================================================================== //
    // FINDER METHODS
    // ==================================================================================================== //

    CfJournal findJournalById(Long id);

    CfJournalTransaction findJournalTransactionById(Long id);

    CfJournal findJournalByReferenceNo(String referenceNo);

    CfJournal findJournalByJournalNo(String journalNo);

    CfJournal findJournalBySourceNo(String sourceNo);

    List<CfJournal> findJournalsBySourceNo(String sourceNo);

    List<CfJournal> findJournals(CfJournalType type, Integer offset, Integer limit);

    List<CfJournal> findJournals(CfJournalType type, CfCostCenter costCenter);

    List<CfJournal> findJournals(CfJournalType type, CfCostCenter costCenter, Integer offset, Integer limit);

    List<CfJournalTransaction> findJournalTransactions(CfJournal journal);

    List<CfJournalTransaction> findJournalTransactions(CfJournal journal, boolean voteDetailed);

    List<CfJournalTransaction> findJournalTransactions(CfJournal journal, Integer offset, Integer limit);


    // ==================================================================================================== //
    // COUNT METHODS
    // ==================================================================================================== //

    Integer countJournal(CfJournalType type);

    Integer countJournal(CfJournalType type, String filter);

    Integer countJournal(CfJournalType type, CfCostCenter costCenter);

    Integer countJournalTransaction(CfJournal journal);
}
