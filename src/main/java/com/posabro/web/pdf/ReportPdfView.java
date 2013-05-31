/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.web.pdf;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.posabro.i18n.LocaleLocator;
import com.posabro.web.commons.ReportSpec;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanMap;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Carlos Juarez
 */
public class ReportPdfView extends OwnAbstractPdfView implements MessageSourceAware{

    /**
     * The logger
     */
    final org.slf4j.Logger logger = LoggerFactory.getLogger(ReportPdfView.class);
    
    /**
     * Then name of the attribute with which the report spec will bin find in the model
     */
    public static final String REPORT_SPEC = "reportSpec";
    
    /**
     * head font
     */
    private static Font catFont = new Font(FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    
    /**
     * regular font
     */
    private static Font smallBold = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    
    /**
     * The messageSource for i18n purpose
     */
    private MessageSource messageSource;
    
    /**
     * The localeLocator
     */
    private LocaleLocator localeLocator;

    /**
     * Template methods that is implemented to update the workbook with the information about the report spec as well as the rows
     * 
     * @param model - where the report spec is taken
     * @param document - where the report will be created
     * @param writer - pdf writer
     * @param request - the current request
     * @param response - the current response
     * @throws Exception if any runtime exception happens
     */
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ReportSpec<?> reportSpec = (ReportSpec<?>) model.get(REPORT_SPEC);
        if (reportSpec != null) {
            Locale locale = localeLocator.lookLocale();
            logger.debug("the messageSource is kind " + messageSource.getClass());
            String title = messageSource.getMessage(reportSpec.getI18nTitle(), null, locale);
            addMetaData(document, title);
            String realPath = request.getServletContext().getRealPath("/resources/images/psb-small.png");
            String currentUser;
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                currentUser = authentication.getName();
            } else {
                currentUser = "anonymous";
            }
            String author = messageSource.getMessage("report.author", new Object[]{currentUser, new Date()}, locale);
            addTitleHeader(document, title, realPath,author);
            createTable(document, reportSpec, locale);
        }
    }

    /**
     * Adds the meta data info to the document
     * @param document
     * @param title 
     */
    private static void addMetaData(Document document, String title) {
        document.addTitle(title);
//        document.addAuthor("Carlitos");
//        document.addCreator("Carlitos too");
    }

    /**
     * Adds an empty line to the paragraph
     * @param paragraph - the paragraph where the empty line will be added
     * @param number the number of spaces that the line is going to content
     */
    public static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    /**
     * Adds title header to the document
     * 
     * @param document -  the document where the title header is going to be added
     * @param title - the title
     * @param imagePath - the image in the title
     * @param author - the author
     * @throws DocumentException 
     */
    private void addTitleHeader(Document document, String title, String imagePath, String author) throws DocumentException {
        PdfPTable headerTable = new PdfPTable(2);
        headerTable.setLockedWidth(true);
        headerTable.setTotalWidth(500);
        headerTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        try {
            logger.debug("resource : " + imagePath);
            PdfPCell image = new PdfPCell(Image.getInstance(imagePath));
            image.setBorder(Rectangle.NO_BORDER);
            headerTable.addCell(new PdfPCell(image));
        } catch (BadElementException ex) {
            logger.error("Exception while loading image", ex);
        } catch (MalformedURLException ex) {
            logger.error("Exception while loading image", ex);
        } catch (IOException ex) {
            logger.error("Exception while loading image", ex);
        }
        PdfPTable titleTable = new PdfPTable(1);
        titleTable.setTotalWidth(125);
        titleTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        titleTable.addCell(new Phrase(title, catFont));
        titleTable.addCell(new Phrase(author, smallBold));
        headerTable.addCell(titleTable);
        // Will create: Report generated by: _name, _date
        document.add(headerTable);
        document.add(new Paragraph(" "));
    }

    /**
     * Adds a table to the document by using the report spec as data source
     * 
     * @param document - the document where the table is added
     * @param reportSpec - the data source
     * @param locale - the locale... cause the table is i18n
     * @throws DocumentException 
     */
    private void createTable(Document document, ReportSpec<?> reportSpec, Locale locale) throws DocumentException {
        PdfPTable table = new PdfPTable(reportSpec.getColumns().size());
        PdfPCell c1;
        String columnName;
        for (ReportSpec.Column column : reportSpec.getColumns()) {
            columnName = messageSource.getMessage(column.getI18nName(), null, locale);
            c1 = new PdfPCell(new Phrase(columnName));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
        }
        table.setHeaderRows(1);
        table.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
//        ObjectMapper mapper = new ObjectMapper();
//        Map<String, Object> props;
        BeanMap beanMap;
        for (Object bean : reportSpec.getRows()) {
//            props = mapper.convertValue(bean, Map.class);
            beanMap = new BeanMap(bean);
            Object value;
            for (ReportSpec.Column column : reportSpec.getColumns()) {
                value = findValue(beanMap, column.getPropertyName());
                table.addCell(value == null ? null : value.toString());
            }
        }
        document.add(table);
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
    
    /**
     * @param localeLocator - the localeLocator to set
     */
    public void setLocaleLocator(LocaleLocator localeLocator) {
        this.localeLocator = localeLocator;
    }
    
}
