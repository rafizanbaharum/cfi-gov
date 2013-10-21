package net.canang.cfi.biz.so.manager;

import net.canang.cfi.core.dd.dao.CfReferenceNoDao;
import net.canang.cfi.core.dd.model.CfReferenceNo;
import net.canang.cfi.core.so.dao.*;
import net.canang.cfi.core.so.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
public class SoFinderImpl implements SoFinder {

    @Autowired
    private CfPrincipalDao principalDao;

    @Autowired
    private CfActorDao actorDao;

    @Autowired
    private CfUserDao userDao;

    @Autowired
    private CfGroupDao groupDao;

    @Autowired
    private CfReferenceNoDao referenceNoDao;

    @Autowired
    private CfConfigurationDao configurationDao;

    // ====================================================================================================
    // PRINCIPAL METHODS
    // ====================================================================================================

    @Override
    public CfPrincipal findPrincipalById(Long Id) {
        return principalDao.findById(Id);
    }

    @Override
    public CfPrincipal findPrincipalByName(String name) {
        return principalDao.findByName(name);
    }

    @Override
    public List<CfPrincipal> findGroupMembers(CfGroup group) {
        return groupDao.findMembers(group);
    }

    @Override
    public List<CfPrincipal> findGroupMembers(CfGroup group, CfPrincipalType type) {
        return groupDao.findMembers(group, type);
    }

    @Override
    public List<CfPrincipal> findAllPrincipals() {
        return principalDao.findAllPrincipals();
    }

    @Override
    public List<CfPrincipal> findPrincipals(String filter) {
        return principalDao.findPrincipals(filter);
    }

    // ====================================================================================================
    // USER METHODS
    // ====================================================================================================

    @Override
    public CfUser findUserById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public CfUser findUserByUsername(String username) {
        return null;  // TODO:

    }

    @Override
    public CfUser findUserByActor(CfActor actor) {
        return null;  // TODO:

    }

    @Override
    public List<CfUser> findUsers(Integer offset, Integer limit) {
        return null;  // TODO:

    }

    @Override
    public List<CfUser> findUsers(String filter, Integer offset, Integer limit) {
        return null;  // TODO:

    }

    @Override
    public Integer countUser() {
        return null;  // TODO:

    }

    @Override
    public Integer countUser(String filter) {
        return null;  // TODO:

    }

    // ====================================================================================================
    // GROUP METHODS
    // ====================================================================================================

    @Override
    public CfGroup findGroupById(Long id) {
        return groupDao.findById(id);
    }

    @Override
    public CfGroup findGroupByName(String name) {
        return groupDao.findByName(name);
    }

    @Override
    public List<CfGroup> findGroups(Integer offset, Integer limit) {
        return groupDao.find(offset, limit);
    }

    @Override
    public List<CfGroup> findGroups() {
        return groupDao.findAll();
    }

    @Override
    public List<CfGroup> findGroups(String filter, Integer offset, Integer limit) {
        return groupDao.find(filter, offset, limit);
    }

    @Override
    public List<CfGroup> findGroups(CfPrincipal principal) {
        return groupDao.findMemberships(principal);
    }

    @Override
    public Integer countGroup() {
        return groupDao.count();
    }

    @Override
    public Integer countGroup(String filter) {
        return groupDao.count(filter);
    }

    // ====================================================================================================
    // ACTOR METHODS
    // ====================================================================================================

    @Override
    public CfActor findActorById(Long id) {
        return actorDao.findById(id);
    }

    @Override
    public CfActor findActorByIdentityNo(String identityNo) {
        return actorDao.findByIdentityNo(identityNo);
    }

    @Override
    public List<CfActor> findActors(Integer offset, Integer limit) {
        return actorDao.find(offset, limit);
    }

    @Override
    public List<CfActor> findActors(CfActorType type, Integer offset, Integer limit) {
        return actorDao.find(type, offset, limit);
    }

    @Override
    public List<CfActor> findActors(String filter, Integer offset, Integer limit) {
        return actorDao.find(filter, offset, limit);
    }

    @Override
    public Integer countActor() {
        return actorDao.count();
    }

    @Override
    public Integer countActor(CfActorType type) {
        return actorDao.count(type);
    }

    @Override
    public Integer countActor(String filter) {
        return actorDao.count(filter);
    }

    // ====================================================================================================
    // REFERENCE METHODS
    // ====================================================================================================


    @Override
    public CfReferenceNo findReferenceNoById(Long id) {
        return referenceNoDao.findById(id);
    }

    @Override
    public CfReferenceNo findReferenceNoByCode(String code) {
        return referenceNoDao.findByCode(code);
    }


    @Override
    public List<CfReferenceNo> findReferenceNos(Integer offset, Integer limit) {
        return referenceNoDao.find(offset, limit);
    }

    @Override
    public List<CfReferenceNo> findReferenceNos(String filter, Integer offset, Integer limit) {
        return referenceNoDao.find(filter, offset, limit);
    }


    @Override
    public Integer countReferenceNo() {
        return referenceNoDao.count();
    }

    @Override
    public Integer countReferenceNo(String filter) {
        return referenceNoDao.count(filter);
    }


    // ====================================================================================================
    // CONFIGURATION METHODS
    // ====================================================================================================

    @Override
    public CfConfiguration findConfigurationById(Long id) {
        return configurationDao.findById(id);
    }

    @Override
    public CfConfiguration findConfigurationByKey(String key) {
        return configurationDao.findByKey(key);
    }

    @Override
    public List<CfConfiguration> findConfigurations(Integer offset, Integer limit) {
        return configurationDao.find(offset, limit);
    }

    @Override
    public List<CfConfiguration> findConfigurations(String filter, Integer offset, Integer limit) {
        return configurationDao.find(filter, offset, limit);
    }

    @Override
    public Integer countConfiguration() {
        return configurationDao.count();
    }

    @Override
    public Integer countConfiguration(String filter) {
        return configurationDao.count(filter);
    }
}
