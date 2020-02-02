package com.zjqiu.pwdmng.entity.modal.details;


import com.zjqiu.pwdmng.entity.modal.Menu;
import lombok.Data;

@Data
public class MenuDetails extends Menu {

    private String menuNameCn;
    private String menuNameEn;
}
