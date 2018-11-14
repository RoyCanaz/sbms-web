/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.restweb;

import com.sbms.Mapper.QuoteWrapper;
import com.sbms.domain.BankingDetail;
import com.sbms.domain.Client;
import com.sbms.domain.Company;
import com.sbms.domain.Contact;
import com.sbms.domain.Currency;
import com.sbms.domain.Modules;
import com.sbms.domain.Product;
import com.sbms.domain.Qoute;
import com.sbms.domain.QuoteItem;
import com.sbms.emails.SendQuoteEmailService;
import com.sbms.repository.QouteRepository;
import com.sbms.service.BankingDetailService;
import com.sbms.service.ClientService;
import com.sbms.service.ContactService;
import com.sbms.service.CurrencyService;
import com.sbms.service.EmailAccountService;
import com.sbms.service.ProductService;
import com.sbms.service.QouteService;
import com.sbms.service.QuoteItemService;
import com.sbms.service.StockService;
import com.sbms.service.UserService;
import com.sbms.utilities.AppUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kanaz
 */
@RestController
@RequestMapping("/rest/web")
public class WebRestDataController {
    
     @Resource
    private ClientService clientService;
    @Resource
    private QouteService qouteService;
    @Resource
    private QuoteItemService quoteItemService;
    @Resource
    private UserService userService; 
    @Resource
    private SendQuoteEmailService quoteEmailService; 
   @Resource
   private ContactService contactService;
    @Resource
    private BankingDetailService bankingDetailService;
    @Resource 
    private CurrencyService currencyService;
    @Resource
    private StockService stockService;
    @Resource
    private EmailAccountService emailAccountService;
    @Resource
    private ProductService productService;
    
    
    @GetMapping("/quote/currency/{currencyId}")
    public ResponseEntity setCurrency(@PathVariable("currencyId") Long currencyId, HttpSession session){        
        Currency currency = currencyService.get(currencyId);
        session.setAttribute("quoteCurrency", currency); 
        return new ResponseEntity(HttpStatus.OK);       
    } 
      @GetMapping("/quote/edit/currency/{currencyId}")
    public Long setCurrencyEdit(@PathVariable("currencyId") Long currencyId, HttpSession session){        
        Currency currency = currencyService.get(currencyId);
        Long quoteId = (Long) session.getAttribute("editQuoteId");
          Qoute qoute = qouteService.get(quoteId);
          Double total = 0.0;
          for(QuoteItem item : qoute.getQuoteItems()){
              Product product = item.getProduct();
              Double unitPrice = AppUtil.calculateUnitPrice(currency.getRate(), product.getSellingPrice());
              total = total + (unitPrice*item.getQuantity());
              
          }
          Double vat = AppUtil.calculateVat(total);
          Double totalIncVat = AppUtil.calculateTotal(total, vat);
          qoute.setCurrency(currency);
          qoute.setTotal(AppUtil.roundToTwoDecimal(total));
          qoute.setVat(vat);
          qoute.setTotalIncVat(totalIncVat);
          qouteService.save(qoute);
           session.removeAttribute("editQuoteId");
           return quoteId;     
    } 
     @GetMapping("/quote/bank/{bankId}")
    public ResponseEntity setBank(@PathVariable("bankId") Long bankId, HttpSession session){        
        BankingDetail bankingDetail  = bankingDetailService.get(bankId);
        if(bankingDetail!=null){
             session.setAttribute("quoteBank", bankingDetail); 
        }
        return new ResponseEntity(HttpStatus.OK);       
    } 
     @GetMapping("/quote/edit/bank/{bankId}")
    public Long setBankEdit(@PathVariable("bankId") Long bankId, HttpSession session){        
        BankingDetail bankingDetail  = bankingDetailService.get(bankId);
       Long quoteId = (Long) session.getAttribute("editQuoteId");
          Qoute qoute = qouteService.get(quoteId);
          qoute.setBankingDetail(bankingDetail);
          qouteService.save(qoute);
          session.removeAttribute("editQuoteId");
      return quoteId;       
           
    } 
    @GetMapping("/quote/client/{clientId}")
    public ResponseEntity setClient(@PathVariable("clientId") Long clientId, HttpSession session){        
        Client  client = clientService.get(clientId);
        session.setAttribute("quoteContacts", contactService.findByActiveAndClientId(clientId)); 
        session.setAttribute("quoteClient", client); 
        session.removeAttribute("quoteContact");
        return new ResponseEntity(HttpStatus.OK);       
    } 
    @GetMapping("/quote/contact/{contactId}")
    public ResponseEntity setContact(@PathVariable("contactId") Long contactId, HttpSession session){        
        Contact contact = contactService.get(contactId);
        if(contact!=null){
            session.setAttribute("quoteContact", contact); 
        }
        return new ResponseEntity(HttpStatus.OK);       
    } 
      @GetMapping("/quote/edit/contact/{contactId}")
    public Long setContactEdit(@PathVariable("contactId") Long contactId, HttpSession session){        
        Contact contact = contactService.get(contactId);
         Long quoteId = (Long) session.getAttribute("editQuoteId");
          Qoute qoute = qouteService.get(quoteId);
          qoute.setContact(contact);
          qouteService.save(qoute);
          session.removeAttribute("editQuoteId");
        return quoteId;
    } 
      
