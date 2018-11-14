/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.controller;

import com.sbms.domain.Client;
import com.sbms.domain.RequiredDocuments;
import com.sbms.service.ClientService;
import com.sbms.service.RequiredDocumentsService;
import com.sbms.utilities.AppMessage;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/client/requiredDocuments")
public class RequiredDocumentsController {
    @Resource
    private RequiredDocumentsService documentsService;
    @Resource
    private ClientService clientService;
    
    
     private String setUpModel(ModelMap model, RequiredDocuments item) {
         
         model.addAttribute("pageTitle", "Create/Edit Required Documents");
         model.addAttribute("item", item);
       
         model.addAttribute("clickAddRequiredDocs", true);               
         return "master";
      }
     
     @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String editProc(@RequestParam (required = false) Long id,  ModelMap model) {  
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        RequiredDocuments rd = new RequiredDocuments();
        model.addAttribute("ci", id);          
        return setUpModel(model, rd);
    }   
     @RequestMapping(value = "/form", method = RequestMethod.POST)
      public String saveRequireddocs(@ModelAttribute("item") RequiredDocuments item, ModelMap model){  
        RequiredDocuments rd = documentsService.findByClient(item.getCi());
        Client client = clientService.get(item.getCi());
        if(rd==null){                  
            item.setClient(client);
            documentsService.save(item);         
        }
        return "redirect:/client/contact/addContact?id="+client.getId();  
   }
    
}
