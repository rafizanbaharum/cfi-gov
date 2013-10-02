package net.canang.cfi.core.ar.dao;

import net.canang.cfi.core.ar.model.CfReceipt;
import net.canang.cfi.core.ar.model.CfReceiptItem;
import net.canang.cfi.core.ar.model.CfReceiptPayment;
import net.canang.cfi.core.ar.model.CfReceiptType;
import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfReceiptDao extends CfReceivableDao {

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    CfReceipt newInstance(CfReceiptType type);

    CfReceiptItem newItemInstance();

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    CfReceipt findById(Long id);

    CfReceiptItem findItemById(Long id);

    CfReceiptPayment findPaymentById(Long id);

    CfReceipt findByReferenceNo(String referenceNo);

    CfReceipt findBySourceNo(String sourceNo);

    List<CfReceipt> findManyBySourceNo(String sourceNo);

    CfReceipt findByReceiptNo(String sourceNo);

    CfReceipt refresh(CfReceipt receipt);

    List<CfReceipt> find(CfReceiptType type, CfCostCenter costCenter);

    List<CfReceipt> find(CfReceiptType type, CfCostCenter costCenter, Integer offset, Integer limit);

    List<CfReceipt> find(CfReceiptType type, Integer offset, Integer limit);

    List<CfReceipt> find(CfReceiptType type, String filter, Integer offset, Integer limit);

    List<CfReceiptItem> findItems(CfReceiptPayment payment);

    List<CfReceiptItem> findItems(CfReceiptPayment payment, Integer offset, Integer limit);

    List<CfReceiptPayment> findPayments(CfReceipt receipt);

    List<CfReceiptPayment> findPayments(CfReceipt receipt, Integer offset, Integer limit);

    Integer count(CfReceiptType type);

    Integer count(CfReceiptType type, CfCostCenter costCenter);

    Integer count(CfReceiptType type, String filter);

    Integer countPayment(CfReceipt receipt);

    Integer countItem(CfReceiptPayment payment);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    CfReceipt save(CfReceipt receipt, CfUser user);

    CfReceipt update(CfReceipt receipt, CfUser user);

    CfReceipt deactivate(CfReceipt receipt, CfUser user);

    void remove(CfReceipt receipt, CfUser user);


    void addPayment(CfReceipt receipt, CfReceiptPayment payment, CfUser user);

    void addPayments(CfReceipt receipt, List<CfReceiptPayment> payments, CfUser user);

    void updatePayment(CfReceipt receipt, CfReceiptPayment payment, CfUser user);

    void updatePayments(CfReceipt receipt, List<CfReceiptPayment> payments, CfUser user);

    void removePayment(CfReceipt receipt, CfReceiptPayment payment, CfUser user);

    void removePayments(CfReceipt receipt, List<CfReceiptPayment> payments, CfUser user);

    void addItem(CfReceiptPayment payment, CfReceiptItem item, CfUser user);

    void addItems(CfReceiptPayment payment, List<CfReceiptItem> items, CfUser user);

    void updateItem(CfReceiptPayment payment, CfReceiptItem item, CfUser user);

    void updateItems(CfReceiptPayment payment, List<CfReceiptItem> items, CfUser user);

    void removeItem(CfReceiptPayment payment, CfReceiptItem item, CfUser user);

    void removeItems(CfReceiptPayment payment, List<CfReceiptItem> items, CfUser user);
}
