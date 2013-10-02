package net.canang.cfi.core.so.model;

import java.util.Date;

/**
 * @author canang.technologies
 * @since 10/2/13
 */
public interface CfVendor extends CfActor {

    String getCertNo();

    void setCertNo(String certNo);

    boolean isBumiputera();

    void setBumiputera(boolean bumiputera);

    Date getStartDate();

    void setStartDate(Date startDate);

    Date getEndDate();

    void setEndDate(Date endDate);
}
