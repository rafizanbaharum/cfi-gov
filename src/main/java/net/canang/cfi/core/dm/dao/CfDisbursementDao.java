package net.canang.cfi.core.dm.dao;

import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.dm.model.CfDisbursement;
import net.canang.cfi.core.dm.model.CfDisbursementItem;
import net.canang.cfi.core.dm.model.CfDisbursementMethod;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfDisbursementDao {

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    CfDisbursement newInstance();

    CfDisbursementItem newItemInstance();

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    CfDisbursement findById(Long id);

    CfDisbursementItem findItemById(Long id);

    CfDisbursement findByReferenceNo(String referenceNo);

    List<CfDisbursement> find(Integer offset, Integer limit);

    List<CfDisbursement> find(CfDisbursementMethod method, CfCostCenter costCenter);

    List<CfDisbursement> find(CfDisbursementMethod method, CfCostCenter costCenter, Integer offset, Integer limit);

    List<CfDisbursement> find(CfDisbursementMethod method, Integer offset, Integer limit);

    List<CfDisbursement> find(String filter, CfDisbursementMethod method, Integer offset, Integer limit);

    List<CfDisbursement> find(CfDisbursementMethod method, String filter, Integer offset, Integer limit);

    List<CfDisbursementItem> findItems(CfDisbursement disbursement);

    List<CfDisbursementItem> findItems(CfDisbursement disbursement, Integer offset, Integer limit);

    Integer count();

    Integer count(CfDisbursementMethod method);

    Integer count(CfDisbursementMethod method, String filter);

    Integer countItem(CfDisbursement disbursement);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    CfDisbursement save(CfDisbursement disbursement, CfUser user);

    CfDisbursement update(CfDisbursement disbursement, CfUser user);

    CfDisbursement deactivate(CfDisbursement disbursement, CfUser user);

    void remove(CfDisbursement disbursement, CfUser user);

    void addItem(CfDisbursement disbursement, CfDisbursementItem item, CfUser user);

    void addItems(CfDisbursement disbursement, List<CfDisbursementItem> items, CfUser user);

    void updateItem(CfDisbursement disbursement, CfDisbursementItem item, CfUser user);

    void updateItems(CfDisbursement disbursement, List<CfDisbursementItem> items, CfUser user);

    void removeItem(CfDisbursement disbursement, CfDisbursementItem item, CfUser user);

    void removeItems(CfDisbursement disbursement, List<CfDisbursementItem> items, CfUser user);
}