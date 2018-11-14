/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service;

import com.sbms.domain.Client;
import com.sbms.domain.Company;
import com.sbms.domain.User;
import java.util.List;

/**
 *
 * @author user
 */
public interface ClientService extends GenericNameService<Client>{
    List<Client> findByActiveAndCompany(Company company);
    List<Client> findByActiveAndUserAndCompany(Boolean active, User user, Company company);
}
