package com.spectral369.AAI;

import java.io.File;
import java.io.IOException;
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
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.spectral369.birotica.PdfList;
import com.spectral369.utils.FooterEvt;
import com.spectral369.utils.PDFHelper;
import com.spectral369.utils.Utils;

public class AdeverintaAjutorIncalzireCreator {
    File pdff = null;
    private transient PdfDocument document;
    private transient PdfWriter writer;
    String id = Utils.getRandomStringSerial("AAI");

    public AdeverintaAjutorIncalzireCreator(Map<String, String> map, String tm) {

	pdff = new File(Utils.getSaveFileLocation("Adeverinta_Ajutor_Incalzire_" + tm + ".pdf"));
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
	    document.getDocumentInfo().setTitle("Adeverinta_ajutor_incalzire_" + tm);
	    document.setDefaultPageSize(PageSize.A4);
	    Document doc = new Document(document);
	   // doc.setFontSize(11.0f);
	    float width = doc.getPageEffectiveArea(PageSize.A4).getWidth();
	    float marginLeft =  doc.getLeftMargin();
	    float marginRIght = doc.getRightMargin();
	    document.addEventHandler(PdfDocumentEvent.END_PAGE, new FooterEvt(width+(marginLeft+marginRIght)));

	    final Paragraph antet = new Paragraph();

	    float documentWidth = document.getDefaultPageSize().getWidth() - doc.getLeftMargin() - doc.getRightMargin();

	    float documentHeight = document.getDefaultPageSize().getHeight() - doc.getTopMargin()
		    - doc.getBottomMargin();
	 
	    antetLogo.scaleToFit(documentWidth-50, documentHeight-80);

	    antet.add(antetLogo);
	    antet.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    antet.setTextAlignment(TextAlignment.CENTER);
	    doc.add(antet);

	    final Paragraph nrInreg = new Paragraph();
	    nrInreg.add("\n\n");
	    nrInreg.add(new Tab());
	    Text nrI = new Text(
		    "\tNr. " + PDFHelper.getStrWithDash(12, "") + " " + "/ " + PDFHelper.getStrWithDash(18, ""));
	    nrInreg.add(nrI);
	    doc.add(nrInreg);

	    final Paragraph titlu = new Paragraph();
	    Text t1 = new Text("\nAdeverinta\n").setCharacterSpacing(2f).setBold().setFontSize(16f);
	    titlu.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    titlu.setTextAlignment(TextAlignment.CENTER);
	    titlu.add(t1).addStyle(PDFHelper.bold12nr);
	    doc.add(titlu);

	    final Paragraph declaratie = new Paragraph();
	    declaratie.add(PDFHelper.addTab());
	    Text dec1 = new Text("Se adevereste prin prezenta ca dl./d-na. " + PDFHelper.getStrWithDash(32, "")
		    + " domiciliat/a in comuna " + PDFHelper.getStrWithDash(20, "") + ", nr. "
		    + PDFHelper.getStrWithDash(6, "") + ", avand C.N.P "
		    + PDFHelper.getStrWithDash(18, "") + ","
		    + " figureaza in registrul agricol al localitatii " + PDFHelper.getStrWithDash(25, "")
		    + " cu urmatoarea stare materiala:\n");
	    declaratie.add(dec1);

	    Table table = new Table(3);
	    table.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.setVerticalAlignment(VerticalAlignment.MIDDLE);
	    table.setTextAlignment(TextAlignment.CENTER);
	     table.setWidth(width/1.3f);
	    
	    Cell cell = new Cell();
	    cell.add(new Paragraph("Suprafata Constructie Corp Principal-mp-"));
	    cell.setWidth(width/2.22f);
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    
	    cell = new Cell();
	    cell.add(new Paragraph());
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    
	    cell = new Cell();
	    cell.add(new Paragraph());
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    
	    //row1
	    cell = new Cell();
	    cell.add(new Paragraph("Suprafata Corp Anexe-mp-"));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    
	    cell = new Cell();
	    cell.add(new Paragraph());
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    
	    cell = new Cell();
	    cell.add(new Paragraph());
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    
	    
	    //row3
	    
	    cell = new Cell();
	    cell.add(new Paragraph("Suprafata Teren Intravilan-mp-"));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    
	    cell = new Cell(1,2);
	    cell.add(new Paragraph());
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    
	   
	    
	    //row4
	    
	    cell = new Cell();
	    cell.add(new Paragraph("Suprafata Teren Extravilan-ha-"));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    
	    cell = new Cell(1,2);
	    cell.add(new Paragraph());
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    
	    
	    
	    
	    declaratie.add(PDFHelper.addTab());
	  
	 //   declaratie.add(dec2);

	    doc.add(declaratie);
	    doc.add(table);
	    Paragraph dec12 = new Paragraph();
	    dec12.add("\n\n\n\n\n\n\n\n");
	    dec12.add(PDFHelper.addTab());
	   
	    Text dec2 = new Text(
		    "Am eliberat prezenta adeverinta pe baza datelor din registrul de rol spre a-i servi "
		    + "susnumitului/ei la completarea dosarului pentru "+PDFHelper.getStrWithDash(20, "")+".\n\n\n\n\n\n\n\n");
	    dec12.add(dec2);
	    doc.add(dec12);
	    

	    Table semnaturi = new Table(3);
	    semnaturi.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    semnaturi.setTextAlignment(TextAlignment.CENTER);

	    semnaturi.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph p1 = new Paragraph();
	    Paragraph p3 = new Paragraph();
	    Text primar = new Text("PRIMAR,");
	    primar.setUnderline();
	    p1.add(primar);


	    Text intocmit = new Text("INTOCMIT,");
	    intocmit.setUnderline();
	    p3.add(intocmit);

	    Cell cell1 = new Cell();
	    cell1.setWidth(width / 2.2f);
	    cell1.setBorder(Border.NO_BORDER);
	    cell1.add(p1);


	    Cell cell3 = new Cell();
	    cell3.setWidth(width / 2.2f);
	    cell3.setBorder(Border.NO_BORDER);
	    cell3.add(p3);

	    semnaturi.addCell(cell1);
	    semnaturi.addCell(cell3);
	    doc.add(semnaturi);

	    Table semnaturiR = new Table(3);
	    semnaturiR.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    semnaturiR.setTextAlignment(TextAlignment.CENTER);

	    Paragraph semnR1 = new Paragraph();
	    Paragraph semnR3 = new Paragraph();

	    Text semnPrim = new Text(Utils.getSettingsInfo().get("primar")[0].toUpperCase());
	    semnR1.add(semnPrim);


	    Text semnIntocmit = new Text(PDFHelper.getStrWithDash(20, ""));
	    semnR3.add(semnIntocmit);

	    Cell cell21 = new Cell();
	    cell21.setWidth(width / 2.2f);
	    cell21.setBorder(Border.NO_BORDER);
	    cell21.add(semnR1);


	    Cell cell23 = new Cell();
	    cell23.setWidth(width / 2.2f);
	    cell23.setBorder(Border.NO_BORDER);
	    cell23.add(semnR3);

	    semnaturiR.addCell(cell21);
	    semnaturiR.addCell(cell23);
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
	    document.getDocumentInfo().setTitle("Adeverinta_ajutor_incalzire_" + tm);
	    document.setDefaultPageSize(PageSize.A4);
	    Document doc = new Document(document);
	   // doc.setFontSize(11.0f);
	    float width = doc.getPageEffectiveArea(PageSize.A4).getWidth();
	    float marginLeft =  doc.getLeftMargin();
	    float marginRIght = doc.getRightMargin();
	    document.addEventHandler(PdfDocumentEvent.END_PAGE, new FooterEvt(width+(marginLeft+marginRIght)));

	    final Paragraph antet = new Paragraph();

	    float documentWidth = document.getDefaultPageSize().getWidth() - doc.getLeftMargin() - doc.getRightMargin();

	    float documentHeight = document.getDefaultPageSize().getHeight() - doc.getTopMargin()
		    - doc.getBottomMargin();
	 
	    antetLogo.scaleToFit(documentWidth-50, documentHeight-80);

	    antet.add(antetLogo);
	    antet.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    antet.setTextAlignment(TextAlignment.CENTER);
	    doc.add(antet);

	    final Paragraph nrInreg = new Paragraph();
	    nrInreg.add("\n\n");
	    nrInreg.add(new Tab());
	    Text nrI = new Text(
		    "\tNr. " + PDFHelper.getStrWithDash(12, "") + " " + "/ " + PDFHelper.getStrWithDash(18, ""));
	    nrInreg.add(nrI);
	    doc.add(nrInreg);

	    final Paragraph titlu = new Paragraph();
	    Text t1 = new Text("\nAdeverinta\n").setCharacterSpacing(2f).setBold().setFontSize(16f);
	    titlu.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    titlu.setTextAlignment(TextAlignment.CENTER);
	    titlu.add(t1).addStyle(PDFHelper.bold12nr);
	    doc.add(titlu);

	    String num = null;
	    String dom = null;
	    String susnum = null;
	    if (map.get("titlu").contains("Dl")) {
		num = new String("numitul");
		dom = new String("domiciliat");
		susnum = new String("susnumitului");
	    } else {
		num = new String("numita");
		dom = new String("domiciliata");
		susnum = new String("susnumitei");
	    }
	    
	    final Paragraph declaratie = new Paragraph();
	   
	    declaratie.add(new Tab());
	    declaratie.add("Se adevereste prin prezenta ca ");
	    declaratie.add(" "+num+" ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(42,
		    new Paragraph(map.get("nume")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" "+dom);
	    declaratie.add(" in localitatea ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(25,
		    new Paragraph(map.get("localitate")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" Nr. ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(12,
		    new Paragraph(map.get("nrCasaAddr")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(", avand C.N.P ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(25,
		    new Paragraph(map.get("cnp")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(", figureaza in registrul agricol al localitatii ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(32,
		    new Paragraph(map.get("localitate")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" cu urmatoarea stare materiala:\n");
	    

	    Table table = new Table(3);
	    table.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.setVerticalAlignment(VerticalAlignment.MIDDLE);
	    table.setTextAlignment(TextAlignment.CENTER);
	     table.setWidth(width/1.12f);
	    
	    Cell cell = new Cell();
	    cell.add(new Paragraph("Suprafata Constructie"));
	    cell.setWidth(width/4f);
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
	    table.addCell(cell);
	    
	    cell = new Cell();
	    cell.add(new Paragraph(map.get("supCasa")+" m²"));
	    cell.setWidth(width/4.5f);
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
	    table.addCell(cell);
	    
	    cell = new Cell();
	    cell.add(new Paragraph(map.get("tipCasa")));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
	    table.addCell(cell);
	    
	    //row1
	    cell = new Cell();
	    cell.add(new Paragraph("Suprafata Anexe"));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
	    table.addCell(cell);
	    
	    cell = new Cell();
	    cell.add(new Paragraph(map.get("supAnexe")+" m²"));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
	    table.addCell(cell);
	    
	    cell = new Cell();
	    cell.add(new Paragraph(map.get("tipAnexe")));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
	    table.addCell(cell);
	    
	    
	    //row3
	    
	    cell = new Cell();
	    cell.add(new Paragraph("Suprafata Teren Intravilan"));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
	    table.addCell(cell);
	    
	    cell = new Cell(1,2);
	    cell.add(new Paragraph(map.get("supIntravilan")+" m²"));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
	    table.addCell(cell);
	    
	   
	    
	    //row4
	    
	    cell = new Cell();
	    cell.add(new Paragraph("Suprafata Teren Extravilan"));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
	    table.addCell(cell);
	    
	    cell = new Cell(1,2);
	    cell.add(new Paragraph(map.get("supExtravilan")+" ha"));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
	    table.addCell(cell);
	    
	     
	    declaratie.add(PDFHelper.addTab());
	  

	    doc.add(declaratie);
	    doc.add(table);
	    
	    Paragraph dec12 = new Paragraph();
	    dec12.add("\n\n\n\n\n\n\n");
	    dec12.add(PDFHelper.addTab());
	   
	    Text dec2 = new Text(
		    "Am eliberat prezenta adeverinta pe baza datelor din registrul de rol spre a-i servi ");
	    dec12.add(dec2);
	    dec12.add(" "+susnum+" ");
	    dec12.add("la completarea dosarului pentru ");
	    dec12.add(PDFHelper.createAdjustableParagraph(30,
		    new Paragraph(map.get("detine")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec12.add(" .\n\n\n");
	    doc.add(dec12);
	    

	    Table semnaturi = new Table(3);
	    semnaturi.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    semnaturi.setTextAlignment(TextAlignment.CENTER);

	    semnaturi.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph p1 = new Paragraph();
	    Paragraph p3 = new Paragraph();
	    Text primar = new Text("PRIMAR,");
	    primar.setUnderline();
	    p1.add(primar);


	    Text intocmit = new Text("INTOCMIT,");
	    intocmit.setUnderline();
	    p3.add(intocmit);

	    Cell cell1 = new Cell();
	    cell1.setWidth(width / 2.2f);
	    cell1.setBorder(Border.NO_BORDER);
	    cell1.add(p1);


	    Cell cell3 = new Cell();
	    cell3.setWidth(width / 2.2f);
	    cell3.setBorder(Border.NO_BORDER);
	    cell3.add(p3);

	    semnaturi.addCell(cell1);
	    semnaturi.addCell(cell3);
	    doc.add(semnaturi);

	    Table semnaturiR = new Table(3);
	    semnaturiR.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    semnaturiR.setTextAlignment(TextAlignment.CENTER);

	    Paragraph semnR1 = new Paragraph();
	    Paragraph semnR3 = new Paragraph();

	    Text semnPrim = new Text(Utils.getSettingsInfo().get("primar")[0].toUpperCase());
	    semnR1.add(semnPrim);


	    Text semnIntocmit = new Text(map.get("intocmit").toUpperCase());
	    semnR3.add(semnIntocmit);

	    Cell cell21 = new Cell();
	    cell21.setWidth(width / 2.2f);
	    cell21.setBorder(Border.NO_BORDER);
	    cell21.add(semnR1);


	    Cell cell23 = new Cell();
	    cell23.setWidth(width / 2.2f);
	    cell23.setBorder(Border.NO_BORDER);
	    cell23.add(semnR3);

	    semnaturiR.addCell(cell21);
	    semnaturiR.addCell(cell23);
	    doc.add(semnaturiR);

	    doc.close();
	    document.close();
	    writer.flush();


	} catch (IOException ex2) {
	}

    }

}
