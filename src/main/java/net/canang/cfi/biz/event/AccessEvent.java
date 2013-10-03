package net.canang.cfi.biz.event;

import net.canang.cfi.biz.integration.springacl.CfAclPermission;
import net.canang.cfi.core.so.model.CfDocument;
import net.canang.cfi.core.so.model.CfMetaObject;
import net.canang.cfi.core.so.model.CfPrincipal;
import org.springframework.context.ApplicationEvent;

/**
 * @author rafizan.baharum
 * @since 10/3/13
 */
public class AccessEvent extends ApplicationEvent {

    private CfMetaObject object;
    private CfAclPermission permission;
    private CfPrincipal principal;
    private Command command = Command.GRANT;

    public enum Command {GRANT, REVOKE}

    public AccessEvent(CfMetaObject object, CfPrincipal principal, CfAclPermission permission) {
        super(object);
        this.object = object;
        this.principal = principal;
        this.permission = permission;
    }

    public AccessEvent(CfMetaObject object, CfPrincipal principal, CfAclPermission permission, AccessEvent.Command command) {
        super(object);
        this.object = object;
        this.principal = principal;
        this.permission = permission;
        this.command = command;
    }

    public CfMetaObject getObject() {
        return object;
    }

    public void setDocument(CfDocument document) {
        this.object = document;
    }

    public CfAclPermission getPermission() {
        return permission;
    }

    public void setPermission(CfAclPermission permission) {
        this.permission = permission;
    }

    public CfPrincipal getPrincipal() {
        return principal;
    }

    public void setPrincipal(CfPrincipal principal) {
        this.principal = principal;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}

