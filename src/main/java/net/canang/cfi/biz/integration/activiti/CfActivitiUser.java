package net.canang.cfi.biz.integration.activiti;

import net.canang.cfi.core.so.model.CfUser;
import org.activiti.engine.impl.persistence.entity.UserEntity;

/**
 * @author rafizan.baharum
 * @since 10/2/13
 */
public class CfActivitiUser extends UserEntity {

    private CfUser user;

    public CfActivitiUser(CfUser user) {
        this.user = user;
    }

    @Override
    public String getId() {
        return user.getName();
    }

    @Override
    public void setId(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getFirstName() {
        return user.getName();
    }

    @Override
    public void setFirstName(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setLastName(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getLastName() {
        return user.getName();
    }

    @Override
    public void setEmail(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getEmail() {
        return user.getEmail();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public void setPassword(String s) {
        throw new UnsupportedOperationException();
    }
}
