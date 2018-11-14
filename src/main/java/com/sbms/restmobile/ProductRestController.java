/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.restmobile;

import com.sbms.domain.Category;
import com.sbms.domain.Company;
import com.sbms.domain.Product;
import com.sbms.dto.ProductDTO;
import com.sbms.service.BrandService;
import com.sbms.service.CategoryService;
import com.sbms.service.CompanyService;
import com.sbms.service.ProductService;
import com.sbms.service.StockService;
import com.sbms.service.SupplierService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/rest/client/product")
public class ProductRestController {
    
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
    
    @GetMapping("/category/{company}/{category}")
    public ResponseEntity<List<ProductDTO>> getproductsByCategory(@PathVariable("company") Long companyId, @PathVariable("category") Long categoryId){
        Company company = companyService.get(companyId);
        Category category = categoryService.get(categoryId);
        List<Product> products = productService.getByCategoryAndCompany(category, company);
        List<ProductDTO> productDTO = new ArrayList<>();
        products.forEach(p->{
            ProductDTO o = new ProductDTO(p.getId(), p.getModel(), p.getBrand(), p.getProductCode(), p.getWarranty(), p.getMonthYear(), p.getDescription(), p.getQuantityDelivered(), p.getAvailableStock(), p.getSellingPrice(), p.getUnitPrice(), p.getSpecification());
            productDTO.add(o);
        
        });
        
      return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }
    @GetMapping("/all/{company}")
    public ResponseEntity<List<ProductDTO>> getAllProducts(@PathVariable("company") Long companyId){
        Company company = companyService.get(companyId);
        
        List<Product> products = productService.findByCompany(company);
        List<ProductDTO> productDTO = new ArrayList<>();
        products.forEach(p->{
            ProductDTO o = new ProductDTO(p.getId(), p.getModel(), p.getBrand(), p.getProductCode(), p.getWarranty(), p.getMonthYear(), p.getDescription(), p.getQuantityDelivered(), p.getAvailableStock(), p.getSellingPrice(), p.getUnitPrice(), p.getSpecification());
            productDTO.add(o);
        
        });
        
      return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }
     @GetMapping("/id/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long id){      
        Product p = productService.get(id);      
            ProductDTO product = new ProductDTO(p.getId(), p.getModel(), p.getBrand(), p.getProductCode(), p.getWarranty(), p.getMonthYear(), p.getDescription(), p.getQuantityDelivered(), p.getAvailableStock(), p.getSellingPrice(), p.getUnitPrice(), p.getSpecification());
     
      return new ResponseEntity<>(product, HttpStatus.OK);
    }
    
    
    
}
