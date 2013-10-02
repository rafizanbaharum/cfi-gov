package net.canang.cfi.core.am.model.impl;

import net.canang.cfi.core.am.model.CfAclSid;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author canang.technologies
 * Date: 5/13/11
 */
@Entity(name = "AclSid")
@Table(name = "ACL_SID")
public class CfAclSidImpl implements CfAclSid {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "PRINCIPAL", nullable = false)
    private Boolean principal;

    @Column(name = "SID", nullable = false)
    private String sid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
