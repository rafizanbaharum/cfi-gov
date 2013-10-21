package net.canang.cfi.core.dd.dao;

import net.canang.cfi.core.dd.model.CfUnitCode;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfUnitCodeDao {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    List<CfUnitCode> find();

    CfUnitCode findById(Long id);

    CfUnitCode findByCode(String code);

    List<CfUnitCode> find(Integer offset, Integer limit);

    List<CfUnitCode> find(String filter, Integer offset, Integer limit);

    Integer count();

    Integer count(String filter);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================


}
