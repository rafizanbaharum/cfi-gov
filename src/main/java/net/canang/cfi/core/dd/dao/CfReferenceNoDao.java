package net.canang.cfi.core.dd.dao;

import net.canang.cfi.core.dd.model.CfReferenceNo;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfReferenceNoDao {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    CfReferenceNo newInstance();

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    CfReferenceNo findById(Long id);

    CfReferenceNo findByCode(String code);

    List<CfReferenceNo> find(Integer offset, Integer limit);

    List<CfReferenceNo> find(String filter, Integer offset, Integer limit);

    Integer count();

    Integer count(String filter);

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    void save(CfReferenceNo referenceNo, CfUser user);

    void update(CfReferenceNo referenceNo, CfUser user);

    void deactivate(CfReferenceNo referenceNo, CfUser user);

    void remove(CfReferenceNo referenceNo, CfUser user);
}
