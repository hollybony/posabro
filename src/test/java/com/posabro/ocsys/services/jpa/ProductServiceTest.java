/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import com.posabro.ocsys.domain.Product;
import com.posabro.ocsys.services.ProductService;
import com.posabro.services.AbstractServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Carlos Juarez
 */
public class ProductServiceTest extends AbstractServiceTest{
    
    @Autowired
    private ProductService productService;
    
    @Test
    public void testSaveProduct(){
        Product product = new Product("PR1","Alcohol","good alcohol");
        productService.saveProduct(product);
        Product foundProduct = productService.findProduct("PR1");
        assertNotNull(foundProduct);
        assertEquals("PR1", foundProduct.getId());
    }
    
}
