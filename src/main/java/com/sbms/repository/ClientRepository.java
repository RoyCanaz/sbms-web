/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.repository;

import com.sbms.domain.Client;
import com.sbms.domain.Company;
import com.sbms.domain.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author user
 */
public interface ClientRepository extends CrudRepository<Client, Long>{
    @Override
    public List<Client> findAll();
    Client findByName(String name);
    List<Client> findByCreatedById(Long id);
    List<Client> findByActiveAndCompany(Boolean active, Company company);
    List<Client> findByActiveAndCreatedByAndCompany(Boolean active, User user, Company company);
   // @Query("from Client c where c.createdBy.company=:company")
   // List<Client> findByCompany(@Param("company")Company company);
    
}
