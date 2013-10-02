package net.canang.cfi.core.dd.model;


import net.canang.cfi.core.so.model.CfMetaObject;

public interface CfSodoCode extends CfMetaObject {

    String getCode();

    String getAlias();

    String getDescription();

    CfSodoCode getParent();

    CfAccountCode getAccountCode();

    String getSummary();

}
