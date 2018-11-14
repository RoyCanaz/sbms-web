/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.domain;

import com.sbms.utilities.StringUtils;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "brand")

public class Brand extends BaseName{
    private static final long serialVersionUID = 1L;
    @Transient
    private String printName;
  /*  @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;*/
    
    
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "brand_category", joinColumns = {
        @JoinColumn(name = "brand_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "category_id", nullable = false)})
    private Set<Category> categories = new HashSet<>();

    
    
  

    /*  public Category getCategory() {
    return category;
    }
    public void setCategory(Category category) {
    this.category = category;
    }
     */
    public Set<Category> getCategories() {
        return categories;
    }
    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public String getPrintName() {
        return StringUtils.toCamelCase3(super.getName());
    }
    
}
