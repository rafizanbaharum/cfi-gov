package net.canang.cfi.core.so.model.impl;

import net.canang.cfi.core.so.model.CfMetadata;
import net.canang.cfi.core.so.model.CfPrincipal;
import net.canang.cfi.core.so.model.CfPrincipalRole;
import net.canang.cfi.core.so.model.CfRoleType;

import javax.persistence.*;

/**
 * @author canang.technologies
 * @since 7/12/13
 */
@Table(name = "CF_PCPL_ROLE")
@Entity(name = "CfPrincipalRole")
public class CfPrincipalRoleImpl implements CfPrincipalRole {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ROLE_TYPE")
    private CfRoleType roleType;

    @OneToOne(targetEntity = CfPrincipalImpl.class)
    @JoinColumn(name = "PRINCIPAL_ID")
    private CfPrincipal principal;

    @Embedded
    private CfMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CfRoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(CfRoleType roleType) {
        this.roleType = roleType;
    }

    public CfPrincipal getPrincipal() {
        return principal;
    }

    public void setPrincipal(CfPrincipal principal) {
        this.principal = principal;
    }

    public CfMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CfMetadata metadata) {
        this.metadata = metadata;
    }
}
