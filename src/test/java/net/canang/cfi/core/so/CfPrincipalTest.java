package net.canang.cfi.core.so;

import junit.framework.Assert;
import net.canang.cfi.core.config.CfCoreConfig;
import net.canang.cfi.core.so.dao.CfUserDao;
import net.canang.cfi.core.so.model.CfUser;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    public void findUser() {
        CfUser root = userDao.findByUsername("root");
        Assert.assertEquals("root", root.getUsername());

        List<CfUser> cfUsers = userDao.find(0, 100);
        log.debug("size: " + cfUsers.size());
        for (CfUser cfUser : cfUsers) {
            log.debug("user: " + cfUser);
        }
    }
}
