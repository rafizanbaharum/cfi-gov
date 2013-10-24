package net.canang.cfi.web.client.view;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.MarginData;
import com.extjs.gxt.ui.client.widget.treegrid.TreeGrid;
import com.extjs.gxt.ui.client.widget.treegrid.TreeGridCellRenderer;
import com.google.gwt.user.client.ui.RootPanel;
import net.canang.cfi.web.client.model.MenuModel;

import java.util.ArrayList;
import java.util.List;
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

    private void onInitApplicationController() {
    }

    // app main
    // app header
    // app footer
    private void onInitApplicationView() {
        final Dispatcher dispatcher = Dispatcher.get();
        viewport = new Viewport();
        viewport.setId("app-viewport");
        viewport.setLayout(new BorderLayout());

        // header
        LayoutContainer header = new LayoutContainer();
        header.setId("app-header");
        header.setLayout(new FitLayout());
        BorderLayoutData northData = new BorderLayoutData(Style.LayoutRegion.NORTH, 0.08f);
        northData.setMargins(new Margins(0, 0, 0, 0));
        viewport.add(header, northData);

        // main
        LayoutContainer main = new LayoutContainer();
        main.setId("app-main");
        main.setLayout(new FitLayout());
        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0, 0, 0, 0));
        viewport.add(main, centerData);

        LayoutContainer view = new LayoutContainer();
        view.setId("app-main-view");
        view.setLayout(new BorderLayout());
        main.add(view);

        createWest(view);
        createEast(view);
        createNorth(view);

        viewport.add(main, centerData);
        RootPanel.get().add(viewport);
//        dispatcher.dispatch(FinanceEvents.InitApplicationModule);
    }

    private void createNorth(LayoutContainer view) {
        // main > view > breadcrumb
        LayoutContainer breadcrumb = new LayoutContainer();
        breadcrumb.setId("app-main-breadcrumb");
        breadcrumb.setLayout(new FitLayout());
        BorderLayoutData northData = new BorderLayoutData(Style.LayoutRegion.NORTH, 0.14f);
        northData.setMargins(new Margins(0, 0, 0, 0));
        view.add(breadcrumb, northData);
        breadcrumb.add(new Html("Home"), new MarginData(30,0,0,360));

    }

    private void createEast(LayoutContainer view) {
        // main > view > canvas
        LayoutContainer canvas = new LayoutContainer();
        canvas.setId("app-main-canvas");
        canvas.setLayout(new FitLayout());
        BorderLayoutData eastData = new BorderLayoutData(Style.LayoutRegion.EAST, 0.75f);
        eastData.setMargins(new Margins(0, 0, 0, 0));
        view.add(canvas, eastData);

        // dummy grid
        ContentPanel gridPanel = new ContentPanel();
        gridPanel.setLayout(new FitLayout());

        ListStore store = new ListStore<MenuModel>();
        store.add(new MenuModel("3", "Networks", true));
        store.add(new MenuModel("3", "Backup", true));

        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
        ColumnConfig column = new ColumnConfig();

        // checkbox
        CheckBoxSelectionModel<MenuModel> selectionModel = new CheckBoxSelectionModel<MenuModel>();
        selectionModel.setSelectionMode(Style.SelectionMode.MULTI);
        configs.add(selectionModel.getColumn());

        column.setId(MenuModel.NAME);
        column.setHeader("Name");
        column.setWidth(400);
        configs.add(column);

        column = new ColumnConfig();
        column.setId(MenuModel.SUMMARY);
        column.setHeader("Summary");
        column.setWidth(100);
        column.setSortable(false);
        configs.add(column);
        final ColumnModel cm = new ColumnModel(configs);
        final Grid<MenuModel> grid = new Grid<MenuModel>(store, cm);
        grid.getView().setShowDirtyCells(false);
        grid.getView().setEmptyText("grid empty");
        grid.getView().setForceFit(true);
        grid.setStripeRows(true);
        grid.setStateful(true);
        grid.setLoadMask(true);
        grid.setAutoExpandColumn(MenuModel.NAME);
        grid.setSelectionModel(selectionModel);
        grid.addPlugin(selectionModel);
        gridPanel.add(grid);
        canvas.add(gridPanel, new MarginData(10,10,10,20));
    }

    private void createWest(LayoutContainer view) {
        // main > view > menu
        LayoutContainer menu = new LayoutContainer();
        menu.setId("app-main-menu");
        menu.setLayout(new FitLayout());
        BorderLayoutData westData = new BorderLayoutData(Style.LayoutRegion.WEST, 0.25f);
        westData.setMargins(new Margins(0, 0, 0, 0));
        view.add(menu, westData);

        // dummy tree menu
        TreeStore<MenuModel> store = new TreeStore<MenuModel>();
        store.add(new MenuModel("1", "Home", true), false);
        store.add(new MenuModel("2", "Reports", true), false);

        MenuModel networkMenu = new MenuModel("3", "Networks", true);
        networkMenu.add(new MenuModel("2.1", "My Networks", true));
        networkMenu.add(new MenuModel("2.2", "Deployment", true));
        networkMenu.add(new MenuModel("2.3", "Settings", true));

        MenuModel backupMenu = new MenuModel("3", "Backup", true);
        backupMenu.add(new MenuModel("3.1", "My Computers", true));
        backupMenu.add(new MenuModel("3.2", "Deployment", true));
        store.add(networkMenu, true);
        store.add(backupMenu, true);

        LayoutContainer menuPanel = new LayoutContainer();
        menuPanel.setId("app-main-menu-panel");
        menuPanel.setLayout(new FitLayout());

        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
        ColumnConfig column = new ColumnConfig();
        column.setRenderer(new TreeGridCellRenderer<MenuModel>());
        column.setId(MenuModel.NAME);
        column.setHeader("");
        column.setWidth(200);
        configs.add(column);

        final ColumnModel cm = new ColumnModel(configs);
        TreeGrid<MenuModel> tree = new TreeGrid<MenuModel>(store, cm);
        tree.setId("app-main-menu-tree");
        tree.getView().setForceFit(true);
        tree.setAutoExpandColumn(MenuModel.NAME);
        tree.setTrackMouseOver(false);
        tree.setHideHeaders(true);
        tree.setBorders(false);
        tree.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);
        tree.setAutoExpand(true);
        menuPanel.add(tree);
        menu.add(menuPanel);
    }
}
