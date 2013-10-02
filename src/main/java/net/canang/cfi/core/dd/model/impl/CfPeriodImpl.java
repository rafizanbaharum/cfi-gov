package net.canang.cfi.core.dd.model.impl;

import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.so.model.CfMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Table(name = "CF_PERD")
@Entity(name = "CfPeriod")
public class CfPeriodImpl implements CfPeriod, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_CF_PERD")
    @SequenceGenerator(name = "SEQ_CF_PERD", sequenceName = "SEQ_CF_PERD", allocationSize = 1)
    private Long id;

    @NotNull
    @Size(min = 20, max = 100)
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "ACTIVE")
    private boolean current;

    @ManyToOne(targetEntity = CfCostCenterImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "COST_CENTER_ID")
    private CfCostCenter costCenter;

    @Embedded
    private CfMetadata metadata;

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public CfCostCenter getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(CfCostCenter costCenter) {
        this.costCenter = costCenter;
    }

    public CfMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CfMetadata metadata) {
        this.metadata = metadata;
    }

    @Transient
    public String getSummary() {
        return getCostCenter().getCode() + " - " + getCostCenter().getDescription();
    }

    @Transient
    public String getShortSummary() {
        return getCostCenter().getCode();
    }

    @Transient
    public String getLongSummary() {
        return getCostCenter().getCode() + " : " + dateFormat.format(getStartDate()) + " - " + dateFormat.format(getEndDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CfPeriodImpl)) return false;

        CfPeriodImpl ctPeriod = (CfPeriodImpl) o;

        if (getId() != null ? !getId().equals(ctPeriod.getId()) : ctPeriod.getId() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CfPeriodImpl{" +
                "code ='" + getCostCenter().getCode() + '\'' +
                "description='" + description + '\'' +
                ", id=" + id +
                '}';
    }

}
