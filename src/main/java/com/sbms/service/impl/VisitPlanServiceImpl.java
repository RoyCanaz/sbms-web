/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service.impl;

import com.sbms.domain.Client;
import com.sbms.domain.VisitPlan;
import com.sbms.repository.VisitPlanRepository;
import com.sbms.service.UserService;
import com.sbms.service.VisitPlanService;
import java.util.Date;
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
public class VisitPlanServiceImpl implements VisitPlanService{
    
    @Resource
    private UserService userService;
    
    @Resource
    private VisitPlanRepository visitPlanRepository;

    @Override
    public List<VisitPlan> findByClientId(Long id) {
        return visitPlanRepository.findByClientId(id);
    }

    @Override
    public List<VisitPlan> getAll() {
        return visitPlanRepository.findAll();
    }

    @Override
    public VisitPlan get(Long id) {
        return visitPlanRepository.getOne(id);
    }

    @Override
    public void delete(VisitPlan t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VisitPlan> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VisitPlan save(VisitPlan t) {
        if (t.getId()==null || t.getId()==0) {
              if(t.getCreatedBy()==null){
                     t.setCreatedBy(userService.getCurrentUser());
                     t.setDateCreated(new Date());
                     return visitPlanRepository.save(t);
            }                     
            t.setDateCreated(new Date());
            return visitPlanRepository.save(t);
        }
        
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return visitPlanRepository.save(t);
    }

    @Override
    public Boolean checkDuplicate(VisitPlan current, VisitPlan old) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VisitPlan> getCreatedBy(Long createdBy) {
        return visitPlanRepository.findByCreatedById(createdBy);
    }

    @Override
    public List<VisitPlan> findByStatusAndCreatedBy(String status, Long id) {
      return visitPlanRepository.findFirst7ByStatusAndCreatedById(status, id);
    }

    @Override
    public List<VisitPlan> findByDateOfVisitAndCreatedBy(Date date, Long id) {
        return visitPlanRepository.findByDateOfVisitAndCreatedById(date, id);
    }

    @Override
    public List<VisitPlan> findByClient(Client client) {
        return visitPlanRepository.findByClient(client);
    }
    
}
