package com.zjqiu.pwdmng.config;


import com.zjqiu.pwdmng.utils.IpUtil;
import com.zjqiu.pwdmng.utils.JSONUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 实现Web层的日志切面
 * @author qzj
 * @version v0.0.1-SNAPSHOT
 */
@Aspect
@Component
public class ControllerAspect {

    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    /**
     * 定义一个切入点.
     * 解释下：
     *
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二个 * 任意包名
     * ~ 第三个 * 代表任意方法.
     * ~ 第四个 * 定义在web包或者子包
     * ~ 第五个 * 任意方法
     * ~ .. 匹配任意数量的参数.
     */
    @Pointcut("execution(* com.zjqiu.pwdmng.controller..*.*(..))")
    public void webLog(){
    }

    @Before("webLog()")
    public void doBefore( JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        logger.info("\r\n\r\n\r\n\r\n================以下是新的请求======================");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //region 打印请求内容
        logger.info("URI : " + request.getRequestURI().toString());
        logger.info("ServerName : " + request.getServerName().toString() + ":" + request.getServerPort());
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("IP : " + IpUtil.getIP());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        //获取所有参数方法一：
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            logger.info(paraName + " : " + request.getParameter(paraName));
        }
    }

    @AfterReturning(value = "webLog()",returning = "rvt")
    public void  doAfterReturning(JoinPoint joinPoint,Object rvt){
        // 处理完请求，返回内容
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        logger.info("{},{};返回数据:{}",method.getDeclaringClass().getName(),method.getName(),JSONUtils.toString(rvt));
    }

}