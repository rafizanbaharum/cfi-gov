package net.canang.cfi.biz.jm;

import net.canang.cfi.biz.config.CfBizConfig;
import net.canang.cfi.biz.config.CfBizSecurityConfig;
import net.canang.cfi.biz.dd.manager.DdFinder;
import net.canang.cfi.biz.jm.manager.JmManager;
import net.canang.cfi.biz.jm.manager.workflow.JournalTask;
import net.canang.cfi.biz.jm.manager.workflow.JournalWorkflow;
import net.canang.cfi.core.dd.dao.CfCostCenterDao;
import net.canang.cfi.core.dd.dao.CfSodoCodeDao;
import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.jm.model.CfJournalTransaction;
import net.canang.cfi.core.jm.model.CfJournalType;
import net.canang.cfi.core.jm.model.CfManualJournal;
import net.canang.cfi.core.jm.model.impl.CfJournalTransactionImpl;
import net.canang.cfi.core.jm.model.impl.CfManualJournalImpl;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/4/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CfBizConfig.class, CfBizSecurityConfig.class})
public class JournalTest extends AbstractTransactionalJUnit4SpringContextTests {

    private Logger log = LoggerFactory.getLogger(JournalTest.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DdFinder ddFinder;

    @Autowired
    private CfCostCenterDao costCenterDao;

    @Autowired
    private CfSodoCodeDao sodoCodeDao;

    @Autowired
    private JournalWorkflow workflow;

    @Autowired
    private JmManager jmManager;

    @Autowired(required = true)
    private AuthenticationManager authenticationManager;

    @Before
    public void setUp() {
        log.debug("logging in user");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("root", "abc123");
        Authentication authed = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authed);
    }

    @Test
    @Rollback(value = false)
    public void testJournal() {
        CfCostCenter costCenter = costCenterDao.findByCode("U.K070000.0100.0000");
        CfManualJournal journal = new CfManualJournalImpl();
        journal.setDescription("MANUAL JOURNAL");
        journal.setRequester(costCenter);
        journal.setTotalAmount(new BigDecimal(0.00));

        // drafted
        workflow.process(journal);

        List<CfJournalTransaction> txs = new ArrayList<CfJournalTransaction>();
        CfJournalTransaction tx1 = new CfJournalTransactionImpl();
        tx1.setPeriod(costCenter.getCurrentPeriod());
        tx1.setSodoCode(sodoCodeDao.findByCode("B21000"));
        tx1.setAmount(new BigDecimal(10.00));
        txs.add(tx1);

        // journal item 2
        CfJournalTransaction tx2 = new CfJournalTransactionImpl();
        tx2.setPeriod(costCenter.getCurrentPeriod());
        tx2.setSodoCode(sodoCodeDao.findByCode("B22000"));
        tx2.setAmount(new BigDecimal(10.00).negate());
        txs.add(tx2);
        jmManager.addJournalTransactions(journal, txs);

        List<JournalTask> tasks = workflow.findTasks(CfJournalType.MANUAL, 0, 100);
        log.debug("found {} tasks", tasks.size());
        for (JournalTask task : tasks) {
            workflow.completeTask(task);
        }
    }
}
