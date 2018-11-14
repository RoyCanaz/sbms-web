/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Roy
 */
@Controller
@RequestMapping("/client/procurement")
public class ProcurementController extends BaseController{
 /*   @Resource
    private ProcurementService proService;
    @Resource
    private ClientService clientService;
    @Resource
    private BrandService brandService;
    @Resource
    private ProcurementValidator proValidator;
    
    private String setUpModel(ModelMap model, Procurement item) {   
        
         model.addAttribute("pageTitle", "Create/Edit Procurement");
         model.addAttribute("clickAddProcurement", true); 
         model.addAttribute("brands", brandService.getAll());
         model.addAttribute("item", item);           
         return "master";
      }
    
    @RequestMapping("/addProcurement")
    public String addProc(ModelMap model, @RequestParam Long id){
         Procurement p = new Procurement();
         Client c = clientService.get(id);
         
         model.addAttribute("pageTitle", "Create/Edit Procurement");
         model.addAttribute("clickAddProcurement", true); 
         System.err.println(brandService.getAll()+"&&&&&&&&&&&");
         model.addAttribute("brands", brandService.getAll());
         model.addAttribute("item", p);   
         model.addAttribute(c);
         return "master";
        
        // return setUpModel(model, proc);
     }
    @RequestMapping(value = "/addProcurement/form", method = RequestMethod.GET)
    public String editProc(ModelMap model, @RequestParam(required = false) Long id) {
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        Procurement c = new Procurement();
        if (id != null) {
            c = proService.get(id);
        }
        return setUpModel(model, c);
    }
    
    @RequestMapping(value = "/addProcurement/form", method = RequestMethod.POST)
    public String saveUsers(@ModelAttribute("item") @Valid Procurement proc, BindingResult result, ModelMap model){  
        proValidator.validate(proc, result);     
        if (result.hasErrors()) {  
       
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Data entry error has occurred").messageType(MessageType.ERROR).build());           
        return setUpModel(model, proc);  
        }
        
        proService.save(proc);       
        Procurement p = new Procurement();
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Procurement Added Successfully").messageType(MessageType.MESSAGE).build());           
        return setUpModel(model, p);   
   }
*/
}
