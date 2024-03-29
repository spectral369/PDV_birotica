package com.spectral369.ASEA;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
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
import com.spectral369.utils.PDFHelper;
import com.spectral369.utils.Utils;

public class AdresaScoatereEvidentaAutoCreator {
    File pdff = null;
    private transient PdfDocument document;
    private transient PdfWriter writer;
    String id = Utils.getRandomStringSerial("ASEA");

    public AdresaScoatereEvidentaAutoCreator(Map<String, String> map, String tm) {

	pdff = new File(Utils.getSaveFileLocation("Adeverinta_Scoatere_Evidenta_Auto_" + tm + ".pdf"));
	if (map.isEmpty()) {
	    generatePDF(tm, pdff);
	    try {
		PdfList.addFile(id, pdff.getCanonicalPath());
	    } catch (IOException e) {
		e.printStackTrace();
	    }

	} else {

	    generatePDF(tm, pdff, map);
	    try {
		PdfList.addFile(id, pdff.getCanonicalPath());
	    } catch (IOException e) {

		e.printStackTrace();
	    }
	}
    }

    public String getID() {
	return id;
    }
    public String getFileName() {
	return pdff.getName();
    }

    private void generatePDF(final String tm, final File pdfFile) {
	try {
	    writer = new PdfWriter(pdfFile);
	    Image antetLogo = PDFHelper.getAntetLogo();
	    document = new PdfDocument(writer);
	    document.getDocumentInfo().addCreationDate();
	    document.getDocumentInfo().setAuthor("spectral369");
	    document.getDocumentInfo().setTitle("Adeverinta_Scoatere_Evidenta_Auto_" + tm);

	    document.setDefaultPageSize(PageSize.A4);
	    Document doc = new Document(document);
	    doc.setFontSize(10.5f);
	    float width = doc.getPageEffectiveArea(PageSize.A4).getWidth();
	    document.addEventHandler(PdfDocumentEvent.START_PAGE, new handleEvt(width));
	    // document.open();
	    // document.addTitle();
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

	    Paragraph titlu = new Paragraph();
	    Text t1 = new Text("\n\n\n\nCatre "+PDFHelper.getStrWithDots(65, "")+"\n\n").setBold();   
	    titlu.setTextAlignment(TextAlignment.JUSTIFIED);
	    titlu.add(t1).addStyle(PDFHelper.bold12nr);
	    doc.add(titlu);

	    Paragraph declaratie = new Paragraph();
	    declaratie.add(PDFHelper.addTab());
	    declaratie.add("Va facem cunoscut ca potrivit actului "+PDFHelper.getStrWithDots(10, "")+"/"+PDFHelper.getStrWithDots(12, "")+
		    "contribuabilulul "+PDFHelper.getStrWithDots(40, "")+"cu domiciliul fiscal in ROMANIA/"+PDFHelper.getStrWithDots(20, "")+
		    " judetul/sectorul "+PDFHelper.getStrWithDots(25, "")+" codul postal "+PDFHelper.getStrWithDots(15, "")+
		    " municipiul/orasul"+" comuna "+PDFHelper.getStrWithDots(30, "")+" satul "+PDFHelper.getStrWithDots(25, "")+" str "+PDFHelper.getStrWithDots(35, "")+
		    "nr. "+PDFHelper.getStrWithDots(10, "")+" bl. "+PDFHelper.getStrWithDots(8, "")+" et. "+PDFHelper.getStrWithDots(8, "")+
		    " ap. "+PDFHelper.getStrWithDots(8, "")+" identificat prin B.I/C.I/C.I.P./Pasaport seria "+PDFHelper.getStrWithDots(10, "")+
		    " nr. "+PDFHelper.getStrWithDots(18, "")+" C.I.F. "+PDFHelper.getStrWithDots(18, "")+" tel./fax "+PDFHelper.getStrWithDots(35, "")+
		    " e-mail "+PDFHelper.getStrWithDots(20, "")+" a dobandit mijlocul de transport marca "+PDFHelper.getStrWithDots(35, "")+
		    " cu seria motor "+PDFHelper.getStrWithDots(15, "")+" seria sasiu "+PDFHelper.getStrWithDots(25, "")+" capacitate cilindrica "+PDFHelper.getStrWithDots(10, "")+
		    " cm3, capacitate "+PDFHelper.getStrWithDots(10, "")+"tone, conform contractului de instrainare - domandire nr. "+PDFHelper.getStrWithDots(10, "")+
		    "/"+PDFHelper.getStrWithDots(15, "")+" (inregistrat la organul fiscal local) /factura seria "+PDFHelper.getStrWithDots(10, "")+" nr. "+PDFHelper.getStrWithDots(12, "")+
		    "/"+PDFHelper.getStrWithDots(15, "")+" (inregistrata la organul fiscal local) sau alt act care atesta calitatea de proprietar "+PDFHelper.getStrWithDots(102, " ")
		    +PDFHelper.getStrWithDots(126, "")+".\n\n");   
	    doc.add(declaratie);
	    
	    
	    Paragraph dec2 =  new Paragraph();
	    dec2.add(new Tab());
	    dec2.add("Va rugam sa luati masurile necesare pentru inscrierea in evidentele dvs. conform titlului IX din"
	    	+ " Legea nr. 227/2015 privind Codul Fiscal, cu modificarile si completarile ulterioare.\n\n");
	    
	    doc.add(dec2);

	    Table semnaturi = new Table(1);

	    semnaturi.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph p1 = new Paragraph();
	    Text primar = new Text("Conducatorul organului fiscal local");
	    primar.setUnderline();
	    p1.add(primar);
	    p1.addTabStops(new TabStop(width / 1.18f, TabAlignment.RIGHT));
	    p1.add(new Tab());
	    Text intocmit = new Text("Intocmit,");
	    intocmit.setUnderline();
	    p1.add(intocmit);
	    Cell cell1 = new Cell();
	    cell1.setBorder(Border.NO_BORDER);
	    cell1.add(p1);
	    semnaturi.addCell(cell1);
	    doc.add(semnaturi);
	    doc.add(new Paragraph("\n"));
	    Table semnaturiR = new Table(1);

	    semnaturiR.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph semnR = new Paragraph();

	    Text semnPrim = new Text("L.S. "+PDFHelper.getStrWithDots(30, ""));

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
	    document.getDocumentInfo().setTitle("Adeverinta_Scoatere_Evidenta_Auto_" + tm);

	    document.setDefaultPageSize(PageSize.A4);
	    Document doc = new Document(document);
	    doc.setFontSize(10.5f);
	    float width = doc.getPageEffectiveArea(PageSize.A4).getWidth();
	    document.addEventHandler(PdfDocumentEvent.START_PAGE, new handleEvt(width));
	    // document.open();
	    // document.addTitle();
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

	    Paragraph titlu = new Paragraph();
	    Text t1 = new Text("\n\n\n\nCatre").setBold();
	    titlu.add(t1);
	    titlu.add(PDFHelper.addTab());
	    titlu.add(PDFHelper.createAdjustableParagraph(58,
		    new Paragraph(map.get("nume")).setBold()));
	    Text t2 = new Text("\n\n");
	    titlu.add(t2);
	    titlu.setTextAlignment(TextAlignment.JUSTIFIED);
	    titlu.addStyle(PDFHelper.bold12nr);
	    doc.add(titlu);

	    Paragraph declaratie = new Paragraph();
	    declaratie.add(PDFHelper.addTab());
	    
	    declaratie.add("Va facem cunoscut ca potrivit actului ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(8,
		    new Paragraph(map.get("act")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add("/");
	    declaratie.add(PDFHelper.createAdjustableParagraph(17,
		    new Paragraph(map.get("dataact")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" contribuabilul ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(52,
		    new Paragraph(map.get("contribuabil")).setBold().setTextAlignment(TextAlignment.CENTER)));  
	    declaratie.add(" cu domiciliul fiscal in ROMANIA/");
	    declaratie.add(PDFHelper.createAdjustableParagraph(12,
		    new Paragraph(map.get("tara")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" judet/sectorul ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(25,
		    new Paragraph(map.get("judet")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" codul postal ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(14,
		    new Paragraph(map.get("zip")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" municipiul/orasul ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(30,
		    new Paragraph(map.get("localitate")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" satul ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(25,
		    new Paragraph(map.get("sat")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" strada ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(35,
		    new Paragraph(map.get("strada")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" nr.");
	    declaratie.add(PDFHelper.createAdjustableParagraph(10,
		    new Paragraph(map.get("nrStr")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" bl.");
	    declaratie.add(PDFHelper.createAdjustableParagraph(8,
		    new Paragraph(map.get("bl")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" et.");
	    declaratie.add(PDFHelper.createAdjustableParagraph(8,
		    new Paragraph(map.get("et")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" ap.");
	    declaratie.add(PDFHelper.createAdjustableParagraph(8,
		    new Paragraph(map.get("ap")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" identificat prin B.I/C.I/C.I.P./Pasaport seria ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(10,
		    new Paragraph(map.get("ciserie")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" nr.");
	    declaratie.add(PDFHelper.createAdjustableParagraph(18,
		    new Paragraph(map.get("cinr")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" C.I.F.");
	    declaratie.add(PDFHelper.createAdjustableParagraph(18,
		    new Paragraph(map.get("cui")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" tel./fax ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(35,
		    new Paragraph(map.get("telefon")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" e-mail ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(28,
		    new Paragraph(map.get("email")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" a dobandit mijlocul de transport marca ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(20,
		    new Paragraph(map.get("marca")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" cu seria motor ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(20,
		    new Paragraph(map.get("seriemotor")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" seria sasiu ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(40,
		    new Paragraph(map.get("seriesasiu")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" capacitate cilindrica ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(8,
		    new Paragraph(map.get("capacitate")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" cm3, capacitate ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(8,
		    new Paragraph(map.get("tone")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" tone, conform contractului de instrainare-dobandire nr. ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(8,
		    new Paragraph(map.get("nrcontract")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add("/");
	    declaratie.add(PDFHelper.createAdjustableParagraph(17,
		    new Paragraph(map.get("datacontract")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" (inregistrat la organul fiscal local) /factura seria ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(12,
		    new Paragraph(map.get("seriefactura")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" nr. ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(12,
		    new Paragraph(map.get("nrfactura")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add("/");
	    declaratie.add(PDFHelper.createAdjustableParagraph(17,
		    new Paragraph(map.get("datafactura")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" (inregistrata la organul fiscal local) sau alt act care atesta calitatea de proprietar ");
	    if(map.get("altactprop").isBlank()||map.get("altactprop").isBlank())
		declaratie.add(PDFHelper.getStrWithDots(50, ""));
	    else
	    declaratie.add(PDFHelper.createAdjustableParagraph(50,
		    new Paragraph(map.get("altactprop")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    
	    doc.add(declaratie);
	    
	    Paragraph dec2 =  new Paragraph();
	    dec2.add(new Tab());
	    dec2.add("Va rugam sa luati masurile necesare pentru inscrierea in evidentele dvs. conform titlului IX din"
	    	+ " Legea nr. 227/2015 privind Codul Fiscal, cu modificarile si completarile ulterioare.\n\n");
	    
	    doc.add(dec2);

	    Table semnaturi = new Table(1);

	    semnaturi.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph p1 = new Paragraph();
	    Text primar = new Text("Conducatorul organului fiscal local");
	    primar.setUnderline();
	    p1.add(primar);
	    p1.addTabStops(new TabStop(width / 1.18f, TabAlignment.RIGHT));
	    p1.add(new Tab());
	    Text intocmit = new Text("Intocmit,");
	    intocmit.setUnderline();
	    p1.add(intocmit);
	    Cell cell1 = new Cell();
	    cell1.setBorder(Border.NO_BORDER);
	    cell1.add(p1);
	    semnaturi.addCell(cell1);
	    doc.add(semnaturi);

	    doc.add(new Paragraph("\n"));
	    Table semnaturiR = new Table(1);

	    semnaturiR.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph semnR = new Paragraph();

	    Text semnPrim = new Text("L.S. "+PDFHelper.getStrWithDots(30, ""));

	    semnR.add(semnPrim);
	    semnR.addTabStops(new TabStop(width / 1.18f, TabAlignment.RIGHT));
	    semnR.add(new Tab());
	    Text semnIntocmit = new Text(map.get("intocmit"));

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

class handleEvt implements IEventHandler {
    float width = 0;

    @Override
    public void handleEvent(Event event) {
	PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
	PdfPage page = docEvent.getPage();
	int pageNum = docEvent.getDocument().getPageNumber(page);
	int pageTotal = docEvent.getDocument().getNumberOfPages();
	PdfCanvas canvas = new PdfCanvas(page);
	canvas.beginText();
	try {
	    canvas.setFontAndSize(PdfFontFactory.createFont(StandardFonts.HELVETICA), 12);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	canvas.moveText(width / 2, 0);
	canvas.showText(String.format("%d/%d", pageNum, pageTotal));
	canvas.endText();
	canvas.stroke();
	canvas.release();

    }

    public handleEvt(float width) {
	this.width = width;
    }

}