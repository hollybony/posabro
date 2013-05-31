/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.web.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.AbstractView;

/**
 * PDF view ready to be used with iText 4.x.x
 * 
 * @author Carlos Juarez
 */
public abstract class OwnAbstractPdfView extends AbstractView {

    /**
     * Creates an instance of <code>OwnAbstractPdfView</code> class
     */
    public OwnAbstractPdfView() {
        setContentType("application/pdf");
    }

    /**
     * @see AbstractView#generatesDownloadContent() 
     * @return true
     */
    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }

    /**
     * @see AbstractView#renderMergedOutputModel(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)  
     * Creates the new document then the hook abstract methods are called and finally the document is closed.
     * 
     * @param model
     * @param request
     * @param response
     * @throws Exception 
     */
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // IE workaround: write into byte array first.
        ByteArrayOutputStream baos = createTemporaryOutputStream();

        // Apply preferences and build metadata.
        Document document = newDocument();
        PdfWriter writer = newWriter(document, baos);
        prepareWriter(model, writer, request);
        buildPdfMetadata(model, document, request);

        // Build PDF document.
        document.open();
        buildPdfDocument(model, document, writer, request, response);
        document.close();

        // Flush to HTTP response.
        writeToResponse(response, baos);
    }

    /**
     * By default the new document is A4 size, subclasses can override it to define a new document size
     * @return the new document
     */
    protected Document newDocument() {
        return new Document(PageSize.A4);
    }

    /**
     * Creates an new writer
     * 
     * @param document - document with which the new writer is created
     * @param os - the outputStream
     * @return the new writer
     * @throws DocumentException 
     */
    protected PdfWriter newWriter(Document document, OutputStream os) throws DocumentException {
        return PdfWriter.getInstance(document, os);
    }

    /**
     * Subclasses can override this method in order to change the preferences, the current implementation
     * allows to print the document
     * 
     * @param model
     * @param writer
     * @param request
     * @throws DocumentException 
     */
    protected void prepareWriter(Map<String, Object> model, PdfWriter writer, HttpServletRequest request)
            throws DocumentException {

        writer.setViewerPreferences(getViewerPreferences());
    }

    /**
     * @return the bitwise integer that contains the preferences of the document
     */
    protected int getViewerPreferences() {
        return PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage;
    }

    /**
     * By default any metadata is added, overrides to define the document metadata
     * 
     * @param model
     * @param document
     * @param request 
     */
    protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
    }

    /**
     * This is the main method where the body of the document is created.
     * 
     * @param model - from the PDF is created
     * @param document - the target document where the elements are aded
     * @param writer
     * @param request
     * @param response
     * @throws Exception 
     */
    protected abstract void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception;
}
