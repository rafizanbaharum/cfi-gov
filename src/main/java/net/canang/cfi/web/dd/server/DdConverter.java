package net.canang.cfi.web.dd.server;

import net.canang.cfi.biz.dd.manager.DdFinder;
import net.canang.cfi.core.dd.model.*;
import net.canang.cfi.web.dd.client.model.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
@Component("ddConverter")
public class DdConverter {

    private static final Logger log = Logger.getLogger(DdConverter.class);

    @Autowired
    private DdFinder ddFinder;

    public SodoCodeModel convert(CfSodoCode sodo) {
        if (null == sodo) {
            log.warn("sodo is null");
            return null;
        }
        SodoCodeModel sodoModel = new SodoCodeModel();
        sodoModel.setId(sodo.getId());
        sodoModel.setCode(sodo.getCode());
        sodoModel.setDescription(sodo.getDescription());
        sodoModel.setSummary(sodo.getSummary());
        return sodoModel;
    }

    public UnitCodeModel convert(CfUnitCode unitCode) {
        if (null == unitCode) {
            log.warn("unit code is null");
            return null;
        }
        UnitCodeModel model = new UnitCodeModel();
        model.setId(unitCode.getId());
        model.setCode(unitCode.getCode());
        model.setDescription(unitCode.getDescription());
        model.setSummary(unitCode.getSummary());
        return model;
    }

    public PeriodModel convert(CfPeriod Period) {
        if (null == Period) {
            log.warn("Period is null");
            return null;
        }
        PeriodModel model = new PeriodModel();
        model.setDescription(Period.getDescription());
        model.setCostCenter(convert(Period.getCostCenter()));
        model.setId(Period.getId());
        model.setStartDate(Period.getStartDate());
        model.setEndDate(Period.getEndDate());
        model.setCurrent(Period.isCurrent());
        model.setSummary(Period.getSummary());
        model.setShortSummary(Period.getShortSummary());
        model.setLongSummary(Period.getLongSummary());
        return model;
    }

    public ProjectCodeModel convert(CfProjectCode project) {
        ProjectCodeModel model = new ProjectCodeModel();
        model.setId(project.getId());
        model.setCode(project.getCode());
        model.setDescription(project.getDescription());
        model.setSummary(project.getSummary());
        return model;
    }

    public SubProjectCodeModel convert(CfSubProjectCode subProject) {
        if (null == subProject) {
            log.warn("subproject is null");
            return null;
        }
        SubProjectCodeModel model = new SubProjectCodeModel();
        model.setId(subProject.getId());
        model.setCode(subProject.getCode());
        model.setDescription(subProject.getDescription());
        model.setSummary(subProject.getSummary());
        return model;
    }

    public FundCodeModel convert(CfFundCode fund) {
        if (null == fund) {
            log.warn("fund is null");
            return null;
        }
        FundCodeModel model = new FundCodeModel();
        model.setId(fund.getId());
        model.setCode(fund.getCode());
        model.setDescription(fund.getDescription());
        model.setSummary(fund.getSummary());
        return model;
    }

    public DepartmentCodeModel convert(CfDepartmentCode responsibilityCenter) {
        if (null == responsibilityCenter) {
            log.warn("responsibility center is null");
            return null;
        }
        DepartmentCodeModel model = new DepartmentCodeModel();
        model.setId(responsibilityCenter.getId());
        model.setCode(responsibilityCenter.getCode());
        model.setDescription(responsibilityCenter.getDescription());
        model.setSummary(responsibilityCenter.getSummary());
        return model;
    }

    public CostCenterModel convert(CfCostCenter costCenter) {
        if (null == costCenter) {
            log.warn("cost center is null");
            return null;
        }
        CostCenterModel model = new CostCenterModel();
        model.setId(costCenter.getId());
        model.setCode(costCenter.getCode());
        model.setDescription(costCenter.getDescription());
        model.setSummary(costCenter.getSummary());
        model.setFund(convert(costCenter.getFundCode()));
        model.setDepartment(convert(costCenter.getDepartmentCode()));
        model.setProject(convert(costCenter.getProjectCode()));
        model.setSubProject(convert(costCenter.getSubProjectCode()));
        return model;
    }

    public CountryCodeModel convert(CfCountryCode country) {
        if (null == country) {
            log.warn("country is null");
            return null;
        }
        CountryCodeModel model = new CountryCodeModel();
        model.setId(country.getId());
        model.setCode(country.getCode());
        model.setDescription(country.getDescription());
        model.setSummary(country.getCode() + " - " + country.getDescription());
        model.setCurrencyCode(convert(country.getCurrencyCode()));
        return model;
    }

    public CurrencyCodeModel convert(CfCurrencyCode currency) {
        if (null == currency) {
            log.warn("currency is null");
            return null;
        }
        CurrencyCodeModel model = new CurrencyCodeModel();
        model.setId(currency.getId());
        model.setCode(currency.getCode());
        model.setDescription(currency.getDescription());
        model.setSell(currency.getSell());
        model.setBuy(currency.getBuy());
        model.setSummary(currency.getCode() + " - " + currency.getDescription());
        return model;
    }

    public CityCodeModel convert(CfCityCode city) {
        CityCodeModel model = new CityCodeModel();
        model.setId(city.getId());
        model.setCode(city.getCode());
        model.setDescription(city.getDescription());
        model.setStateCode(convert(city.getStateCode()));
        model.setSummary(city.getCode() + " - " + city.getDescription());
        return model;
    }

    public StateCodeModel convert(CfStateCode state) {
        StateCodeModel model = new StateCodeModel();
        model.setId(state.getId());
        model.setCode(state.getCode());
        model.setDescription(state.getDescription());
        model.setSummary(state.getCode() + " - " + state.getDescription());
        return model;
    }

    public BankCodeModel convert(CfBankCode bank) {
        BankCodeModel model = new BankCodeModel();
        model.setId(bank.getId());
        model.setDescription(bank.getDescription());
        model.setCode(bank.getCode());
        model.setSummary(bank.getSummary());
        return model;
    }

    public AccountCodeModel convert(CfAccountCode accountCode) {
        AccountCodeModel model = new AccountCodeModel();
        model.setId(accountCode.getId());
        model.setCode(accountCode.getCode());
        model.setDescription(accountCode.getDescription());
        model.setSummary(accountCode.getCode() + " - " + accountCode.getDescription());
        return model;
    }

    public CampusCodeModel convert(CfCampusCode campus) {
        CampusCodeModel campusModel = new CampusCodeModel();
        campusModel.setId(campus.getId());
        campusModel.setCode(campus.getCode());
        campusModel.setDescription(campus.getDescription());
        campusModel.setSummary(campus.getCode() + " - " + campus.getSummary());
        return campusModel;
    }

    public PositionCodeModel convert(CfPositionCode positionCode) {
        PositionCodeModel model = new PositionCodeModel();
        model.setId(positionCode.getId());
        model.setCode(positionCode.getCode());
        model.setDescription(positionCode.getDescription());
        model.setSummary(positionCode.getSummary());
        model.setGrade(positionCode.getGrade());
        return model;
    }
}