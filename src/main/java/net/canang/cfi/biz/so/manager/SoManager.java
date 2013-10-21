package net.canang.cfi.biz.so.manager;

import net.canang.cfi.core.dd.model.*;
import net.canang.cfi.core.so.model.CfActor;
import net.canang.cfi.core.so.model.CfConfiguration;
import net.canang.cfi.core.so.model.CfVendor;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
public interface SoManager {

    // ====================================================================================================
    // ACTOR
    // ====================================================================================================

    void saveActor(CfActor actor);

    void updateActor(CfActor actor);

    void deactivateActor(CfActor actor);

    // ====================================================================================================
    // REFERENCE NO
    // ====================================================================================================

    void saveReferenceNo(CfReferenceNo referenceNo);

    void updateReferenceNo(CfReferenceNo referenceNo);

    void deactivateReferenceNo(CfReferenceNo referenceNo);

    String generatePlainReferenceNo(String code);

    String generateReferenceNo(String code);

    String generateFormattedReferenceNo(String code, CfCostCenter costCenter,
                                        CfCostCenter requester, CfPeriod period,
                                        CfDepartmentCode department);

    // ====================================================================================================
    // CONFIGURATION
    // ====================================================================================================

    void saveConfiguration(CfConfiguration configuration);

    void updateConfiguration(CfConfiguration configuration);

    void deactivateConfiguration(CfConfiguration configuration);

}
