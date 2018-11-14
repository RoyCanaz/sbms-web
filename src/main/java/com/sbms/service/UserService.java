/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service;

import com.sbms.domain.Company;
import com.sbms.domain.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author user
 */
public interface UserService  extends GenericService<User>{
     public User findByUserName(String name);
     public List<User> findByCompany(Company company);
     public List<User> getByActiveAndUser();
     public Optional<User> findUserByResetToken(String resetToken);
      User findByCreatedById(Long id);
     
 //   public User findByUserName(String name, Boolean active);
    List<User> getActiveUsers(Boolean active);
    public String getCurrentUsername();

    public User getCurrentUser();
    
    public User changePassword(User t);
    
       
    public void delete();
}
