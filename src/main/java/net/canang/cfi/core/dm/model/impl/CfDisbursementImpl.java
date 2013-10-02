package net.canang.cfi.core.dm.model.impl;

import net.canang.cfi.core.dm.model.CfDisbursement;
import net.canang.cfi.core.dm.model.CfDisbursementItem;
import net.canang.cfi.core.dm.model.CfDisbursementMethod;
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
@Table(name = "CF_DSBM")
@Entity(name = "CfDisbursement")
public class CfDisbursementImpl implements CfDisbursement, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_CF_DSBM")
    @SequenceGenerator(name = "SEQ_CF_DSBM", sequenceName = "SEQ_CF_DSBM", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "REFERENCE_NO")
    private String referenceNo;

    @Column(name = "PROCESSED")
    private boolean processed;

    @NotNull
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "METHOD")
    private CfDisbursementMethod method;

    @OneToMany(targetEntity = CfDisbursementItemImpl.class, mappedBy = "disbursement", fetch = FetchType.LAZY)
    private List<CfDisbursementItem> items;

    @Embedded
    private CfMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public CfDisbursementMethod getMethod() {
        return method;
    }

    public void setMethod(CfDisbursementMethod method) {
        this.method = method;
    }

    public List<CfDisbursementItem> getItems() {
        return items;
    }

    public void setItems(List<CfDisbursementItem> items) {
        this.items = items;
    }

    public CfMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CfMetadata metadata) {
        this.metadata = metadata;
    }
}
