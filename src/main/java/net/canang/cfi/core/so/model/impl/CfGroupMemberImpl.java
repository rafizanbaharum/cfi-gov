package net.canang.cfi.core.so.model.impl;

import net.canang.cfi.core.so.model.CfGroup;
import net.canang.cfi.core.so.model.CfGroupMember;
import net.canang.cfi.core.so.model.CfMetadata;
import net.canang.cfi.core.so.model.CfPrincipal;

import javax.persistence.*;

/**
 * @author canang.technologies
 * @since 7/12/13
 */
@Table(name = "CF_GROP_MMBR")
@Entity(name = "CfGroupMember")
public class CfGroupMemberImpl implements CfGroupMember {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = CfGroupImpl.class)
    @JoinColumn(name = "GROUP_ID")
    private CfGroup group;

    @OneToOne(targetEntity = CfPrincipalImpl.class)
    @JoinColumn(name = "MEMBER_ID")
    private CfPrincipal member;

    @Embedded
    private CfMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CfGroup getGroup() {
        return group;
    }

    public void setGroup(CfGroup group) {
        this.group = group;
    }

    public CfPrincipal getMember() {
        return member;
    }

    public void setMember(CfPrincipal member) {
        this.member = member;
    }

    public CfMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CfMetadata metadata) {
        this.metadata = metadata;
    }
}
