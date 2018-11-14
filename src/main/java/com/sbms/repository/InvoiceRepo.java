/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.repository;

import com.sbms.domain.Invoice;
import com.sbms.domain.Qoute;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author user
 */
public interface InvoiceRepo extends JpaRepository<Invoice, Long> {
     @Query(value="Select Max(id) From invoice", nativeQuery = true)
      Long getMaxId();
     
}
