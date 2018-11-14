/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.restmobile;

import com.sbms.domain.Client;
import com.sbms.domain.RequiredDocuments;
import com.sbms.domain.User;
import com.sbms.dto.ProcurementDocsDTO;
import com.sbms.service.ClientService;
import com.sbms.service.RequiredDocumentsService;
import com.sbms.service.UserService;
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
public class ProcurementDocsRestController {
    @Resource
    private UserService userService;
    @Resource
    private RequiredDocumentsService documentsService;
    
    @Resource
    private ClientService clientService;
    
    @RequestMapping(value = "/procurementDocs", method = RequestMethod.POST)
    public ResponseEntity<Object> saveDocs(@RequestBody ProcurementDocsDTO dTO){
            Client client = clientService.get(dTO.getClientId());           
            RequiredDocuments r = documentsService.findByClient(dTO.getClientId());
            
            if(r==null){
                  User user = userService.get(dTO.getCreatedBy());
                   dTO.setUser(user);
                   dTO.setClient(client);
                    RequiredDocuments rd = documentsService.save(dTO.getInstance(dTO));            
                    ProcurementDocsDTO docsDTO = new ProcurementDocsDTO(rd.getId(), rd.getApplicationLetter(), rd.getCompanyProfile(),
                            rd.getCertOfIncorporation(), rd.getMou(), rd.getCrFourteen(), rd.getVat(), rd.getItf(), rd.getTradeLicense(), rd.getTraceableReference(), rd.getOther(), rd.getClient().getId(), dTO.getClientId());
                             return new ResponseEntity<>(docsDTO, HttpStatus.OK);
            }
           ProcurementDocsDTO docsDTO = new ProcurementDocsDTO(r.getId(), r.getApplicationLetter(), r.getCompanyProfile(),
                    r.getCertOfIncorporation(), r.getMou(), r.getCrFourteen(), r.getVat(), r.getItf(), r.getTradeLicense(), r.getTraceableReference(), r.getOther(), r.getClient().getId(), dTO.getClientId());
                     return new ResponseEntity<>(docsDTO, HttpStatus.OK);   
    }
    
     @RequestMapping(value = "/getProcurementDocs/{createdBy}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProcurementDocsDTO> listProcurementDocs(@PathVariable("createdBy") Long createdBy) {         
           List<RequiredDocuments> list = documentsService.getCreatedBy(createdBy);
           List<ProcurementDocsDTO> procDTO = new ArrayList<>();
           for(RequiredDocuments rd : list){
               ProcurementDocsDTO dTO = new ProcurementDocsDTO(rd.getId(), rd.getApplicationLetter(), rd.getCompanyProfile(),
                       rd.getCertOfIncorporation(), rd.getMou(), rd.getCrFourteen(), rd.getVat(), rd.getItf(), rd.getTradeLicense(),
                       rd.getTraceableReference(), rd.getOther(), rd.getClient().getId(), createdBy);
               procDTO.add(dTO);
              
           }    
          return procDTO;         
    } 
    
}
