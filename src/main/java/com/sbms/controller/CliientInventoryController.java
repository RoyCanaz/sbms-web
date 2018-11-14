/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.controller;

import com.sbms.domain.ClientInventory;
import com.sbms.domain.Contact;
import com.sbms.service.BrandService;
import com.sbms.service.CategoryService;
import com.sbms.service.ClientInventoryService;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("client/inventory")
public class CliientInventoryController {
    @Resource
    private ClientInventoryService cis;
     @Resource
    private BrandService brand;
     @Resource
     private CategoryService categoryService;
    
     private String setUpModel(ModelMap model,  ClientInventory item) {
         
         model.addAttribute("pageTitle", "Create/Edit Client Inventory");
         model.addAttribute("inventoryForm", item);                    
         return "master";
      }
     @RequestMapping(value = "/addClientinventory", method = RequestMethod.GET)
     public String addContacts(ModelMap model, @RequestParam Long id){
         ClientInventory clientInventory =  new ClientInventory();
         model.addAttribute("clickClientInventory", true); 
         model.addAttribute("ci", id);
         model.addAttribute("categories", categoryService.getAll());
         model.addAttribute("inventories", cis.findByClientId(id));
         return setUpModel(model, clientInventory);
     }
    
}
