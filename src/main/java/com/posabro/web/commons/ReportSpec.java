/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.web.commons;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a report specification
 * 
 * @author Carlos Juarez
 */
public class ReportSpec<T> {

    /**
     * Title of the report
     */
    private String title;
    
    /**
     * The columns of the report
     */
    private List<Column> columns;
    
    /**
     * the rows of the report
     */
    private Iterable<T> rows;

    /**
     * Creates an instance of <code>ReportSpec</code>
     */
    public ReportSpec() {
        this(new ArrayList<T>());
    }

    /**
     * * Creates an instance of <code>ReportSpec</code>
     * 
     * @param rows - the rows to set
     */
    public ReportSpec(Iterable<T> rows) {
        this.rows = rows;
        columns = new ArrayList<Column>();
    }

    /**
     * Chain method to add columns
     * 
     * @param column - column to add
     * @return the itself instance
     */
    public ReportSpec addColumn(Column column) {
        columns.add(column);
        return this;
    }

    /**
     * @return the columns
     */
    public List<Column> getColumns() {
        return columns;
    }

    /**
     * @return the rows
     */
    public Iterable<T> getRows() {
        return rows;
    }

    /**
     * @return the i18n title
     */
    public String getI18nTitle() {
        return title;
    }
    
    /**
     * @param title - the i18n title to set
     */
    public void setI18nTitle(String title) {
        this.title = title;
    }

    /**
     * Represents a column
     */
    public static class Column {

        /**
         * the i18n name of column
         */
        private String name;
        
        /**
         * the property of the beans row that this column is referring
         */
        private String propertyName;

        /**
         * Creates an instance of <code>Column</code> class
         * 
         * @param propertyName - the propertyName to set
         */
        public Column(String propertyName) {
            this(propertyName, propertyName);
        }

        /**
         * Creates an instance of <code>Column</code> class
         * 
         * @param i18nName - the i18n name to set
         * @param propertyName - the propertyName to set
         */
        public Column(String i18nName, String propertyName) {
            this.name = i18nName;
            this.propertyName = propertyName;
        }

        /**
         * @return the i18n name
         */
        public String getI18nName() {
            return name;
        }

        /**
         * @return the propertyName
         */
        public String getPropertyName() {
            return propertyName;
        }
    }
}
