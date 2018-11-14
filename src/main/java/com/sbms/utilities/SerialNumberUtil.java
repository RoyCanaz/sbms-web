/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.utilities;

import com.sbms.domain.Invoice;
import com.sbms.domain.Product;
import com.sbms.domain.Qoute;
import com.sbms.domain.Stock;

/**
 *
 * @author user
 */
public class SerialNumberUtil {
    public static String getSerialNumbers(Invoice i, Qoute q, Product p){
        String sn = "";
         for(Invoice j : q.getInvoice()){
                     if(j==i){
                         for(Stock s : j.getInvoiceStock()){
                             if(s.getProduct()==p){
                                 sn += s.getSerialNumber()+", ";
                             }
                         }
                     }
                 }
         if(sn.length()<3){
             return " ";
         }
         return "Serial Number(s) : ["+sn+"]";
    }
}
