package net.canang.cfi.core.so.model;

/**
 * @author canang.technologies
 * @since 7/10/13
 */
public interface CfMetaObject {

    Long getId();

    CfMetadata getMetadata();

    void setMetadata(CfMetadata metadata);
}
