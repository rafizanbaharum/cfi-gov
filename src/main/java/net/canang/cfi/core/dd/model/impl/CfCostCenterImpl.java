package net.canang.cfi.core.dd.model.impl;

import net.canang.cfi.core.dd.model.*;
import net.canang.cfi.core.so.model.CfMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Entity(name = "CfCostCenter")
@Table(name = "CF_COST_CNTR")
public class CfCostCenterImpl implements CfCostCenter, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_CF_COST_CNTR")
    @SequenceGenerator(name = "SEQ_CF_COST_CNTR", sequenceName = "SEQ_CF_COST_CNTR", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", nullable = false)
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(targetEntity = CfFundCodeImpl.class)
    @JoinColumn(name = "FUND_ID")
    private CfFundCode fundCode;

    @ManyToOne(targetEntity = CfDepartmentCodeImpl.class)
    @JoinColumn(name = "DEPARTMENT_ID")
    private CfDepartmentCode departmentCode;

    @ManyToOne(targetEntity = CfProjectCodeImpl.class)
    @JoinColumn(name = "PROJECT_ID")
    private CfProjectCode projectCode;

    @ManyToOne(targetEntity = CfSubProjectCodeImpl.class)
    @JoinColumn(name = "SUB_PROJECT_ID")
    private CfSubProjectCode subProjectCode;

    @OneToMany(targetEntity = CfPeriodImpl.class, mappedBy = "costCenter", fetch = FetchType.EAGER)
    private List<CfPeriod> periods;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CfFundCode getFundCode() {
        return fundCode;
    }

    public void setFundCode(CfFundCode fundCode) {
        this.fundCode = fundCode;
    }

    public CfDepartmentCode getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(CfDepartmentCode departmentCode) {
        this.departmentCode = departmentCode;
    }

    public CfProjectCode getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(CfProjectCode projectCode) {
        this.projectCode = projectCode;
    }

    public CfSubProjectCode getSubProjectCode() {
        return subProjectCode;
    }

    public void setSubProjectCode(CfSubProjectCode subProjectCode) {
        this.subProjectCode = subProjectCode;
    }

    public List<CfPeriod> getPeriods() {
        return periods;
    }

    public void setPeriods(List<CfPeriod> periods) {
        this.periods = periods;
    }

    public CfMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CfMetadata metadata) {
        this.metadata = metadata;
    }

    @Transient
    public String getSummary() {
        return code + " - " + description;
    }

    @Override
    public CfPeriod getCurrentPeriod() {
        for (CfPeriod period : periods) {
            if (period.isCurrent()) return period;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        net.canang.cfi.core.dd.model.impl.CfCostCenterImpl that = (net.canang.cfi.core.dd.model.impl.CfCostCenterImpl) o;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (fundCode != null ? !fundCode.equals(that.fundCode) : that.fundCode != null) return false;
        if (projectCode != null ? !projectCode.equals(that.projectCode) : that.projectCode != null) return false;
        if (departmentCode != null ? !departmentCode.equals(that.departmentCode) : that.departmentCode != null)
            return false;
        if (subProjectCode != null ? !subProjectCode.equals(that.subProjectCode) : that.subProjectCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (fundCode != null ? fundCode.hashCode() : 0);
        result = 31 * result + (departmentCode != null ? departmentCode.hashCode() : 0);
        result = 31 * result + (projectCode != null ? projectCode.hashCode() : 0);
        result = 31 * result + (subProjectCode != null ? subProjectCode.hashCode() : 0);
        return result;
    }

}
