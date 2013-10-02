package net.canang.cfi.core.so.model.impl;

import net.canang.cfi.core.so.model.CfDocument;
import net.canang.cfi.core.so.model.CfFlowdata;
import net.canang.cfi.core.so.model.CfMetadata;

import javax.persistence.*;
import java.io.File;

/**
 * @author canang.technologies
 * @since 7/11/13
 */
@Table(name = "CF_DOCM")
@Entity(name = "CfDocument")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CfDocumentImpl implements CfDocument {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "REFERENCE_NO")
    private String referenceNo;

    @Column(name = "SOURCE_NO")
    private String sourceNo;

    @Column(name = "DESCRIPTION")
    private String description;

    @Embedded
    private CfMetadata metadata;

    @Embedded
    private CfFlowdata flowdata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getSourceNo() {
        return sourceNo;
    }

    public void setSourceNo(String sourceNo) {
        this.sourceNo = sourceNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CfMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CfMetadata metadata) {
        this.metadata = metadata;
    }

    public CfFlowdata getFlowdata() {
        return flowdata;
    }

    public void setFlowdata(CfFlowdata flowdata) {
        this.flowdata = flowdata;
    }

    public String getPath() {
        return File.pathSeparator + "Dummy";
    }
}
