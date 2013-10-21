package net.canang.cfi.web.dd.client.model;

import net.canang.cfi.web.client.model.CodedModel;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
public class CityCodeModel extends CodedModel {

    public static final String STATE_CODE = "stateCode";

    private StateCodeModel stateCode;

    public StateCodeModel getStateCode() {
        return get(STATE_CODE);
    }

    public void setStateCode(StateCodeModel code) {
        set(STATE_CODE, code);
    }


}



