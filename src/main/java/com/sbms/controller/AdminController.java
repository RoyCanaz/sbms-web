/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.controller;

import com.sbms.domain.Company;
import com.sbms.domain.CompanyEmail;
import com.sbms.domain.GlobalEmail;
import com.sbms.domain.Modules;
import com.sbms.domain.User;
import com.sbms.domain.UserRole;
import com.sbms.emails.service.EmailService;
import com.sbms.service.CompanyEmailService;
import com.sbms.service.CompanyService;
import com.sbms.service.ModuleService;
import com.sbms.service.UserRoleService;
import com.sbms.service.UserService;
import com.sbms.utilities.AppMessage;
import com.sbms.utilities.MessageType;
import com.sbms.validator.UserValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/admin/gb")
public class AdminController {
    @Resource
    private ModuleService moduleService;
    @Resource
    private CompanyService companyService;
    @Resource
    private UserRoleService roleService;
    
    @Resource
    private UserValidator userValidator;
    @Resource
    private UserService userService;
     @Resource
    private CompanyEmailService companyEmailService;
     
    @Resource
    private EmailService  emailService;
    
     
    
     private String setUpModel(ModelMap model, Object item, String title) {
        model.addAttribute("pageTitle", title);
         model.addAttribute("item", item); 
        model.addAttribute("sapActive", "active");
        return "admin-master";
    }
    
     @GetMapping("/dashboard")
        public String adminHome(ModelMap model){
              model.addAttribute("pageTitle", "Admin");
              model.addAttribute("clickAdminDashboard", true);
              model.addAttribute("sapActive", "active");
              return "admin-master";
     } 
        @GetMapping("/addCompany")
        public String addCompany(ModelMap model, @RequestParam(required = false) Long id){
              Company company = new Company();  
              model.addAttribute("modules", moduleService.getAll());
              model.addAttribute("clickAddCompany", true);     
              if(id!=null){
                  company = companyService.get(id);
                 
              }
              return setUpModel(model, company, "Add Company");
        }
         @GetMapping("/addAdmin")
        public String addAdmin(ModelMap model, @RequestParam(required = false) Long id){
              User users =  new User(); 
              User user = userService.getCurrentUser();              
              if(id!=null){                 
                  users = userService.get(id);
                  model.addAttribute("userId", id);                
              } 
              if(user!=null){              
              List<UserRole> roless =  roleService.getAll(); 
              for(UserRole role : user.getUserRoles()){
                  if(role.getName().equals("GLOBAL")){
                      model.addAttribute("userRole", sanitizeGlobalRoles(roless));
                  }
                  else if(role.getName().equals("SUPER_ADMIN")){
                      model.addAttribute("userRole", sanitizeSuperAdminRoles(roless));
                      model.addAttribute("companies", companyService.getByActiveAndUser());
                  }                
                }
              }
              else{
                  model.addAttribute("userRole", roleService.getAll());
              }
                        
              model.addAttribute("clickAddAdmin", true);                
              return setUpModel(model, users, "Add Admin");
        }
  
        @GetMapping("/addModule")
        public String addModule(ModelMap model){
              Modules modules  =new Modules();
              model.addAttribute("clickAddModule", true);             
              return setUpModel(model, modules, "Add Module");
        }
        
       
         @GetMapping("/addRole")
        public String addRole(ModelMap model){
              UserRole userRole = new UserRole();
              model.addAttribute("clickAddRole", true);             
              return setUpModel(model, userRole, "Add Role");
        }
        
    
        
