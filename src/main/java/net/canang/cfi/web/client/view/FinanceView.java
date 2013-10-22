package net.canang.cfi.web.client.view;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
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
        onInitApplicationView();
    }

    private void onInitApplicationModel() {
    }

    private void onInitApplicationController(){}
    private void onInitApplicationView(){
        final Dispatcher dispatcher = Dispatcher.get();
        viewport = new Viewport();
        viewport.setId("app-main-viewport");
        viewport.setLayout(new BorderLayout());

        BorderLayoutData data = new BorderLayoutData(Style.LayoutRegion.CENTER);
        data.setMargins(new Margins(0, 0, 0, 0));
        LayoutContainer mainContainer = new LayoutContainer();
        mainContainer.setLayout(new FitLayout());
        mainContainer.setId("app-main-panel");
        mainContainer.add(new Html("texxxxxxst"));
        viewport.add(mainContainer, data);
        RootPanel.get().add(viewport);
//        dispatcher.dispatch(FinanceEvents.InitApplicationModule);
    }
}
