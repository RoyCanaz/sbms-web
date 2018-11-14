/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service.impl;

import com.sbms.domain.QuoteItem;
import com.sbms.repository.QuoteItemRepo;
import com.sbms.service.QuoteItemService;
import com.sbms.service.UserService;
import java.util.Date;
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
public class QuoteItemServiceImpl implements QuoteItemService{
    @Resource
    private QuoteItemRepo quoteItemRepo;
    @Resource
    private UserService userService;

    @Override
    public List<QuoteItem> getAll() {
        return quoteItemRepo.findAll();
    }

    @Override
    public QuoteItem get(Long id) {
        return quoteItemRepo.getOne(id);
    }

    @Override
    public void delete(QuoteItem t) {
        quoteItemRepo.delete(t);
    }

    @Override
    public List<QuoteItem> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public QuoteItem save(QuoteItem t) {
        return quoteItemRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(QuoteItem current, QuoteItem old) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<QuoteItem> getCreatedBy(Long createdBy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
