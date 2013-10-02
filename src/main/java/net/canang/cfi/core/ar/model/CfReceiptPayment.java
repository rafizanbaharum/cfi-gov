package net.canang.cfi.core.ar.model;

import net.canang.cfi.core.dd.model.CfBankCode;
import net.canang.cfi.core.dd.model.CfStateCode;
import net.canang.cfi.core.so.model.CfMetaObject;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
public interface CfReceiptPayment extends CfMetaObject {

    String getSourceNo();

    void setSourceNo(String sourceNo);

    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    Date getReceivedDate();

    void setReceivedDate(Date receivedDate);

    CfPayType getPayType();

    void setPayType(CfPayType payType);

    CfBankCode getBankCode();

    void setBankCode(CfBankCode bank);

    CfStateCode getStateCode();

    void setStateCode(CfStateCode state);

    CfReceipt getReceipt();

    void setReceipt(CfReceipt receipt);

    List<CfReceiptItem> getItems();

    void setItems(List<CfReceiptItem> items);
}
