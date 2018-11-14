/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.restmobile;

import com.sbms.domain.Client;
import com.sbms.domain.RequiredDocuments;
import com.sbms.domain.User;
import com.sbms.domain.VisitPlan;
import com.sbms.dto.NotesDTO;
import com.sbms.dto.ProcurementDocsDTO;
import com.sbms.dto.VisitPlanDTO;
import com.sbms.service.ClientService;
import com.sbms.service.UserService;
import com.sbms.service.VisitPlanService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/rest/client")
public class VisitPlanRestController {
    @Resource
    private UserService userService;
    @Resource
    private VisitPlanService visitPlanService;
    @Resource
    private ClientService clientService;
    
    @RequestMapping(value = "/visitPlan", method = RequestMethod.POST)
    public ResponseEntity<VisitPlanDTO> saveVisitPlan(@RequestBody VisitPlanDTO visitPlanDTO){
        if(visitPlanDTO.getRealId()!=0){
            Client c = clientService.get(visitPlanDTO.getClientId());
            VisitPlan plan = visitPlanService.get(visitPlanDTO.getRealId());
            plan.setDateOfVisit(visitPlanDTO.getDateOfVisit());
            plan.setStatus(visitPlanDTO.getStatus());
            plan.setVisitResult(visitPlanDTO.getVisitResult());
            plan.setClient(c);
            visitPlanService.save(plan);
            VisitPlanDTO planDTO = new VisitPlanDTO(plan.getId(), plan.getStatus(), plan.getVisitResult(), plan.getDateOfVisit(),
                    visitPlanDTO.getClientId(), visitPlanDTO.getCreatedBy());
            return new ResponseEntity<>(planDTO, HttpStatus.OK);  
        }
        else{
        
         User user = userService.get(visitPlanDTO.getCreatedBy());
         Client client = clientService.get(visitPlanDTO.getClientId());
         visitPlanDTO.setUser(user);
         visitPlanDTO.setClient(client);
         VisitPlan plan = visitPlanService.save(visitPlanDTO.getInstance(visitPlanDTO));
         
         VisitPlanDTO planDTO = new VisitPlanDTO(plan.getId(), plan.getStatus(), plan.getVisitResult(), plan.getDateOfVisit(), visitPlanDTO.getClientId(), visitPlanDTO.getCreatedBy());
         
        return new ResponseEntity<>(planDTO, HttpStatus.OK);  
        } 
    }
     @RequestMapping(value = "/getVisitPlan/{createdBy}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VisitPlanDTO> listContacts(@PathVariable("createdBy") Long createdBy) {         
           List<VisitPlan> list = visitPlanService.getCreatedBy(createdBy);
           List<VisitPlanDTO> planDTOs = new ArrayList<>();
           for(VisitPlan visitPlan : list){
               VisitPlanDTO dTO = new VisitPlanDTO(visitPlan.getId(), visitPlan.getStatus(), visitPlan.getVisitResult(),
                       visitPlan.getDateOfVisit(), visitPlan.getClient().getId(), createdBy);
               planDTOs.add(dTO);
               
           }    
          return planDTOs;         
    } 
}
