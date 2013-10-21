package net.canang.cfi.biz.dd.manager;

import net.canang.cfi.core.dd.dao.*;
import net.canang.cfi.core.dd.model.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/4/13
 */
@Transactional(readOnly = true)
@Service("ddFinder")
public class DdFinderImpl implements DdFinder {

    private static final Logger log = Logger.getLogger(DdFinderImpl.class);

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
    private CfBankCodeDao bankCodeDao;

    @Autowired
    private CfCurrencyCodeDao currencyCodeDao;

    @Autowired
    private CfAccountCodeDao accountCodeDao;

    @Autowired
    private CfSodoCodeDao sodoCodeDao;

    @Autowired
    private CfPositionCodeDao positionCodeDao;

    @Autowired
    private CfUnitCodeDao unitCodeDao;

    @Autowired
    private CfPeriodDao periodDao;

    // =============================================================================
    // COUNTRY METHODS
    // =============================================================================

    @Override
    public CfCountryCode findCountryCodeById(Long id) {
        return countryCodeDao.findById(id);
    }

    @Override
    public CfCountryCode findCountryCodeByCode(String code) {
        return countryCodeDao.findByCode(code);
    }

    @Override
    public List<CfCountryCode> findCountryCodes(Integer offset, Integer limit) {
        return countryCodeDao.find(offset, limit);
    }

    @Override
    public List<CfCountryCode> findCountryCodes(String filter, Integer offset, Integer limit) {
        return countryCodeDao.find(filter, offset, limit);
    }

    @Override
    public Integer countCountryCode() {
        return countryCodeDao.count();
    }

    @Override
    public Integer countCountryCode(String filter) {
        return countryCodeDao.count(filter);
    }

    // =============================================================================
    // STATE METHODS
    // =============================================================================

    @Override
    public CfStateCode findStateCodeById(Long id) {
        return stateCodeDao.findById(id);
    }

    @Override
    public CfStateCode findStateCodeByCode(String code) {
        return stateCodeDao.findByCode(code);
    }

    @Override
    public List<CfStateCode> findStateCodes(Integer offset, Integer limit) {
        return stateCodeDao.find(offset, limit);
    }

    @Override
    public List<CfStateCode> findStateCodes(String filter, Integer offset, Integer limit) {
        return stateCodeDao.find(filter, offset, limit);
    }

    @Override
    public Integer countStateCode() {
        return stateCodeDao.count();
    }

    @Override
    public Integer countStateCode(String filter) {
        return stateCodeDao.count(filter);
    }

    // =============================================================================
    // CITY METHODS
    // =============================================================================


    @Override
    public CfCityCode findCityCodeById(Long id) {
        return cityCodeDao.findById(id);
    }

    @Override
    public CfCityCode findCityCodeByCode(String code) {
        return cityCodeDao.findByCode(code);
    }

    @Override
    public List<CfCityCode> findCityCodes(Integer offset, Integer limit) {
        return cityCodeDao.find(offset, limit);
    }

    @Override
    public List<CfCityCode> findCityCodes(String filter, Integer offset, Integer limit) {
        return cityCodeDao.find(filter, offset, limit);
    }

    @Override
    public List<CfCityCode> findCityCodes(CfStateCode stateCode, String filter, Integer offset, Integer limit) {
        return cityCodeDao.find(stateCode, filter, offset, limit);
    }

    @Override
    public Integer countCityCode() {
        return cityCodeDao.count();
    }

    @Override
    public Integer countCityCode(String filter) {
        return cityCodeDao.count(filter);
    }

    @Override
    public Integer countCityCode(CfStateCode stateCode, String filter) {
        return cityCodeDao.count(stateCode, filter);
    }

    // =============================================================================
    // FUND METHODS
    // =============================================================================


    @Override
    public CfFundCode findFundCodeById(Long id) {
        return fundCodeDao.findById(id);
    }

    @Override
    public CfFundCode findFundCodeByCode(String code) {
        return fundCodeDao.findByCode(code);
    }

    @Override
    public List<CfFundCode> findFundCodes(Integer offset, Integer limit) {
        return fundCodeDao.find(offset, limit);
    }

    @Override
    public List<CfFundCode> findFundCodes(String filter, String[] funds, Integer offset, Integer limit) {
        return fundCodeDao.find(filter, funds, offset, limit);
    }

    @Override
    public Integer countFundCode() {
        return fundCodeDao.count();
    }

    @Override
    public Integer countFundCode(String filter) {
        return fundCodeDao.count(filter);
    }


    // =============================================================================
    // CAMPUS METHODS
    // =============================================================================

