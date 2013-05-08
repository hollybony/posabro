/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services;

import com.posabro.ocsys.domain.Product;
import java.util.List;

/**
 *
 * @author Carlos Juarez
 */
public interface ProductService {
    
    public Product findProduct(String id);
    
    public List<Product> getAllProducts();
    
    public void saveProduct(Product product);
    
    public void updateProduct(Product product);
    
    public void removeProduct(String id);
    
}
