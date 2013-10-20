package net.canang.cfi.web.listener;

/**
 * @author rafizan.baharum
 * @since 10/20/13
 */

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.reflect.Field;
import java.net.URLClassLoader;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Finds Finalizer threads and removes any "bad" references.
 * Look at <a href="http://code.google.com/p/google-collections/issues/detail?id=92">Issue 92</a>.
 * <b>Note: This is not intended for production use.</b>
 */
public class FinalizerLeakPatchListener implements ServletContextListener {

    public static final String FINALIZER_CLASS = "com.google.common.base.internal.Finalizer";
    public static final String GUICE_FINALIZER_CLASS = "com.google.inject.internal.Finalizer";

    private static final Field accessControlContext = getThreadAccField();
    private static final Field urlClassLoaderAccessControlContext = getUrlClAccField();
    // this is only for Guice Finalizer (it has an older copy of Finalizer from GCollect)
    private static final Field inheritableThreadLocals = getInheritableThreadLocalsField();
    private static final Logger logger = Logger.getLogger(FinalizerLeakPatchListener.class.getName());

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        /* Nothing to do here. */
    }


    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                try {
                    visitAll(new ThreadVisitor() {
                        public void visit(Thread thread) {
                            String name = thread.getClass().getName();
                            if (FINALIZER_CLASS.equals(name) || GUICE_FINALIZER_CLASS.equals(name)) {

                                /* Strike one
                                * Get rid of ContextClassLoader. Just in case it was set by some odd coincidence.
                                * Should be unnecessary.
                                */
                                thread.setContextClassLoader(null);

                                /* Strike two
                                * Get rid of AccessControlContext
                                */
                                try {
                                    if (accessControlContext != null) {
                                        accessControlContext.set(thread, null);
                                    }
                                } catch (Throwable t) {
                                    logger.log(Level.SEVERE, "Failed to clear thread access control context."
                                            + "Memory leak is still present.", t);
                                }

                                /* Strike three
                                * Get rid of AccessControlContext on URLClassLoader
                                * If this was loaded in DirectLoader maybe we can try and interrupt the thread?
                                * The finalizer doesn't care about interrupts
                                * If using Thread#Stop God would surely kill a Kitten.
                                */
                                ClassLoader threadClassLoader = thread.getClass().getClassLoader();
                                if (threadClassLoader instanceof URLClassLoader) {
                                    try {
                                        if (urlClassLoaderAccessControlContext != null) {
                                            urlClassLoaderAccessControlContext.set(threadClassLoader, null);
                                        }
                                    } catch (Throwable t) {
                                        logger.log(Level.SEVERE, "Failed to clear url class loader access control context."
                                                + "Memory leak is still present.", t);
                                    }
                                }

                                /* Strike three and a half
                                * For Guice's Finalizer
                                */
                                try {
                                    if (inheritableThreadLocals != null) {
                                        inheritableThreadLocals.set(thread, null);
                                    }
                                } catch (Throwable t) {
                                    logger.log(Level.SEVERE, "Failed to clear thread local values inherited"
                                            + " Memory leak is still present.", t);
                                }

                                /* And you're out! */
                            }

                        }
                    });
                } catch (SecurityException ex) {
                    logger.log(Level.SEVERE, "Failed to patch any Finalizer threads due to Security restrictions.",
                            ex);
                }
                return null;
            }
        });

    }


    private static Field getThreadAccField() {
        Field[] declaredFields = Thread.class.getDeclaredFields();

        for (Field declaredField : declaredFields) {
            if (AccessControlContext.class == declaredField.getType()) {
                declaredField.setAccessible(true);
                return declaredField;
            }
        }
        logger.log(Level.SEVERE, "Failed to get Thread#AccessControlerField field. Patcher is useless.");
        return null;
    }

    private static Field getUrlClAccField() {
        try {
            Field field = URLClassLoader.class.getDeclaredField("acc");
            field.setAccessible(true);
            return field;
        } catch (Throwable e) {
            logger.log(Level.SEVERE, "Failed to get URLClassLoader Acc field. Patcher is useless.");
        }
        return null;
    }

    private static Field getInheritableThreadLocalsField() {
        try {
            Field inheritableThreadLocals = Thread.class
                    .getDeclaredField("inheritableThreadLocals");
            inheritableThreadLocals.setAccessible(true);
            return inheritableThreadLocals;
        } catch (Throwable t) {
            logger.log(Level.SEVERE, "Couldn't access Thread.inheritableThreadLocals."
                    + " Patcher is useless.");
            return null;
        }
    }


    /* Cut and paste with slight modification from http://www.exampledepot.com/egs/java.lang/ListThreads.html */
    private static void visitAll(ThreadVisitor visitor) throws SecurityException {
        ThreadGroup root = Thread.currentThread().getThreadGroup().getParent();
        while (root.getParent() != null) {
            root = root.getParent();
        }

        visit(root, visitor);
    }

    private interface ThreadVisitor {
        void visit(Thread thread);
    }

    private static void visit(ThreadGroup group, ThreadVisitor visitor) {
        int numThreads = group.activeCount();
        Thread[] threads = new Thread[numThreads * 2];
        numThreads = group.enumerate(threads, false);

        for (int i = 0; i < numThreads; i++) {
            visitor.visit(threads[i]);
        }

        int numGroups = group.activeGroupCount();
        ThreadGroup[] groups = new ThreadGroup[numGroups * 2];
        numGroups = group.enumerate(groups, false);

        for (int i = 0; i < numGroups; i++) {
            visit(groups[i], visitor);
        }
    }
}