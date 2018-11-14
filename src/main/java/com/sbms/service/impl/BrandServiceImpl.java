/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service.impl;

import com.sbms.domain.Brand;
import com.sbms.repository.BrandRepository;
import com.sbms.service.BrandService;
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
public class BrandServiceImpl implements BrandService{
    @Resource
    private BrandRepository brandRepository;
    @Resource
    private UserService userService;

    @Override
    public Brand getByName(String name) {
        if (name == null) {
            throw new IllegalStateException("Item to be read does not exist:");
        }
        return brandRepository.findByName(name);
    }

    
    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand get(Long id) {
          if (id == null) {
            throw new IllegalStateException("Item to be deleted does not exist:");
        }
          Optional<Brand> br = brandRepository.findById(id);
          Brand brand = br.get();
          return brand;
    }

    @Override
    public void delete(Brand t) {
          if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state:");
        }
        t.setActive(Boolean.FALSE);
        brandRepository.save(t);
    }

    @Override
    public List<Brand> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public Brand save(Brand t) {
        if (t.getId() == null) {
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return brandRepository.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return brandRepository.save(t);
    }

    @Override
    public Boolean checkDuplicate(Brand current, Brand old) {
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
    public List<Brand> getCreatedBy(Long createdBy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 /*   @Override
    public List<Brand> getByCategoryId(Long id) {
        return brandRepository.findByCategoryId(id);
    }
*/
    @Override
    public List<Brand> getByActive(Boolean active) {
        return brandRepository.findByActive(active);
    }
}
