/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service;

import com.sbms.domain.Category;
import com.sbms.domain.Stock;
import java.util.List;

/**
 *
 * @author user
 */
public interface StockService extends GenericService<Stock> {
    List<Stock> getAllByProductId(Long id);
    List<Stock> getByAvailability(Boolean available);
    List<Stock> getByAvailabilityAndCategory(Category category);
    List<Stock> getByCategory(Category category);
}
