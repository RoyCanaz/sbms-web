/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbms.domain.BankingDetail;
import com.sbms.domain.Client;
import com.sbms.domain.Company;
import com.sbms.domain.Contact;
import com.sbms.domain.Currency;
import com.sbms.domain.EmailAccount;
import com.sbms.domain.Product;
import com.sbms.domain.Qoute;
import com.sbms.domain.QuoteItem;
import com.sbms.emails.SendQuoteEmailService;
import com.sbms.pdf.GenerateQuotePdf;
import com.sbms.repository.QouteRepository;
import com.sbms.repository.QuoteItemRepo;
import com.sbms.service.BankingDetailService;
import com.sbms.service.BrandService;
import com.sbms.service.CategoryService;
import com.sbms.service.ClientService;
import com.sbms.service.CompanyService;
import com.sbms.service.ContactService;
import com.sbms.service.CurrencyService;
import com.sbms.service.EmailAccountService;
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("quote")
public class QuoteController {

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
    @Autowired
    private QouteRepository qouteRepository;
    @Autowired
    private QuoteItemRepo quoteItemRepo;
    private static final String TEMP_DIRECTORY = "/temp";
    //private static final String TEMP_DIRECTORY = "/uploads";

    public String setUpModel(ModelMap model) {
        Qoute qoute = new Qoute();
        model.addAttribute("pageTitle", "Qoute List");
        model.addAttribute("clickAddQuote", true);
        model.addAttribute("quote", qoute);
        model.addAttribute("upActive", "active");
        return "quote-master";
    }

    public String setUpModel(ModelMap model, String title, Company company) {
        Qoute qoute = new Qoute();
        model.addAttribute("pageTitle", title);
        model.addAttribute("quote", qoute);
        model.addAttribute("clickEditQuote", true);
        model.addAttribute("upActive", "active");
        return "quote-master";
    }

   
    @GetMapping("/list")
    public String currentQuote(ModelMap model, HttpSession session) {
        Company company = (Company) session.getAttribute("company");
        Double total = new Double(0);    
        List<Currency> currencyList = currencyService.findByCompany(company);
        List<Client> clientList = clientService.findByActiveAndCompany(company);
        List<BankingDetail> bankingDetailList = bankingDetailService.findByCompany(company);
        
        List<QuoteItem> list = (List<QuoteItem>) session.getAttribute("quoteItems");
        
        Currency currency = (Currency) session.getAttribute("quoteCurrency");
          BankingDetail bankingDetail = (BankingDetail) session.getAttribute("quoteBank");
        Currency c = currency==null?currencyService.getByActiveAndCompany(Boolean.TRUE, company):currency;
       BankingDetail bd = bankingDetail==null?bankingDetailService.findByActiveAndCompany(Boolean.TRUE, company):bankingDetail;
       
       
       List<Product> products =  productService.findByCompany(company);
       List<Product> inventory =  AppUtil.streamProducts(products, list);//remove products already in quote 
        if (list != null) {
            for (QuoteItem item : list) {
                total = total + AppUtil.calculateUnitPrice(c.getRate(),
                        item.getProduct()
                                .getSellingPrice()) 
                        * item.getQuantity();
            }
        }
        model.addAttribute("total", AppUtil.roundToTwoDecimal(total));
        session.setAttribute("quoteItems", list);
        session.setAttribute("quoteCurrency", c);
        session.setAttribute("quoteBank", bd); 
       
        model.addAttribute("currencyList", currencyList);
        model.addAttribute("clientList", clientList);
        model.addAttribute("bankingDetailList", bankingDetailList);
        model.addAttribute("inventory", inventory);
        return setUpModel(model);
    }
    
    
    
