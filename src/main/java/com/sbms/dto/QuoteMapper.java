/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

/**
 *
 * @author user
 */
public class QuoteMapper {
    QuoteDTO quote;
    List<QuoteItemDTO> quoteItem;
   

    public QuoteMapper() {
         super();
    }

    public QuoteMapper(QuoteDTO quote, List<QuoteItemDTO> quoteItem) {
        this.quote = quote;
        this.quoteItem = quoteItem;
    }
    
    

    public QuoteDTO getQuote() {
        return quote;
    }

    public void setQuote(QuoteDTO quote) {
        this.quote = quote;
    }

    public List<QuoteItemDTO> getQuoteItem() {
        return quoteItem;
    }

    public void setQuoteItem(List<QuoteItemDTO> quoteItem) {
        this.quoteItem = quoteItem;
    }

  
}
