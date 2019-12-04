package com.zjqiu.pwdmng.resolver;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;


public class ThymeleafResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //获取连接路径上的l后的值 l=zh_CN or en_US
        String lang = request.getParameter("lang");
        //如果没有值则使用默认语言版本
        Locale locale = Locale.getDefault();
        //如果有值则进行语言切换
        if(!StringUtils.isEmpty(lang) ){
            String[] split = lang.split("_");
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
