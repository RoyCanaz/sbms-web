/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author user
 */
public class DateUtil {
    public static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      public static DateFormat dfmt = new SimpleDateFormat("yyyy-MM-dd");
    
    
    public static String currentDate(){
        String requiredDate = df.format(new Date());
        return requiredDate;
    }
    public static String currentDate(Date date){
        String requiredDate = df.format(date);
        return requiredDate;
    }
     public static Date formatStringToDate(String date) throws ParseException{
       Date dat = dfmt.parse(date);
        return dat;
    }
}
