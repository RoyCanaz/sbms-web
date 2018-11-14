/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.service.impl;

import com.sbms.domain.Notes;
import com.sbms.repository.NotesRepository;
import com.sbms.service.NotesService;
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
public class NotesServiceImpl implements NotesService{
    
    @Resource
    private UserService userService;
    
    @Resource
    private NotesRepository notesRepository;
/*
    @Override
    public List<Notes> findByClientId(Long id) {
        return notesRepository.findByClientId(id);
    }
*/
    @Override
    public List<Notes> getAll() {
        return notesRepository.findAll();
    }

    @Override
    public Notes get(Long id) {
        return notesRepository.getOne(id);
    }

    @Override
    public void delete(Notes t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Notes> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Notes save(Notes t) {
        if (t.getId()==null || t.getId()==0) {
              if(t.getCreatedBy()==null){
                     t.setCreatedBy(userService.getCurrentUser());
                     t.setDateCreated(new Date());
                     return notesRepository.save(t);
            }                     
            t.setDateCreated(new Date());
            return notesRepository.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return notesRepository.save(t);
    }

    @Override
    public Boolean checkDuplicate(Notes current, Notes old) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Notes> getCreatedBy(Long createdBy) {
        return notesRepository.findByCreatedById(createdBy);
    }
    
}
