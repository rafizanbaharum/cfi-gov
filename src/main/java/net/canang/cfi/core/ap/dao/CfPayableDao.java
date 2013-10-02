package net.canang.cfi.core.ap.dao;

import net.canang.cfi.core.ap.model.CfPayable;
import net.canang.cfi.core.ap.model.CfPayableTransaction;
import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.dm.model.CfVoucher;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfPayableDao {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    CfPayable findPayableById(Long id);

    CfPayable findPayableByReferenceNo(String referenceNo);

    CfVoucher findVoucherByPayable(CfPayable payable);

    CfPayableTransaction findTransactionById(Long id);

    List<CfPayable> findPayables(CfCostCenter request);

    List<CfPayable> findPayables(CfCostCenter request, Integer offset, Integer limit);

    List<CfPayableTransaction> findTransactions(CfPayable payable);

    List<CfPayableTransaction> findTransactions(CfPayable payable, Integer offset, Integer limit);

    Integer countPayable(CfCostCenter request);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    void addTransaction(CfPayable payable, CfPayableTransaction transaction, CfUser user);

    void addTransactions(CfPayable payable, List<CfPayableTransaction> transactions, CfUser user);

    void updateTransaction(CfPayable payable, CfPayableTransaction transaction, CfUser user);

    void updateTransactions(CfPayable payable, List<CfPayableTransaction> transactions, CfUser user);

    void removeTransaction(CfPayable payable, CfPayableTransaction transaction, CfUser user);

    void removeTransactions(CfPayable payable, List<CfPayableTransaction> transactions, CfUser user);

}
