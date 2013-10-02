package net.canang.cfi.core.so.model.impl;

import net.canang.cfi.core.dd.model.CfConsumerType;
import net.canang.cfi.core.so.model.CfActorType;
import net.canang.cfi.core.so.model.CfVendor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Table(name = "CF_VNDR")
@Entity(name = "CfVendor")
public class CfVendorImpl extends CfActorImpl implements CfVendor, Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "CERT_NO")
    private String certNo;

    @Column(name = "BUMIPUTERA")
    private boolean bumiputera;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    public CfVendorImpl() {
        setActorType(CfActorType.STAFF);
    }

    public String getVendorNo() {
        return super.getIdentityNo();
    }

    public void setVendorNo(String vendorNo) {
        super.setIdentityNo(vendorNo);
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public boolean isBumiputera() {
        return bumiputera;
    }

    public void setBumiputera(boolean bumiputera) {
        this.bumiputera = bumiputera;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
