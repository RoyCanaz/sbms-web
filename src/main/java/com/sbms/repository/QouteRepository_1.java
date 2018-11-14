/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.repository;

import com.sbms.domain.Company;
import com.sbms.domain.Qoute;
import com.sbms.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Roy
 */
public interface QouteRepository_1 extends JpaRepository<Qoute, Long>{
    @Query(value="Select Max(id) From qoute", nativeQuery = true)
    Long getMaxId();
    
    Long countByClientId(Long clientId);
    
    List<Qoute> findByClientId(Long id);
    List<Qoute> findByCompany(Company company);
    List<Qoute> findByCompanyAndCreatedBy(Company company, User user);
}
