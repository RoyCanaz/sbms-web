/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/user")
public class UserDashboardController {
    
    
     @GetMapping("/dashboard")
        public String userDashBoard(ModelMap model){
              model.addAttribute("pageTitle", "Dashboard");
              model.addAttribute("clickUserDashboard", true);
              model.addAttribute("upActive", "active");
              return "users/user-master";
     } 
    
}
