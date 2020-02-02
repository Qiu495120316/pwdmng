package com.zjqiu.pwdmng.interceptor;

import com.google.common.base.Strings;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.zjqiu.pwdmng.utils.JSONUtils;
import com.zjqiu.pwdmng.utils.XmlUtil;
import lombok.Data;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.InputSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;


public class TimeoutInterceptor implements HandlerInterceptor {

    private Logger log = LoggerFactory.getLogger(TimeoutInterceptor.class);

    private final static String FILTER_PATH = "./src/main/resources/loginFilter.xml";

    private static  LoginFilter loginFilter;

     {
        loginFilter = new LoginFilter();
        try {
            SAXReader sax=new SAXReader();//创建一个SAXReader对象
            File xmlFile = new File(FILTER_PATH);//根据指定的路径创建file对象
            Document document= sax.read(xmlFile);//获取document对象,如果文档无节点，则会抛出Exception提前结束
            Element root = document.getRootElement();//获取根节点

            loginFilter = new LoginFilter();
            Element pathNode = getNodeByName( root , "path" );
            List<Element> listElement = pathNode.elements();//所有一级子节点的list
            for(Element e:listElement){//遍历所有一级子节点
                loginFilter.getPath().add(  e.getTextTrim() );
            }
            Element pageNode = getNodeByName( root , "page" );
            listElement = pageNode.elements();//所有一级子节点的list
            for(Element e:listElement){//遍历所有一级子节点
                loginFilter.getPage().add(  e.getTextTrim() );
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("数据解析错误");
        }
    }

    @Override
    public synchronized boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String uri = request.getRequestURI();
        if(Strings.isNullOrEmpty( uri ) || "/".equals( uri ) ){
            return  true;
        }else{
            uri = uri.substring( 1 , uri.length() );
            for( String path: loginFilter.getPath() ){
                if( uri.startsWith( path )){
                    return true;
                }
            }
            for( String page: loginFilter.getPage() ){
                if( uri.endsWith( page )  ){
                    return true;
                }
            }
        }
        if( request.getSession().getAttribute("loginInfo") == null ){
            try {
                response.sendRedirect(request.getContextPath() + "/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;

    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


    static Element getNodeByName(  Element node , String nodeName ){
        if( nodeName.equals( node.getName() ) ){
            return node;
        }else{
            List<Element> listElement = node.elements();//所有一级子节点的list
            for(Element e:listElement){//遍历所有一级子节点
                node =  getNodeByName( e , nodeName  );
                if( node != null ){
                    return node;
                }
            }
        }
        return null;
    }



}
