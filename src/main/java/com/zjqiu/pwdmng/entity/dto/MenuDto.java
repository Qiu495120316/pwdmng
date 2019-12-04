package com.zjqiu.pwdmng.entity.dto;

public class MenuDto {

    private Long menuId;
    private String menuName;
    private boolean enable;

    public MenuDto(){}
    public MenuDto( boolean enable ){
        this.enable = enable;
    }



    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getMenuId() {
        return menuId;
    }

    public String getMenuName() {
        return menuName;
    }
}
