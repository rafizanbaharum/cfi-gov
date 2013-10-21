package net.canang.cfi.core.am.model.impl;

import net.canang.cfi.core.am.model.CfModule;
import net.canang.cfi.core.am.model.CfSubModule;
import net.canang.cfi.core.so.model.CfMetadata;

import javax.persistence.*;

/**
 * @author canang.technologies
 * @since 7/10/13
 */
@Table(name = "CF_SMDL")
@Entity(name = "CfSubModule")
public class CfSubModuleImpl implements CfSubModule {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "ALIAS")
    private String alias;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ORDR")
    private Integer order;

    @OneToOne(targetEntity = CfModuleImpl.class)
    @JoinColumn(name = "MODULE_ID")
    private CfModule module;

    @Embedded
    private CfMetadata metadata = new CfMetadata();

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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public CfModule getModule() {
        return module;
    }

    public void setModule(CfModule module) {
        this.module = module;
    }

    public CfMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CfMetadata metadata) {
        this.metadata = metadata;
    }
}
