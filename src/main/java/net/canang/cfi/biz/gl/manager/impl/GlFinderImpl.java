package net.canang.cfi.biz.gl.manager.impl;

import net.canang.cfi.biz.gl.manager.GlFinder;
import net.canang.cfi.core.gl.dao.CfLedgerDao;
import net.canang.cfi.core.gl.model.CfLedger;
import net.canang.cfi.core.gl.model.CfLedgerTransaction;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
@Service("glFinder")
public class GlFinderImpl implements GlFinder {

    private static final Logger log = Logger.getLogger(GlFinderImpl.class);

    @Autowired
    private CfLedgerDao ledgerDao;

    @Override
    public CfLedger findLedgerById(Long id) {
        return ledgerDao.findById(id);
    }

    @Override
    public CfLedger findLedgerByReferenceNo(String referenceNo) {
        return ledgerDao.findByReferenceNo(referenceNo);
    }

    @Override
    public List<CfLedger> findLedgers(Integer offset, Integer limit) {
        return ledgerDao.find(offset, limit);
    }

    @Override
    public List<CfLedger> findLedgers(String filter, Integer offset, Integer limit) {
        return ledgerDao.find(filter, offset, limit);
    }

    @Override
    public Integer countLedger() {
        return ledgerDao.count();
    }

    @Override
    public Integer countLedger(String filter) {
        return ledgerDao.count(filter);
    }

    @Override
    public CfLedgerTransaction findLedgerTransactionById(Long id) {
        return ledgerDao.findTransactionById(id);
    }

    @Override
    public List<CfLedgerTransaction> findLedgerTransactions(Integer offset, Integer limit) {
        return ledgerDao.findTransactions(offset, limit);
    }

    @Override
    public List<CfLedgerTransaction> findLedgerTransactions(CfLedger ledger) {
        return ledgerDao.findTransactions(ledger);
    }

    @Override
    public List<CfLedgerTransaction> findLedgerTransactions(CfLedger ledger, Integer offset, Integer limit) {
        return ledgerDao.findTransactions(ledger, offset, limit);
    }

    @Override
    public List<CfLedgerTransaction> findLedgerTransactions(String filter, Integer offset, Integer limit) {
        return ledgerDao.findTransactions(filter, offset, limit);
    }

    @Override
    public List<CfLedgerTransaction> findLedgerTransactions(CfLedger ledger, String filter, Integer offset, Integer limit) {
        return ledgerDao.findTransactions(ledger, filter, offset, limit);
    }

    @Override
    public Integer countLedgerTransaction(String filter) {
        return ledgerDao.countTransaction(filter);
    }

    @Override
    public Integer countLedgerTransaction(CfLedger ledger) {
        return ledgerDao.countTransaction(ledger);
    }

    @Override
    public Integer countLedgerTransaction(CfLedger ledger, String filter) {
        return ledgerDao.countTransaction(ledger, filter);
    }
}