    @Override
    public CfCampusCode findCampusCodeById(Long id) {
        return campusCodeDao.findById(id);
    }

    @Override
    public CfCampusCode findCampusCodeByCode(String code) {
        return campusCodeDao.findByCode(code);
    }

    @Override
    public List<CfCampusCode> findCampusCodes(Integer offset, Integer limit) {
        return campusCodeDao.find(offset, limit);
    }

    @Override
    public List<CfCampusCode> findCampusCodes(String filter, Integer offset, Integer limit) {
        return campusCodeDao.find(filter, offset, limit);
    }

    @Override
    public Integer countCampusCode() {
        return campusCodeDao.count();
    }

    @Override
    public Integer countCampusCode(String filter) {
        return campusCodeDao.count(filter);
    }


    // =============================================================================
    // RESPONSIBILITY METHODS
    // =============================================================================
    @Override
    public CfDepartmentCode findDepartmentCodeById(Long id) {
        return departmentCodeDao.findById(id);
    }

    @Override
    public CfDepartmentCode findDepartmentCodeByCode(String code) {
        return departmentCodeDao.findByCode(code);
    }

    @Override
    public List<CfDepartmentCode> findDepartmentCodes(Integer offset, Integer limit) {
        return departmentCodeDao.find(offset, limit);
    }

    @Override
    public List<CfDepartmentCode> findDepartmentCodes(String filter, Integer offset, Integer limit) {
        return departmentCodeDao.find(filter, offset, limit);
    }

    @Override
    public Integer countDepartmentCode() {
        return departmentCodeDao.count();
    }

    @Override
    public Integer countDepartmentCode(String filter) {
        return departmentCodeDao.count(filter);
    }

    // =============================================================================
    // COST CENTER METHODS
    // =============================================================================

    @Override
    public CfCostCenter findCostCenterById(Long id) {
        return costCenterDao.findById(id);
    }

    @Override
    public CfCostCenter findCostCenterByCode(String code) {
        return costCenterDao.findByCode(code);
    }

    @Override
    public List<CfCostCenter> findCostCenters() {
        return costCenterDao.find();
    }

    @Override
    public List<CfCostCenter> findCostCenters(Integer offset, Integer limit) {
        return costCenterDao.find(offset, limit);
    }

    @Override
    public List<CfCostCenter> findCostCenters(String filter, Integer offset, Integer limit) {
        return costCenterDao.find(filter, offset, limit);
    }

    @Override
    public List<CfCostCenter> findCostCenters(CfFundCode fund, Integer offset, Integer limit) {
        return costCenterDao.find(fund, offset, limit);
    }

    @Override
    public List<CfCostCenter> findCostCenters(String filter, CfFundCode fund, Integer offset, Integer limit) {
        return costCenterDao.find(filter, fund, offset, limit);
    }

    @Override
    public List<CfCostCenter> findCostCenters(CfDepartmentCode departmentCode, Integer offset, Integer limit) {
        return costCenterDao.find(departmentCode, offset, limit);
    }

    @Override
    public List<CfCostCenter> findCostCenters(CfProjectCode project, Integer offset, Integer limit) {
        return costCenterDao.find(project, offset, limit);
    }

    @Override
    public List<CfCostCenter> findCostCenters(CfFundCode fund, CfDepartmentCode departmentCode, Integer offset, Integer limit) {
        return costCenterDao.find(fund, departmentCode, offset, limit);
    }

    @Override
    public List<CfCostCenter> findCostCenters(CfFundCode fund, CfDepartmentCode departmentCode, CfProjectCode project, Integer offset, Integer limit) {
        return costCenterDao.find(fund, departmentCode, project, offset, limit);
    }

    @Override
    public Integer countCostCenter() {
        return costCenterDao.count();
    }

    @Override
    public Integer countCostCenter(String filter) {
        return costCenterDao.count(filter);
    }

    @Override
    public Integer countCostCenter(String filter, CfFundCode fund) {
        return costCenterDao.count(filter, fund);
    }


    @Override
    public CfCostCenterMember findCostCenterMemberById(Long id) {
        return costCenterDao.findMemberById(id);
    }

    @Override
    public List<CfCostCenterMember> findCostCenterMembers(CfCostCenter costCenter) {
        return costCenterDao.findMembers(costCenter);
    }

    @Override
    public List<CfCostCenterMember> findCostCenterMembers(CfCostCenter costCenter, Integer offset, Integer limit) {
        return costCenterDao.findMembers(costCenter, offset, limit);
    }

    @Override
    public Integer countCostCenterMember(CfCostCenter costCenter) {
        return costCenterDao.countMember(costCenter);
    }

