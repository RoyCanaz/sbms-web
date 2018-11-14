/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.repository;

import com.sbms.domain.Addon;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Roy
 */
public interface AddonRepo  extends JpaRepository<Addon, Long> {
    
}
