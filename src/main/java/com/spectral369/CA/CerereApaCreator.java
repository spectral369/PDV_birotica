package com.spectral369.CA;


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

public class CerereApaCreator {
    File pdff = null;
    private transient PdfDocument document;
    private transient PdfWriter writer;
    String id = Utils.getRandomStringSerial("IU");

    public CerereApaCreator(Map<String, String> map, String tm) {

	pdff = new File(Utils.getSaveFileLocation("Cerere_apa_" + tm + ".pdf"));
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
	    document.getDocumentInfo().setTitle("Cerere_apa_" + tm);
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

	    final Paragraph nrInreg = new Paragraph();
	    nrInreg.add("\n\n");
	    nrInreg.add(new Tab());
	    Text nrI = new Text(
		    "\tNr. " + PDFHelper.getStrWithDots(15, "") + " " + "data " + PDFHelper.getStrWithDots(20, ""));
	    nrInreg.add(nrI);
	    doc.add(nrInreg);

	    final Paragraph titlu = new Paragraph();
	    Text t1 = new Text("\n\nCATRE\nPRIMARIA COMUNEI DUDESTII VECHI\n\n").setBold();
	    titlu.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    titlu.setTextAlignment(TextAlignment.CENTER);
	    titlu.add(t1).addStyle(PDFHelper.bold12nr);
	    doc.add(titlu);

	    
	    final Paragraph declaratie = new Paragraph();
	    declaratie.add(PDFHelper.addTab());
	    Text dec1 = new Text("Subsemnatul " + PDFHelper.getStrWithDots(50, "") + " avand C.N.P "
		    + PDFHelper.getStrWithDots(30, "") + " domiciliat in localitatea");
	    declaratie.add(dec1);
	    dec1.setTextAlignment(TextAlignment.CENTER);

	    Text dec11 = new Text(PDFHelper.getStrWithDots(32, "")+" la numarul " + PDFHelper.getStrWithDots(12, "") + " telefon "
		    + PDFHelper.getStrWithDots(32, ""));
	    dec11.setTextAlignment(TextAlignment.CENTER);
	    declaratie.add(dec11);
	   // declaratie.add(PDFHelper.addTab());
	    Text dec2 = new Text(" judetul Timis prin prezenta sesizez faptul ca: \n"
		    + PDFHelper.getStrWithDots(466, "") + " .\n");
	    dec2.setTextAlignment(TextAlignment.CENTER);
	    declaratie.add(dec2);
	    declaratie.setTextAlignment(TextAlignment.CENTER);

	    doc.add(declaratie);

	    final Paragraph declaratie2 = new Paragraph();
	 
	    declaratie2.add(PDFHelper.addTab());
	    Text dec4 = new Text(
		    "Va rog sa verificati situatia si sa procedati la remedierea sitatiei intrucat nu mai am access "
		    + "la apa potabila precum si sa nu fie pierderi de apa de la retea.\n");
	    dec4.setTextAlignment(TextAlignment.CENTER);
	    declaratie2.add(dec4);
	    declaratie2.add(PDFHelper.addTab());
	    Text dec5 = new Text("Va multumesc pentru intelegere.\n\n");
	    dec5.setTextAlignment(TextAlignment.CENTER);
	    declaratie2.add(dec5);
	    declaratie2.add(PDFHelper.addTab());
	    doc.add(declaratie2);

	    Table semnaturi = new Table(1);

	    semnaturi.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph p1 = new Paragraph();
	    Text primar = new Text("Data");
	    primar.setUnderline();
	    p1.add(primar);
	    p1.addTabStops(new TabStop(width / 1.18f, TabAlignment.RIGHT));
	    p1.add(new Tab());
	    Text intocmit = new Text("Semnatura");
	    intocmit.setUnderline();
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
	    document.getDocumentInfo().setTitle("Inchiriere_utilaje_" + tm);
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

	    final Paragraph nrInreg = new Paragraph();
	    nrInreg.add("\n\n");
	    nrInreg.add(new Tab());
	    Text nrI = new Text(
		    "\tNr. " + PDFHelper.getStrWithDots(15, "") + " " + "data " + PDFHelper.getStrWithDots(20, ""));
	    nrInreg.add(nrI);
	    doc.add(nrInreg);

	    final Paragraph titlu = new Paragraph();
	    Text t1 = new Text("\nCATRE\nPRIMARIA COMUNEI DUDESTII VECHI\n\n").setBold();
	    titlu.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    titlu.setTextAlignment(TextAlignment.CENTER);
	    titlu.add(t1).addStyle(PDFHelper.bold12nr);
	    doc.add(titlu);
	    
	    
	    Paragraph dec =  new Paragraph();
	    dec.add(new Tab());
	    dec.add("Subsemnatul/a ");
	    dec.add(PDFHelper.createAdjustableParagraph(50,
		    new Paragraph(map.get("nume")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(" avand C.N.P ");
	    dec.add(PDFHelper.createAdjustableParagraph(32,
		    new Paragraph(map.get("cnp")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(" domiciliat/a in localitatea ");
	    dec.add(PDFHelper.createAdjustableParagraph(30,
		    new Paragraph(map.get("localitate")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(" la numarul ");
	    dec.add(PDFHelper.createAdjustableParagraph(8,
		    new Paragraph(map.get("nrCasaAddr")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(" si telefon ");
	    dec.add(PDFHelper.createAdjustableParagraph(20,
		    new Paragraph(map.get("telefon")).setBold().setTextAlignment(TextAlignment.CENTER)));
	  //  dec.add(" .\n");
	    //dec.add(new Tab());
	    dec.add("judetul Timis prin prezenta sesizez faptul ca:\n");
	    dec.add(PDFHelper.createAdjustableParagraph(250,
		    new Paragraph(map.get("problema")).setBold().setTextAlignment(TextAlignment.CENTER)));
	 
	    doc.add(dec);

	    final Paragraph declaratie2 = new Paragraph();
	    declaratie2.add(PDFHelper.addTab());
	    Text dec4 = new Text(
		    "Va rog sa verificati situatia si sa procedati la remedierea situatiei intrucat nu mai am acces "
		    + "la apa potabila precum si sa nu fie pierderi de apa de la retea.\n");
	    dec4.setTextAlignment(TextAlignment.CENTER);
	    declaratie2.add(dec4);
	
	    declaratie2.add(PDFHelper.addTab());
	    Text dec5 = new Text("Va multumesc pentru intelegere.\n\n");
	    dec5.setTextAlignment(TextAlignment.CENTER);
	    declaratie2.add(dec5);
	    doc.add(declaratie2);
	   

	    Table semnaturi = new Table(1);

	    semnaturi.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph datasemn = new Paragraph();
	    Text primar = new Text("Data");
	    primar.setUnderline();
	    datasemn.add(primar);
	    datasemn.addTabStops(new TabStop(width / 1.18f, TabAlignment.RIGHT));
	    datasemn.add(new Tab());
	    Text intocmit = new Text("Semnatura");
	    intocmit.setUnderline();
	    datasemn.add(intocmit);
	    Cell cellsemn1 = new Cell();
	    cellsemn1.setBorder(Border.NO_BORDER);
	    cellsemn1.add(datasemn);
	    semnaturi.addCell(cellsemn1);
	    doc.add(semnaturi);

	    Table semnaturiR = new Table(1);

	    semnaturiR.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph semnR = new Paragraph();
	    String dateNow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	    Text semnPrim = new Text(dateNow);
	    semnR.add(semnPrim);
	    semnR.addTabStops(new TabStop(width / 1.18f, TabAlignment.RIGHT));
	    semnR.add(new Tab());
	    Text semnIntocmit = new Text(PDFHelper.getStrWithDots(30, ""));
	    semnR.add(semnIntocmit);
	    Cell cellsemn2 = new Cell();
	    cellsemn2.setBorder(Border.NO_BORDER);
	    cellsemn2.add(semnR);
	    semnaturiR.addCell(cellsemn2);
	    doc.add(semnaturiR);

	    doc.close();
	    document.close();
	    writer.flush();
	  

	
	} catch (IOException ex2) {
	}
	
    }

}

