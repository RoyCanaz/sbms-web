/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service.impl;

import com.sbms.domain.Branch;
import com.sbms.repository.BranchRepository;
import com.sbms.service.BranchService;
import com.sbms.service.UserService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
public class BranchServiceImpl implements BranchService{
    @Resource
    private BranchRepository branchRepository;
    @Resource
    private UserService userService;

    @Override
    public List<Branch> getAll() {
        return branchRepository.findAll();
    }

    @Override
    public Branch get(Long id) {
        if (id == null) {
            throw new IllegalStateException("Item to be deleted does not exist:");
        }
        Optional<Branch> cat = branchRepository.findById(id);
        Branch c = cat.get();
        return c;
    }

    @Override
    public void delete(Branch t) {
         if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        branchRepository.save(t);
    }

    @Override
    public List<Branch> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Branch save(Branch t) {
         if (t.getId()==null || t.getId()==0) {
              if(t.getCreatedBy()==null){
                     t.setCreatedBy(userService.getCurrentUser());
                        t.setDateCreated(new Date());
                        return branchRepository.save(t);
            }          
            
            t.setDateCreated(new Date());
            return branchRepository.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return branchRepository.save(t);
    }

    @Override
    public Boolean checkDuplicate(Branch current, Branch old) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Branch> getCreatedBy(Long createdBy) {
       return branchRepository.findByCreatedById(createdBy);
    }
    
}
