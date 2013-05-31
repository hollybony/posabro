/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.web.controller;

import com.posabro.ocsys.domain.Product;
import com.posabro.ocsys.domain.ProductType;
import com.posabro.ocsys.services.ProductService;
import com.posabro.web.controller.ValidationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Web controller that processes all the requests related to products
 * 
 * @author Carlos Juarez
 */
@Controller
@RequestMapping("/productController/*")
public class ProductController extends ValidationController{
    
    /**
     * The productService
     */
    @Autowired
    private ProductService productService;
    
    /**
     * find the <code>Product</code> which has the id given
     * 
     * @param id - the id to match
     * @return the product found
     */
    @RequestMapping(value="findProductById", method = RequestMethod.POST)
    public @ResponseBody Product findProductById(@RequestBody String id){
        try{
            ProductType productType = ProductType.valueOf(id);
            return productService.findProduct(productType);
        }catch(IllegalArgumentException ex){
            return null;
        }
    }
    
}
