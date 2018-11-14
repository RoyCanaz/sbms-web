/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.controller;

import com.sbms.domain.Company;
import com.sbms.domain.EmailType;
import com.sbms.domain.Modules;
import com.sbms.domain.User;
import com.sbms.domain.UserRole;
import com.sbms.repository.EmailTypeRepo;
import com.sbms.service.ClientService;
import com.sbms.service.CompanyService;
import com.sbms.service.UserService;
import com.sbms.service.VisitPlanService;
import com.sbms.utilities.AppMessage;
import com.sbms.utilities.MessageType;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author user
 */
@Controller
public class LoginController extends BaseController{
    @Resource
    private UserService userService;
    @Autowired
    private EmailTypeRepo emailTypeRepo;
    @Resource
    private CompanyService companyService;
    @Resource
    private VisitPlanService planService;
     @Resource
     private ClientService clientService;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(Model model){
        return "login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
    if (error != null){
    model.addAttribute("error", "Your username and password is invalid.");
    }
    if (logout != null){
    model.addAttribute("message", "You have been logged out successfully.");
    }
    return "login";
    }
    
    @RequestMapping(value = "/loginfailed")
    public String getloginFailed(ModelMap model) {
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Incorrect Username/Password").messageType(MessageType.ERROR).build());
        model.addAttribute("pageTitle", "Access Denied");
        return "login";
    }
    /*
    @RequestMapping(value = "/error",  method = RequestMethod.GET)
    public String getRedirectError(ModelMap model) {
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("pageTitle", "Access Denied");
        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Incorrect Username/Password").messageType(MessageType.ERROR).build());
        return "login";
    }
*/
    @RequestMapping(value = "/home")
    public String getSuccess(ModelMap model, HttpSession session) {
        
        User user = userService.getCurrentUser(); 
        Company c = user.getCompany();
        String companyName = "";
       
        
       
        Set<UserRole> roles = user.getUserRoles();
           for(UserRole userRole : roles){
               if(userRole.getName().equals("GLOBAL")){  
                   companyName = "GLOBAL ACCOUNT";  
                   session.setAttribute("companiez", companyService.getAll());
                   List<EmailType> emailTypes = emailTypeRepo.findAll();
                   if(emailTypes.size()<1){
                     saveEmailTypes();
                   }
               }
               else if(userRole.getName().equals("SUPER_ADMIN")){
                   session.setAttribute("companiez", companyService.getByActiveAndUser());
                   companyName = "SUPER ADMIN ACCOUNT"; 
               }
               else{
                   if(c!=null){
                       session.setAttribute("company", c);
                         for(Modules m : c.getModules()){                               
                                companyName = user.getCompany().getCompanyName();
                                if(m.getName().equals("Sales")){
                                    session.setAttribute("sales", true);
                                }
                            }
                     }
               }
           }
          
         
        session.setAttribute("orgName", companyName);   
        session.setAttribute("accName", user.getDisplayName());
        
       
        
        model.addAttribute("visitPlans", planService.findByStatusAndCreatedBy("0", user.getId()));
        model.addAttribute("plansToday", planService.findByDateOfVisitAndCreatedBy(new Date(), user.getId()));
        
        
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Login Successfull!").messageType(MessageType.INFO).build());
        model.addAttribute("home", true);
        return "master";
    }

    @RequestMapping(value = "/logout")
    public String logout(ModelMap model, HttpSession httpSession) {
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("message", "Access Failed");
        httpSession.invalidate();
        return "redirect:login";

    }
    
    @RequestMapping(value = "/acceessdenied")
    public String accessDenied(ModelMap model, HttpSession httpSession) {
        model.addAttribute("pageTitle", "Access Denied");
        return "accessdenied";
    }
    
    public void saveEmailTypes(){
         EmailType emailType = new EmailType();
                        emailType.setName("Bcc");
                        emailType.setDescription("Bcc Email Type");
                        emailTypeRepo.save(emailType);                       
                    EmailType emailType2 = new EmailType();
                        emailType2.setName("Cc");
                        emailType2.setDescription("Ccc Email Type");
                        emailTypeRepo.save(emailType2); 
    }
}
