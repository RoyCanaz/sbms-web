/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.repository;

import com.sbms.domain.Branch;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author user
 */
public interface BranchRepository extends CrudRepository<Branch, Long>{
    @Override
    public List<Branch> findAll();
    Branch findByName(String name);
    List<Branch> findByCreatedById(Long id);
}
