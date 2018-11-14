/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.controller;

import com.sbms.domain.Client;
import com.sbms.domain.Company;
import com.sbms.domain.User;
import com.sbms.domain.util.ClientType;
import com.sbms.domain.util.YesNo;
import com.sbms.service.ClientService;
import com.sbms.service.CompanyService;
import com.sbms.service.UserService;
import com.sbms.utilities.AppMessage;
import com.sbms.utilities.MessageType;
import com.sbms.validator.ClientValidator;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import jdk.nashorn.internal.objects.annotations.Getter;
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
@RequestMapping("/client")
public class ClientController {
    
    @Resource
    private ClientService clientService;
    @Resource
    private UserService userService;
    @Resource
    private ClientValidator clientValidator;
   // @Autowired
   // private ClientService clientService;
    @Resource
    private CompanyService companyService;
    
   
     private String setUpModel(ModelMap model, Client item) {
         
         model.addAttribute("pageTitle", "Create/Edit Client");
         model.addAttribute("item", item);
         model.addAttribute("clientType", ClientType.values());
         model.addAttribute("yesNo", YesNo.values());
         model.addAttribute("upActive", "active");
          return "master";
      }
     @RequestMapping("/addClient")
     public String addUsers(ModelMap model){
         Client client =  new Client();
         model.addAttribute("clickAddClient", true); 
         model.addAttribute("upActive", "active");
         return setUpModel(model, client);
     }
     @RequestMapping("/list")
     public String listClient(ModelMap model, HttpSession session){        
         Company company = (Company) session.getAttribute("company");
         User user = userService.getCurrentUser(); 
         List roles  = new ArrayList(user.getUserRoles());
         String role = roles.get(0).toString();
         List<Client> list;
          if(role.equalsIgnoreCase("GLOBAL") || role.equalsIgnoreCase("SUPER_ADMIN") || role.equalsIgnoreCase("ADMIN")){    
              list = clientService.findByActiveAndCompany(company);
          }
          else{
              list = clientService.findByActiveAndUserAndCompany(Boolean.TRUE, user, company);
          }        
         model.addAttribute("pageTitle", "Client List");
         model.addAttribute("clickListClient", true);
         model.addAttribute("clients", list);
         model.addAttribute("upActive", "active");
         return "master";
     }
     @RequestMapping("/view-all") 
     String getAllClients(ModelMap model, HttpSession session){
         Company company = (Company) session.getAttribute("company");
         List<Client> list = clientService.findByActiveAndCompany(company);
         model.addAttribute("pageTitle", "Client List");
         model.addAttribute("clickListClient", true);
         model.addAttribute("all", true);
         model.addAttribute("clients", list);
         model.addAttribute("upActive", "active");
         return "master";
     }
    @RequestMapping(value = "/addClient/form", method = RequestMethod.GET)
    public String userForm(ModelMap model, @RequestParam(required = false) Long id) {        
       model.addAttribute("message", new AppMessage.MessageBuilder().build());
       model.addAttribute("clickAddClient", true); 
        Client c = new Client();
        if (id != null) {
            c = clientService.get(id);
        }
        return setUpModel(model, c);
    }   
    
    @GetMapping("/delete/{id}")
    public String deleteClient(ModelMap model, @PathVariable(value = "id", required = true) Long id, RedirectAttributes redirectAttrs){
        Client client = clientService.get(id);
        clientService.delete(client);
         redirectAttrs.addFlashAttribute("message", new AppMessage.MessageBuilder().build());
         redirectAttrs.addFlashAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Delete Successful.").messageType(MessageType.MESSAGE).build());
         return "redirect:/client/list";
    }
    /**
     * 
     * @param model
     * @param clientId Client Id
     * @return Client Details by id from client table
     */
    
    @GetMapping("/details/{clientId}")
    public String clientDetails(ModelMap model, @PathVariable("clientId") Long clientId){
        Client client = clientService.get(clientId);       
        model.addAttribute("client", client);
        model.addAttribute("clickClientDetails", true);
        model.addAttribute("upActive", "active");
        return "users/user-master";
    }
 
    @RequestMapping(value = "/addClient/form", method = RequestMethod.POST)
    public String saveUsers(@ModelAttribute("item") @Valid Client client, BindingResult result, ModelMap model, RedirectAttributes redirectAttrs, HttpSession session){  
            
        clientValidator.validate(client, result);     
         model.addAttribute("message", new AppMessage.MessageBuilder().build());
        if (result.hasErrors()) {  
            model.addAttribute("clickAddClient", true); 
            model.addAttribute("message", new AppMessage.MessageBuilder().build());
            model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Data entry error has occurred").messageType(MessageType.ERROR).build());           
            return setUpModel(model, client);  
        }
        if(client.getId()!=null){
            Client c = clientService.get(client.getId());
            client.setCompany(c.getCompany());
            client.setDateCreated(c.getDateCreated());
            client.setCreatedBy(c.getCreatedBy());
            clientService.save(client);
            redirectAttrs.addFlashAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Saved Successfully").messageType(MessageType.MESSAGE).build());
             return "redirect:/client/list";
        }
       
        Company company = (Company) session.getAttribute("company");
        client.setCompany(company);
      
             
        Client savedRecord = clientService.save(client); 
        model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("New Client Was Added Successfully").messageType(MessageType.MESSAGE).build());
        
       
        if(client.getBranch().equals("Yes")){  
            redirectAttrs.addAttribute(savedRecord);
            model.addAttribute("clickAddBranch", true); 
            return "redirect:/client/branch/addBranch/form";
        }
        else{
            redirectAttrs.addAttribute(savedRecord);
         return "redirect:/client/requiredDocuments/form?id="+savedRecord.getId();
        }                               
   }    
}
