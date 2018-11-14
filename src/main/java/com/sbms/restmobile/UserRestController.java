/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.restmobile;

import com.sbms.domain.User;
import com.sbms.domain.UserRole;
import com.sbms.dto.UserDTO;
import com.sbms.service.UserService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/rest/client")
public class UserRestController {
    @Resource
    private UserService userService;
    public static String ROLE = null;
    
    @GetMapping("/all")
    public List<User> getAllUsers(){     
        return userService.getAll();
    }   
    /*@PostMapping("/login")
    public ResponseEntity<Object> logClient(@RequestBody UserDTO dTO){
            
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = userService.findByUserName(dTO.getUserName());
        if(user==null){
            
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        if(user.getActive()==false){
           
           return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        if(user.getUserName()==null){
            
             return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        else{
          
            String savedPassword = user.getPassword();
            String enteredPassword = dTO.getPassword();
            if(encoder.matches(enteredPassword, savedPassword)){          
              UserDTO udto = new UserDTO(user.getId(), user.getActive(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getUserName());
                 return new ResponseEntity<>(udto, HttpStatus.OK);
            }
        }
        
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }
    */
   /* @GetMapping("/login")
    public User getUser(@RequestParam("userName") String userName){
        return userService.findByUserName(userName);
    }*/
    
    @GetMapping("/login/{userName}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("userName")  String userName){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      
       
        User user = userService.findByUserName(userName);
        Long companyId = user.getCompany()==null? 0L : user.getCompany().getId();  
        String companyName = user.getCompany()==null? "You Have No Company" : user.getCompany().getCompanyName();
        System.err.println("==============");
        System.err.println("Company Id" +companyId);
        System.err.println("==============");
        Collection c = auth.getAuthorities();
        List list  = new ArrayList(c);
        System.err.println("====================");
        System.err.println("Role "+list.get(0).toString());
        System.err.println("====================");
       
         UserDTO udto = new UserDTO(user.getId(), user.getActive(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getUserName(), companyId, companyName, list.get(0).toString());
         return new ResponseEntity<>(udto, HttpStatus.OK);
    }
    
}
