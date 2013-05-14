/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.google.common.collect.Lists;
import com.posabro.ocsys.domain.BranchPK;
import com.posabro.ocsys.domain.Container;
import com.posabro.ocsys.domain.ContainerType;
import com.posabro.ocsys.domain.ConversionFactor;
import com.posabro.ocsys.domain.ConversionFactorPK;
import com.posabro.ocsys.domain.CustomerPK;
import com.posabro.ocsys.domain.FacilityPK;
import com.posabro.ocsys.domain.OutboundBol;
import com.posabro.ocsys.domain.OutboundBolPK;
import com.posabro.ocsys.domain.Product;
import com.posabro.ocsys.domain.ProductType;
import com.posabro.ocsys.domain.UnitOfMeasurement;
import com.posabro.ocsys.repository.ConversionFactorRepository;
import com.posabro.ocsys.repository.OutboundBolRepository;
import com.posabro.ocsys.services.BranchService;
import com.posabro.ocsys.services.CarrierService;
import com.posabro.ocsys.services.ContainerService;
import com.posabro.ocsys.services.CustomerService;
import com.posabro.ocsys.services.FacilityService;
import com.posabro.ocsys.services.OutboundBolService;
import com.posabro.ocsys.services.ProductService;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Carlos Juarez
 */
@Service("outboundBolService")
@Repository
@Transactional
public class DefaultOutboundBolService implements OutboundBolService{
    
    @Autowired
    private OutboundBolRepository outboundBolRepository;
    
    @Autowired
    private BranchService branchService;
    
    @Autowired
    private ContainerService containerService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private FacilityService facilityService;

    @Autowired
    private CarrierService carrierService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private ConversionFactorRepository conversionFactorRepository;
    
    @Override
    @Transactional(readOnly=true)
    public OutboundBol findOutboundBol(OutboundBolPK id) {
        return outboundBolRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<OutboundBol> getAllOutboundBols() {
        return Lists.newArrayList(outboundBolRepository.findAll());
    }

    @Override
    public void saveOutboundBol(BranchPK branchPK, OutboundBol outboundBol) {
        String bolId = branchService.generateNewConsecutive(branchPK);
        outboundBol.setOutboundBolPK(new OutboundBolPK(branchPK.getCompanyId(), branchPK.getId(), bolId));
        outboundBol.getOutboundBolPK().setId(bolId);
        if(outboundBol.getContainerType().equals(ContainerType.ISO)){
            Container foundContainer = containerService.findContainer(outboundBol.getContainerId());
            if(foundContainer==null){
                throw new JpaSystemException(new PersistenceException("container id  " + outboundBol.getContainerId() + " doesn't exist"));
            }
            if(outboundBol.getDriver()==null||outboundBol.getDriver().equals("")){
                throw new JpaSystemException(new PersistenceException("Since it is a ISO container, driver is required"));
            }
            if(outboundBol.getProductId().equals(ProductType.NACNL)){
                outboundBol.getContent().setContainedLts(foundContainer.getLtsFillCapacity());
                ConversionFactor ltsToGalls = conversionFactorRepository.findOne(new ConversionFactorPK(UnitOfMeasurement.LTS,UnitOfMeasurement.GALS));
                if(ltsToGalls==null){
                    throw new JpaSystemException(new PersistenceException("There is no conversion factor from  " + UnitOfMeasurement.LTS + " to " + UnitOfMeasurement.GALS));
                }
                outboundBol.getContent().setContainedGallons(ltsToGalls.convert(foundContainer.getLtsFillCapacity()));
            }
        }else{
            outboundBol.setTareWeight(BigDecimal.ZERO);
            outboundBol.setNetWeight(BigDecimal.ZERO);
            outboundBol.setGrossWeight(BigDecimal.ZERO);
        }
        ConversionFactor kgsToLbs = conversionFactorRepository.findOne(new ConversionFactorPK(UnitOfMeasurement.KGS, UnitOfMeasurement.LBS));
        if(kgsToLbs==null){
            throw new JpaSystemException(new PersistenceException("There is no conversion factor from  " + UnitOfMeasurement.KGS + " to " + UnitOfMeasurement.LBS));
        }
        outboundBol.getContent().setContainedLbs(kgsToLbs.convert(outboundBol.getContent().getContainedKgs()));
        //constraints
        if(customerService.findCustomer(new CustomerPK(outboundBol.getCustomerId(), branchPK.getCompanyId()))==null){
            throw new JpaSystemException(new PersistenceException("customer id  " + outboundBol.getCustomerId() + " doesn't exist"));
        }
        if(customerService.findCustomer(new CustomerPK(outboundBol.getCustomerId(), branchPK.getCompanyId()))==null){
            throw new JpaSystemException(new PersistenceException("customer id  " + outboundBol.getCustomerId() + " doesn't exist"));
        }
        FacilityPK facilityPK = new FacilityPK(outboundBol.getFacilityId(), outboundBol.getCustomerId(), branchPK.getCompanyId());
        if(facilityService.findFacility(facilityPK)==null){
            throw new JpaSystemException(new PersistenceException("facility  " + facilityPK + " doesn't exist"));
        }
        if(carrierService.findCarrier(outboundBol.getCarrierId())==null){
            throw new JpaSystemException(new PersistenceException("carrier Id  " + outboundBol.getCarrierId() + " doesn't exist"));
        }
        Product foundProduct = productService.findProduct(outboundBol.getProductId());
        if(foundProduct==null){
            throw new JpaSystemException(new PersistenceException("Product Id  " + outboundBol.getProductId() + " doesn't exist"));
        }else{
            outboundBol.setProductBolDescription(foundProduct.getBolDescription());
        }
        outboundBolRepository.save(outboundBol);
    }

    @Override
    public void removeOutboundBol(OutboundBolPK id) {
        outboundBolRepository.delete(id);
    }
    
}
