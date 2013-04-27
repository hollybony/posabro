/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.web.commons;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a convenience page of rows ready to be used in a JQuery Datatables plugin component
 * under the server side processing configuration. In order to send an object of this class to the client
 * side it needs to be serialized to a JSON object.
 * <url>http://www.datatables.net/index</url>
 * 
 * @author Carlos Juarez
 */
public class JQueryPage {

    /**
     * Total records before filtering
     */
    private int iTotalRecords;
    
    /**
     * Total records after filtering
     */
    private int iTotalDisplayRecords;
    
    /**
     * An unaltered copy of sEcho sent from the client side and taken back to the same
     */
    private String sEcho;
    
    /**
     * this is a string of column names, comma separated (used in combination with sName)
     */
    private String sColumns;
    
    /**
     * The data in Collection of beans.
     */
    private List<?> aaData;

    /**
     * Creates an instance of <code>JQueryPage</code> class
     */
    public JQueryPage() {
        this(new ArrayList<Object>(), "");
    }
    
    /**
     * Creates an instance of <code>JQueryPage</code> class
     * 
     * @param aaData - aaData to set
     * @param sEcho - sEcho to set
     */
    public JQueryPage(List<?> aaData, String sEcho) {
        this(aaData, sEcho, aaData.size());
    }
    
    /**
     * Creates an instance of <code>JQueryPage</code> class
     * 
     * @param aaData - aaData to set
     * @param sEcho - sEcho to set
     * @param iTotalRecords  - iTotalRecords to set
     */
    public JQueryPage(List<?> aaData, String sEcho, int iTotalRecords) {
        this.aaData = aaData;
        this.iTotalRecords = iTotalRecords;
        this.iTotalDisplayRecords = iTotalRecords;
        this.sEcho = sEcho;
    }
    
    /**
     * @return the iTotalRecords
     */
    public int getiTotalRecords() {
        return iTotalRecords;
    }

    /**
     * @param iTotalRecords - iTotalRecords to set
     */
    public void setiTotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    /**
     * @return the iTotalDisplayRecords
     */
    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    /**
     * @param iTotalDisplayRecords - the iTotalDisplayRecords to set
     */
    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    /**
     * @return the sEcho
     */
    public String getsEcho() {
        return sEcho;
    }

    /**
     * @param sEcho - the sEcho to set
     */
    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    /**
     * @return the sColumns
     */
    public String getsColumns() {
        return sColumns;
    }

    /**
     * @param sColumns - the sColumns to set
     */
    public void setsColumns(String sColumns) {
        this.sColumns = sColumns;
    }

    /**
     * @return the aaData
     */
    public List<?> getAaData() {
        return aaData;
    }

    /**
     * @param aaData - the aaData to set
     */
    public void setAaData(List<?> aaData) {
        this.aaData = aaData;
    }

    @Override
    public String toString() {
        return "Page{" + "iTotalRecords=" + iTotalRecords + ", iTotalDisplayRecords=" + iTotalDisplayRecords + ", sEcho=" + sEcho + ", sColumns=" + sColumns + '}';
    }
    
}
