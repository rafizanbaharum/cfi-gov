package net.canang.cfi.core.dd.model.impl;

import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.dd.model.CfCostCenterMember;
import net.canang.cfi.core.so.model.CfMetadata;
import net.canang.cfi.core.so.model.CfPrincipal;
import net.canang.cfi.core.so.model.impl.CfPrincipalImpl;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
@Entity(name = "CfCostCenterMember")
@Table(name = "CF_COST_CNTR_MMBR")
public class CfCostCenterMemberImpl implements CfCostCenterMember, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_FS_COST_CNTR_MMBR")
    @SequenceGenerator(name = "SEQ_FS_COST_CNTR_MMBR", sequenceName = "SEQ_FS_COST_CNTR_MMBR", allocationSize = 1)
    private Long id;

    @ManyToOne(targetEntity = CfCostCenterImpl.class)
    @JoinColumn(name = "COST_CENTER_ID")
    private CfCostCenter costCenter;

    @ManyToOne(targetEntity = CfPrincipalImpl.class)
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

    public CfCostCenter getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(CfCostCenter costCenter) {
        this.costCenter = costCenter;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CfCostCenterMemberImpl that = (CfCostCenterMemberImpl) o;

        if (costCenter != null ? !costCenter.equals(that.costCenter) : that.costCenter != null) return false;
        if (principal != null ? !principal.equals(that.principal) : that.principal != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (costCenter != null ? costCenter.hashCode() : 0);
        result = 31 * result + (principal != null ? principal.hashCode() : 0);
        return result;
    }

}
