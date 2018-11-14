/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.repository;

import com.sbms.domain.Category;
import com.sbms.domain.Stock;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user
 */
public interface StockRepo extends JpaRepository<Stock, Long> {
    List<Stock> findByProductId(Long id);
    List<Stock> findByAvailable(Boolean available);
    List<Stock> findByAvailableAndCategory(Boolean available, Category category);
    List<Stock> findByCategory(Category category);
}
