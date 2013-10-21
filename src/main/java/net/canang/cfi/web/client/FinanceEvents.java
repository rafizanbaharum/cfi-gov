package net.canang.cfi.web.client;

import com.extjs.gxt.ui.client.event.EventType;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
public class FinanceEvents {

    public static final EventType InitApplicationModule = new EventType();
    public static final EventType InitApplicationView = new EventType();
    public static final EventType InitApplicationModel = new EventType();
    public static final EventType InitApplicationController = new EventType();
    public static final EventType ApplicationError = new EventType();
    public static final EventType ApplicationLogout = new EventType();
    public static final EventType ApplicationTimeout = new EventType();

}
