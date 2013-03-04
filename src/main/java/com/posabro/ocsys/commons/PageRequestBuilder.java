/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.commons;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

/**
 *
 * @author Carlos Juarez
 */
public class PageRequestBuilder {

    static final org.slf4j.Logger logger = LoggerFactory.getLogger(PageRequestBuilder.class);

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
