/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.web;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.posabro.i18n.LocaleLocator;
import com.posabro.ocsys.domain.OutboundBol;
import com.posabro.web.pdf.OwnAbstractPdfView;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

/**
 *
 * @author Carlos Juarez
 */
public class OutboundBolPdfView extends OwnAbstractPdfView implements MessageSourceAware{

    final org.slf4j.Logger logger = LoggerFactory.getLogger(OutboundBolPdfView.class);
    
    public static final String OUTBOUNDBOL_MODEL = "outboundBol";
    
    /**
     * The messageSource for i18n purpose
     */
    private MessageSource messageSource;
    
    private LocaleLocator localeLocator;
    
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        OutboundBol outboundBol = (OutboundBol) model.get(OUTBOUNDBOL_MODEL);
        document.add(new Paragraph("branch : " + outboundBol.getOutboundBolPK().getBranchId()));
        document.add(new Paragraph("company : " + outboundBol.getOutboundBolPK().getCompanyId()));
        document.add(new Paragraph("id : " + outboundBol.getOutboundBolPK().getId()));
        document.add(new Paragraph("carrier id : " + outboundBol.getCarrierId()));
        document.add(new Paragraph("container id : " + outboundBol.getContainerId()));
        document.add(new Paragraph("customer id : " + outboundBol.getCustomerId()));
        document.add(new Paragraph("facility id : " + outboundBol.getFacilityId()));
        document.add(new Paragraph("product bol description : " + outboundBol.getProductBolDescription()));
        document.add(new Paragraph("bol date : " + outboundBol.getBolDate()));
    }

    /**
     * @param messageSource - the messageSource to set
     */
    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
    public void setLocaleLocator(LocaleLocator localeLocator) {
        this.localeLocator = localeLocator;
    }
    
}
