/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "specification")
public class Specification implements Serializable {
        
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", updatable = false, nullable = false)
        private Long id;
        
        private String display;
        
        private String processor;
        private String memory;
        private String hardDrive;
          
        private String os;
        private String compatibility;
        
        private String monitorSize;
        private String resolution;
        private String videoInput;
        
        private String catridge;
        private String color;
        private String dutyCycle;
        private String duplex;
        private String scanner;
        private String ethernet;
        private String wireless;
        private String fax;
        
        //@Transient
        //private String fullDescription;
        @JsonIgnore
        @OneToOne(mappedBy = "specification")
        private Product product;

        public Specification() {
        }
        
        public Specification(Long id, String display, String processor, String memory, String hardDrive, String os, String compatibility, String monitorSize, String resolution, String videoInput, String catridge, String color, String dutyCycle, String duplex, String scanner, String ethernet, String wireless, String fax, Product product) {
        this.id = id;
        this.display = display;
        this.processor = processor;
        this.memory = memory;
        this.hardDrive = hardDrive;
        this.os = os;
        this.compatibility = compatibility;
        this.monitorSize = monitorSize;
        this.resolution = resolution;
        this.videoInput = videoInput;
        this.catridge = catridge;
        this.color = color;
        this.dutyCycle = dutyCycle;
        this.duplex = duplex;
        this.scanner = scanner;
        this.ethernet = ethernet;
        this.wireless = wireless;
        this.fax = fax;
        this.product = product;
    }
  
        public Specification(String display, String processor, String memory, String hardDrive, String os, String compatibility, String monitorSize, String resolution, String videoInput, String catridge, String color, String dutyCycle, String duplex, String scanner, String ethernet, String wireless, String fax) {
        this.display = display;
        this.processor = processor;
        this.memory = memory;
        this.hardDrive = hardDrive;
        this.os = os;
        this.compatibility = compatibility;
        this.monitorSize = monitorSize;
        this.resolution = resolution;
        this.videoInput = videoInput;
        this.catridge = catridge;
        this.color = color;
        this.dutyCycle = dutyCycle;
        this.duplex = duplex;
        this.scanner = scanner;
        this.ethernet = ethernet;
        this.wireless = wireless;
        this.fax = fax;
        
    }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getHardDrive() {
        return hardDrive;
    }

    public void setHardDrive(String hardDrive) {
        this.hardDrive = hardDrive;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(String compatibility) {
        this.compatibility = compatibility;
    }

    public String getMonitorSize() {
        return monitorSize;
    }

    public void setMonitorSize(String monitorSize) {
        this.monitorSize = monitorSize;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getVideoInput() {
        return videoInput;
    }

    public void setVideoInput(String videoInput) {
        this.videoInput = videoInput;
    }

    public String getCatridge() {
        return catridge;
    }

    public void setCatridge(String catridge) {
        this.catridge = catridge;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDutyCycle() {
        return dutyCycle;
    }

    public void setDutyCycle(String dutyCycle) {
        this.dutyCycle = dutyCycle;
    }

    public String getDuplex() {
        return duplex;
    }

    public void setDuplex(String duplex) {
        this.duplex = duplex;
    }

    public String getScanner() {
        return scanner;
    }

    public void setScanner(String scanner) {
        this.scanner = scanner;
    }

    public String getEthernet() {
        return ethernet;
    }

    public void setEthernet(String ethernet) {
        this.ethernet = ethernet;
    }

    public String getWireless() {
        return wireless;
    }

    public void setWireless(String wireless) {
        this.wireless = wireless;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
/*
    public String getFullDescription() {
        
        
        return fullDescription;
    }

    @Override
    public String toString() {
        return "Specification{" + "id=" + id + ", display=" + display + ", processor=" + processor + ", memory=" + memory + ", hardDrive=" + hardDrive + ", os=" + os + ", compatibility=" + compatibility + ", monitorSize=" + monitorSize + ", resolution=" + resolution + ", videoInput=" + videoInput + ", catridge=" + catridge + ", color=" + color + ", dutyCycle=" + dutyCycle + ", duplex=" + duplex + ", scanner=" + scanner + ", ethernet=" + ethernet + ", wireless=" + wireless + ", fax=" + fax + ", fullDescription=" + fullDescription + ", product=" + product + '}';
    }
    
  */      
    
       
       
}
