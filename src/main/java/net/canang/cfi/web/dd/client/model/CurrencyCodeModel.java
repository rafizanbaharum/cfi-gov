package net.canang.cfi.web.dd.client.model;

import net.canang.cfi.web.client.model.CodedModel;

import java.math.BigDecimal;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
public class CurrencyCodeModel extends CodedModel{

    public static final String SELL = "sell";
    public static final String BUY = "buy";

    public void setBuy(BigDecimal buy) {
        set(BUY, buy);
    }

    public BigDecimal getBuy() {
        return get(BUY);
    }

    public void setSell(BigDecimal sell) {
        set(SELL, sell);
    }

    public BigDecimal getSell() {
        return get(SELL);
    }
}
