package net.canang.cfi.web.dd.server;

import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import net.canang.cfi.biz.dd.manager.DdFinder;
import net.canang.cfi.biz.dd.manager.DdManager;
import net.canang.cfi.biz.so.manager.SoFinder;
import net.canang.cfi.biz.so.manager.SoManager;
import net.canang.cfi.core.dd.model.CfSodoCode;
import net.canang.cfi.core.dd.model.CfStateCode;
import net.canang.cfi.web.dd.client.DdDelegate;
import net.canang.cfi.web.dd.client.model.*;
import net.canang.cfi.web.server.common.AutoInjectingRemoteServiceServlet;
import net.canang.cfi.web.so.server.SoConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
public class DdDelegateImpl extends AutoInjectingRemoteServiceServlet implements DdDelegate {

    private static final Logger log = Logger.getLogger(DdDelegateImpl.class);

    @Autowired
    private DdManager ddManager;

    @Autowired
    private DdFinder ddFinder;

    @Autowired
    private DdConverter ddConverter;

    @Autowired
    private SoManager soManager;

    @Autowired
    private SoFinder soFinder;

    @Autowired
    private SoConverter soConverter;

    @Autowired
    private AuthenticationManager authenticationManager;

    // ==================================================================================================== //
    // COUNTRY
    // ==================================================================================================== //

    @Override
    public CountryCodeModel findCountryCodeById(Long id) {
        return ddConverter.convert(ddFinder.findCountryCodeById(id));
    }

    @Override
    public CountryCodeModel findCountryCodeByCode(String code) {
        return ddConverter.convert(ddFinder.findCountryCodeByCode(code));
    }

    @Override
    public PagingLoadResult<CountryCodeModel> findCountryCodes(Integer offset, Integer limit) {
        return ddConverter.convertToPagingCountryCodes(
                ddFinder.findCountryCodes(offset, limit),
                ddFinder.countCountryCode(),
                offset);
    }

    @Override
    public PagingLoadResult<CountryCodeModel> findCountryCodes(String filter, Integer offset, Integer limit) {
        return ddConverter.convertToPagingCountryCodes(
                ddFinder.findCountryCodes(filter, offset, limit),
                ddFinder.countCountryCode(filter),
                offset);
    }

    // ==================================================================================================== //
    // STATE
    // ==================================================================================================== //

    @Override
    public StateCodeModel findStateCodeById(Long id) {
        return ddConverter.convert(ddFinder.findStateCodeById(id));
    }

    @Override
    public StateCodeModel findStateCodeByCode(String code) {
        return ddConverter.convert(ddFinder.findStateCodeByCode(code));
    }

    @Override
    public PagingLoadResult<StateCodeModel> findStateCodes(Integer offset, Integer limit) {
        return ddConverter.convertToPagingStateCodes(
                ddFinder.findStateCodes(offset, limit),
                ddFinder.countStateCode(),
                offset);
    }

    @Override
    public PagingLoadResult<StateCodeModel> findStateCodes(String filter, Integer offset, Integer limit) {
        return ddConverter.convertToPagingStateCodes(
                ddFinder.findStateCodes(offset, limit),
                ddFinder.countStateCode(),
                offset);
    }

    // ==================================================================================================== //
    // CITY
    // ==================================================================================================== //

    @Override
    public CityCodeModel findCityCodeById(Long id) {
        return ddConverter.convert(ddFinder.findCityCodeById(id));
    }

    @Override
    public CityCodeModel findCityCodeByCode(String code) {
        return ddConverter.convert(ddFinder.findCityCodeByCode(code));
    }

    @Override
    public PagingLoadResult<CityCodeModel> findCityCodes(Integer offset, Integer limit) {
        return ddConverter.convertToPagingCityCodes(
                ddFinder.findCityCodes(offset, limit),
                ddFinder.countCityCode(),
                offset);
    }

    @Override
    public PagingLoadResult<CityCodeModel> findCityCodes(String filter, Integer offset, Integer limit) {
        return ddConverter.convertToPagingCityCodes(
                ddFinder.findCityCodes(filter, offset, limit),
                ddFinder.countCityCode(filter),
                offset);
    }

    @Override
    public PagingLoadResult<CityCodeModel> findCityCodes(StateCodeModel stateCodeModel, String filter, Integer offset, Integer limit) {
        CfStateCode stateCode = ddFinder.findStateCodeById(stateCodeModel.getId());
        return ddConverter.convertToPagingCityCodes(
                ddFinder.findCityCodes(stateCode, filter, offset, limit),
                ddFinder.countCityCode(stateCode, filter),
                offset);
    }

    // ==================================================================================================== //
    // CURRENCY
    // ==================================================================================================== //

