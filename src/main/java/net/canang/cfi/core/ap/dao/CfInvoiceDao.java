package net.canang.cfi.core.ap.dao;

import net.canang.cfi.core.ap.model.CfInvoice;
import net.canang.cfi.core.ap.model.CfInvoiceItem;
import net.canang.cfi.core.ap.model.CfInvoiceType;
import net.canang.cfi.core.ap.model.CfPayableTransaction;
import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.so.model.CfFlowState;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfInvoiceDao {

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    CfInvoice newInstance(CfInvoiceType type);

    CfInvoiceItem newItemInstance();

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    CfInvoice findById(Long id);

    CfInvoiceItem findItemById(Long id);

    CfPayableTransaction findTransactionById(Long id);

    CfInvoice findByReferenceNo(String referenceNo);

    List<CfInvoice> find(CfInvoiceType type);

    List<CfInvoice> findByRequester(CfInvoiceType type, CfCostCenter costCenter);

    List<CfInvoice> findByRequester(CfInvoiceType type, CfCostCenter costCenter, Integer offset, Integer limit);

    List<CfInvoice> findByRequester(CfInvoiceType type, CfPeriod period, CfCostCenter costCenter);

    List<CfInvoice> findByRequester(CfInvoiceType type, CfPeriod period, CfCostCenter costCenter, Integer offset, Integer limit);

    List<CfInvoice> findByPeriod(CfInvoiceType type, CfCostCenter costCenter);

    List<CfInvoice> findByPeriod(CfInvoiceType type, CfCostCenter costCenter, Integer offset, Integer limit);

    List<CfInvoice> findByPeriod(CfInvoiceType type, CfPeriod period);

    List<CfInvoice> findByPeriod(CfInvoiceType type, CfPeriod period, Integer offset, Integer limit);

    List<CfInvoice> find(CfInvoiceType type, Integer offset, Integer limit);

    List<CfInvoice> find(CfInvoiceType type, CfFlowState flowState, Integer offset, Integer limit);

    List<CfInvoice> find(CfInvoiceType type, String filter, Integer offset, Integer limit);

    List<CfInvoiceItem> findItems(CfInvoice invoice);

    List<CfInvoiceItem> findItems(CfInvoice invoice, Integer offset, Integer limit);

    Integer count(CfInvoiceType type);

    Integer count(CfInvoiceType type, String filter);

    Integer countByRequester(CfInvoiceType type, CfCostCenter costCenter);

    Integer countByPeriod(CfInvoiceType type, CfCostCenter costCenter);

    Integer countByRequester(CfInvoiceType type, CfPeriod period, CfCostCenter costCenter);

    Integer countByPeriod(CfInvoiceType type, CfPeriod period);

    Integer countItem(CfInvoice invoice);

    Integer countTransaction(CfInvoice invoice);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    CfInvoice save(CfInvoice invoice, CfUser user);

    CfInvoice update(CfInvoice invoice, CfUser user);

    CfInvoice deactivate(CfInvoice invoice, CfUser user);

    void remove(CfInvoice invoice, CfUser user);

    void addItem(CfInvoice invoice, CfInvoiceItem item, CfUser user);

    void addItems(CfInvoice invoice, List<CfInvoiceItem> items, CfUser user);

    void updateItem(CfInvoice invoice, CfInvoiceItem item, CfUser user);

    void updateItems(CfInvoice invoice, List<CfInvoiceItem> items, CfUser user);

    void removeItem(CfInvoice invoice, CfInvoiceItem item, CfUser user);

    void removeItems(CfInvoice invoice, List<CfInvoiceItem> items, CfUser user);

}
