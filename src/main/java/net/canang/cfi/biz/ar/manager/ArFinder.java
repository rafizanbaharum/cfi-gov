package net.canang.cfi.biz.ar.manager;

import net.canang.cfi.core.ar.model.CfReceipt;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public interface ArFinder {
    public CfReceipt findReceiptById(Long id);
}
