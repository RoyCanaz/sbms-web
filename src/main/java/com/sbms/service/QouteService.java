/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service;

import com.sbms.domain.Company;
import com.sbms.domain.Product;
import com.sbms.domain.Qoute;
import com.sbms.domain.User;
import java.util.List;

/**
 *
 * @author Roy
 */
public interface QouteService extends GenericService<Qoute>{
     List<Qoute> getByClientId(Long id);
     public List<Qoute> findByCompany(Company company);
     List<Qoute> findByCompanyAndUser(Company company, User user);
}
