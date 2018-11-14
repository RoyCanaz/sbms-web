/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service;

import com.sbms.domain.BankingDetail;
import com.sbms.domain.Company;
import java.util.List;

/**
 *
 * @author user
 */
public interface BankingDetailService extends GenericService<BankingDetail>  {
    List<BankingDetail> findByCompany(Company company);
    BankingDetail findByActiveAndCompany(Boolean active, Company company);
}
