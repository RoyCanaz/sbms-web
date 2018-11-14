/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.ajaxRest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sbms.domain.Category;
import com.sbms.domain.Client;
import com.sbms.domain.ClientInventory;
import com.sbms.domain.Company;
import com.sbms.domain.Contact;
import com.sbms.domain.RequiredDocuments;
import com.sbms.dto.CategoryDTO;
import com.sbms.dto.ClientInventoryDTO;
import com.sbms.dto.ContactDTO;
import com.sbms.dto.ProcurementDocsDTO;
import com.sbms.service.BrandService;
import com.sbms.service.CategoryService;
import com.sbms.service.ClientInventoryService;
import com.sbms.service.ClientService;
import com.sbms.service.ContactService;
import com.sbms.service.RequiredDocumentsService;
import com.sbms.utilities.AjaxResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("ajax/")
public class RestAjaxController {
     @Resource
     private CategoryService categoryService;
     @Resource
     private ContactService contactService;
     @Resource
     private ClientService clientService;
     @Resource
     private ClientInventoryService inventoryService;
     @Resource
     private BrandService brandService;
     @Resource
     private RequiredDocumentsService documentsService;
    
     @PostMapping(value = "/category/add", consumes = "application/json")
       public AjaxResponse addCategory(@RequestBody CategoryDTO categoryDto, HttpSession session) throws JsonProcessingException {  
           Company company = (Company) session.getAttribute("company");
           Category category = categoryDto.getInstance(categoryDto);
           category.setCompany(company);
           Category cat =   categoryService.save(category);
            //Category cat =   categoryService.save(categoryDto.getInstance(categoryDto));
            
            CategoryDTO catDto = new CategoryDTO(cat.getId(), cat.getName(), cat.getDescription());           
            AjaxResponse response = new AjaxResponse("Done", catDto);
            return response;         
     }
    @GetMapping(value = "/category/all")
    public AjaxResponse listCat() {
            
           List<Category> list = categoryService.getAll();
           List<CategoryDTO> categoryDTOs = new ArrayList<>();
           for(Category category : list){
               CategoryDTO categoryDTO = new CategoryDTO(category.getId(), category.getName(), category.getDescription());
               categoryDTOs.add(categoryDTO);
           }          
            AjaxResponse response = new AjaxResponse("Done", categoryDTOs);
            return response;
    }
    @PostMapping(value = "/contact/add", consumes = "application/json")
    public AjaxResponse addContact(@RequestBody ContactDTO contactDTO){
        Client client = clientService.get(contactDTO.getClientId());
        contactDTO.setClient(client);
        Contact c = contactService.save(contactDTO.getInstance(contactDTO));     
        ContactDTO con = new ContactDTO(c.getId(), c.getFirstName(), c.getLastName(), c.getGender(), c.getJobPosition(),
                c.getDepartment(), c.getOfficePhone(), c.getMobilePhone(), c.getEmail(), contactDTO.getClientId(), c.getCreatedBy().getId());
         AjaxResponse response = new AjaxResponse("Done", con);
         return response;  
    }
     @PostMapping(value = "/inventory/add", consumes = "application/json")
    public AjaxResponse addClientInventory(@RequestBody ClientInventoryDTO clientInventoryDTO){
         System.err.println("=========================");
         System.err.println(clientInventoryDTO.getClientId());
         System.err.println("=========================");
         Client client = clientService.get(clientInventoryDTO.getClientId());
         
         Category cat = categoryService.get(clientInventoryDTO.getCategory());
         clientInventoryDTO.setClient(client); 
         clientInventoryDTO.setCategories(cat);
         ClientInventory ci = inventoryService.save(clientInventoryDTO.getInstance(clientInventoryDTO));
         ClientInventoryDTO cidto = new ClientInventoryDTO(ci.getModel(), ci.getQuantity(), ci.getNeedMaintenence());
        
         AjaxResponse response = new AjaxResponse("Done", cidto);
         return response;  
    }
     @GetMapping(value = "/procurementDocs/{id}")
    public AjaxResponse getProcurementDocs(@PathVariable Long id) {
         RequiredDocuments rd = documentsService.findByClient(id);
         
            ProcurementDocsDTO docsDTO = new ProcurementDocsDTO(rd.getId(), rd.getApplicationLetter(), rd.getCompanyProfile(),
                    rd.getCertOfIncorporation(), rd.getMou(), rd.getCrFourteen(), rd.getVat(), rd.getItf(), rd.getTradeLicense(), rd.getTraceableReference(), rd.getOther(), rd.getClient().getId(), 1l);
         AjaxResponse response = new AjaxResponse("Done", docsDTO);
         return response;
    }
    
}
