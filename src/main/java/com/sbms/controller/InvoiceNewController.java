/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.controller;

import com.sbms.domain.BankingDetail;
import com.sbms.domain.Client;
import com.sbms.domain.Company;
import com.sbms.domain.Contact;
import com.sbms.domain.Currency;
import com.sbms.domain.EmailAccount;
import com.sbms.domain.Invoice;
import com.sbms.domain.Product;
import com.sbms.domain.Qoute;
import com.sbms.domain.QuoteItem;
import com.sbms.domain.Stock;
import com.sbms.emails.SendInvoiceEmailService;
import com.sbms.emails.SendQuoteEmailService;
import com.sbms.pdf.GenerateInvoice;
import com.sbms.repository.InvoiceRepo;
import com.sbms.repository.QouteRepository;
import com.sbms.service.BankingDetailService;
import com.sbms.service.BrandService;
import com.sbms.service.CategoryService;
import com.sbms.service.ClientService;
import com.sbms.service.CompanyService;
import com.sbms.service.ContactService;
import com.sbms.service.CurrencyService;
import com.sbms.service.EmailAccountService;
import com.sbms.service.InvoiceService;
import com.sbms.service.ProductService;
import com.sbms.service.QouteService;
import com.sbms.service.QuoteItemService;
import com.sbms.service.StockService;
import com.sbms.service.SupplierService;
import com.sbms.service.UserService;
import com.sbms.utilities.AppUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("invoice")
public class InvoiceNewController {
    @Resource
    private ProductService productService;
    @Resource
    private CompanyService companyService;
    @Resource
    private BrandService brandService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private SupplierService supplierService;
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
    private SendInvoiceEmailService sendInvoiceEmailService; 
      @Resource
    private InvoiceService invoiceService;
    
    @Autowired
    private QouteRepository qouteRepository;
     private static final String TEMP_DIRECTORY = "/temp";
     
     
     
