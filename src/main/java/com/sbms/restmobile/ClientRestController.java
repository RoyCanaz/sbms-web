/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.restmobile;

import com.sbms.domain.Branch;
import com.sbms.domain.Client;
import com.sbms.domain.ClientInventory;
import com.sbms.domain.Company;
import com.sbms.domain.Contact;
import com.sbms.domain.Notes;
import com.sbms.domain.RequiredDocuments;
import com.sbms.domain.User;
import com.sbms.domain.VisitPlan;
import com.sbms.dto.BranchDTO;
import com.sbms.dto.ClientDTO;
import com.sbms.dto.ClientInventoryDTO;
import com.sbms.dto.ClientDataMapper;
import com.sbms.dto.ClientDataMapperX;
import com.sbms.dto.ClientRequestMapper;
import com.sbms.dto.ContactDTO;
import com.sbms.dto.NotesDTO;
import com.sbms.dto.ProcurementDocsDTO;
import com.sbms.dto.VisitPlanDTO;
import com.sbms.service.BranchService;
import com.sbms.service.ClientInventoryService;
import com.sbms.service.ClientService;
import com.sbms.service.CompanyService;
import com.sbms.service.ContactService;
import com.sbms.service.NotesService;
import com.sbms.service.RequiredDocumentsService;
import com.sbms.service.UserService;
import com.sbms.service.VisitPlanService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/rest/client")
public class ClientRestController {
    @Resource
    private UserService userService;
    @Resource
    private ClientService clientService;
    @Resource
    private CompanyService companyService;
    @Resource
    private ContactService contactService;
    @Resource
    private RequiredDocumentsService documentsService;
    @Resource
    private BranchService branchService;
    @Resource
    private ClientInventoryService inventoryService;
    @Resource
    private VisitPlanService visitPlanService;
    @Resource
    private NotesService notesService;
    
    @RequestMapping(value = "/addClient", method = RequestMethod.POST)
    public ResponseEntity<Object> saveClient(@RequestBody ClientDTO clientDTO){
     
        User user = userService.get(clientDTO.getCreatedBy());
        Company company = companyService.get(clientDTO.getCompanyId());
        clientDTO.setUser(user);
        clientDTO.setCompany(company);
        clientDTO.setRealId(clientDTO.getRealId());
        Client client = clientService.save(clientDTO.getInstance(clientDTO));        
        ClientDTO cdto = new ClientDTO(client.getId(), client.getClientType(), client.getName(), client.getDescription(),
                 client.getWebsite(), client.getAddress(), client.getEmail(), client.getPhone(), client.getBranch(), clientDTO.getCreatedBy());
         return new ResponseEntity<>(cdto, HttpStatus.OK);
    }
     
    @RequestMapping(value = "/getClient/{companyId}/{createdBy}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClientDTO> listClients(@PathVariable("companyId") Long companyId, @PathVariable("createdBy") Long createdBy) {  
           Company company = companyService.get(companyId);
           List<Client> list = clientService.findByActiveAndCompany(company);
           
           
           List<ClientDTO> clientDTO = new ArrayList<>();
           for(Client client : list){
              ClientDTO cdto = new ClientDTO(client.getId(), client.getClientType(), client.getName(), client.getDescription(), client.getWebsite(),
                      client.getAddress(), client.getEmail(), client.getPhone(), client.getBranch(), createdBy);
              clientDTO.add(cdto);
           }    
          return clientDTO;         
    }  
    
