/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

/**
 * Compound convenience class that represents a bill of lading ready to be render in some
 * visual format
 * 
 * @author Carlos Juarez
 */
public class BillOfLading {
    
    /**
     * The outboundBol
     */
    private OutboundBol outboundBol;
    
    /**
     * The customer owner of the outboundBol
     */
    private Customer customer;
    
    /**
     * The facility where the outboundBol goes
     */
    private Facility facility;
    
    /**
     * Creates an object of <code>BillOfLading</code> class
     */
    public BillOfLading(){
        this(null, null, null);
    }
    
    /**
     * Creates an object of <code>BillOfLading</code> class
     * 
     * @param outboundBol - the outboundBol to set
     * @param customer - the customer to set
     * @param facility - the facility to set
     */
    public BillOfLading(OutboundBol outboundBol, Customer customer, Facility facility){
        this.outboundBol = outboundBol;
        this.customer = customer;
        this.facility = facility;
    }

    /**
     * @return the outboundBol
     */
    public OutboundBol getOutboundBol() {
        return outboundBol;
    }

    /**
     * @param outboundBol - the outboundBol to set
     */
    public void setOutboundBol(OutboundBol outboundBol) {
        this.outboundBol = outboundBol;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer - the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the facility
     */
    public Facility getFacility() {
        return facility;
    }

    /**
     * @param facility - the facility to set
     */
    public void setFacility(Facility facility) {
        this.facility = facility;
    }
    
}
