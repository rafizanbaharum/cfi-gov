package net.canang.cfi.biz.dd.manager;

import net.canang.cfi.biz.Util;
import net.canang.cfi.core.dd.dao.*;
import net.canang.cfi.core.dd.model.*;
import net.canang.cfi.core.so.model.CfPrincipal;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rafizan.baharum
 * @since 10/4/13
 */
@Service("ddManager")
public class DdManagerImpl implements DdManager {

    private static final Logger log = Logger.getLogger(DdManagerImpl.class);

    @Autowired
    private CfCountryCodeDao countryCodeDao;

    @Autowired
    private CfStateCodeDao stateCodeDao;

    @Autowired
    private CfCityCodeDao cityCodeDao;

    @Autowired
    private CfFundCodeDao fundCodeDao;

    @Autowired
    private CfCampusCodeDao campusCodeDao;

    @Autowired
    private CfDepartmentCodeDao departmentCodeDao;

    @Autowired
    private CfCostCenterDao costCenterDao;

    @Autowired
    private CfProjectCodeDao projectCodeDao;

    @Autowired
    private CfSubProjectCodeDao subProjectCodeDao;

    @Autowired
    private CfPeriodDao periodDao;

    @Autowired
    private CfBankCodeDao bankCodeDao;

    @Autowired
    private CfCurrencyCodeDao currencyCodeDao;

    @Autowired
    private CfAccountCodeDao accountCodeDao;

    @Autowired
    private CfSodoCodeDao sodoCodeDao;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveCountryCode(CfCountryCode country) {
        countryCodeDao.save(country, Util.getCurrentUser());
    }

    @Override
    public void updateCountryCode(CfCountryCode country) {
        countryCodeDao.update(country, Util.getCurrentUser());
    }

    @Override
    public void deactivateCountryCode(CfCountryCode country) {
        countryCodeDao.deactivate(country, Util.getCurrentUser());
    }


    // =============================================================================
    // STATE METHODS
    // =============================================================================


    @Override
    public void saveStateCode(CfStateCode state) {
        stateCodeDao.save(state, Util.getCurrentUser());
    }

    @Override
    public void updateStateCode(CfStateCode state) {
        stateCodeDao.update(state, Util.getCurrentUser());
    }

    @Override
    public void deactivateStateCode(CfStateCode state) {
        stateCodeDao.deactivate(state, Util.getCurrentUser());
    }
// =============================================================================
    // CITY METHODS
    // =============================================================================


    @Override
    public void saveCityCode(CfCityCode city) {
        cityCodeDao.save(city, Util.getCurrentUser());
    }

    @Override
    public void updateCityCode(CfCityCode city) {
        cityCodeDao.update(city, Util.getCurrentUser());
    }

    @Override
    public void deactivateCityCode(CfCityCode city) {
        cityCodeDao.deactivate(city, Util.getCurrentUser());
    }

    // =============================================================================
    // FUND METHODS
    // =============================================================================


    @Override
    public void saveFundCode(CfFundCode fund) {
        fundCodeDao.save(fund, Util.getCurrentUser());
    }

    @Override
    public void updateFundCode(CfFundCode fund) {
        fundCodeDao.update(fund, Util.getCurrentUser());
    }

    @Override
    public void deactivateFundCode(CfFundCode fund) {
        fundCodeDao.deactivate(fund, Util.getCurrentUser());
    }

    // =============================================================================
    // CAMPUS METHODS
    // =============================================================================

    @Override
    public void saveCampusCode(CfCampusCode campus) {
        campusCodeDao.save(campus, Util.getCurrentUser());
    }

    @Override
    public void updateCampusCode(CfCampusCode campus) {
        campusCodeDao.update(campus, Util.getCurrentUser());
    }

    @Override
    public void deactivateCampusCode(CfCampusCode campus) {
        campusCodeDao.deactivate(campus, Util.getCurrentUser());
    }

    // =============================================================================
    // RESPONSIBILITY CENTER METHODS
    // =============================================================================

    @Override
    public void saveDepartmentCode(CfDepartmentCode department) {
        departmentCodeDao.save(department, Util.getCurrentUser());
    }

    @Override
    public void updateDepartmentCode(CfDepartmentCode department) {
        departmentCodeDao.update(department, Util.getCurrentUser());
    }

    @Override
    public void deactivateDepartmentCode(CfDepartmentCode department) {
        departmentCodeDao.deactivate(department, Util.getCurrentUser());
    }

    // =============================================================================
    // COST CENTER METHODS
    // =============================================================================

