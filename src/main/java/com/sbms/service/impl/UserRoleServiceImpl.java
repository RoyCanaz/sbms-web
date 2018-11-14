/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service.impl;

import com.sbms.domain.UserRole;
import com.sbms.repository.UserRoleRepository;
import com.sbms.service.UserRoleService;
import com.sbms.service.UserService;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Repository
public class UserRoleServiceImpl implements UserRoleService{
    
    @Resource
    private UserRoleRepository userRoleRepo;
    @Resource
    private UserService userService;
    
    @Override
    public UserRole getByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserRole> getAll() {
        return userRoleRepo.findAll();
    }

    @Override
    public UserRole get(Long id) {
       return null;
    }

    @Override
    public void delete(UserRole t) {
       userRoleRepo.delete(t);
    }

    @Override
    public List<UserRole> getPageable() {
        return null;
    }

    @Override
    public UserRole save(UserRole t) {
        t.setDateCreated(new Date());
        return userRoleRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(UserRole current, UserRole old) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserRole> getCreatedBy(Long createdBy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