    @Override
    public CurrencyCodeModel findCurrencyCodeById(Long id) {
        return ddConverter.convert(ddFinder.findCurrencyCodeById(id));
    }

    @Override
    public CurrencyCodeModel findCurrencyCodeByCode(String code) {
        return ddConverter.convert(ddFinder.findCurrencyCodeByCode(code));
    }

    @Override
    public PagingLoadResult<CurrencyCodeModel> findCurrencyCodes(Integer offset, Integer limit) {
        return ddConverter.convertToPagingCurrencyCodes(
                ddFinder.findCurrencyCodes(offset, limit),
                ddFinder.countCurrencyCode(),
                offset);
    }

    @Override
    public PagingLoadResult<CurrencyCodeModel> findCurrencyCodes(String filter, Integer offset, Integer limit) {
        return ddConverter.convertToPagingCurrencyCodes(
                ddFinder.findCurrencyCodes(filter, offset, limit),
                ddFinder.countCurrencyCode(filter),
                offset);
    }

    // ==================================================================================================== //
    // FUND
    // ==================================================================================================== //

    @Override
    public FundCodeModel findFundCodeById(Long id) {
        return ddConverter.convert(ddFinder.findFundCodeById(id));
    }

    @Override
    public FundCodeModel findFundCodeByCode(String code) {
        return ddConverter.convert(ddFinder.findFundCodeByCode(code));
    }

    @Override
    public PagingLoadResult<FundCodeModel> findFundCodes(Integer offset, Integer limit) {
        return ddConverter.convertToPagingFundCodes(
                ddFinder.findFundCodes(offset, limit),
                ddFinder.countFundCode(),
                offset);
    }

    @Override
    public PagingLoadResult<FundCodeModel> findFundCodes(String filter, String funds, Integer offset, Integer limit) {
        return ddConverter.convertToPagingFundCodes(
                ddFinder.findFundCodes(filter, funds.split(","), offset, limit),
                ddFinder.countFundCode(filter),
                offset);
    }

    // ==================================================================================================== //
    // CURRENCY
    // ==================================================================================================== //

    @Override
    public CampusCodeModel findCampusCodeById(Long id) {
        return ddConverter.convert(ddFinder.findCampusCodeById(id));
    }

    @Override
    public CampusCodeModel findCampusCodeByCode(String code) {
        return ddConverter.convert(ddFinder.findCampusCodeByCode(code));
    }

    @Override
    public PagingLoadResult<CampusCodeModel> findCampusCodes(Integer offset, Integer limit) {
        return ddConverter.convertToPagingCampusCodes(
                ddFinder.findCampusCodes(offset, limit),
                ddFinder.countCampusCode(),
                offset);
    }

    @Override
    public PagingLoadResult<CampusCodeModel> findCampusCodes(String filter, Integer offset, Integer limit) {
        return ddConverter.convertToPagingCampusCodes(
                ddFinder.findCampusCodes(filter, offset, limit),
                ddFinder.countCampusCode(filter),
                offset);
    }

    // ==================================================================================================== //
    // CURRENCY
    // ==================================================================================================== //

    @Override
    public DepartmentCodeModel findDepartmentCodeById(Long id) {
        return ddConverter.convert(ddFinder.findDepartmentCodeById(id));
    }

    @Override
    public DepartmentCodeModel findDepartmentCodeByCode(String code) {
        return ddConverter.convert(ddFinder.findDepartmentCodeByCode(code));
    }

    @Override
    public PagingLoadResult<DepartmentCodeModel> findDepartmentCodes(Integer offset, Integer limit) {
        return ddConverter.convertToPagingDepartmentCodes(
                ddFinder.findDepartmentCodes(offset, limit),
                ddFinder.countDepartmentCode(),
                offset);

    }

    @Override
    public PagingLoadResult<DepartmentCodeModel> findDepartmentCodes(String filter, Integer offset, Integer limit) {
        return ddConverter.convertToPagingDepartmentCodes(
                ddFinder.findDepartmentCodes(filter, offset, limit),
                ddFinder.countDepartmentCode(filter),
                offset);
    }

    // ==================================================================================================== //
    // CURRENCY
    // ==================================================================================================== //


    @Override
    public ProjectCodeModel findProjectCodeById(Long id) {
        return ddConverter.convert(ddFinder.findProjectCodeById(id));

    }

    @Override
    public ProjectCodeModel findProjectCodeByCode(String code) {
        return ddConverter.convert(ddFinder.findProjectCodeByCode(code));
    }

    @Override
    public PagingLoadResult<ProjectCodeModel> findProjectCodes(Integer offset, Integer limit) {
        return ddConverter.convertToPagingProjectCodes(
                ddFinder.findProjectCodes(offset, limit),
                ddFinder.countProjectCode(),
                offset);
    }

