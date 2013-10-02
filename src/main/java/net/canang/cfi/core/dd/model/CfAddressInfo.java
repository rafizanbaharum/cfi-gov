package net.canang.cfi.core.dd.model;

import javax.persistence.Embeddable;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Embeddable
public interface CfAddressInfo {

    String getAddress1();

    void setAddress1(String address1);

    String getAddress2();

    void setAddress2(String address2);

    String getAddress3();

    void setAddress3(String address3);

    String getAddress4();

    void setAddress4(String address4);

    CfCityCode getCityCode();

    void setCityCode(CfCityCode city);

    String getPostcode();

    void setPostcode(String postcode);

    CfDistrictCode getDistrictCode();

    void setDistrictCode(CfDistrictCode districtCode);

    CfStateCode getStateCode();

    void setStateCode(CfStateCode stateCode);

    CfCountryCode getCountryCode();

    void setCountryCode(CfCountryCode countryCode);
}
