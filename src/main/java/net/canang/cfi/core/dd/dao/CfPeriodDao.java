package net.canang.cfi.core.dd.dao;

import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfPeriodDao {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    CfPeriod newInstance();

    CfPeriod findById(Long id);

    CfPeriod findCurrent(CfCostCenter costCenter);

    List<CfPeriod> find(Integer offset, Integer limit);

    List<CfPeriod> find(String filter, Integer offset, Integer limit);

    List<CfPeriod> find(String filter, String[] funds, Integer offset, Integer limit);

    List<CfPeriod> find(CfCostCenter costCenter, Integer offset, Integer limit);

    Integer count();

    Integer count(String filter);

    Integer count(String filter, String[] funds);

    Integer count(CfCostCenter costCenter);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    void save(CfPeriod budget, CfUser user);

    void update(CfPeriod budget, CfUser user);

    void deactivate(CfPeriod budget, CfUser user);

    void remove(CfPeriod budget, CfUser user);

}
