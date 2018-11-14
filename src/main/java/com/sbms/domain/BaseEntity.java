/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Roy
 */

@MappedSuperclass
abstract public class BaseEntity implements Serializable{
    
  /*  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    private String uuid;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)     
    private User modifiedBy;
  
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    
    @Temporal(TemporalType.DATE)
    private Date dateModified;
  
    @Version
    private Long version;
    private Boolean active = Boolean.TRUE;
    private Boolean deleted = Boolean.FALSE;

    
    public BaseEntity(){
        
    }

    public BaseEntity(Long id, User createdBy) {
        this.id = id;
        this.createdBy = createdBy;
    }
    
    
    public BaseEntity(User createdBy, User modifiedBy, Date dateCreated, Date dateModified){
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }
    public BaseEntity(Boolean active){
        this.active = active;
    }
    
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
    
    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (! (obj instanceof BaseEntity)) {
            return false;
        }
        return this.getId().equals(((BaseEntity)obj).getId());
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
      
    }

}
