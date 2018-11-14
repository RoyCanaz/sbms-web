/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.controller;

import com.sbms.domain.Company;
import com.sbms.domain.Logo;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/admin")
public class AdminSuperController {
    
     @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET) 
        public String adminHome(ModelMap model){
              model.addAttribute("pageTitle", "Admin");
              model.addAttribute("clickAdminDashboard", true);
              model.addAttribute("apActive", "active");
              return "admin-super";
        }
        @RequestMapping(value = {"/sales"}, method = RequestMethod.GET) 
        public String adminSales(ModelMap model){
              model.addAttribute("pageTitle", "Admin ");
              model.addAttribute("clickAdminSales", true);
               model.addAttribute("apActive", "active");
              return "admin-super";
        }
        @GetMapping("/setup") 
        public String adminSetUp(ModelMap model, HttpSession session){
            Company company = (Company) session.getAttribute("company");
            String companyname = company.getCompanyName();
            String newCompanyName = companyname.replaceAll("\\s+", "");
              ServletContext context = session.getServletContext();
              String contextPath  = context.getContextPath();
              String logoname = contextPath.concat("/resources/logo/").concat(newCompanyName).concat(".png").toLowerCase();
              model.addAttribute("pageTitle", "Setup");
             
              model.addAttribute("clickAdminSetup", true);
              model.addAttribute("logoname", logoname);
              model.addAttribute("image", new Logo());
               model.addAttribute("apActive", "active");
              return "admin-super";
        }
        
        @PostMapping("/setup/logo")
        public String submit(@RequestParam MultipartFile file, @RequestParam String name, ModelMap modelMap, HttpSession session) {
            Company company = (Company) session.getAttribute("company");
            String[] fileFrags = file.getOriginalFilename().split("\\.");
            String extension = fileFrags[fileFrags.length-1];
            String companyname = company.getCompanyName();
            String newCompanyName = companyname.replaceAll("\\s+", "");
            String filename = newCompanyName.concat(".").concat(extension).toLowerCase();
            File imageFile = new File(session.getServletContext().getRealPath("/resources/logo"), filename);
             try
                {
                    file.transferTo(imageFile);
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            
          return "redirect:/admin/setup";
        }
    
    
}
