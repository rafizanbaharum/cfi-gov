package net.canang.cfi.core.so;

import junit.framework.Assert;
import net.canang.cfi.biz.config.CfBizConfig;
import net.canang.cfi.biz.dd.manager.DdFinder;
import net.canang.cfi.biz.jm.manager.workflow.JournalWorkflow;
import net.canang.cfi.core.config.CfCoreConfig;
import net.canang.cfi.core.dd.dao.CfCostCenterDao;
import net.canang.cfi.core.dd.dao.CfSodoCodeDao;
import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.jm.model.CfJournalTransaction;
import net.canang.cfi.core.jm.model.CfManualJournal;
import net.canang.cfi.core.jm.model.impl.CfJournalTransactionImpl;
import net.canang.cfi.core.jm.model.impl.CfManualJournalImpl;
import net.canang.cfi.core.so.dao.CfPrincipalDao;
import net.canang.cfi.core.so.dao.CfUserDao;
import net.canang.cfi.core.so.model.CfPrincipalType;
import net.canang.cfi.core.so.model.CfUser;
import net.canang.cfi.core.so.model.impl.CfUserImpl;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CfCoreConfig.class})
public class CfPrincipalTest extends AbstractTransactionalJUnit4SpringContextTests {

    private Logger log = LoggerFactory.getLogger(CfPrincipalTest.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CfUserDao userDao;

    private CfUser root;

    @Before
    public void setUp() {
        root = userDao.findByUsername("root");
    }

    @Test
    @Rollback(value = true)
    public void createUser() {
        CfUser user = new CfUserImpl();
        user.setName("root2");
        user.setPassword("abc123");
        user.setPrincipalType(CfPrincipalType.USER);
        user.setEmail("root@canangtech.my");
        user.setRealname("Root 2");
        user.setActor(null);

        userDao.save(user, root);

        CfUser root2 = userDao.findByUsername("root2");
        Assert.assertEquals("root2", root2.getUsername());
    }
}
