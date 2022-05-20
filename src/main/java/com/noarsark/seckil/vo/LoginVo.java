package com.noarsark.seckil.vo;

import com.noarsark.seckil.util.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author Noahs_Ark
 */
@Data
public class LoginVo {
    @NotNull
    @IsMobile
    private String mobile;

    @Length(min = 32)
    private String password;

    /*public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "LoginVo [mobile=" + mobile + ", password=" + password + "]";
    }*/
}