    @GetMapping("/getClientData/{companyId}/{createdBy}")
    ResponseEntity<ClientRequestMapper> getClientData(@PathVariable("companyId") Long companyId, @PathVariable("createdBy") Long createdBy){
      
         User user = userService.getCurrentUser(); 
         List roles  = new ArrayList(user.getUserRoles());
         String role = roles.get(0).toString();
   
          Company company = companyService.get(companyId);
          List<Client> list;
          if(role.equalsIgnoreCase("GLOBAL") || role.equalsIgnoreCase("SUPER_ADMIN") || role.equalsIgnoreCase("ADMIN")){
              
              list = clientService.findByActiveAndCompany(company);
          }
          else{
              
              list = clientService.findByActiveAndUserAndCompany(Boolean.TRUE, user, company);
          }
   
          ClientRequestMapper clientRequestMapper = new ClientRequestMapper();
          
          List<BranchDTO> branchDTOs = new ArrayList<>();
          List<ClientInventoryDTO> clientInventoryDTO = new ArrayList<>();
          List<ContactDTO> contactDTO = new ArrayList<>();
          List<ClientDTO> clientDTO = new ArrayList<>();
          List<ProcurementDocsDTO> procDTO = new ArrayList<>();
           for(Client client : list){
              ClientDTO cdto = new ClientDTO(client.getId(), client.getClientType(), client.getName(), client.getDescription(), client.getWebsite(),
                      client.getAddress(), client.getEmail(), client.getPhone(), client.getBranch(), createdBy);
              clientDTO.add(cdto);
           } 
          for(Client client : list){             
              for(Branch branch : client.getBranches()){
                    BranchDTO bdto = new BranchDTO(branch.getId(), branch.getName(), branch.getDescription(), branch.getAddress(), branch.getClient().getId(), createdBy);
                    branchDTOs.add(bdto);
              }  
              
           for(ClientInventory ci : client.getClientInventorys()){
              ClientInventoryDTO dTO = new ClientInventoryDTO(ci.getId(), ci.getModel(), ci.getQuantity(), ci.getNeedMaintenence(),
                      ci.getTonerType(), ci.getDescription(), ci.getCategory()==null ? 1 : ci.getCategory().getId(),ci.getClient().getId(), createdBy);
              clientInventoryDTO.add(dTO);
           }
          
           for(Contact c : client.getContact()){
              ContactDTO cdto = new ContactDTO(c.getId(), c.getFirstName(), c.getLastName(), c.getGender(), c.getJobPosition(),
                      c.getDepartment(), c.getOfficePhone(), c.getMobilePhone(), c.getEmail(), c.getClient().getId(), createdBy);
                contactDTO.add(cdto);
           } 
          
           RequiredDocuments rd = client.getRequiredDocuments();
           if(rd!=null){
               ProcurementDocsDTO dTO = new ProcurementDocsDTO(rd.getId(), rd.getApplicationLetter(), rd.getCompanyProfile(),
                       rd.getCertOfIncorporation(), rd.getMou(), rd.getCrFourteen(), rd.getVat(), rd.getItf(), rd.getTradeLicense(),
                       rd.getTraceableReference(), rd.getOther(), rd.getClient().getId(), createdBy);
                       procDTO.add(dTO);
              }            
           } 
          clientRequestMapper.setBranchList(branchDTOs);
          clientRequestMapper.setInventoryList(clientInventoryDTO);
          clientRequestMapper.setContactList(contactDTO);
          clientRequestMapper.setClientList(clientDTO);
          clientRequestMapper.setProcurementList(procDTO);
          return new ResponseEntity<>(clientRequestMapper, HttpStatus.OK);
          
          
    }
    
    @PostMapping("/save_client/{company}") 
    ResponseEntity<ClientDataMapper> clientData(@RequestBody ClientDataMapper clientMapper, @PathVariable("company") Long id){
           System.err.println("==============");
           System.err.println("Company Id "+id);
           System.err.println("==============");
          Company company = companyService.get(id);
          
        
          ClientDTO clientDTO = clientMapper.getClient();
          ProcurementDocsDTO pdd = clientMapper.getDocument();
          List<ContactDTO> contactList = clientMapper.getContactList();
          List<BranchDTO> branchList = clientMapper.getBranchList();
          List<ClientInventoryDTO> clientInventoryList = clientMapper.getClientInventoryList();
          List<VisitPlanDTO> visitPlanList = clientMapper.getVisitPlanList();
          List<NotesDTO> notesList = clientMapper.getNoteList();
          
         
            clientDTO.setCompany(company);
            clientDTO.setRealId(clientDTO.getRealId());
            Client client = clientService.save(clientDTO.getInstance(clientDTO));
            
             ClientDTO cdto = new ClientDTO(clientDTO.getId(), client.getId());
             
             //Save Contacts and Return Contact id
             List<ContactDTO> responseContacts = new ArrayList<>();
             contactList.forEach(con->{
                 con.setClient(client);              
                 Contact contact = contactService.save(con.getInstance(con));
                 ContactDTO contactDTO = new ContactDTO(con.getId(), contact.getId(), client.getId());
                 responseContacts.add(contactDTO);
             });
             
             //Save ProcurementDocuments and Return id
              ProcurementDocsDTO pddto = null;
              
             if(pdd!=null){
                //  RequiredDocuments r = documentsService.findByClient(pdd.getClientId());
                //  if(r==null){
                    pdd.setClient(client);
                    RequiredDocuments rd = documentsService.save(pdd.getInstance(pdd)); 
                     pddto = new ProcurementDocsDTO(pdd.getId(), rd.getId(), client.getId());
                 // }
             } 
              //Save Branches and Return id
               List<BranchDTO> responseBranchs = new ArrayList<>();
               branchList.forEach(b->{
                   b.setClient(client);
                   Branch branch = branchService.save(b.getInstance(b));
                   BranchDTO bdto = new BranchDTO(b.getId(), branch.getId(), client.getId());
                   responseBranchs.add(bdto);
               });
               
               //Save ClientInventory
               List<ClientInventoryDTO> responseClientInv = new ArrayList<>();
               clientInventoryList.forEach(ci->{
                    ci.setClient(client);
                    ClientInventory clientIn = inventoryService.save(ci.getInstance(ci)); 
                    ClientInventoryDTO inventoryDTO = new ClientInventoryDTO(ci.getId(), clientIn.getId(), client.getId());
                    responseClientInv.add(inventoryDTO);
               });
               
               List<NotesDTO> responseNotes = new ArrayList<>();
               notesList.forEach((t) -> {
                   t.setClient(client);
                   Notes n = notesService.save(t.getInstance(t));
                   NotesDTO notesDTO = new NotesDTO(t.getId(), n.getId(), client.getId());
                   responseNotes.add(notesDTO);
               });
               
               ClientDataMapper cdm = new ClientDataMapper();
               cdm.setClient(cdto);
               cdm.setBranchList(responseBranchs);
               cdm.setClientInventoryList(responseClientInv);
               cdm.setContactList(responseContacts);
               cdm.setDocument(pddto);
               cdm.setNoteList(responseNotes);
               cdm.setVisitPlanList(visitPlans(client, visitPlanList));
               return new ResponseEntity<>(cdm, HttpStatus.OK);        
    }
    
