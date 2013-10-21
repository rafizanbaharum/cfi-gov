package net.canang.cfi.core.am.model.impl;

import net.canang.cfi.core.am.model.CfModule;
import net.canang.cfi.core.am.model.CfSubModule;
import net.canang.cfi.core.so.model.CfMetadata;

import javax.persistence.*;
import java.util.Set;

/**
 * @author canang.technologies
 * @since 7/10/13
 */
@Table(name = "CF_MODL")
@Entity(name = "CfModule")
public class CfModuleImpl implements CfModule {

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

    @OneToMany(targetEntity = CfSubModuleImpl.class, mappedBy = "module", fetch = FetchType.EAGER)
    private Set<CfSubModule> subModules;

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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Set<CfSubModule> getSubModules() {
        return subModules;
    }

    public void setSubModules(Set<CfSubModule> subModules) {
        this.subModules = subModules;
    }

    public CfMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CfMetadata metadata) {
        this.metadata = metadata;
    }
}
