package net.canang.cfi.biz.integration.springacl;

import org.springframework.security.acls.domain.AbstractPermission;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public class CfAclPermission extends AbstractPermission {

    private static final long serialVersionUID = 1L;

    public static final CfAclPermission VIEW = new CfAclPermission(1 << 0, 'V'); //   0000001 // 1
    public static final CfAclPermission CREATE = new CfAclPermission(1 << 1, 'C'); // 0000010 // 2
    public static final CfAclPermission UPDATE = new CfAclPermission(1 << 2, 'U'); // 0000100 //  4
    public static final CfAclPermission DELETE = new CfAclPermission(1 << 3, 'D'); // 0001000 // 8
    public static final CfAclPermission CANCEL = new CfAclPermission(1 << 4, 'K'); // 0010000 // 16
    public static final CfAclPermission PRINT = new CfAclPermission(1 << 5, 'P'); //  0100000 // 32
    public static final CfAclPermission ADMIN = new CfAclPermission(1 << 6, 'A'); //  1000000 // 64

    protected CfAclPermission(int mask, char code) {
        super(mask, code);
    }

    protected CfAclPermission(int mask) {
        super(mask);
    }


}
