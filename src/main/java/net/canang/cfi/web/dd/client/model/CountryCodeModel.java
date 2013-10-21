package net.canang.cfi.web.dd.client.model;

import net.canang.cfi.web.client.model.CodedModel;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
public class CountryCodeModel extends CodedModel {

    public static final String CURRENCY_CODE = "currencyCode";

    private CurrencyCodeModel currencyCode;

    public CurrencyCodeModel getCurrencyCode() {
        return get(CURRENCY_CODE);
    }

    public void setCurrencyCode(CurrencyCodeModel code) {
        set(CURRENCY_CODE, code);
    }
}
