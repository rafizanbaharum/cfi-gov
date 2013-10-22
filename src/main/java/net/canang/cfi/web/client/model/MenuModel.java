package net.canang.cfi.web.client.model;

import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.TreeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/22/13
 */
public class MenuModel extends BaseTreeModel {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String COUNT = "count";
    public static final String SUMMARY = "summary";
    public static final String SHOW = "show";


    protected List<MenuModel> entries = new ArrayList<MenuModel>();

    public MenuModel(String id, String name, Boolean show) {
        setId(id);
        setName(name);
        show = null == show ? Boolean.FALSE : show;
        setShow(show);
    }

    public MenuModel(String id, String name, int count, Boolean show) {
        setId(id);
        setName(name);
        setCount(count);
        show = null == show ? Boolean.FALSE : show;
        setShow(show);
    }

    public void setId(String id) {
        set(ID, id);
    }

    public String getId() {
        return get(ID);
    }

    public void setName(String name) {
        set(NAME, name);
    }

    public String getName() {
        return get(NAME);
    }

    public Integer getCount() {
        return get(COUNT);
    }

    public Integer setCount(Integer count) {
        return set(COUNT, count);
    }

    public MenuModel findEntry(String name) {
        if (get(name) != null) {
            return (MenuModel) get(name);
        }
        for (MenuModel entry : getEntries()) {
            if (name.equals(entry.getName())) {
                return entry;
            }
        }
        return null;
    }

    public void setShow(Boolean show) {
        set(SHOW, show);
    }

    public Boolean getShow() {
        return get(SHOW);
    }

    public List<MenuModel> getEntries() {
        return entries;
    }


    public void loadEntries() {
        loadEntries(this);
    }

    private void loadEntries(TreeModel model) {
        for (ModelData child : model.getChildren()) {
            if (child instanceof MenuModel) {
                entries.add((MenuModel) child);
            }
        }
    }
}


