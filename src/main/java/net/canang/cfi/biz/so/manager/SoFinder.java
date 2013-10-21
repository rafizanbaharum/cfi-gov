package net.canang.cfi.biz.so.manager;

import net.canang.cfi.core.dd.model.CfReferenceNo;
import net.canang.cfi.core.so.model.*;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 10/20/13
 */
public interface SoFinder {

    // =============================================================================
    // PRINCIPAL METHODS
    // =============================================================================

    CfPrincipal findPrincipalById(Long id);

    CfPrincipal findPrincipalByName(String name);

    List<CfPrincipal> findGroupMembers(CfGroup group);

    List<CfPrincipal> findGroupMembers(CfGroup group, CfPrincipalType type);

    List<CfPrincipal> findAllPrincipals();

    List<CfPrincipal> findPrincipals(String filter);

    // =============================================================================
    // USER METHODS
    // =============================================================================

    CfUser findUserById(Long id);

    CfUser findUserByUsername(String username);

    CfUser findUserByActor(CfActor actor);

    List<CfUser> findUsers(Integer offset, Integer limit);

    List<CfUser> findUsers(String filter, Integer offset, Integer limit);

    Integer countUser();

    Integer countUser(String filter);

    // =============================================================================
    // GROUP METHODS
    // =============================================================================

    CfGroup findGroupById(Long id);

    CfGroup findGroupByName(String name);

    List<CfGroup> findGroups(Integer offset, Integer limit);

    List<CfGroup> findGroups();

    List<CfGroup> findGroups(String filter, Integer offset, Integer limit);

    List<CfGroup> findGroups(CfPrincipal principal);

    Integer countGroup();

    Integer countGroup(String filter);

    //====================================================================================================
    // ACTOR
    //====================================================================================================

    CfActor findActorById(Long id);

    CfActor findActorByIdentityNo(String identityNo);

    List<CfActor> findActors(Integer offset, Integer limit);

    List<CfActor> findActors(CfActorType type, Integer offset, Integer limit);

    List<CfActor> findActors(String filter, Integer offset, Integer limit);

    Integer countActor();

    Integer countActor(CfActorType type);

    Integer countActor(String filter);

    //====================================================================================================
    // CONFIGURATION
    //====================================================================================================

    CfConfiguration findConfigurationById(Long id);

    CfConfiguration findConfigurationByKey(String key);

    List<CfConfiguration> findConfigurations(Integer offset, Integer limit);

    List<CfConfiguration> findConfigurations(String filter, Integer offset, Integer limit);

    Integer countConfiguration();

    Integer countConfiguration(String filter);

    //====================================================================================================
    // REFERENCE NO
    //====================================================================================================

    CfReferenceNo findReferenceNoById(Long id);

    CfReferenceNo findReferenceNoByCode(String code);

    List<CfReferenceNo> findReferenceNos(Integer offset, Integer limit);

    List<CfReferenceNo> findReferenceNos(String filter, Integer offset, Integer limit);

    Integer countReferenceNo();

    Integer countReferenceNo(String filter);

}
