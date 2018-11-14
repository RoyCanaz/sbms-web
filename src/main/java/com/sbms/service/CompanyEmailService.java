/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service;

import com.sbms.domain.Company;
import com.sbms.domain.CompanyEmail;
import java.util.List;

/**
 *
 * @author user
 */
public interface CompanyEmailService extends GenericService<CompanyEmail> {
    List<CompanyEmail> findByCompany(Company company);
    CompanyEmail getByActiveAndCompany(Boolean active, Company company);
    List<CompanyEmail> getByDeletedAndGlobalEmail(Boolean deleted, Boolean globalEmail);
    List<CompanyEmail> getByActiveAndDeletedAndGlobalEmail(Boolean active, Boolean deleted, Boolean globalEmail);
    CompanyEmail getByActiveAndGlobalEmail(Boolean active, Boolean globalEmail);
}
