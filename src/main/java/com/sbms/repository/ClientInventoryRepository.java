/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.repository;

import com.sbms.domain.ClientInventory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Roy
 */
public interface ClientInventoryRepository extends JpaRepository<ClientInventory, Long>{
    public List<ClientInventory> findByClientId(Long id);
    List<ClientInventory> findByCreatedById(Long id);
   
}
