/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.web.commons;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

/**
 * Builder of <code>PageRequest</code> objects
 * 
 * @author Carlos Juarez
 */
public class PageRequestBuilder {

    /**
     * The logger
     */
    static final org.slf4j.Logger logger = LoggerFactory.getLogger(PageRequestBuilder.class);

    /**
     * Crates and populate a <code>PageRequest</code> object based on the parameters taken from the request.
     * The parameter names expected are based on the server side processing of the JQuery Datatables plugin
     * <url>http://www.datatables.net/usage/server-side</url>
     * 
     * @param request - The request
     * @return - The PageRequest created
     */
    public static PageRequest build(ServletRequest request) {
        int size = Integer.parseInt(request.getParameter("iDisplayLength"));
        int startIndex = Integer.parseInt(request.getParameter("iDisplayStart"));
        int page = startIndex/size;
        List<Order> orders = new ArrayList<Order>();
        int sortingColumns = Integer.parseInt(request.getParameter("iSortingCols"));
        String currentSortCol;
        int currentColSortNumber;
        for (int i = 0; i < sortingColumns; i++) {
            //to get the column number of the current sorting number
            currentColSortNumber = Integer.parseInt(request.getParameter("iSortCol_" + i));
            currentSortCol = request.getParameter("sSortDir_" + i);
            if (currentSortCol != null) {
                String column = request.getParameter("mDataProp_" + currentColSortNumber);
                if (currentSortCol.trim().equals("desc")) {
                    orders.add(new Order(Sort.Direction.DESC,column));
                } else {
                    orders.add(new Order(Sort.Direction.ASC,column));
                }
            }
        }
        PageRequest pageRequest = orders.isEmpty()? new PageRequest(page, size):new PageRequest(page, size, new Sort(orders));
        return pageRequest;
    }
}