         @PostMapping("/addCompany/form")
         public String saveModule(@ModelAttribute("item") Company company, ModelMap model, HttpSession session, RedirectAttributes redirectAttributes){ 
              
             if(company.getId()!=null){
               Company com = companyService.get(company.getId());
               company.setDateCreated(com.getDateCreated());
             }
                   
               companyService.save(company);
               for(Modules m : company.getModules()){
                      if(m.getName().equals("Sales")){
                          session.setAttribute("sales", true);
                      }
                      else{
                          session.setAttribute("sales", false);
                      }
                  }
             User user = userService.getCurrentUser();                      
                      for(UserRole role : user.getUserRoles()){
                          if(role.getName().equals("SUPER_ADMIN")){
                              
                             session.setAttribute("companiez", companyService.getByActiveAndUser());  
                          }
                      }
              Company c = new Company();
             // model.addAttribute("clickListCompany", true);
             // model.addAttribute("companies", companyService.getAll());
              model.addAttribute("message", new AppMessage.MessageBuilder().build());
              model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Company Added Successfully").messageType(MessageType.MESSAGE).build());           
            //  return setUpModel(model, c, "Add Company");
          //  redirectAttributes.addAttribute(model);
             return "redirect:/admin/gb/list-company";
         }
        
         @PostMapping("/addModule/form")
         public String saveModule(@ModelAttribute("item") Modules modules, ModelMap model){ 
              moduleService.save(modules);
              Modules module = new Modules();
              model.addAttribute("clickAddModule", true);
              model.addAttribute("message", new AppMessage.MessageBuilder().build());
              model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Module Added Successfully").messageType(MessageType.MESSAGE).build());           
              return setUpModel(model, module, "Add Module");
         }
         @PostMapping("/addRole/form")
         public String saveRole(@ModelAttribute("item") UserRole userRole, ModelMap model){ 
              roleService.save(userRole);
              UserRole role = new UserRole();
              model.addAttribute("clickAddRole", true);
              model.addAttribute("message", new AppMessage.MessageBuilder().build());
              model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Module Added Successfully").messageType(MessageType.MESSAGE).build());           
              return setUpModel(model, role, "Add Role");
         }
         @PostMapping("/addUsers/form")
          public String saveUsers(@ModelAttribute("item") @Valid User user, BindingResult result, ModelMap model, RedirectAttributes redirectAttributes, HttpServletRequest request){  
            userValidator.validate(user, result);   
            User cu = userService.getCurrentUser();         
             List<UserRole> roless =  roleService.getAll(); 
             String roleType = "";
             if(cu!=null){
              for(UserRole role : cu.getUserRoles()){
                  if(role.getName().equals("GLOBAL")){
                      roleType = role.getName();
                      model.addAttribute("userRole", sanitizeGlobalRoles(roless));
                  }
                  else if(role.getName().equals("SUPER_ADMIN")){
                       roleType = role.getName();
                      model.addAttribute("userRole", sanitizeSuperAdminRoles(roless));
                      model.addAttribute("companies", companyService.getByActiveAndUser());
                  }
                  
              } 
             }
            if (result.hasErrors()) {    
                 if(user.getId()!=null){
                       model.addAttribute("userId", user.getId());
                    }
            model.addAttribute("clickAddAdmin", true); 
            model.addAttribute("message", new AppMessage.MessageBuilder().build());
            model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Data entry error has occurred").messageType(MessageType.ERROR).build());           
            return setUpModel(model, user, "Add Admin");  
            }
                if(user.getId()!=null && user.getPassword().isEmpty()){
                     User us = userService.get(user.getId());
                      user.setUserRoles(us.getUserRoles());
                     user.setDateCreated(us.getDateCreated());
                     user.setPassword(us.getPassword());
                     user.setConfirmPassword(us.getConfirmPassword());

                 }
                else if(user.getId()!=null && user.getPassword().length()>3){
                 User us = userService.get(user.getId());
                  user.setUserRoles(us.getUserRoles());
                 user.setDateCreated(us.getDateCreated());
                 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();    
                 String hashedPassword = encoder.encode(user.getPassword());
                     user.setPassword(hashedPassword); 
                }
               
        User u = userService.save(user); 
    //     if(user.getId()==null){//Send Email
                System.err.println("====================");
                System.err.println("Sending Email........");
                System.err.println("====================");
                String appUrl = request.getScheme() + "://" + request.getServerName();
                emailService.sendUserDetails(appUrl, roleType, u);
       // } 
       redirectAttributes.addFlashAttribute("message", new AppMessage.MessageBuilder().build());
       redirectAttributes.addFlashAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Saved Successfully").messageType(MessageType.MESSAGE).build());    
       redirectAttributes.addFlashAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Email Sent").messageType(MessageType.MESSAGE).build()); 
        return "redirect:/admin/gb/list-users";
       
        
   }
          
