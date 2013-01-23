/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.domain;

import javax.servlet.ServletRequest;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Carlos
 */
public class QueryReportBuilder {

    static final org.slf4j.Logger logger = LoggerFactory.getLogger(QueryReportBuilder.class);

    public static PageQuery build(ServletRequest request) {
        int columnsNumber = Integer.parseInt(request.getParameter("iColumns"));
        PageQuery pageQuery = new PageQuery();
        String currentSortCol;
        for (int i = 0; i < columnsNumber; i++) {
            currentSortCol = request.getParameter("sSortDir_" + i);
            if (currentSortCol != null) {
                String column = request.getParameter("mDataProp_" + i);
                SortOrder.Type type;
                if (currentSortCol.trim().equals("desc")) {
                    type = SortOrder.Type.DESC;
                } else {
                    type = SortOrder.Type.ASC;
                }
                SortOrder sortOrder = new SortOrder(type, column);
                pageQuery.setSortOrder(sortOrder);
                break;
            }
        }
        logger.debug("after for");
        pageQuery.setEcho(Integer.parseInt(request.getParameter("sEcho")));
        String searchPattern = request.getParameter("sSearch");
        pageQuery.setSearchPattern(searchPattern);
        int startIndex = Integer.parseInt(request.getParameter("iDisplayStart"));
        pageQuery.setStartIndex(startIndex);
        int size = Integer.parseInt(request.getParameter("iDisplayLength"));
        pageQuery.setSize(size);
        logger.debug("build end");
        return pageQuery;
    }
}