    @Override
    public void saveCostCenter(CfCostCenter costCenter) {
        costCenterDao.save(costCenter, Util.getCurrentUser());
    }

    @Override
    public void updateCostCenter(CfCostCenter costCenter) {
        costCenterDao.update(costCenter, Util.getCurrentUser());
    }

    @Override
    public void deactivateCostCenter(CfCostCenter costCenter) {
        costCenterDao.deactivate(costCenter, Util.getCurrentUser());
    }

    @Override
    public void addCostCenterMember(CfCostCenter costCenter, CfPrincipal principal) throws Exception {
        costCenterDao.addMember(costCenter, principal, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void removeCostCenterMember(CfCostCenter costCenter, CfPrincipal principal) throws Exception {
        costCenterDao.removeMember(costCenter, principal, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }


    // =============================================================================
    // PROJECT METHODS
    // =============================================================================

    @Override
    public void saveProjectCode(CfProjectCode project) {
        projectCodeDao.save(project, Util.getCurrentUser());
    }

    @Override
    public void updateProjectCode(CfProjectCode project) {
        projectCodeDao.update(project, Util.getCurrentUser());
    }

    @Override
    public void deactivateProjectCode(CfProjectCode project) {
        projectCodeDao.deactivate(project, Util.getCurrentUser());
    }

    // =============================================================================
    // SUBPROJECT METHODS
    // =============================================================================

    @Override
    public void saveSubProjectCode(CfSubProjectCode subProjectCode) {
        subProjectCodeDao.save(subProjectCode, Util.getCurrentUser());
    }

    @Override
    public void updateSubProjectCode(CfSubProjectCode subProjectCode) {
        subProjectCodeDao.update(subProjectCode, Util.getCurrentUser());
    }

    @Override
    public void deactivateSubProjectCode(CfSubProjectCode subProjectCode) {
        subProjectCodeDao.deactivate(subProjectCode, Util.getCurrentUser());
    }

    // =============================================================================
    // BANK METHODS
    // =============================================================================

    @Override
    public void saveBankCode(CfBankCode bank) {
        bankCodeDao.save(bank, Util.getCurrentUser());
    }

    @Override
    public void updateBankCode(CfBankCode bank) {
        bankCodeDao.update(bank, Util.getCurrentUser());
    }

    @Override
    public void deactivateBankCode(CfBankCode bank) {
        bankCodeDao.deactivate(bank, Util.getCurrentUser());
    }

    // =============================================================================
    // CURRENCY METHODS
    // =============================================================================

    @Override
    public void saveCurrencyCode(CfCurrencyCode currency) {
        currencyCodeDao.save(currency, Util.getCurrentUser());
    }

    @Override
    public void updateCurrencyCode(CfCurrencyCode currency) {
        currencyCodeDao.update(currency, Util.getCurrentUser());
    }

    @Override
    public void deactivateCurrencyCode(CfCurrencyCode currency) {
        currencyCodeDao.deactivate(currency, Util.getCurrentUser());
    }

    // =============================================================================
    // BUDGET METHODS
    // =============================================================================

    @Override
    @Transactional
    public void savePeriod(CfPeriod period) {
        periodDao.save(period, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updatePeriod(CfPeriod period) {
        periodDao.update(period, Util.getCurrentUser());
    }


    @Override
    public void deactivatePeriod(CfPeriod period) {
        periodDao.deactivate(period, Util.getCurrentUser());
    }

    // =============================================================================
    // BUDGET METHODS
    // =============================================================================

    @Override
    public void saveAccountCode(CfAccountCode account) {
        accountCodeDao.save(account, Util.getCurrentUser());
    }

    @Override
    public void updateAccountCode(CfAccountCode account) {
        accountCodeDao.update(account, Util.getCurrentUser());
    }

    @Override
    public void deactivateAccountCode(CfAccountCode account) {
        accountCodeDao.deactivate(account, Util.getCurrentUser());
    }

    // =============================================================================
    // SODO METHODS
    // =============================================================================
    @Override
    public void saveSodoCode(CfSodoCode sodo) {
        sodoCodeDao.save(sodo, Util.getCurrentUser());
    }

    @Override
    public void updateSodoCode(CfSodoCode sodo) {
        sodoCodeDao.update(sodo, Util.getCurrentUser());
    }

    @Override
    public void deactivateSodoCode(CfSodoCode sodo) {
        sodoCodeDao.deactivate(sodo, Util.getCurrentUser());
    }

}
