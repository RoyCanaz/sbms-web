/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service.impl;

import com.sbms.domain.BankingDetail;
import com.sbms.domain.Company;
import com.sbms.domain.Currency;
import com.sbms.repository.BankingDetailRepo;
import com.sbms.service.BankingDetailService;
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
public class BankingDetailServiceImpl implements BankingDetailService{
    @Resource
    private UserService userService;
    @Resource
    private BankingDetailRepo bankingDetailRepo;

    @Override
    public List<BankingDetail> getAll() {
        return bankingDetailRepo.findAll();
    }

    @Override
    public BankingDetail get(Long id) {
       // return bankingDetailRepo.getOne(id);
        Optional<BankingDetail> optional = bankingDetailRepo.findById(id);        
        return optional.get();
    }

    @Override
    public void delete(BankingDetail t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BankingDetail> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BankingDetail save(BankingDetail t) {
         if (t.getId()==null || t.getId()==0) {
              if(t.getCreatedBy()==null){
                     t.setCreatedBy(userService.getCurrentUser());
                     t.setDateCreated(new Date());
                     return bankingDetailRepo.save(t);
            }          
            
            t.setDateCreated(new Date());
            return bankingDetailRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return bankingDetailRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(BankingDetail current, BankingDetail old) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BankingDetail> getCreatedBy(Long createdBy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BankingDetail> findByCompany(Company company) {
        return bankingDetailRepo.findByCompany(company);
    }

    @Override
    public BankingDetail findByActiveAndCompany(Boolean active, Company company) {
        return bankingDetailRepo.findByActiveAndCompany(active, company);
    }
}
