/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service.impl;

import com.sbms.domain.Category;
import com.sbms.domain.Stock;
import com.sbms.repository.StockRepo;
import com.sbms.service.StockService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Repository
public class StockServiceImpl implements StockService{
    @Resource
    private StockRepo stockRepo;

    @Override
    public List<Stock> getAll() {
        return stockRepo.findAll();
    }

    @Override
    public Stock get(Long id) {
        return stockRepo.getOne(id);
    }

    @Override
    public void delete(Stock t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Stock> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Stock save(Stock t) {
        return stockRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(Stock current, Stock old) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Stock> getCreatedBy(Long createdBy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Stock> getAllByProductId(Long id) {
        return stockRepo.findByProductId(id);
    }

    @Override
    public List<Stock> getByAvailability(Boolean available) {
        return stockRepo.findByAvailable(available);
    }

    @Override
    public List<Stock> getByAvailabilityAndCategory(Category category) {
        return stockRepo.findByAvailableAndCategory(Boolean.TRUE, category);
    }

    @Override
    public List<Stock> getByCategory(Category category) {
        return stockRepo.findByCategory(category);
    }
}
