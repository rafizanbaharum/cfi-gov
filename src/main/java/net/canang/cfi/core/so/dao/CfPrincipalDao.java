package net.canang.cfi.core.so.dao;

import net.canang.cfi.core.exception.RecursiveGroupException;
import net.canang.cfi.core.so.model.CfPrincipal;
import net.canang.cfi.core.so.model.CfPrincipalType;
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

    Set<GrantedAuthority> loadEffectiveAuthorities(CfPrincipal principal) throws RecursiveGroupException;

}
