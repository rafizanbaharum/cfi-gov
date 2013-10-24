package net.canang.cfi.core.so.model.impl;

import net.canang.cfi.core.dd.model.CfReferenceNo;
import net.canang.cfi.core.so.model.CfMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Table(name = "CF_RFRN_NO")
@Entity(name = "CfReferenceNo")
public class CfReferenceNoImpl implements CfReferenceNo, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_CF_RFRN_NO")
    @SequenceGenerator(name = "SEQ_CF_RFRN_NO", sequenceName = "SEQ_CF_RFRN_NO", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", nullable = false)
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "PREFIX")
    private String prefix;

    @NotNull
    @Column(name = "YEAR")
    private Integer year;  // expiry

    @NotNull
    @Column(name = "SEQUENCE_FORMAT")
    private String sequenceFormat;

    @NotNull
    @Column(name = "REFERENCE_FORMAT")
    private String referenceFormat;

    @NotNull
    @Column(name = "INCREMENT_VALUE")
    private Integer incrementValue;

    @NotNull
    @Column(name = "CURRENT_VALUE")
    private Integer currentValue;

    @Embedded
    private CfMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSequenceFormat() {
        return sequenceFormat;
    }

    public void setSequenceFormat(String sequenceFormat) {
        this.sequenceFormat = sequenceFormat;
    }

    public String getReferenceFormat() {
        return referenceFormat;
    }

    public void setReferenceFormat(String referenceFormat) {
        this.referenceFormat = referenceFormat;
    }

    public Integer getIncrementValue() {
        return incrementValue;
    }

    public void setIncrementValue(Integer incrementValue) {
        this.incrementValue = incrementValue;
    }

    public Integer getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Integer currentValue) {
        this.currentValue = currentValue;
    }

    public CfMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CfMetadata metadata) {
        this.metadata = metadata;
    }
}
