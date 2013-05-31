/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.web;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.posabro.i18n.LocaleLocator;
import com.posabro.ocsys.domain.Address;
import com.posabro.ocsys.domain.BillOfLading;
import com.posabro.ocsys.domain.Company;
import com.posabro.ocsys.domain.ContainerType;
import com.posabro.ocsys.domain.Content;
import com.posabro.ocsys.domain.Country;
import com.posabro.ocsys.domain.Customer;
import com.posabro.ocsys.domain.Facility;
import com.posabro.ocsys.domain.InboundBolData;
import com.posabro.ocsys.domain.OutboundBol;
import com.posabro.ocsys.domain.OutboundBolPK;
import com.posabro.ocsys.domain.ProductType;
import com.posabro.ocsys.domain.State;
import com.posabro.web.pdf.OwnAbstractPdfView;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
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
    
    /**
     * The messageSource for i18n purpose
     */
    private MessageSource messageSource;
    
    /**
     * The localeLocator
     */
    private LocaleLocator localeLocator;
    
    public static final String OUTBOUNDBOL_MODEL = "outboundBol";
    
    public static final float defaultPadding = 15;
    
    private static final NumberFormat formatter = NumberFormat.getInstance(Locale.US);
    
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    
    public static void main(String... args) throws FileNotFoundException, DocumentException {
        OutboundBolPdfView outboundBolPdfView = new OutboundBolPdfView();
        OutboundBol outboundBol = new OutboundBol();
        outboundBol.setOutboundBolPK(new OutboundBolPK("POSABRO", "COAHUILA BRANCH", "20130001"));
        outboundBol.setBolDate(new Date());
        outboundBol.setCarrierId("CARR1");
        outboundBol.setContainerId("CONT2");
        outboundBol.setContainerType(ContainerType.ISO);
        outboundBol.setContent(new Content(new BigDecimal("161.2"), new BigDecimal("9114.12"), new BigDecimal("20050.8"), new BigDecimal("62023.1")));
        outboundBol.setCustomerId("CUST_1");
        outboundBol.setDriver("Roberto Perez");
        outboundBol.setFacilityId("FAC1");
        outboundBol.setGrossWeight(new BigDecimal("40"));
        outboundBol.setInboundBolData(new InboundBolData("SHPX220984"));
        outboundBol.setNacnPct(new BigDecimal("30.77"));
        outboundBol.setNetWeight(new BigDecimal("23890.1"));
        outboundBol.setPh(new BigDecimal("12.53"));
        outboundBol.setProductBolDescription("UN 3414, SODIUM CYANIDE SOLUTION 24-32%, NOS (Sodium Cyanide) 6.1, PGI Marine Pollutant");
        outboundBol.setProductId(ProductType.NACNL);
        outboundBol.setShipmentDate(new Date(outboundBol.getBolDate().getTime()-86400000));
        outboundBol.setSpecificGravity(new BigDecimal("1.176"));
        outboundBol.setTareWeight(BigDecimal.ZERO);

        Customer customer = new Customer("CUST_1", "PROYECTOS Y DESARROLLOS PRODESA, S.A. DE C.V.", "TX1",
                new Company("POSABRO", "POSABRO, S.A. de C.V."),
                new Address("Calle de la Plata 151-A", "Parque Industrial", 83229, "Hermosillo",
                new State("SON", "Sonora", new Country("MX", "Mexico"))));
        Facility facility = new Facility("FAC1", "CUST_1", "POSABRO", "PROYECTOS Y DESARROLLOS PRODESA, S.A. DE C.V.",
                new Address("Calle Cuauhtemoc Final S/N", null, 82343, "Estacion Llano",
                new State("SON", "Sonora", new Country("MX", "Mexico"))));
        BillOfLading billOfLading = new BillOfLading(outboundBol, customer, facility);
        
        outboundBolPdfView.init(billOfLading);
        
    }

    public void init(BillOfLading billOfLading) throws DocumentException, FileNotFoundException {
        Document document = new Document(PageSize.A4, 30, 15, 27, 27);
        PdfWriter.getInstance(document, new FileOutputStream("C:/temp/temp.pdf"));
        document.open();
        render(document, billOfLading);
        document.close();
    }
    
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BillOfLading billOfLading = (BillOfLading) model.get(OUTBOUNDBOL_MODEL);
        render(document, billOfLading);
    }
    
    public void render(Document document, BillOfLading billOfLading) throws DocumentException{
        document.add(getHeaderInfoTable(billOfLading));
        document.add(getInboundBolDetails(billOfLading.getOutboundBol()));
        document.add(getContentDetails(billOfLading.getOutboundBol()));
        document.add(getFooterDetails());
    }
    
      private PdfPTable getHeaderInfoTable(BillOfLading billOfLading) throws DocumentException {
        PdfPTable headerTable = new PdfPTable(4);
        headerTable.setWidthPercentage(100);
        headerTable.setWidths(new int[]{310, 320, 165, 165});
        //first row
        headerTable.addCell(getTitleCell());
        headerTable.addCell(getNoteCell());
        headerTable.addCell(getBolNumAndDateCell(billOfLading.getOutboundBol().getOutboundBolPK().getId(), billOfLading.getOutboundBol().getBolDate()));
        //second row
        headerTable.addCell(getSenderCell(billOfLading.getCustomer().getCompany()));
        headerTable.addCell(getCustomerCell(billOfLading.getCustomer()));
        headerTable.addCell(getAddresseeCell(billOfLading.getFacility()));
        return headerTable;
    }

    private PdfPCell getTitleCell() {
        Phrase phrase = new Phrase();
        phrase.setFont(FontsHelper.BOLD_12_5_FONT);
        phrase.add("CONOCIMIENTO");
        phrase.add(Chunk.NEWLINE);
        phrase.add("DE EMBARQUE");
        phrase.add(Chunk.NEWLINE);
        phrase.add(new Chunk("bill of Lading", FontsHelper.BOLD_ITALIC_FONT));
        PdfPCell cell = new PdfPCell(phrase);
        cell.setLeading(0, 1.3f);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    private PdfPCell getNoteCell() {
        Phrase phrase = BlocksHelper.getNarrowPhrase(FontsHelper.NORMAL_12_5_FONT, 0.9f,
                "EN CASO DE SITUACION DE EMERGENCIA",
                "CON PRODUCTOS QUIMICOS PELIGROSOS",
                "LLAMAR A CHEMTREC:",
                "1-800-424-9300 (FROM USA)",
                "001-703-527-3887 (FROM MEXICO)",
                "(24 HOURS)     (REFERENCE CYANCO)");
        PdfPCell cell = new PdfPCell(phrase);
        cell.setLeading(0, 1.45f);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setRowspan(2);
        cell.setPadding(10);
        cell.setPaddingTop(2);
        return cell;
    }

    public PdfPCell getBolNumAndDateCell(String bolId, Date bolDate) {
        PdfPTable table = new PdfPTable(1);
        PdfPCell numCell = new PdfPCell(new Phrase("B/L NUMERO", FontsHelper.WHITE_BOLD_FONT));
        numCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        numCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        numCell.setBackgroundColor(BaseColor.BLACK);
        table.addCell(numCell);
        PdfPCell numValueCell = new PdfPCell(new Phrase(bolId));
        numValueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(numValueCell);
        table.addCell(BlocksHelper.getNoBorderCell());
        PdfPCell dateCell = new PdfPCell(new Phrase("B/L FECHA", FontsHelper.WHITE_BOLD_FONT));
        dateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        dateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        dateCell.setBackgroundColor(BaseColor.BLACK);
        table.addCell(dateCell);
        PdfPCell dateValueCell = new PdfPCell(new Phrase(dateFormatter.format(bolDate)));
        dateValueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(dateValueCell);
        table.addCell(BlocksHelper.getNoBorderCell());
        PdfPCell pageCell = new PdfPCell(new Phrase("page 1 of 1", FontsHelper.NORMAL_10_FONT));
        pageCell.setBorder(Rectangle.NO_BORDER);
        pageCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        table.addCell(pageCell);
        PdfPCell cell = new PdfPCell(table);
        cell.setPaddingTop(defaultPadding);
        cell.setPaddingLeft(defaultPadding);
        cell.setRowspan(2);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    public PdfPCell getSenderCell(Company company) {
        PdfPTable table = new PdfPTable(1);
        Phrase titlePhrase = new Phrase("DE ", FontsHelper.WHITE_BOLD_FONT);
        titlePhrase.add(new Chunk("(From)", FontsHelper.WHITE_ITALIC_FONT));
        PdfPCell titleCell = new PdfPCell(titlePhrase);
        titleCell.setBackgroundColor(BaseColor.BLACK);
        titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(titleCell);
        //data
        Phrase phrase = BlocksHelper.getPhrase(FontsHelper.NORMAL_10_FONT,
                company.getName(),
                "La Aaa 111",
                "Ccl. Mitttt Cccccc",
                "Monterrey, N.L. 64460",
                "MEXICO",
                "Phone: +52 (81) 8303-1079");
        PdfPCell dataCell = new PdfPCell(phrase);
        dataCell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT);
        dataCell.setLeading(0, 1.24f);
        dataCell.setPaddingTop(0);
        table.addCell(dataCell);
        PdfPCell cell = new PdfPCell(table);
        cell.setRowspan(2);
        cell.setPaddingTop(defaultPadding);
        cell.setPaddingRight(defaultPadding);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    public PdfPCell getCustomerCell(Customer customer) {
        PdfPTable table = new PdfPTable(1);
        Phrase titlePhrase = new Phrase("CLIENTE ", FontsHelper.WHITE_BOLD_FONT);
        titlePhrase.add(new Chunk("( Customer)", FontsHelper.WHITE_ITALIC_FONT));
        PdfPCell titleCell = new PdfPCell(titlePhrase);
        titleCell.setBackgroundColor(BaseColor.BLACK);
        titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(titleCell);
        //data
        Phrase phrase = BlocksHelper.getPhrase(FontsHelper.NORMAL_10_FONT,
                customer.getName(),
                customer.getAddress().getLine1(),
                customer.getAddress().getLine2(),
                customer.getAddress().getZipCode() + " " + customer.getAddress().getCity() + ", " + customer.getAddress().getState().getName());
        PdfPCell dataCell = new PdfPCell(phrase);
        dataCell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT);
        dataCell.setLeading(0, 1.24f);
        dataCell.setPaddingTop(0);
        dataCell.setPaddingBottom(5);
        table.addCell(dataCell);
        PdfPCell cell = new PdfPCell(table);
        cell.setPaddingTop(defaultPadding);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    public PdfPCell getAddresseeCell(Facility facility) {
        PdfPTable table = new PdfPTable(1);
        Phrase titlePhrase = new Phrase("ENVIAR A ", FontsHelper.WHITE_BOLD_FONT);
        titlePhrase.add(new Chunk("( Ship to)", FontsHelper.WHITE_ITALIC_FONT));
        PdfPCell titleCell = new PdfPCell(titlePhrase);
        titleCell.setBackgroundColor(BaseColor.BLACK);
        titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(titleCell);
        //data
        Phrase phrase = BlocksHelper.getPhrase(FontsHelper.NORMAL_10_FONT,
                facility.getName(),
                facility.getAddress().getLine1(),
                facility.getAddress().getLine2(),
                facility.getAddress().getCity() + ", " + facility.getAddress().getState().getName(),
                "At'n Ing. Rodolfo I. Ramirez");
        PdfPCell dataCell = new PdfPCell(phrase);
        dataCell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT);
        dataCell.setLeading(0, 1.24f);
        dataCell.setPaddingTop(0);
        dataCell.setPaddingBottom(5);
        table.addCell(dataCell);
        PdfPCell cell = new PdfPCell(table);
        cell.setPaddingTop(defaultPadding);
        cell.setPaddingLeft(defaultPadding);
        cell.setColspan(2);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    private PdfPTable getInboundBolDetails(OutboundBol outboundBol) throws DocumentException {
        PdfPTable table = new PdfPTable(6);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingBefore(defaultPadding);
        table.setWidthPercentage(97);
        table.setWidths(new int[]{95, 95, 95, 105, 115, 95});
        table.addCell(BlocksHelper.getNarrowPhrase(FontsHelper.NORMAL_9_FONT, 0.8f, "CARRO TANQUE #"));
        table.addCell(BlocksHelper.getNarrowPhrase(FontsHelper.NORMAL_9_FONT, 0.8f, "CONTENEDOR #"));
        table.addCell(BlocksHelper.getNarrowPhrase(FontsHelper.NORMAL_9_FONT, 0.8f, "TRANSPORTISTA"));
        table.addCell(BlocksHelper.getNarrowPhrase(FontsHelper.NORMAL_9_FONT, 0.8f, "CHOFER"));
        table.addCell(BlocksHelper.getNarrowPhrase(FontsHelper.NORMAL_9_FONT, 0.8f, "BILL OF LADING CYANGO"));
        table.addCell(BlocksHelper.getNarrowPhrase(FontsHelper.NORMAL_9_FONT, 0.8f, "FECHA DE ENVIO"));
        //setting values
        table.addCell(BlocksHelper.getNarrowPhrase(FontsHelper.NORMAL_9_FONT, 0.8f, outboundBol.getInboundBolData().getInbouundContId1()));
        table.addCell(BlocksHelper.getNarrowPhrase(FontsHelper.NORMAL_9_FONT, 0.8f, outboundBol.getContainerId()==null?"":outboundBol.getContainerId()));
        table.addCell(BlocksHelper.getNarrowPhrase(FontsHelper.NORMAL_9_FONT, 0.8f, outboundBol.getCarrierId()));
        table.addCell(BlocksHelper.getNarrowPhrase(FontsHelper.NORMAL_9_FONT, 0.8f, outboundBol.getDriver()));
        table.addCell(BlocksHelper.getNarrowPhrase(FontsHelper.NORMAL_9_FONT, 0.8f, outboundBol.getInboundBolData().getInbouundBolId1()==null?"":outboundBol.getInboundBolData().getInbouundBolId1()));
        table.addCell(BlocksHelper.getNarrowPhrase(FontsHelper.NORMAL_9_FONT, 0.8f, dateFormatter.format(outboundBol.getShipmentDate())));
        return table;
    }

    private PdfPTable getContentDetails(OutboundBol outboundBol) throws DocumentException {
        PdfPTable table = new PdfPTable(7);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingBefore(defaultPadding);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{40, 80, 100, 320, 85, 85, 40});
        table.addCell(BlocksHelper.getNarrowPhrase(FontsHelper.NORMAL_9_FONT, 0.8f, "PKGS"));
        table.addCell(BlocksHelper.getNarrowPhrase(FontsHelper.NORMAL_9_FONT, 0.8f, "TIPO PKGS"));
        table.addCell(BlocksHelper.getNarrowPhrase(FontsHelper.NORMAL_9_FONT, 0.8f, "CONTENIDO KG"));
        table.addCell(BlocksHelper.getNarrowPhrase(FontsHelper.NORMAL_9_FONT, 0.8f, "DESCRIPCION"));
        table.addCell(BlocksHelper.getNarrowPhrase(FontsHelper.NORMAL_9_FONT, 0.8f, "PESO", "NETO"));
        table.addCell(BlocksHelper.getNarrowPhrase(FontsHelper.NORMAL_9_FONT, 0.8f, "PESO", "BRUTO"));
        table.addCell(BlocksHelper.getNarrowPhrase(FontsHelper.NORMAL_9_FONT, 0.8f, ""));
        //setting values
        PdfPCell pkgsCell = new PdfPCell(new Phrase("1", FontsHelper.NORMAL_10_FONT));
        pkgsCell.setRowspan(2);
        pkgsCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(pkgsCell);
        PdfPCell pkgsTypeCell = new PdfPCell(new Phrase("TRAILER", FontsHelper.NORMAL_10_FONT));
        pkgsTypeCell.setRowspan(2);
        pkgsTypeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(pkgsTypeCell);
        PdfPCell contentKgsCell = new PdfPCell(new Phrase(formatter.format(outboundBol.getContent().getContainedKgs()) + " KG", FontsHelper.NORMAL_10_FONT));
        contentKgsCell.setRowspan(2);
        contentKgsCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(contentKgsCell);
        table.addCell(getProductDescCell(outboundBol));
        PdfPCell netWeightCell = new PdfPCell(new Phrase(formatter.format(outboundBol.getNetWeight()) + " KG", FontsHelper.NORMAL_10_FONT));
        netWeightCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(netWeightCell);
        PdfPCell grossWeightCell = new PdfPCell(new Phrase(formatter.format(outboundBol.getGrossWeight()) + " KG", FontsHelper.NORMAL_10_FONT));
        grossWeightCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(grossWeightCell);
        table.addCell("");
        PdfPCell weightTotalCell = new PdfPCell(new Phrase("Total Peso:", FontsHelper.BOLD_10_FONT));
        weightTotalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        weightTotalCell.setBorder(Rectangle.BOTTOM);
        weightTotalCell.setPaddingBottom(15);
        table.addCell(weightTotalCell);
        PdfPCell totalNetWeightCell = new PdfPCell(new Phrase(formatter.format(outboundBol.getNetWeight()) + " KG", FontsHelper.BOLD_10_FONT));
        totalNetWeightCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        totalNetWeightCell.setPaddingBottom(15);
        table.addCell(totalNetWeightCell);
        PdfPCell totalGrossWeightCell = new PdfPCell(new Phrase(formatter.format(outboundBol.getGrossWeight()) + " KG", FontsHelper.BOLD_10_FONT));
        totalGrossWeightCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        totalGrossWeightCell.setPaddingBottom(15);
        table.addCell(totalGrossWeightCell);
        table.addCell("");
        return table;
    }

    private PdfPCell getProductDescCell(OutboundBol outboundBol) {
        Phrase productPhrase = new Phrase();
        PdfPTable table = new PdfPTable(1);
        productPhrase.add(new Chunk(outboundBol.getProductBolDescription(), FontsHelper.NORMAL_11_FONT));
        productPhrase.add(Chunk.NEWLINE);
        productPhrase.add(Chunk.NEWLINE);
        PdfPCell productCell = new PdfPCell(productPhrase);
        productCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(productCell);
        Phrase analysisPhrase = new Phrase();
        analysisPhrase.add(new Chunk("CERTIFICADO DE ANALISIS", FontsHelper.UNDERLINED_8_FONT));
        PdfPCell analysisCell = new PdfPCell(analysisPhrase);
        analysisCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        analysisCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(analysisCell);
        Phrase prodDataPhrase = new Phrase();
        prodDataPhrase.add(new Chunk("% NaCN: " + outboundBol.getNacnPct() + "%", FontsHelper.BOLD_FONT));
        prodDataPhrase.add(Chunk.NEWLINE);
        prodDataPhrase.add(new Chunk("SP. GR: " + outboundBol.getSpecificGravity(), FontsHelper.BOLD_FONT));
        prodDataPhrase.add(Chunk.NEWLINE);
        prodDataPhrase.add(new Chunk("pH: " + outboundBol.getPh(), FontsHelper.BOLD_FONT));
        prodDataPhrase.add(Chunk.NEWLINE);
        PdfPCell prodDataCell = new PdfPCell(prodDataPhrase);
        prodDataCell.setBorder(Rectangle.NO_BORDER);
        prodDataCell.setPaddingLeft(33);
        table.addCell(prodDataCell);
        Phrase contenedLtsPhrase = new Phrase();
        contenedLtsPhrase.add(new Chunk("Contenido LITROS:", FontsHelper.BOLD_UNDERLINED_FONT));
        contenedLtsPhrase.add(new Chunk(" " + formatter.format(outboundBol.getContent().getContainedLts()), FontsHelper.BOLD_FONT));
        PdfPCell contenedLtsCell = new PdfPCell(contenedLtsPhrase);
        contenedLtsCell.setPaddingLeft(15);
        contenedLtsCell.setPaddingBottom(80);
        contenedLtsCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(contenedLtsCell);
        PdfPCell cell = new PdfPCell(table);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPaddingRight(10);
        return cell;
    }

    private PdfPTable getFooterDetails() throws DocumentException {
        PdfPTable table = new PdfPTable(3);
        table.setSpacingBefore(defaultPadding);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{285, 240, 175});
        Phrase shipmentCertPhrase = BlocksHelper.getPhrase(FontsHelper.NORMAL_8_FONT,
                "CERTIFICACION DE EMBARQUE: Este documento certifica",
                "que los materiales anteriormente mencionados estan",
                "correctamente clasificados, descritos, empacados, marcados",
                "y etiquetados y se encuentran en condiciones adecuadas",
                "para su transporte de acuerdo con las normas aplicables.",
                "",
                "(Firma/Puesto)",
                "",
                "",
                "X_____________________________________________");
        PdfPCell shipmentCertCell = new PdfPCell(shipmentCertPhrase);
        shipmentCertCell.setRowspan(4);
        shipmentCertCell.setPaddingBottom(5);
        shipmentCertCell.setLeading(0, 1.45f);
        table.addCell(shipmentCertCell);
        Phrase containersLevelPhrase = new Phrase();
        containersLevelPhrase.add(new Chunk("                   Nivel de tanques", FontsHelper.BOLD_9_FONT));
        containersLevelPhrase.add(Chunk.NEWLINE);
        containersLevelPhrase.add(Chunk.NEWLINE);
        containersLevelPhrase.add(new Chunk("Antes:       ______________________", FontsHelper.NORMAL_8_FONT));
        containersLevelPhrase.add(Chunk.NEWLINE);
        containersLevelPhrase.add(Chunk.NEWLINE);
        containersLevelPhrase.add(new Chunk("Despues:  ______________________", FontsHelper.NORMAL_8_FONT));
        PdfPCell containersLevelCell = new PdfPCell(containersLevelPhrase);
        containersLevelCell.setRowspan(2);
        containersLevelCell.setPaddingBottom(5);
        table.addCell(containersLevelCell);
        Phrase deliveredByPhrase = new Phrase();
        deliveredByPhrase.add(new Chunk("ENTREGADO POR:", FontsHelper.NORMAL_8_FONT));
        deliveredByPhrase.add(Chunk.NEWLINE);
        deliveredByPhrase.add(Chunk.NEWLINE);
        deliveredByPhrase.add(Chunk.NEWLINE);
        deliveredByPhrase.add(Chunk.NEWLINE);
        table.addCell(deliveredByPhrase);
        Phrase receivedByPhrase = new Phrase();
        receivedByPhrase.add(new Chunk("RECIBIDO POR:", FontsHelper.NORMAL_8_FONT));
        receivedByPhrase.add(Chunk.NEWLINE);
        receivedByPhrase.add(Chunk.NEWLINE);
        receivedByPhrase.add(Chunk.NEWLINE);
        receivedByPhrase.add(Chunk.NEWLINE);
        PdfPCell receivedByCell = new PdfPCell(receivedByPhrase);
        receivedByCell.setRowspan(2);
        table.addCell(receivedByCell);
        Phrase driverSignaturePhrase = new Phrase();
        driverSignaturePhrase.add(new Chunk("I verify that Mine Attendant was present during making and", FontsHelper.NORMAL_7_FONT));
        driverSignaturePhrase.add(Chunk.NEWLINE);
        driverSignaturePhrase.add(new Chunk("breaking of connections and start of product flow", FontsHelper.NORMAL_7_FONT));
        driverSignaturePhrase.add(Chunk.NEWLINE);
        driverSignaturePhrase.add(Chunk.NEWLINE);
        driverSignaturePhrase.add(new Chunk("FIRMA CHOFER", FontsHelper.NORMAL_8_FONT));
        driverSignaturePhrase.add(Chunk.NEWLINE);
        driverSignaturePhrase.add(Chunk.NEWLINE);
        driverSignaturePhrase.add(Chunk.NEWLINE);
        driverSignaturePhrase.add(new Chunk("X______________________________", FontsHelper.NORMAL_8_FONT));
        driverSignaturePhrase.add(Chunk.NEWLINE);
        driverSignaturePhrase.add(Chunk.NEWLINE);
        driverSignaturePhrase.add(new Chunk("FIRMA RESPONSABLE MINA", FontsHelper.NORMAL_8_FONT));
        driverSignaturePhrase.add(Chunk.NEWLINE);
        driverSignaturePhrase.add(Chunk.NEWLINE);
        driverSignaturePhrase.add(Chunk.NEWLINE);
        driverSignaturePhrase.add(new Chunk("X______________________________", FontsHelper.NORMAL_8_FONT));
        PdfPCell driverSignatureCell = new PdfPCell(driverSignaturePhrase);
        driverSignatureCell.setRowspan(3);
        driverSignatureCell.setPaddingBottom(5);
        table.addCell(driverSignatureCell);
        Phrase datePhrase = new Phrase();
        datePhrase.add(new Chunk("FECHA:", FontsHelper.NORMAL_8_FONT));
        datePhrase.add(Chunk.NEWLINE);
        datePhrase.add(Chunk.NEWLINE);
        datePhrase.add(Chunk.NEWLINE);
        table.addCell(datePhrase);
        PdfPCell emptyCell = new PdfPCell();
        emptyCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(emptyCell);
        Phrase finalNotePhrase = BlocksHelper.getPhrase(FontsHelper.NORMAL_8_FONT,
                "",
                "LA FIRMA POR ESTE MEDIO CONFIRMA QUE",
                "LA CANTIDAD ESPECIFICADA ES CORRECTA Y",
                "RECIBIDA EN BUEN ESTADO");
        table.addCell(finalNotePhrase);
        return table;
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