      @GetMapping("/invoice/currency/{currencyId}")
    public ResponseEntity setInvoiceCurrency(@PathVariable("currencyId") Long currencyId, HttpSession session){        
        Currency currency = currencyService.get(currencyId);
        session.setAttribute("invoiceCurrency", currency); 
        return new ResponseEntity(HttpStatus.OK);       
    } 
      @GetMapping("/invoice/ids")
    public List<QuoteWrapper> getIds(HttpSession session){        
        List<QuoteItem> list = (List<QuoteItem>) session.getAttribute("listInvoiceItems");
        List<QuoteWrapper> quoteWrappers = new ArrayList<>();
         if(list!=null){
        for(QuoteItem item : list){
            QuoteWrapper quoteWrapper = new QuoteWrapper();
            quoteWrapper.setNote(item.getNote());
            quoteWrapper.setProductId(item.getProduct().getId());
            quoteWrappers.add(quoteWrapper);
        }
       return quoteWrappers;
         }
         return null;
     
    } 
     @GetMapping("/quote/ids")
    public List<QuoteWrapper> getQuoteIds(HttpSession session){        
        List<QuoteItem> list = (List<QuoteItem>) session.getAttribute("quoteItems");
        List<QuoteWrapper> quoteWrappers = new ArrayList<>();
        if(list!=null){
            for(QuoteItem item : list){
                QuoteWrapper quoteWrapper = new QuoteWrapper();
                quoteWrapper.setNote(item.getNote());
                quoteWrapper.setProductId(item.getProduct().getId());
                quoteWrappers.add(quoteWrapper);
            }
           return quoteWrappers;
        }
        return null;
     
    } 
     @GetMapping("/invoice/bank/{bankId}")
    public ResponseEntity setInvoiceBank(@PathVariable("bankId") Long bankId, HttpSession session){        
        BankingDetail bankingDetail  = bankingDetailService.get(bankId);
        if(bankingDetail!=null){
             session.setAttribute("invoiceBank", bankingDetail); 
        }
        return new ResponseEntity(HttpStatus.OK);       
    } 
    @GetMapping("/invoice/client/{clientId}")
    public ResponseEntity setInvoiceClient(@PathVariable("clientId") Long clientId, HttpSession session){        
        Client  client = clientService.get(clientId);
        session.setAttribute("invoiceContacts", contactService.findByActiveAndClientId(clientId)); 
        session.setAttribute("invoiceClient", client); 
        session.removeAttribute("invoiceContact");
        return new ResponseEntity(HttpStatus.OK);       
    } 
    @GetMapping("/invoice/contact/{contactId}")
    public ResponseEntity setInvoiceContact(@PathVariable("contactId") Long contactId, HttpSession session){        
        Contact contact = contactService.get(contactId);
        if(contact!=null){
            session.setAttribute("invoiceContact", contact); 
        }
        return new ResponseEntity(HttpStatus.OK);       
    } 
    
    
    
