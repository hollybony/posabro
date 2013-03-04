/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.commons;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class ReportSpec<T> {

    private String title;
    
    private List<Column> columns;
    
    private Iterable<T> rows;

    public ReportSpec() {
        this(new ArrayList<T>());
    }

    public ReportSpec(Iterable<T> rows) {
        this.rows = rows;
        columns = new ArrayList<Column>();
    }

    public ReportSpec addColumn(Column column) {
        columns.add(column);
        return this;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public Iterable<T> getRows() {
        return rows;
    }

    public String getTitleKey() {
        return title;
    }
    
    public void setTitleKey(String title) {
        this.title = title;
    }

    public static class Column {

        private String nameKey;
        private String propertyName;

        public Column(String propertyName) {
            this(propertyName, propertyName);
        }

        public Column(String nameKey, String propertyName) {
            this.nameKey = nameKey;
            this.propertyName = propertyName;
        }

        public String getNameKey() {
            return nameKey;
        }

        public String getPropertyName() {
            return propertyName;
        }
    }
}