  @GetMapping("/add/{id}")
    public String listQuote(ModelMap model, @PathVariable("id") Long id,  HttpSession session) {
        
        List<QuoteItem> list = (List<QuoteItem>) session.getAttribute("quoteItems");
        if (list == null) {
            list = new ArrayList<>();
        }
        
        Product product = productService.get(id);
        if (product != null) {
             QuoteItem qi = new QuoteItem();
                qi.setProduct(product);
                qi.setQuantity(1L);
                List<QuoteItem> quoteItems = getNewQuoteList(list, qi);
                session.setAttribute("quoteItems", quoteItems);
         
        }

        return "redirect:/quote/list";
    }
    @GetMapping("/reduce-quantity/{id}")
    public String reduceQuantity(ModelMap model, @PathVariable("id") Long id, HttpSession session) {
        List<QuoteItem> list = (List<QuoteItem>) session.getAttribute("quoteItems");
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
        session.setAttribute("quoteItems", quoteItems); 
        return "redirect:/quote/list";
    }
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String removeItem(ModelMap model, @PathVariable Long id, HttpSession session) {
        List<QuoteItem> list = (List<QuoteItem>) session.getAttribute("quoteItems");
        if (list != null) {
             List<QuoteItem> quoteItems = removeQuoteItem(list, id);
            session.setAttribute("quoteItems", quoteItems);
        }
        return "redirect:/quote/list";
    }
   

   /* @RequestMapping(value = "/listEdit", method = RequestMethod.GET)
    public String currentQuoteEdit(ModelMap model, @RequestParam Long quoteId, @RequestParam Long clientId, @RequestParam Long contactId, @RequestParam Long bankId, HttpSession session) {
        Company company = (Company) session.getAttribute("company");
        Client client = clientService.get(clientId);
        Contact contactDetails = contactService.get(contactId);
        BankingDetail bankingDetail = bankingDetailService.get(bankId);
        Double total = new Double(0);
        Long quoteI = (Long) session.getAttribute("quoteId");
        Qoute qoute = qouteService.get(quoteId);
        List<QuoteItem> list = (List<QuoteItem>) session.getAttribute("quoteItemsAdd");
        if (list != null) {
            for (QuoteItem item : list) {
                total = total + item.getProduct().getUnitPrice() * item.getQuantity();
            }
        }
        double amt = total + qoute.getTotal();
        model.addAttribute("total", amt);
        session.setAttribute("quoteItemsAdd", list);
        model.addAttribute("listQuoteItemsEdit", qoute.getQuoteItems());
        model.addAttribute("listQuoteItemsAdd", list);
        model.addAttribute("client", client);
        model.addAttribute("contact", contactDetails);
        model.addAttribute("bank", bankingDetail);
        model.addAttribute("quoteId", quoteId);
        return setUpModel(model, "Edit Quote", company);
    }
*/
    @RequestMapping(value = "/edit-quote/{quoteId}", method = RequestMethod.GET)
     public String viewInvoice(ModelMap model, @PathVariable("quoteId") Long quoteId, HttpSession session){     
            session.setAttribute("viewInvoice", false);
            return "redirect:/quote/edit/"+quoteId;
     }
    @RequestMapping(value = "/edit/{quoteId}", method = RequestMethod.GET)
    public String editQuote(ModelMap model, @PathVariable("quoteId") Long quoteId,  HttpSession session) {     
        Qoute qoute = qouteService.get(quoteId);
        Company company = qoute.getCompany();
        Client client = qoute.getClient();
        Currency currency = qoute.getCurrency();
        Contact contactDetails = qoute.getContact();
        BankingDetail bankingDetail = qoute.getBankingDetail();
          List<Product> products =  productService.findByCompany(company);
         List<Product> inventory =  AppUtil.streamProducts(products, qoute.getQuoteItems());//remove products already in quote 
        model.addAttribute("listQuoteItemsEdit", qoute.getQuoteItems());
        model.addAttribute("editTotal", qoute.getTotal());
        model.addAttribute("currencyList", currencyService.findByCompany(company));
        model.addAttribute("bankList", bankingDetailService.findByCompany(company));
        model.addAttribute("contactList", client.getContact());
        model.addAttribute("clientList", clientService.findByActiveAndCompany(company));
        model.addAttribute("editClient", client);
        model.addAttribute("editContact", contactDetails);
        model.addAttribute("editBank", bankingDetail);
        model.addAttribute("editCurrency", currency);
        model.addAttribute("quoteId", quoteId);
        session.setAttribute("editQuoteId", quoteId);    
        model.addAttribute("inventory", inventory);
        return setUpModel(model, "Edit Quote", company);
    }
     @RequestMapping(value = "/edit/send", method = RequestMethod.GET)
    public String editQuoteSend(ModelMap model, @RequestParam("quoteId") Long quoteId,  HttpSession session) {
           GenerateQuotePdf g = new GenerateQuotePdf();
            Qoute qo = qouteService.get(quoteId);
            ServletContext context = session.getServletContext();
           
            File tempDirectory = new File(context.getRealPath(TEMP_DIRECTORY) + "/quotes");
            if (!tempDirectory.exists()) {
                tempDirectory.mkdirs();
            }
            String path = tempDirectory + File.separator + qo.getQuoteUuid() + ".pdf";

            try {

                g.pdfFile(qo, userService.getCurrentUser() == null ? "Total " : userService.getCurrentUser().getDisplayName(), path, context);

            } catch (Exception ex) {
                Logger.getLogger(QuoteController.class.getName()).log(Level.SEVERE, null, ex);
            }

            List<EmailAccount> emailCc = emailAccountService.getCc(qo.getCompany());
            List<EmailAccount> emailBcc = emailAccountService.getBcc(qo.getCompany());
            String[] cc = new String[emailCc.size()];
            int index = 0;
            for (EmailAccount ea : emailCc) {
                cc[index] = (String) ea.getEmail();
                index++;
            }
            String[] bcc = new String[emailBcc.size()];
            int in = 0;
            for (EmailAccount account : emailBcc) {
                bcc[in] = (String) account.getEmail();
                index++;
            }
            quoteEmailService.sendQuoteWithAttachment(qo.getContact().getEmail(), "Quote", "Please Find Attached Quote.", cc, bcc, path, qo, qo.getCompany());
             removeSessionAttributes(session);
            return "redirect:/quote/success?id=" + quoteId;
    }
    
