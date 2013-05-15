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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Carlos Juarez
 */
@Entity
@Table(name="OUTBOUND_BOLS")
public class OutboundBol implements Serializable, Auditable{
    
    @EmbeddedId
    private OutboundBolPK outboundBolPK;
    
    @Embedded
    private AuditData auditData;
    
    @Embedded
    private InboundBolData inboundBolData;    
    
    @Embedded
    private Content content;
    
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="BOL_DATE", nullable=false)
    private Date bolDate;
    
    @NotEmpty
    @Column(name="CARRIER_ID", length=20, nullable=false)
    private String carrierId;

    @NotEmpty
    @Size(min = 3, max = 20)
    @Column(name="CONTAINER_ID", length=20, nullable=false)
    private String containerId;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name="CONTAINER_TYPE", length=8, nullable=false)
    private ContainerType containerType;
    
    @NotEmpty
    @Column(name="CUST_ID", length=20, nullable=false)
    private String customerId;
    
//    @NotEmpty required if the container is ISO
    @Column(name="DRIVER", length=20)
    private String driver;
    
    @NotEmpty
    @Column(name="FACILITY_ID", length=20, nullable=false)
    private String facilityId;
    
    @Column(name="GROSS_WGT", precision=10, scale=2)
    private BigDecimal grossWeight;
    
    @Column(name="TARE_WGT", precision=10, scale=2)
    private BigDecimal tareWeight;
    
    @Column(name="NET_WGT", precision=10, scale=2)
    private BigDecimal netWeight;
    
    @Column(name="NACN_PCT", precision=6, scale=4, nullable=false)
    private BigDecimal nacnPct;
    
    @Column(name="PH", precision=6, scale=4)
    private BigDecimal ph;
    
    @Column(name="SP_GR", precision=6, scale=4)
    private BigDecimal specificGravity;
    
    @Column(name="PRODUCT_BOL_DESC", length=200, nullable=false)
    private String productBolDescription;
    
    @Column(name="PRODUCT_ID", length=20, nullable=false)
    private ProductType productId;
    
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="SHIP_DATE")
    private Date shipmentDate;
    
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
    
    public OutboundBolPK getOutboundBolPK() {
        return outboundBolPK;
    }

    public void setOutboundBolPK(OutboundBolPK outboundBolPK) {
        this.outboundBolPK = outboundBolPK;
    }
    
    @Override
    public AuditData getAuditData() {
        return auditData;
    }

    public void setAuditData(AuditData auditData) {
        this.auditData = auditData;
    }
    
    public InboundBolData getInboundBolData() {
        return inboundBolData;
    }

    public void setInboundBolData(InboundBolData inboundBolData) {
        this.inboundBolData = inboundBolData;
    }

    public Date getBolDate() {
        return bolDate;
    }

    public void setBolDate(Date bolDate) {
        this.bolDate = bolDate;
    }

    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
    
    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public ContainerType getContainerType() {
        return containerType;
    }

    public void setContainerType(ContainerType containerType) {
        this.containerType = containerType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    public BigDecimal getNacnPct() {
        return nacnPct;
    }

    public void setNacnPct(BigDecimal nacnPct) {
        this.nacnPct = nacnPct;
    }

    public BigDecimal getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(BigDecimal netWeight) {
        this.netWeight = netWeight;
    }

    public BigDecimal getPh() {
        return ph;
    }

    public void setPh(BigDecimal ph) {
        this.ph = ph;
    }

    public String getProductBolDescription() {
        return productBolDescription;
    }

    public void setProductBolDescription(String productBolDescription) {
        this.productBolDescription = productBolDescription;
    }

    public ProductType getProductId() {
        return productId;
    }

    public void setProductId(ProductType productId) {
        this.productId = productId;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public BigDecimal getSpecificGravity() {
        return specificGravity;
    }

    public void setSpecificGravity(BigDecimal specificGravity) {
        this.specificGravity = specificGravity;
    }

    public BigDecimal getTareWeight() {
        return tareWeight;
    }

    public void setTareWeight(BigDecimal tareWeight) {
        this.tareWeight = tareWeight;
    }
    
}
