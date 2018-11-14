/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.repository;

import com.sbms.domain.Company;
import com.sbms.domain.EmailAccount;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user
 */
public interface EmailAccountRepo extends JpaRepository<EmailAccount, Long>{
 //   List<EmailAccount> findByActiveAndEmailType(Boolean active, String emailType);
    public List<EmailAccount> findByActiveAndCompany(Boolean active, Company company);
      public List<EmailAccount> findByDeletedAndCompany(Boolean active, Company company);
}