    @RequestMapping(value = "/sub-item/{itemId}/{quoteId}", method = RequestMethod.GET)
    public String subItem(ModelMap model, @PathVariable("itemId") Long itemId, @PathVariable("quoteId") Long quoteId,  HttpSession session) {
        
        QuoteItem quoteItem = quoteItemRepo.getOne(itemId);
        quoteItem.setQuantity(quoteItem.getQuantity()-1L);
        quoteItemRepo.save(quoteItem);
        Product product = quoteItem.getProduct();
        Qoute qoute = qouteService.get(quoteId);
        Currency currency = qoute.getCurrency();
        Double unitPrice = AppUtil.calculateUnitPrice(currency.getRate(), product.getSellingPrice());
        Double total = qoute.getTotal() - unitPrice;
        Double vat = AppUtil.calculateVat(total);
        Double totalIncVat = AppUtil.calculateTotal(total, vat);       
        
        qoute.setTotal(AppUtil.roundToTwoDecimal(total));
        qoute.setVat(vat);
        qoute.setTotalIncVat(totalIncVat);
        qoute.setNumOfItems(qoute.getNumOfItems()-1);
        qouteService.save(qoute);
        return "redirect:/quote/edit/"+quoteId;
        
    }
    @RequestMapping(value = "/add-item/{itemId}/{quoteId}", method = RequestMethod.GET)
    public String addItem(ModelMap model, @PathVariable("itemId") Long itemId, @PathVariable("quoteId") Long quoteId,  HttpSession session) {
        
        QuoteItem quoteItem = quoteItemRepo.getOne(itemId);
        quoteItem.setQuantity(quoteItem.getQuantity()+1L);
        quoteItemRepo.save(quoteItem);
        Product product = quoteItem.getProduct();
        Qoute qoute = qouteService.get(quoteId);
        Currency currency = qoute.getCurrency();
        Double unitPrice = AppUtil.calculateUnitPrice(currency.getRate(), product.getSellingPrice());
        Double total = qoute.getTotal() + unitPrice;
        Double vat = AppUtil.calculateVat(total);
        Double totalIncVat = AppUtil.calculateTotal(total, vat);       
        
        qoute.setTotal(AppUtil.roundToTwoDecimal(total));
        qoute.setVat(vat);
        qoute.setTotalIncVat(totalIncVat);
        qoute.setNumOfItems(qoute.getNumOfItems()+1);
        qouteService.save(qoute);
        return "redirect:/quote/edit/"+quoteId;
        
    }
    @RequestMapping(value = "/delete-item/{itemId}/{quoteId}", method = RequestMethod.GET)
    public String deleteItem(ModelMap model, @PathVariable("itemId") Long itemId, @PathVariable("quoteId") Long quoteId,  HttpSession session) {
        
        QuoteItem quoteItem = quoteItemRepo.getOne(itemId);
        quoteItemRepo.delete(quoteItem);
        Product product = quoteItem.getProduct();
        Qoute qoute = qouteService.get(quoteId);
         Currency currency = qoute.getCurrency();
        Double total = qoute.getTotal();
        Double unitPrice = AppUtil.calculateUnitPrice(currency.getRate(), product.getSellingPrice());
        for(int i = 0; i<quoteItem.getQuantity();i++){
              total = total - unitPrice;
        }
        Double vat = AppUtil.calculateVat(total);
        Double totalIncVat = AppUtil.calculateTotal(total, vat);              
        qoute.setTotal(AppUtil.roundToTwoDecimal(total));
        qoute.setVat(vat);
        qoute.setTotalIncVat(totalIncVat);
        qoute.setNumOfItems(qoute.getNumOfItems()-quoteItem.getQuantity().intValue());
        qouteService.save(qoute);
        return "redirect:/quote/edit/"+quoteId;
        
    }
    
