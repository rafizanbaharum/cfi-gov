package net.canang.cfi.core.dd.model;

import net.canang.cfi.core.so.model.CfMetaObject;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfFundCode extends CfMetaObject {

    String getCode();

    String getAlias();

    String getDescription();

    String getSummary();
}
