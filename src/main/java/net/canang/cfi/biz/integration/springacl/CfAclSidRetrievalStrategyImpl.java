package net.canang.cfi.biz.integration.springacl;

import net.canang.cfi.core.so.dao.CfPrincipalDao;
import net.canang.cfi.core.so.dao.CfUserDao;
import net.canang.cfi.core.so.model.CfGroup;
import org.apache.log4j.Logger;
import org.perf4j.aop.Profiled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.domain.SidRetrievalStrategyImpl;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author rafizan.baharum
 * @since 10/2/13
 */
public class CfAclSidRetrievalStrategyImpl extends SidRetrievalStrategyImpl{

    private static final Logger log = Logger.getLogger(CfAclSidRetrievalStrategyImpl.class);
    
    @Autowired
    private CfPrincipalDao principalDao;

    @Autowired
    private CfUserDao userDao;

    @Profiled
    @Override
    public List<Sid> getSids(Authentication authentication) {

        Set<CfGroup> groups = null;

        // add current sid to collection based
        // on our authentication
        List<Sid> sids = new ArrayList<Sid>();
        sids.addAll(super.getSids(authentication));

        // find all groups by username
//        try {
            if (authentication.getPrincipal() instanceof UserDetails) {
                String username = ((UserDetails) authentication.getPrincipal()).getUsername();
                //TODO:
                throw new UnsupportedOperationException("TODO");
//                groups = principalDao.loadEffectivePrincipalGroups(userDao.findByUsername(username));
            } else {
                groups = new HashSet<CfGroup>();
            }
//        } catch (RecursiveGroupException e) {
//            groups = new HashSet<CfGroup>();
//        }

        // add groups to sids collection
        for (CfGroup group : groups) {
            sids.add(new PrincipalSid(group.getName()));
        }
        return sids;
    }
}
