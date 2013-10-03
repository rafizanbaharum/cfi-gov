package net.canang.cfi.biz.gl.manager;

import net.canang.cfi.core.gl.model.CfLedger;
import net.canang.cfi.core.gl.model.CfLedgerTransaction;
import net.canang.cfi.core.so.model.CfDocument;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public interface GlManager {

    void saveLedger(CfLedger ledger);

    void updateLedger(CfLedger ledger);

    void updateLedgerTransaction(CfLedger ledger, CfLedgerTransaction transaction);

    void post(CfDocument document) throws Exception;
}
