/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.repository;

import com.sbms.domain.Company;
import com.sbms.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user
 */
public interface CompanyRepo extends JpaRepository<Company, Long>{
  //  public Company findByCreatedBy(User createdBy);
    public List<Company> findByCreatedBy(User createdBy);
    public List<Company> findByActive(Boolean active);
    public List<Company> findByActiveAndCreatedBy(Boolean active, User createdBy);
}
