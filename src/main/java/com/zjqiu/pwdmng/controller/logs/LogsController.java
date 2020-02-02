package com.zjqiu.pwdmng.controller.logs;


import com.zjqiu.pwdmng.entity.BaseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/logs/")
public class LogsController {

    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public BaseResult<List<String>> list(){
        System.out.println();
        System.out.println();
        File logPath = new File( System.getProperty("user.dir") );
        System.out.println( logPath.getAbsolutePath() );
        System.out.println( logPath.exists() );
        List<String> result = new ArrayList<String>();
        return BaseResult.returnSuccessResult( result );
    }




}
