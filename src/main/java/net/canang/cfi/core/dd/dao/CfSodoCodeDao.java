package net.canang.cfi.core.dd.dao;

import net.canang.cfi.core.dd.model.CfAccountCode;
import net.canang.cfi.core.dd.model.CfSodoCode;
import net.canang.cfi.core.so.model.CfUser;

import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfSodoCodeDao {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    CfSodoCode findById(Long id);

    CfSodoCode findByCode(String code);

    List<CfSodoCode> findByParent(CfSodoCode parent);

    List<CfSodoCode> findRoots();

    List<CfSodoCode> findRoots(CfAccountCode code);

    List<CfSodoCode> find(Integer offset, Integer limit);

    List<CfSodoCode> findLevel12();

    List<CfSodoCode> findLevel23();

    List<CfSodoCode> find(Integer level, Integer offset, Integer limit);

    List<CfSodoCode> find(CfSodoCode parent, Integer offset, Integer limit);

    List<CfSodoCode> find(String filter, Integer offset, Integer limit);

    List<CfSodoCode> find(Integer level, String filter, Integer offset, Integer limit);

    Integer count();

    Integer count(CfSodoCode parent);

    Integer count(String filter);

    Integer count(Integer level, String filter);

    boolean hasChildren(CfSodoCode parent);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    void save(CfSodoCode sodo, CfUser user);

    void update(CfSodoCode sodo, CfUser user);

    void deactivate(CfSodoCode sodo, CfUser user);

    void remove(CfSodoCode sodo, CfUser user);

}
