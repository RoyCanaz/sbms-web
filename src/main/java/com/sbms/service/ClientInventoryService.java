/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service;

import com.sbms.domain.ClientInventory;
import java.util.List;

/**
 *
 * @author user
 */
public interface ClientInventoryService extends GenericService<ClientInventory> {
    List<ClientInventory> findByClientId(Long id);
    
}
