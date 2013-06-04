/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * The data about a InboundBol
 * 
 * @author Carlos Juarez
 */
@Embeddable
public class InboundBolData implements Serializable {
    
    /**
     * The inboundBolId1
     */
    @Size(min = 3, max = 20)
    @Column(name="INBOUND_BOL_ID_01", length=20)
    private String inboundBolId1;
    
    /**
     * The inboundBolId2
     */
    @Size(min = 3, max = 20)
    @Column(name="INBOUND_BOL_ID_02", length=20)
    private String inboundBolId2;
    
    /**
     * The inboundContId1
     */
    @NotEmpty
    @Size(min = 3, max = 20)
    @Column(name="INBOUND_CONT_ID_01", length=20, nullable=false)
    private String inboundContId1;
    
    /**
     * The inboundContId2
     */
    @Size(min = 3, max = 20)
    @Column(name="INBOUND_CONT_ID_02", length=20)
    private String inboundContId2;

    /**
     * Creates an instance of <code>InboundBolData</code> class
     */
    public InboundBolData(){
        this(null);
    }
    
    /**
     * Creates an instance of <code>InboundBolData</code> class
     * 
     * @param inboundContId1 - the inboundContId1 to set
     */
    public InboundBolData(String inboundContId1){
        this.inboundContId1 = inboundContId1;
    }
    
    /**
     * @return the inboundBolId1
     */
    public String getInboundBolId1() {
        return inboundBolId1;
    }

    /**
     * @param inboundBolId1 - the inboundBolId1 to set
     */
    public void setInboundBolId1(String inboundBolId1) {
        this.inboundBolId1 = inboundBolId1;
    }

    /**
     * @return the inboundBolId2
     */
    public String getInboundBolId2() {
        return inboundBolId2;
    }

    /**
     * @param inboundBolId2 - the inboundBolId2 to set
     */
    public void setInboundBolId2(String inboundBolId2) {
        this.inboundBolId2 = inboundBolId2;
    }

    /**
     * @return the inboundContId1
     */
    public String getInboundContId1() {
        return inboundContId1;
    }

    /**
     * @param inboundContId1 - the inboundContId1 to set
     */
    public void setInboundContId1(String inboundContId1) {
        this.inboundContId1 = inboundContId1;
    }

    /**
     * @return the inboundContId2
     */
    public String getInboundContId2() {
        return inboundContId2;
    }

    /**
     * @param inboundContId2 - the inboundContId2 to set
     */
    public void setInboundContId2(String inboundContId2) {
        this.inboundContId2 = inboundContId2;
    }
    
}
