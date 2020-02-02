package com.zjqiu.pwdmng.service;

import com.zjqiu.pwdmng.dao.MenuMapper;
import com.zjqiu.pwdmng.entity.dto.MenuDto;
import com.zjqiu.pwdmng.entity.modal.Menu;
import com.zjqiu.pwdmng.entity.modal.MenuExample;
import com.zjqiu.pwdmng.entity.modal.details.MenuDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    private Integer tabIndex = 0;

     public String getHomeMenuString(){
        StringBuilder menuBuilder = new StringBuilder();
        menuBuilder.append( "<table style=\"margin-bottom: 15px; \">" );
        menuBuilder.append( "<tr><td><div class=\"dropdown\">" );
        menuBuilder.append( "<a id=\"dLabel1\" role=\"button\" data-toggle=\"dropdown\" class=\"btn btn-primary\" data-target=\"#\" href=\"javascript:switchFrame('/welcome');\">" );
        menuBuilder.append( "首页" );
        menuBuilder.append( "</a>" );
        menuBuilder.append( "</div></td>" );
        //拿出所有菜单
        MenuDto menuDto = new MenuDto();
        List<MenuDetails> allMenus = menuMapper.findMenuDetails( menuDto );
        menuDto.setLevel( -1 );
        List<MenuDetails> menus = menuMapper.findMenuDetails( menuDto );
        menuBuilder.append( buildMenu( menus.get(0), allMenus , new StringBuilder() ) );
        menuBuilder.append( "<td><button type=\"button\" class=\"btn btn-info\">" );
        menuBuilder.append( "<i class=\"fa fa-gear\"></i>" );
        menuBuilder.append( "</button></td>" );
        menuBuilder.append( "<td><button type=\"button\" class=\"btn btn-danger\">" );
        menuBuilder.append( "<i class=\"fa fa-user\"></i>" );
        menuBuilder.append( "</button></td>" );
        menuBuilder.append( "<td><button type=\"button\" class=\"btn btn-success\">" );
        menuBuilder.append( "<i class=\"fa fa-sign-language\"></i>" );
        menuBuilder.append( "</button></td>" );
        menuBuilder.append( "<td><button type=\"button\" class=\"btn btn-default\">" );
        menuBuilder.append( "<i class=\"fa fa-sign-out\"></i>" );
        menuBuilder.append( "</button></td>" );
        menuBuilder.append( "</tr></table>" );
        return menuBuilder.toString();
    }

    public  StringBuilder buildMenu( MenuDetails root , List<MenuDetails> allMenus ,  StringBuilder builder ){
        List<MenuDetails> childs = new ArrayList<>();
        for ( MenuDetails menu : allMenus  ){
            if( root.getMenuId().equals( menu.getMenuParent() ) ){
                childs.add( menu );
            }
        }
        if( childs != null && !childs.isEmpty() ){
            for ( MenuDetails child : childs  ){
                if( "Y".equals( child.getHasChild() ) ){
                    switch( child.getLevel() ){
                        case 0 :
                            builder.append("<td><div class=\"dropdown\">");
                            builder.append("<a id=\"" + child.getMenuId() +"\" role=\"button\" data-toggle=\"dropdown\" class=\"btn btn-primary\" data-target=\"#\" href=\"javascript:void();\">");
                            builder.append( child.getMenuNameCn() );
                            builder.append("<span class=\"caret\"></span></a>");
                            builder.append("<ul class=\"dropdown-menu multi-level\" role=\"menu\" aria-labelledby=\"dropdownMenu\">");
                            builder = buildMenu( child, allMenus , builder  );
                            builder.append("</ul></div></td>");
                            break;
                        case 1 :
                            builder.append("<li class=\"dropdown-submenu\">");
                            builder.append("<a href=\"javascript: switchFrame('" + child.getMenuPath()  +"');\" class=\"submenu-left\">");
                            builder.append( child.getMenuNameCn());
                            builder.append("</a>");
                            builder.append("<ul class=\"dropdown-menu\">");
                            builder = buildMenu( child, allMenus , builder  );
                            builder.append("</ul></li>");
                            break;
                        case 2:
                            builder.append("<li class=\"dropdown-submenu\">");
                            builder.append("<a href=\"javascript: switchFrame('" + child.getMenuPath() +"');\" class=\"submenu-left\">");
                            builder.append( child.getMenuNameCn() );
                            builder.append("</a>");
                            builder.append("<ul class=\"dropdown-menu\">");
                            builder = buildMenu( child, allMenus , builder  );
                            builder.append("</ul>");
                            builder.append("</li>");
                            break;
                    }
                }else{
                    builder = buildMenu( child, allMenus , builder  );
                }
            }
        }else{
            switch ( root.getLevel() ){
                case 0:
                    builder.append("<td><div class=\"dropdown\">");
                    builder.append(" <a id=\"" + root.getMenuId() +"\" role=\"button\" data-toggle=\"dropdown\" class=\"btn btn-primary\" data-target=\"#\" href=\"javascript:switchFrame('" + root.getMenuPath() + "');\">");
                    builder.append( root.getMenuNameCn() );
                    builder.append("</a></div></td>");
                    break;
                case 1:
                case 2:
                    builder.append("<li><a href=\"javascript:switchFrame('" + root.getMenuPath() +"');\">");
                    builder.append( root.getMenuNameCn() );
                    builder.append("</a></li>");
                    break;
            }
        }
        allMenus.remove( root );
        return  builder;
    }


    private MenuExample buildExample(MenuDto dto){
        MenuExample example = new MenuExample();
        if( dto != null ){
            MenuExample.Criteria criteria = example.createCriteria();
            if( dto.getMenuId() != null ){
                criteria.andMenuIdEqualTo( dto.getMenuId() );
            }
            if( dto.getLevel() != null ){
                criteria.andLevelEqualTo( dto.getLevel() );
            }
            criteria.andEnableEqualTo( dto.isEnable() ? "Y" : "N" );
        }
        return example;
    }


}
