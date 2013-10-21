package net.canang.cfi.web.client.model;

/**
 * @author rafizan.baharum
 * @since 10/20/13
 */
public class KeyedModel extends MetaModel {

    public static final String ID = "id";
    public static final String STATE_ID = "stateId";
    public static final String KEY = "key";
    public static final String ALIAS = "key";
    public static final String DESCRIPTION = "description";
    public static final String VALUE = "value";
    public static final String SUMMARY = "summary";

    public Long getId() {
        return get(ID);
    }

    public void setId(Long id) {
        set(ID, id);
    }

    public String getKey() {
        return get(KEY);
    }

    public void setKey(String key) {
        set(KEY, key);
    }

    public String getAlias() {
        return get(ALIAS);
    }

    public void setAlias(String alias) {
        set(ALIAS, alias);
    }

    public String getDescription() {
        return get(DESCRIPTION);
    }

    public void setDescription(String description) {
        set(DESCRIPTION, description);
    }

    public String getValue() {
        return get(VALUE);
    }

    public void setValue(String value) {
        set(VALUE, value);
    }

    public String getSummary() {
        return get(SUMMARY);
    }

    public void setSummary(String summary) {
        set(SUMMARY, summary);
    }

    public String toString() {
        return getKey() + " - " + getDescription();
    }
}

