package net.canang.cfi.web.am.client.model;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * @author rafizan.baharum
 * @since 10/20/13
 */
public class ModuleModel extends BaseModel {

    public static final String ID = "id";
    public static final String CODE = "code";
    public static final String ALIAS = "alias";
    public static final String DESCRIPTION = "description";
    public static final String ORDER = "order";

    public ModuleModel() {
    }

    public Long getId() {
        return (Long) get(ID);
    }

    public void setId(Long id) {
        set(ID, id);
    }

    public String getCode() {
        return (String) get(CODE);
    }

    public void setCode(String code) {
        set(CODE, code);
    }

    public String getAlias() {
        return (String) get(ALIAS);
    }

    public void setAlias(String alias) {
        set(ALIAS, alias);
    }

    public Integer getOrder() {
        return get(ORDER);
    }

    public void setOrder(Integer order) {
        set(ORDER, order);
    }

    public String getDescription() {
        return (String) get(DESCRIPTION);
    }

    public void setDescription(String description) {
        set(DESCRIPTION, description);
    }

    public String toString() {
        return getCode() + ":" + getDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (null == o) return false;
        if (!(o instanceof ModuleModel)) return false;

        ModuleModel that = (ModuleModel) o;
        if (null == getId() || null == that.getId()) return false;
        return (getId().equals(that.getId()));
    }

    @Override
    public int hashCode() {
        return null == getId() ? 0 : getId().hashCode();
    }

}
