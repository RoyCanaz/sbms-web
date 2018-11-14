/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service.impl;

import com.sbms.domain.Addon;
import com.sbms.repository.AddonRepo;
import com.sbms.service.AddonService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Roy
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Repository
public class AddonServiceImpl implements AddonService{
    @Resource
    private AddonRepo addonRepo;
    
    
    @Override
    @Transactional
    public List<Addon> getAll() {
        return addonRepo.findAll();
    }

    @Override
    public Addon get(Long id) {
        return addonRepo.getOne(id);
    }

    @Override
    public void delete(Addon t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Addon> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Addon save(Addon t) {    
        return addonRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(Addon current, Addon old) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Addon> getCreatedBy(Long createdBy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
