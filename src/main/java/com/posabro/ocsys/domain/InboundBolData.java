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
 *
 * @author Carlos Juarez
 */
@Embeddable
public class InboundBolData implements Serializable {
    
    @NotEmpty
    @Size(min = 3, max = 20)
    @Column(name="INBOUND_BOL_ID_01", length=20)
    private String inbouundBolId1;
    
    @NotEmpty
    @Size(min = 3, max = 20)
    @Column(name="INBOUND_BOL_ID_02", length=20)
    private String inbouundBolId2;
    
    @NotEmpty
    @Size(min = 3, max = 20)
    @Column(name="INBOUND_CONT_ID_01", length=20, nullable=false)
    private String inbouundContId1;
    
    @NotEmpty
    @Size(min = 3, max = 20)
    @Column(name="INBOUND_CONT_ID_02", length=20)
    private String inbouundContId2;

    public String getInbouundBolId1() {
        return inbouundBolId1;
    }

    public void setInbouundBolId1(String inbouundBolId1) {
        this.inbouundBolId1 = inbouundBolId1;
    }

    public String getInbouundBolId2() {
        return inbouundBolId2;
    }

    public void setInbouundBolId2(String inbouundBolId2) {
        this.inbouundBolId2 = inbouundBolId2;
    }

    public String getInbouundContId1() {
        return inbouundContId1;
    }

    public void setInbouundContId1(String inbouundContId1) {
        this.inbouundContId1 = inbouundContId1;
    }

    public String getInbouundContId2() {
        return inbouundContId2;
    }

    public void setInbouundContId2(String inbouundContId2) {
        this.inbouundContId2 = inbouundContId2;
    }
    
}
