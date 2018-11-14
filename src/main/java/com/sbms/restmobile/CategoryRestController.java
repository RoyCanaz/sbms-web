/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.restmobile;

import com.sbms.domain.Category;
import com.sbms.domain.Company;
import com.sbms.dto.CategoryDTO;
import com.sbms.service.CategoryService;
import com.sbms.service.CompanyService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/rest/client")
public class CategoryRestController {
    @Resource
    private CategoryService categoryService;
    @Resource
    private CompanyService companyService;
    
    
    @GetMapping("/category/{companyId}")
    public List<CategoryDTO> listCatategory(@PathVariable("companyId") Long companyId) {
          System.err.println("=====================");
          System.err.println("Company Id "+companyId);
          System.err.println("=====================");        
           Company company = companyService.get(companyId);
           List<Category> list = categoryService.findByActiveAndCompany(Boolean.TRUE, company);
           List<CategoryDTO> categoryDTOs = new ArrayList<>();
           for(Category category : list){
               CategoryDTO categoryDTO = new CategoryDTO(category.getId(), category.getName(), category.getDescription());
               categoryDTOs.add(categoryDTO);
           }    
          return categoryDTOs;         
    }   
}
