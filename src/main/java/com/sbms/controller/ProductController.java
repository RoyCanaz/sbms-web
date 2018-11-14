/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.controller;

import com.sbms.domain.Addon;
import com.sbms.domain.Brand;
import com.sbms.domain.Category;
import com.sbms.domain.Company;
import com.sbms.domain.Currency;
import com.sbms.domain.Product;
import com.sbms.domain.Specification;
import com.sbms.domain.Stock;
import com.sbms.repository.SpecificationRepo;
import com.sbms.service.AddonService;
import com.sbms.service.BrandService;
import com.sbms.service.CategoryService;
import com.sbms.service.CurrencyService;
import com.sbms.service.ProductService;
import com.sbms.service.StockService;
import com.sbms.service.SupplierService;
import com.sbms.utilities.AppMessage;
import com.sbms.utilities.MessageType;
import com.sbms.utilities.PageLists;
import com.sbms.utilities.AppUtil;
import com.sbms.validator.ProductValidator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Roy
 */
@Controller
@RequestMapping("inventory")
public class ProductController {

    @Resource
    private CategoryService categoryService;
    @Resource
    private SupplierService supplierService;
       
    @Resource
    private ProductValidator productValidator;
    @Resource
    private StockService stockService;
        
    @Resource
    private ProductService productService;
    @Resource
    private AddonService addonService;  
    @Resource
    private BrandService brandService;
     @Resource
    private CurrencyService currencyService;
     @Autowired
     private SpecificationRepo specificationRepo;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loadInventoryPage(Model model, HttpSession session) {
        Company company = (Company) session.getAttribute("company");
        model.addAttribute("pageTitle", "Product Categories");
        model.addAttribute("category", categoryService.findByActiveAndCompany(Boolean.TRUE, company));
        model.addAttribute("clickProducts", true);
        model.addAttribute("apActive", "active");
        return "stock-master";
    }
    
    @GetMapping("/delete/{productId}")
    public String deleteInventory(ModelMap model, @PathVariable("productId") Long productId){
        Product product = productService.get(productId);
        Category category = product.getCategory();
        productService.delete(product);
         return "redirect:/inventory/list?id="+category.getId()+"&name="+category.getName();     
    }

