/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.controller;

import com.sbms.domain.Company;
import com.sbms.domain.User;
import com.sbms.domain.UserRole;
import com.sbms.emails.service.EmailService;
import com.sbms.service.CompanyService;
import com.sbms.service.UserRoleService;
import com.sbms.service.UserService;
import com.sbms.utilities.AppMessage;
import com.sbms.utilities.MessageType;
import com.sbms.validator.UserValidator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Roy
 */
@Controller
@RequestMapping("/admin")
public class UserController extends BaseController  {
    
    
    @Resource
    private UserService userService;
    @Resource
    private UserRoleService userRole;
    @Resource
    private UserValidator userValidator;
    @Resource
    private CompanyService companyService;
    @Resource
    private EmailService  emailService;
    
     @RequestMapping("/addUsers")
     public String addUsers(ModelMap model){
         User users =  new User();
       List<UserRole> list =  userRole.getAll(); 
       List<UserRole> removedList = new ArrayList();
            for(UserRole u : list){
                if(u.getName().equalsIgnoreCase("GLOBAL")){
                    
                    removedList.add(u);

                }
                if(u.getName().equalsIgnoreCase("SUPER_ADMIN")){
                    
                    removedList.add(u);

                }
            }
        list.removeAll(removedList);
         
        
         
         model.addAttribute("message", new AppMessage.MessageBuilder().build());
         model.addAttribute("pageTitle", "Add Users");
         model.addAttribute("item", users);
         model.addAttribute("user", userService.getCurrentUsername());
         model.addAttribute("userRoles", list);
         model.addAttribute("clickAddUsers", true);
          model.addAttribute("apActive", "active");
         return "master";
     }
   private String setUpModel(ModelMap model, User item) {
      /*  List<UserRole> userRoles = new ArrayList<>();
        for(UserRole ur : userRole.getAll()){
            if(!ur.getName().equals("GLOBAL") || !ur.getName().equals("SUPER_ADMIN")){
                userRoles.add(ur);
            }
        }*/
        List<UserRole> list =  userRole.getAll(); 
       List<UserRole> removedList = new ArrayList();
            for(UserRole u : list){
                if(u.getName().equalsIgnoreCase("GLOBAL")){
                    
                    removedList.add(u);

                }
                if(u.getName().equalsIgnoreCase("SUPER_ADMIN")){
                    
                    removedList.add(u);

                }
            }
        list.removeAll(removedList);
        model.addAttribute("pageTitle", "Create/Edit User");   
        model.addAttribute("item", item);
        model.addAttribute("clickAddUsers", true);
        model.addAttribute("userRoles", list);
         model.addAttribute("apActive", "active");
        return "master";
    }
    @RequestMapping(value = "/addUsers/form", method = RequestMethod.GET)
    public String userForm(ModelMap model, @RequestParam(required = false) Long id) {
       model.addAttribute("message", new AppMessage.MessageBuilder().build());
       
        User p = new User();
        if (id != null) {
            p = userService.get(id);
            model.addAttribute("userId", p.getId());
        }
        return setUpModel(model, p);
    }
   @RequestMapping(value = "/addUsers/form", method = RequestMethod.POST)
   public String saveUsers(@ModelAttribute("item") @Valid User user, BindingResult result, ModelMap model, HttpSession session, HttpServletRequest request){  
       userValidator.validate(user, result);   
       model.addAttribute("message", new AppMessage.MessageBuilder().build());
        if (result.hasErrors()) { 
                    if(user.getId()!=null){
                       model.addAttribute("userId", user.getId());
                    }
                    
        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Data entry error has occurred").messageType(MessageType.ERROR).build());           
        return setUpModel(model, user);  
        }       
        Company c = (Company) session.getAttribute("company");
         if(user.getId()==null){
             user.setCompany(c);
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
       
      
       User u =  userService.save(user); 
       String appUrl = request.getScheme() + "://" + request.getServerName();
       emailService.sendUserDetails(appUrl, u, c);
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("New User Was Created Successfully").messageType(MessageType.MESSAGE).build());           
        return "redirect:/admin/users/list";
       
        
   }
   @Async
   @RequestMapping(value = {"/users/list"}, method = RequestMethod.GET)
    public String userList(ModelMap model, HttpSession session) {
        Company c = (Company) session.getAttribute("company");
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("pageTitle", "User List");
        model.addAttribute("items", userService.findByCompany(c));
        model.addAttribute("clickListUsers", true);
         model.addAttribute("apActive", "active");
        return "master";
    }
    
   @GetMapping("/account/deactivate/{id}")
          public String deleteUser(ModelMap model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes){
              User user = userService.get(id);
              userService.delete(user);
              redirectAttributes.addFlashAttribute("message", new AppMessage.MessageBuilder().build());
              redirectAttributes.addFlashAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Account Set to Inactive").messageType(MessageType.MESSAGE).build());
              return "redirect:/admin/users/list";
              
          }
 
}
