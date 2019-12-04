package com.zjqiu.pwdmng.service;

import com.zjqiu.pwdmng.dao.UserMapper;
import com.zjqiu.pwdmng.entity.dto.SearchDto;
import com.zjqiu.pwdmng.entity.dto.UserDto;
import com.zjqiu.pwdmng.entity.modal.User;
import com.zjqiu.pwdmng.entity.modal.UserExample;
import com.zjqiu.pwdmng.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

   /* public List<User> getUsersBySearchDto(SearchDto dto){
        return mapper.selectByExample( buildExampleBySearch(dto) );
    }*/

    public List<User> getUsersBySearchDto(SearchDto dto){
        if( dto != null ){
            if( dto.getPage() != null && dto.getRows() != null ){
                int startIndex = (dto.getPage() -1 ) * dto.getRows() + 1;
                int endIndex = dto.getPage() * dto.getRows();
                dto.setPage( startIndex );
                dto.setRows( endIndex );
            }
            if( !StringUtils.isEmpty( dto.getSearch() ) &&  dto.getSearch().length() > 0 ){
                dto.setSearch( "%" +   dto.getSearch() + "%" );
            }else{
                dto.setSearch( null );
            }

            if( StringUtils.isNotEmpty( dto.getSort() ) ){
                dto.setSort( StringUtils.humpToLine2( dto.getSort() ) );
            }
        }
        return mapper.searchUsersWithPage( dto );
    }

    public List<User> getUsersByDto( UserDto dto  ){
        return mapper.selectByExample( buildExample( dto ) );
    }

    public User getUserByDto(UserDto dto ){
        List<User> users =  getUsersByDto( dto );
        return users != null && users.size() == 1 ? users.get(0) : null;
    }

    private UserExample buildExample(UserDto dto){
        UserExample example = null;
        if ( dto != null ){
            example = new UserExample();
            UserExample.Criteria criteria = example.createCriteria();
            if(!StringUtils.isEmpty( dto.getLoginName() )){
                criteria.andLoginNameEqualTo( dto.getLoginName() );
            }
            if ( !StringUtils.isEmpty( dto.getPassword() ) ){
                criteria.andPassswordEqualTo( dto.getPassword() );
            }
        }
        return example;
    }

    private UserExample buildExampleBySearch(SearchDto dto){
        UserExample example = null;
        if ( dto != null ){
            example = new UserExample();
            UserExample.Criteria criteria = example.createCriteria();
            if(!StringUtils.isEmpty( dto.getSearch() )){
                criteria.andLoginNameLike( dto.getSearch() );
            }
        }
        return example;
    }

}
