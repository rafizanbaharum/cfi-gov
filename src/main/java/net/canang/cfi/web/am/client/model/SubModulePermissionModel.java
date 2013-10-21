package net.canang.cfi.web.am.client.model;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * @author rafizan.baharum
 * @since 10/20/13
 */
public class SubModulePermissionModel extends BaseModel {

    public static final String CODE = "code";
    public static final String DESCRIPTION = "description";
    public static final String VIEW = "view";
    public static final String CREATE = "create";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";
    public static final String CANCEL = "cancel";
    public static final String PRINT = "print";
    public static final String ADMIN = "admin";
    public static final String MODULE = "module";

    public String getCode() {
        return get(CODE);
    }

    public void setCode(String code) {
        set(CODE, code);
    }

    public String getDescription() {
        return get(DESCRIPTION);
    }

    public void setDescription(String description) {
        set(DESCRIPTION, description);
    }

    public Boolean getView() {
        return get(VIEW);
    }

    public void setView(Boolean view) {
        set(VIEW, view);
    }

    public Boolean getCreate() {
        return get(CREATE);
    }

    public void setCreate(Boolean create) {
        set(CREATE, create);
    }

    public Boolean getUpdate() {
        return get(UPDATE);
    }

    public void setUpdate(Boolean update) {
        set(UPDATE, update);
    }

    public Boolean getDelete() {
        return get(DELETE);
    }

    public void setDelete(Boolean delete) {
        set(DELETE, delete);
    }

    public Boolean getCancel() {
        return get(CANCEL);
    }

    public void setCancel(Boolean cancel) {
        set(CANCEL, cancel);
    }

    public Boolean getPrint() {
        return get(PRINT);
    }

    public void setPrint(Boolean print) {
        set(PRINT, print);
    }

    public Boolean getAdmin() {
        return get(ADMIN);
    }

    public void setAdmin(Boolean admin) {
        set(ADMIN, admin);
    }

    public String getModule() {
        return get(ADMIN);
    }

    public void setModule(String module) {
        set(MODULE, module);
    }

}