    @Override
    public PagingLoadResult<ProjectCodeModel> findProjectCodes(String filter, Integer offset, Integer limit) {
        return ddConverter.convertToPagingProjectCodes(
                ddFinder.findProjectCodes(filter, offset, limit),
                ddFinder.countProjectCode(filter),
                offset);
    }

    // ==================================================================================================== //
    // CURRENCY
    // ==================================================================================================== //

    @Override
    public SubProjectCodeModel findSubProjectCodeById(Long id) {
        return ddConverter.convert(ddFinder.findSubProjectCodeById(id));

    }

    @Override
    public SubProjectCodeModel findSubProjectCodeByCode(String code) {
        return ddConverter.convert(ddFinder.findSubProjectCodeByCode(code));

    }

    @Override
    public PagingLoadResult<SubProjectCodeModel> findSubProjectCodes(Integer offset, Integer limit) {
        return ddConverter.convertToPagingSubProjectCodes(
                ddFinder.findSubProjectCodes(offset, limit),
                ddFinder.countSubProjectCode(),
                offset);
    }

    @Override
    public PagingLoadResult<SubProjectCodeModel> findSubProjectCodes(String filter, Integer offset, Integer limit) {
        return ddConverter.convertToPagingSubProjectCodes(
                ddFinder.findSubProjectCodes(offset, limit),
                ddFinder.countSubProjectCode(),
                offset);
    }

    // ==================================================================================================== //
    // CURRENCY
    // ==================================================================================================== //


    @Override
    public CostCenterModel findCostCenterById(Long id) {
        return ddConverter.convert(ddFinder.findCostCenterById(id));

    }

    @Override
    public CostCenterModel findCostCenterByCode(String code) {
        return ddConverter.convert(ddFinder.findCostCenterByCode(code));
    }

    @Override
    public PagingLoadResult<CostCenterModel> findCostCenters(Integer offset, Integer limit) {
        return ddConverter.convertToPagingCostCenters(
                ddFinder.findCostCenters(offset, limit),
                ddFinder.countCostCenter(),
                offset);
    }

    @Override
    public PagingLoadResult<CostCenterModel> findCostCenters(String filter, Integer offset, Integer limit) {
        return ddConverter.convertToPagingCostCenters(
                ddFinder.findCostCenters(filter, offset, limit),
                ddFinder.countCostCenter(filter),
                offset);
    }

    // ==================================================================================================== //
    // CURRENCY
    // ==================================================================================================== //

    @Override
    public BankCodeModel findBankCodeById(Long id) {
        return ddConverter.convert(ddFinder.findBankCodeById(id));
    }

    @Override
    public BankCodeModel findBankCodeByCode(String code) {
        return ddConverter.convert(ddFinder.findBankCodeByCode(code));
    }

    @Override
    public PagingLoadResult<BankCodeModel> findBankCodes(Integer offset, Integer limit) {
        return ddConverter.convertToPagingBankCodes(
                ddFinder.findBankCodes(offset, limit),
                ddFinder.countBankCode(),
                offset);
    }

    @Override
    public PagingLoadResult<BankCodeModel> findBankCodes(String filter, Integer offset, Integer limit) {
        return ddConverter.convertToPagingBankCodes(
                ddFinder.findBankCodes(filter, offset, limit),
                ddFinder.countBankCode(filter),
                offset);
    }

    // ==================================================================================================== //
    // CURRENCY
    // ==================================================================================================== //

    @Override
    public PagingLoadResult<PeriodModel> findPeriods(Integer offset, Integer limit) {
        return ddConverter.convertToPagingPeriods(
                ddFinder.findPeriods(offset, limit),
                ddFinder.countPeriod(),
                offset);
    }

    @Override
    public PagingLoadResult<PeriodModel> findPeriods(String filter, Integer offset, Integer limit) {
        return ddConverter.convertToPagingPeriods(
                ddFinder.findPeriods(filter, offset, limit),
                ddFinder.countPeriod(filter),
                offset);

    }

    @Override
    public PagingLoadResult<PeriodModel> findPeriods(String filter, String funds, Integer offset, Integer limit) {
        return ddConverter.convertToPagingPeriods(
                ddFinder.findPeriods(filter, offset, limit),
                ddFinder.countPeriod(filter),
                offset);
    }

    @Override
    public PagingLoadResult<PeriodModel> findPeriods(CostCenterModel costCenterModel, Integer offset, Integer limit) {
        return ddConverter.convertToPagingPeriods(
                ddFinder.findPeriods(offset, limit),
                ddFinder.countPeriod(),
                offset);
    }

