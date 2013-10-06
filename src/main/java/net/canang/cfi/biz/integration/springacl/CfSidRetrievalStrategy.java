package net.canang.cfi.biz.integration.springacl;

import net.canang.cfi.core.so.dao.CfPrincipalDao;
import net.canang.cfi.core.so.model.CfGroup;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.SidRetrievalStrategyImpl;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author rafizan.baharum
 * @since 10/6/13
 */
public class CfSidRetrievalStrategy extends SidRetrievalStrategyImpl {

    private static final Logger log = Logger.getLogger(CfSidRetrievalStrategy.class);

    @Autowired
    private CfPrincipalDao principalDao;

    @Override
    public List<Sid> getSids(Authentication authentication) {
        Set<CfGroup> groups = null;

        // add current sid to collection based
        // on our authentication
        List<Sid> sids = new ArrayList<Sid>();
        sids.addAll(super.getSids(authentication));

        // find all groups by username
        long start = System.currentTimeMillis();
//        try {
            if (authentication.getPrincipal() instanceof UserDetails) {
                String username = ((UserDetails) authentication.getPrincipal()).getUsername();
//                groups = principalDao.loadEffectiveGroups(principalDao.findByName(username));     // todo
//            } else {
//                groups = new HashSet<CfGroup>();
            }
//        } catch (RecursiveGroupException e) {
//            groups = new HashSet<CfGroup>();
//        }

        // add groups to sids collection
//        for (CfGroup group : groups) {
//            sids.add(new PrincipalSid(group.getName()));
//        }
        return sids;
    }
}
