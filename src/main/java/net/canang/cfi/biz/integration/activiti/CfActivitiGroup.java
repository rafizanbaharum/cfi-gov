package net.canang.cfi.biz.integration.activiti;

import net.canang.cfi.core.so.model.CfGroup;
import net.canang.cfi.core.so.model.CfRoleType;
import org.activiti.engine.impl.persistence.entity.GroupEntity;

/**
 * @author rafizan.baharum
 * @since 10/2/13
 */
public class CfActivitiGroup extends GroupEntity {

    private CfGroup group;

    public CfActivitiGroup(CfGroup group) {
        this.group = group;
    }

    @Override
    public String getId() {
        return group.getName();
    }

    @Override
    public void setId(String id) {
    }

    @Override
    public String getName() {
        return group.getName();
    }

    @Override
    public void setName(String name) {
        group.setName(name);
    }

    @Override
    public String getType() {
        return CfRoleType.ROLE_USER.name();
    }

    @Override
    public void setType(String string) {
    }

}
