package net.canang.cfi.core.dd.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * todo: comment
 *
 * @author canang.technologies
 */
@Embeddable
public interface CfConsumerInfo extends Serializable {

    String getName();

    void setName(String name);

    String getIdentityNo();

    void setIdentityNo(String identityNo);

    String getCode();

    void setCode(String code);

    String getAddress1();

    void setAddress1(String address1);

    String getAddress2();

    void setAddress2(String address2);

    String getAddress3();

    void setAddress3(String address3);

    String getPhone();

    void setPhone(String phone);

    String getEmail();

    void setEmail(String email);
}
