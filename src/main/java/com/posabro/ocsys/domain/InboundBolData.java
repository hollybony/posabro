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
    private String inbouundBolId1;
    
    /**
     * The inboundBolId2
     */
    @Size(min = 3, max = 20)
    @Column(name="INBOUND_BOL_ID_02", length=20)
    private String inbouundBolId2;
    
    /**
     * The inboundContId1
     */
    @NotEmpty
    @Size(min = 3, max = 20)
    @Column(name="INBOUND_CONT_ID_01", length=20, nullable=false)
    private String inbouundContId1;
    
    /**
     * The inboundContId2
     */
    @Size(min = 3, max = 20)
    @Column(name="INBOUND_CONT_ID_02", length=20)
    private String inbouundContId2;

    public InboundBolData(){
        this(null);
    }
    
    public InboundBolData(String inbouundContId1){
        this.inbouundContId1 = inbouundContId1;
    }
    
    /**
     * @return the inboundBolId1
     */
    public String getInbouundBolId1() {
        return inbouundBolId1;
    }

    /**
     * @param inboundBolId1 - the inboundBolId1 to set
     */
    public void setInbouundBolId1(String inbouundBolId1) {
        this.inbouundBolId1 = inbouundBolId1;
    }

    /**
     * @return the inboundBolId2
     */
    public String getInbouundBolId2() {
        return inbouundBolId2;
    }

    /**
     * @param inboundBolId2 - the inboundBolId2 to set
     */
    public void setInbouundBolId2(String inbouundBolId2) {
        this.inbouundBolId2 = inbouundBolId2;
    }

    /**
     * @return the inboundContId1
     */
    public String getInbouundContId1() {
        return inbouundContId1;
    }

    /**
     * @param inboundContId1 - the inboundContId1 to set
     */
    public void setInbouundContId1(String inbouundContId1) {
        this.inbouundContId1 = inbouundContId1;
    }

    /**
     * @return the inboundContId2
     */
    public String getInbouundContId2() {
        return inbouundContId2;
    }

    /**
     * @param inboundContId2 - the inboundContId2 to set
     */
    public void setInbouundContId2(String inbouundContId2) {
        this.inbouundContId2 = inbouundContId2;
    }
    
}
