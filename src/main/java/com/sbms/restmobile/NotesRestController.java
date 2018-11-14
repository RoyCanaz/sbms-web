/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.restmobile;

import com.sbms.domain.Client;
import com.sbms.domain.Contact;
import com.sbms.domain.Notes;
import com.sbms.domain.User;
import com.sbms.dto.NotesDTO;
import com.sbms.service.ClientService;
import com.sbms.service.NotesService;
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
 * @author user
 */
@RestController
@RequestMapping("/rest/client")
public class NotesRestController {
    @Resource
    private UserService userService;
    @Resource
    private NotesService notesService;
    @Resource
    private ClientService clientService;
    
    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    public ResponseEntity<NotesDTO> saveNotes(@RequestBody NotesDTO notesDTO){
        User user = userService.get(notesDTO.getCreatedBy());
        Client client = clientService.get(notesDTO.getClientId());
        notesDTO.setClient(client);
        notesDTO.setUser(user);
        Notes n = notesService.save(notesDTO.getInstance(notesDTO));
        NotesDTO dTO = new NotesDTO(n.getId(), n.getNote(), notesDTO.getClientId(), notesDTO.getCreatedBy());
        return new ResponseEntity<>(dTO, HttpStatus.OK);  
        
    }
    
    @RequestMapping(value = "/getNotes/{createdBy}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NotesDTO> listContacts(@PathVariable("createdBy") Long createdBy) {         
           List<Notes> list = notesService.getCreatedBy(createdBy);
           List<NotesDTO> notesDTO = new ArrayList<>();
           for(Notes notes : list){
              NotesDTO dTO = new NotesDTO(notes.getId(), notes.getNote(), notes.getClient().getId(), createdBy);
              notesDTO.add(dTO);
           }    
          return notesDTO;         
    } 
    
    
    
    
}
