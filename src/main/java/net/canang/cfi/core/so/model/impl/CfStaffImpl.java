package net.canang.cfi.core.so.model.impl;

import net.canang.cfi.core.dd.model.CfGender;
import net.canang.cfi.core.dd.model.CfPositionCode;
import net.canang.cfi.core.dd.model.impl.CfPositionCodeImpl;
import net.canang.cfi.core.so.model.CfActorType;
import net.canang.cfi.core.so.model.CfStaff;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author canang.technologies
 * @since 7/10/13
 */
@Table(name = "CF_STAF")
@Entity(name = "CfStaff")
public class CfStaffImpl extends CfActorImpl implements CfStaff {

    @Column(name = "NRIC_NO")
    private String nricNo;

    @Column(name = "SUSPENDED")
    private boolean suspended = false;

    @Column(name = "SALARY")
    private BigDecimal salary;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "GENDER")
    private CfGender gender;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @OneToOne(targetEntity = CfPositionCodeImpl.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "POSITION_CODE_ID")
    private CfPositionCode positionCode;

    public CfStaffImpl() {
        setActorType(CfActorType.STAFF);
    }

    public String getStaffNo() {
        return getIdentityNo();
    }

    public void setStaffNo(String staffNo) {
        setIdentityNo(staffNo);
    }

}
