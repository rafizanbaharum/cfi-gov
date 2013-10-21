package net.canang.cfi.web.so.client.model;

import net.canang.cfi.web.client.model.CodedModel;
import net.canang.cfi.web.dd.client.model.CampusCodeModel;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
public class ReferenceNoModel extends CodedModel {

    public static final String SEQUENCE_FORMAT = "sequenceFormat";
    public static final String REFERENCE_FORMAT = "referenceFormat";
    public static final String PREFIX = "prefix";
    public static final String INCREMENT_VALUE = "incVal";
    public static final String CURRENT_VALUE = "currVal";

    private CampusCodeModel campus;

    public ReferenceNoModel() {

    }

    public ReferenceNoModel(String code, String description) {
        setCode(code);
        setDescription(description);
        setSummary(code + " - " + description);
    }

    public String getSequenceFormat() {
        return get(SEQUENCE_FORMAT);
    }

    public void setSequenceFormat(String format) {
        set(SEQUENCE_FORMAT, format);
    }

    public String getReferenceFormat() {
        return get(REFERENCE_FORMAT);
    }

    public void setReferenceFormat(String format) {
        set(REFERENCE_FORMAT, format);
    }

    public String getPrefix() {
        return get(PREFIX);
    }

    public void setPrefix(String prefix) {
        set(PREFIX, prefix);
    }

    public Integer getIncrementValue() {
        return get(INCREMENT_VALUE);
    }

    public void setIncrementValue(Integer incrementValue) {
        set(INCREMENT_VALUE, incrementValue);
    }

    public Integer getCurrentValue() {
        return get(CURRENT_VALUE);
    }

    public void setCurrentValue(Integer currentValue) {
        set(CURRENT_VALUE, currentValue);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof ReferenceNoModel) {
            ReferenceNoModel mobj = (ReferenceNoModel) obj;
            return getDescription().equals(mobj.getDescription()) && getCode().equals(mobj.getCode());
        }
        return super.equals(obj);
    }
}
