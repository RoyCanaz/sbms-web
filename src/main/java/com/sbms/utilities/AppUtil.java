/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.utilities;

import com.sbms.domain.Product;
import com.sbms.domain.QuoteItem;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.util.Precision;

/**
 *
 * @author kanaz
 */
public class AppUtil {
    public static double BASEVALUE = 1.15;
      public static double BASEVALUEVAT = 0.15;
    public static Double calculateUnitPrice(Float rate, Double sellingPrice){
        Double retailPrice = rate * sellingPrice;
        Double unitPrice = retailPrice/BASEVALUE;
        Double finalValue = Precision.round(unitPrice, 2);
        return finalValue;
    }
    public static Double calculateVat(Double total){
        Double vat = total * BASEVALUEVAT;
        return roundToTwoDecimal(vat);
    }
    
    public static Double calculateTotal(Double total, Double vat){
          return roundToTwoDecimal(total + vat);
    }
    public static Double roundToTwoDecimal(Double value){
       String roundedValue =  String.format("%.2f", value);
       return Double.parseDouble(roundedValue);
    }
    public static  List<Product> streamProducts(List<Product> products,   List<QuoteItem> list){
        List<Product> newProductsList =  new ArrayList<>();
           for(Product p : products){
           boolean exists = false;
           if (list != null) {
                for(QuoteItem item : list){
                    if(p.getId().equals(item.getProduct().getId())){
                       exists = true; 
                    }
                } 
           }
                if(!exists){
                    newProductsList.add(p);
                }  
       }
           return newProductsList;
    }
}
