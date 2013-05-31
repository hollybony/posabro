/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.repository;

import com.posabro.ocsys.domain.OutboundBol;
import com.posabro.ocsys.domain.OutboundBolPK;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Contains all the repository methods related to <code>OutboundBol</code>s
 * 
 * @author Carlos Juarez
 */
public interface OutboundBolRepository extends PagingAndSortingRepository<OutboundBol,OutboundBolPK>{
    
}
