package net.canang.cfi.core.so.model;

/**
 * @author canang.technologies
 * @since 7/11/13
 */
public interface CfDocument extends CfFlowObject {

    String getReferenceNo();

    void setReferenceNo(String referenceNo);

    String getSourceNo();

    void setSourceNo(String sourceNo);

    String getDescription();

    void setDescription(String description);

    String getPath();
}
