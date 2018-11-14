/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.restmobile;

import com.sbms.domain.Company;
import com.sbms.domain.Modules;
import com.sbms.service.CompanyService;
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
@RequestMapping("/rest")
public class CompanyRestController {
    @Resource
    private CompanyService companyService;
    
    @GetMapping("/gb/company/{companyId}")
    public ResponseEntity setAttribute(@PathVariable("companyId") Long companyId, HttpSession session){        
        Company c = companyService.get(companyId);
        session.setAttribute("company", c); 
        session.setAttribute("companyName", c.getCompanyName());
        for(Modules m : c.getModules()){                                                           
            if(m.getName().equals("Sales")){
                session.setAttribute("sales", true);
            }
        }
        session.setAttribute("sales", true);
        return new ResponseEntity(HttpStatus.OK);       
    } 
    
}
