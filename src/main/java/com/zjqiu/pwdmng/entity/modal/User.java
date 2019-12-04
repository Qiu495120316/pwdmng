package com.zjqiu.pwdmng.entity.modal;

import java.util.Date;

public class User {

    private Long userId;

    private Long userInfo;

    private String loginName;

    private String passsword;

    private String isAdmin;

    private Date createDate;

    private String createdBy;

    private Date lastUpdateDate;

    private String lastUpdatedBy;

/*    public static User instance( User data ){
        User user = new User();
        user.userId = data.getUserId();
        user.userInfo = data.getUserInfo();
        user.loginName = data.getLoginName();
        user.isAdmin = data.getIsAdmin();
        return user;
    }*/


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Long userInfo) {
        this.userInfo = userInfo;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPasssword() {
        return passsword;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword == null ? null : passsword.trim();
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin == null ? null : isAdmin.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy == null ? null : lastUpdatedBy.trim();
    }
}