          @GetMapping("/users/deactivate")
          public String deleteAdmin(ModelMap model, @RequestParam(required = false) Long id, RedirectAttributes redirectAttributes){
              User user = userService.get(id);
              userService.delete(user);
              model.addAttribute("message", new AppMessage.MessageBuilder().build());
              model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Deactivate  Successfull").messageType(MessageType.MESSAGE).build());
            // redirectAttributes.addAttribute(model);
              return "redirect:/admin/gb/list-users";
              
          }
           @GetMapping("/company/deactivate")
          public String deactivateCom(ModelMap model, @RequestParam(required = false) Long id, RedirectAttributes redirectAttributes){             
              Company company = companyService.get(id);
              companyService.delete(company);
              model.addAttribute("message", new AppMessage.MessageBuilder().build());
              model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Deactivate  Successfull").messageType(MessageType.MESSAGE).build());
            // redirectAttributes.addAttribute(model);
              return "redirect:/admin/gb/list-company";              
          }

          
          
        @GetMapping("/list-company")
        public String listCompany(ModelMap model){
            User user =  userService.getCurrentUser();
              for(UserRole userRole : user.getUserRoles()){
                    if(userRole.getName().equals("SUPER_ADMIN")){
                        
                         model.addAttribute("companies", companyService.getByActiveAndUser());
                    }
                    else{
                         model.addAttribute("companies", companyService.getActiveCompanies(Boolean.TRUE));
                    }
              }
              model.addAttribute("pageTitle", "Companies");
              model.addAttribute("clickListCompany", true);
              model.addAttribute("sapActive", "active");
              return "admin-master";
        }
        @GetMapping("/list-module")
        public String listModule(ModelMap model){
              model.addAttribute("pageTitle", "Modules");
              model.addAttribute("clickListModule", true);
              model.addAttribute("modules", moduleService.getAll());
              model.addAttribute("sapActive", "active");
              return "admin-master";
        }
         @GetMapping("/list-roles")
        public String listRoles(ModelMap model){
              model.addAttribute("pageTitle", "Roles");
              model.addAttribute("clickListRoles", true);
              model.addAttribute("roles", roleService.getAll());
              model.addAttribute("sapActive", "active");
              return "admin-master";
        }
        @GetMapping("/list-users")
        public String listUsers(ModelMap model){
              User user =  userService.getCurrentUser();
               if(user==null){
                  model.addAttribute("users", userService.getActiveUsers(Boolean.TRUE));
              }
               else{
              for(UserRole userRole : user.getUserRoles()){
                    if(userRole.getName().equals("SUPER_ADMIN")){
                        
                         model.addAttribute("users", userService.getByActiveAndUser());
                    }
                    else{
                         model.addAttribute("users", userService.getActiveUsers(Boolean.TRUE));
                        
                    }
              } 
               } 
              model.addAttribute("pageTitle", "Admins");
              model.addAttribute("clickListUsers", true);
              model.addAttribute("sapActive", "active");
              return "admin-master";
        }
        
         @GetMapping("/email")
        public String emails(ModelMap model){
              CompanyEmail ce = new CompanyEmail();
              List<CompanyEmail> emails = companyEmailService.getByDeletedAndGlobalEmail(Boolean.FALSE, Boolean.TRUE);
              model.addAttribute("clickAddGlobalEmail", true);   
              model.addAttribute("globalEmails", emails);
              model.addAttribute("activeGbListEmail", "active");
              return setUpModel(model, ce, "Global Email");
        }
         @GetMapping("/email/edit/{id}")
        public String editEmails(ModelMap model, @PathVariable Long id, RedirectAttributes ra){
           CompanyEmail e = companyEmailService.get(id);
           if(e!=null){
             List<CompanyEmail> emails = companyEmailService.getByDeletedAndGlobalEmail(Boolean.FALSE, Boolean.TRUE);
              model.addAttribute("clickAddGlobalEmail", true);   
              model.addAttribute("globalEmails", emails);
              model.addAttribute("activeGbAddEmail", "in show active");
              return setUpModel(model, e, "Global Email");
           
        }
           return null;
        }
        
