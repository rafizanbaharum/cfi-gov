package net.canang.cfi.biz.dd.manager;

import net.canang.cfi.core.dd.model.*;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public interface DdFinder {

    //====================================================================================================
    // COUNTRY
    //====================================================================================================

    CfCountryCode findCountryCodeById(Long id);

    CfCountryCode findCountryCodeByCode(String code);

    List<CfCountryCode> findCountryCodes(Integer offset, Integer limit);

    List<CfCountryCode> findCountryCodes(String filter, Integer offset, Integer limit);

    Integer countCountryCode();

    Integer countCountryCode(String filter);


    //====================================================================================================
    // STATE
    //====================================================================================================

    CfStateCode findStateCodeById(Long id);

    CfStateCode findStateCodeByCode(String code);

    List<CfStateCode> findStateCodes(Integer offset, Integer limit);

    List<CfStateCode> findStateCodes(String filter, Integer offset, Integer limit);

    Integer countStateCode();

    Integer countStateCode(String filter);

    //====================================================================================================
    // CITY
    //====================================================================================================

    CfCityCode findCityCodeById(Long id);

    CfCityCode findCityCodeByCode(String code);

    List<CfCityCode> findCityCodes(Integer offset, Integer limit);

    List<CfCityCode> findCityCodes(String filter, Integer offset, Integer limit);

    List<CfCityCode> findCityCodes(CfStateCode stateCode, String filter, Integer offset, Integer limit);

    Integer countCityCode();

    Integer countCityCode(String filter);

    Integer countCityCode(CfStateCode stateCode, String filter);

    //====================================================================================================
    // FUND
    //====================================================================================================

    CfFundCode findFundCodeById(Long id);

    CfFundCode findFundCodeByCode(String code);

    List<CfFundCode> findFundCodes(Integer offset, Integer limit);

    List<CfFundCode> findFundCodes(String filter, String[] funds, Integer offset, Integer limit);

    Integer countFundCode();

    Integer countFundCode(String filter);

    //====================================================================================================
    // CAMPUS
    //====================================================================================================

    CfCampusCode findCampusCodeById(Long id);

    CfCampusCode findCampusCodeByCode(String code);

    List<CfCampusCode> findCampusCodes(Integer offset, Integer limit);

    List<CfCampusCode> findCampusCodes(String filter, Integer offset, Integer limit);

    Integer countCampusCode();

    Integer countCampusCode(String filter);


    //====================================================================================================
    // DEPARTMENT
    //====================================================================================================

    CfDepartmentCode findDepartmentCodeById(Long id);

    CfDepartmentCode findDepartmentCodeByCode(String code);

    List<CfDepartmentCode> findDepartmentCodes(Integer offset, Integer limit);

    List<CfDepartmentCode> findDepartmentCodes(String filter, Integer offset, Integer limit);

    Integer countDepartmentCode();

    Integer countDepartmentCode(String filter);

    //====================================================================================================
    // PROJECT
    //====================================================================================================

    CfProjectCode findProjectCodeById(Long id);

    CfProjectCode findProjectCodeByCode(String code);

    List<CfProjectCode> findProjectCodes();

    List<CfProjectCode> findProjectCodes(Integer offset, Integer limit);

    List<CfProjectCode> findProjectCodes(String filter, Integer offset, Integer limit);

    Integer countProjectCode();

    Integer countProjectCode(String filter);

    //====================================================================================================
    // SUB PROJECT
    //====================================================================================================

    CfSubProjectCode findSubProjectCodeById(Long id);

    CfSubProjectCode findSubProjectCodeByCode(String code);

    List<CfSubProjectCode> findSubProjectCodes();

    List<CfSubProjectCode> findSubProjectCodes(Integer offset, Integer limit);

    List<CfSubProjectCode> findSubProjectCodes(String filter, Integer offset, Integer limit);

    Integer countSubProjectCode();

    Integer countSubProjectCode(String filter);


    //====================================================================================================
    // COST CENTER
    //====================================================================================================

    CfCostCenter findCostCenterById(Long id);

    CfCostCenter findCostCenterByCode(String code);

    List<CfCostCenter> findCostCenters();

    List<CfCostCenter> findCostCenters(CfFundCode fund, Integer offset, Integer limit);

    List<CfCostCenter> findCostCenters(String filter, CfFundCode fund, Integer offset, Integer limit);

    List<CfCostCenter> findCostCenters(CfDepartmentCode departmentCode, Integer offset, Integer limit);

    List<CfCostCenter> findCostCenters(CfProjectCode project, Integer offset, Integer limit);

    List<CfCostCenter> findCostCenters(CfFundCode fund, CfDepartmentCode departmentCode, Integer offset, Integer limit);

    List<CfCostCenter> findCostCenters(CfFundCode fund, CfDepartmentCode departmentCode, CfProjectCode project, Integer offset, Integer limit);

    List<CfCostCenter> findCostCenters(Integer offset, Integer limit);

    List<CfCostCenter> findCostCenters(String filter, Integer offset, Integer limit);

    Integer countCostCenter();

    Integer countCostCenter(String filter);

    Integer countCostCenter(String filter, CfFundCode fund);

    CfCostCenterMember findCostCenterMemberById(Long id);

    List<CfCostCenterMember> findCostCenterMembers(CfCostCenter costCenter);

    List<CfCostCenterMember> findCostCenterMembers(CfCostCenter costCenter, Integer offset, Integer limit);

    Integer countCostCenterMember(CfCostCenter costCenter);

    //====================================================================================================
    // BANK
    //====================================================================================================

    CfBankCode findBankCodeById(Long id);

    CfBankCode findBankCodeByCode(String code);

    List<CfBankCode> findBankCodes();

    List<CfBankCode> findBankCodes(Integer offset, Integer limit);

    List<CfBankCode> findBankCodes(String filter, Integer offset, Integer limit);

    Integer countBankCode();

    Integer countBankCode(String filter);

    //====================================================================================================
    // CURRENCY
    //====================================================================================================

    CfCurrencyCode findCurrencyCodeById(Long id);

    CfCurrencyCode findCurrencyCodeByCode(String code);

    List<CfCurrencyCode> findCurrencyCodes(Integer offset, Integer limit);

    List<CfCurrencyCode> findCurrencyCodes(String filter, Integer offset, Integer limit);

    Integer countCurrencyCode();

    Integer countCurrencyCode(String filter);


    //====================================================================================================
    // PERIOD
    //====================================================================================================

    CfPeriod findPeriodById(Long id);

    List<CfPeriod> findPeriods(Integer offset, Integer limit);

    List<CfPeriod> findPeriods(String filter, Integer offset, Integer limit);

    List<CfPeriod> findPeriods(String filter, String[] funds, Integer offset, Integer limit);

    List<CfPeriod> findPeriods(CfCostCenter costCenter, Integer offset, Integer limit);

    CfPeriod findCurrentPeriod(CfCostCenter costCenter);

    Integer countPeriod();

    Integer countPeriod(String filter);

    Integer countPeriod(String filter, String[] funds);

    Integer countPeriod(CfCostCenter costCenter);

    //====================================================================================================
    // ACCOUNT
    //====================================================================================================

    CfAccountCode findAccountCodeById(Long id);

    CfAccountCode findAccountCodeByCode(String code);

    List<CfAccountCode> findAccountCodes(Integer offset, Integer limit);

    List<CfAccountCode> findAccountCodes(String filter, Integer offset, Integer limit);

    Integer countAccountCode();

    Integer countAccountCode(String filter);

    //====================================================================================================
    // SODO
    //====================================================================================================

    CfSodoCode findSodoCodeById(Long id);

    CfSodoCode findSodoCodeByCode(String code);

    List<CfSodoCode> findSodoCodeRoots();

    List<CfSodoCode> findSodoCodes(CfSodoCode parent);

    List<CfSodoCode> findSodoCodes(Integer offset, Integer limit);

    List<CfSodoCode> findSodoCodes(CfSodoCode parent, Integer offset, Integer limit);

    List<CfSodoCode> findSodoCodes(String filter, Integer offset, Integer limit);

    List<CfSodoCode> findSodoCodes(Integer level, String filter, Integer offset, Integer limit);

    Integer countSodoCode();

    Integer countSodoCode(CfSodoCode parent);

    Integer countSodoCode(String filter);

    boolean hasSodoCodeChildren(CfSodoCode parent);

    // ====================================================================================================
    // POSITION CODE
    // ====================================================================================================

    CfPositionCode findPositionCodeById(Long id);

    CfPositionCode findPositionCodeByCode(String code);

    List<CfPositionCode> findPositionCodes(Integer offset, Integer limit);

    List<CfPositionCode> findPositionCodes(String filter, Integer offset, Integer limit);

    Integer countPositionCode(String filter);

    //====================================================================================================
    // UNIT
    //====================================================================================================

    CfUnitCode findUnitCodeById(Long id);

    CfUnitCode findUnitCodeByCode(String code);

    List<CfUnitCode> findUnitCodes(Integer offset, Integer limit);

    List<CfUnitCode> findUnitCodes(String filter, Integer offset, Integer limit);

    Integer countUnitCode();

    Integer countUnitCode(String filter);
}
