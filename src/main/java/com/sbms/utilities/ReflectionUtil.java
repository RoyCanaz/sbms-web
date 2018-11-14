/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.utilities;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class ReflectionUtil {
    
    public static List<String> getFieldNamesFromObject(Object object){
        List<String> fieldNames = new ArrayList<>();
        Class clasz = object.getClass();
        Field [] fields = clasz.getDeclaredFields();
        for(Field field : fields){
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }
}
