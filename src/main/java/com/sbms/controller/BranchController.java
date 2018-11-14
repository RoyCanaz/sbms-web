/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.controller;

import com.sbms.domain.Branch;
import com.sbms.domain.Client;
import com.sbms.service.BranchService;
import com.sbms.service.ClientService;
import com.sbms.utilities.AppMessage;
import com.sbms.utilities.MessageType;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/client/branch")
public class BranchController {
    
    
    @Resource
    private BranchService branchService;
    @Resource
    private ClientService clientService;
    
    
     private String setUpModel(ModelMap model, Branch item) {
         
         model.addAttribute("pageTitle", "Create/Edit Branch");
         model.addAttribute("item", item);
       
         model.addAttribute("clickAddBranch", true);               
         return "master";
      }
    @RequestMapping(value = "/addBranch/form", method = RequestMethod.GET)
    public String editProc(@ModelAttribute("client") Client client,  ModelMap model) {  
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        Branch b = new Branch();
        model.addAttribute("ci", client.getId());          
        return setUpModel(model, b);
    }   
     @RequestMapping(value = "/addBranch/form", method = RequestMethod.POST)
      public String saveUsers(@ModelAttribute("item") Branch item, ModelMap model, RedirectAttributes redirectAttrs){  
        
        Client client = clientService.get(item.getCi());
        item.setClient(client);
        branchService.save(item);         
        redirectAttrs.addAttribute(client); 
        return "redirect:/client/branch/addBranch/form";  
   }
    
}
