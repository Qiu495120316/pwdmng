package com.zjqiu.pwdmng.entity.dto;

public class UserDto {

    private Long userId;
    private String loginName;
    private String password;

    public Long getUserId() {
        return userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
