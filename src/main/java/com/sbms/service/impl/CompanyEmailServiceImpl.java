/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service.impl;

import com.sbms.domain.Company;
import com.sbms.domain.CompanyEmail;
import com.sbms.repository.CompanyEmailRepo;
import com.sbms.service.CompanyEmailService;
import com.sbms.service.CompanyService;
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
public class CompanyEmailServiceImpl implements CompanyEmailService{
    @Resource
    private UserService userService;
    @Resource
    private CompanyEmailRepo repo;

    @Override
    public List<CompanyEmail> findByCompany(Company company) {
        return repo.findByDeletedAndCompany(Boolean.FALSE, company);
    }

    @Override
    public CompanyEmail getByActiveAndCompany(Boolean active, Company company) {
        return repo.findByActiveAndCompany(active, company);
    }

    @Override
    public List<CompanyEmail> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CompanyEmail get(Long id) {
        return repo.getOne(id);
    }

    @Override
    public void delete(CompanyEmail t) {
          if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state [Id Not Found]");
        }
        t.setActive(Boolean.FALSE);
        t.setDeleted(Boolean.TRUE);
        repo.save(t);
    }

    @Override
    public List<CompanyEmail> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CompanyEmail save(CompanyEmail t) {
        if (t.getId()==null) {
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return repo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return repo.save(t);
    }

    @Override
    public Boolean checkDuplicate(CompanyEmail current, CompanyEmail old) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CompanyEmail> getCreatedBy(Long createdBy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<CompanyEmail> getByDeletedAndGlobalEmail(Boolean deleted, Boolean globalEmail) {
        return repo.findByDeletedAndGlobalEmail(deleted, globalEmail);
    }

    @Override
    public List<CompanyEmail> getByActiveAndDeletedAndGlobalEmail(Boolean active, Boolean deleted, Boolean globalEmail) {
        return repo.findByActiveAndDeletedAndGlobalEmail(active, deleted, globalEmail);
    }

    @Override
    public CompanyEmail getByActiveAndGlobalEmail(Boolean active, Boolean globalEmail) {
        return repo.findByActiveAndGlobalEmail(active, globalEmail);
    }

   
}
