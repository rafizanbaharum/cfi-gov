package net.canang.cfi.web.listener;

import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author rafizan.baharum
 * @since 10/20/13
 */
public class Log4jCleanupListener implements ServletContextListener {
    public void contextDestroyed(final ServletContextEvent sce) {
        LogFactory.release(Thread.currentThread().getContextClassLoader());
    }

    public void contextInitialized(final ServletContextEvent sce) {
    }
}
