package net.canang.cfi.core.so.model;

/**
 * @author canang.technologies
 * @since 7/10/13
 */
public interface CfPrincipalRole extends CfMetaObject {

    CfRoleType getRoleType();

    CfPrincipal getPrincipal();

}
