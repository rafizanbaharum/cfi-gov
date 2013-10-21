package net.canang.cfi.core.bm.model.impl;

import net.canang.cfi.core.bm.model.CfControl;
import net.canang.cfi.core.bm.model.CfControlItem;
import net.canang.cfi.core.bm.model.CfControlType;
import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.dd.model.impl.CfPeriodImpl;
import net.canang.cfi.core.so.model.impl.CfDocumentImpl;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Table(name = "CF_CTRL")
@Entity(name = "CfControl")
@Inheritance(strategy = InheritanceType.JOINED)
public class  CfControlImpl extends CfDocumentImpl implements CfControl, Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;

    @Column(name = "CANCEL_COMMENT")
    private String cancelComment;

    @Column(name = "REMOVE_COMMENT")
    private String removeComment;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "CONTROL_TYPE")
    private CfControlType controlType;

    @Column(name = "INTERNAL")
    private boolean internal = true;

    @ManyToOne(targetEntity = CfPeriodImpl.class)
    @JoinColumn(name = "SOURCE_ID")
    private CfPeriod source;

    @ManyToOne(targetEntity = CfPeriodImpl.class)
    @JoinColumn(name = "SINK_ID")
    private CfPeriod sink;

    @OneToMany(targetEntity = CfControlItemImpl.class, mappedBy = "control", fetch = FetchType.LAZY)
    private List<CfControlItem> items;

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public CfControlType getControlType() {
        return controlType;
    }

    public void setControlType(CfControlType type) {
        this.controlType = type;
    }

    public boolean isInternal() {
        return internal;
    }

    public void setInternal(boolean internal) {
        this.internal = internal;
    }

    public String getCancelComment() {
        return cancelComment;
    }

    public void setCancelComment(String cancelComment) {
        this.cancelComment = cancelComment;
    }

    public String getRemoveComment() {
        return removeComment;
    }

    public void setRemoveComment(String removeComment) {
        this.removeComment = removeComment;
    }


    public CfPeriod getSource() {
        return source;
    }

    public void setSource(CfPeriod source) {
        this.source = source;
    }

    public CfPeriod getSink() {
        return sink;
    }

    public void setSink(CfPeriod sink) {
        this.sink = sink;
    }

    public List<CfControlItem> getItems() {
        return items;
    }

    public void setItems(List<CfControlItem> items) {
        this.items = items;
    }
}
