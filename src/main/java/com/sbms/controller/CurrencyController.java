/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.controller;

import com.sbms.domain.Company;
import com.sbms.domain.Currency;
import com.sbms.domain.Modules;
import com.sbms.domain.Product;
import com.sbms.domain.User;
import com.sbms.service.CurrencyService;
import com.sbms.service.ProductService;
import com.sbms.utilities.AppMessage;
import com.sbms.utilities.MessageType;
import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.commons.math3.util.Precision;
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
@RequestMapping("/admin/currency")
public class CurrencyController {
    @Resource
    private CurrencyService currencyService;
    @Resource
    private ProductService productService;
    
    @GetMapping("/add/form") 
        public String adminHome(ModelMap model, @RequestParam(required = false) Long id){
              Currency currency = new Currency();
               if(id!=null){
                   currency = currencyService.get(id);
               }
              model.addAttribute("pageTitle", "Currency");
              model.addAttribute("clickAddCurrency", true);
              model.addAttribute("item", currency);
               model.addAttribute("apActive", "active");
              return "admin-super";
        }
    
    @PostMapping("/add")
         public String saveCurrency(@ModelAttribute("item") Currency currency, ModelMap model, HttpSession session){ 
              Company company = (Company) session.getAttribute("company");
              currency.setCompany(company);
              currency.setActive(Boolean.FALSE);
              currencyService.save(currency);
              model.addAttribute("clickAddCurrency", true);
              model.addAttribute("message", new AppMessage.MessageBuilder().build());
              model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Currency Added Successfully").messageType(MessageType.MESSAGE).build());           
              return "redirect:/admin/currency/list";
         }
         
        @GetMapping("/list")
        public String listModule(ModelMap model, HttpSession session){
             Company company = (Company) session.getAttribute("company");
              model.addAttribute("pageTitle", "Currency");
              model.addAttribute("clickListCurrency", true);
              model.addAttribute("currencies", currencyService.findByCompany(company));
               model.addAttribute("apActive", "active");
              return "admin-super";
        }
        @GetMapping("/delete")
          public String deleteCurrency(ModelMap model, @RequestParam(required = false) Long id, RedirectAttributes redirectAttributes){
              Currency currency = currencyService.get(id);
              currencyService.delete(currency);
              model.addAttribute("message", new AppMessage.MessageBuilder().build());
              model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Delete Successfull").messageType(MessageType.MESSAGE).build());
              redirectAttributes.addAttribute(model);
              return "redirect:/admin/currency/list";
          }   
           @GetMapping("/enable")
          public String enableCurrency(ModelMap model, @RequestParam(required = false) Long id, HttpSession session, RedirectAttributes redirectAttributes){
              Company company = (Company) session.getAttribute("company");
              Currency currency = currencyService.get(id);
              currency.setActive(Boolean.TRUE);
              currencyService.save(currency);
              
              
              
              List<Currency> cs = currencyService.findByCompany(company);
              for(Currency c : cs){
                  if(!Objects.equals(c.getId(), currency.getId())){
                      c.setActive(Boolean.FALSE);
                      currencyService.save(c);
                  }
              }
              
              updateRetailPrice(company);
              model.addAttribute("message", new AppMessage.MessageBuilder().build());
              model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Enable Successfull").messageType(MessageType.MESSAGE).build());
           //   redirectAttributes.addAttribute(model);
              return "redirect:/admin/currency/list";
          }   
    
          @GetMapping("/disable")
          public String disableCurrency(ModelMap model, @RequestParam(required = false) Long id, HttpSession session){
              Company company = (Company) session.getAttribute("company");
              Currency currency = currencyService.get(id);
              currency.setActive(Boolean.FALSE);
              currencyService.save(currency);  
              updateDefault(company);
              model.addAttribute("message", new AppMessage.MessageBuilder().build());
              model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Enable Successfull").messageType(MessageType.MESSAGE).build());
              return "redirect:/admin/currency/list";
          }  
           public void updateRetailPrice(Company company){
                Currency currency = currencyService.getByActiveAndCompany(Boolean.TRUE, company);
                if(currency==null){
                    return;
                }

                for(Product product : productService.findByCompany(company)){
                     Double rp = currency.getRate().doubleValue() * product.getSellingPrice();
                         Double up = rp/1.15;
                         product.setRetailPrice(Precision.round(rp,2));
                         product.setUnitPrice(Precision.round(up,2));
                     productService.save(product);
       
                }
           } 
           public void updateDefault(Company company){
              
                for(Product product : productService.findByCompany(company)){
                     Double rp = 1 * product.getSellingPrice();
                         Double up = rp/1.15;
                         product.setRetailPrice(Precision.round(rp,2));
                         product.setUnitPrice(Precision.round(up,2));
                     productService.save(product);
       
                }
           }
}
