/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.controller;

import com.sbms.domain.Company;
import com.sbms.domain.Supplier;
import com.sbms.service.CategoryService;
import com.sbms.service.SupplierService;
import com.sbms.utilities.AppMessage;
import com.sbms.utilities.MessageType;
import com.sbms.validator.SupplierValidator;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("admin/supplier")
public class SupplierController {
    
    @Resource
    private CategoryService categoryService;
    @Resource
    private SupplierService supplierService;
     @Resource
    private SupplierValidator supplierValidator;
    
     public String setUpModel(ModelMap model, Object item, Company company){
         model.addAttribute("item", item);
         model.addAttribute("clickAddSupplier", true); 
         model.addAttribute("categories", categoryService.findByActiveAndCompany(Boolean.TRUE, company));
         model.addAttribute("pageTitle", "Add/Edit Supplier");
          model.addAttribute("apActive", "active");
         return "page-master";
    }
     @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public String loadSupplier(ModelMap model, HttpSession session, @PathVariable(value = "id", required = false) Long id){
        Company company = (Company) session.getAttribute("company");
        Supplier supplier = new Supplier();
          if(id!=null){
              supplier = supplierService.get(id);
          }
        return setUpModel(model, supplier, company);
    }
     @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String liSupplier(ModelMap model, HttpSession session, @PathVariable(value = "id", required = false) Long id){
        Company company = (Company) session.getAttribute("company");
        Supplier supplier = new Supplier();
          if(id!=null){
              supplier = supplierService.get(id);
          }
        return setUpModel(model, supplier, company);
    }
    
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String saveSupplier(@ModelAttribute("item") Supplier supplier, BindingResult result, ModelMap model, HttpSession session){ 
        Company company = (Company) session.getAttribute("company");
        //supplierValidator.validate(supplier, result);     
      /*  if (result.hasErrors()) {   
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Data entry error has occurred").messageType(MessageType.ERROR).build());           
        return setUpModel(model, supplier, company);  
        }
      */  
        if(supplier.getId()!=null){
            Supplier s = supplierService.get(supplier.getId());
            supplier.setCompany(s.getCompany());
            supplierService.save(supplier); 
             model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Supplier Saved Successfully").messageType(MessageType.MESSAGE).build());           
        return setUpModel(model, s, company); 
        }
        
        supplier.setCompany(company);
        supplierService.save(supplier); 
        Supplier s = new Supplier();        
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("New Supplier Was Created Successfully").messageType(MessageType.MESSAGE).build());           
        return setUpModel(model, s, company);       
   }
        @GetMapping("/list")
        public String listSuppliers(ModelMap model, HttpSession session){
              Company company = (Company) session.getAttribute("company");
              model.addAttribute("pageTitle", "Suppliers");
              model.addAttribute("clickListSuppliers", true);
              model.addAttribute("suppliers", supplierService.findByCompany(company));
              model.addAttribute("apActive", "active");
              return "page-master";
        }
         
       
}
