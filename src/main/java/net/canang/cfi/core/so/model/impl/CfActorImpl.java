package net.canang.cfi.core.so.model.impl;

import net.canang.cfi.core.so.model.CfActor;
import net.canang.cfi.core.so.model.CfActorType;
import net.canang.cfi.core.so.model.CfMetadata;

import javax.persistence.*;

/**
 * @author canang.technologies
 * @since 7/10/13
 */
@Table(name = "CF_ACTR")
@Entity(name = "CfActor")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CfActorImpl implements CfActor {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "IDENTITY_NO")
    private String identityNo;

    @Column(name = "NRIC_NO")
    private String nricNo;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS1")
    private String address1;

    @Column(name = "ADDRESS2")
    private String address2;

    @Column(name = "ADDRESS3")
    private String address3;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "FAX")
    private String fax;

    @Column(name = "ACTOR_TYPE")
    private CfActorType actorType;

    @Embedded
    private CfMetadata metadata = new CfMetadata();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getNricNo() {
        return nricNo;
    }

    public void setNricNo(String nricNo) {
        this.nricNo = nricNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public CfActorType getActorType() {
        return actorType;
    }

    public void setActorType(CfActorType actorType) {
        this.actorType = actorType;
    }

    public CfMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CfMetadata metadata) {
        this.metadata = metadata;
    }
}

