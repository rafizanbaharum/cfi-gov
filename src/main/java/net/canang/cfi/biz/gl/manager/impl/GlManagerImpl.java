package net.canang.cfi.biz.gl.manager.impl;

import net.canang.cfi.biz.Util;
import net.canang.cfi.biz.gl.manager.GlManager;
import net.canang.cfi.core.gl.dao.CfLedgerDao;
import net.canang.cfi.core.gl.model.CfLedger;
import net.canang.cfi.core.gl.model.CfLedgerTransaction;
import net.canang.cfi.core.so.model.CfDocument;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public class GlManagerImpl implements GlManager {

    private static final Logger log = Logger.getLogger(GlManagerImpl.class);

    // TODO
//    @Autowired
//    private LedgerPostProcessor ledgerPostProcessor;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CfLedgerDao ledgerDao;

    @Override
    public void saveLedger(CfLedger ledger) {
        ledgerDao.save(ledger, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateLedger(CfLedger ledger) {
        ledgerDao.update(ledger, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateLedgerTransaction(CfLedger ledger, CfLedgerTransaction transaction) {
        // TODO:

    }

    @Override
    public void post(CfDocument document) throws Exception {
//        ledgerPostProcessor.process(new LedgerContext(document));
    }
}
