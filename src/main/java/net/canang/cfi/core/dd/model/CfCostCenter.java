package net.canang.cfi.core.dd.model;

import net.canang.cfi.core.so.model.CfMetaObject;

import java.util.List;


/**
 * NOTE:
 * format: [fundCode].[departmentCode].[projectCode].[subProjectCode]
 */
public interface CfCostCenter extends CfMetaObject {

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

    CfFundCode getFundCode();

    void setFundCode(CfFundCode fund);

    CfDepartmentCode getDepartmentCode();

    void setDepartmentCode(CfDepartmentCode departmentCode);

    CfProjectCode getProjectCode();

    void setProjectCode(CfProjectCode project);

    CfSubProjectCode getSubProjectCode();

    void setSubProjectCode(CfSubProjectCode subProject);

    List<CfPeriod> getPeriods();

    void setPeriods(List<CfPeriod> periods);

    CfPeriod getCurrentPeriod();

    String getSummary();
}