    // =============================================================================
    // PROJECT METHODS
    // =============================================================================

    @Override
    public CfProjectCode findProjectCodeById(Long id) {
        return projectCodeDao.findById(id);
    }

    @Override
    public CfProjectCode findProjectCodeByCode(String code) {
        return projectCodeDao.findByCode(code);
    }

    @Override
    public List<CfProjectCode> findProjectCodes() {
        return projectCodeDao.find();
    }

    @Override
    public List<CfProjectCode> findProjectCodes(Integer offset, Integer limit) {
        return projectCodeDao.find(offset, limit);
    }

    @Override
    public List<CfProjectCode> findProjectCodes(String filter, Integer offset, Integer limit) {
        return projectCodeDao.find(filter, offset, limit);
    }

    @Override
    public Integer countProjectCode() {
        return projectCodeDao.count();
    }

    @Override
    public Integer countProjectCode(String filter) {
        return projectCodeDao.count(filter);
    }


    @Override
    public CfSubProjectCode findSubProjectCodeById(Long id) {
        return subProjectCodeDao.findById(id);
    }

    @Override
    public CfSubProjectCode findSubProjectCodeByCode(String code) {
        return subProjectCodeDao.findByCode(code);
    }

    @Override
    public List<CfSubProjectCode> findSubProjectCodes() {
        return subProjectCodeDao.find();
    }

    @Override
    public List<CfSubProjectCode> findSubProjectCodes(Integer offset, Integer limit) {
        return subProjectCodeDao.find(offset, limit);
    }

    @Override
    public List<CfSubProjectCode> findSubProjectCodes(String filter, Integer offset, Integer limit) {
        return subProjectCodeDao.find(filter, offset, limit);
    }

    @Override
    public Integer countSubProjectCode() {
        return subProjectCodeDao.count();
    }

    @Override
    public Integer countSubProjectCode(String filter) {
        return subProjectCodeDao.count(filter);
    }


    @Override
    public CfBankCode findBankCodeById(Long id) {
        return bankCodeDao.findById(id);
    }

    @Override
    public CfBankCode findBankCodeByCode(String code) {
        return bankCodeDao.findByCode(code);
    }

    @Override
    public List<CfBankCode> findBankCodes() {
        return bankCodeDao.find();
    }

    @Override
    public List<CfBankCode> findBankCodes(Integer offset, Integer limit) {
        return bankCodeDao.find(offset, limit);
    }

    @Override
    public List<CfBankCode> findBankCodes(String filter, Integer offset, Integer limit) {
        return bankCodeDao.find(filter, offset, limit);
    }

    @Override
    public Integer countBankCode() {
        return bankCodeDao.count();
    }

    @Override
    public Integer countBankCode(String filter) {
        return bankCodeDao.count(filter);
    }

    //====================================================================================================
    // CURRENCY
    //====================================================================================================


    @Override
    public CfCurrencyCode findCurrencyCodeById(Long id) {
        return currencyCodeDao.findById(id);
    }

    @Override
    public CfCurrencyCode findCurrencyCodeByCode(String code) {
        return currencyCodeDao.findByCode(code);
    }

    @Override
    public List<CfCurrencyCode> findCurrencyCodes(Integer offset, Integer limit) {
        return currencyCodeDao.find(offset, limit);
    }

    @Override
    public List<CfCurrencyCode> findCurrencyCodes(String filter, Integer offset, Integer limit) {
        return currencyCodeDao.find(filter, offset, limit);
    }

    @Override
    public Integer countCurrencyCode() {
        return currencyCodeDao.count();
    }

    @Override
    public Integer countCurrencyCode(String filter) {
        return currencyCodeDao.count(filter);
    }
    //====================================================================================================
    // PERIOD
    //====================================================================================================


    @Override
    public CfPeriod findPeriodById(Long id) {
        return periodDao.findById(id);
    }

    @Override
    public List<CfPeriod> findPeriods(Integer offset, Integer limit) {
        return periodDao.find(offset, limit);
    }

    @Override
    public List<CfPeriod> findPeriods(String filter, Integer offset, Integer limit) {
        return periodDao.find(filter, offset, limit);
    }

    @Override
    public List<CfPeriod> findPeriods(String filter, String[] funds, Integer offset, Integer limit) {
        return periodDao.find(filter, funds, offset, limit);
    }

    @Override
    public List<CfPeriod> findPeriods(CfCostCenter costCenter, Integer offset, Integer limit) {
        return periodDao.find(costCenter, offset, limit);
    }

    @Override
    public CfPeriod findCurrentPeriod(CfCostCenter costCenter) {
        return periodDao.findCurrent(costCenter);
    }

