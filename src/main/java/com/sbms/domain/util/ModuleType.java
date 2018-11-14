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
public enum ModuleType {
    SALES(1), HR(2), TRANSPORT(3);
    private final Integer code;

    private ModuleType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
    public static ModuleType get(Integer code){
        switch(code){
            case 1 :
                return SALES;
                case 2 :
                    return HR;
                    case 3 :
                        return TRANSPORT;
                        
            default:
                throw new IllegalArgumentException("Illegal parameter passed to method :" + code);
        }
    }
    public static List<ModuleType> getModuleType(){
        List<ModuleType> items = new ArrayList<>();
        items.add(SALES);
        items.add(HR);
        items.add(TRANSPORT);
        return items;
    }
    
    public String getName(){
        return StringUtils.toCamelCase3(super.name());
    }
}
