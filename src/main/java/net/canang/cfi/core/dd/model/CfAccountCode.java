package net.canang.cfi.core.dd.model;

import net.canang.cfi.core.so.model.CfMetaObject;

/**
 * todo: comment
 * A B E L H P
 *
 * @author canang.technologies
 */
public interface CfAccountCode extends CfMetaObject {

    public static final String A = "A";
    public static final String B = "B";
    public static final String E = "E";
    public static final String L = "L";
    public static final String H = "H";
    public static final String P = "P";

    String getCode();

    String getAlias();

    String getDescription();

}
