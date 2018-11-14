/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.controller;

import com.sbms.domain.Company;
import com.sbms.domain.User;
import com.sbms.emails.PasswordResetEmailService;
import com.sbms.service.UserService;
import com.sbms.utilities.AppMessage;
import com.sbms.utilities.MessageType;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/password")
public class PasswordController {
    @Resource
    private UserService userService;
    @Resource
    private PasswordResetEmailService pres;
    
    
    @GetMapping("/forgot")
    public String forgot(ModelMap model){
        model.addAttribute("clickForgotPassword", true);
        return "passwords/index";
    }
    @GetMapping("/send")
    public String sendLink(ModelMap model, @RequestParam("email") String email, HttpServletRequest request){
       User user = userService.findByUserName(email);
       
       if(user==null){
           model.addAttribute("unknownEmail", "Unknown Email");
           model.addAttribute("message", new AppMessage.MessageBuilder().build());
           model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Not Found Error.").messageType(MessageType.ERROR).build()); 
       }
       else{
           user.setResetToken(UUID.randomUUID().toString());
           userService.save(user);
            Company company = user.getCompany()==null?null:user.getCompany();
            String appUrl = request.getScheme() + "://" + request.getServerName();			
           pres.sendEmail(appUrl, user, company);
           model.addAttribute("message", new AppMessage.MessageBuilder().build());
           model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("A password reset link has been sent to " + user.getUserName()).messageType(MessageType.MESSAGE).build()); 
       }
        model.addAttribute("clickForgotPassword", true);
        return "passwords/index";
    }
    
    @GetMapping("/reset")
	public String displayResetPasswordPage(ModelMap model, @RequestParam("token") String token, RedirectAttributes redirectAttributes) {
		
		Optional<User> user = userService.findUserByResetToken(token);

		if (user.isPresent()) { 
			model.addAttribute("resetToken", token);
                         model.addAttribute("clickResetPassword", true);
                        return "passwords/index";
		} else { 
			 model.addAttribute("message", new AppMessage.MessageBuilder().build());
                        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Not Found Error.").messageType(MessageType.ERROR).build()); 
                        model.addAttribute("clickForgotPassword", true);
                        return "passwords/index";
		}
             	
	}
     /*   @GetMapping("/reset")
        public String resetPwd(ModelMap model){
            model.addAttribute("clickResetPassword", true);
            return "passwords/index";
        }*/
        @PostMapping("/reset")
	public String setNewPassword(ModelMap model, @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {

	
		Optional<User> user = userService.findUserByResetToken(requestParams.get("token"));

		if (user.isPresent()) {			
			User resetUser = user.get();   
                         BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 
                        resetUser.setPassword(encoder.encode(requestParams.get("password")));         
			resetUser.setResetToken(null);
			userService.save(resetUser);
                        redir.addFlashAttribute("message", new AppMessage.MessageBuilder().build());
                        redir.addFlashAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("You have successfully reset your password.  You may now login.").messageType(MessageType.MESSAGE).build()); 
		      return "redirect:/";
			
			
		} else {
			 model.addAttribute("message", new AppMessage.MessageBuilder().build());
                        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Not Found Error.").messageType(MessageType.ERROR).build()); 
                        model.addAttribute("clickResetPassword", true);
                        return "passwords/index";	
		}
		
		
   }
    
}
