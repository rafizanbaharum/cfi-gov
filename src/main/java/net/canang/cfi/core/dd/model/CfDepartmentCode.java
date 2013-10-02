package net.canang.cfi.core.dd.model;

import net.canang.cfi.core.so.model.CfMetaObject;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfDepartmentCode extends CfMetaObject {

    String getCode();

    String getAlias();

    String getDescription();

    CfCampusCode getCampusCode();

    String getSummary();

}
