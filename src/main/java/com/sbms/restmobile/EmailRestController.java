/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.restmobile;

import com.sbms.domain.Company;
import com.sbms.domain.CompanyEmail;
import com.sbms.domain.Currency;
import com.sbms.domain.EmailAccount;
import com.sbms.domain.Modules;
import com.sbms.service.CompanyEmailService;
import com.sbms.service.CompanyService;
import com.sbms.service.EmailAccountService;
import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/rest/admin")
public class EmailRestController {
    @Resource
    private CompanyEmailService companyEmailService;
     @Resource
    private EmailAccountService emailAccountService;
    
    
    @GetMapping("/email/default/enable/{emailId}")
    public ResponseEntity enableAccount(@PathVariable("emailId") Long emailId, HttpSession session){   
         Company company = (Company) session.getAttribute("company");
         CompanyEmail companyEmail = companyEmailService.get(emailId);
              companyEmail.setActive(Boolean.TRUE);
              companyEmailService.save(companyEmail);
                            
              List<CompanyEmail> companyEmails = companyEmailService.findByCompany(company);
              for(CompanyEmail ce : companyEmails){
                  if(!Objects.equals(ce.getId(), emailId)){
                      ce.setActive(Boolean.FALSE);
                       companyEmailService.save(ce);
                  }
              }
        
        return new ResponseEntity(HttpStatus.OK);       
    } 
    @GetMapping("/email/default/disable/{emailId}")
    public ResponseEntity disableAccount(@PathVariable("emailId") Long emailId){   
         CompanyEmail companyEmail = companyEmailService.get(emailId);
              companyEmail.setActive(Boolean.FALSE);
              companyEmailService.save(companyEmail);       
        return new ResponseEntity(HttpStatus.OK);       
    } 
    
    @GetMapping("/email/enable/{emailId}")
    public ResponseEntity enableCC(@PathVariable("id") Long id){   
        

        EmailAccount e = emailAccountService.get(id);
            
              e.setActive(Boolean.TRUE);
              emailAccountService.save(e);
                                   
        return new ResponseEntity(HttpStatus.OK);       
    } 
     @GetMapping("/email/disable/{emailId}")
    public ResponseEntity disableCC(@PathVariable("emailId") Long emailId){   
        EmailAccount e = emailAccountService.get(emailId);
              e.setActive(Boolean.FALSE);
              emailAccountService.save(e);
                                   
        return new ResponseEntity(HttpStatus.OK);       
    } 
    
}
