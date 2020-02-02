package com.zjqiu.pwdmng.controller.home;


import com.zjqiu.pwdmng.service.MenuService;
import com.zjqiu.pwdmng.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 跳转请求controller
 */
@Controller
public class HomeController{

    @Autowired
    private MenuService menuService;
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value={"/gotoLogin", "/login" })
    public String gotoLogin(){
        return "login";
    }

    @RequestMapping("/home")
    public String home(Map<String ,String>  resultMap){
        //resultMap.put( "menuLists" , menuService.getHomeMenuString() );
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