    @Override
    public Integer countPeriod() {
        return periodDao.count();
    }

    @Override
    public Integer countPeriod(String filter) {
        return periodDao.count(filter);
    }

    @Override
    public Integer countPeriod(String filter, String[] funds) {
        return periodDao.count(filter, funds);
    }

    @Override
    public Integer countPeriod(CfCostCenter costCenter) {
        return periodDao.count(costCenter);
    }


    //====================================================================================================
    // ACCOUNT
    //====================================================================================================

    @Override
    public CfAccountCode findAccountCodeById(Long id) {
        return accountCodeDao.findById(id);
    }

    @Override
    public CfAccountCode findAccountCodeByCode(String code) {
        return accountCodeDao.findByCode(code);
    }

    @Override
    public List<CfAccountCode> findAccountCodes(Integer offset, Integer limit) {
        return accountCodeDao.find(offset, limit);
    }

    @Override
    public List<CfAccountCode> findAccountCodes(String filter, Integer offset, Integer limit) {
        return accountCodeDao.find(filter, offset, limit);
    }

    @Override
    public Integer countAccountCode() {
        return accountCodeDao.count();
    }

    @Override
    public Integer countAccountCode(String filter) {
        return accountCodeDao.count(filter);
    }

    //====================================================================================================
    // SODO
    //====================================================================================================


    @Override
    public CfSodoCode findSodoCodeById(Long id) {
        return sodoCodeDao.findById(id);
    }

    @Override
    public CfSodoCode findSodoCodeByCode(String code) {
        return sodoCodeDao.findByCode(code);
    }

    @Override
    public List<CfSodoCode> findSodoCodeRoots() {
        return sodoCodeDao.findRoots();
    }

    @Override
    public List<CfSodoCode> findSodoCodes(CfSodoCode parent) {
        return sodoCodeDao.findByParent(parent);
    }

    @Override
    public List<CfSodoCode> findSodoCodes(Integer offset, Integer limit) {
        return sodoCodeDao.find(offset, limit);
    }

    @Override
    public List<CfSodoCode> findSodoCodes(String filter, Integer offset, Integer limit) {
        return sodoCodeDao.find(filter, offset, limit);
    }

    @Override
    public List<CfSodoCode> findSodoCodes(Integer level, String filter, Integer offset, Integer limit) {
        return sodoCodeDao.find(level, filter, offset, limit);
    }

    @Override
    public List<CfSodoCode> findSodoCodes(CfSodoCode parent, Integer offset, Integer limit) {
        return sodoCodeDao.find(parent, offset, limit);
    }

    @Override
    public Integer countSodoCode() {
        return sodoCodeDao.count();
    }

    @Override
    public Integer countSodoCode(CfSodoCode parent) {
        return sodoCodeDao.count(parent);
    }

    @Override
    public Integer countSodoCode(String filter) {
        return sodoCodeDao.count(filter);
    }

    @Override
    public boolean hasSodoCodeChildren(CfSodoCode parent) {
        return sodoCodeDao.hasChildren(parent);
    }

    // ====================================================================================================
    // POSITION METHODS
    // ====================================================================================================

    @Override
    public CfPositionCode findPositionCodeById(Long id) {
        return positionCodeDao.findById(id);
    }

    @Override
    public CfPositionCode findPositionCodeByCode(String code) {
        return positionCodeDao.findByCode(code);
    }

    @Override
    public List<CfPositionCode> findPositionCodes(Integer offset, Integer limit) {
        return positionCodeDao.find(offset, limit);
    }

    @Override
    public List<CfPositionCode> findPositionCodes(String filter, Integer offset, Integer limit) {
        return positionCodeDao.find(filter, offset, limit);
    }

    @Override
    public Integer countPositionCode(String filter) {
        return positionCodeDao.count(filter);
    }

    // ====================================================================================================
    // UNIT METHODS
    // ====================================================================================================

    @Override
    public CfUnitCode findUnitCodeById(Long id) {
        return unitCodeDao.findById(id);
    }

    @Override
    public CfUnitCode findUnitCodeByCode(String code) {
        return unitCodeDao.findByCode(code);
    }

    @Override
    public List<CfUnitCode> findUnitCodes(Integer offset, Integer limit) {
        return unitCodeDao.find(offset, limit);
    }

    @Override
    public List<CfUnitCode> findUnitCodes(String filter, Integer offset, Integer limit) {
        return unitCodeDao.find(filter, offset, limit);
    }

    @Override
    public Integer countUnitCode() {
        return unitCodeDao.count();
    }

    @Override
    public Integer countUnitCode(String filter) {
        return unitCodeDao.count(filter);
    }
}

