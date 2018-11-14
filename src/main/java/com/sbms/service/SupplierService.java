/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service;

import com.sbms.domain.Client;
import com.sbms.domain.Company;
import com.sbms.domain.Supplier;
import java.util.List;

/**
 *
 * @author user
 */
public interface SupplierService extends GenericService<Supplier>  {
  //  List<Supplier> getByCategoryId(Long id);
    List<Supplier> findByCompany(Company company);
}
