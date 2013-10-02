package net.canang.cfi.core.am.dao;


import net.canang.cfi.core.am.model.CfAclObjectIdentity;
import net.canang.cfi.core.so.model.CfFlowState;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 */
public interface CfAclDao {

    List<CfAclObjectIdentity> find(String filter, Date startDate, Date endDate, Class clazz, Set<String> principal, int mask, Integer ofCtet, Integer limit);

    List<CfAclObjectIdentity> find(String filter, CfFlowState flowType, Date startDate, Date endDate, Class clazz, Set<String> principal, int mask, Integer ofCtet, Integer limit);

    Integer count(String filter, CfFlowState flowType, Date startDate, Date endDate, Class clazz, Set<String> principals, int mask);

}
