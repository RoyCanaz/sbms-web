/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.utilities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class PageLists {
    public static List<String> getComputerBrands(){
        List<String> list = new ArrayList<String>();
        list.add("HP");
        list.add("Lenovo");
        list.add("Acer");
        list.add("PackardBell");
        list.add("Samsung");
        list.add("Apple");
        list.add("Microsoft");
        list.add("Asus");
        list.add("Dell");
        list.add("Toshiba");
        list.add("Sony");
        list.add("Compaq");
        list.add("Fujitsu");
        return list;
    }
    public static List<String> getWarrant(){
        List<String> list = new ArrayList<String>();
        list.add("No Warranty");
        list.add("6 Months");
        list.add("1 Year");
        list.add("2 Years");
        list.add("3 Years");
        return list;
    }
     public static List<String> getProcessor(){
        List<String> list = new ArrayList<String>();
        list.add("Dual Core");
        list.add("Core i3");
        list.add("Core i5");
        list.add("Core i7");
        list.add("Core i9");
        return list;
    }
      public static List<String> getMemory(){
        List<String> list = new ArrayList<String>();
        list.add("2 GB");
        list.add("4 GB");
        list.add("6 GB");
        list.add("8 GB");
        list.add("12 GB");
        list.add("16 GB");
        return list;
    }
       public static List<String> getHardDrive(){
        List<String> list = new ArrayList<String>();
        list.add("50 GB");
        list.add("100 GB");
        list.add("250 GB");
        list.add("300 GB");
        list.add("500 GB");
        list.add("1 TB");
        list.add("2 TB");       
        return list;
    }
     public static List<String> getFactor(){
        List<String> list = new ArrayList<String>();
        list.add("1.3");
        list.add("1.5");
        list.add("1.7");              
        return list;
    }
    public static List<String> getYesNo(){
        List<String> list = new ArrayList<String>();
        list.add("Yes");
        list.add("No");              
        return list;
    }
    public static List<String> getCatridge(){
        List<String> list = new ArrayList<String>();
        list.add("Toner");
        list.add("Ink");              
        return list;
    }
     public static List<String> getColor(){
        List<String> list = new ArrayList<String>();
        list.add("Black");
        list.add("Colour");              
        return list;
    }
}
