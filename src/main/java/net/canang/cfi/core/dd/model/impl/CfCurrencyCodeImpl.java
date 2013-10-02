package net.canang.cfi.core.dd.model.impl;

import net.canang.cfi.core.dd.model.CfCurrencyCode;
import net.canang.cfi.core.so.model.CfMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Table(name = "CF_CRCY_CODE")
@Entity(name = "CfCurrencyCode")
public class CfCurrencyCodeImpl implements CfCurrencyCode, Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_CF_CRCY")
    @SequenceGenerator(name = "SEQ_CF_CRCY", sequenceName = "SEQ_CF_CRCY", allocationSize = 1)
    private Long id;

    @Column(name = "CODE", nullable = false)
    private String code;

    @NotNull
    @Column(name = "ALIAS", nullable = false)
    private String alias;

    @Column(name = "DESCRIPTION")
    private String description;

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

    public CfMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CfMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public String getSummary() {
        return getCode() + Character.DASH_PUNCTUATION + getDescription();
    }
}
