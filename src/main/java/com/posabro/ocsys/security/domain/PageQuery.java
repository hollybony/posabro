/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.domain;


/**
 *
 * @author Carlos
 */
public class PageQuery {

    private String searchPattern;
    
    private SortOrder sortOrder;
    
    private int startIndex;
    
    private int size;
    
    private int echo;

    public PageQuery() {
        this("");
    }

    public PageQuery(String searchPattern) {
        this(searchPattern, null);
    }

    public PageQuery(SortOrder sortOrder) {
        this("", sortOrder);
    }

    public PageQuery(String searchPattern, SortOrder sortOrder) {
        this.searchPattern = searchPattern;
        this.sortOrder = sortOrder;
    }

    public String getSearchPattern() {
        return searchPattern;
    }

    public void setSearchPattern(String searchPattern) {
        if(searchPattern==null){
            throw new IllegalArgumentException("searchPattern cannot be null");
        }
        this.searchPattern = searchPattern;
    }

    public SortOrder getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        if(startIndex<0){
            throw new IllegalArgumentException("startIndex must be grater than 0");
        }
        this.startIndex = startIndex;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if(size<=0){
            throw new IllegalArgumentException("size must be grater than 0");
        }
        this.size = size;
    }
    
     public int getEcho() {
        return echo;
    }

    public void setEcho(int echo) {
        this.echo = echo;
    }

    @Override
    public String toString() {
        return "QueryReport{" + "searchPattern=" + searchPattern + ", sortOrder=" + sortOrder + ", startIndex=" + startIndex + ", size=" + size + '}';
    }
    
}
