package com.zjqiu.pwdmng.entity.dto;

import lombok.Data;

@Data
public class MenuDto {

    private Long menuId;
    private String menuName;
    private Integer level;
    private boolean enable;

    public MenuDto(){}
    public MenuDto( boolean enable ){
        this.enable = enable;
    }

}
