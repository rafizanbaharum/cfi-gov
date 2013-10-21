package net.canang.cfi.web.dd.client.model;

import com.extjs.gxt.ui.client.data.ModelData;
import net.canang.cfi.web.client.model.CodedModel;
import net.canang.cfi.web.so.client.model.PrincipalModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/20/13
 */
public class CostCenterModel extends CodedModel {

    public static final String LABEL = "Cost Center";
    public static final String STATE_ID = "costCenterModels";
    public static final String SHARED = "shared";
    public static final String ZONED = "zoned";
    public static final String ALLOW_NEGATIVE = "allowNegative";
    public static final String FUND = "fund";
    public static final String DEPARTMENT = "department";
    public static final String PROJECT = "project";
    public static final String SUB_PROJECT = "subProject";

    protected List<PrincipalModel> costCenterMembers = new ArrayList<PrincipalModel>();

    // compiler trick
    private FundCodeModel fund;
    private DepartmentCodeModel department;
    private ProjectCodeModel project;
    private SubProjectCodeModel subProject;

    public CostCenterModel() {
    }

    public CostCenterModel(Long id, String code, String description, String summary) {
        setId(id);
        setCode(code);
        setDescription(description);
        setSummary(summary);
    }

    public Boolean isShared() {
        return get(SHARED);
    }

    public void setShared(Boolean shared) {
        set(SHARED, shared);
    }

    public Boolean isZoned() {
        return get(ZONED);
    }

    public void setZoned(Boolean zoned) {
        set(ZONED, zoned);
    }

    public Boolean isAllowNegative() {
        return get(ALLOW_NEGATIVE);
    }

    public void setAllowNegative(Boolean allow) {
        set(ALLOW_NEGATIVE, allow);
    }

    public FundCodeModel getFund() {
        return get(FUND);
    }

    public void setFund(FundCodeModel fundModel) {
        set(FUND, fundModel);
    }

    public DepartmentCodeModel getDepartment() {
        return get(DEPARTMENT);
    }

    public void setDepartment(DepartmentCodeModel departmentModel) {
        set(DEPARTMENT, departmentModel);
    }

    public ProjectCodeModel getProject() {
        return get(PROJECT);
    }

    public void setProject(ProjectCodeModel projectModel) {
        set(PROJECT, projectModel);
    }

    public SubProjectCodeModel getSubProject() {
        return get(SUB_PROJECT);
    }

    public void setSubProject(SubProjectCodeModel subProjectModel) {
        set(SUB_PROJECT, subProjectModel);
    }

    @Override
    public String toString() {
        return getSummary();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof CostCenterModel) {
            CostCenterModel mobj = (CostCenterModel) obj;
            return getCode().equals(mobj.getCode());
        }
        return super.equals(obj);
    }

    public void add(ModelData child) {
        try {
            if (child instanceof PrincipalModel) {
                costCenterMembers.add((PrincipalModel) child);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(List childs) {
        for (Object child : childs) {
            add((ModelData) child);
        }
    }

    public List<PrincipalModel> getCostCenterMembers() {
        return costCenterMembers;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}


