/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.restmobile;

import com.sbms.domain.Branch;
import com.sbms.domain.Client;
import com.sbms.domain.User;
import com.sbms.dto.BranchDTO;
import com.sbms.service.BranchService;
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
 * @author Roy
 */

@RestController
@RequestMapping("/rest/client")
public class BranchRestController {
    @Resource
    private UserService userService;
    @Resource
    private ClientService clientService;
    @Resource
    private BranchService branchService;
    
    @RequestMapping(value = "/branch", method = RequestMethod.POST)
    public ResponseEntity<Object> saveBranch(@RequestBody BranchDTO branchDTO){
        User user = userService.get(branchDTO.getCreatedBy());
        Client client = clientService.get(branchDTO.getClientId());
        branchDTO.setClient(client);
        branchDTO.setUser(user);
        Branch branch = branchService.save(branchDTO.getInstance(branchDTO));
        BranchDTO bdto = new BranchDTO(branch.getId(), branch.getName(), branch.getDescription(), branch.getAddress(), branchDTO.getClientId(), branchDTO.getCreatedBy());     
        return new ResponseEntity<>(bdto, HttpStatus.OK);        
    }
    @RequestMapping(value = "/getBranch/{createdBy}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BranchDTO> listClients(@PathVariable("createdBy") Long createdBy) {         
           List<Branch> list = branchService.getCreatedBy(createdBy);
           List<BranchDTO> branchDTO = new ArrayList<>();
           for(Branch branch : list){
              BranchDTO bdto = new BranchDTO(branch.getId(), branch.getName(), branch.getDescription(), branch.getAddress(), branch.getClient().getId(), createdBy);
                  branchDTO.add(bdto);
           }    
          return branchDTO;         
    }  
    
}
