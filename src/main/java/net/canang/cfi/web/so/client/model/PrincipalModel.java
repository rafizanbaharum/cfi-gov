package net.canang.cfi.web.so.client.model;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * @author rafizan.baharum
 * @since 10/20/13
 */
public class PrincipalModel extends BaseModel {

    public static final String ID = "id";
    public static final String STATE_ID = "principalId";
    public static final String SUMMARY = "summary";
    public static final String NAME = "name";
    public static final String TYPE = "type";

    public PrincipalModel() {

    }

    public Long getId() {
        return get(ID);
    }

    public void setId(Long id) {
        set(ID, id);
    }

    public String getName() {
        return get(NAME);
    }

    public void setName(String name) {
        set(NAME, name);
    }

    public PrincipalType getType() {
        return get(TYPE);
    }

    public void setType(PrincipalType type) {
        set(TYPE, type);
    }

    public String getSummary() {
        return get(SUMMARY);
    }

    public void setSummary(String summary) {
        set(SUMMARY, summary);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrincipalModel)) return false;

        PrincipalModel that = (PrincipalModel) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
