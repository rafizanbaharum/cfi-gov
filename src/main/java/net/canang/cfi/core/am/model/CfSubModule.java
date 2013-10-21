package net.canang.cfi.core.am.model;

import net.canang.cfi.core.so.model.CfMetaObject;

/**
 * @author canang.technologies
 * @since 7/10/13
 */
public interface CfSubModule extends CfMetaObject {

    String getCode();

    void setCode(String code);

    String getAlias();

    void setAlias(String alias);

    String getDescription();

    void setDescription(String description);

    Integer getOrder();

    void setOrder(Integer order);

    CfModule getModule();

    void setModule(CfModule module);
}
