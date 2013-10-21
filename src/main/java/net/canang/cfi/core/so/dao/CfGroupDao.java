package net.canang.cfi.core.so.dao;

import net.canang.cfi.core.exception.LockedGroupException;
import net.canang.cfi.core.exception.RecursiveGroupException;
import net.canang.cfi.core.so.model.*;

import java.util.List;
import java.util.Set;

/**
 * @author canang.technologies
 * @since 7/11/13
 */
public interface CfGroupDao {

    // finders

    CfGroup findById(Long id);

    CfGroup findByName(String name);

    List<CfGroup> findAll();

    List<String> findAllGroupName();

    List<CfPrincipal> findMembers(CfGroup group);

    List<CfPrincipal> findMembers(CfGroup group, CfPrincipalType type);

    List<CfGroup> findMemberships(CfPrincipal principal);

    List<CfPrincipal> findMembers(CfGroup group, Integer offset, Integer limit);

    List<CfGroup> find(Integer offset, Integer limit);

    List<CfGroup> find(String filter, Integer offset, Integer limit);

    List<CfGroup> findParentGroup(CfGroup group);

    Set<CfGroup> findHierarchicalGroupAsNative(CfPrincipal principal);

    CfGroupMember findGroupMember(CfGroup group, CfPrincipal principal);

    Integer count();

    Integer count(String filter);

    boolean isMemberOf(CfGroup group, CfPrincipal principal);

    void save(CfGroup group, CfUser user);

    void update(CfGroup group, CfUser user);

    void deactivate(CfGroup group, CfUser user);

    void remove(CfGroup group, CfUser user) throws LockedGroupException;

    void addMember(CfGroup group, CfPrincipal principal, CfUser user) throws RecursiveGroupException, LockedGroupException;

    void addMembers(CfGroup group, List<CfPrincipal> principals, CfUser user) throws RecursiveGroupException, LockedGroupException;

    void removeMember(CfGroup group, CfPrincipal principal) throws LockedGroupException;

}
