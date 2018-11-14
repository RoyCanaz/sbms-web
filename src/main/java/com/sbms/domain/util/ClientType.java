/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.domain.util;

import com.sbms.utilities.StringUtils;

/**
 *
 * @author user
 */
public enum ClientType {
    COMPANY(1), INDIVIDUAL(2);
    private final Integer code;
    
    private ClientType(Integer code){
        this.code = code;
    }
    
    public Integer getCode(){
        return code;
    }
    
    public static ClientType get(Integer code){
        switch(code){
            case 1:
                return COMPANY;
            case 2:
                return INDIVIDUAL;
            default:
                throw new IllegalArgumentException("Unknown parameter passed to method expected {1-2} and receieved :"+code);
        }
    }
    
    public static ClientType get(String code){
        switch(code){
             case "company":
                return COMPANY;
            case "individual":
                return INDIVIDUAL;         
            default:
                throw new IllegalArgumentException("Unknown parameter passed to method expected {1-2} and receieved :"+code);
        }
    }
    
    public String getName() {
        return StringUtils.toCamelCase3(super.name());
    }
    
    private static final ClientType[] VALUES = {ClientType.COMPANY, ClientType.INDIVIDUAL};
    
    public static ClientType[] getItems(){
        return VALUES.clone();
    }
}
