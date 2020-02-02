package com.zjqiu.pwdmng.config;

import com.zjqiu.pwdmng.interceptor.TimeInterceptor;
import com.zjqiu.pwdmng.interceptor.TimeoutInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created with XP.
 * User: Administration
 * Date: 2019/5/29
 * Time: 15:59
 * Description: 控制层拦截器的配置类
 */
@Configuration
public class WebApplicationConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TimeInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new TimeoutInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
