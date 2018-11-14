/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.domain.util;

import com.sbms.utilities.StringUtils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public enum UserRoles {
    GLOBAL(1), SUPER_ADMIN(2), ADMIN(3), USER(4);
     private final Integer code;
    
    private UserRoles(Integer code){
        this.code = code;
    }
    
    public Integer getCode(){
        return code;
    }
    public static UserRoles get(Integer code){
        switch(code){
            case 1:
                return GLOBAL;
            case 2:
                return SUPER_ADMIN;
            case 3:
                return ADMIN;
            case 4:
                return USER;
            default:
                throw new IllegalArgumentException("Illegal parameter passed to method :" + code);
        }
    }
     public static List<UserRoles> getUserRoles(){
        List<UserRoles> items = new ArrayList<>();
        items.add(GLOBAL);
        items.add(SUPER_ADMIN);
        items.add(ADMIN);
        items.add(USER);  
        return items;
    }
     public String getName(){
        return StringUtils.toCamelCase3(super.name());
    }
}
