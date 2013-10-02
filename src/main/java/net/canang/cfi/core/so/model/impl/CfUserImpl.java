package net.canang.cfi.core.so.model.impl;

import net.canang.cfi.core.so.model.CfActor;
import net.canang.cfi.core.so.model.CfPrincipalType;
import net.canang.cfi.core.so.model.CfUser;

import javax.persistence.*;

/**
 * @author canang.technologies
 * @since 7/10/13
 */
@Table(name = "CF_USER")
@Entity(name = "CfUser")
public class CfUserImpl extends CfPrincipalImpl implements CfUser {

    @Column(name = "REALNAME")
    private String realname;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @OneToOne(targetEntity = CfActorImpl.class)
    @JoinColumn(name = "ACTOR_ID")
    private CfActor actor;

    public CfUserImpl() {
        setPrincipalType(CfPrincipalType.USER);
    }

    public String getUsername() {
        return getName();
    }

    public void setUsername(String username) {
        setName(username);
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CfActor getActor() {
        return actor;
    }

    public void setActor(CfActor actor) {
        this.actor = actor;
    }

    @Override
    public String toString() {
        return "CmUserImpl{" +
                "name='" + getName() + '\'' +
                "realname='" + realname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
