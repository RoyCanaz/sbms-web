/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.restmobile;

import com.sbms.domain.Category;
import com.sbms.domain.Company;
import com.sbms.domain.Product;
import com.sbms.domain.Specification;
import com.sbms.domain.Stock;
import com.sbms.domain.Supplier;
import com.sbms.dto.StockDTO;
import com.sbms.service.BrandService;
import com.sbms.service.CategoryService;
import com.sbms.service.CompanyService;
import com.sbms.service.ProductService;
import com.sbms.service.StockService;
import com.sbms.service.SupplierService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.math3.util.Precision;
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
@RequestMapping("/rest/client/stock")
public class StockRestController {
    @Resource
    private StockService stockService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private BrandService brandService;
    @Resource
    private SupplierService supplierService;
    
    @Resource
    private ProductService productService;
    
    @Resource
    private CompanyService companyService;
    
    public static final String ENTITY_NAME = "stock";
    
    /**
     * POST /updateSerialNumber
     * @param dTO 
     * @return The Response Entity with status 201
     */
    
    
    @PostMapping(value = "/updateSerialNumber", consumes = "application/json")
    public ResponseEntity updateSerialNumber(@RequestBody StockDTO dTO){
        Stock stock = stockService.get(dTO.getUuid());
       
        stock.setSerialNumber(dTO.getSerialNumber());
        stockService.save(stock);
        return new ResponseEntity(HttpStatus.OK);       
    }
    @PostMapping(value = "/updateSn", consumes = "application/json")
    public ResponseEntity updateSerialNumberMobile(@RequestBody StockDTO dTO){
        System.out.println("Rest request to update Serial Number for Entity_"+ ENTITY_NAME);
        
        Stock stock = stockService.get(dTO.getId());
       
        stock.setSerialNumber(dTO.getSerialNumber());
        stockService.save(stock);
        return new ResponseEntity(HttpStatus.OK);       
    }
  
    
    /**
     * Get /available/{categoryId}
     * @param categoryId category id of the stock to be fetched
     * @return Returns available stock with boolean true(available) by category in stock table.
     */
    @GetMapping("/available/{categoryId}")
    public List<Integer> getAvailableStock(@PathVariable("categoryId") Long categoryId){
        
        
        
        List<Stock> listAvailable = new ArrayList<>(); 
        List<Integer> availableStock = new ArrayList<>();  
        Category category = categoryService.get(categoryId);
        if(category!=null){
            List<Stock> all = stockService.getByCategory(category);
                all.forEach((stock) -> {
                    if(stock.getAvailable()) {
                        listAvailable.add(stock);
                    }           
                });   
            
             availableStock.add(0, listAvailable.size());
              availableStock.add(1, all.size());
             return availableStock;
                
        }
       return availableStock;
    }
    @GetMapping("/brands")
    public Set<String> getBrands(){
        Set<String> list = new HashSet();
       brandService.getAll().forEach( (t) -> {        
           list.add(t.getName());
       });
       return list;
    }
    
    @GetMapping("/suppliers/{companyId}")
    public List<Supplier> getSuppliers(@PathVariable("companyId") Long companyId) {   
           Company company = companyService.get(companyId);
           List<Supplier> list = supplierService.findByCompany(company);           
           return list;         
    } 
    @PostMapping("/addproduct")
    public ResponseEntity<List<StockDTO>> addProduct(@RequestBody Product product){
             Category category = categoryService.get(product.getCategoryId());
             Company company = companyService.get(product.getCompanyId());
        
             Double rp = 1 * product.getSellingPrice();
             Double up = rp/1.15;
             product.setRetailPrice(Precision.round(rp, 2));
             product.setUnitPrice(Precision.round(up, 2));
             Set<Supplier> suppliers = product.getSuppliers();
             Set<Supplier> list = new HashSet<>();
             suppliers.forEach(supplier -> {
                 list.add(supplierService.get(supplier.getId()));
             });
             product.setSuppliers(list);
             Specification specification = new Specification(product.getDisplay(), product.getProcessor(), product.getMemory(),
                      product.getHardDrive(), product.getOs(), product.getCompatibility(), product.getMonitorSize(),
                      product.getResolution(), product.getVideoInput(), product.getCatridge(), product.getColor(),
                      product.getDutyCycle(), product.getDuplex(), product.getScanner(), product.getEthernet(), product.getWireless(), product.getFax());
            
              product.setSpecification(specification);
              product.setCategory(category);
              product.setCompany(company);
              product.setAvailableStock(Long.parseLong(product.getQuantityDelivered()));
              Product pr = productService.save(product);
              
               List<StockDTO> stocks = new ArrayList<>();
              int quantity = Integer.parseInt(product.getQuantityDelivered());
              for(int i = 1; i<=quantity; i++){
                
                Stock stock = new Stock();
                stock.setPurchasedBy(null);
                stock.setCategory(pr.getCategory());
                stock.setProduct(pr);
                stock.setSerialNumber("nil");
                Stock s = stockService.save(stock);
                StockDTO stockDTO = new StockDTO(s.getId(), s.getDatePurchased(), s.getSerialNumber(), s.getProduct().getId(), s.getCategory().getId());
                
                stocks.add(stockDTO);
              }
        
        return new ResponseEntity(stocks, HttpStatus.OK);
    }
}
