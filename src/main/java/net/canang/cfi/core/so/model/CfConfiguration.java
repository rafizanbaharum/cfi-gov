package net.canang.cfi.core.so.model;

import java.math.BigDecimal;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfConfiguration extends CfMetaObject {

    String getKey();

    void setKey(String value);

    String getValue();

    void setValue(String value);

    String getDescription();

    void setDescription(String description);

    Integer getValueAsInteger();

    Double getValueAsDouble();

    Long getValueAsLong();

    BigDecimal getValueAsBigDecimal();
}
