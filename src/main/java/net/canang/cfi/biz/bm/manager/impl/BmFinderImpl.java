package net.canang.cfi.biz.bm.manager.impl;

import net.canang.cfi.biz.bm.manager.BmFinder;
import net.canang.cfi.core.bm.dao.CfControlDao;
import net.canang.cfi.core.bm.model.CfControl;
import net.canang.cfi.core.bm.model.CfControlItem;
import net.canang.cfi.core.bm.model.CfControlType;
import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.so.model.CfMetaState;
import net.canang.cfi.core.vm.dao.CfVoteDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
@Service("bmFinder")
public class BmFinderImpl implements BmFinder {

    private static final Logger log = Logger.getLogger(BmFinderImpl.class);

    @Autowired
    private CfControlDao controlDao;

    @Autowired
    private CfVoteDao voteDao;

    // ==================================================================================================== //
    // NON-WORKFLOW
    // ==================================================================================================== //

    @Override
    public CfControl findControlById(Long id) {
        return controlDao.findById(id);
    }

    @Override
    public CfControlItem findControlItemById(Long id) {
        return controlDao.findItemById(id);
    }


    @Override
    public CfControl findControlByReferenceNo(String referenceNo) {
        return controlDao.findByReferenceNo(referenceNo);
    }

    @Override
    public CfControl findControlBySourceNo(String sourceNo) {
        return controlDao.findBySourceNo(sourceNo);
    }

    @Override
    public List<CfControl> findControlsBySourceNo(String sourceNo) {
        return controlDao.findManyBySourceNo(sourceNo);
    }

    @Override
    public List<CfControl> findControls(Integer offset, Integer limit) {
        return controlDao.find(offset, limit);
    }

    @Override
    public List<CfControlItem> findControlItems(CfControl control) {
        return controlDao.findItems(control);
    }

    @Override
    public List<CfControlItem> findControlItems(CfControl control, boolean voteDetailed) {
        return decorate(control, voteDetailed, controlDao.findItems(control));
    }

    @Override
    public List<CfControlItem> findControlItems(CfControl control, Integer level) {
        return controlDao.findItems(control, level);
    }

    @Override
    public List<CfControlItem> findControlItems(CfControl control, Integer offset, Integer limit) {
        return controlDao.findItems(control, offset, limit);
    }

    @Override
    public List<CfControlItem> findControlItems(CfControl control, Integer level, Integer offset, Integer limit) {
        return controlDao.findItems(control, offset, limit, level);
    }

    @Override
    public List<CfControlItem> findControlItemChildren(CfControlItem item) {
        return decorate(controlDao.findItemChildren(item));
    }

    @Override
    public List<CfControlItem> findControlItemChildren(CfControlItem item, Integer offset, Integer limit) {
        return controlDao.findItemChildren(item, offset, limit);
    }

    @Override
    public Integer countControlItem(CfControl control) {
        return controlDao.countItem(control);
    }

    @Override
    public boolean isExist(CfPeriod period, CfControlType type) {
        return controlDao.isExists(period, type);
    }

    @Override
    public boolean isExist(CfControl control, CfControlType type) {
        return controlDao.isExists(control, type);
    }

    @Override
    public boolean isExist(CfControl control, CfControlType type, CfMetaState metaState) {
        return controlDao.isExists(control, type, metaState);
    }

    private List<CfControlItem> decorate(CfControl control, boolean voteDetailed, List<CfControlItem> items) {
        return items; // TODO
    }

    private List<CfControlItem> decorate(List<CfControlItem> items) {
        return items; // TODO
    }
}
