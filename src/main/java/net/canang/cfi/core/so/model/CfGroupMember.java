package net.canang.cfi.core.so.model;

/**
 * @author canang.technologies
 * @since 7/10/13
 */
public interface CfGroupMember extends CfMetaObject {

    CfGroup getGroup();

    void setGroup(CfGroup group);

    CfPrincipal getMember();

    void setMember(CfPrincipal member);
}
