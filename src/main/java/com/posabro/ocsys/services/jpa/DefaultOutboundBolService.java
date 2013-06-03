/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.google.common.collect.Lists;
import com.posabro.ocsys.domain.BillOfLading;
import com.posabro.ocsys.domain.BranchPK;
import com.posabro.ocsys.domain.Company;
import com.posabro.ocsys.domain.Container;
import com.posabro.ocsys.domain.ContainerType;
import com.posabro.ocsys.domain.ConversionFactor;
import com.posabro.ocsys.domain.ConversionFactorPK;
import com.posabro.ocsys.domain.Customer;
import com.posabro.ocsys.domain.CustomerPK;
import com.posabro.ocsys.domain.Facility;
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
import com.posabro.ocsys.services.CompanyService;
import com.posabro.ocsys.services.ContainerService;
import com.posabro.ocsys.services.CustomerService;
import com.posabro.ocsys.services.FacilityService;
import com.posabro.ocsys.services.OutboundBolService;
import com.posabro.ocsys.services.ProductService;
import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Defaults implementation of <code>OutboundBolService</code>
 * 
 * @author Carlos Juarez
 */
@Service("outboundBolService")
@Repository
@Transactional
public class DefaultOutboundBolService implements OutboundBolService{
    
    /**
     * The outboundBolRepository
     */
    @Autowired
    private OutboundBolRepository outboundBolRepository;
    
    /**
     * The branchService
     */
    @Autowired
    private BranchService branchService;
    
    /**
     * The containerService
     */
    @Autowired
    private ContainerService containerService;
    
    /**
     * The customerService
     */
    @Autowired
    private CustomerService customerService;
    
    /**
     * The facilityService
     */
    @Autowired
    private FacilityService facilityService;

    /**
     * The carrierService
     */
    @Autowired
    private CarrierService carrierService;
    
    /**
     * The productService
     */
    @Autowired
    private ProductService productService;
    
    /**
     * The conversionFactorRepository
     */
    @Autowired
    private ConversionFactorRepository conversionFactorRepository;
    
    /**
     * @see OutboundBolService#findOutboundBol(com.posabro.ocsys.domain.OutboundBolPK) 
     * 
     * @param id
     * @return 
     */
    @Override
    @Transactional(readOnly=true)
    public OutboundBol findOutboundBol(OutboundBolPK id) {
        return outboundBolRepository.findOne(id);
    }
    
    /**
     * @see OutboundBolService#findBillOfLading(com.posabro.ocsys.domain.OutboundBolPK) 
     * 
     * @param id
     * @return 
     */
    @Override
    @Transactional(readOnly=true)
    public BillOfLading findBillOfLading(OutboundBolPK id) {
        OutboundBol outboundBol = outboundBolRepository.findOne(id);
        if(outboundBol==null){
            return null;
        }
        Customer customer = customerService.findCustomer(new CustomerPK(outboundBol.getCustomerId(), outboundBol.getOutboundBolPK().getCompanyId()));
        Facility facility = facilityService.findFacilityById(new FacilityPK(outboundBol.getFacilityId(), outboundBol.getCustomerId(), outboundBol.getOutboundBolPK().getCompanyId()));
        BillOfLading billOfLading = new BillOfLading(outboundBol, customer, facility);
        return billOfLading;
    }

    /**
     * @see OutboundBolService#getAllOutboundBols() 
     * 
     * @return 
     */
    @Override
    @Transactional(readOnly=true)
    public List<OutboundBol> getAllOutboundBols() {
        return Lists.newArrayList(outboundBolRepository.findAll());
    }

