package net.canang.cfi.core.dd.model.impl;

import net.canang.cfi.core.dd.model.CfAccountCode;
import net.canang.cfi.core.dd.model.CfSodoCode;
import net.canang.cfi.core.so.model.CfMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Table(name = "CF_SODO_CODE")
@Entity(name = "CfSodoCode")
public class CfSodoCodeImpl implements CfSodoCode, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_CF_SODO_CODE")
    @SequenceGenerator(name = "SEQ_CF_SODO_CODE", sequenceName = "SEQ_CF_SODO_CODE", allocationSize = 1)
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


    @ManyToOne(targetEntity = CfSodoCodeImpl.class)
    @JoinColumn(name = "PARENT_ID")
    private CfSodoCode parent;

    @NotNull
    @ManyToOne(targetEntity = CfAccountCodeImpl.class)
    @JoinColumn(name = "ACCOUNT_CODE_ID")
    private CfAccountCode accountCode;

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

    public CfSodoCode getParent() {
        return parent;
    }

    public void setParent(CfSodoCode parent) {
        this.parent = parent;
    }

    public CfAccountCode getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(CfAccountCode accountCode) {
        this.accountCode = accountCode;
    }

    public CfMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CfMetadata metadata) {
        this.metadata = metadata;
    }

    @Transient
    public String getSummary() {
        return code + " - " + description;
    }

    @Override
    public String toString() {
        return code + " - " + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CfSodoCodeImpl)) return false;

        CfSodoCodeImpl that = (CfSodoCodeImpl) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
