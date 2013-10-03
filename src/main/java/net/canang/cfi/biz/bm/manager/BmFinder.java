package net.canang.cfi.biz.bm.manager;

import net.canang.cfi.core.bm.model.CfControl;
import net.canang.cfi.core.bm.model.CfControlItem;
import net.canang.cfi.core.bm.model.CfControlType;
import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.so.model.CfMetaState;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public interface BmFinder {

    // ==================================================================================================== //
    // FINDER METHODS
    // ==================================================================================================== //

    CfControl findControlById(Long id);

    CfControlItem findControlItemById(Long id);

    CfControl findControlByReferenceNo(String referenceNo);

    CfControl findControlBySourceNo(String sourceNo);

    List<CfControl> findControlsBySourceNo(String sourceNo);

    List<CfControl> findControls(Integer offset, Integer limit);

    List<CfControlItem> findControlItems(CfControl control);

    List<CfControlItem> findControlItems(CfControl control, boolean voteDetailed);

    List<CfControlItem> findControlItems(CfControl control, Integer level);

    List<CfControlItem> findControlItems(CfControl control, Integer offset, Integer limit);

    List<CfControlItem> findControlItems(CfControl control, Integer level, Integer offset, Integer limit);

    List<CfControlItem> findControlItemChildren(CfControlItem item);

    List<CfControlItem> findControlItemChildren(CfControlItem item, Integer offset, Integer limit);

    // ==================================================================================================== //
    // COUNT METHODS
    // ==================================================================================================== //

    Integer countControlItem(CfControl control);

    // ==================================================================================================== //
    // HELPER METHODS
    // ==================================================================================================== //

    boolean isExist(CfPeriod Period, CfControlType type);

    boolean isExist(CfControl control, CfControlType type);

    boolean isExist(CfControl control, CfControlType type, CfMetaState state);
}
