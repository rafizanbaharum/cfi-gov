package net.canang.cfi.core.dd.model.impl;

import net.canang.cfi.core.dd.model.CfStateCode;
import net.canang.cfi.core.so.model.CfMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Table(name = "CF_PSTN_CODE")
@Entity(name = "CfPositionCode")
public class CfPositionCodeImpl {


    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_CF_PSTN_CODE")
    @SequenceGenerator(name = "SEQ_CF_PSTN_CODE", sequenceName = "SEQ_CF_PSTN_CODE", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", nullable = false)
    private String code;

    @NotNull
    @Column(name = "ALIAS", nullable = false)
    private String alias;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @OneToOne(targetEntity = CfStateCodeImpl.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "STATE_ID")
    private CfStateCode stateCode;

    @Embedded
    private CfMetadata metadata;

}
