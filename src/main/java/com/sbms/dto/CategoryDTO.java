/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.dto;

import com.sbms.domain.Category;
import com.sbms.domain.Company;
import java.io.Serializable;

/**
 *
 * @author user
 */
public class CategoryDTO implements Serializable{
    private Long id;
    private String name;
    private String description;

    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
     
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    public Category getInstance(CategoryDTO categoryDTO){
        Category c =  new Category(categoryDTO.getName(), categoryDTO.getDescription());
        return c;
    }

   
}
