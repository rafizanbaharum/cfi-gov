package net.canang.cfi.core.gl.dao;

import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.gl.model.CfLedger;
import net.canang.cfi.core.gl.model.CfLedgerTransaction;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfLedgerDao {

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    CfLedger newInstance();

    CfLedgerTransaction newTransactionInstance();

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    CfLedger findById(Long id);

    CfLedger findByReferenceNo(String referenceNo);

    List<CfLedger> findByCostCenter(CfCostCenter costCenter);

    CfLedgerTransaction findTransactionById(Long id);

    List<CfLedger> find(Integer offset, Integer limit);

    List<CfLedger> find(String filter, Integer offset, Integer limit);

    List<CfLedgerTransaction> findTransactions(Integer offset, Integer limit);

    List<CfLedgerTransaction> findTransactions(String filter, Integer offset, Integer limit);

    List<CfLedgerTransaction> findTransactions(CfLedger ledger);

    List<CfLedgerTransaction> findTransactions(CfLedger ledger, Integer offset, Integer limit);

    List<CfLedgerTransaction> findTransactions(CfLedger ledger, String filter, Integer offset, Integer limit);

    Integer count();

    Integer count(String filter);

    Integer countTransaction(String filter);

    Integer countTransaction(CfLedger ledger);

    Integer countTransaction(CfLedger ledger, String filter);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    CfLedger save(CfLedger ledger, CfUser user);

    CfLedger update(CfLedger ledger, CfUser user);

    CfLedger deactivate(CfLedger ledger, CfUser user);

    void remove(CfLedger ledger, CfUser user);

    void addTransaction(CfLedger ledger, CfLedgerTransaction ltx, CfUser user);

    void addTransactions(CfLedger ledger, List<CfLedgerTransaction> ltxs, CfUser user);
}
