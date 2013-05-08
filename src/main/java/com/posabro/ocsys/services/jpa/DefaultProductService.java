/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.google.common.collect.Lists;
import com.posabro.ocsys.domain.Product;
import com.posabro.ocsys.repository.ProductRepository;
import com.posabro.ocsys.services.ProductService;
import java.util.List;
import javax.persistence.PersistenceException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Carlos Juarez
 */
@Service("productService")
@Repository
@Transactional
public class DefaultProductService implements ProductService{

    final org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultProductService.class);
    
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    @Transactional(readOnly=true)
    public Product findProduct(String id) {
        return productRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Product> getAllProducts() {
        return Lists.newArrayList(productRepository.findAll());
    }

    @Override
    public void saveProduct(Product product) {
        if(!productRepository.exists(product.getId())){
            productRepository.save(product);
        }else{
            throw new JpaSystemException(new PersistenceException("product " + product.getId() + " already exists"));
        }
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void removeProduct(String id) {
        productRepository.delete(id);
    }
    
}
