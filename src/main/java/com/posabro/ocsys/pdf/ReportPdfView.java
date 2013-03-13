/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.pdf;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import com.posabro.ocsys.commons.ReportSpec;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanMap;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.document.AbstractPdfView;

/**
 *
 * @author Carlos
 */
public class ReportPdfView extends AbstractPdfView implements MessageSourceAware {

    final org.slf4j.Logger logger = LoggerFactory.getLogger(ReportPdfView.class);
    
    public static final String REPORT_SPEC = "reportSpec";
    private static Font catFont = new Font(Font.TIMES_ROMAN, 18, Font.BOLD);
    private static Font smallBold = new Font(Font.TIMES_ROMAN, 12, Font.BOLD);
    private MessageSource messageSource;

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ReportSpec<?> reportSpec = (ReportSpec<?>) model.get(REPORT_SPEC);
        if (reportSpec != null) {
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            Locale locale = localeResolver.resolveLocale(request);
            logger.debug("the messageSource is kind " + messageSource.getClass());
            String title = messageSource.getMessage(reportSpec.getTitleKey(), null, locale);
            addMetaData(document, title);
            String realPath = request.getServletContext().getRealPath("/resources/images/psb-small.png");
            String currentUser;
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                currentUser = authentication.getName();
            } else {
                currentUser = "anonymous";
            }
            String author = messageSource.getMessage("report.author", new Object[]{currentUser,new Date()}, locale);
            addTitleHeader(document, title, realPath,author);
            createTable(document, reportSpec, locale);
        }
    }

    private static void addMetaData(Document document, String title) {
        document.addTitle(title);
//        document.addAuthor("Carlitos");
//        document.addCreator("Carlitos too");
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private void addTitleHeader(Document document, String title, String imagePath, String author) throws DocumentException {
        Table headerTable = new Table(2);
        headerTable.setWidth(100);
        headerTable.getDefaultCell().setBorder(Table.NO_BORDER);
        headerTable.setBorder(Table.NO_BORDER);
        try {
            logger.debug("resource : " + imagePath);
            Image image = Image.getInstance(imagePath);
            headerTable.addCell(new Cell(image));
        } catch (BadElementException ex) {
            logger.error("Exception while loading image", ex);
        } catch (MalformedURLException ex) {
            logger.error("Exception while loading image", ex);
        } catch (IOException ex) {
            logger.error("Exception while loading image", ex);
        }
        Table titleTable = new Table(1);
        titleTable.setWidth(125);
        titleTable.getDefaultCell().setBorder(Table.NO_BORDER);
        titleTable.setBorder(Table.NO_BORDER);
        titleTable.addCell(new Cell(new Chunk(title, catFont)));
        titleTable.addCell(new Cell(new Chunk(author, smallBold)));
        headerTable.addCell(new Cell(titleTable));
        // Will create: Report generated by: _name, _date
        document.add(headerTable);
        document.add(new Paragraph(" "));
    }

    private void createTable(Document document, ReportSpec<?> reportSpec, Locale locale) throws DocumentException {
        PdfPTable table = new PdfPTable(reportSpec.getColumns().size());
        PdfPCell c1;
        String columnName;
        for (ReportSpec.Column column : reportSpec.getColumns()) {
            columnName = messageSource.getMessage(column.getNameKey(), null, locale);
            c1 = new PdfPCell(new Phrase(columnName));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
        }
        table.setHeaderRows(1);
        table.setHorizontalAlignment(PdfTable.ALIGN_LEFT);
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

    private Object findValue(BeanMap beanMap, String propertyName) {
        if (propertyName.contains(".")) {
            BeanMap innerBeanMap = new BeanMap(beanMap.get(propertyName.substring(0, propertyName.indexOf("."))));
            return findValue(innerBeanMap, propertyName.substring(propertyName.indexOf(".") + 1, propertyName.length()));
        } else {
            return beanMap.get(propertyName);
        }
    }

    private Image getRepairedImage(String fileName) throws IOException, BadElementException {
        BufferedImage img = ImageIO.read(new File(fileName));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", baos);
        Image image = Image.getInstance(baos.toByteArray());
        image.scaleToFit(550, 800);
        return image;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
