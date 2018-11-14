/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.controller;

import com.sbms.domain.Company;
import com.sbms.domain.CompanyEmail;
import com.sbms.domain.Currency;
import com.sbms.domain.EmailAccount;
import com.sbms.domain.EmailType;
import com.sbms.repository.EmailTypeRepo;
import com.sbms.service.CompanyEmailService;
import com.sbms.service.EmailAccountService;
import com.sbms.service.UserService;
import com.sbms.utilities.AppMessage;
import com.sbms.utilities.MessageType;
import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
@RequestMapping("/admin/email")
public class EmailAccountController {
    
    @Resource
    private UserService userService;
    @Resource
    private EmailAccountService emailAccountService;
    @Resource
    private CompanyEmailService companyEmailService;
    @Autowired
    private EmailTypeRepo emailTypeRepo;
    
    
     private String setUpModel(ModelMap model, EmailAccount item, Company company) {
        model.addAttribute("pageTitle", "Emails");
        model.addAttribute("emailTypes", emailTypeRepo.findAll());
        model.addAttribute("emailAccount", item);
        model.addAttribute("emails", emailAccountService.findByCompany(company));
        model.addAttribute("clickEmailSettings", true);
        model.addAttribute("apActive", "active");
        return "settings-master";
    }
  @RequestMapping(value = {"/list"}, method = RequestMethod.GET) 
  public String settingsPage(ModelMap model, HttpSession session){
      Company company = (Company) session.getAttribute("company");
      
        EmailAccount account = new EmailAccount();        
        return setUpModel(model, account, company);
  }
  
        @GetMapping("/edit") 
        public String editHome(ModelMap model, @RequestParam(required = false) Long id){
             EmailAccount emailAccount = new EmailAccount();
               if(id!=null){
                   emailAccount = emailAccountService.get(id);
                   model.addAttribute("typeId", id);
               }
                    model.addAttribute("pageTitle", "Cc/Bcc Email Accounts");
                    model.addAttribute("clickEditCcEmails", true);
                    model.addAttribute("emailAccount", emailAccount);   
                    model.addAttribute("emailTypes", emailTypeRepo.findAll());
                     model.addAttribute("apActive", "active");
                    return "admin-super";
        }
  
  @RequestMapping(value = {"/add"}, method = RequestMethod.POST) 
  public String addEmails(@ModelAttribute("emailAccount")EmailAccount account, ModelMap model, HttpSession session ){
      Company company = (Company) session.getAttribute("company");
    
      if(account.getId()!=null){
         EmailAccount e = emailAccountService.get(account.getId());
          account.setDateCreated(e.getDateCreated());
          emailAccountService.save(account);
          EmailAccount emailAccount = new EmailAccount();
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Email Created Successfully").messageType(MessageType.MESSAGE).build());           
        return setUpModel(model, emailAccount, company);
      }
        account.setCompany(company);
        emailAccountService.save(account);
        EmailAccount emailAccount = new EmailAccount();
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Email Created Successfully").messageType(MessageType.MESSAGE).build());           
        return setUpModel(model, emailAccount, company);
  }
  
  
       @GetMapping("/default/form") 
        public String adminHome(ModelMap model, @RequestParam(required = false) Long id){
             CompanyEmail companyEmail =  new CompanyEmail();
               if(id!=null){
                   companyEmail = companyEmailService.get(id);
                   model.addAttribute("emailId", id);
               }
              model.addAttribute("pageTitle", "Emails");
              model.addAttribute("clickAddCompanyEmail", true);
              model.addAttribute("item", companyEmail);
               model.addAttribute("apActive", "active");
                  return "admin-super";
        }
        @GetMapping("/default/list")
        public String listEmails(ModelMap model, HttpSession session){
             Company company = (Company) session.getAttribute("company");
              model.addAttribute("pageTitle", "Email List");
              model.addAttribute("clickListCompanyEmails", true);
              model.addAttribute("companyEmails", companyEmailService.findByCompany(company));
               model.addAttribute("apActive", "active");
              return "admin-super";
        }
             @GetMapping("/delete-acc/{id}")
          public String deleteBccCc(ModelMap model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes){
                EmailAccount  emailAccount = emailAccountService.get(id);
                emailAccountService.delete(emailAccount);
               redirectAttributes.addFlashAttribute("message", new AppMessage.MessageBuilder().build());
               redirectAttributes.addFlashAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Delete  Successfull").messageType(MessageType.MESSAGE).build());
                return "redirect:/admin/email/list";
          }
          @GetMapping("/delete-email/{id}")
          public String deleteCompanyEmail(ModelMap model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes){
                CompanyEmail  companyEmail = companyEmailService.get(id);
                companyEmailService.delete(companyEmail);
               redirectAttributes.addFlashAttribute("message", new AppMessage.MessageBuilder().build());
               redirectAttributes.addFlashAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Delete  Successfull").messageType(MessageType.MESSAGE).build());
               return "redirect:/admin/email/default/list";
          }
             
