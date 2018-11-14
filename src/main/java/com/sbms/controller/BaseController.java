/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.controller;

import com.sbms.domain.User;
import com.sbms.service.UserService;
import com.sbms.utilities.AppMessage;
import com.sbms.utilities.MessageType;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author user
 */
abstract public class BaseController {
    @Resource
    private UserService userService;
    
    @ModelAttribute("currentuser")
    public User getUserName(){
        return userService.getCurrentUser();
    }
    
     public AppMessage getMessage(Integer type){
        switch(type){
            case 1:
                return new AppMessage.MessageBuilder(Boolean.TRUE).message("Record saved").messageType(MessageType.MESSAGE).build();
            case 2:
                return new AppMessage.MessageBuilder(Boolean.TRUE).message("Record deleted").messageType(MessageType.MESSAGE).build();
            case 3:
                return new AppMessage.MessageBuilder(Boolean.TRUE).message("Operation cancelled").messageType(MessageType.MESSAGE).build();
            default:
                throw new IllegalArgumentException("Parameter provided not recognised :"+type);
        }
    }
}
