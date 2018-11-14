/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.utilities;

import com.sbms.domain.Specification;

/**
 *
 * @author user
 */

public class SpecificationsUtil {
    public static String setDescription(Specification s){
       
        String display = s.getDisplay()==null || s.getDisplay().equals("") ? "" : s.getDisplay()+" Display, ";
        
        String processor = s.getProcessor()== null ? "" : s.getProcessor()+", ";
         String memory  = s.getMemory()== null ? "" : s.getMemory()+" Memory, ";
         String hardDrive  = s.getHardDrive()==null? "" : s.getHardDrive()+" Harddrive, ";
          
         String os  = s.getOs()==null ? "" : s.getOs()+", ";
         String compatibility  = s.getCompatibility()==null? "" : s.getCompatibility()+", ";
        
         String monitorSize  = s.getMonitorSize()==null || s.getMonitorSize().equals("") ? "" : s.getMonitorSize()+" Size, ";
         String resolution = s.getResolution()==null || s.getResolution().equals("") ? "" : s.getResolution()+" Resolution, ";
         String videoInput  = s.getVideoInput()==null || s.getVideoInput().equals("")? "" : s.getVideoInput()+" Video Input, ";
        
         String catridge  = s.getCatridge()==null? "" : s.getCatridge()+" Catridge, ";
         String color  = s.getColor()==null? "" : s.getColor()+" Color, ";
         String dutyCycle  = s.getDutyCycle()==null? "" : s.getDutyCycle()+" DutyCycle, ";
         String duplex  = s.getDuplex()==null? "" : "Duplex, ";
         String scanner  = s.getScanner()==null? "" : "Scanner, ";
         String ethernet  = s.getEthernet()==null? "" : "Ethernet, ";
         String wireless  = s.getWireless()==null? "" : "Wireless, ";
         String fax  = s.getFax()==null? "" : "Fax, ";
         return display.concat(processor).concat(memory).concat(hardDrive).concat(os).concat(compatibility).concat(monitorSize)
                 .concat(resolution).concat(videoInput).concat(catridge).concat(dutyCycle).concat(color).concat(duplex).concat(scanner).concat(ethernet)
                 .concat(wireless).concat(fax);
        
    }
}
