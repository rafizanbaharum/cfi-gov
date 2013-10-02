package net.canang.cfi.core.dm.dao;

import net.canang.cfi.core.dd.model.CfCampusCode;
import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.dd.model.CfPaymentMethod;
import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.dm.model.CfVoucher;
import net.canang.cfi.core.dm.model.CfVoucherRecipient;
import net.canang.cfi.core.dm.model.CfVoucherTransaction;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfVoucherDao {

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    CfVoucher newInstance();

    CfVoucherRecipient newRecipientInstance();

    CfVoucherTransaction newTransactionInstance();

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    CfVoucher findById(Long id);

    CfVoucherRecipient findRecipientById(Long id);

    CfVoucherTransaction findTransactionById(Long id);

    CfVoucher findByReferenceNo(String referenceNo);

    CfVoucher findBySourceNo(String sourceNo);

    List<CfVoucher> findByPeriod(CfPeriod period);

    List<CfVoucher> findByPeriod(CfPeriod period, Integer offset, Integer limit);

    List<CfVoucher> findByCostCenter(CfCostCenter costCenter, Integer offset, Integer limit);

    List<CfVoucher> findByCostCenter(CfCostCenter costCenter);

    List<CfVoucher> find();

    List findTransactionSummary(CfVoucher voucher);

    List<CfVoucher> find(Integer offset, Integer limit);

    List<CfVoucher> find(String filter, Integer offset, Integer limit);

    List<CfVoucherRecipient> findRecipients(CfVoucher voucher);

    List<CfVoucherRecipient> findRecipients(CfVoucher voucher, Integer offset, Integer limit);

    List<CfVoucherTransaction> findTransactions(CfVoucher voucher);

    List<CfVoucherTransaction> findTransactions(CfVoucher voucher, Integer offset, Integer limit);

    List<CfVoucherTransaction> findTransactions(CfVoucherRecipient recipient);

    List<CfVoucherTransaction> findTransactions(CfVoucherRecipient recipient, Integer offset, Integer limit);

    List<CfVoucherRecipient> findDisbursedRecipients(Integer offset, Integer limit);

    List<CfVoucherRecipient> findUndisbursedRecipients(Integer offset, Integer limit);

    List<CfVoucherRecipient> findUndisbursedRecipients(String filter, CfPaymentMethod paymentMethod, CfCampusCode campus);

    List<CfVoucherRecipient> findUndisbursedRecipients(CfPaymentMethod paymentMethod, CfCampusCode campus);

    Integer count();

    Integer count(String filter);

    Integer countByPeriod(CfPeriod period);

    Integer countRecipient(CfVoucher voucher);

    Integer countDisbursedRecipient();

    Integer countUndisbursedRecipient();

    Integer countTransaction(CfVoucher voucher);

    Integer countTransaction(CfVoucherRecipient recipient);


    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    CfVoucher save(CfVoucher voucher, CfUser user);

    CfVoucher update(CfVoucher voucher, CfUser user);

    CfVoucher deactivate(CfVoucher voucher, CfUser user);

    void remove(CfVoucher voucher, CfUser user);

    void addRecipient(CfVoucher voucher, CfVoucherRecipient recipient, CfUser user);

    void addRecipients(CfVoucher voucher, List<CfVoucherRecipient> recipients, CfUser user);

    void updateRecipient(CfVoucher voucher, CfVoucherRecipient recipient, CfUser user);

    void updateRecipients(CfVoucher voucher, List<CfVoucherRecipient> recipients, CfUser user);

    void removeRecipient(CfVoucher voucher, CfVoucherRecipient recipient, CfUser user);

    void removeRecipients(CfVoucher voucher, List<CfVoucherRecipient> recipients, CfUser user);

    void addTransaction(CfVoucherRecipient recipient, CfVoucherTransaction transaction, CfUser user);

    void addTransactions(CfVoucherRecipient recipient, List<CfVoucherTransaction> transactions, CfUser user);

    void updateTransaction(CfVoucherRecipient recipient, CfVoucherTransaction transaction, CfUser user);

    void updateTransactions(CfVoucherRecipient recipient, List<CfVoucherTransaction> transactions, CfUser user);

    void removeTransaction(CfVoucherRecipient recipient, CfVoucherTransaction transaction, CfUser user);

    void removeTransactions(CfVoucherRecipient recipient, List<CfVoucherTransaction> transactions, CfUser user);

}
