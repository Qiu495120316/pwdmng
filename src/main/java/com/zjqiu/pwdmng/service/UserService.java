package com.zjqiu.pwdmng.service;

import com.zjqiu.pwdmng.entity.dto.LoginDto;
import com.zjqiu.pwdmng.entity.dto.SearchDto;
import com.zjqiu.pwdmng.entity.modal.User;


import java.util.List;


public interface UserService {

    User getUserByDto(LoginDto dto );

    List<User> getUsersBySearchDto(SearchDto dto);


}
