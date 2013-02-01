/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos Juarez
 */
public class JQueryPage {

    private int iTotalRecords;
    
    private int iTotalDisplayRecords;
    
    private String sEcho;
    
    private String sColumns;
    
    private List<?> aaData;

    public JQueryPage() {
        this(new ArrayList<Object>(), "");
    }
    
    public JQueryPage(List<?> aaData, String sEcho) {
        this(aaData, sEcho, aaData.size());
    }
    
    public JQueryPage(List<?> aaData, String sEcho, int iTotalRecords) {
        this.aaData = aaData;
        this.iTotalRecords = iTotalRecords;
        this.iTotalDisplayRecords = iTotalRecords;
        this.sEcho = sEcho;
    }
    public int getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    public String getsColumns() {
        return sColumns;
    }

    public void setsColumns(String sColumns) {
        this.sColumns = sColumns;
    }

    public List<?> getAaData() {
        return aaData;
    }

    public void setAaData(List<?> aaData) {
        this.aaData = aaData;
    }

    @Override
    public String toString() {
        return "Page{" + "iTotalRecords=" + iTotalRecords + ", iTotalDisplayRecords=" + iTotalDisplayRecords + ", sEcho=" + sEcho + ", sColumns=" + sColumns + '}';
    }
    
}
