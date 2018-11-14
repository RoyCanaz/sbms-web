/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.controller;

import com.sbms.domain.Client;
import com.sbms.domain.Company;
import com.sbms.domain.EmailAccount;
import com.sbms.domain.Invoice;
import com.sbms.domain.Product;
import com.sbms.domain.Qoute;
import com.sbms.domain.QuoteItem;
import com.sbms.domain.Stock;
import com.sbms.emails.SendInvoiceEmailService;
import com.sbms.pdf.GenerateInvoice;
import com.sbms.repository.InvoiceRepo;
import com.sbms.service.BrandService;
import com.sbms.service.CategoryService;
import com.sbms.service.ClientService;
import com.sbms.service.EmailAccountService;
import com.sbms.service.InvoiceService;
import com.sbms.service.ProductService;
import com.sbms.service.QouteService;
import com.sbms.service.QuoteItemService;
import com.sbms.service.StockService;
import com.sbms.service.SupplierService;
import com.sbms.service.UserService;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Roy
 */
@Controller
@RequestMapping("invoice")
public class InvoiceController {
    
    @Resource
    private ProductService productService;
    
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
    private SendInvoiceEmailService sendInvoiceEmailService; 
    
    @Resource
    private InvoiceService invoiceService;
    @Resource
    private StockService stockService;
    @Autowired
    private InvoiceRepo invoiceRepo;
    @Resource
    private EmailAccountService emailAccountService;
     private static final String TEMP_DIRECTORY = "/temp";
    
     
      public String setUpModel(ModelMap model, String title, Company company) {
        Qoute qoute = new Qoute();
        model.addAttribute("pageTitle", title);
        model.addAttribute("quote", qoute);
        model.addAttribute("clickViewInvoice", true);
        model.addAttribute("upActive", "active");
        return "invoice-master";
    }
    
    @RequestMapping(value = "/list" , method = RequestMethod.GET)
     public String listClient(ModelMap model, HttpSession session){
         Company  company = (Company) session.getAttribute("company");
         model.addAttribute("pageTitle", "List Client");
         model.addAttribute("clickListClients", true);
         model.addAttribute("clients", clientService.findByActiveAndCompany(company));
         model.addAttribute("upActive", "active");
         return "invoice-master";
     }
     
     @RequestMapping(value = "/clientsAll", method = RequestMethod.GET)
     public String listClientAll(ModelMap model, HttpSession session){
         Company  company = (Company) session.getAttribute("company");
         model.addAttribute("pageTitle", "List Client");
         model.addAttribute("clickListAllClients", true);
         model.addAttribute("clients", clientService.findByActiveAndCompany(company));
         model.addAttribute("upActive", "active");
         return "invoice-master";
     }
     
