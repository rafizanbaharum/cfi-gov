package net.canang.cfi.core.so.dao;

import net.canang.cfi.core.so.model.CfMetaObject;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
public interface CfMetaObjectDao {

    CfMetaObject findObjectById(String entityName, Long id);

}