     public String setUpModel(ModelMap model, String title, Company company) {
        model.addAttribute("pageTitle", title);
        model.addAttribute("inventory", productService.findByCompany(company));
        model.addAttribute("clickNewInvoiceList", true);
        model.addAttribute("upActive", "active");
        return "invoice/master";
    }
     public String setUpModel(ModelMap model) {
        model.addAttribute("pageTitle", "Send Invoice");
        model.addAttribute("clickNewInvoiceList", true);
        model.addAttribute("upActive", "active");
        return "invoice/master";
    }
      public void removeSessionAttributes(HttpSession session){
            session.removeAttribute("invoiceClient");
            session.removeAttribute("invoiceContact");
            session.removeAttribute("invoiceBank");
            session.removeAttribute("invoiceCurrency");
            session.removeAttribute("total");
            session.removeAttribute("listInvoiceItems");             
    }
     
     
    @GetMapping("/new")
    public String setInvoiceAttributes(ModelMap model, HttpSession session){
            Company company = (Company) session.getAttribute("company");
        Double total = new Double(0);    
        List<Currency> currencyList = currencyService.findByCompany(company);
        List<Client> clientList = clientService.findByActiveAndCompany(company);
        List<BankingDetail> bankingDetailList = bankingDetailService.findByCompany(company);
        
        List<QuoteItem> list = (List<QuoteItem>) session.getAttribute("listInvoiceItems");
        
        Currency currency = (Currency) session.getAttribute("invoiceCurrency");
          BankingDetail bankingDetail = (BankingDetail) session.getAttribute("invoiceBank");
        Currency c = currency==null?currencyService.getByActiveAndCompany(Boolean.TRUE, company):currency;
       BankingDetail bd = bankingDetail==null?bankingDetailService.findByActiveAndCompany(Boolean.TRUE, company):bankingDetail;
       List<Product> inventory =  new ArrayList<>();
       List<Product> products =  productService.findByCompany(company);
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
                    inventory.add(p);
                }  
       }
              
        if (list != null) {
            for (QuoteItem item : list) {
              
                total = total + AppUtil.calculateUnitPrice(c.getRate(), item.getProduct().getSellingPrice()) * item.getQuantity();
            }
        }
        
        model.addAttribute("invoiceTotal", AppUtil.roundToTwoDecimal(total));
        session.setAttribute("listInvoiceItems", list);
        session.setAttribute("invoiceCurrency", c);
        session.setAttribute("invoiceBank", bd); 
        model.addAttribute("currencyList", currencyList);
        model.addAttribute("clientList", clientList);
        model.addAttribute("bankingDetailList", bankingDetailList);
        model.addAttribute("inventory",inventory);
        return setUpModel(model);
    }
   @GetMapping("/add/{id}")
    public String addQuoteItem(ModelMap model, @PathVariable("id") Long id,  HttpSession session) {
        
        List<QuoteItem> list = (List<QuoteItem>) session.getAttribute("listInvoiceItems");
        if (list == null) {
            list = new ArrayList<>();
        }
        
        Product product = productService.get(id);
        if (product != null) {
                 QuoteItem qi = new QuoteItem();
                qi.setProduct(product);
                qi.setQuantity(1L);
                List<QuoteItem> quoteItems = getNewQuoteList(list, qi);
                session.setAttribute("listInvoiceItems", quoteItems);
         
        }

        return "redirect:/invoice/new";
    }
    @GetMapping("/reduce-quantity/{id}")
    public String reduceQuantity(ModelMap model, @PathVariable("id") Long id, HttpSession session) {
        List<QuoteItem> list = (List<QuoteItem>) session.getAttribute("listInvoiceItems");
        List<QuoteItem> quoteItems = new ArrayList<>();
        if (list != null) {
            for (QuoteItem item : list) {
                if (Objects.equals(item.getProduct().getId(), id)) {
                    Long quantityNumber = item.getQuantity() - 1L;
                     item.setQuantity(quantityNumber);                  
                }
                quoteItems.add(item);
            }
        }
        session.setAttribute("listInvoiceItems", quoteItems); 
        return "redirect:/invoice/new";
    }
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String removeItem(ModelMap model, @PathVariable Long id, HttpSession session) {
        List<QuoteItem> list = (List<QuoteItem>) session.getAttribute("listInvoiceItems");
        if (list != null) {
             List<QuoteItem> quoteItems = removeQuoteItem(list, id);
            session.setAttribute("listInvoiceItems", quoteItems);
        }
        return "redirect:/invoice/new";
    }
    @GetMapping("/send")
    public String sendInvoice(HttpSession session) throws Exception{
        
          Company company = (Company) session.getAttribute("company");
          List<QuoteItem> list = (List<QuoteItem>) session.getAttribute("listInvoiceItems");
           Client client = (Client) session.getAttribute("invoiceClient");
           Contact contactDetails = (Contact) session.getAttribute("invoiceContact");
           BankingDetail bankingDetail = (BankingDetail) session.getAttribute("invoiceBank");      
        //  Double total = (Double) session.getAttribute("invoiceTotal");  
          Currency currency = (Currency) session.getAttribute("invoiceCurrency");
          Qoute qoute = new Qoute();       
          Long invoiceId = null;
          Double tot = 0.0;
            if (!list.isEmpty()) {
            int numberOfItems = 0;
            for (QuoteItem i : list) {
                numberOfItems = numberOfItems + i.getQuantity().intValue();
                Double up = AppUtil.calculateUnitPrice(currency.getRate(), i.getProduct().getSellingPrice());
                tot = tot + (up*i.getQuantity());
                
            }
                System.err.println("============================");
                System.err.println("Inside Total:: "+tot);
                System.err.println("============================");
            
            Double vat = tot * AppUtil.BASEVALUEVAT;
            Double totalIncVat = vat + tot;
            qoute.setQuoteItems(list);
            if(client!=null){
               qoute.setQuoteUuid(createQuoteUuid(client.getId()));
            }
            qoute.setCountNumberOfSent(0);
            qoute.setTotal(AppUtil.roundToTwoDecimal(tot));
            qoute.setVat(AppUtil.roundToTwoDecimal(vat));
            qoute.setTotalIncVat(AppUtil.roundToTwoDecimal(totalIncVat));
            qoute.setNumOfItems(numberOfItems);
            qoute.setCompany(company);
            qoute.setBankingDetail(bankingDetail);
            qoute.setClient(client);
            qoute.setContact(contactDetails);         
            qoute.setCurrency(currency);
            Qoute q = qouteService.save(qoute);
            for (QuoteItem item : list) {
                item.setQoute(q);
                quoteItemService.save(item);
            }
            invoiceId = generateInvoice(q, company, session); 
            }
        
          
          return "redirect:/invoice/success?id="+invoiceId; 
              
          
    }
     @RequestMapping(value = "/edit/send", method = RequestMethod.GET)
    public String editQuoteSend(ModelMap model, @RequestParam("quoteId") Long quoteId,  HttpSession session) throws Exception {
        Qoute qo = qouteService.get(quoteId);
        Long  invoiceId = generateInvoice(qo, qo.getCompany(), session); 
        return "redirect:/invoice/success?id="+invoiceId; 
    }
    @GetMapping("/cancel")
   public String cancelQuote(HttpSession session){
         removeSessionAttributes(session);
         return "redirect:/user/dashboard";
   }
    
   public Long generateInvoice(Qoute qoute, Company company, HttpSession session) throws Exception{  
         Client client = qoute.getClient();
         Invoice invoice = new Invoice();
                 
         invoice.setCountNumberOfSent(1);
         invoice.setClient(client);
         invoice.setInvoiceUuid(createInvoiceUuid(client.getId()));
         invoice.setQoute(qoute);  
          ServletContext context = session.getServletContext();
          String uuid = invoice.getInvoiceUuid();
          File tempDirectory = new File(context.getRealPath(TEMP_DIRECTORY) + "/invoices");
            if (!tempDirectory.exists()) {
                tempDirectory.mkdirs();
            }
         String path = tempDirectory+File.separator+uuid+".pdf";
         GenerateInvoice generateInvoice = new GenerateInvoice();
         generateInvoice.save(invoice, userService.getCurrentUser()==null ? "": userService.getCurrentUser().getDisplayName(), path, context);
         List<EmailAccount> emailCc = emailAccountService.getCc(company);
         List<EmailAccount> emailBcc = emailAccountService.getBcc(company);
         String[] cc = new String[emailCc.size()];
            int index = 0;
            for (EmailAccount ea : emailCc) {
              cc[index] = (String)ea.getEmail();
              index++;
            }
         String[] bcc = new String[emailBcc.size()];
            int in = 0;
            for (EmailAccount account : emailBcc) {
              bcc[in] = (String)account.getEmail();
              index++;
            }
       String companyName = company.getCompanyName() + " -  " + "Invoice";
         sendInvoiceEmailService.sendInvoiceWithAttachment(qoute.getContact().getEmail(), companyName, cc, bcc, uuid, path, company);
        removeSessionAttributes(session);
         List<Stock> invoiceStocks = new ArrayList<>();
         Long quantities = 0L;
          for(QuoteItem item : qoute.getQuoteItems()){
             Product product = productService.get(item.getProduct().getId());
             Long av = product.getAvailableStock();
             Long soldItems = item.getQuantity();
             quantities = quantities+item.getQuantity();
             Long availableStock = av -soldItems;
             product.setAvailableStock(availableStock);
             productService.save(product);
             //Retrive Stock for Sold Product.
             if(product.getStock()!=null){
                    for(int size = 0; size < product.getStock().size(); size++){
                        for(int i = 0; i < item.getQuantity(); i++) {                    
                                   invoiceStocks.add(product.getStock().get(i));                       
                           }
                    }
             }                            
         }       
          for(Stock s : invoiceStocks){
               Stock stock = stockService.get(s.getId());
               stock.setAvailable(false);
               stock.setPurchasedBy(client);
               stock.setDatePurchased(new Date());
               stockService.save(stock);             
         }       
         Invoice i = invoiceService.save(invoice);
         
         return i.getId();
     }
    
    public void updateRetailPrice(Company company, Currency currency) {
        //  Currency currency = currencyService.getByActiveAndCompany(Boolean.TRUE, company);
        if (currency == null) {
            return;
        }

        for (Product product : productService.findByCompany(company)) {
            Double rp = currency.getRate().doubleValue() * product.getSellingPrice();
            Double up = rp / 1.15;
            product.setRetailPrice(Precision.round(rp, 2));
            product.setUnitPrice(Precision.round(up, 2));
            productService.save(product);

        }

    }
    public String createQuoteUuid(Long id) {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);     
        Long maxCount = qouteRepository.countByClientId(id) == null ? 1L : qouteRepository.countByClientId(id) + 1L;
        String quoteUuid = String.valueOf(year) + String.valueOf(month) + String.valueOf(day) + String.valueOf(id)+"0" + String.valueOf(maxCount);
        return quoteUuid;
    }
    public String createInvoiceUuid(Long id) {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);     
        Long maxCount = qouteRepository.countByClientId(id) == null ? 1L : qouteRepository.countByClientId(id);
        String quoteUuid = "#"+String.valueOf(year) + String.valueOf(month) + String.valueOf(day) + String.valueOf(id)+"0" + String.valueOf(maxCount);
        return quoteUuid;
    }
    
    
    public List<QuoteItem> getNewQuoteList(List<QuoteItem> quoteItems, QuoteItem item){
        List<QuoteItem> list = new ArrayList<>();
         boolean exists = false;
         for (QuoteItem qi : quoteItems) {
                if (qi.equals(item)) {
                    qi.setQuantity(qi.getQuantity() + 1L);
                    exists = true;
                }   
                list.add(qi);
        }
         if(exists==true){
             return list;
         }
         else{
             quoteItems.add(item);
             return quoteItems;
         }
    }
    public List<QuoteItem> removeQuoteItem(List<QuoteItem> quoteItems, Long productId){
      
        for (Iterator<QuoteItem> it = quoteItems.iterator(); it.hasNext();) {
            QuoteItem qi = it.next();
            if (Objects.equals(qi.getProduct().getId(), productId)) {
                it.remove();
            }      
        }
        return quoteItems;
    }
    
    
}
