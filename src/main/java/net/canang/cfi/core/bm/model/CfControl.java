package net.canang.cfi.core.bm.model;

import net.canang.cfi.core.dd.model.CfPeriod;
import net.canang.cfi.core.so.model.CfDocument;

import java.math.BigDecimal;
import java.util.List;


public interface CfControl extends CfDocument {

    BigDecimal getTotalAmount();

    void setTotalAmount(BigDecimal totalAmount);

    CfControlType getControlType();

    void setControlType(CfControlType type);

    CfPeriod getSource();

    void setSource(CfPeriod source);

    CfPeriod getSink();

    void setSink(CfPeriod sink);

    List<CfControlItem> getItems();

    void setItems(List<CfControlItem> items);
}
