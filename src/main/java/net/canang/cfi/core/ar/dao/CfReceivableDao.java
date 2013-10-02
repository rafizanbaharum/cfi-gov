package net.canang.cfi.core.ar.dao;

import net.canang.cfi.core.ar.model.CfReceivable;
import net.canang.cfi.core.ar.model.CfReceivableTransaction;
import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfReceivableDao {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    CfReceivable findReceivableById(Long id);

    CfReceivable findReceivableByReferenceNo(String referenceNo);

    CfReceivableTransaction findTransactionById(Long id);

    List<CfReceivable> findReceivables(CfCostCenter request);

    List<CfReceivable> findReceivables(CfCostCenter request, Integer offset, Integer limit);

    List<CfReceivableTransaction> findTransactions(CfReceivable payable);

    List<CfReceivableTransaction> findTransactions(CfReceivable payable, Integer offset, Integer limit);

    Integer countReceivable(CfCostCenter request);

    Integer countTransaction(CfReceivable receivable);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    void addTransaction(CfReceivable payable, CfReceivableTransaction transaction, CfUser user);

    void addTransactions(CfReceivable payable, List<CfReceivableTransaction> transactions, CfUser user);

    void updateTransaction(CfReceivable payable, CfReceivableTransaction transaction, CfUser user);

    void updateTransactions(CfReceivable payable, List<CfReceivableTransaction> transactions, CfUser user);

    void removeTransaction(CfReceivable payable, CfReceivableTransaction transaction, CfUser user);

    void removeTransactions(CfReceivable payable, List<CfReceivableTransaction> transactions, CfUser user);

}
