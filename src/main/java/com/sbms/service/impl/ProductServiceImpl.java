/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service.impl;

import com.sbms.domain.Category;
import com.sbms.domain.Company;
import com.sbms.domain.Product;
import com.sbms.repository.ProductRepo;
import com.sbms.service.ProductService;
import com.sbms.service.UserService;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Repository
public class ProductServiceImpl implements ProductService{

    @Resource
    private ProductRepo productRepo;
    @Resource
    private UserService userService;
    @Override
    public List<Product> getAll() {
        return productRepo.findAll();
    }

    @Override
    public Product get(Long id) {
        return productRepo.getOne(id);
    }

    @Override
    public void delete(Product t) {
         if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        t.setDeleted(Boolean.TRUE);
        productRepo.save(t);
    }

    @Override
    public List<Product> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public Product save(Product t) {
          if (t.getId()==null || t.getId()==0) {
              if(t.getCreatedBy()==null){
                     t.setCreatedBy(userService.getCurrentUser());
                     t.setDateCreated(new Date());
                     return productRepo.save(t);
            }          
            
            t.setDateCreated(new Date());
            return productRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return productRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(Product current, Product old) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> getCreatedBy(Long createdBy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
/*
    @Override
    public List<Product> getByCategory(Category category) {
        return productRepo.findByCategory(category);
    }
*/
    @Override
    public List<Product> findByCompany(Company company) {
        return productRepo.findByDeletedAndCompany(Boolean.FALSE, company);
    }

    @Override
    public List<Product> getByCategoryAndCompany(Category category, Company company) {
        return productRepo.findByDeletedAndCategoryAndCompany(Boolean.FALSE, category, company);
    }
    
}
