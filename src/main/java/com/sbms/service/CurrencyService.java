/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service;

import com.sbms.domain.Company;
import com.sbms.domain.Currency;
import java.util.List;

/**
 *
 * @author user
 */
public interface CurrencyService extends GenericService<Currency>{
     List<Currency> findByCompany(Company company);
     Currency getByActiveAndCompany(Boolean active, Company company);
}
