package net.canang.cfi.web.dd.client.model;

import net.canang.cfi.web.client.model.CodedModel;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
public class PositionCodeModel extends CodedModel {

    public static final String GRADE = "grade";

    private Integer grade;

    public Integer getGrade() {
        return get(GRADE);
    }

    public void setGrade(Integer grade) {
        set(GRADE, grade);
    }

}
