package net.canang.cfi.biz.dd.manager;

import net.canang.cfi.core.dd.model.*;
import net.canang.cfi.core.so.model.CfPrincipal;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public interface DdManager {

    // ====================================================================================================
    // COUNTRY
    // ====================================================================================================

    void saveCountryCode(CfCountryCode country);

    void updateCountryCode(CfCountryCode country);

    void deactivateCountryCode(CfCountryCode country);

    // ====================================================================================================
    // STATE
    // ====================================================================================================

    void saveStateCode(CfStateCode state);

    void updateStateCode(CfStateCode state);

    void deactivateStateCode(CfStateCode state);

    // ====================================================================================================
    // CITY
    // ====================================================================================================

    void saveCityCode(CfCityCode city);

    void updateCityCode(CfCityCode city);

    void deactivateCityCode(CfCityCode city);

    // ====================================================================================================
    // FUND
    // ====================================================================================================

    void saveFundCode(CfFundCode fund);

    void updateFundCode(CfFundCode fund);

    void deactivateFundCode(CfFundCode fund);

    // ====================================================================================================
    // campus
    // ====================================================================================================

    void saveCampusCode(CfCampusCode campus);

    void updateCampusCode(CfCampusCode campus);

    void deactivateCampusCode(CfCampusCode campus);


    // ====================================================================================================
    // DEPARTMENT
    // ====================================================================================================

    void saveDepartmentCode(CfDepartmentCode department);

    void updateDepartmentCode(CfDepartmentCode department);

    void deactivateDepartmentCode(CfDepartmentCode department);

    // ====================================================================================================
    // project
    // ====================================================================================================

    void saveProjectCode(CfProjectCode project);

    void updateProjectCode(CfProjectCode project);

    void deactivateProjectCode(CfProjectCode project);


    // ====================================================================================================
    // sub project
    // ====================================================================================================

    void saveSubProjectCode(CfSubProjectCode subProject);

    void updateSubProjectCode(CfSubProjectCode subProject);

    void deactivateSubProjectCode(CfSubProjectCode subProject);


    // ====================================================================================================
    // COST CENTER
    // ====================================================================================================

    void saveCostCenter(CfCostCenter costCenter);

    void updateCostCenter(CfCostCenter costCenter);

    void deactivateCostCenter(CfCostCenter costCenter);

    void addCostCenterMember(CfCostCenter costCenter, CfPrincipal principal) throws Exception;

    void removeCostCenterMember(CfCostCenter costCenter, CfPrincipal principal) throws Exception;

    // ====================================================================================================
    // PERIOD
    // ====================================================================================================

    void savePeriod(CfPeriod budget);

    void updatePeriod(CfPeriod budget);

    void deactivatePeriod(CfPeriod period);

    // ====================================================================================================
    // BANK
    // ====================================================================================================

    void saveBankCode(CfBankCode bank);

    void updateBankCode(CfBankCode bank);

    void deactivateBankCode(CfBankCode bank);

    // ====================================================================================================
    // CURRENCY
    // ====================================================================================================

    void saveCurrencyCode(CfCurrencyCode currency);

    void updateCurrencyCode(CfCurrencyCode currency);

    void deactivateCurrencyCode(CfCurrencyCode currency);


    // ====================================================================================================
    // account
    // ====================================================================================================

    void saveAccountCode(CfAccountCode account);

    void updateAccountCode(CfAccountCode account);

    void deactivateAccountCode(CfAccountCode account);

    // ====================================================================================================
    // sodo
    // ====================================================================================================

    void saveSodoCode(CfSodoCode sodo);

    void updateSodoCode(CfSodoCode sodo);

    void deactivateSodoCode(CfSodoCode sodo);

}
