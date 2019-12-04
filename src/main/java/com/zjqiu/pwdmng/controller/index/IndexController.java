package com.zjqiu.pwdmng.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class IndexController {

    @RequestMapping(value={"/","/index"})
    public String index(Map<String,Object> map){
        SimpleDateFormat format =  new SimpleDateFormat( "YYYY-MM-dd hh:mm:ss" );
        map.put("visitTime" , format.format( new Date() ));
        return  "login" ;
    }
    @RequestMapping("/404")
    public String error(){
        return "/404";
    }

}
