package com.zjqiu.pwdmng.controller.system;

import com.zjqiu.pwdmng.basic.Resp;
import com.zjqiu.pwdmng.service.MenuService;
import com.zjqiu.pwdmng.utils.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/getHomeMenuHTML" )
    @ResponseBody
    public String getHomeMenuHTML(  ){
        return JSONUtils.parseObject2JsonString(Resp.create().success( "获取成功" , menuService.getHomeMenuString() ));
    }

}
