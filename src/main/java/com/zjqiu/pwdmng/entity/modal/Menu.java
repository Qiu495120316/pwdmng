package com.zjqiu.pwdmng.entity.modal;

import lombok.Data;

import java.util.Date;

@Data
public class Menu {
    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                "menuParent=" + menuParent +
                ", menuName='" + menuName + '\'' +
                ", menuPath='" + menuPath + '\'' +
                ", level=" + level +
                ", hasChild=" + hasChild +
                '}';
    }

    private Long menuId;

    private String menuName;

    private String menuDesc;

    private String menuPath;

    private Long menuParent;

    private Integer menuOrder;

    private String hasChild;

    private Integer level;

    private String enable;

    private Date createDate;

    private String createdBy;

    private Date lastUpdateDate;

    private String lastUpdatedBy;


}