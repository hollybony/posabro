/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services;

import com.posabro.ocsys.domain.Product;
import com.posabro.ocsys.domain.ProductType;
import java.util.List;

/**
 * Contains all the service methods of <code>Product</code>s
 * 
 * @author Carlos Juarez
 */
public interface ProductService {
    
    /**
     * Finds a product by id
     * 
     * @param id - the id with which the product is looked for
     * @return the product found
     */
    public Product findProduct(ProductType id);
    
    /**
     * Gets all the products
     * 
     * @return the products found
     */
    public List<Product> getAllProducts();
    
    /**
     * Saves a product
     * 
     * @param product - the product to save
     */
    public void saveProduct(Product product);
    
    /**
     * Updates a product
     * 
     * @param product - the product to update
     */
    public void updateProduct(Product product);
    
    /**
     * Removes a product
     * 
     * @param id - the id of the product to be removed
     */
    public void removeProduct(ProductType id);
    
}
