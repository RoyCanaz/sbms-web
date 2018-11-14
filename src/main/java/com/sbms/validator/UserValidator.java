/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.validator;

import com.sbms.domain.User;
import com.sbms.service.UserService;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author user
 */
@Component
public class UserValidator implements Validator{

    @Resource
    private UserService userService;
    @Override
    public boolean supports(Class<?> type) {
        return User.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
         User user = (User) o;
         User old = null;
         ValidationUtils.rejectIfEmpty(errors, "firstName", "field.empty");
         ValidationUtils.rejectIfEmpty(errors, "lastName", "field.empty");
         ValidationUtils.rejectIfEmpty(errors, "userName", "field.empty");
        
         if (user.getId() == null) {
            ValidationUtils.rejectIfEmpty(errors, "password", "field.empty");
            ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "field.empty");
            ValidationUtils.rejectIfEmpty(errors, "userRoles", "field.empty");
        }
          
        if (user.getId() == null) {
            if (!user.getPassword().equals(user.getConfirmPassword())) {
                errors.rejectValue("confirmPassword", "password.confirm");
                
            }
            
        }
        if(user.getId()!=null){
            if(user.getPassword().length()>1){
                if (!user.getPassword().equals(user.getConfirmPassword())) {
                errors.rejectValue("confirmPassword", "password.confirm");
                
            }
            }
        }
       /*  if (!EmailValidator.getInstance().isValid(user.getUserName())) {
            errors.rejectValue("userName", "email.format");
        }*/
         if (user.getId() != null) {
            old = userService.get(user.getId());
        }
         
        if (userService.checkDuplicate(user, old)) {
            errors.rejectValue("userName", "item.duplicate");
        }
    }
    
}
