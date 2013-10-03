package net.canang.cfi.biz.bm.manager;

import net.canang.cfi.core.bm.model.CfControl;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public interface BmFinder {
    CfControl findControlById(Long controlId);
}
