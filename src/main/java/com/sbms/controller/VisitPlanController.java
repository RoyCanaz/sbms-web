/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.controller;

import com.sbms.domain.Client;
import com.sbms.domain.Contact;
import com.sbms.domain.VisitPlan;
import com.sbms.service.ClientService;
import com.sbms.service.UserService;
import com.sbms.service.VisitPlanService;
import com.sbms.utilities.AppMessage;
import com.sbms.utilities.DateUtil;
import com.sbms.utilities.MessageType;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("visitplan")
public class VisitPlanController {
    @Resource
    private VisitPlanService planService;
     @Resource
    private UserService userService;
     @Resource
     private ClientService clientService;
    
    private String setUpModel(ModelMap model, VisitPlan item) {
         
         model.addAttribute("pageTitle", "Add/View Visit Plans");
         model.addAttribute("visitForm", item);  
         model.addAttribute("upActive", "active");
         return "users/user-master";
      }
     @GetMapping("/list")
     public String listVisits(ModelMap model){
         VisitPlan plan =  new VisitPlan();
         model.addAttribute("clickVisitPlans", true); 
         model.addAttribute("list", "active");
         model.addAttribute("showActiveList", "in show active");
         
         List <VisitPlan> visitPlans = planService.getCreatedBy(userService.getCurrentUser().getId());
         model.addAttribute("clients", clientService.getCreatedBy(userService.getCurrentUser().getId()));
         
         model.addAttribute("visitPlans", visitPlans);
         return setUpModel(model, plan);
     }
     @GetMapping("/new")
     public String newVisit(ModelMap model){
         VisitPlan plan =  new VisitPlan();
         model.addAttribute("clickVisitPlans", true); 
         model.addAttribute("add", "active");
         model.addAttribute("showActiveAdd", "in show active");
         
         List <VisitPlan> visitPlans = planService.getCreatedBy(userService.getCurrentUser().getId());
         model.addAttribute("clients", clientService.getCreatedBy(userService.getCurrentUser().getId()));
         
         model.addAttribute("visitPlans", visitPlans);
         return setUpModel(model, plan);
     }
     @GetMapping("/add/{id}")
     public String addVisits(ModelMap model, @PathVariable(name="id", required = true) Long id){
         VisitPlan plan =  new VisitPlan();
         if(id!=null){
             plan = planService.get(id);
         }
         
         model.addAttribute("clickVisitPlans", true); 
         model.addAttribute("add", "active");
         model.addAttribute("showActiveAdd", "in show active");
         model.addAttribute("clients", clientService.getCreatedBy(userService.getCurrentUser().getId()));
         model.addAttribute("visitPlans", planService.getCreatedBy(userService.getCurrentUser().getId()));
         return setUpModel(model, plan);             
     }
     @PostMapping("/add")
     public String addVisit(ModelMap model, @ModelAttribute("visitForm") VisitPlan plan) throws ParseException{
        
         Date dateOfVisit = DateUtil.formatStringToDate(plan.getDateVisit());
         
         
          if(plan.getId()!=null){
            VisitPlan visitPlan = planService.get(plan.getId());
            visitPlan.setClient(plan.getClient());
            visitPlan.setDateOfVisit(dateOfVisit);
            planService.save(visitPlan);
             return "redirect:/visitplan/list";
        }
        
         plan.setDateOfVisit(dateOfVisit);
         plan.setStatus("0");
          planService.save(plan);
          return "redirect:/visitplan/list";          
     }
     @GetMapping("/callvisit/{id}") 
        String callSummary(ModelMap model, @PathVariable(name="id", required = true) Long id){
            VisitPlan visitPlan = planService.get(id);
           model.addAttribute("pageTitle", "Call/Visit Summary");
           model.addAttribute("clickVisitSummary", true); 
             model.addAttribute("visitForm", visitPlan);
             model.addAttribute("upActive", "active");
           return "users/user-master";
     }
     @PostMapping("/summary")
     public String saveCallSummary(ModelMap model, @ModelAttribute("visitForm") VisitPlan plan, RedirectAttributes redir){
         redir.addFlashAttribute("message", new AppMessage.MessageBuilder().build());
          if(plan.getId()!=null){
            VisitPlan visitPlan = planService.get(plan.getId());
            visitPlan.setVisitResult(plan.getVisitResult());
            visitPlan.setStatus("1");
            visitPlan.setDateCalledVisited(new Date());
            planService.save(visitPlan);
     
            redir.addFlashAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Summary Saved.").messageType(MessageType.MESSAGE).build()); 
             return "redirect:/visitplan/list";
        }
       redir.addFlashAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Error Encounted. Summary Not Saved.").messageType(MessageType.ERROR).build()); 
       return "redirect:/visitplan/list";    
     }
     @GetMapping("/client-plans/{clientId}")
     public String getClientVisitPlans(ModelMap model, @PathVariable("clientId") Long clientId)
     {
         
         Client client = clientService.get(clientId);
         List<VisitPlan> visitPlans = planService.findByClient(client);
         model.addAttribute("clientVisitPlans", visitPlans);
         model.addAttribute("clientName",client.getName());
         model.addAttribute("clickClientVisitPlansList", true);
         return "visitPlans/visit-master";
     }
}
