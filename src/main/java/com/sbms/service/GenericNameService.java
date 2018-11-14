/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author user
 */
interface GenericNameService <T extends Serializable> extends GenericService<T> {
    public T getByName(String name);
    
    
}
