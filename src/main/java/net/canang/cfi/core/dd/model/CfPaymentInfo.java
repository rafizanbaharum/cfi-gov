package net.canang.cfi.core.dd.model;

import javax.persistence.Embeddable;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Embeddable
public interface CfPaymentInfo {

    CfPaymentMethod getPaymentMethod();

    void setPaymentMethod(CfPaymentMethod method);

    CfBankCode getBankCode();

    void setBankCode(CfBankCode bankCode);

    String getAccountNo();

    void setAccountNo(String accountNo);
}
