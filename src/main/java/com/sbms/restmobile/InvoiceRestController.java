/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.restmobile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbms.Mapper.QuoteWrapper;
import com.sbms.domain.Product;
import com.sbms.domain.QuoteItem;
import com.sbms.emails.SendQuoteEmailService;
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
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/rest/client/invoice")
public class InvoiceRestController {
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
    
  
    private static final String TEMP_DIRECTORY = "/temp";
    
    
}
