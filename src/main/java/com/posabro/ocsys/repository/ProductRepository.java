/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.repository;

import com.posabro.ocsys.domain.Product;
import com.posabro.ocsys.domain.ProductType;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Carlos Juarez
 */
public interface ProductRepository extends PagingAndSortingRepository<Product,ProductType>{
    
}
