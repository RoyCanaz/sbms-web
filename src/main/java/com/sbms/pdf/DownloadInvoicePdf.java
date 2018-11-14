/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.pdf;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.BorderRadius;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.ListNumberingType;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.sbms.domain.Addon;
import com.sbms.domain.Company;
import com.sbms.domain.Invoice;
import com.sbms.domain.Product;
import com.sbms.domain.Qoute;
import com.sbms.domain.QuoteItem;
import com.sbms.domain.Stock;
import com.sbms.service.UserService;
import com.sbms.utilities.DateUtil;
import com.sbms.utilities.SerialNumberUtil;
import com.sbms.utilities.SpecificationsUtil;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

/**
 *
 * @author user
 */
@Component("generateInvoice")
public class DownloadInvoicePdf extends AbstractView{
    @Resource
    private UserService userService;
    public static final String NEWLINE = "\n";
    public static float itemsHeaderFontSize = 11;
    public static float quoteItemsFontSize = 9;
     private static final String IMAGE_DIRECTORY = "/resources/img";
    
     @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
       
        // Qoute q = (Qoute) map.get("quote");
         Invoice i = (Invoice) map.get("invoice");
         
         String filename = i.getInvoiceUuid();
         Qoute q = i.getQoute();
         Company c = q.getCompany();
        response.setHeader("Content-Disposition", "attachment; filename="+filename+".pdf");
         PdfWriter pdfWriter = new PdfWriter(response.getOutputStream());
         PdfDocument pdf = new PdfDocument(pdfWriter);
         ServletContext context = request.getServletContext();
         String realpath = context.getRealPath(IMAGE_DIRECTORY);
         
         
         
         
        try (Document pdfDocument = new Document(pdf)) {
            
            String imgPath= realpath+File.separator+"nice_logo.png";
           // String imgPath = "src/main/webapp/resources/img/nice_logo.png";
            Image image = new Image(ImageDataFactory.create(imgPath));
            image.setWidth(new UnitValue(UnitValue.PERCENT, 70));
            image.setHeight(new UnitValue(UnitValue.PERCENT, 50));
            image.setTextAlignment(TextAlignment.RIGHT);
            Table header = new Table(new UnitValue[]{new UnitValue(UnitValue.PERCENT, 50), new UnitValue(UnitValue.PERCENT, 50)});
             Paragraph he = new Paragraph("Tax Invoice").setFontSize(32).setBold().setUnderline();
             Cell cellTex = new Cell().setBorder(Border.NO_BORDER).add(he);
             Cell cellImage = new Cell().setBorder(Border.NO_BORDER).add(image);
            header.addCell(cellTex);
            header.addCell(cellImage);
            pdfDocument.add(header);
            
            pdfDocument.add(new Paragraph().setBold().setFontColor(ColorConstants.BLUE).setFontSize(15).setTextAlignment(TextAlignment.RIGHT).add(i.getInvoiceUuid()));
            pdfDocument.add(new Paragraph().setFontSize(14).setTextAlignment(TextAlignment.RIGHT).add(DateUtil.currentDate()));
            Table table = new Table(new UnitValue[]{new UnitValue(UnitValue.PERCENT, 70), new UnitValue(UnitValue.PERCENT, 30)});
            table.addCell(cellInfor("To :", q.getClient().getName(), q.getClient().getAddress(), q.getClient().getEmail(), q.getClient().getPhone(), q.getClient().getPhone()));
            table.addCell(fromDetails(i.getInvoiceUuid(),
                   // DateUtil.currentDate(q.getDateCreated()),
                    DateUtil.currentDate(),
                    userService.getCurrentUser()==null ? "Total IT": userService.getCurrentUser().getDisplayName(),
                    "0773394687", "200038664", "10019550"));
            table.addCell(cellInfor("From :", c.getCompanyName(), c.getAddress(), c.getEmail(), c.getOfficePhone()
                    + c.getMobilePhone(), c.getWebsite()));
            table.addCell(bankingDetails(q.getBankingDetail().getName(), q.getBankingDetail().getBank(), q.getBankingDetail().getAccountNumber(), q.getBankingDetail().getBranch()));
            Paragraph spacing = new Paragraph("");
            pdfDocument.add(spacing);
            pdfDocument.add(table);
            pdfDocument.add(spacing); 
            
            
            
             Table items = new Table(new UnitValue[]{
                 new UnitValue(UnitValue.PERCENT, 10),
                 new UnitValue(UnitValue.PERCENT, 50),
                 new UnitValue(UnitValue.PERCENT, 10),
                 new UnitValue(UnitValue.PERCENT, 15),
                 new UnitValue(UnitValue.PERCENT, 15)});
             items.setWidth(new UnitValue(UnitValue.PERCENT, 100));
             Paragraph itemCode = new Paragraph("ITEM CODE").setBold().setFontSize(itemsHeaderFontSize);
             items.addCell(itemCode);
             Paragraph descr = new Paragraph("DESCRIPTION").setBold().setFontSize(itemsHeaderFontSize);
             items.addCell(descr);
             Paragraph qty = new Paragraph("QTY").setBold().setFontSize(itemsHeaderFontSize);
             items.addCell(qty).setTextAlignment(TextAlignment.CENTER);
             Paragraph unitPrice = new Paragraph("UNIT PRICE").setBold().setFontSize(itemsHeaderFontSize);
             items.addCell(unitPrice);
             Paragraph linePrice = new Paragraph("LINE PRICE").setBold().setFontSize(itemsHeaderFontSize);
             items.addCell(linePrice);
             
             
             List<QuoteItem>  quoteItems = q.getQuoteItems();
            
             
             for(QuoteItem quoteItem : quoteItems){
                 Product p = quoteItem.getProduct();
                 Qoute qoute = quoteItem.getQoute();
                 String sn = SerialNumberUtil.getSerialNumbers(i, qoute, p);
                 String des = "";
                 if(p.getCategory().getName().equalsIgnoreCase("Monitor")){
                     des = SpecificationsUtil.setDescription(p.getSpecification()) +sn;
                 }
                else if(p.getCategory().getName().equalsIgnoreCase("Desktop PC")){
                    des = SpecificationsUtil.setDescription(p.getSpecification()) +sn;
                }
                else if(p.getCategory().getName().equalsIgnoreCase("Printer")){
                    des = SpecificationsUtil.setDescription(p.getSpecification())+sn;
                }
                 else if(p.getCategory().getName().equalsIgnoreCase("Laptop")){
                    des = SpecificationsUtil.setDescription(p.getSpecification())+sn;
                }
                 else{
                     des = p.getDescription()+sn;
                 }
                 Paragraph proCode = new Paragraph(p.getProductCode()).setFontSize(quoteItemsFontSize);
                 Paragraph desc = new Paragraph(p.getBrand()+", "+p.getModel()+", "+des).setFontSize(quoteItemsFontSize);
                 Paragraph quantity = new Paragraph(String.valueOf(quoteItem.getQuantity())).setFontSize(quoteItemsFontSize);
                 Paragraph unitP = new Paragraph(String.valueOf(p.getUnitPrice())).setFontSize(quoteItemsFontSize);
                 Paragraph lineP = new Paragraph(String.valueOf(Precision.round(p.getUnitPrice()*quoteItem.getQuantity(), 2))).setFontSize(quoteItemsFontSize);
                 items.addCell(proCode);
                 items.addCell(desc);
                 items.addCell(quantity);
                 items.addCell(unitP);
                 items.addCell(lineP);
             }
             pdfDocument.add(spacing);
             pdfDocument.add(items);
             pdfDocument.add(spacing);
           
             
            Paragraph pEmpty = new Paragraph("");
             Cell cellText = new Cell().setBorder(Border.NO_BORDER).add(pEmpty);
             Table amount = new Table(new UnitValue[]{
                 new UnitValue(UnitValue.PERCENT, 10),
                 new UnitValue(UnitValue.PERCENT, 50),
                 new UnitValue(UnitValue.PERCENT, 10),
                 new UnitValue(UnitValue.PERCENT, 15),
                 new UnitValue(UnitValue.PERCENT, 15)});
             amount.setWidth(new UnitValue(UnitValue.PERCENT, 100)).setTextAlignment(TextAlignment.CENTER);
            
             amount.addCell(cellText);
             amount.addCell(cellText);
             amount.addCell(cellText);
             
             Paragraph subTotalHeader = new Paragraph("SUB TOTAL").setFontSize(itemsHeaderFontSize).setBold();
             Paragraph subTotalAmount = new Paragraph("$ "+ String.valueOf(q.getTotal())).setFontSize(itemsHeaderFontSize).setBold();
             amount.addCell(subTotalHeader);
             amount.addCell(subTotalAmount);
             pdfDocument.add(spacing);
             pdfDocument.add(amount); 
             
             Table vat = new Table(new UnitValue[]{
                 new UnitValue(UnitValue.PERCENT, 10),
                 new UnitValue(UnitValue.PERCENT, 50),
                 new UnitValue(UnitValue.PERCENT, 10),
                 new UnitValue(UnitValue.PERCENT, 15),
                 new UnitValue(UnitValue.PERCENT, 15)});
             vat.setWidth(new UnitValue(UnitValue.PERCENT, 100)).setTextAlignment(TextAlignment.CENTER);
            
             vat.addCell(cellText);
             vat.addCell(cellText);
             vat.addCell(cellText);
             
             Paragraph vatHeader = new Paragraph("VAT").setFontSize(itemsHeaderFontSize).setBold();
             Paragraph vatAmount = new Paragraph("$ "+ String.valueOf(q.getVat())).setFontSize(itemsHeaderFontSize).setBold();
             vat.addCell(vatHeader);
             vat.addCell(vatAmount);
             pdfDocument.add(vat);
             
               Table totalInc = new Table(new UnitValue[]{
                new UnitValue(UnitValue.PERCENT, 10),
                 new UnitValue(UnitValue.PERCENT, 50),
                 new UnitValue(UnitValue.PERCENT, 10),
                 new UnitValue(UnitValue.PERCENT, 15),
                 new UnitValue(UnitValue.PERCENT, 15)});
             totalInc.setWidth(new UnitValue(UnitValue.PERCENT, 100)).setTextAlignment(TextAlignment.CENTER);
            
             totalInc.addCell(cellText);
             totalInc.addCell(cellText);
             totalInc.addCell(cellText);
             
             Paragraph totHeader = new Paragraph("TOTAL").setFontSize(14).setBold().setBackgroundColor(ColorConstants.LIGHT_GRAY);
             Paragraph totAmount = new Paragraph("$ "+ String.valueOf(q.getTotalIncVat())).setFontSize(14).setBold().setBackgroundColor(ColorConstants.LIGHT_GRAY);
             totalInc.addCell(totHeader);
             totalInc.addCell(totAmount);
             pdfDocument.add(spacing);
             pdfDocument.add(totalInc);
             
            
            
            
            
             pdfDocument.add(spacing);
            Paragraph addOnsHeader = new Paragraph("RECOMMENDED ADDONS").setFontColor(ColorConstants.BLUE).setFontSize(itemsHeaderFontSize).setBold().setTextAlignment(TextAlignment.CENTER);
            pdfDocument.add(spacing);
          //  pdfDocument.add(addOnsHeader);
           // pdfDocument.add(recommendedAddons(quoteItems));
            pdfDocument.add(spacing);
            pdfDocument.add(spacing);
            com.itextpdf.layout.element.List listInfo = new com.itextpdf.layout.element.List(ListNumberingType.ENGLISH_UPPER);
            listInfo.add(new ListItem("Ownership of all goods supplied remains vested in Total IT until payment has been made in full."));
            listInfo.add(new ListItem("Interest will accrue on overdue amounts."));
            listInfo.add(new ListItem("Errors and omissions accepted."));
            listInfo.add(new ListItem("Pricing is subject to change without notice."));
            listInfo.setFontSize(quoteItemsFontSize);
            listInfo.setFontColor(ColorConstants.BLACK, 5);
            Paragraph footerInfor = new Paragraph("We greatly appreciate the business you give us and greatly value our relationship,"
                    + "if there is a way we can improve our service to you please let us know at care@totalit.org ").setFontSize(12);
            pdfDocument.add(listInfo);
            pdfDocument.add(footerInfor);
            
            
             
        }
        
    }
    
    public Cell cellInfor(String header, String name, String address, String email, String mobile, String office){
        Paragraph p = new Paragraph()
        .setMultipliedLeading(1.0f)
        .add(new Text(header).setBold().setFontColor(ColorConstants.BLUE)).add(NEWLINE)
        .add(name).add(NEWLINE)
        .add(address).add(NEWLINE)
        .add("Email : "+email).add(NEWLINE)
        .add(mobile).add(NEWLINE)
        .add(office);
        Cell cell = new Cell()
        .setBorder(Border.NO_BORDER)
        .add(p);
    return cell;
    }
    public Cell fromDetails(String invoiceuuid, String dateCreated, String contactName, String phone, String bpn, String vat){
        Paragraph p = new Paragraph().setMultipliedLeading(1.0f)
        .add("Invoice No : "+invoiceuuid).add(NEWLINE)
        .add("Date Created : "+dateCreated).add(NEWLINE)
        .add("Contact : "+contactName).add(NEWLINE)
        .add("Phone : "+phone).add(NEWLINE)
        .add("BPN : "+bpn).add(NEWLINE)
        .add("VAT : "+vat);
        Cell cell = new Cell()
        .setBorder(Border.NO_BORDER)
       
        .add(p);
         return cell;
    }

    public Cell bankingDetails(String name, String bank, String accNo, String branch){
         Paragraph p = new Paragraph()
        .setMultipliedLeading(1.0f)
        .add(new Text("Banking Details").setBold()).add(NEWLINE)
        .add("Name : "+name).add(NEWLINE)
        .add("Bank : "+bank).add(NEWLINE)
        .add("Acc No : "+accNo).add(NEWLINE)
         .add("Branch : "+branch);
          Cell cell = new Cell()
        .setBorder(Border.NO_BORDER)
        .add(p);
         return cell;
    }
   public Table recommendedAddons(List<QuoteItem>  quoteItems){
           Table items = new Table(new UnitValue[]{
                 new UnitValue(UnitValue.PERCENT, 50),
                 new UnitValue(UnitValue.PERCENT, 50)});
             items.setWidth(new UnitValue(UnitValue.PERCENT, 40)).setTextAlignment(TextAlignment.CENTER).setBorderRadius(new BorderRadius(20));
             items.setHorizontalAlignment(HorizontalAlignment.CENTER);
             
             Paragraph name = new Paragraph("Name").setBold().setFontSize(9);
             items.addCell(name).setTextAlignment(TextAlignment.CENTER);
             Paragraph price = new Paragraph("Price").setBold().setFontSize(9);
             items.addCell(price).setTextAlignment(TextAlignment.CENTER);
             
            for(QuoteItem quoteItem : quoteItems){
                Product p = quoteItem.getProduct();
                Set<Addon> set = p.getAddons();
                for(Addon addon : set){
                Paragraph pName = new Paragraph(addon.getName()).setFontSize(8);
                Paragraph pPrice = new Paragraph(String.valueOf(addon.getUnitPrice())).setFontSize(8);
                 items.addCell(pName);
                 items.addCell(pPrice);
                }
            }
        return items;    
   }
}
