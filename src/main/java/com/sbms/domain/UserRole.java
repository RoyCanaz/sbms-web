/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sbms.utilities.StringUtils;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "role")
public class UserRole extends BaseName{
    private static final long serialVersionUID = 1L;
    @Transient
    private String printName;
    @JsonIgnore
    @ManyToMany(mappedBy = "userRoles")
    private Set<User> users = new HashSet<>();

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
    
    public String getPrintName(){
        return StringUtils.toCamelCase3(super.getName()).toUpperCase();
    }
}
