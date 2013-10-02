package net.canang.cfi.core.bm.dao;

import net.canang.cfi.core.bm.model.CfControl;
import net.canang.cfi.core.bm.model.CfControlItem;
import net.canang.cfi.core.bm.model.CfControlType;
import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.so.model.CfMetaState;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/2/13
 */
public interface CfControlDao {

    // finders

    CfControl newInstance(CfControlType type);

    CfControl findById(Long id);

    CfControlItem findItemById(Long id);

    CfControlItem findItemByCode(String code, CfControl control);

    CfControl findByReferenceNo(String referenceNo);

    CfControl findBySourceNo(String sourceNo);

    List<CfControl> findManyBySourceNo(String sourceNo);

    List<CfControl> find(Integer offset, Integer limit);

    List<CfControlItem> findItems(CfControl control);

    List<CfControlItem> findItems(CfControl control, Integer level);

    List<CfControlItem> findItems(CfControl control, Integer offset, Integer limit);

    List<CfControlItem> findItems(CfControl control, Integer level, Integer offset, Integer limit);

    List<CfControlItem> findItemChildren(CfControlItem parent);

    List<CfControlItem> findItemChildren(CfControlItem parent, Integer offset, Integer limit);

    Integer count();

    Integer count(CfPeriod source, CfPeriod sink, CfControlType type);

    Integer count(CfPeriod source, CfPeriod sink, CfControlType type, String filter);

    Integer countItem(CfControl control);

    Integer countChildren(CfControlItem controlItem);

    boolean isExists(CfPeriod period, CfControlType type);

    boolean isExists(CfControl control, CfControlType type);

    boolean isExists(CfControl control, CfControlType type, CfMetaState metaState);

    // cruds

    void save(CfControl control, CfUser user);

    void update(CfControl control, CfUser user);

    void deactivate(CfControl control, CfUser user);

    void remove(CfControl control, CfUser user);

    // crud 

    void addItem(CfPeriod period, CfControl control, CfControlItem item, CfUser user);

    void addItems(CfPeriod period, CfControl control, List<CfControlItem> items, CfUser user);

    void updateItem(CfControl control, CfControlItem item, CfUser user);

    void updateItems(CfControl control, List<CfControlItem> items, CfUser user);

    void removeItem(CfControl control, CfControlItem item, CfUser user);

    void removeItems(CfControl control, List<CfControlItem> items, CfUser user);

}
