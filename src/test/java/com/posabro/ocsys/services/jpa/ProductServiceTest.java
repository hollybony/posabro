/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.posabro.ocsys.domain.Product;
import com.posabro.ocsys.domain.ProductType;
import com.posabro.ocsys.services.ProductService;
import com.posabro.services.AbstractServiceTest;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Carlos Juarez
 */
public class ProductServiceTest extends AbstractServiceTest{
    
    private final Product aProduct = new Product(ProductType.NACNB,"Alcohol","good alcohol");
    
    private final List<Product> someProducts = Arrays.asList(
            new Product(ProductType.NACNH, "NC1 somethin", null),
            new Product(ProductType.NACNL, "AC3 something", "Bla bla"));
    
    @Autowired
    private ProductService productService;
    
    @Test
    public void testProductServiceUpdates(){
        //save
        productService.saveProduct(aProduct);
        //find
        Product foundProduct = productService.findProduct(ProductType.NACNB);
        assertNotNull(foundProduct);
        assertEquals(ProductType.NACNB, foundProduct.getId());
        assertEquals("Alcohol", foundProduct.getDescription());
        //remove
        productService.removeProduct(ProductType.NACNB);
        assertNull(productService.findProduct(ProductType.NACNB));
    }
    
    @Test
    public void testProductServiceQueries(){
        for(Product product : someProducts){
            productService.saveProduct(product);
        }
        List<Product> allProducts = productService.getAllProducts();
        assertTrue(someProducts.size()==allProducts.size());
        for(Product product : allProducts){
            productService.removeProduct(product.getId());
        }
    }
    
}
