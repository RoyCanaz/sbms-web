/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.validator;

import com.sbms.domain.Supplier;
import com.sbms.utilities.ReflectionUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author user
 */
@Component
public class SupplierValidator implements Validator{
    
    @Override
    public boolean supports(Class<?> type) {
        return Supplier.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ReflectionUtil.getFieldNamesFromObject(new Supplier()).forEach(name ->{
            
          /*  if( ! name.equals("serialVersionUID") && !name.equals("officePhone") && !name.equals("mobilePhone")&& !name.equals("website")){
                ValidationUtils.rejectIfEmpty(errors, name, "field.empty");
            }*/
        });
    }
}
