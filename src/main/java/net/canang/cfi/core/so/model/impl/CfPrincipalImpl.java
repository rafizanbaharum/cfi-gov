package net.canang.cfi.core.so.model.impl;

import net.canang.cfi.core.so.model.CfMetadata;
import net.canang.cfi.core.so.model.CfPrincipal;
import net.canang.cfi.core.so.model.CfPrincipalRole;
import net.canang.cfi.core.so.model.CfPrincipalType;

import javax.persistence.*;
import java.util.Set;

/**
 * @author canang.technologies
 * @since 7/10/13
 */
@Table(name = "CF_PCPL")
@Entity(name = "CfPrincipal")
@Inheritance(strategy = InheritanceType.JOINED)
public class CfPrincipalImpl implements CfPrincipal {

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "SEQ_RND_PRINCIPAL", name = "SEQ_RND_PRINCIPAL")
    @GeneratedValue(generator = "SEQ_RND_PRINCIPAL", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LOCKED")
    private boolean locked;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "PRINCIPAL_TYPE")
    private CfPrincipalType principalType;

    @OneToMany(targetEntity = CfPrincipalRoleImpl.class, mappedBy = "principal", fetch = FetchType.EAGER)
    private Set<CfPrincipalRole> roles;

    @Embedded
    private CfMetadata metadata = new CfMetadata();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public CfPrincipalType getPrincipalType() {
        return principalType;
    }

    public void setPrincipalType(CfPrincipalType principalType) {
        this.principalType = principalType;
    }

    public Set<CfPrincipalRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<CfPrincipalRole> roles) {
        this.roles = roles;
    }

    public CfMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CfMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "CmPrincipalImpl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + locked +
                '}';
    }
}
