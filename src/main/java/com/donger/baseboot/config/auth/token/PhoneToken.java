package com.donger.baseboot.config.auth.token;

import org.apache.shiro.authc.AuthenticationToken;

public class PhoneToken implements AuthenticationToken {

    private String phone;
    private String code;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public Object getPrincipal() {
        return phone;
    }

    @Override
    public Object getCredentials() {
        return code;
    }
}
