/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.repository;

import com.sbms.domain.BankingDetail;
import com.sbms.domain.Company;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user
 */
public interface BankingDetailRepo extends JpaRepository<BankingDetail, Long> {
      public List<BankingDetail> findByCompany(Company company);
      BankingDetail findByActiveAndCompany(Boolean active, Company company);
}
