/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

/**
 *
 * @author Carlos Juarez
 */
public class BillOfLading {
    
    private OutboundBol outboundBol;
    
    private Customer customer;
    
    private Facility facility;
    
    public BillOfLading(){
        this(null, null, null);
    }
    
    public BillOfLading(OutboundBol outboundBol, Customer customer, Facility facility){
        this.outboundBol = outboundBol;
        this.customer = customer;
        this.facility = facility;
    }

    public OutboundBol getOutboundBol() {
        return outboundBol;
    }

    public void setOutboundBol(OutboundBol outboundBol) {
        this.outboundBol = outboundBol;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }
    
}
