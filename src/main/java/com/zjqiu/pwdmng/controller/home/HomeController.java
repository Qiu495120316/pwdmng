package com.zjqiu.pwdmng.controller.home;


import com.zjqiu.pwdmng.basic.Resp;
import com.zjqiu.pwdmng.entity.dto.SearchDto;
import com.zjqiu.pwdmng.service.MenuService;
import com.zjqiu.pwdmng.service.UserService;
import com.zjqiu.pwdmng.utils.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


/**
 * 跳转请求controller
 */
@Controller
public class HomeController{

    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;

    @RequestMapping(value={"/gotoLogin", "/login" })
    public String gotoLogin(){
        return "login";
    }

    @RequestMapping("/home")
    public String home(Map<String ,String>  resultMap){
        resultMap.put( "menuLists" , menuService.getHomeMenuString() );
        return "home";
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping(value="/sys/manager/{path}")
    public String manager(@PathVariable String path  ){
        return "sys/manager-" + path;
    }

}
