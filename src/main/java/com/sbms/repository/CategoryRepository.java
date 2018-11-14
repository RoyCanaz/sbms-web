/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.repository;

import com.sbms.domain.Category;
import com.sbms.domain.Company;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ROY
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
   
    
   // @Override
   // public List<Category> findAll();

    Category findByName(String name);
    public List<Category> findByActiveAndCompany(Boolean active, Company company);
}
