/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.google.common.collect.Lists;
import com.posabro.ocsys.domain.Product;
import com.posabro.ocsys.domain.ProductType;
import com.posabro.ocsys.repository.ProductRepository;
import com.posabro.ocsys.services.ProductService;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.PersistenceException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Defaults implementation of <code>ProductService</code>
 * 
 * @author Carlos Juarez
 */
@Service("productService")
@Repository
@Transactional
public class DefaultProductService implements ProductService{

    /**
     * The logger
     */
    final org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultProductService.class);
    
    /**
     * The productRepository
     */
    @Autowired
    private ProductRepository productRepository;
    
    /**
     * @see ProductService#findProduct(com.posabro.ocsys.domain.ProductType) 
     * 
     * @param id
     * @return 
     */
    @Override
    @Transactional(readOnly=true)
    public Product findProduct(ProductType id) {
        Product product = productRepository.findOne(id);
        if(product!=null){
            if(product.getId().equals(ProductType.NACNL)){
                product.setNacnPct(new BigDecimal("30.5"));
                product.setSpecificGravity(new BigDecimal("1.176"));
                product.setPh(new BigDecimal("12.50"));
            }else if(product.getId().equals(ProductType.NACNB)){
                product.setNacnPct(new BigDecimal("98.0"));
                product.setSpecificGravity(null);
                product.setPh(null);
            }else if(product.getId().equals(ProductType.NACNH)){
                product.setNacnPct(new BigDecimal("98.0"));
                product.setSpecificGravity(null);
                product.setPh(null);
            }
        }
        return productRepository.findOne(id);
    }

    /**
     * @see ProductService#getAllProducts() 
     * 
     * @return 
     */
    @Override
    @Transactional(readOnly=true)
    public List<Product> getAllProducts() {
        return Lists.newArrayList(productRepository.findAll());
    }

    /**
     * @see ProductService#saveProduct(com.posabro.ocsys.domain.Product) 
     * 
     * @param product 
     */
    @Override
    public void saveProduct(Product product) {
        if(!productRepository.exists(product.getId())){
            productRepository.save(product);
        }else{
            throw new JpaSystemException(new PersistenceException("product " + product.getId() + " already exists"));
        }
    }

    /**
     * @see ProductService#updateProduct(com.posabro.ocsys.domain.Product) 
     * 
     * @param product 
     */
    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    /**
     * @see ProductService#removeProduct(com.posabro.ocsys.domain.ProductType) 
     * 
     * @param id 
     */
    @Override
    public void removeProduct(ProductType id) {
        productRepository.delete(id);
    }
    
}
