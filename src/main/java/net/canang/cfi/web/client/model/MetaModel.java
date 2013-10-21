package net.canang.cfi.web.client.model;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.Date;

/**
 * @author rafizan.baharum
 * @since 10/20/13
 */
public class MetaModel extends BaseModel implements IsSerializable {

    public static final String META_STATE = "metaState";
    public static final String CREATED_DATE = "createdDate";
    public static final String MODIFIED_DATE = "modifiedDate";
    public static final String DELETED_DATE = "deletedDate";

    public String getMetaState() {
        return get(META_STATE);
    }

    public void setMetaState(String metaState) {
        set(META_STATE, metaState);
    }

    public Date getCreatedDate() {
        return get(CREATED_DATE);
    }

    public void setCreatedDate(Date createdDate) {
        set(CREATED_DATE, createdDate);
    }

    public Date getModifidDate() {
        return get(MODIFIED_DATE);
    }

    public void setModifidDate(Date modifiedDate) {
        set(MODIFIED_DATE, modifiedDate);
    }

    public Date getDeletedDate() {
        return get(DELETED_DATE);
    }

    public void setDeletedDate(Date deletedDate) {
        set(DELETED_DATE, deletedDate);
    }
}


