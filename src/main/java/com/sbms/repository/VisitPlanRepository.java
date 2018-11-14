/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.repository;

import com.sbms.domain.Client;
import com.sbms.domain.Notes;
import com.sbms.domain.VisitPlan;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user
 */
public interface VisitPlanRepository extends JpaRepository<VisitPlan, Long>{
    List<VisitPlan> findByClientId(Long id);
     List<VisitPlan> findByClient(Client client);
    List<VisitPlan> findByCreatedById(Long id);
    List<VisitPlan> findFirst7ByStatusAndCreatedById(String status, Long id);
    List<VisitPlan> findByDateOfVisitAndCreatedById(Date date, Long id);
}
