package net.canang.cfi.web.dd.client;

import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import net.canang.cfi.web.client.exception.CrudException;
import net.canang.cfi.web.dd.client.model.*;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
public interface DdDelegate extends RemoteService {

    // ==================================================================================================== //
    // COUNTRY
    // ==================================================================================================== //

    CountryCodeModel findCountryCodeById(Long id);

    CountryCodeModel findCountryCodeByCode(String code);

    PagingLoadResult<CountryCodeModel> findCountryCodes(Integer offset, Integer limit);

    PagingLoadResult<CountryCodeModel> findCountryCodes(String filter, Integer offset, Integer limit);

    // ==================================================================================================== //
    // STATE
    // ==================================================================================================== //

    StateCodeModel findStateCodeById(Long id);

    StateCodeModel findStateCodeByCode(String code);

    PagingLoadResult<StateCodeModel> findStateCodes(Integer offset, Integer limit);

    PagingLoadResult<StateCodeModel> findStateCodes(String filter, Integer offset, Integer limit);

    // ==================================================================================================== //
    // CITY
    // ==================================================================================================== //

    CityCodeModel findCityCodeById(Long id);

    CityCodeModel findCityCodeByCode(String code);

    PagingLoadResult<CityCodeModel> findCityCodes(Integer offset, Integer limit);

    PagingLoadResult<CityCodeModel> findCityCodes(String filter, Integer offset, Integer limit);

    PagingLoadResult<CityCodeModel> findCityCodes(StateCodeModel StateCodeModel, String filter, Integer offset, Integer limit);


    // ==================================================================================================== //
    // CURRENCY
    // ==================================================================================================== //

    CurrencyCodeModel findCurrencyCodeById(Long id);

    CurrencyCodeModel findCurrencyCodeByCode(String code);

    PagingLoadResult<CurrencyCodeModel> findCurrencyCodes(Integer offset, Integer limit);

    PagingLoadResult<CurrencyCodeModel> findCurrencyCodes(String filter, Integer offset, Integer limit);

    // ==================================================================================================== //
    // FUND
    // ==================================================================================================== //

    FundCodeModel findFundCodeById(Long id);

    FundCodeModel findFundCodeByCode(String code);

    PagingLoadResult<FundCodeModel> findFundCodes(Integer offset, Integer limit);

    PagingLoadResult<FundCodeModel> findFundCodes(String filter, String funds, Integer offset, Integer limit);

    // ==================================================================================================== //
    // CAMPUS
    // ==================================================================================================== //

    CampusCodeModel findCampusCodeById(Long id);

    CampusCodeModel findCampusCodeByCode(String code);

    PagingLoadResult<CampusCodeModel> findCampusCodes(Integer offset, Integer limit);

    PagingLoadResult<CampusCodeModel> findCampusCodes(String filter, Integer offset, Integer limit);

    // ==================================================================================================== //
    // DEPARTMENT
    // ==================================================================================================== //

    DepartmentCodeModel findDepartmentCodeById(Long id);

    DepartmentCodeModel findDepartmentCodeByCode(String code);

    PagingLoadResult<DepartmentCodeModel> findDepartmentCodes(Integer offset, Integer limit);

    PagingLoadResult<DepartmentCodeModel> findDepartmentCodes(String filter, Integer offset, Integer limit);

    // ==================================================================================================== //
    // PROJECT
    // ==================================================================================================== //

    ProjectCodeModel findProjectCodeById(Long id);

    ProjectCodeModel findProjectCodeByCode(String code);

    PagingLoadResult<ProjectCodeModel> findProjectCodes(Integer offset, Integer limit);

    PagingLoadResult<ProjectCodeModel> findProjectCodes(String filter, Integer offset, Integer limit);

    // ==================================================================================================== //
    // SUB PROJECT
    // ==================================================================================================== //

    SubProjectCodeModel findSubProjectCodeById(Long id);

    SubProjectCodeModel findSubProjectCodeByCode(String code);

    PagingLoadResult<SubProjectCodeModel> findSubProjectCodes(Integer offset, Integer limit);

    PagingLoadResult<SubProjectCodeModel> findSubProjectCodes(String filter, Integer offset, Integer limit);

    // ==================================================================================================== //
    // COST CENTER
    // ==================================================================================================== //

    CostCenterModel findCostCenterById(Long id);

    CostCenterModel findCostCenterByCode(String code);

    PagingLoadResult<CostCenterModel> findCostCenters(Integer offset, Integer limit);

    PagingLoadResult<CostCenterModel> findCostCenters(String filter, Integer offset, Integer limit);

    // ==================================================================================================== //
    // BANK
    // ==================================================================================================== //

    BankCodeModel findBankCodeById(Long id);

    BankCodeModel findBankCodeByCode(String code);

    PagingLoadResult<BankCodeModel> findBankCodes(Integer offset, Integer limit);

    PagingLoadResult<BankCodeModel> findBankCodes(String filter, Integer offset, Integer limit);

    // ==================================================================================================== //
    // PERIOD
    // ==================================================================================================== //

    PagingLoadResult<PeriodModel> findPeriods(Integer offset, Integer limit);

    PagingLoadResult<PeriodModel> findPeriods(String filter, Integer offset, Integer limit);

    PagingLoadResult<PeriodModel> findPeriods(String filter, String funds, Integer offset, Integer limit);

    PagingLoadResult<PeriodModel> findPeriods(CostCenterModel costCenterModel, Integer offset, Integer limit);

    // ==================================================================================================== //
    // ACCOUNT CODE
    // ==================================================================================================== //

    AccountCodeModel findAccountCodeById(Long id);

    AccountCodeModel findAccountCodeByCode(String code);

    PagingLoadResult<AccountCodeModel> findAccountCodes(Integer offset, Integer limit);

    PagingLoadResult<AccountCodeModel> findAccountCodes(String filter, Integer offset, Integer limit);

    // ==================================================================================================== //
    // SODO CODE
    // ==================================================================================================== //

    SodoCodeModel findSodoCodeById(Long id);

    SodoCodeModel findSodoCodeByCode(String code);

    ListLoadResult<SodoCodeModel> findSodoCodeRoots();

    ListLoadResult<SodoCodeModel> findSodoCodes(SodoCodeModel parent);

    PagingLoadResult<SodoCodeModel> findSodoCodes(Integer offset, Integer limit);

    PagingLoadResult<SodoCodeModel> findSodoCodes(SodoCodeModel parent, Integer offset, Integer limit);

    PagingLoadResult<SodoCodeModel> findSodoCodes(String filter, Integer offset, Integer limit);

    PagingLoadResult<SodoCodeModel> findSodoCodes(Integer level, String filter, Integer offset, Integer limit);

    // ==================================================================================================== //
    // UNIT
    // ==================================================================================================== //

    PagingLoadResult<UnitCodeModel> findUnitCodes(Integer offset, Integer limit);

    PagingLoadResult<UnitCodeModel> findUnitCodes(String filter, Integer offset, Integer limit);

    // ==================================================================================================== //
    // POSITION
    // ==================================================================================================== //

    PagingLoadResult<PositionCodeModel> findPositionCodes(Integer offset, Integer limit);

    PagingLoadResult<PositionCodeModel> findPositionCodes(String filter, Integer offset, Integer limit);

}
