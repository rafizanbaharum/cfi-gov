package net.canang.cfi.web.dd.client;

import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import net.canang.cfi.web.dd.client.model.*;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
public interface DdDelegateAsync {

    // ==================================================================================================== //
    // FUND
    // ==================================================================================================== //

    void findFundCodeById(Long id, AsyncCallback<FundCodeModel> async);

    void findFundCodeByCode(String code, AsyncCallback<FundCodeModel> async);

    void findFundCodes(Integer offset, Integer limit, AsyncCallback<PagingLoadResult<FundCodeModel>> async);

    void findFundCodes(String filter, String funds, Integer offset, Integer limit, AsyncCallback<PagingLoadResult<FundCodeModel>> async);

    // ==================================================================================================== //
    // CURRENCY
    // ==================================================================================================== //

    void findCurrencyCodeById(Long id, AsyncCallback<CurrencyCodeModel> async);

    void findCurrencyCodeByCode(String code, AsyncCallback<CurrencyCodeModel> async);

    void findCurrencyCodes(String filter, Integer offset, Integer limit, AsyncCallback<PagingLoadResult<CurrencyCodeModel>> async);

    void findCurrencyCodes(Integer offset, Integer limit, AsyncCallback<PagingLoadResult<CurrencyCodeModel>> async);

    // ==================================================================================================== //
    // PERIOD
    // ==================================================================================================== //

    void findPeriods(CostCenterModel costCenterModel, Integer offset, Integer limit, AsyncCallback<PagingLoadResult<PeriodModel>> async);

    void findPeriods(String filter, String funds, Integer offset, Integer limit, AsyncCallback<PagingLoadResult<PeriodModel>> async);

    void findPeriods(String filter, Integer offset, Integer limit, AsyncCallback<PagingLoadResult<PeriodModel>> async);

    void findPeriods(Integer offset, Integer limit, AsyncCallback<PagingLoadResult<PeriodModel>> async);

    // ==================================================================================================== //
    // UNIT
    // ==================================================================================================== //

    void findBankCodeById(Long id, AsyncCallback<BankCodeModel> async);

    void findBankCodeByCode(String code, AsyncCallback<BankCodeModel> async);

    void findBankCodes(String filter, Integer offset, Integer limit, AsyncCallback<PagingLoadResult<BankCodeModel>> async);

    void findBankCodes(Integer offset, Integer limit, AsyncCallback<PagingLoadResult<BankCodeModel>> async);

    // ==================================================================================================== //
    // CAMPUS
    // ==================================================================================================== //

    void findCampusCodeById(Long id, AsyncCallback<CampusCodeModel> async);

    void findCampusCodeByCode(String code, AsyncCallback<CampusCodeModel> async);

    void findCampusCodes(String filter, Integer offset, Integer limit, AsyncCallback<PagingLoadResult<CampusCodeModel>> async);

    void findCampusCodes(Integer offset, Integer limit, AsyncCallback<PagingLoadResult<CampusCodeModel>> async);

    // ==================================================================================================== //
    // DEPARTMENT
    // ==================================================================================================== //

    void findDepartmentCodeById(Long id, AsyncCallback<DepartmentCodeModel> async);

    void findDepartmentCodeByCode(String code, AsyncCallback<DepartmentCodeModel> async);

    void findDepartmentCodes(String filter, Integer offset, Integer limit, AsyncCallback<PagingLoadResult<DepartmentCodeModel>> async);

    void findDepartmentCodes(Integer offset, Integer limit, AsyncCallback<PagingLoadResult<DepartmentCodeModel>> async);

    // ==================================================================================================== //
    // PROJECT
    // ==================================================================================================== //

    void findProjectCodeById(Long id, AsyncCallback<ProjectCodeModel> async);

    void findProjectCodeByCode(String code, AsyncCallback<ProjectCodeModel> async);

    void findProjectCodes(String filter, Integer offset, Integer limit, AsyncCallback<PagingLoadResult<ProjectCodeModel>> async);

    void findProjectCodes(Integer offset, Integer limit, AsyncCallback<PagingLoadResult<ProjectCodeModel>> async);

    // ==================================================================================================== //
    // SUB PROJECT
    // ==================================================================================================== //

    void findSubProjectCodeById(Long id, AsyncCallback<SubProjectCodeModel> async);

    void findSubProjectCodeByCode(String code, AsyncCallback<SubProjectCodeModel> async);

    void findSubProjectCodes(String filter, Integer offset, Integer limit, AsyncCallback<PagingLoadResult<SubProjectCodeModel>> async);

    void findSubProjectCodes(Integer offset, Integer limit, AsyncCallback<PagingLoadResult<SubProjectCodeModel>> async);

