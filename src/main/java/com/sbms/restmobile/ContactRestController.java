/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.restmobile;

import com.sbms.domain.Client;
import com.sbms.domain.ClientInventory;
import com.sbms.domain.Contact;
import com.sbms.domain.User;
import com.sbms.dto.ClientInventoryDTO;
import com.sbms.dto.ContactDTO;
import com.sbms.service.ClientService;
import com.sbms.service.ContactService;
import com.sbms.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class ContactRestController {
    @Resource
    private UserService userService;
    @Resource
    private ContactService contactService;
    @Resource
    private ClientService clientService;
    
    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public ResponseEntity<Object> saveContact(@RequestBody ContactDTO contactDTO){
        User user = userService.get(contactDTO.getCreatedBy());
        Client client = clientService.get(contactDTO.getClientId());
        contactDTO.setClient(client);
        contactDTO.setUser(user);
        Contact contact = contactService.save(contactDTO.getInstance(contactDTO));
        ContactDTO c = new ContactDTO(contact.getId(), contact.getFirstName(), contact.getLastName(), contact.getGender(), contact.getJobPosition(),
                contact.getDepartment(), contact.getOfficePhone(), contact.getMobilePhone(), contact.getEmail(), contactDTO.getClientId(), contact.getCreatedBy().getId());
               return new ResponseEntity<>(c, HttpStatus.OK);        
    }
    
    @RequestMapping(value = "/getContact/{createdBy}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ContactDTO> listContacts(@PathVariable("createdBy") Long createdBy) {         
           List<Contact> list = contactService.getCreatedBy(createdBy);
           List<ContactDTO> contactDTO = new ArrayList<>();
           for(Contact c : list){
              ContactDTO cdto = new ContactDTO(c.getId(), c.getFirstName(), c.getLastName(), c.getGender(), c.getJobPosition(),
                      c.getDepartment(), c.getOfficePhone(), c.getMobilePhone(), c.getEmail(), c.getClient().getId(), createdBy);
              contactDTO.add(cdto);
           }    
          return contactDTO;         
    } 
    @GetMapping(value = "/contactByClient/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ContactDTO>> getContactByClient(@PathVariable("clientId") Long clientId){
        Client client = clientService.get(clientId);
        Set<Contact> contacts = client.getContact();
        List<ContactDTO>  contactDTOs = new ArrayList<>();
        for(Contact c : contacts){
            ContactDTO cdto = new ContactDTO(c.getId(), c.getFirstName(), c.getLastName(), c.getGender(), c.getJobPosition(),
                      c.getDepartment(), c.getOfficePhone(), c.getMobilePhone(), c.getEmail(), c.getClient().getId(), c.getCreatedBy().getId());
              contactDTOs.add(cdto);
        }
     return new ResponseEntity<>(contactDTOs, HttpStatus.OK);
    }
    
}
