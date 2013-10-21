package net.canang.cfi.web.client.view;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.google.gwt.user.client.ui.RootPanel;

import java.util.logging.Logger;

/**
 * @author rafizan.baharum
 * @since 10/20/13
 */
public class FinanceView extends View {

    private static final Logger log = Logger.getLogger(FinanceView.class.getName());

    private Viewport viewport;
    private LayoutContainer container;

    public FinanceView(Controller controller) {
        super(controller);
    }

    @Override
    protected void handleEvent(AppEvent appEvent) {
        // TODO:

    }

    private void onInitApplicationModel() {
    }

    private void onInitApplicationController(){}
    private void onInitApplicationView(){
        final Dispatcher dispatcher = Dispatcher.get();
        viewport = new Viewport();
        viewport.setId("app-main-viewport");
        viewport.setLayout(new BorderLayout());
        RootPanel.get().add(viewport);
//        dispatcher.dispatch(FinanceEvents.InitApplicationModule);
//        dispatcher.dispatch(FinanceEvents.NavModuleDsh);
    }
}
