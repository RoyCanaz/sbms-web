/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service.impl;

import com.sbms.domain.Company;
import com.sbms.domain.Currency;
import com.sbms.repository.CurrencyRepo;
import com.sbms.service.CurrencyService;
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
public class CurrencyServiceImpl implements CurrencyService{
    @Resource
    private CurrencyRepo currencyRepo;
    @Resource
    private UserService userService;

    @Override
    public List<Currency> getAll() {
        return currencyRepo.findAll();
    }

    @Override
    public Currency get(Long id) {
       // return currencyRepo.getOne(id);
        Optional<Currency> optional = currencyRepo.findById(id);        
        return optional.get();
    }

    @Override
    public void delete(Currency t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Currency> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override   
    @Transactional
    public Currency save(Currency t) {
         if (t.getId()==null) {
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return currencyRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return currencyRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(Currency current, Currency old) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Currency> getCreatedBy(Long createdBy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Currency> findByCompany(Company company) {
      return currencyRepo.findByDeletedAndCompany(Boolean.FALSE, company);
    }

    @Override
    public Currency getByActiveAndCompany(Boolean active, Company company) {
        return currencyRepo.findByActiveAndCompany(active, company);
    }

    
}
