package net.canang.cfi.core.bm.model.impl;

import net.canang.cfi.core.bm.model.CfControl;
import net.canang.cfi.core.bm.model.CfControlItem;
import net.canang.cfi.core.dd.model.CfSodoCode;
import net.canang.cfi.core.dd.model.impl.CfSodoCodeImpl;
import net.canang.cfi.core.so.model.CfMetadata;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Table(name = "CF_CTRL_ITEM")
@Entity(name = "CfControlItem")
public class CfControlItemImpl implements CfControlItem, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_FS_CTRL_ITEM")
    @SequenceGenerator(name = "SEQ_FS_CTRL_ITEM", sequenceName = "SEQ_FS_CTRL_ITEM", allocationSize = 1)
    private Long id;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @ManyToOne(targetEntity = CfControlImpl.class, cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTROL_ID")
    private CfControl control;

    @ManyToOne(targetEntity = CfSodoCodeImpl.class, cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "SODO_CODE_ID")
    private CfSodoCode sodoCode;

    @ManyToOne(targetEntity = CfControlItemImpl.class, cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private CfControlItem parent;

    @Embedded
    private CfMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CfControl getControl() {
        return control;
    }

    public void setControl(CfControl control) {
        this.control = control;
    }

    public CfSodoCode getSodoCode() {
        return sodoCode;
    }

    public void setSodoCode(CfSodoCode sodo) {
        this.sodoCode = sodo;
    }

    public CfControlItem getParent() {
        return parent;
    }

    public void setParent(CfControlItem parent) {
        this.parent = parent;
    }

    public CfMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CfMetadata metadata) {
        this.metadata = metadata;
    }
}
