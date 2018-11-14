/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.repository;

import com.sbms.domain.Contact;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user
 */
public interface ContactRepository extends JpaRepository<Contact, Long>{
    public List<Contact> findByActiveAndClientId(Boolean active, Long id);
    List<Contact> findByActiveAndCreatedById(Boolean active, Long id);
}
