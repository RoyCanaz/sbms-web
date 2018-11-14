/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.repository;

import com.sbms.domain.Company;
import com.sbms.domain.CompanyEmail;
import com.sbms.domain.EmailAccount;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user
 */
public interface CompanyEmailRepo extends JpaRepository<CompanyEmail, Long>{
    List<CompanyEmail> findByDeletedAndCompany(Boolean deleted, Company company);
    CompanyEmail findByActiveAndCompany(Boolean active, Company company);
     List<CompanyEmail> findByDeletedAndGlobalEmail(Boolean deleted, Boolean globalEmail);
     List<CompanyEmail> findByActiveAndDeletedAndGlobalEmail(Boolean active, Boolean deleted, Boolean globalEmail);
     CompanyEmail findByActiveAndGlobalEmail(Boolean active, Boolean globalEmail);
     
}