   @GetMapping("/cancel")
   public String cancelQuote(HttpSession session){
         removeSessionAttributes(session);
         return "redirect:/user/dashboard";
   }


    
    
    public void removeSessionAttributes(HttpSession session){
            session.removeAttribute("quoteClient");
            session.removeAttribute("quoteContact");
            session.removeAttribute("quoteBank");
            session.removeAttribute("quoteCurrency");
            session.removeAttribute("total");
            session.removeAttribute("quoteItems"); 
            session.removeAttribute("editQuoteId");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createQuote(@ModelAttribute("quote") Qoute qoute, ModelMap model, HttpSession session, RedirectAttributes redirectAttrs) {
        Company company = (Company) session.getAttribute("company");
        List<QuoteItem> list = (List<QuoteItem>) session.getAttribute("quoteItems");
        Currency currency = (Currency) session.getAttribute("quoteCurrency");
        BankingDetail bankingDetail = (BankingDetail) session.getAttribute("quoteBank");
        if (!list.isEmpty()) {
            int numberOfItems = 0;
            for (QuoteItem i : list) {
                numberOfItems = numberOfItems + i.getQuantity().intValue();
            }

            Double vat = qoute.getTotal() * AppUtil.BASEVALUEVAT;
            Double totalIncVat = vat + qoute.getTotal();
            qoute.setQuoteItems(list);
            qoute.setQuoteUuid(createQuoteUuid(qoute.getClient().getId()));
            qoute.setCountNumberOfSent(0);
            qoute.setVat(AppUtil.roundToTwoDecimal(vat));
            qoute.setTotalIncVat(AppUtil.roundToTwoDecimal(totalIncVat));
            qoute.setNumOfItems(numberOfItems);
            qoute.setCompany(company);
         
            qoute.setCurrency(currency);
            qoute.setBankingDetail(bankingDetail);
            Qoute q = qouteService.save(qoute);
            for (QuoteItem item : list) {
                item.setQoute(q);
                quoteItemService.save(item);
            }
            

            GenerateQuotePdf g = new GenerateQuotePdf();
            Qoute qo = qouteService.get(q.getId());
            ServletContext context = session.getServletContext();
           
            File tempDirectory = new File(context.getRealPath(TEMP_DIRECTORY) + "/quotes");
            if (!tempDirectory.exists()) {
                tempDirectory.mkdirs();
            }
            String path = tempDirectory + File.separator + qo.getQuoteUuid() + ".pdf";

            try {

                g.pdfFile(qo, userService.getCurrentUser() == null ? "Total " : userService.getCurrentUser().getDisplayName(), path, context);

            } catch (Exception ex) {
                Logger.getLogger(QuoteController.class.getName()).log(Level.SEVERE, null, ex);
            }

            List<EmailAccount> emailCc = emailAccountService.getCc(company);
            List<EmailAccount> emailBcc = emailAccountService.getBcc(company);
            String[] cc = new String[emailCc.size()];
            int index = 0;
            for (EmailAccount ea : emailCc) {
                cc[index] = (String) ea.getEmail();
                index++;
            }
            String[] bcc = new String[emailBcc.size()];
            int in = 0;
            for (EmailAccount account : emailBcc) {
                bcc[in] = (String) account.getEmail();
                index++;
            }
            String companyName = company.getCompanyName() + " -  " + "Quote";
            quoteEmailService.sendQuoteWithAttachment(qo.getContact().getEmail(), companyName, "Please Find Attached Quote.", cc, bcc, path, qo, company);
             removeSessionAttributes(session);
            return "redirect:/quote/success?id=" + q.getId();
        }
        return null;
    }

    @RequestMapping(value = "/updateEdit", method = RequestMethod.POST)
    public String updateQuote(@ModelAttribute("quote") Qoute qoute, ModelMap model, HttpSession session, RedirectAttributes redirectAttrs) throws Exception {
        Company company = (Company) session.getAttribute("company");
        GenerateQuotePdf g = new GenerateQuotePdf();
        Qoute qo = qouteService.get(qoute.getId());

        ServletContext context = session.getServletContext();
        String tempDirectory = context.getRealPath(TEMP_DIRECTORY);
        File quotesDirectory = new File(tempDirectory);
        if (!quotesDirectory.exists()) {
            quotesDirectory.mkdir();
        }
        String path = tempDirectory + File.separator + qo.getQuoteUuid() + ".pdf";
        g.pdfFile(qo, userService.getCurrentUser().getDisplayName(), path, context);
        // String path = "src/main/webapp/resources/temp/"+qo.getQuoteUuid()+".pdf";
        List<EmailAccount> emailCc = emailAccountService.getCc(company);
        List<EmailAccount> emailBcc = emailAccountService.getBcc(company);
        String[] cc = new String[emailCc.size()];
        int index = 0;
        for (EmailAccount ea : emailCc) {
            cc[index] = (String) ea.getEmail();
            index++;
        }
        String[] bcc = new String[emailBcc.size()];
        int in = 0;
        for (EmailAccount account : emailBcc) {
            bcc[in] = (String) account.getEmail();
            index++;
        }
        quoteEmailService.sendQuoteWithAttachment(qo.getContact().getEmail(), "Quote", "Please Find Attached Quote.", cc, bcc, path, qo, company);

        return "redirect:/quote/success?id=" + qo.getId();

    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String listAll(@ModelAttribute("quote") Qoute quote, ModelMap model, HttpSession session) {

        Company company = (Company) session.getAttribute("company");
        model.addAttribute("clickListAllQuotes", true);

        model.addAttribute("quotes", qouteService.findByCompany(company));
        model.addAttribute("upActive", "active");
        return "quote-master";
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String ne(@RequestParam Long id, ModelMap model, HttpSession session) {
        Company company = (Company) session.getAttribute("company");

        Qoute qou = qouteService.get(id);
        String filename = qou.getQuoteUuid();
        ServletContext context = session.getServletContext();
        String tempDirectory = context.getRealPath(TEMP_DIRECTORY);
        String path = tempDirectory + File.separator + filename + ".pdf";

        FileSystemResource file = new FileSystemResource(new File(path));
        if (file.exists()) {
            file.getFile().delete();
        }
        model.addAttribute("clickSuccessNewQuote", true);
        model.addAttribute("newquote", qou);
        model.addAttribute("allquotes", qouteService.findByCompany(company));
        model.addAttribute("upActive", "active");
        return "quote-master";
    }

    @GetMapping("/view")
    public String viewQuote(ModelMap model, @RequestParam Long quoteId) {
        Qoute q = qouteService.get(quoteId);
        model.addAttribute("quote", q);
        return "generateQuote";
    }

    

    

    @RequestMapping(value = "/removeEdit", method = RequestMethod.GET)
    public String removeFromSessionEdit(ModelMap model, @RequestParam Long id, @RequestParam Long quoteId, @RequestParam Long clientId, @RequestParam Long contactId, @RequestParam Long bankId, HttpSession session) {

        List<QuoteItem> list = (List<QuoteItem>) session.getAttribute("quoteItemsAdd");
        if (list != null) {
            Double total = removeItemFromQuote(list, id);
            model.addAttribute("total", total);
            session.setAttribute("quoteItems", list);
        }

        model.addAttribute("listQuoteItems", list);
        return "redirect:/quote/listEdit?quoteId=" + quoteId + "&clientId=" + clientId + "&contactId=" + contactId + "&bankId=" + bankId;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteItemEdit(ModelMap model, @RequestParam Long id, @RequestParam Long quoteId, @RequestParam Long clientId, @RequestParam Long contactId, @RequestParam Long bankId, HttpSession session) {
        QuoteItem item = quoteItemService.get(id);
        //  Long quoteId = (Long) session.getAttribute("quoteId");
        Qoute qoute = qouteService.get(quoteId);
        int subQuantity = qoute.getNumOfItems() - item.getQuantity().intValue();
        Double total = qoute.getTotal() - item.getQuantity() * item.getProduct().getUnitPrice();
        Double vat = total * 0.15;
        Double totalIncVat = vat + total;
        qoute.setVat(vat);
        qoute.setTotalIncVat(totalIncVat);
        qoute.setTotal(Precision.round(total, 2));
        qoute.setNumOfItems(subQuantity);
        qouteService.save(qoute);
        quoteItemService.delete(item);

        return "redirect:/quote/listEdit?quoteId=" + quoteId + "&clientId=" + clientId + "&contactId=" + contactId + "&bankId=" + bankId;
    }

    public Double addItemToQuote(List<QuoteItem> quoteItems, QuoteItem item, Currency currency) {
        Double doubl = new Double(0);
        boolean isExit = false;
        for (QuoteItem qi : quoteItems) {
            if (qi.equals(item)) {
                qi.setQuantity(qi.getQuantity() + 1L);
                isExit = true;
            }
            doubl = doubl +  AppUtil.calculateUnitPrice(currency.getRate(), qi.getProduct().getSellingPrice())  * qi.getQuantity();
        }
        if (isExit == false) {
            quoteItems.add(item);
            doubl = doubl +AppUtil.calculateUnitPrice(currency.getRate(), item.getProduct().getSellingPrice()) * item.getQuantity();
        }

        return Precision.round(doubl, 2);
    }
    
    public List<QuoteItem> getNewQuoteList(List<QuoteItem> quoteItems, QuoteItem item){
        List<QuoteItem> list = new ArrayList<>();
         boolean exists = false;
         for (QuoteItem qi : quoteItems) {
                if (qi.getProduct().getId().equals(item.getProduct().getId())) {
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

    public Double removeItemFromQuote(List<QuoteItem> list, long id) {
        Double total = new Double(0);
        QuoteItem temp = null;
        for (QuoteItem qi : list) {
            if (qi.getProduct().getId() == id) {
                temp = qi;
            }
            total = total + qi.getProduct().getUnitPrice() * qi.getQuantity();
        }
        if (temp != null) {
            list.remove(temp);
        }
        return Precision.round(total, 2);
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

}
