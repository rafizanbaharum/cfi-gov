package net.canang.cfi.biz.gl.manager;

import net.canang.cfi.core.gl.model.CfLedger;
import net.canang.cfi.core.gl.model.CfLedgerTransaction;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public interface GlFinder {

    CfLedger findLedgerById(Long id);

    CfLedger findLedgerByReferenceNo(String referenceNo);

    List<CfLedger> findLedgers(Integer offset, Integer limit);

    List<CfLedger> findLedgers(String filter, Integer offset, Integer limit);

    Integer countLedger();

    Integer countLedger(String filter);


    // ledgerTransaction

    CfLedgerTransaction findLedgerTransactionById(Long id);

    List<CfLedgerTransaction> findLedgerTransactions(CfLedger ledger);

    List<CfLedgerTransaction> findLedgerTransactions(Integer offset, Integer limit);

    List<CfLedgerTransaction> findLedgerTransactions(CfLedger ledger, Integer offset, Integer limit);

    List<CfLedgerTransaction> findLedgerTransactions(String filter, Integer offset, Integer limit);

    List<CfLedgerTransaction> findLedgerTransactions(CfLedger ledger, String filter, Integer offset, Integer limit);

    Integer countLedgerTransaction(String filter);

    Integer countLedgerTransaction(CfLedger ledger);

    Integer countLedgerTransaction(CfLedger ledger, String filter);

}
