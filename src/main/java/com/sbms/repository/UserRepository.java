/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.repository;

import com.sbms.domain.Company;
import com.sbms.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user
 */
public interface UserRepository extends JpaRepository<User, Long>{
    
    public List<User> findByActiveAndCompany(Boolean active, Company company);
    public List<User> findByCreatedBy(User user);
    public Optional<User> findByResetToken(String resetToken);
    
    public List<User> findByActiveAndCreatedBy(Boolean active, User createdBy);
    User findByUserName(String username);
    User findByCreatedById(Long id);
    List<User> findByActive(Boolean active);
    
}
