package net.canang.cfi.core.so.model;

import java.util.Set;

/**
 * @author canang.technologies
 * @since 7/10/13
 */
public interface CfPrincipal extends CfMetaObject {

    String getName();

    void setName(String name);

    boolean isLocked();

    void setLocked(boolean locked);

    CfPrincipalType getPrincipalType();

    void setPrincipalType(CfPrincipalType principalType);

    Set<CfPrincipalRole> getRoles();

    void setRoles(Set<CfPrincipalRole> roles);

}
