package com.zjqiu.pwdmng.service;

import com.zjqiu.pwdmng.dao.MenuMapper;
import com.zjqiu.pwdmng.entity.dto.MenuDto;
import com.zjqiu.pwdmng.entity.modal.Menu;
import com.zjqiu.pwdmng.entity.modal.MenuExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;


    public String getHomeMenuString(){
        StringBuilder menuBuilder = new StringBuilder();


        menuBuilder.append( " <nav class=\"navbar navbar-expand-lg bg-light\">" );
        menuBuilder.append( " <ul class=\"navbar-nav\">" );

        menuBuilder.append("<button type=\"button\" class=\"btn btn-default\" style=\"margin-right: 10px;\">");
        menuBuilder.append( "<img style=\"width: 20px; height: 18px; \" src=\"/images/icon_user_default.png\"/>" );
        menuBuilder.append("</button>");


        menuBuilder.append("<div class=\"dropdown\" style=\"margin-right: 10px;\">");
        menuBuilder.append("<button type=\"button\" class=\"btn btn-success dropdown-toggle\" data-toggle=\"dropdown\">");
        menuBuilder.append( "Setting" );
        menuBuilder.append("</button>");
        menuBuilder.append("<div class=\"dropdown-menu\">");
        menuBuilder.append("<a class=\"dropdown-item\" href=\"/home?lang=zh_CN\">中文</a>");
        menuBuilder.append("<a class=\"dropdown-item\" href=\"/home?lang=en_US\">English</a>");
        menuBuilder.append("</div></div>");
        //拿出所有菜单
        List<Menu> allMenus = getMenuListById( new MenuDto( true ) );
        List<Long> jumpIndex = new ArrayList<>();
        jumpIndex.add( (long)-1 );
        for ( int i = 0 ; i < allMenus.size() ; i ++ ){
            if( allMenus.get(i).getMenuParent() != null
                    &&  allMenus.get(i).getLevel() == 0 ){ //找出子目录
                if( "Y".equals(allMenus.get(i).getHasChild()) ){//是否有子节点
                    jumpIndex.add( allMenus.get(i).getMenuId() );
                    menuBuilder.append("<div class=\"dropdown\">");
                    menuBuilder.append("<button type=\"button\" class=\"btn btn-primary dropdown-toggle\" data-toggle=\"dropdown\">");
                    menuBuilder.append( allMenus.get(i).getMenuDesc() );
                    menuBuilder.append("</button>");
                    menuBuilder.append("<div class=\"dropdown-menu\">");
                    for( int j = 0 ; j < allMenus.size() ; j ++  ){
                        if( allMenus.get(j).getMenuParent() != null
                                && allMenus.get(j).getMenuParent()  == allMenus.get(i).getMenuId() ){
                            menuBuilder.append("<a class=\"dropdown-item\" href=\"javascript:void(0);\" ");
                            menuBuilder.append("onclick=\"openSubWeb('" + allMenus.get(j).getMenuPath() + "')\">");
                            menuBuilder.append(allMenus.get(j).getMenuDesc() +"</a>");
                        }
                    }
                    menuBuilder.append("</div></div>");
                }else{
                    menuBuilder.append("<li class=\"nav-item\">");
                    if( allMenus.get(i).getMenuPath() != null ){
                        menuBuilder.append("<a class=\"nav-link\" href=\"javascript:void(0);\" onclick=\"" + allMenus.get(i).getMenuPath() +"\">");
                        menuBuilder.append(allMenus.get(i).getMenuDesc() +"</a>");
                    }else{
                        menuBuilder.append("<a class=\"nav-link\" href=\"javascript:void(0);\">"+allMenus.get(i).getMenuDesc() +"</a>");
                    }
                    menuBuilder.append("</li>");
                }
            }
        }
        menuBuilder.append( " </ul> </nav> " );
        return menuBuilder.toString();
    }

    public  String buildLangueSwitor(){
        StringBuilder result = new StringBuilder();


        result.append( "<div class=\"collapse navbar-collapse justify-content-end pull-right\">");
        result.append(" <li class=\"nav-item\"><a class=\"nav-link\" href=\"javascript:void(0);\">中文</a></li>");
        result.append(" <li class=\"nav-item\"><a  class=\"nav-link\" href=\"javascript:void(0);\">English</a></li>");
        result.append( "</div>");
        return  result.toString();
    }

    public List<Menu> getMenuListById( MenuDto dto ){
        List<Menu> menus = menuMapper.selectByExample( buildExample( dto ) );
        return menus;
    }

    private MenuExample buildExample(MenuDto dto){
        MenuExample example = new MenuExample();
        if( dto != null ){
            MenuExample.Criteria criteria = example.createCriteria();
            if( dto.getMenuId() != null ){
                criteria.andMenuIdEqualTo( dto.getMenuId() );
            }
            criteria.andEnableEqualTo( dto.isEnable() ? "Y" : "N" );
        }
        return example;
    }


}
