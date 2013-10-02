package net.canang.cfi.core.dd.model;

import net.canang.cfi.core.so.model.CfMetaObject;

import java.util.Date;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfPeriod extends CfMetaObject {

    String getDescription();

    void setDescription(String description);

    CfCostCenter getCostCenter();

    void setCostCenter(CfCostCenter costCenter);

    Date getStartDate();

    void setStartDate(Date startDate);

    Date getEndDate();

    void setEndDate(Date endDate);

    boolean isCurrent();

    void setCurrent(boolean current);

    String getSummary();

    String getShortSummary();

    String getLongSummary();

}
