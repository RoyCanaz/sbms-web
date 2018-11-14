/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.controller;

import com.sbms.domain.BankingDetail;
import com.sbms.domain.Company;
import com.sbms.domain.BankingDetail;
import com.sbms.service.BankingDetailService;
import com.sbms.utilities.AppMessage;
import com.sbms.utilities.MessageType;
import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("admin/bankingDetail")
public class BankingDetailController {
    @Resource
    private BankingDetailService bankingService;
    
     public String setUpModel(ModelMap model, Object item){
         model.addAttribute("item", item);
         model.addAttribute("clickAddBankingDetails", true); 
         model.addAttribute("pageTitle", "Add Banking Details");
          model.addAttribute("apActive", "active");
         return "page-master";
    }
    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public String loadPage(ModelMap model, @PathVariable(name = "id", required = false) Long id){
        BankingDetail bankingDetail = new BankingDetail();
        if(id!=null)
        {
            bankingDetail = bankingService.get(id);
        }
        return setUpModel(model, bankingDetail);
    }
    @GetMapping("/add")
    public String loadPage2(ModelMap model){
        BankingDetail bankingDetail = new BankingDetail();  
        return setUpModel(model, bankingDetail);
    }
    
   @RequestMapping(value = "/form", method = RequestMethod.POST)
   public String saveSupplier(@ModelAttribute("item")BankingDetail bankingDetail, ModelMap model, HttpSession session, RedirectAttributes ra){  
       Company company = (Company) session.getAttribute("company");
       
       ra.addFlashAttribute("message", new AppMessage.MessageBuilder().build());
       ra.addFlashAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Banking Details Saved Successfully").messageType(MessageType.MESSAGE).build());
       if(bankingDetail.getId()!=null){
           BankingDetail bd = bankingService.get(bankingDetail.getId());
           bankingDetail.setDateCreated(bd.getDateCreated());
           bankingDetail.setCompany(bd.getCompany());
           bankingDetail.setCreatedBy(bd.getCreatedBy());
           bankingService.save(bankingDetail);
           return "redirect:/admin/bankingDetail/list";
       }
       bankingDetail.setCompany(company);
       bankingService.save(bankingDetail); 
       return "redirect:/admin/bankingDetail/list";
              
   }
    @GetMapping("/list")
        public String listSuppliers(ModelMap model, HttpSession session){
            Company company = (Company) session.getAttribute("company");
              model.addAttribute("pageTitle", "Banks");
              model.addAttribute("clickListBanks", true);
              model.addAttribute("banks", bankingService.findByCompany(company));
              model.addAttribute("apActive", "active");
              return "page-master";
        }
        @GetMapping("/enable/{id}")
          public String enableDefaultEmail(ModelMap model, @PathVariable Long id, HttpSession session){
              Company company = (Company) session.getAttribute("company");
                BankingDetail e = bankingService.get(id);
            
                    e.setActive(Boolean.TRUE);
                    bankingService.save(e);
                    List<BankingDetail> cs = bankingService.findByCompany(company);
              for(BankingDetail c : cs){
                  if(!Objects.equals(c.getId(), e.getId())){
                      c.setActive(Boolean.FALSE);
                      bankingService.save(c);
                  }
              }
                    return "redirect:/admin/bankingDetail/list";
          }   
    
            @GetMapping("/disable/{id}")
          public String disableDefaultEmail(ModelMap model, @PathVariable Long id){
                BankingDetail e = bankingService.get(id);
                e.setActive(Boolean.FALSE);
                bankingService.save(e);
                return "redirect:/admin/bankingDetail/list";
          } 
}
