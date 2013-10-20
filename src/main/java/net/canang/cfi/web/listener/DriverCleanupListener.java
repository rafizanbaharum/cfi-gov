package net.canang.cfi.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.beans.Introspector;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

/**
 * @author rafizan.baharum
 * @since 10/20/13
 */
public class DriverCleanupListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
    }

    public void contextDestroyed(ServletContextEvent event) {
        try {
            Introspector.flushCaches();
            for (Enumeration e = DriverManager.getDrivers(); e.hasMoreElements(); ) {
                Driver driver = (Driver) e.nextElement();
                if (driver.getClass().getClassLoader() == getClass().getClassLoader()) {
                    DriverManager.deregisterDriver(driver);
                }
            }
        } catch (Throwable e) {
            System.err.println("Failled to cleanup ClassLoader for webapp");
            e.printStackTrace();
        }
    }
}
