/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service.impl;

import com.sbms.domain.Category;
import com.sbms.domain.Company;
import com.sbms.repository.CategoryRepository;
import com.sbms.service.CategoryService;
import com.sbms.service.UserService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
public class CategoryServiceImpl implements CategoryService{
   
    @Resource
    private CategoryRepository categoryRepository;
    @Resource
    private UserService userService;

    @Override
    public Category getByName(String name) {
        if (name == null) {
            throw new IllegalStateException("Item to be read does not exist:");
        }
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAll() {
       // return categoryRepository.findAll();
       return categoryRepository.findAll();
    }

    @Override
    public Category get(Long id) {
      /* if (id == null) {
           throw new IllegalStateException("Item to be read does not exist:");
       }
        Optional<Category> cat = categoryRepository.findById(id);
        if(cat==null){
            return null;
        }
        Category c = cat.get();       
        return c;
        */
      if (id == null) {
           throw new IllegalStateException("Item to be read does not exist:");
       }
      return categoryRepository.getOne(id);
    }

    @Override
    public void delete(Category t) {
         if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        categoryRepository.save(t);
    }

    @Override
    public List<Category> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public Category save(Category t) {
        if (t.getId()==null) {
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return categoryRepository.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return categoryRepository.save(t);
    }

    @Override
    public Boolean checkDuplicate(Category current, Category old) {
         if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!current.getName().equals(old.getName())) {
                if (getByName(current.getName()) != null) {
                    return true;
                }
            }

        } else if (current.getId() == null) {
            /**
             * @param current is new
             */
            if (getByName(current.getName()) != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Category> getCreatedBy(Long createdBy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public List<Category> findByActiveAndCompany(Boolean active, Company company) {
        return categoryRepository.findByActiveAndCompany(active, company);
    }

    @Override
    public List<Category> findByActive(Boolean active) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
