/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.controller;

import com.sbms.domain.Brand;
import com.sbms.domain.Category;
import com.sbms.domain.Company;
import com.sbms.service.BrandService;
import com.sbms.service.CategoryService;
import com.sbms.service.UserService;
import com.sbms.utilities.AppMessage;
import com.sbms.utilities.MessageType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
 * @author user
 */
@Controller
@RequestMapping("/admin/brand")
public class BrandController {
    @Resource
    private UserService userService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private BrandService brandService;
    
    
    
     private String setUpModel(ModelMap model, Brand item, Company company) {
        
        List<Category> categories = categoryService.findByActiveAndCompany(Boolean.TRUE, company);
         Set<Brand> brands = new HashSet<>();  
          
          
          categories.forEach(category->{
              if(category.getBrands()!=null){
                 brands.addAll(category.getBrands());
              }
          });
        model.addAttribute("pageTitle", "Brand List");
        model.addAttribute("category", categories);
        model.addAttribute("brand", item);
        model.addAttribute("brands", brands);
        model.addAttribute("clickListBrands", true);
        model.addAttribute("apActive", "active");
        return "master";
    }
  @RequestMapping(value = {"/list"}, method = RequestMethod.GET) 
  public String listBrands(ModelMap model, HttpSession session){
        ///model.addAttribute("message", new AppMessage.MessageBuilder().build());
         Company company = (Company) session.getAttribute("company");
       
        Brand brand = new Brand();
        
        return setUpModel(model, brand, company);
  }
  @RequestMapping(value = {"/add"}, method = RequestMethod.POST) 
  public String addBrands(@ModelAttribute("brand") Brand brand, ModelMap model , HttpSession session){
      Company company = (Company) session.getAttribute("company");
       // Category category = categoryService.get(brand.getCategory().getId());
       // brand.setCategory(category);
        brandService.save(brand); 
        Brand u = new Brand();
        model.addAttribute("message", new AppMessage.MessageBuilder().build());
        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("New Brand Was Created Successfully").messageType(MessageType.MESSAGE).build());           
        return setUpModel(model, u, company);
  }
  
       @GetMapping("/edit")
        public String edit(ModelMap model, @RequestParam(required = false) Long id, HttpSession session){
              Company company = (Company) session.getAttribute("company");
              List<Category> category = categoryService.findByActiveAndCompany(Boolean.TRUE, company);
              Brand brand = new Brand();
              if(id!=null){                 
                  brand = brandService.get(id);              
              }
              List<Category> newCategoryList = new ArrayList<>();
           for(Category item : category){
                  boolean exists = false;
                    for(Category c : brand.getCategories()){
                        if(item.getId().equals(c.getId())){
                            exists = true;
                        }
                    }
                   if(!exists){
                    newCategoryList .add(item);
                }  
           }
         
           
              model.addAttribute("brand", brand);
              model.addAttribute("categories", newCategoryList);
              model.addAttribute("category", category);
              model.addAttribute("clickAddBrand", true);  
               model.addAttribute("apActive", "active");
              return "admin-super";
        }
         @GetMapping("/delete")
          public String deleteBrand(ModelMap model, @RequestParam(required = false) Long id, RedirectAttributes redirectAttributes){
              Brand brand = brandService.get(id);
              brandService.delete(brand);
              model.addAttribute("message", new AppMessage.MessageBuilder().build());
              model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Delete  Successfull").messageType(MessageType.MESSAGE).build());
            // redirectAttributes.addAttribute(model);
              return "redirect:/admin/brand/list";
              
          }
          @PostMapping("/add-brand-categories")
          public String addBrandcategories(@ModelAttribute("brand") Brand brand, ModelMap model , HttpSession session){          
              Brand b = brandService.get(brand.getId());
              Set<Category>  categorys = b.getCategories();
              categorys.addAll(brand.getCategories());
              b.setCategories(categorys);
              brandService.save(b);
              return  "redirect:/admin/brand/edit?id="+brand.getId();
          }
}
