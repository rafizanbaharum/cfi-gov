package net.canang.cfi.core.dd.model;


import net.canang.cfi.core.so.model.CfMetaObject;

public interface CfReferenceNo extends CfMetaObject {

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

    String getPrefix();

    void setPrefix(String prefix);

    Integer getYear();

    void setYear(Integer year);

    String getSequenceFormat();

    void setSequenceFormat(String sequenceFormat);

    String getReferenceFormat();

    void setReferenceFormat(String referenceFormat);

    Integer getIncrementValue();

    void setIncrementValue(Integer incrementValue);

    Integer getCurrentValue();

    void setCurrentValue(Integer currentValue);

}
