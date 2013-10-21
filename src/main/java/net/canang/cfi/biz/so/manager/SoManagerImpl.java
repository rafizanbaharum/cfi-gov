package net.canang.cfi.biz.so.manager;

import net.canang.cfi.biz.Util;
import net.canang.cfi.core.dd.dao.CfReferenceNoDao;
import net.canang.cfi.core.dd.model.CfCostCenter;
import net.canang.cfi.core.dd.model.CfDepartmentCode;
import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.dd.model.CfReferenceNo;
import net.canang.cfi.core.so.dao.CfActorDao;
import net.canang.cfi.core.so.dao.CfConfigurationDao;
import net.canang.cfi.core.so.model.CfActor;
import net.canang.cfi.core.so.model.CfConfiguration;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.*;
import java.util.Date;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
@Component("soManager")
public class SoManagerImpl implements SoManager {

    @Autowired
    private CfActorDao actorDao;

    @Autowired
    private CfReferenceNoDao referenceNoDao;

    @Autowired
    private CfConfigurationDao configurationDao;

    @Autowired
    private SessionFactory sessionFactory;


    // =============================================================================
    // CONSUMER METHODS
    // =============================================================================

    @Override
    public void saveActor(CfActor actor) {
        actorDao.save(actor, Util.getCurrentUser());
    }

    @Override
    public void updateActor(CfActor actor) {
        actorDao.update(actor, Util.getCurrentUser());
    }

    @Override
    public void deactivateActor(CfActor actor) {
        actorDao.save(actor, Util.getCurrentUser());
    }

    // =============================================================================
    // REFERENCE NO METHODS
    // =============================================================================

    @Override
    public void saveReferenceNo(CfReferenceNo referenceNo) {
        referenceNoDao.save(referenceNo, Util.getCurrentUser());
    }

    @Override
    public void updateReferenceNo(CfReferenceNo referenceNo) {
        referenceNoDao.update(referenceNo, Util.getCurrentUser());
    }

    @Override
    public void deactivateReferenceNo(CfReferenceNo referenceNo) {
        referenceNoDao.update(referenceNo, Util.getCurrentUser());
    }


    @Override
    public String generatePlainReferenceNo(String code) {
        String generatedRefNo = null;
        synchronized (this) {
            CfReferenceNo referenceNo = referenceNoDao.findByCode(code);
            Integer oldValue = referenceNo.getCurrentValue();
            Integer newValue = referenceNo.getCurrentValue() + referenceNo.getIncrementValue();

            // update
            referenceNo.setCurrentValue(newValue);
            referenceNoDao.save(referenceNo, Util.getCurrentUser());
            sessionFactory.getCurrentSession().flush();

            NumberFormat numberFormat = new DecimalFormat(referenceNo.getSequenceFormat());

            // format
            generatedRefNo = referenceNo.getPrefix() + numberFormat.format(oldValue);
        }

        return generatedRefNo;
    }

    @Override
    public String generateReferenceNo(String code) {
        String generatedRefNo = null;
        synchronized (this) {
            CfReferenceNo referenceNo = referenceNoDao.findByCode(code);
            Integer oldValue = referenceNo.getCurrentValue();
            Integer newValue = referenceNo.getCurrentValue() + referenceNo.getIncrementValue();

            // update
            referenceNo.setCurrentValue(newValue);
            referenceNoDao.save(referenceNo, Util.getCurrentUser());
            sessionFactory.getCurrentSession().flush();

            NumberFormat numberFormat = new DecimalFormat(referenceNo.getSequenceFormat());
            DateFormat yearFormat = new SimpleDateFormat("yyyy");
            DateFormat monthFormat = new SimpleDateFormat("MM");

            // format
            generatedRefNo = referenceNo.getPrefix()
                    + yearFormat.format(new Date())
                    + monthFormat.format(new Date())
                    + numberFormat.format(oldValue);
        }

        return generatedRefNo;
    }


    public String generateFormattedReferenceNo(String code,
                                               CfCostCenter costCenter,
                                               CfCostCenter requester,
                                               CfPeriod period,
                                               CfDepartmentCode department) {
        String generatedRefNo = null;
        Object[] arguments = new Object[17];
        synchronized (this) {

            CfReferenceNo referenceNo = referenceNoDao.findByCode(code);

            // get old and new value
            Integer oldValue = referenceNo.getCurrentValue();
            Integer newValue = referenceNo.getCurrentValue() + referenceNo.getIncrementValue();

            // update
            referenceNo.setCurrentValue(newValue);
            referenceNoDao.save(referenceNo, Util.getCurrentUser());
            sessionFactory.getCurrentSession().flush();

            Date now = new Date();
            NumberFormat numberFormat = new DecimalFormat(referenceNo.getSequenceFormat());
            DateFormat longYearFormat = new SimpleDateFormat("yyyy");
            DateFormat longMonthFormat = new SimpleDateFormat("MMM");
            DateFormat longDayFormat = new SimpleDateFormat("dd");
            DateFormat longHourFormat = new SimpleDateFormat("HH");
            DateFormat shortYearFormat = new SimpleDateFormat("yy");
            DateFormat shortMonthFormat = new SimpleDateFormat("MM");
            DateFormat shortDayFormat = new SimpleDateFormat("dd");
            DateFormat shortHourFormat = new SimpleDateFormat("hh");

            if (null != referenceNo.getPrefix()) arguments[0] = referenceNo.getPrefix();
            arguments[1] = null;
            if (null != costCenter) arguments[2] = costCenter.getCode();
            if (null != requester) arguments[3] = requester.getCode();
            if (null != period) arguments[4] = period.getCostCenter().getCode();
            if (null != department) arguments[5] = department.getCode();
            if (null != department) arguments[6] = department.getCode().substring(0, 3);
            if (null != department) arguments[7] = department.getCode().substring(1, 3);
            if (null != now) arguments[8] = longYearFormat.format(now);
            if (null != now) arguments[9] = shortYearFormat.format(now);
            if (null != now) arguments[10] = longMonthFormat.format(now);
            if (null != now) arguments[11] = shortMonthFormat.format(now);
            if (null != now) arguments[12] = numberFormat.format(oldValue);
            if (null != now) arguments[13] = longDayFormat.format(now);
            if (null != now) arguments[14] = shortDayFormat.format(now);
            if (null != now) arguments[15] = longHourFormat.format(now);
            if (null != now) arguments[16] = shortHourFormat.format(now);

            generatedRefNo = MessageFormat.format(referenceNo.getReferenceFormat(), arguments);
            return generatedRefNo;
        }
    }

    // ====================================================================================================
    // CONFIGURATION
    // ====================================================================================================

    @Override
    public void saveConfiguration(CfConfiguration configuration) {
        configurationDao.save(configuration, Util.getCurrentUser());
    }

    @Override
    public void updateConfiguration(CfConfiguration configuration) {
        configurationDao.update(configuration, Util.getCurrentUser());
    }

    @Override
    public void deactivateConfiguration(CfConfiguration configuration) {
        configurationDao.deactivate(configuration, Util.getCurrentUser());
    }
}
