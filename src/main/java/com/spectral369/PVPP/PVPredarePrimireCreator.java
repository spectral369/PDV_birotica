package com.spectral369.PVPP;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TabAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.spectral369.birotica.PdfList;
import com.spectral369.utils.FooterEvt;
import com.spectral369.utils.PDFHelper;
import com.spectral369.utils.Utils;

public class PVPredarePrimireCreator {
    File pdff = null;
    private transient PdfDocument document;
    private transient PdfWriter writer;
    String id = Utils.getRandomStringSerial("IU");

    public PVPredarePrimireCreator(Map<String, String> map, String tm) {

	pdff = new File(Utils.getSaveFileLocation("PV_Predare_primire_" + tm + ".pdf"));
	if (map.isEmpty()) {
	    generatePDF(tm, pdff);
	    PdfList.addFile(id, pdff.getAbsolutePath());

	} else {

	    generatePDF(tm, pdff, map);
	    PdfList.addFile(id, pdff.getAbsolutePath());
	}
    }

    public String getID() {
	return id;
    }

   private void generatePDF(final String tm, final File pdfFile) {
	try {
	    writer = new PdfWriter(pdfFile);
	    Image antetLogo = PDFHelper.getAntetLogo();
	    document = new PdfDocument(writer);
	    document.getDocumentInfo().addCreationDate();
	    document.getDocumentInfo().setAuthor("spectral369");
	    document.getDocumentInfo().setTitle("PV_Predare_primire_" + tm);
	    document.setDefaultPageSize(PageSize.A4);
	    Document doc = new Document(document);
	    float width = doc.getPageEffectiveArea(PageSize.A4).getWidth();
	    document.addEventHandler(PdfDocumentEvent.START_PAGE, new FooterEvt(width));

	    final Paragraph antet = new Paragraph();

	    float documentWidth = document.getDefaultPageSize().getWidth() - doc.getLeftMargin() - doc.getRightMargin();

	    float documentHeight = document.getDefaultPageSize().getHeight() - doc.getTopMargin()
		    - doc.getBottomMargin();

	    antetLogo.scaleToFit(documentWidth, documentHeight);

	    antet.add(antetLogo);
	    antet.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    doc.add(antet);

	 

	    final Paragraph titlu = new Paragraph("\n\n\n\n");
	    Text t1 = new Text("PROCES VERBAL DE PREDARE-PRIMIRE").setFontSize(18f).setBold().setUnderline();
	    titlu.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    titlu.setTextAlignment(TextAlignment.CENTER);
	    titlu.add(t1).addStyle(PDFHelper.bold12nr);
	    doc.add(titlu);

	    
	    
	    
	    Paragraph incheiat =  new Paragraph("\n\n\n");
	    Text inc =  new Text("INCHEIAT ASTAZI _______________________________ .\n").setFontSize(16f);
	    Text smallinfo =  new Text("(data si ora)\n\n\n").setFontSize(10f);
	    incheiat.add(inc);
	    incheiat.add(smallinfo);
	    
	    doc.add(incheiat);
	    
	    
	    Paragraph decl =  new Paragraph();
	    decl.add(PDFHelper.addTab());
	    decl.add("Subsemnatul ._______________________________________________.\n"
	    	+ "conform dispozitiei nr. 14/04.02.2020, am primit suma de .______________. lei\n"
	    	+ "de la casier .______________________________________. si foile de varsamant\n"
	    	+ "de la numarul .________________. pana la numarul .___________________.\n\n\n\n").setFontSize(14f);
	    
	 
	   
	    doc.add(decl);
	    
	    Table semnaturi = new Table(1);

	    semnaturi.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph p1 = new Paragraph();
	    Text primar = new Text("Am primit,");
	    p1.add(primar);
	    p1.addTabStops(new TabStop(width / 1.18f, TabAlignment.RIGHT));
	    p1.add(new Tab());
	    Text intocmit = new Text("Am predat,");
	    p1.add(intocmit);
	    Cell cell1 = new Cell();
	    cell1.setBorder(Border.NO_BORDER);
	    cell1.add(p1);
	    semnaturi.addCell(cell1);
	    doc.add(semnaturi);

	    Table semnaturiR = new Table(1);

	    semnaturiR.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph semnR = new Paragraph();
	    //String dateNow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	    Text semnPrim = new Text(PDFHelper.getStrWithDots(30, ""));
	    semnR.add(semnPrim);
	    semnR.addTabStops(new TabStop(width / 1.18f, TabAlignment.RIGHT));
	    semnR.add(new Tab());
	    Text semnIntocmit = new Text(PDFHelper.getStrWithDots(30, ""));
	    semnR.add(semnIntocmit);
	    Cell cell2 = new Cell();
	    cell2.setBorder(Border.NO_BORDER);
	    cell2.add(semnR);
	    semnaturiR.addCell(cell2);
	    doc.add(semnaturiR);

	    doc.close();
	    document.close();
	    writer.flush();
	  

	
	} catch (IOException ex2) {
	}
	
    }

