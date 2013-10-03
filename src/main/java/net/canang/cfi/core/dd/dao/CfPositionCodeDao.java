package net.canang.cfi.core.dd.dao;

import net.canang.cfi.core.dd.model.CfPositionCode;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfPositionCodeDao {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    List<CfPositionCode> find();

    CfPositionCode findById(Long id);

    CfPositionCode findByCode(String code);

    CfPositionCode findByGrade(Integer grade);

    List<CfPositionCode> find(String filter, Integer offset, Integer limit);

    Integer count(String filter);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

}
