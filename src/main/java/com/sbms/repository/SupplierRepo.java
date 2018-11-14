/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.repository;

import com.sbms.domain.BankingDetail;
import com.sbms.domain.Company;
import com.sbms.domain.Supplier;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user
 */
public interface SupplierRepo extends JpaRepository<Supplier, Long>{
    
     public List<Supplier> findByCompany(Company company);
    
    //@Query("select s from Supplier s join Category c where c.id = :id")
  //  @Query("FROM Supplier s WHERE s.categories.id = :id")
   // List<Supplier> findAllByCategoryId(@Param("id") Long id);
}
