/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.posabro.ocsys.domain.Address;
import com.posabro.ocsys.domain.Branch;
import com.posabro.ocsys.domain.BranchPK;
import com.posabro.ocsys.domain.Carrier;
import com.posabro.ocsys.domain.Company;
import com.posabro.ocsys.domain.Container;
import com.posabro.ocsys.domain.ContainerType;
import com.posabro.ocsys.domain.ConversionFactor;
import com.posabro.ocsys.domain.ConversionFactorPK;
import com.posabro.ocsys.domain.Country;
import com.posabro.ocsys.domain.Customer;
import com.posabro.ocsys.domain.CustomerPK;
import com.posabro.ocsys.domain.Facility;
import com.posabro.ocsys.domain.FacilityPK;
import com.posabro.ocsys.domain.OutboundBol;
import com.posabro.ocsys.domain.OutboundBolPK;
import com.posabro.ocsys.domain.Product;
import com.posabro.ocsys.domain.ProductType;
import com.posabro.ocsys.domain.State;
import com.posabro.ocsys.domain.StatePK;
import com.posabro.ocsys.domain.UnitOfMeasurement;
import com.posabro.ocsys.services.BranchService;
import com.posabro.ocsys.services.CarrierService;
import com.posabro.ocsys.services.CompanyService;
import com.posabro.ocsys.services.ContainerService;
import com.posabro.ocsys.services.ConversionFactorService;
import com.posabro.ocsys.services.CountryService;
import com.posabro.ocsys.services.CustomerService;
import com.posabro.ocsys.services.FacilityService;
import com.posabro.ocsys.services.OutboundBolService;
import com.posabro.ocsys.services.ProductService;
import com.posabro.ocsys.services.StateService;
import com.posabro.services.AbstractServiceTest;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Carlos Juarez
 */
public class OutboundBolServiceTest extends AbstractServiceTest{
    
    @Autowired
    private OutboundBolService outboundBolService;
    
    @Autowired
    private BranchService branchService;
    
    @Autowired
    private CompanyService companyService;
    
    @Autowired
    private ContainerService containerService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private CountryService countryService;
    
    @Autowired
    private StateService stateService;
    
    @Autowired
    private CarrierService carrierService;
    
    @Autowired
    private ConversionFactorService conversionFactorService;
    
    @Autowired
    private FacilityService facilityService;
    
    @Before
    public void loadData(){
        Company company = new Company("BALA", "By the way");
        companyService.saveCompany(company);
        Branch branch = new Branch("B1", company);
        branchService.saveBranch(branch);
        containerService.saveContainer(new Container("CON1", new BigDecimal("20.21") , new BigDecimal("22.1"), new BigDecimal("200.20")));
        productService.saveProduct(new Product(ProductType.NACNL, "NaCN liquido", "NaCN liquido bol"));
        Country uruguay = new Country("UR", "Uruguay");
        countryService.saveCountry(uruguay);
        State monteVideo = new State("MO","Monte video", uruguay);
        stateService.saveState(new State("MO","Monte video", uruguay));
        Address address = new Address("Gardel 500", "", 19876, "Cordoba Cd", monteVideo);
        customerService.saveCustomer(new Customer("CUST1", "Juan Glz S.A", "TX1", company, address));
        facilityService.saveFacility(new Facility("FAC1","CUST1","BALA","Warehouse",address));
        carrierService.saveCarrier(new Carrier("CAR1", "Transporter estrella blanca", "TAX3", address));
        conversionFactorService.saveConversionFactor(new ConversionFactor(UnitOfMeasurement.LTS, UnitOfMeasurement.GALS, new BigDecimal("0.26418")));
        conversionFactorService.saveConversionFactor(new ConversionFactor(UnitOfMeasurement.KGS, UnitOfMeasurement.LBS,  new BigDecimal("2.2046")));
    }
    
    @After
    public void removeData(){
        branchService.removeBranch(new BranchPK("B1", "BALA"));
        containerService.removeContainer("CON1");
        productService.removeProduct(ProductType.NACNL);
        facilityService.removeFacility(new FacilityPK("FAC1", "CUST1", "BALA"));
        customerService.removeCustomer(new CustomerPK("CUST1", "BALA"));
        companyService.removeCompany("BALA");
        carrierService.removeCarrier("CAR1");
        stateService.removeState(new StatePK("MO", "UR"));
        countryService.removeCountry("UR");
        conversionFactorService.removeConversionFactor(new ConversionFactorPK(UnitOfMeasurement.LTS, UnitOfMeasurement.GALS));
        conversionFactorService.removeConversionFactor(new ConversionFactorPK(UnitOfMeasurement.KGS, UnitOfMeasurement.LBS));
    }
    
    @Test
    public void testSaveISO_NACNL_OutboundBol(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        //add unit of measurement
        OutboundBol outboundBol = new OutboundBol();
        outboundBol.setCarrierId("CAR1");
        outboundBol.setContainerId("CON1");
        outboundBol.setCustomerId("CUST1");
        outboundBol.setShipmentDate(new Date());
        outboundBol.setBolDate(new Date());
        outboundBol.setContainerType(ContainerType.ISO);
        outboundBol.setFacilityId("FAC1");
        outboundBol.setDriver("the driver");
        //
        outboundBol.getInboundBolData().setInbouundContId1("CON1");
        outboundBol.setProductId(ProductType.NACNL);
        outboundBolService.saveOutboundBol(new BranchPK("B1", "BALA"), outboundBol);
        OutboundBol foundOutboundBol = outboundBolService.findOutboundBol(new OutboundBolPK("BALA", "B1", year + "0001"));
        assertNotNull(foundOutboundBol);
        assertEquals(new BigDecimal("5.25"), foundOutboundBol.getContent().getContainedGallons());
        //cleanup
        outboundBolService.removeOutboundBol(outboundBol.getOutboundBolPK());
    }
    
   
}
