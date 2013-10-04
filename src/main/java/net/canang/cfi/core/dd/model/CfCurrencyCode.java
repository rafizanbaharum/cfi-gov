package net.canang.cfi.core.dd.model;

import net.canang.cfi.core.so.model.CfMetaObject;

import java.math.BigDecimal;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfCurrencyCode extends CfMetaObject {

    String getCode();

    String getAlias();

    String getDescription();

    BigDecimal getBuy();

    BigDecimal getSell();

    String getSummary();

}