     @GetMapping("/quote")
     public String listInvoiceQuotes(ModelMap model, @RequestParam Long id){
         List<Qoute> quotes = qouteService.getByClientId(id);
         for(Qoute q : quotes){
             model.addAttribute("clientName", q.getClient().getName());
         }
         model.addAttribute("pageTitle", "Client Quotes");
         model.addAttribute("clickListClientQuotes", true);
         model.addAttribute("quotes", quotes);
         model.addAttribute("upActive", "active");
         return "invoice-master";
     }
     @GetMapping("/quotesAll")
     public String listAllQuotesForGeneration(ModelMap model, HttpSession session){
         Company  company = (Company) session.getAttribute("company"); 
         model.addAttribute("pageTitle", "Client Quotes");
         model.addAttribute("clickAllQuotes", true);
         model.addAttribute("quotes", qouteService.findByCompany(company));
         model.addAttribute("upActive", "active");
         return "invoice-master";
     }
     @GetMapping("/list-quotes")
     public String listAllQuotes(ModelMap model, HttpSession session){
         Company  company = (Company) session.getAttribute("company"); 
         model.addAttribute("pageTitle", "Client Quotes");
         model.addAttribute("clickQuotes", true);
         model.addAttribute("all", true);
         model.addAttribute("quotes", qouteService.findByCompany(company));
         model.addAttribute("upActive", "active");
         return "invoice-master";
     }
     @GetMapping("/all-quotes")
     public String listAllQuotesByUser(ModelMap model, HttpSession session){
         Company  company = (Company) session.getAttribute("company"); 
         model.addAttribute("pageTitle", "Client Quotes");
         model.addAttribute("clickQuotes", true);
         model.addAttribute("quotes", qouteService.findByCompanyAndUser(company, userService.getCurrentUser()));
         model.addAttribute("upActive", "active");
         return "invoice-master";
     }
     @GetMapping("/all/{clientId}")
     public String listAllInvoices(ModelMap model, @PathVariable("clientId") Long clientId){
         List<Qoute> qoute = qouteService.getByClientId(clientId);
         List<Invoice> invoices = new ArrayList<>();
         for(Qoute q : qoute){
           for(Invoice invoice : q.getInvoice()){
               invoices.add(invoice);
           }
         }
         model.addAttribute("pageTitle", "Client Invoices");
         model.addAttribute("clickListClientInvoices", true);
         model.addAttribute("invoices", invoices);
         model.addAttribute("upActive", "active");
         return "invoice-master";
     }
     @RequestMapping(value = "/view/{quoteId}", method = RequestMethod.GET)
     public String viewInvoice(ModelMap model, @PathVariable("quoteId") Long quoteId, HttpSession session){     
            session.setAttribute("viewInvoice", true);
            return "redirect:/quote/edit/"+quoteId;
     }
     
     
     @GetMapping("/addItem")
     public String addItem(ModelMap model, @RequestParam Long itemId, @RequestParam Long quoteId){
         Product product = productService.get(itemId);
         QuoteItem newItem = new QuoteItem();
         newItem.setProduct(product);
        // quoteItem.setQouteId(quoteId);
         newItem.setQuantity(1L);
         Boolean exists = false;
        
         QuoteItem ei = null;
         Qoute qoute = qouteService.get(quoteId);
         List<QuoteItem> list = qoute.getQuoteItems();
         for(QuoteItem existItem : list){
             if(existItem.getProduct().getId().equals(product.getId())){               
                 exists = true;                                
                 ei = existItem;
             }
         }
         
         if(exists && ei!=null){
            Long quantity = 1L + ei.getQuantity();
            ei.setQuantity(quantity);
            quoteItemService.save(ei);  
         }
            else{
             newItem.setQoute(qoute);
             quoteItemService.save(newItem);
           }
         
        
         
        
         Double totalAmt =  qoute.getTotal() + product.getUnitPrice();
         Double vat = totalAmt * 0.15;
         Double totalIncVat = vat + totalAmt;
         int numOfItems = 1 + qoute.getNumOfItems();
         qoute.setTotal(Precision.round(totalAmt, 2));
         qoute.setVat(Precision.round(vat, 2));
         qoute.setTotalIncVat(Precision.round(totalIncVat, 2));
         qoute.setNumOfItems(numOfItems);
         qouteService.save(qoute);
         return "redirect:/invoice/view?quoteId="+quoteId;
        
     }
  
