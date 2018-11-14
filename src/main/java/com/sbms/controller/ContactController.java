/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.controller;

import com.sbms.domain.Client;
import com.sbms.domain.Contact;
import com.sbms.service.ContactService;
import com.sbms.utilities.AppMessage;
import com.sbms.utilities.MessageType;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("client/contact")
public class ContactController {
     
    @Resource
    private ContactService contactService;
    
    private String setUpModel(ModelMap model, Contact item) {
         
         model.addAttribute("pageTitle", "Add/View Contacts");
         model.addAttribute("contactsForm", item);
         
              
          return "master";
      }
     @RequestMapping(value = "/addContact", method = RequestMethod.GET)
     public String addContacts(ModelMap model, @RequestParam Long id){
         Contact con =  new Contact();
         
         List<Contact> c = contactService.findByActiveAndClientId(id);
         
         
         model.addAttribute("clickContacts", true); 
         model.addAttribute("ci", id);
         model.addAttribute("contacts", c);
         return setUpModel(model, con);
     }
     @GetMapping("/edit/{id}")
     public String editContact(ModelMap model, @PathVariable("id") Long id){
         Contact contact = new Contact();
         if(id!=null){
             contact = contactService.get(id);
             
         }
          model.addAttribute("item", contact);
          model.addAttribute("clickEditContact", true);  
         return "users/user-master";
     }
     @GetMapping("/delete/{clientId}/{id}")
     public String deleteContact(ModelMap model, @PathVariable("clientId") Long clientId, @PathVariable("id") Long id, RedirectAttributes ra){
        
         if(id!=null){
             Contact contact = contactService.get(id);
             contactService.delete(contact);
         }
         
          ra.addFlashAttribute("message", new AppMessage.MessageBuilder().build());
          ra.addFlashAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Delete Successful.").messageType(MessageType.MESSAGE).build());  
          return "redirect:/client/contact/addContact?id="+clientId;
     }
     @PostMapping("/edit")
     public String saveContact(@ModelAttribute("item") Contact contact, ModelMap model){
         Long id = null;
         if(contact.getId()!=null){
             Contact c = contactService.get(contact.getId());
             contact.setDateCreated(c.getDateCreated());
             contact.setClient(c.getClient());
             id = c.getClient().getId();
             
         }
         contactService.save(contact);
         return "redirect:/client/contact/addContact?id="+id;
     }
}
