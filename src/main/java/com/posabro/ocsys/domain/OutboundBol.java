/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import com.posabro.auditor.domain.AuditData;
import com.posabro.auditor.domain.Auditable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Represents an Out bound boL
 * 
 * @author Carlos Juarez
 */
@Entity
@Table(name="OUTBOUND_BOLS")
public class OutboundBol implements Serializable, Auditable{
    
    /**
     * The primary key
     */
    @EmbeddedId
    private OutboundBolPK outboundBolPK;
    
    /**
     * The auditData
     */
    @Embedded
    private AuditData auditData;
    
    /**
     * The inboundBolData
     */
    @Valid
    @Embedded
    private InboundBolData inboundBolData;
    
    /**
     * The content
     */
    @Valid
    @Embedded
    private Content content;
    
    /**
     * The bolDate
     */
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="BOL_DATE", nullable=false)
    private Date bolDate;
    
    /**
     * The carrierId
     */
    @NotEmpty
    @Column(name="CARRIER_ID", length=20, nullable=false)
    private String carrierId;

    /**
     * The containerId
     */
    @NotEmpty
    @Size(min = 3, max = 20)
    @Column(name="CONTAINER_ID", length=20, nullable=false)
    private String containerId;
    
    /**
     * The containerType
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name="CONTAINER_TYPE", length=8, nullable=false)
    private ContainerType containerType;
    
    /**
     * The customerId
     */
    @NotEmpty
    @Column(name="CUST_ID", length=20, nullable=false)
    private String customerId;
    
    /**
     * The driver
     */
    @Column(name="DRIVER", length=20)
    private String driver;
    
    /**
     * The facilityId
     */
    @NotEmpty
    @Column(name="FACILITY_ID", length=20, nullable=false)
    private String facilityId;
    
    /**
     * The grossWeight
     */
    @DecimalMin("0.00")
    @DecimalMax("9999999999.99")
    @Column(name="GROSS_WGT", precision=10, scale=2)
    private BigDecimal grossWeight;
    
    /**
     * The tareWeight
     */
    @DecimalMin("0.00")
    @DecimalMax("9999999999.99")
    @Column(name="TARE_WGT", precision=10, scale=2)
    private BigDecimal tareWeight;
    
    /**
     * The netWeight
     */
    @DecimalMin("0.00")
    @DecimalMax("9999999999.99")
    @Column(name="NET_WGT", precision=10, scale=2)
    private BigDecimal netWeight;
    
    /**
     * The nacnPct
     */
    @NotNull
    @DecimalMin("0.0000")
    @DecimalMax("999999.9999")
    @Column(name="NACN_PCT", precision=6, scale=4, nullable=false)
    private BigDecimal nacnPct;
    
    /**
     * The ph
     */
    @DecimalMin("0.0000")
    @DecimalMax("999999.9999")
    @Column(name="PH", precision=6, scale=4)
    private BigDecimal ph;
    
    /**
     * The specificGravity
     */
    @DecimalMin("0.0000")
    @DecimalMax("999999.9999")
    @Column(name="SP_GR", precision=6, scale=4)
    private BigDecimal specificGravity;
    
    /**
     * The productBolDescription
     */
    @Column(name="PRODUCT_BOL_DESC", length=200, nullable=false)
    private String productBolDescription;
    
    /**
     * The productId
     */
    @Column(name="PRODUCT_ID", length=20, nullable=false)
    private ProductType productId;
    
    /**
     * The shipmentDate
     */
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="SHIP_DATE")
    private Date shipmentDate;
    
    /**
     * Constructs an instance of <code>OutboundBol</code> class
     */
    public OutboundBol(){
        outboundBolPK = new OutboundBolPK();
        auditData = new AuditData();
        content = new Content();
        inboundBolData = new InboundBolData();
        grossWeight = BigDecimal.ZERO;
        tareWeight = BigDecimal.ZERO;
        netWeight = BigDecimal.ZERO;
        nacnPct = BigDecimal.ZERO;
        ph = BigDecimal.ZERO;
        specificGravity = BigDecimal.ZERO;
    }
    
    /**
     * @return the outboundBolPK
     */
    public OutboundBolPK getOutboundBolPK() {
        return outboundBolPK;
    }

    /**
     * @param outboundBolPK - the outboundBolPK to set
     */
    public void setOutboundBolPK(OutboundBolPK outboundBolPK) {
        this.outboundBolPK = outboundBolPK;
    }
    
    /**
     * @return the auditData
     */
    @Override
    public AuditData getAuditData() {
        return auditData;
    }

    /**
     * @param auditData - the auditData to set
     */
    public void setAuditData(AuditData auditData) {
        this.auditData = auditData;
    }
    
    /**
     * @return the inboundBolData
     */
    public InboundBolData getInboundBolData() {
        return inboundBolData;
    }

    /**
     * @param inboundBolData - the inboundBolData to set
     */
    public void setInboundBolData(InboundBolData inboundBolData) {
        this.inboundBolData = inboundBolData;
    }

    /**
     * @return the bolDate
     */
    public Date getBolDate() {
        return bolDate;
    }

    /**
     * @param bolDate - the bolDate to set
     */
    public void setBolDate(Date bolDate) {
        this.bolDate = bolDate;
    }

    /**
     * @return the carrierId
     */
    public String getCarrierId() {
        return carrierId;
    }

    /**
     * @param carrierId - the carrierId to set
     */
    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    /**
     * @return the content
     */
    public Content getContent() {
        return content;
    }

    /**
     * @param content - the content to set
     */
    public void setContent(Content content) {
        this.content = content;
    }
    
    /**
     * @return the containerId
     */
    public String getContainerId() {
        return containerId;
    }

    /**
     * @param containerId - the containerId to set
     */
    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    /**
     * @return the containerType
     */
    public ContainerType getContainerType() {
        return containerType;
    }

    /**
     * @param containerType - the containerType to set
     */
    public void setContainerType(ContainerType containerType) {
        this.containerType = containerType;
    }

    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId - the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the driver
     */
    public String getDriver() {
        return driver;
    }

    /**
     * @param driver - the driver to set
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**
     * @return the facilityId
     */
    public String getFacilityId() {
        return facilityId;
    }

    /**
     * @param facilityId
     */
    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    /**
     * @return the grossWeight
     */
    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    /**
     * @param grossWeight - the grossWeight to set
     */
    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    /**
     * @return the nacnPct
     */
    public BigDecimal getNacnPct() {
        return nacnPct;
    }

    /**
     * @param nacnPct - the nacnPct to set
     */
    public void setNacnPct(BigDecimal nacnPct) {
        this.nacnPct = nacnPct;
    }

    /**
     * @return the netWeight
     */
    public BigDecimal getNetWeight() {
        return netWeight;
    }

    /**
     * @param netWeight - the netWeight to set
     */
    public void setNetWeight(BigDecimal netWeight) {
        this.netWeight = netWeight;
    }

    /**
     * @return the ph
     */
    public BigDecimal getPh() {
        return ph;
    }

    /**
     * @param ph - the ph to set
     */
    public void setPh(BigDecimal ph) {
        this.ph = ph;
    }

    /**
     * @return the productBolDescription
     */
    public String getProductBolDescription() {
        return productBolDescription;
    }

    /**
     * @param productBolDescription - the productBolDescription to set
     */
    public void setProductBolDescription(String productBolDescription) {
        this.productBolDescription = productBolDescription;
    }

    /**
     * @return the productId
     */
    public ProductType getProductId() {
        return productId;
    }

    /**
     * @param productId - the productId to set
     */
    public void setProductId(ProductType productId) {
        this.productId = productId;
    }

    /**
     * @return the shipmentDate
     */
    public Date getShipmentDate() {
        return shipmentDate;
    }

    /**
     * 
     * @param shipmentDate - the shipmentDate to set
     */
    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    /**
     * @return the specificGravity
     */
    public BigDecimal getSpecificGravity() {
        return specificGravity;
    }

    /**
     * @param specificGravity - the specificGravity to set
     */
    public void setSpecificGravity(BigDecimal specificGravity) {
        this.specificGravity = specificGravity;
    }

    /**
     * @return the tareWeight
     */
    public BigDecimal getTareWeight() {
        return tareWeight;
    }

    /**
     * @param tareWeight - the tareWeight
     */
    public void setTareWeight(BigDecimal tareWeight) {
        this.tareWeight = tareWeight;
    }
    
}
