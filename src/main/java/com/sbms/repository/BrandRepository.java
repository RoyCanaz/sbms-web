/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.repository;

import com.sbms.domain.Brand;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author user
 */
public interface BrandRepository extends CrudRepository<Brand, Long>{
     Brand findByName(String name);
   //  List<Brand> findByCategoryId(Long id);
     List<Brand> findByActive(Boolean active);
     @Override
     List<Brand> findAll();
}
