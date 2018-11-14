/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.restmobile;

import com.sbms.domain.Branch;
import com.sbms.domain.Category;
import com.sbms.domain.Client;
import com.sbms.domain.ClientInventory;
import com.sbms.domain.User;
import com.sbms.dto.BranchDTO;
import com.sbms.dto.ClientInventoryDTO;
import com.sbms.service.CategoryService;
import com.sbms.service.ClientInventoryService;
import com.sbms.service.ClientService;
import com.sbms.service.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author roy
 */

@RestController
@RequestMapping("/rest/client")
public class ClientInventoryRestController {
    
    @Resource
    private UserService userService;
    @Resource
    private ClientInventoryService cis;
    @Resource
    private ClientService clientService;
    @Resource
    private CategoryService categoryService;
    
    
    @RequestMapping(value = "/clientInventory", method = RequestMethod.POST)
    public ResponseEntity<Object> saveClient(@RequestBody ClientInventoryDTO cidto){
        Client client = clientService.get(cidto.getClientId());
        Category c = categoryService.get(cidto.getCategory());
        User user = userService.get(cidto.getCreatedBy());
        cidto.setUser(user);
        cidto.setClient(client);
        cidto.setCategories(c);
        ClientInventory clientIn = cis.save(cidto.getInstance(cidto));       
        ClientInventoryDTO inventoryDTO = new ClientInventoryDTO(clientIn.getId(), clientIn.getModel(), clientIn.getQuantity(), clientIn.getNeedMaintenence(),
                clientIn.getTonerType(), clientIn.getDescription(), cidto.getCategory(), cidto.getClientId(), cidto.getCreatedBy());
        
         return new ResponseEntity<>(inventoryDTO, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/getClientInventory/{createdBy}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClientInventoryDTO> listClients(@PathVariable("createdBy") Long createdBy) {         
           List<ClientInventory> list = cis.getCreatedBy(createdBy);
           List<ClientInventoryDTO> clientInventoryDTO = new ArrayList<>();
           for(ClientInventory ci : list){
              ClientInventoryDTO dTO = new ClientInventoryDTO(ci.getId(), ci.getModel(), ci.getQuantity(), ci.getNeedMaintenence(),
                      ci.getTonerType(), ci.getDescription(), ci.getCategory()==null ? 1 : ci.getCategory().getId(),ci.getClient().getId(), createdBy);
              clientInventoryDTO.add(dTO);
           }    
          return clientInventoryDTO;         
    } 
    
}
