package net.canang.cfi.web.client.controller;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import net.canang.cfi.web.client.FinanceEvents;
import net.canang.cfi.web.client.view.FinanceView;

import java.util.logging.Logger;

/**
 * @author rafizan.baharum
 * @since 10/20/13
 */
public class FinanceController extends Controller {

    private static final Logger log = Logger.getLogger(FinanceController.class.getName());
    protected View view;

    public FinanceController() {
        registerEventTypes(FinanceEvents.InitApplicationModel);
        registerEventTypes(FinanceEvents.InitApplicationModule);
        registerEventTypes(FinanceEvents.InitApplicationView);
        registerEventTypes(FinanceEvents.InitApplicationController);
        registerEventTypes(FinanceEvents.ApplicationError);
    }

    @Override
    protected void initialize() {
        super.initialize();
//        Registry.register(FinanceConstants.MESSAGE_APP, GWT.create(FinanceMessage.class));
        view = new FinanceView(this);
    }

    public void handleEvent(AppEvent event) {
        EventType type = event.getType();
        if (type == FinanceEvents.InitApplicationModel) {
            forwardToView(view, event);
        } else if (type == FinanceEvents.InitApplicationController) {
            forwardToView(view, event);
        } else if (type == FinanceEvents.InitApplicationView) {
            forwardToView(view, event);
        } else if (type == FinanceEvents.InitApplicationModule) {
            forwardToView(view, event);
        } else if (type == FinanceEvents.ApplicationError) {
            onError(event);
        }
    }

    protected void onError(AppEvent ae) {
        log.severe("error: " + ae.toString());
    }
}
