package net.canang.cfi.core.so.dao;

import net.canang.cfi.core.exception.RecursiveGroupException;
import net.canang.cfi.core.so.model.CfGroup;
import net.canang.cfi.core.so.model.CfPrincipal;
import net.canang.cfi.core.so.model.CfPrincipalType;
import net.canang.cfi.core.so.model.CfUser;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Set;

/**
 * @author canang.technologies
 * @since 7/12/13
 */
public interface CfPrincipalDao {

    //principal

    CfPrincipal findById(Long id);

    CfPrincipal findByName(String name);

    List<CfPrincipal> findAllPrincipals();

    List<CfPrincipal> findPrincipals(String filter);

    List<CfPrincipal> findPrincipals(String filter, CfPrincipalType type);

    List<CfPrincipal> findPrincipals(Integer offset, Integer limit);

    Set<CfGroup> loadEffectiveGroups(CfPrincipal principal) throws RecursiveGroupException;

    Set<GrantedAuthority> loadEffectiveAuthorities(CfPrincipal principal) throws RecursiveGroupException;

    // cruds

    void save(CfPrincipal principal, CfUser user);

    void update(CfPrincipal principal, CfUser user);

    void deactivate(CfPrincipal principal, CfUser user);


}
