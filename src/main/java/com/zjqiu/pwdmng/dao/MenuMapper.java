package com.zjqiu.pwdmng.dao;

import com.zjqiu.pwdmng.entity.dto.MenuDto;
import com.zjqiu.pwdmng.entity.modal.Menu;
import com.zjqiu.pwdmng.entity.modal.MenuExample;
import java.util.List;

import com.zjqiu.pwdmng.entity.modal.details.MenuDetails;
import org.apache.ibatis.annotations.Param;

public interface MenuMapper {
    long countByExample(MenuExample example);

    int deleteByExample(MenuExample example);

    int deleteByPrimaryKey(Long menuId);

    int insert(Menu record);

    int insertSelective(Menu record);

    List<Menu> selectByExample(MenuExample example);

    Menu selectByPrimaryKey(Long menuId);

    int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    /*自定义方法*/
    List<MenuDetails> findMenuDetails(@Param("dto") MenuDto dto);
}