    @PostMapping("/save_clientx/{company}") 
    ResponseEntity<ClientDataMapperX> clientDataX(@RequestBody ClientDataMapperX clientMapper, @PathVariable("company") Long id){
         // Company company = companyService.get(id);
         System.err.println("==========");
                 System.err.println("Inside ClientDataMapperX");
                 System.err.println("==========");
          
          List<ContactDTO> contactList = clientMapper.getContactList();
          List<BranchDTO> branchList = clientMapper.getBranchList();
          List<ClientInventoryDTO> clientInventoryList = clientMapper.getClientInventoryList();
          List<VisitPlanDTO> visitPlanList = clientMapper.getVisitPlanList();
          List<NotesDTO> notesList = clientMapper.getNoteList();
          
          List<ContactDTO> responseContacts = new ArrayList<>();
             contactList.forEach(con->{
                 System.err.println("==========");
                 System.err.println(con.getFirstName());
                 System.err.println("==========");
                 Client client = clientService.get(con.getClientId());
                 con.setClient(client);              
                 Contact contact = contactService.save(con.getInstance(con));
                 ContactDTO contactDTO = new ContactDTO(con.getId(), contact.getId(), client.getId());
                 responseContacts.add(contactDTO);
             });
           
             
             
               List<BranchDTO> responseBranchs = new ArrayList<>();
               branchList.forEach(b->{
                   Client client = clientService.get(b.getClientId());
                   b.setClient(client);
                   Branch branch = branchService.save(b.getInstance(b));
                   BranchDTO bdto = new BranchDTO(b.getId(), branch.getId(), client.getId());
                   responseBranchs.add(bdto);
               });
               
                //Save ClientInventory
               List<ClientInventoryDTO> responseClientInv = new ArrayList<>();
               clientInventoryList.forEach(ci->{
                    Client client = clientService.get(ci.getClientId());
                    ci.setClient(client);
                    ClientInventory clientIn = inventoryService.save(ci.getInstance(ci)); 
                    ClientInventoryDTO inventoryDTO = new ClientInventoryDTO(ci.getId(), clientIn.getId(), client.getId());
                    responseClientInv.add(inventoryDTO);
               });
               
               List<NotesDTO> responseNotes = new ArrayList<>();
               notesList.forEach((t) -> {
                   Client client = clientService.get(t.getClientId());
                   t.setClient(client);
                   Notes n = notesService.save(t.getInstance(t));
                   NotesDTO notesDTO = new NotesDTO(t.getId(), n.getId(), client.getId());
                   responseNotes.add(notesDTO);
               });
               
               ClientDataMapperX cdm = new ClientDataMapperX();
              
               cdm.setBranchList(responseBranchs);
               cdm.setClientInventoryList(responseClientInv);
               cdm.setContactList(responseContacts);
               cdm.setNoteList(responseNotes);
               cdm.setVisitPlanList(visitPlansX(visitPlanList));
               return new ResponseEntity<>(cdm, HttpStatus.OK);        
          
    }
    
    public List<VisitPlanDTO> visitPlans(Client client, List<VisitPlanDTO> vl){
        List<VisitPlanDTO> responseVisitPlans = new ArrayList<>();
        
            vl.forEach(v->{
                          
                     v.setClient(client);
                     VisitPlan plan = visitPlanService.save(v.getInstance(v));
                    VisitPlanDTO planDTO = new VisitPlanDTO(v.getId(), plan.getId(), client.getId());
                    responseVisitPlans.add(planDTO);

               
            });

      
       return responseVisitPlans;
        
    }
     public List<VisitPlanDTO> visitPlansX(List<VisitPlanDTO> vl){
        List<VisitPlanDTO> responseVisitPlans = new ArrayList<>();
        
            vl.forEach(v->{
                     Client client = clientService.get(v.getClientId());      
                     v.setClient(client);
                     VisitPlan plan = visitPlanService.save(v.getInstance(v));
                    VisitPlanDTO planDTO = new VisitPlanDTO(v.getId(), plan.getId(), client.getId());
                    responseVisitPlans.add(planDTO);
             
            });

      
       return responseVisitPlans;
        
    }

}