    /**
     * @see OutboundBolService#saveOutboundBol(com.posabro.ocsys.domain.BranchPK, com.posabro.ocsys.domain.OutboundBol) 
     * 
     * @param branchPK
     * @param outboundBol
     * @return 
     */
    @Override
    public String saveOutboundBol(BranchPK branchPK, OutboundBol outboundBol) {
        //generating PK
        String bolId = branchService.generateNewConsecutive(branchPK);
        outboundBol.setOutboundBolPK(new OutboundBolPK(branchPK.getCompanyId(), branchPK.getId(), bolId));
        outboundBol.getOutboundBolPK().setId(bolId);
        //checking customer constraint
        if(customerService.findCustomer(new CustomerPK(outboundBol.getCustomerId(), branchPK.getCompanyId()))==null){
            throw new JpaSystemException(new PersistenceException("customer id  " + outboundBol.getCustomerId() + " doesn't exist"));
        }
        //checking carrier constraint
        if(carrierService.findCarrier(outboundBol.getCarrierId())==null){
            throw new JpaSystemException(new PersistenceException("carrier Id  " + outboundBol.getCarrierId() + " doesn't exist"));
        }
        //checking facility constraint
        FacilityPK facilityPK = new FacilityPK(outboundBol.getFacilityId(), outboundBol.getCustomerId(), branchPK.getCompanyId());
        if(facilityService.findFacilityById(facilityPK)==null){
            throw new JpaSystemException(new PersistenceException("facility  " + facilityPK + " doesn't exist"));
        }
        //checking product costraint
        Product productSelected = productService.findProduct(outboundBol.getProductId());
        if(productSelected==null){
            throw new JpaSystemException(new PersistenceException("Product Id  " + outboundBol.getProductId() + " doesn't exist"));
        }
        //setting product details
        outboundBol.setProductBolDescription(productSelected.getBolDescription());
        outboundBol.setNacnPct(productSelected.getNacnPct());
        outboundBol.setSpecificGravity(productSelected.getSpecificGravity());
        outboundBol.setPh(productSelected.getPh());
        //if Container type ISO
        if(outboundBol.getContainerType().equals(ContainerType.ISO)){
            //checking container constraint
            Container containerSelected = containerService.findContainer(outboundBol.getContainerId());
            if(containerSelected==null){
                throw new JpaSystemException(new PersistenceException("Since the container type is ISO, container id  " + outboundBol.getContainerId() + " must exist"));
            }
            //checking driver constraint
            if(outboundBol.getDriver()==null||outboundBol.getDriver().equals("")){
                throw new JpaSystemException(new PersistenceException("Since it is a ISO container, driver is required"));
            }
            //setting tara weight
            outboundBol.setTareWeight(containerSelected.getTareWeight());
            //setting net weight can cause null pointer exception
            //TO DO causes null pointer exception when the product is no liquid due to getSpecificGravity is null
            outboundBol.setNetWeight(containerSelected.getLtsFillCapacity().multiply(outboundBol.getSpecificGravity()).add(containerSelected.getTareWeight()));
            //if Product type NACNL
            if(outboundBol.getProductId().equals(ProductType.NACNL)){
                outboundBol.getContent().setContainedLts(containerSelected.getLtsFillCapacity());
                outboundBol.getContent().setContainedKgs(outboundBol.getContent().getContainedLts().multiply(outboundBol.getSpecificGravity()).multiply(outboundBol.getPh()));
            }
            //TO DO setting gross weight
//            outboundBol.setGrossWeight(containerSelected.getTareWeight().add(outboundBol.getContent().getContainedKgs()));
        }else{//RAILCAR container
            outboundBol.setDriver("");
            outboundBol.setTareWeight(null);
            outboundBol.setNetWeight(null);
            outboundBol.setGrossWeight(null);
        }
        //calculating contained gallons from lts, only NaCN liquid contains it
        if(outboundBol.getProductId().equals(ProductType.NACNL)){
            //TO DO when NACNL and RAILCAR getContainedLts comes null
            if(outboundBol.getContent().getContainedLts()==null){
                throw new JpaSystemException(new PersistenceException("Contained Lts is required as it is NACNL"));
            }
            ConversionFactor ltsToGalls = conversionFactorRepository.findOne(new ConversionFactorPK(UnitOfMeasurement.LTS,UnitOfMeasurement.GALS));
            if(ltsToGalls==null){
                throw new JpaSystemException(new PersistenceException("There is no conversion factor from  " + UnitOfMeasurement.LTS + " to " + UnitOfMeasurement.GALS));
            }
            outboundBol.getContent().setContainedGallons(ltsToGalls.convert(outboundBol.getContent().getContainedLts()));
        }else{
            outboundBol.getContent().setContainedLts(null);
            outboundBol.getContent().setContainedGallons(null);
        }
        //checking contained kgs not null
        if(outboundBol.getContent().getContainedKgs()==null){
            throw new JpaSystemException(new PersistenceException("Contained Kgs is required"));
        }
        //calculating contained lbs
        ConversionFactor kgsToLbs = conversionFactorRepository.findOne(new ConversionFactorPK(UnitOfMeasurement.KGS, UnitOfMeasurement.LBS));
        if(kgsToLbs==null){
            throw new JpaSystemException(new PersistenceException("There is no conversion factor from  " + UnitOfMeasurement.KGS + " to " + UnitOfMeasurement.LBS));
        }
        outboundBol.getContent().setContainedLbs(kgsToLbs.convert(outboundBol.getContent().getContainedKgs()));
        outboundBolRepository.save(outboundBol);
        return bolId;
    }

    /**
     * @see OutboundBolService#removeOutboundBol(com.posabro.ocsys.domain.OutboundBolPK) 
     * @param id 
     */
    @Override
    public void removeOutboundBol(OutboundBolPK id) {
        outboundBolRepository.delete(id);
    }
    
}
