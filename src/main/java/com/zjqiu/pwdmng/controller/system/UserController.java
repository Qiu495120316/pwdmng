package com.zjqiu.pwdmng.controller.system;

import com.zjqiu.pwdmng.basic.Resp;
import com.zjqiu.pwdmng.entity.dto.SearchDto;
import com.zjqiu.pwdmng.entity.dto.UserDto;
import com.zjqiu.pwdmng.entity.modal.User;
import com.zjqiu.pwdmng.entity.system.ConfigProper;
import com.zjqiu.pwdmng.service.UserService;
import com.zjqiu.pwdmng.utils.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConfigProper configProper;

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody UserDto dto , HttpServletRequest request){
        System.out.println( "get in login" );
        Resp<Object> result = null;
        if (!StringUtils.isEmpty( dto.getLoginName() ) && !StringUtils.isEmpty( dto.getPassword() ) ){
            User user = userService.getUserByDto(dto);
            if( user != null ){
                //保存userInfo到session中
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put( "userToken" , user.getLoginName() + UUID.randomUUID().toString() );
                resultMap.put( "userName" , user.getLoginName() );
                resultMap.put("isAdmin" , user.getIsAdmin());
                resultMap.put("userId" , user.getUserId() );
                HttpSession session =  request.getSession();
                session.setMaxInactiveInterval( Integer.parseInt( configProper.getSessionTimeout()  ) );
                session.setAttribute("loginInfo" , resultMap);
                result = Resp.create().success( "Login Successfully" , resultMap);
            }else{
                result = Resp.create().error("Username or password is wrong." , "Username or password is wrong.");
            }
        }else{
            result = Resp.create().error("Username or password is empty." , "Username or password is empty.");
        }
        System.out.println( "get out login" );
        return JSONUtils.parseObject2JsonString(result);
    }

    @RequestMapping(value="/search" , method = RequestMethod.POST)
    @ResponseBody
    public List<User> searchUsers(@RequestBody SearchDto dto){
        /*
        System.out.println(  );
        System.out.println( dto.toString() );
        System.out.println(  );
        */
        List<User> results = userService.getUsersBySearchDto( dto ) ;
        return results;
    }

}
