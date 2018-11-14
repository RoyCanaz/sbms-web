/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service;

import com.sbms.domain.Category;
import com.sbms.domain.Client;
import com.sbms.domain.Company;
import java.util.List;

/**
 *
 * @author user
 */
public interface CategoryService extends GenericNameService<Category>{
    List<Category> findByActiveAndCompany(Boolean active, Company company);
    List<Category> findByActive(Boolean active);
}