     @PostMapping("/process")
     public String generateInvoice(@ModelAttribute("invoice")Invoice invoice, ModelMap model, HttpSession session) throws Exception{
         Company  company = (Company) session.getAttribute("company");
         Qoute qoute = qouteService.get(invoice.getQuoteId());
         
         Client client = qoute.getClient();
         String uuid = createInvoiceUuid();
                 
         invoice.setCountNumberOfSent(1);
         invoice.setClient(client);
         invoice.setInvoiceUuid(uuid);
         invoice.setQoute(qoute);
       //  Invoice i = invoiceService.save(invoice);
        
        
         
         //Qoute qoute1 = i.getQoute();
        
         
          ServletContext context = session.getServletContext();
         /*   String tempDirectory = context.getRealPath(TEMP_DIRECTORY);
            File quotesDirectory = new File(tempDirectory);
            if(!quotesDirectory.exists()){
                quotesDirectory.mkdir();
            }
         */
          File tempDirectory = new File(context.getRealPath(TEMP_DIRECTORY) + "/invoices");
            if (!tempDirectory.exists()) {
                tempDirectory.mkdirs();
            }
          

     //    String path = tempDirectory+File.separator+i.getInvoiceUuid()+".pdf";
      //   GenerateInvoice generateInvoice = new GenerateInvoice();
      //   generateInvoice.save(i, userService.getCurrentUser()==null ? "": userService.getCurrentUser().getDisplayName(), path, context);
         String path = tempDirectory+File.separator+invoice.getInvoiceUuid()+".pdf";
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
        
         //sendInvoiceEmailService.sendInvoiceWithAttachment(qoute1.getContact().getEmail(), "Invoice", cc, bcc,"Please Find Attached Invoice.",path, company);
         sendInvoiceEmailService.sendInvoiceWithAttachment(qoute.getContact().getEmail(), "Please Find Attached Invoice.", cc, bcc, uuid, path, company);
         
          for(QuoteItem item : qoute.getQuoteItems()){
             Product product = productService.get(item.getProduct().getId());
             Long av = product.getAvailableStock();
             Long soldItems = item.getQuantity();
             Long availableStock = av -soldItems;
             product.setAvailableStock(availableStock);
             productService.save(product);
         }
          /*  List<Product> quoteProduct = new ArrayList<>();
            if(invoice.getInvoiceStock().isEmpty()){
                  for(QuoteItem item : qoute.getQuoteItems()){
                      quoteProduct.add(item.getProduct());
                  }
            }
            List<Stock> availableStock =  new ArrayList<>();
            quoteProduct.forEach((t) -> {
                List<Stock> stock = t.getStock();
                for(Stock s : stock){
                    if(s.getAvailable()){
                        availableStock.add(s);
                    }
                }                
            });
         */
         
          for(Stock s : invoice.getInvoiceStock()){
               Stock stock = stockService.get(s.getId());
               stock.setAvailable(false);
               stock.setPurchasedBy(client);
               stock.setDatePurchased(new Date());
               stockService.save(stock);             
         }
         
         
         Invoice i = invoiceService.save(invoice);
         return "redirect:/invoice/success?id="+i.getId();         
     }
     
     
     
     public String createInvoiceUuid(){
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH)+1;
            int day = now.get(Calendar.DAY_OF_MONTH);
            Long maxCount = invoiceRepo.getMaxId()==null ? 1L : invoiceRepo.getMaxId()+1L;           
            String quoteUuid = "#"+String.valueOf(year)+String.valueOf(month)+String.valueOf(day)+"00"+String.valueOf(maxCount);
            return quoteUuid;         
    }
    
     @GetMapping("/viewInvoice")
    public String viewInvoice( ModelMap model, @RequestParam Long invoiceId){
        //Qoute q = qouteService.get(quoteId);
        Invoice invoice = invoiceService.get(invoiceId);
        model.addAttribute("invoice", invoice);
        return "generateInvoice";
    }
     @GetMapping("/success")
    public String successPage( ModelMap model, @RequestParam Long id, HttpSession session){
        
            Invoice in = invoiceService.get(id);
            String filename = in.getInvoiceUuid();
            ServletContext context = session.getServletContext();
            String tempDirectory = context.getRealPath(TEMP_DIRECTORY);
            String path = tempDirectory+File.separator+filename+".pdf";
           // String path = "src/main/webapp/resources/temp/"+filename+".pdf";
            FileSystemResource file = new FileSystemResource(new File(path));
            if(file.exists()){
              file.getFile().delete();  
            }
         model.addAttribute("pageTitle", "Success");
         model.addAttribute("redirectSuccess", true);
         model.addAttribute("upActive", "active");
         return "invoice-master";
    }
        
}
