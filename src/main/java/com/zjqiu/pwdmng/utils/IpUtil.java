package com.zjqiu.pwdmng.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 获取客户端ip地址
 * 文杰说proxy_add_x_forwarded_for会返回多个ip的，还不知道分隔符是什么，先不管了，以后真的用到这个方法再改 闻伴辉 2018-2-7。
 */
public class IpUtil {

    //静态方法，便于作为工具类
    public static String getIP() {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
                if (request.getHeader("proxy_add_x_forwarded_for") == null) {
                    return request.getRemoteAddr();
                }
                return request.getHeader("proxy_add_x_forwarded_for");

        } catch (Exception e) {
            return "";
        }
    }


    public static void main(String[] args) {
        //测试
    }

}