        @PostMapping("/email")
         public String saveGlobalEmail(@ModelAttribute("item") CompanyEmail companyEmail, ModelMap model, RedirectAttributes ra){ 
               companyEmail.setActive(Boolean.FALSE);
             if(companyEmail.getId()!=null){
                 CompanyEmail ce = companyEmailService.get(companyEmail.getId());
                 companyEmail.setDateCreated(ce.getDateCreated());
                 companyEmail.setActive(ce.getActive());
                 if(companyEmail.getPassword()==null || companyEmail.getPassword().isEmpty()){
                     companyEmail.setPassword(ce.getPassword());
                 }
             } 
              companyEmail.setGlobalEmail(Boolean.TRUE);
              companyEmail.setCompany(null);
              companyEmailService.save(companyEmail);
              ra.addFlashAttribute("message", new AppMessage.MessageBuilder().build());
              ra.addFlashAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Email Saved Successfully").messageType(MessageType.MESSAGE).build());           
              return "redirect:/admin/gb/email";   
         }
         
          @GetMapping("/email/enable/{id}")
          public String enableGlobalEmail(ModelMap model, @PathVariable Long id, HttpSession session,  RedirectAttributes ra){
                CompanyEmail e = companyEmailService.get(id);
                    e.setActive(Boolean.TRUE);
                    companyEmailService.save(e);
                    List<CompanyEmail> cs = companyEmailService.getByActiveAndDeletedAndGlobalEmail(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE);
              for(CompanyEmail c : cs){
                  if(!Objects.equals(c.getId(), e.getId())){
                      c.setActive(Boolean.FALSE);
                      companyEmailService.save(c);
                  }
              }
              ra.addFlashAttribute("message", new AppMessage.MessageBuilder().build());
              ra.addFlashAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Enable Successful").messageType(MessageType.MESSAGE).build()); 
                   return "redirect:/admin/gb/email";
          }   
    
            @GetMapping("/email/disable/{id}")
          public String disableGlobalEmail(ModelMap model, @PathVariable Long id,  RedirectAttributes ra){
                CompanyEmail e = companyEmailService.get(id);
                e.setActive(Boolean.FALSE);
                companyEmailService.save(e);
                ra.addFlashAttribute("message", new AppMessage.MessageBuilder().build());
              ra.addFlashAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Disable Successful").messageType(MessageType.MESSAGE).build()); 
                return "redirect:/admin/gb/email";
          } 
           @GetMapping("/email/delete/{id}")
          public String deleteGlobalEmail(ModelMap model, @PathVariable Long id, RedirectAttributes ra){
                CompanyEmail e = companyEmailService.get(id);
                e.setDeleted(Boolean.TRUE);
                 e.setActive(Boolean.FALSE);
                companyEmailService.save(e);
                ra.addFlashAttribute("message", new AppMessage.MessageBuilder().build());
              ra.addFlashAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Delete Successful").messageType(MessageType.MESSAGE).build()); 
                return "redirect:/admin/gb/email";
          } 
        
        public List<UserRole> sanitizeSuperAdminRoles(List<UserRole> list){
            
            List<UserRole> removedList = new ArrayList();
                     for(UserRole u : list){
                         if(u.getName().equalsIgnoreCase("GLOBAL")){
                             removedList.add(u);
                         }
                         if(u.getName().equalsIgnoreCase("SUPER_ADMIN")){
                             removedList.add(u);
                         }
                         if(u.getName().equalsIgnoreCase("USER")){
                             removedList.add(u);
                         }
                     }
                 list.removeAll(removedList);
                 return list;

        }
        public List<UserRole> sanitizeGlobalRoles(List<UserRole> list){
            
                List<UserRole> removedList = new ArrayList();
                     for(UserRole u : list){
                         if(u.getName().equalsIgnoreCase("ADMIN")){
                             removedList.add(u);
                         }
                         if(u.getName().equalsIgnoreCase("USER")){
                             removedList.add(u);
                         }
                     }
                 list.removeAll(removedList);
                 return list;
        }
}
