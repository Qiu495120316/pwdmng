package com.zjqiu.pwdmng.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with XP.
 * User: Administration
 * Date: 2019/5/29
 * Time: 15:51
 * Description: 计算控制层方法执行耗时的拦截器
 */
public class TimeInterceptor implements HandlerInterceptor {
    private Logger log = LoggerFactory.getLogger(TimeInterceptor.class);
    private long start;     //方法执行的开始时间
    private long end;       //方法执行的结束时间
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        this.start=System.currentTimeMillis();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        String methodName = ((HandlerMethod) o).getMethod().getName();
        this.end=System.currentTimeMillis();
        log.info(methodName+"方法执行了："+(this.end-this.start)+"毫秒");
    }
}
