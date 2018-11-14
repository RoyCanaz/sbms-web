/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service;

import com.sbms.domain.Category;
import com.sbms.domain.Client;
import com.sbms.domain.Company;
import com.sbms.domain.Product;
import java.util.List;

/**
 *
 * @author user
 */
public interface ProductService extends GenericService<Product> {
   // public List<Product> getByCategory(Category category);
    public List<Product> findByCompany(Company company);
     public List<Product> getByCategoryAndCompany(Category category, Company company);
    
}