    @GetMapping("/add/{categoryId}/{categoryName}/{productId}")
    public String newProduct(ModelMap model, @PathVariable("categoryId") Long id, @PathVariable("categoryName") String name,
            @PathVariable(name = "productId",  required = false) Long productId, HttpSession session) {
           Company company = (Company) session.getAttribute("company");
           Category cat = categoryService.get(id);
          Product product = new Product();
         
          List<Category> categories = categoryService.findByActiveAndCompany(Boolean.TRUE, company);
         
          Specification specification = null;
          model.addAttribute("categoryId", id);
          model.addAttribute("brands", cat.getBrands());
          model.addAttribute("name", name);
          model.addAttribute("suppliers", cat.getSuppliers());
          if(productId!=0L){
              product = productService.get(productId);
              specification = product.getSpecification();
          }
          if(specification!=null){
              model.addAttribute("spec", specification);
          }
          
          
        if (name.contains("Lapt") || name.contains("Deskto")) {           
            model.addAttribute("clickNewComputers", true);           
            return setUpModel(model, product, company);
        } else if (name.contains("Print")) {         
            return setUpPrinter(model, product, company);
        } 
        else if(name.contains("Service")){
            return setUpService(model, product, company);
        }
         else if(name.contains("Web")){
            return setUpService(model, product, company);
        }
        else if (name.contains("Access")) {
            model.addAttribute("clickNewAccessory", true);            
            return setUpModel(model, product,  company);
        } else if (name.contains("Moni")) {
            model.addAttribute("clickNewMonitor", true);
         return setUpModel(model, product, company);
        } else if (name.contains("Consum")) {
            model.addAttribute("clickNewConsumable", true);         
            return setUpModel(model, product, company);
        }
        else{
         model.addAttribute("clickOther", true);         
            return setUpModel(model, product,  company);
        }
    }
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listProduct(ModelMap model, @RequestParam Long id, @RequestParam String name, HttpSession session) {
        Category category = categoryService.get(id);
           Company company = (Company) session.getAttribute("company");
        model.addAttribute("clickListInventory", true); 
        model.addAttribute("inventoryCat", category);
        model.addAttribute("inventory", productService.getByCategoryAndCompany(category, company));
        return setUpModelStock(model);      
    }
    @GetMapping("/add")
    public String addProduct(ModelMap model, @RequestParam Long id) {
        Product product = productService.get(id);   
        product.setQuantityDelivered("0");
        model.addAttribute("clickAddStock", true);       
        model.addAttribute("product", product);
         model.addAttribute("apActive", "active");
         return "stock-master"; 
    }
    @PostMapping("/add/form")
    public String addProductForm(@ModelAttribute("product") Product product, ModelMap model, HttpSession session) {
        Product p = productService.get(product.getId());  
         Company company = (Company) session.getAttribute("company");
        Long availableStock = p.getAvailableStock() + Long.parseLong(product.getQuantityDelivered());
        String delivered = p.getQuantityDelivered() + ", " +product.getQuantityDelivered();
        p.setQuantityDelivered(delivered);
        p.setAvailableStock(availableStock);
        p.setReOrderLevel(product.getReOrderLevel());
        p.setReOrderQuantity(product.getReOrderQuantity());
        p.setSellingPrice(product.getSellingPrice());
         float rate;
        Currency currency = currencyService.getByActiveAndCompany(Boolean.TRUE, company);
        if(currency!=null){
            rate = currency.getRate();
        }
        else{
            rate = 1;
        }      
             Double rp = rate * product.getSellingPrice();       
             Double up = AppUtil.calculateUnitPrice(rate, product.getSellingPrice());
        p.setRetailPrice(Precision.round(rp, 2));
        p.setUnitPrice(up);
        Product pr =  productService.save(p);
        
              List<Stock> stocks = new ArrayList<>();
              int quantity = Integer.parseInt(product.getQuantityDelivered());
              for(int i = 1; i<=quantity; i++){
                
                Stock stock = new Stock();
                stock.setPurchasedBy(null);
                stock.setCategory(pr.getCategory());
                stock.setProduct(pr);
                stock.setSerialNumber("nil");
                Stock s = stockService.save(stock);
                stocks.add(s);
              }
        
        model.addAttribute("stocks", stocks);
        model.addAttribute("addStock", true);
        return setUpModelStock(model);
    }
    public String setUpModel(ModelMap model){
         Stock stock = new Stock();
         model.addAttribute("pageTitle", "List Inventory");
        
         model.addAttribute("item", stock);
          model.addAttribute("apActive", "active");
         return "products-master";
    }
    
   
    public String setUpModelStock(ModelMap model){
        // Stock stock = new Stock();
         model.addAttribute("pageTitle", "List Inventory");
        
       //  model.addAttribute("item", stock);
          model.addAttribute("apActive", "active");
         return "stock-master";
    }
    
 
    public String setUpModel(ModelMap model, Product item, Company company) {
        model.addAttribute("item", item);
        model.addAttribute("pageTitle", "New Product");
        model.addAttribute("warrants", PageLists.getWarrant());
        model.addAttribute("processorList", PageLists.getProcessor());
        model.addAttribute("memoryList", PageLists.getMemory());
        model.addAttribute("hardDriveList", PageLists.getHardDrive());
        //model.addAttribute("suppliers", supplierService.findByCompany(company));
        model.addAttribute("addons", addonService.getAll());
         model.addAttribute("apActive", "active");
        return "products-master";
    }
    public String setUpPrinter(ModelMap model, Product item, Company company) {
        model.addAttribute("item", item);
        model.addAttribute("pageTitle", "New Product");        
        model.addAttribute("suppliers", supplierService.findByCompany(company));
        model.addAttribute("addons", addonService.getAll());
        model.addAttribute("clickNewPrinter", true);
        model.addAttribute("catridgeList", PageLists.getCatridge());
        model.addAttribute("yesNoList", PageLists.getYesNo());
        model.addAttribute("colorList", PageLists.getColor());
         model.addAttribute("apActive", "active");
        return "stock-master";
    }
    public String setUpService(ModelMap model, Product item, Company company){
         model.addAttribute("item", item);
         model.addAttribute("pageTitle", "New Service"); 
          model.addAttribute("clickNewService", true);
           model.addAttribute("apActive", "active");
         return "stock-master";
    }
    
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("item") @Valid Product product, BindingResult result, ModelMap model, HttpSession session){
         Company company = (Company) session.getAttribute("company");
         productValidator.validate(product, result);
         Category cat = categoryService.get(product.getCategoryId());
         
          model.addAttribute("categoryId", product.getCategoryId());
          model.addAttribute("name", cat.getName());
          model.addAttribute("brands", cat.getBrands());
          
          
          if (result.hasErrors()) {  
              if(cat.getName().contains("Laptop") || cat.getName().contains("Desktop")){
                  model.addAttribute("clickNewComputers", true);  
              }
              else if(cat.getName().equalsIgnoreCase("Printer")){
                    model.addAttribute("message", new AppMessage.MessageBuilder().build());
                    model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Invalid Fields").messageType(MessageType.ERROR).build());
                    setUpPrinter(model, product, company);
              }
              else if(cat.getName().contains("Access")){
                   model.addAttribute("clickNewAccessory", true);  
              }
              else if(cat.getName().contains("Service")){
                  model.addAttribute("message", new AppMessage.MessageBuilder().build());
                  model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Invalid Fields").messageType(MessageType.ERROR).build());
                  return setUpService(model, product, company);
              }
               else if(cat.getName().contains("Web")){
                  model.addAttribute("message", new AppMessage.MessageBuilder().build());
                  model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Invalid Fields").messageType(MessageType.ERROR).build());
                  return setUpService(model, product, company);
              }
              else if(cat.getName().equalsIgnoreCase("Monitor")){
                  model.addAttribute("clickNewMonitor", true);
              }
              else if(cat.getName().equalsIgnoreCase("Consumable")){
                  model.addAttribute("clickNewConsumable", true);  
              }
              else {
                  model.addAttribute("clickOther", true);  
              }
                model.addAttribute("message", new AppMessage.MessageBuilder().build());
                model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("Invalid Fields").messageType(MessageType.ERROR).build());           
                return setUpModel(model, product, company);  
           }
          
          
             Double rp = 1 * product.getSellingPrice();
             Double up = rp/1.15;
             product.setRetailPrice(Precision.round(rp, 2));
             product.setUnitPrice(Precision.round(up, 2));
             if(product.getQuantityDelivered()==null || product.getQuantityDelivered().equals("")){
                  product.setQuantityDelivered("0");
             }
           if(product.getId()!=null){
               Product prd = productService.get(product.getId());
               Specification sp = updateSpecification(product);   
               product.setSpecification(sp);
               product.setProductCode(prd.getProductCode());
               product.setDateCreated(prd.getDateCreated());
               product.setCreatedBy(prd.getCreatedBy());
               product.setCompany(prd.getCompany());
               product.setAvailableStock(prd.getAvailableStock());
               product.setQuantityDelivered(prd.getQuantityDelivered());
               product.setCategory(cat);
               productService.save(product);
               
               return "redirect:/inventory/list?id="+cat.getId()+"&name="+cat.getName();
           }
        
               Specification specification = new Specification(product.getDisplay(), product.getProcessor(), product.getMemory(),
                      product.getHardDrive(), product.getOs(), product.getCompatibility(), product.getMonitorSize(),
                      product.getResolution(), product.getVideoInput(), product.getCatridge(), product.getColor(),
                      product.getDutyCycle(), product.getDuplex(), product.getScanner(), product.getEthernet(), product.getWireless(), product.getFax());
            
              product.setSpecification(specification);
              product.setCategory(cat);
              product.setCompany(company);
              product.setAvailableStock(product.getQuantityDelivered()==null ? 0L : Long.parseLong(product.getQuantityDelivered()));
         
              
              Product pr = productService.save(product);
              String productCode = generateProductCode(product.getModel(), product.getBrand(), cat.getName(), product.getQuantityDelivered());
              pr.setProductCode(productCode);
              productService.save(pr);
               List<Stock> stocks = new ArrayList<>();
              int quantity = Integer.parseInt(product.getQuantityDelivered()==null ? "0" :product.getQuantityDelivered());
              for(int i = 1; i<=quantity; i++){                
                    Stock stock = new Stock();
                    stock.setPurchasedBy(null);
                    stock.setCategory(cat);
                    stock.setProduct(pr);
                    stock.setSerialNumber("nil");
                    Stock s = stockService.save(stock);
                    stocks.add(s);
              }
            
              
              model.addAttribute("message", new AppMessage.MessageBuilder().build());
              model.addAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("New Product Added Successfully").messageType(MessageType.MESSAGE).build());           
            switch (cat.getName()) {
            case "Laptop":
                model.addAttribute("stocks", stocks);
                 model.addAttribute("addStock", true);
                return  setUpModelStock(model);
             case "Desktop PC":              
                 model.addAttribute("stocks", stocks);
                 model.addAttribute("addStock", true);
                return  setUpModelStock(model);
            case "Accessory":
                Addon addon = new Addon();
                addon.setName(product.getBrand());
                addon.setUnitPrice(Precision.round(up, 2));
                addonService.save(addon);
                model.addAttribute("stocks",stocks);
                model.addAttribute("addStock", true);
                return  setUpModelStock(model);
            case "Monitor":           
                model.addAttribute("stocks", stocks);
                model.addAttribute("addStock", true);
                return  setUpModelStock(model);
            case "Consumable":             
                model.addAttribute("stocks", stocks);
                model.addAttribute("addStock", true);
                return  setUpModelStock(model);
            case "Printer":  
                model.addAttribute("stocks", stocks);
                model.addAttribute("addStock", true);
                return  setUpModelStock(model);
            default:
                 model.addAttribute("stocks", stocks);
                model.addAttribute("addStock", true);
                return  setUpModelStock(model);
        }
          
    }
    
    
   /* public String generateProductCode(String model, String brand, Long productId){
        String newModel = model;       
        if(model.length()>6){
           newModel =  model.substring(2, 2);          
        }        
        String productCode = brand.concat(newModel).concat(String.valueOf(productId));
        return productCode;
    }
*/
    public String generateProductCode(String model, String brand, String category, String quantity){
                    Random rand = new Random();
                    int value = rand.nextInt(99);
                    String newCate = category.substring(1,4);
                    String newBrand = "";
                    if(brand!=null){
                     newBrand = brand.substring(0, 2);
                    }
                    String newModel = "MOD";
                    if(model.length()>3){
                        newModel = model.substring(0, 3);
                    }
                    if(quantity==null){
                        quantity = "0";
                    }
                  String finalCode = newCate.concat(newBrand).concat(newModel);
                    String code = finalCode.substring(0, 6);
                    String productCode = String.valueOf(value).concat(code).concat(quantity);
                    return productCode.toUpperCase();
                }
    public Specification updateSpecification(Product p){
       Specification s = p.getSpecification();
               s.setProcessor(p.getProcessor());
               s.setMemory(p.getMemory());
               s.setHardDrive(p.getHardDrive());
               s.setDisplay(p.getDisplay());             
               s.setOs(p.getOs());
               s.setCompatibility(p.getCompatibility());
               s.setResolution(p.getResolution());
               s.setMonitorSize(p.getMonitorSize());
               s.setVideoInput(p.getVideoInput());
               s.setCatridge(p.getCatridge());
               s.setColor(p.getColor());
               s.setDutyCycle(p.getDutyCycle());
               s.setFax(p.getFax());
               s.setDuplex(p.getDuplex());
               s.setEthernet(p.getEthernet());
               s.setWireless(p.getWireless());
               s.setScanner(p.getScanner());
               return s;              
    }
 
}