    @PostMapping("/quote/product_id")
   public String addQuoteItems(@RequestBody String ids, HttpSession session){
      
      List<QuoteItem> list = new ArrayList<>();
     
      for(Long id : productId(ids)){
          Product product = productService.get(id);
          QuoteItem qi = new QuoteItem();
          qi.addQuoteItem(product);
          list.add(qi);
      }
       double total = getTotal(list);      
       session.setAttribute("quoteItems", list);
       session.setAttribute("total", total);
       session.setAttribute("listQuoteItems", list);
       return "success";
   }
   @PostMapping("/quote/product_id_note") 
   public String addItems(@RequestBody List<QuoteWrapper> quoteWrapper, HttpSession session){
       List<QuoteItem> list = new ArrayList<>();
       quoteWrapper.forEach(t->{
          Product product = productService.get(t.getProductId());
           QuoteItem qi = new QuoteItem();
           qi.setNote(t.getNote());
           qi.addQuoteItem(product);
          list.add(qi);
       });
       double total = getTotal(list);      
       session.setAttribute("quoteItems", list);
       session.setAttribute("total", total);
       session.setAttribute("listQuoteItems", list);
       return "Success";
   }
   @PostMapping("/quote/edit/product_id_note") 
   public Long addItemsEdit(@RequestBody List<QuoteWrapper> quoteWrapper, HttpSession session){
        Long quoteId = (Long) session.getAttribute("editQuoteId");
        Qoute qoute = qouteService.get(quoteId);
        Double total = qoute.getTotal();
        int numberOfItems = qoute.getNumOfItems();
           for(QuoteWrapper t : quoteWrapper){
          Product product = productService.get(t.getProductId());
           QuoteItem qi = new QuoteItem();
           qi.setNote(t.getNote());
           qi.addQuoteItem(product);
           qi.setQoute(qoute);
           quoteItemService.save(qi);
           total = total + AppUtil.calculateUnitPrice(qoute.getCurrency().getRate(), product.getSellingPrice());
           numberOfItems = numberOfItems + 1;
   }
           
           Double vat = AppUtil.calculateVat(total);
           Double totalIncVat = AppUtil.calculateTotal(total, vat);
           qoute.setTotal(AppUtil.roundToTwoDecimal(total));
           qoute.setVat(vat);
           qoute.setTotalIncVat(totalIncVat);
           qoute.setNumOfItems(numberOfItems);
           qouteService.save(qoute);
          session.removeAttribute("editQuoteId");    
       return quoteId;
   }
   @PostMapping("/invoice/product_id_note") 
   public String addInvoiceItems(@RequestBody List<QuoteWrapper> quoteWrapper, HttpSession session){
       List<QuoteItem> list = new ArrayList<>();
       quoteWrapper.forEach(t->{
          Product product = productService.get(t.getProductId());
           QuoteItem qi = new QuoteItem();
           qi.setNote(t.getNote());
           qi.addQuoteItem(product);
          list.add(qi);
       });
       double total = getTotalInvoice(list, (Currency) session.getAttribute("invoiceCurrency"));      
       session.setAttribute("invoiceTotal", total);
       session.setAttribute("listInvoiceItems", list);
       return "Success";
   }
   
 
   /**
    * 
    * @param ids string from ajax(quote & invoice .js) Ids Selected from product Table
    * @return 
    */
   public List<Long> productId(String ids){
        List<Long> listIds = new ArrayList<>();     
        String[] arrayId = ids.split(",");
 
         for(String id : arrayId){
             Long i = Long.parseLong(id);
             listIds.add(i);
         }
      return listIds;
      
   }
  
   public Double getTotal(List<QuoteItem> quoteItems) {
        Double doubl = new Double(0);
        for (QuoteItem qi : quoteItems) {            
            doubl = doubl + qi.getProduct().getUnitPrice();
        }
        return Precision.round(doubl, 2);
    }
   public Double getTotalInvoice(List<QuoteItem> quoteItems, Currency currency) {
        Double doubl = new Double(0);
        for (QuoteItem qi : quoteItems) {            
            doubl = doubl + (qi.getQuantity() * AppUtil.calculateUnitPrice(currency.getRate(), qi.getProduct().getSellingPrice()));
        }
        return AppUtil.roundToTwoDecimal(doubl);
    }
}

    

