/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.validator;

import com.sbms.domain.Client;
import com.sbms.service.ClientService;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author user
 */
@Component
public class ClientValidator implements Validator {
    @Resource
    private ClientService clientService;
    @Override
    public boolean supports(Class<?> type) {
        return Client.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Client client = (Client)o;
        Client old = null;
        ValidationUtils.rejectIfEmpty(errors, "clientType", "field.empty");
        ValidationUtils.rejectIfEmpty(errors, "name", "field.empty");
        ValidationUtils.rejectIfEmpty(errors, "address", "field.empty");
        ValidationUtils.rejectIfEmpty(errors, "branch", "field.empty");
     /*   if(client.getClientType() == null){
          errors.rejectValue("clientType", "field.empty");
        }*/
        if (client.getId() != null) {
            old = clientService.get(client.getId());
        }
      /*  if (clientService.checkDuplicate(client, old)) {
            errors.rejectValue("name", "item.duplicate");
        }
*/
    }
}