    private void generatePDF(final String tm, final File pdfFile, final Map<String, String> map) {
	try {
	    writer = new PdfWriter(pdfFile);
	    Image antetLogo = PDFHelper.getAntetLogo();
	    document = new PdfDocument(writer);
	    document.getDocumentInfo().addCreationDate();
	    document.getDocumentInfo().setAuthor("spectral369");
	    document.getDocumentInfo().setTitle("PV_Predare_primire_" + tm);
	    document.setDefaultPageSize(PageSize.A4);
	    Document doc = new Document(document);
	    float width = doc.getPageEffectiveArea(PageSize.A4).getWidth();
	    document.addEventHandler(PdfDocumentEvent.START_PAGE, new FooterEvt(width));

	    final Paragraph antet = new Paragraph();

	    float documentWidth = document.getDefaultPageSize().getWidth() - doc.getLeftMargin() - doc.getRightMargin();

	    float documentHeight = document.getDefaultPageSize().getHeight() - doc.getTopMargin()
		    - doc.getBottomMargin();

	    antetLogo.scaleToFit(documentWidth, documentHeight);

	    antet.add(antetLogo);
	    antet.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    doc.add(antet);

	 

	    final Paragraph titlu = new Paragraph("\n\n\n\n");
	    Text t1 = new Text("PROCES VERBAL DE PREDARE-PRIMIRE").setFontSize(18f).setBold().setUnderline();
	    titlu.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    titlu.setTextAlignment(TextAlignment.CENTER);
	    titlu.add(t1).addStyle(PDFHelper.bold12nr);
	    doc.add(titlu);

	    
	    
	    
	    Paragraph incheiat =  new Paragraph("\n\n\n");
	    Text inc =  new Text("INCHEIAT ASTAZI ").setFontSize(16f);
	    incheiat.add(inc);
	    incheiat.add(PDFHelper.createAdjustableParagraph(70,
		    new Paragraph(new SimpleDateFormat("dd-MM-yyyy").format(new Date())).setBold().setFontSize(17f).setTextAlignment(TextAlignment.CENTER)));
	    Text inc2 = new  Text(" .\n").setFontSize(16f);
	    incheiat.add(inc2);
	    Text smallinfo =  new Text("(data si ora)\n\n\n").setFontSize(10f);
	    
	    incheiat.add(smallinfo);
	    
	    doc.add(incheiat);
	    
	    Paragraph decl = new Paragraph().setFontSize(15f);
	    decl.add(new Tab());
	    decl.add("Subsemnatul .");
	    decl.add(PDFHelper.createAdjustableParagraph(85, new Paragraph(map.get("nume")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    decl.add(" .\nconform dispozitiei nr. 14/04.02.2020, am primit suma de .");
	    decl.add(PDFHelper.createAdjustableParagraph(25, new Paragraph(map.get("suma")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    decl.add(". lei \n de la casier .");
	    decl.add(PDFHelper.createAdjustableParagraph(60, new Paragraph(map.get("casier")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    decl.add(". si foile de varsamant\nde la numarul .");
	    decl.add(PDFHelper.createAdjustableParagraph(20, new Paragraph(map.get("foaie1")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    decl.add(". pana la numarul .");
	    decl.add(PDFHelper.createAdjustableParagraph(20, new Paragraph(map.get("foaie2")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    decl.add(".\n\n\n\n");        	   
	    doc.add(decl);
	    
	    Table semnaturi = new Table(1);

	    semnaturi.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph p1 = new Paragraph();
	    Text primar = new Text("Am primit,");
	    p1.add(primar);
	    p1.addTabStops(new TabStop(width / 1.18f, TabAlignment.RIGHT));
	    p1.add(new Tab());
	    Text intocmit = new Text("Am predat,");
	    p1.add(intocmit);
	    Cell cell1 = new Cell();
	    cell1.setBorder(Border.NO_BORDER);
	    cell1.add(p1);
	    semnaturi.addCell(cell1);
	    doc.add(semnaturi);

	    Table semnaturiR = new Table(1);

	    semnaturiR.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph semnR = new Paragraph();
	    //String dateNow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	    Text semnPrim = new Text(PDFHelper.getStrWithDots(30, ""));
	    semnR.add(semnPrim);
	    semnR.addTabStops(new TabStop(width / 1.18f, TabAlignment.RIGHT));
	    semnR.add(new Tab());
	    Text semnIntocmit = new Text(PDFHelper.getStrWithDots(30, ""));
	    semnR.add(semnIntocmit);
	    Cell cell2 = new Cell();
	    cell2.setBorder(Border.NO_BORDER);
	    cell2.add(semnR);
	    semnaturiR.addCell(cell2);
	    doc.add(semnaturiR);

	    doc.close();
	    document.close();
	    writer.flush();
	  

	
	} catch (IOException ex2) {
	}
	
	
    }

}
