/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.web.excel;

import com.posabro.web.commons.ReportSpec;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanMap;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 * Excel View that renders a report by iterating a <code>ReportSpec</code> object
 * 
 * @author Carlos Juarez
 */
public class ReportExcelView extends AbstractExcelView implements MessageSourceAware {

    /**
     * Then name of the attribute with which the report spec will bin find in the model
     */
    public static final String REPORT_SPEC = "reportSpec";
    
    /**
     * The messageSource for i18n purpose
     */
    private MessageSource messageSource;

    /**
     * Template methods that is implemented to update the workbook with the information about the report spec as well as the rows
     * 
     * @param model - where the report spec is taken
     * @param workbook - where the report will be created
     * @param request - the current request
     * @param response - the current response
     * @throws Exception if any runtime exception happens
     */
    @Override
    protected void buildExcelDocument(Map<String, Object> model, org.apache.poi.hssf.usermodel.HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        
        ReportSpec<?> reportSpec = (ReportSpec<?>) model.get(REPORT_SPEC);
        if (reportSpec != null) {
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            Locale locale = localeResolver.resolveLocale(request);
            
            HSSFSheet sheet = workbook.createSheet(messageSource.getMessage(reportSpec.getI18nTitle(), null, locale));
            HSSFRow currentRow;
            int i = -1;
            //creating headers
            currentRow = sheet.createRow(++i);
            int j = 0;
            for (ReportSpec.Column column : reportSpec.getColumns()) {
                currentRow.createCell(j++).setCellValue(messageSource.getMessage(column.getI18nName(), null, locale));
                currentRow.getCell(j-1).setCellStyle(cellStyle);
            }
            BeanMap beanMap;
            for (Object bean : reportSpec.getRows()) {
                beanMap = new BeanMap(bean);
                Object value;
                currentRow = sheet.createRow(++i);
                j = 0;
                for (ReportSpec.Column column : reportSpec.getColumns()) {
                    value = findValue(beanMap, column.getPropertyName());
                    currentRow.createCell(j++).setCellValue(value == null ? null : value.toString());
                    currentRow.getCell(j-1).setCellStyle(cellStyle);
                }
            }
            for(j=0; j<=reportSpec.getColumns().size(); j++) {
                sheet.autoSizeColumn(j);
            }
        } else {
            HSSFSheet sheet = workbook.createSheet();
            HSSFRow row = sheet.createRow(0);
            row.createCell(0).setCellValue("If you were provide a model, you would see a nice report here!");
        }
    }

    /**
     * Populates a <code>HSSFRow</code> with the information of the props according to the property names defined in columns
     * 
     * @param columns - where the property names will be pulled
     * @param currentRow - the row to populate
     * @param props - to populate every cell
     */
    public static void populateRow(List<ReportSpec.Column> columns, HSSFRow currentRow, Map<String, Object> props) {
        int i = -1;
        for (ReportSpec.Column column : columns) {
            currentRow.createCell(++i).setCellValue(props.get(column.getPropertyName()).toString());
        }
    }

    /**
     * In case propertyName is a nested property (contains dots) this method goes in deep until find the value of the
     * nested property
     * @param beanMap where the property value will be found
     * @param propertyName - the propertyName
     * @return the object found
     */
    private Object findValue(BeanMap beanMap, String propertyName) {
        if (propertyName.contains(".")) {
            BeanMap innerBeanMap = new BeanMap(beanMap.get(propertyName.substring(0, propertyName.indexOf("."))));
            return findValue(innerBeanMap, propertyName.substring(propertyName.indexOf(".") + 1, propertyName.length()));
        } else {
            return beanMap.get(propertyName);
        }
    }

    /**
     * @param messageSource - the messageSource to set
     */
    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
