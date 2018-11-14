/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.repository;

import com.sbms.domain.Client;
import com.sbms.domain.Company;
import com.sbms.domain.Currency;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author user
 */
public interface CurrencyRepo extends CrudRepository<Currency, Long>{
    @Override
    public List<Currency> findAll();
    List<Currency> findByDeletedAndCompany(Boolean deleted, Company company);
    Currency findByActiveAndCompany(Boolean active, Company company);
}
