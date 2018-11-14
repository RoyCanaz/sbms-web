/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.controller;

import com.sbms.domain.Brand;
import com.sbms.domain.Category;
import com.sbms.domain.Company;
import com.sbms.service.CategoryService;
import com.sbms.utilities.AppMessage;
import com.sbms.utilities.MessageType;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Roy
 */
@Controller
@RequestMapping("/admin/category")
public class CategoryController {
   @Resource
   private CategoryService categoryService;
    
        @RequestMapping(value = {"/list"}, method = RequestMethod.GET) 
        public String listCategories(ModelMap model, HttpSession session){
        Company company = (Company) session.getAttribute("company");
        Category cat = new Category();
        model.addAttribute("pageTitle", "Category List");
        model.addAttribute("categories", cat);
        model.addAttribute("category", categoryService.findByActiveAndCompany(Boolean.TRUE, company));
        model.addAttribute("clickListCategories", true);
         model.addAttribute("apActive", "active");
        return "master";
  }
  
        @GetMapping("/edit")
        public String edit(ModelMap model, @RequestParam(required = false) Long id){
              
              Category category = new Category();
              if(id!=null){                 
                  category = categoryService.get(id);
                  model.addAttribute("categoryId", id);
              }
              model.addAttribute("category", category);
              model.addAttribute("clickAddCategory", true);  
               model.addAttribute("apActive", "active");
              return "admin-super";
        }
        
        @PostMapping("/edit")
        public String addCategory(@ModelAttribute("category") Category category, ModelMap model, RedirectAttributes ra){      
            Category c = categoryService.get(category.getId());
            c.setName(category.getName());
            c.setDescription(category.getDescription());
            categoryService.save(c);
            ra.addFlashAttribute("message", new AppMessage.MessageBuilder().build());
            ra.addFlashAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Saved  Successfully").messageType(MessageType.MESSAGE).build());           
            return "redirect:/admin/category/list";
       }
        @GetMapping("/delete")
          public String deleteCategory(ModelMap model, @RequestParam(required = true) Long id, RedirectAttributes ra){
              Category category = categoryService.get(id);
              categoryService.delete(category);
              ra.addFlashAttribute("message", new AppMessage.MessageBuilder().build());
              ra.addFlashAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Delete  Successfull").messageType(MessageType.MESSAGE).build());
            // redirectAttributes.addAttribute(model);
              return "redirect:/admin/category/list";
              
          }
        
}
