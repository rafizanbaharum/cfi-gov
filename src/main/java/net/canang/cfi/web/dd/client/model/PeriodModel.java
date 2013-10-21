package net.canang.cfi.web.dd.client.model;

import net.canang.cfi.web.client.model.MetaModel;

import java.util.Date;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
public class PeriodModel extends MetaModel {

    public static final String ID = "id";
    public static final String DESCRIPTION = "description";
    public static final String SUMMARY = "summary";
    public static final String SHORT_SUMMARY = "shortSummary";
    public static final String LONG_SUMMARY = "longSummary";
    public static final String COST_CENTER = "costCenter";
    public static final String START_DATE = "startDate";
    public static final String END_DATE = "endDate";
    public static final String IS_CURRENT = "isCurrent";

    private CostCenterModel costCenterModel; // dummy to trick compiler

    public PeriodModel() {

    }

    public Long getId() {
        return get(ID);
    }

    public void setId(Long id) {
        set(ID, id);
    }

    public String getDescription() {
        return get(DESCRIPTION);
    }

    public void setDescription(String description) {
        set(DESCRIPTION, description);
    }

    public String getSummary() {
        return get(SUMMARY);
    }

    public void setSummary(String summary) {
        set(SUMMARY, summary);
    }

    public String getShortSummary() {
        return get(SHORT_SUMMARY);
    }

    public void setShortSummary(String shortSummary) {
        set(SHORT_SUMMARY, shortSummary);
    }

    public String getLongSummary() {
        return get(LONG_SUMMARY);
    }

    public void setLongSummary(String longSummary) {
        set(LONG_SUMMARY, longSummary);
    }

    public CostCenterModel getCostCenter() {
        return get(COST_CENTER);
    }

    public void setCostCenter(CostCenterModel costCenter) {
        set(COST_CENTER, costCenter);
    }

    public Date getStartDate() {
        return get(START_DATE);
    }

    public void setStartDate(Date startDate) {
        set(START_DATE, startDate);
    }

    public Date getEndDate() {
        return get(END_DATE);
    }

    public void setEndDate(Date endDate) {
        set(END_DATE, endDate);
    }

    public void setCurrent(Boolean current) {
        set(IS_CURRENT, current);
    }

    public Boolean isCurrent() {
        return get(IS_CURRENT);
    }

    @Override
    public String toString() {
        return getSummary();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof PeriodModel) {
            PeriodModel mobj = (PeriodModel) obj;
            return getId().equals(mobj.getId());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

}
