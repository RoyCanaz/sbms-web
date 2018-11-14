/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.restmobile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbms.controller.QuoteController;
import com.sbms.domain.BankingDetail;
import com.sbms.domain.Client;
import com.sbms.domain.Company;
import com.sbms.domain.Contact;
import com.sbms.domain.Currency;
import com.sbms.domain.EmailAccount;
import com.sbms.domain.Product;
import com.sbms.domain.Qoute;
import com.sbms.domain.QuoteItem;
import com.sbms.dto.QuoteDTO;
import com.sbms.dto.QuoteItemDTO;
import com.sbms.dto.QuoteMapper;
import com.sbms.Mapper.QuoteWrapper;
import com.sbms.dto.RateDto;
import com.sbms.emails.SendQuoteEmailService;
import com.sbms.pdf.GenerateQuotePdf;
import com.sbms.repository.QouteRepository;
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
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
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
 * @author user
 */
@RestController
@RequestMapping("/rest/client/quote")
public class QuoteRestController {
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
    private ObjectMapper objectMapper;
    private static final String TEMP_DIRECTORY = "/temp";
    
    
    @GetMapping("/rates/{companyId}")
    public Set<RateDto> getRates(@PathVariable("companyId") Long companyId) {   
           Company company = companyService.get(companyId);
           List<Currency> list = currencyService.findByCompany(company);
           
           Set<RateDto> set = new HashSet<>();
           list.forEach(r->{
               RateDto rate = new RateDto(r.getId(), r.getActive(), r.getName(), r.getRate());
               set.add(rate);
           });          
           return set;         
    }
    
    @GetMapping("/bank/{companyId}")
    public Set<BankingDetail> getBank(@PathVariable("companyId") Long companyId){   
           Company company = companyService.get(companyId);
           List<BankingDetail> list = bankingDetailService.findByCompany(company);
           Set<BankingDetail> set = new HashSet<>();
           list.forEach(b->{
               set.add(b);
           });          
           return set;         
    }
    @PostMapping("/new_quote/{company}") 
    ResponseEntity<QuoteMapper> newQuote(@RequestBody QuoteMapper quoteMapper, @PathVariable("company") Long id){
         Company company = companyService.get(id);        
         QuoteDTO quoteDTO = quoteMapper.getQuote();
         Client client  = clientService.get(quoteDTO.getClient());
         Qoute qoute = new Qoute();
         qoute.setCompany(company);
         qoute.setContact(contactService.get(quoteDTO.getContact()));
         qoute.setQuoteUuid(createQuoteUuid(client.getId()));
         qoute.setClient(client);
         qoute.setBankingDetail(bankingDetailService.get(quoteDTO.getBankingDetail()));
         qoute.setCurrency(currencyService.get(quoteDTO.getCurrency()));
         qoute.setCountNumberOfSent(0);
         qoute.setTotal(quoteDTO.getTotal());
         qoute.setVat(quoteDTO.getVat());
         qoute.setTotalIncVat(quoteDTO.getTotalIncVat());
         Qoute q = qouteService.save(qoute);
         
         quoteDTO.setId(q.getId());
         quoteDTO.setLastSendMailStatus(0);
         quoteDTO.setQuoteUuid(qoute.getQuoteUuid());
         quoteDTO.setDateCreated(q.getDateCreated().toString());
         List<QuoteItemDTO> items = new ArrayList<>();
         for(QuoteItemDTO itemDTO : quoteMapper.getQuoteItem()){
               QuoteItem item = new QuoteItem();
               item.setProduct(productService.get(itemDTO.getProductId()));
               item.setQoute(q);
               item.setQuantity(itemDTO.getQuantity());
               QuoteItem quoteItem = quoteItemService.save(item);
               
               itemDTO.setQuoteId(q.getId());
               itemDTO.setId(quoteItem.getId());
               items.add(itemDTO);
              
         }
         QuoteMapper mapper = new QuoteMapper();
         mapper.setQuote(quoteDTO);
         mapper.setQuoteItem(items);
         return new ResponseEntity<>(mapper, HttpStatus.OK);
        
    }
    
    @PostMapping("/send_quote/{quoteId}") 
    ResponseEntity<QuoteDTO> sendQuote( @PathVariable("quoteId") Long quoteId, HttpSession session) throws Exception{
        Qoute qo = qouteService.get(quoteId);
        Company company = qo.getCompany();
        System.err.println("==============");
        System.err.println("Quote Id..  "+quoteId);
        System.err.println("==============");
        
        GenerateQuotePdf g = new GenerateQuotePdf();
            
            ServletContext context = session.getServletContext();
           
            File tempDirectory = new File(context.getRealPath(TEMP_DIRECTORY) + "/quotes");
            if (!tempDirectory.exists()) {
                tempDirectory.mkdirs();
            }
            String path = tempDirectory+File.separator+qo.getQuoteUuid()+".pdf";           
            g.pdfFile(qo, userService.getCurrentUser()==null ? "Total ": userService.getCurrentUser().getDisplayName(), path, context);
            
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
            
           quoteEmailService.sendQuoteWithAttachment(qo.getContact().getEmail(), "Quote", "Please Find Attached Quote.", cc, bcc, path, qo, company);
           Qoute q = qouteService.get(quoteId);
           String dateSent = null;
           if(q.getLastDateOfSent()==null){
               dateSent = q.getDateCreated().toString();
           }
           else{
               dateSent = q.getLastDateOfSent().toString();
           }
           QuoteDTO quoteDTO = new QuoteDTO(q.getId(), dateSent, q.getQuoteUuid(), q.getTotal(), q.getNumOfItems(), q.getCountNumberOfSent(),
                   q.getVat(), q.getTotalIncVat(), q.getLastSendMailStatus(), q.getContact().getId(), q.getCurrency().getId(),
                   q.getClient().getId(), q.getBankingDetail().getId(), q.getCompany().getId());
           
           return new ResponseEntity<>(quoteDTO, HttpStatus.OK);
           
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
}