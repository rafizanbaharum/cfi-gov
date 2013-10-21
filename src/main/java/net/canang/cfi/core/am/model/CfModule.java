package net.canang.cfi.core.am.model;

import net.canang.cfi.core.so.model.CfMetaObject;

import java.util.Set;

/**
 * @author canang.technologies
 * @since 7/10/13
 */
public interface CfModule extends CfMetaObject {

    String getCode();

    void setCode(String code);

    String getAlias();

    void setAlias(String alias);

    String getDescription();

    void setDescription(String description);

    Integer getOrder();

    void setOrder(Integer order);

    Set<CfSubModule> getSubModules();

    void setSubModules(Set<CfSubModule> subModules);

}