    // ==================================================================================================== //
    // COST CENTER
    // ==================================================================================================== //

    void findCostCenterById(Long id, AsyncCallback<CostCenterModel> async);

    void findCostCenterByCode(String code, AsyncCallback<CostCenterModel> async);

    void findCostCenters(String filter, Integer offset, Integer limit, AsyncCallback<PagingLoadResult<CostCenterModel>> async);

    void findCostCenters(Integer offset, Integer limit, AsyncCallback<PagingLoadResult<CostCenterModel>> async);

    // ==================================================================================================== //
    // CITY
    // ==================================================================================================== //

    void findCityCodeById(Long id, AsyncCallback<CityCodeModel> async);

    void findCityCodeByCode(String code, AsyncCallback<CityCodeModel> async);

    void findCityCodes(StateCodeModel StateCodeModel, String filter, Integer offset, Integer limit, AsyncCallback<PagingLoadResult<CityCodeModel>> async);

    void findCityCodes(String filter, Integer offset, Integer limit, AsyncCallback<PagingLoadResult<CityCodeModel>> async);

    void findCityCodes(Integer offset, Integer limit, AsyncCallback<PagingLoadResult<CityCodeModel>> async);

    // ==================================================================================================== //
    // STATE
    // ==================================================================================================== //

    void findStateCodeById(Long id, AsyncCallback<StateCodeModel> async);

    void findStateCodeByCode(String code, AsyncCallback<StateCodeModel> async);

    void findStateCodes(String filter, Integer offset, Integer limit, AsyncCallback<PagingLoadResult<StateCodeModel>> async);

    void findStateCodes(Integer offset, Integer limit, AsyncCallback<PagingLoadResult<StateCodeModel>> async);


    // ==================================================================================================== //
    // COUNTRY
    // ==================================================================================================== //

    void findCountryCodeById(Long id, AsyncCallback<CountryCodeModel> async);

    void findCountryCodeByCode(String code, AsyncCallback<CountryCodeModel> async);

    void findCountryCodes(String filter, Integer offset, Integer limit, AsyncCallback<PagingLoadResult<CountryCodeModel>> async);

    void findCountryCodes(Integer offset, Integer limit, AsyncCallback<PagingLoadResult<CountryCodeModel>> async);

    // ==================================================================================================== //
    // ACCOUNT
    // ==================================================================================================== //

    void findAccountCodeById(Long id, AsyncCallback<AccountCodeModel> async);

    void findAccountCodeByCode(String code, AsyncCallback<AccountCodeModel> async);

    void findAccountCodes(String filter, Integer offset, Integer limit, AsyncCallback<PagingLoadResult<AccountCodeModel>> async);

    void findAccountCodes(Integer offset, Integer limit, AsyncCallback<PagingLoadResult<AccountCodeModel>> async);

    // ==================================================================================================== //
    // SODO
    // ==================================================================================================== //

    void findSodoCodeById(Long id, AsyncCallback<SodoCodeModel> async);

    void findSodoCodeByCode(String code, AsyncCallback<SodoCodeModel> async);

    void findSodoCodes(Integer level, String filter, Integer offset, Integer limit, AsyncCallback<PagingLoadResult<SodoCodeModel>> async);

    void findSodoCodes(String filter, Integer offset, Integer limit, AsyncCallback<PagingLoadResult<SodoCodeModel>> async);

    void findSodoCodes(SodoCodeModel parent, Integer offset, Integer limit, AsyncCallback<PagingLoadResult<SodoCodeModel>> async);

    void findSodoCodes(Integer offset, Integer limit, AsyncCallback<PagingLoadResult<SodoCodeModel>> async);

    void findSodoCodes(SodoCodeModel parent, AsyncCallback<ListLoadResult<SodoCodeModel>> async);

    void findSodoCodeRoots(AsyncCallback<ListLoadResult<SodoCodeModel>> async);


    // ==================================================================================================== //
    // POSITION
    // ==================================================================================================== //

    void findPositionCodes(String filter, Integer offset, Integer limit, AsyncCallback<PagingLoadResult<PositionCodeModel>> async);

    void findPositionCodes(Integer offset, Integer limit, AsyncCallback<PagingLoadResult<PositionCodeModel>> async);

    // ==================================================================================================== //
    // UNIT
    // ==================================================================================================== //

    void findUnitCodes(String filter, Integer offset, Integer limit, AsyncCallback<PagingLoadResult<UnitCodeModel>> async);

    void findUnitCodes(Integer offset, Integer limit, AsyncCallback<PagingLoadResult<UnitCodeModel>> async);

}
