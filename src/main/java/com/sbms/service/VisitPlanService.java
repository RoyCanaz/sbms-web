/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service;

import com.sbms.domain.Client;
import com.sbms.domain.VisitPlan;
import java.util.Date;
import java.util.List;

/**
 *
 * @author user
 */
public interface VisitPlanService extends GenericService<VisitPlan>  {
    List<VisitPlan> findByClientId(Long id);
    List<VisitPlan> findByClient(Client client);
    List<VisitPlan> findByStatusAndCreatedBy(String status, Long id);
    List<VisitPlan> findByDateOfVisitAndCreatedBy(Date date, Long id);
    
}
