package net.canang.cfi.core.dd.model.impl;

import net.canang.cfi.core.dd.model.CfPositionCode;
import net.canang.cfi.core.so.model.CfMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Table(name = "CF_PSTN_CODE")
@Entity(name = "CfPositionCode")
public class CfPositionCodeImpl implements CfPositionCode, Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_CF_PSTN_CODE")
    @SequenceGenerator(name = "SEQ_CF_PSTN_CODE", sequenceName = "SEQ_CF_PSTN_CODE", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", nullable = false)
    private String code;

    @NotNull
    @Column(name = "ALIAS", nullable = false)
    private String alias;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "GRADE")
    private Integer grade;

    @Embedded
    private CfMetadata metadata;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_CF_PSTN_CODE")
    @SequenceGenerator(name = "SEQ_CF_PSTN_CODE", sequenceName = "SEQ_CF_PSTN_CODE", allocationSize = 1)
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public CfMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CfMetadata metadata) {
        this.metadata = metadata;
    }

    @Transient
    @Override
    public String getSummary() {
        return getCode() + Character.DASH_PUNCTUATION + getDescription();
    }
}
