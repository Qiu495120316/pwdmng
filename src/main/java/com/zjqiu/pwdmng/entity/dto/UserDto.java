package com.zjqiu.pwdmng.entity.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long userId;
    private String loginName;
    private String password;
}
