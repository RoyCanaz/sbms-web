/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.validator;

import com.sbms.domain.Product;
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
public class ProductValidator implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return Product.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
         ValidationUtils.rejectIfEmpty(errors, "sellingPrice", "field.empty");
    }
    
}