         @PostMapping("/default")
         public String saveDefault(@ModelAttribute("item") CompanyEmail companyEmail, ModelMap model, HttpSession session){ 
             companyEmail.setActive(Boolean.FALSE);
             if(companyEmail.getId()!=null){
                 CompanyEmail ce = companyEmailService.get(companyEmail.getId());
                 companyEmail.setDateCreated(ce.getDateCreated());
                 companyEmail.setCompany(ce.getCompany());
                  companyEmail.setActive(ce.getActive());
                 if(companyEmail.getPassword()==null || companyEmail.getPassword().isEmpty()){
                     companyEmail.setPassword(ce.getPassword());
                 }
                 
             }
             else{
                Company company = (Company) session.getAttribute("company"); 
                companyEmail.setCompany(company);
             }
             
              
              companyEmailService.save(companyEmail);
              model.addAttribute("clickAddCurrency", true);
              model.addAttribute("message", new AppMessage.MessageBuilder().build());
              model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Email Added Successfully").messageType(MessageType.MESSAGE).build());           
              return "redirect:/admin/email/default/list";
         }
         
         @GetMapping("/enable/{id}")
          public String enableDefaultEmail(ModelMap model, @PathVariable Long id, HttpSession session){
              Company company = (Company) session.getAttribute("company");
                CompanyEmail e = companyEmailService.get(id);
            
                    e.setActive(Boolean.TRUE);
                    companyEmailService.save(e);
                    List<CompanyEmail> cs = companyEmailService.findByCompany(company);
              for(CompanyEmail c : cs){
                  if(!Objects.equals(c.getId(), e.getId())){
                      c.setActive(Boolean.FALSE);
                      companyEmailService.save(c);
                  }
              }
                    return "redirect:/admin/email/default/list";
          }   
    
            @GetMapping("/disable/{id}")
          public String disableDefaultEmail(ModelMap model, @PathVariable Long id){
                CompanyEmail e = companyEmailService.get(id);
                e.setActive(Boolean.FALSE);
                companyEmailService.save(e);
                return "redirect:/admin/email/default/list";
          } 
  
           @GetMapping("/disable/type/{id}")
          public String disableCcBcc(ModelMap model, @PathVariable(value = "id", required = true) Long id, RedirectAttributes ra){
              
              EmailAccount e = emailAccountService.get(id);
            
              e.setActive(Boolean.FALSE);
              emailAccountService.save(e);        
              ra.addFlashAttribute("message", new AppMessage.MessageBuilder().build());
              ra.addFlashAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Disable Successfull").messageType(MessageType.MESSAGE).build());
            //  redirectAttributes.addAttribute(model);
              return "redirect:/admin/email/list";
          } 
          @GetMapping("/enable/type/{id}")
          public String enableCcBcc(ModelMap model, @PathVariable(value = "id", required = true) Long id, RedirectAttributes ra){
              
              EmailAccount e = emailAccountService.get(id);
            
              e.setActive(Boolean.TRUE);
              emailAccountService.save(e);        
              ra.addFlashAttribute("message", new AppMessage.MessageBuilder().build());
              ra.addFlashAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Enable Successfull").messageType(MessageType.MESSAGE).build());
           
              return "redirect:/admin/email/list";
          } 
    
}
