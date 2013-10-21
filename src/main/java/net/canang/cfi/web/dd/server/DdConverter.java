package net.canang.cfi.web.dd.server;

import com.extjs.gxt.ui.client.data.BaseListLoadResult;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import net.canang.cfi.biz.dd.manager.DdFinder;
import net.canang.cfi.core.dd.model.*;
import net.canang.cfi.web.dd.client.model.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
    
    public PagingLoadResult<SodoCodeModel> convertToPagingSodoCodes(List<CfSodoCode> sodoCodes, Integer count, Integer offset) {
        List<SodoCodeModel> models = new ArrayList<SodoCodeModel>();
        for (CfSodoCode sodoCode : sodoCodes) {
            models.add(convert(sodoCode));
        }
        return new BasePagingLoadResult<SodoCodeModel>(models, offset, count);
    }

    public ListLoadResult<SodoCodeModel> convertToListSodoCodes(List<CfSodoCode> sodoCodes) {
        List<SodoCodeModel> models = new ArrayList<SodoCodeModel>();
        for (CfSodoCode sodoCode : sodoCodes) {
            models.add(convert(sodoCode));
        }
        return new BaseListLoadResult<SodoCodeModel>(models);
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
    
    public PagingLoadResult<PeriodModel> convertToPagingPeriods(List<CfPeriod> periods, Integer count, Integer offset) {
        List<PeriodModel> models = new ArrayList<PeriodModel>();
        for (CfPeriod period : periods) {
            models.add(convert(period));
        }
        return new BasePagingLoadResult<PeriodModel>(models, offset, count);
    }
    

    public ProjectCodeModel convert(CfProjectCode project) {
        ProjectCodeModel model = new ProjectCodeModel();
        model.setId(project.getId());
        model.setCode(project.getCode());
        model.setDescription(project.getDescription());
        model.setSummary(project.getSummary());
        return model;
    }

    public PagingLoadResult<ProjectCodeModel> convertToPagingProjectCodes(List<CfProjectCode> projectCodes, Integer count, Integer offset) {
        List<ProjectCodeModel> models = new ArrayList<ProjectCodeModel>();
        for (CfProjectCode projectCode : projectCodes) {
            models.add(convert(projectCode));
        }
        return new BasePagingLoadResult<ProjectCodeModel>(models, offset, count);
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

    public PagingLoadResult<SubProjectCodeModel> convertToPagingSubProjectCodes(List<CfSubProjectCode> projectCodes, Integer count, Integer offset) {
        List<SubProjectCodeModel> models = new ArrayList<SubProjectCodeModel>();
        for (CfSubProjectCode projectCode : projectCodes) {
            models.add(convert(projectCode));
        }
        return new BasePagingLoadResult<SubProjectCodeModel>(models, offset, count);
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

    public PagingLoadResult<FundCodeModel> convertToPagingFundCodes(List<CfFundCode> fundCodes, Integer count, Integer offset) {
        List<FundCodeModel> models = new ArrayList<FundCodeModel>();
        for (CfFundCode fundCode : fundCodes) {
            models.add(convert(fundCode));
        }
        return new BasePagingLoadResult<FundCodeModel>(models, offset, count);
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

    public PagingLoadResult<DepartmentCodeModel> convertToPagingDepartmentCodes(List<CfDepartmentCode> departmentCodes, Integer count, Integer offset) {
        List<DepartmentCodeModel> models = new ArrayList<DepartmentCodeModel>();
        for (CfDepartmentCode departmentCode : departmentCodes) {
            models.add(convert(departmentCode));
        }
        return new BasePagingLoadResult<DepartmentCodeModel>(models, offset, count);
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

    public PagingLoadResult<CostCenterModel> convertToPagingCostCenters(List<CfCostCenter> costCenters, Integer count, Integer offset) {
        List<CostCenterModel> models = new ArrayList<CostCenterModel>();
        for (CfCostCenter costCenter : costCenters) {
            models.add(convert(costCenter));
        }
        return new BasePagingLoadResult<CostCenterModel>(models, offset, count);
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

    public PagingLoadResult<CountryCodeModel> convertToPagingCountryCodes(List<CfCountryCode> countryCodes, Integer count, Integer offset) {
        List<CountryCodeModel> models = new ArrayList<CountryCodeModel>();
        for (CfCountryCode countryCode : countryCodes) {
            models.add(convert(countryCode));
        }
        return new BasePagingLoadResult<CountryCodeModel>(models, offset, count);
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

    public PagingLoadResult<CurrencyCodeModel> convertToPagingCurrencyCodes(List<CfCurrencyCode> currencyCodes, Integer count, Integer offset) {
        List<CurrencyCodeModel> models = new ArrayList<CurrencyCodeModel>();
        for (CfCurrencyCode currencyCode : currencyCodes) {
            models.add(convert(currencyCode));
        }
        return new BasePagingLoadResult<CurrencyCodeModel>(models, offset, count);
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

    public PagingLoadResult<CityCodeModel> convertToPagingCityCodes(List<CfCityCode> cityCodes, Integer count, Integer offset) {
        List<CityCodeModel> models = new ArrayList<CityCodeModel>();
        for (CfCityCode cityCode : cityCodes) {
            models.add(convert(cityCode));
        }
        return new BasePagingLoadResult<CityCodeModel>(models, offset, count);
    }


    public StateCodeModel convert(CfStateCode state) {
        StateCodeModel model = new StateCodeModel();
        model.setId(state.getId());
        model.setCode(state.getCode());
        model.setDescription(state.getDescription());
        model.setSummary(state.getCode() + " - " + state.getDescription());
        return model;
    }

    public PagingLoadResult<StateCodeModel> convertToPagingStateCodes(List<CfStateCode> stateCodes, Integer count, Integer offset) {
        List<StateCodeModel> models = new ArrayList<StateCodeModel>();
        for (CfStateCode stateCode : stateCodes) {
            models.add(convert(stateCode));
        }
        return new BasePagingLoadResult<StateCodeModel>(models, offset, count);
    }


    public BankCodeModel convert(CfBankCode bank) {
        BankCodeModel model = new BankCodeModel();
        model.setId(bank.getId());
        model.setDescription(bank.getDescription());
        model.setCode(bank.getCode());
        model.setSummary(bank.getSummary());
        return model;
    }

    public PagingLoadResult<BankCodeModel> convertToPagingBankCodes(List<CfBankCode> bankCodes, Integer count, Integer offset) {
        List<BankCodeModel> models = new ArrayList<BankCodeModel>();
        for (CfBankCode bankCode : bankCodes) {
            models.add(convert(bankCode));
        }
        return new BasePagingLoadResult<BankCodeModel>(models, offset, count);
    }


    public AccountCodeModel convert(CfAccountCode accountCode) {
        AccountCodeModel model = new AccountCodeModel();
        model.setId(accountCode.getId());
        model.setCode(accountCode.getCode());
        model.setDescription(accountCode.getDescription());
        model.setSummary(accountCode.getCode() + " - " + accountCode.getDescription());
        return model;
    }
    
    public PagingLoadResult<AccountCodeModel> convertToPagingAccountCodes(List<CfAccountCode> accountCodes, Integer count, Integer offset) {
        List<AccountCodeModel> models = new ArrayList<AccountCodeModel>();
        for (CfAccountCode accountCode : accountCodes) {
            models.add(convert(accountCode));
        }
        return new BasePagingLoadResult<AccountCodeModel>(models, offset, count);
    }
    

    public CampusCodeModel convert(CfCampusCode campus) {
        CampusCodeModel campusModel = new CampusCodeModel();
        campusModel.setId(campus.getId());
        campusModel.setCode(campus.getCode());
        campusModel.setDescription(campus.getDescription());
        campusModel.setSummary(campus.getCode() + " - " + campus.getSummary());
        return campusModel;
    }
    
    public PagingLoadResult<CampusCodeModel> convertToPagingCampusCodes(List<CfCampusCode> campusCodes, Integer count, Integer offset) {
        List<CampusCodeModel> models = new ArrayList<CampusCodeModel>();
        for (CfCampusCode campusCode : campusCodes) {
            models.add(convert(campusCode));
        }
        return new BasePagingLoadResult<CampusCodeModel>(models, offset, count);
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

    public PagingLoadResult<PositionCodeModel> convertToPagingPositionCodes(List<CfPositionCode> positionCodes, Integer count, Integer offset) {
        List<PositionCodeModel> models = new ArrayList<PositionCodeModel>();
        for (CfPositionCode positionCode : positionCodes) {
            models.add(convert(positionCode));
        }
        return new BasePagingLoadResult<PositionCodeModel>(models, offset, count);
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

    public PagingLoadResult<UnitCodeModel> convertToPagingUnitCodes(List<CfUnitCode> unitCodes, Integer count, Integer offset) {
        List<UnitCodeModel> models = new ArrayList<UnitCodeModel>();
        for (CfUnitCode unitCode : unitCodes) {
            models.add(convert(unitCode));
        }
        return new BasePagingLoadResult<UnitCodeModel>(models, offset, count);
    }

}