/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service;

import com.sbms.domain.Brand;
import java.util.List;

/**
 *
 * @author user
 */
public interface BrandService extends GenericNameService<Brand>{
    // List<Brand> getByCategoryId(Long id);
     List<Brand> getByActive(Boolean active);
}
