/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.repository;

import com.sbms.domain.Category;
import com.sbms.domain.Company;
import com.sbms.domain.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user
 */
public interface ProductRepo extends JpaRepository<Product, Long>{
    List<Product> findByCategory(Category category);
    public List<Product> findByDeletedAndCompany(Boolean deleted,Company company);
    List<Product> findByDeletedAndCategoryAndCompany(Boolean  deleted, Category category, Company company);
}
