/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service;

import com.sbms.domain.Company;
import com.sbms.domain.User;
import java.util.List;

/**
 *
 * @author user
 */
public interface CompanyService extends GenericService<Company>{
   // public Company  findByUser();
    public List<Company> getByUser();
    public List<Company> getByActiveAndUser();
    List<Company> getActiveCompanies(Boolean active);
    
}
