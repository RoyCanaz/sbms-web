/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service.impl;

import com.sbms.domain.Modules;
import com.sbms.repository.CompanyRepo;
import com.sbms.repository.ModuleRepo;
import com.sbms.service.ModuleService;
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
public class ModuleServiceImpl implements ModuleService{
    @Resource
    private ModuleRepo moduleRepo;
    @Resource
    private UserService userService; 

    @Override
    public List<Modules> getAll() {
        return moduleRepo.findAll();
    }

    @Override
    public Modules get(Long id) {
        return moduleRepo.getOne(id);
    }

    @Override
    public void delete(Modules t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Modules> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Modules save(Modules t) {
         if (t.getId()==null || t.getId()==0) {
              if(t.getCreatedBy()==null){
                     t.setCreatedBy(userService.getCurrentUser());
                     t.setDateCreated(new Date());
                     return moduleRepo.save(t);
            }          
            
            t.setDateCreated(new Date());
            return moduleRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return moduleRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(Modules current, Modules old) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Modules> getCreatedBy(Long createdBy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