    // ==================================================================================================== //
    // CURRENCY
    // ==================================================================================================== //

    @Override
    public AccountCodeModel findAccountCodeById(Long id) {
        return ddConverter.convert(ddFinder.findAccountCodeById(id));
    }

    @Override
    public AccountCodeModel findAccountCodeByCode(String code) {
        return ddConverter.convert(ddFinder.findAccountCodeByCode(code));
    }

    @Override
    public PagingLoadResult<AccountCodeModel> findAccountCodes(Integer offset, Integer limit) {
        return ddConverter.convertToPagingAccountCodes(
                ddFinder.findAccountCodes(offset, limit),
                ddFinder.countAccountCode(),
                offset);
    }

    @Override
    public PagingLoadResult<AccountCodeModel> findAccountCodes(String filter, Integer offset, Integer limit) {
        return ddConverter.convertToPagingAccountCodes(
                ddFinder.findAccountCodes(filter, offset, limit),
                ddFinder.countAccountCode(filter),
                offset);
    }

    // ==================================================================================================== //
    // SODO
    // ==================================================================================================== //

    @Override
    public SodoCodeModel findSodoCodeById(Long id) {
        return ddConverter.convert(ddFinder.findSodoCodeById(id));
    }

    @Override
    public SodoCodeModel findSodoCodeByCode(String code) {
        return ddConverter.convert(ddFinder.findSodoCodeByCode(code));

    }

    @Override
    public ListLoadResult<SodoCodeModel> findSodoCodeRoots() {
        return ddConverter.convertToListSodoCodes(
                ddFinder.findSodoCodeRoots());
    }

    @Override
    public ListLoadResult<SodoCodeModel> findSodoCodes(SodoCodeModel parent) {
        CfSodoCode parentCode = ddFinder.findSodoCodeById(parent.getId());
        return ddConverter.convertToListSodoCodes(
                ddFinder.findSodoCodes(parentCode));
    }

    @Override
    public PagingLoadResult<SodoCodeModel> findSodoCodes(Integer offset, Integer limit) {
        return ddConverter.convertToPagingSodoCodes(
                ddFinder.findSodoCodes(offset, limit),
                ddFinder.countSodoCode(),
                offset);
    }

    @Override
    public PagingLoadResult<SodoCodeModel> findSodoCodes(SodoCodeModel parent, Integer offset, Integer limit) {
        CfSodoCode parentCode = ddFinder.findSodoCodeById(parent.getId());
        return ddConverter.convertToPagingSodoCodes(
                ddFinder.findSodoCodes(parentCode, offset, limit),
                ddFinder.countSodoCode(parentCode),
                offset);

    }

    @Override
    public PagingLoadResult<SodoCodeModel> findSodoCodes(String filter, Integer offset, Integer limit) {
        return ddConverter.convertToPagingSodoCodes(
                ddFinder.findSodoCodes(filter, offset, limit),
                ddFinder.countSodoCode(filter),
                offset);
    }

    @Override
    public PagingLoadResult<SodoCodeModel> findSodoCodes(Integer level, String filter, Integer offset, Integer limit) {
        return ddConverter.convertToPagingSodoCodes(
                ddFinder.findSodoCodes(level, filter, offset, limit),
                ddFinder.countSodoCode(filter),
                offset);
    }

    // ==================================================================================================== //
    // UNIT
    // ==================================================================================================== //


    @Override
    public PagingLoadResult<UnitCodeModel> findUnitCodes(Integer offset, Integer limit) {
        return ddConverter.convertToPagingUnitCodes(
                ddFinder.findUnitCodes(offset, limit),
                ddFinder.countUnitCode(),
                offset);
    }

    @Override
    public PagingLoadResult<UnitCodeModel> findUnitCodes(String filter, Integer offset, Integer limit) {
        return ddConverter.convertToPagingUnitCodes(
                ddFinder.findUnitCodes(filter, offset, limit),
                ddFinder.countUnitCode(filter),
                offset);
    }

    // ==================================================================================================== //
    // POSITION
    // ==================================================================================================== //

    @Override
    public PagingLoadResult<PositionCodeModel> findPositionCodes(Integer offset, Integer limit) {
        return ddConverter.convertToPagingPositionCodes(
                ddFinder.findPositionCodes(offset, limit),
                ddFinder.countPositionCode(),
                offset);

    }

    @Override
    public PagingLoadResult<PositionCodeModel> findPositionCodes(String filter, Integer offset, Integer limit) {
        return ddConverter.convertToPagingPositionCodes(
                ddFinder.findPositionCodes(filter, offset, limit),
                ddFinder.countPositionCode(filter),
                offset);
    }
}
