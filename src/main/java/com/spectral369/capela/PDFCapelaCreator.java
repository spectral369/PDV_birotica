package com.spectral369.capela;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TabAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.spectral369.birotica.PdfList;
import com.spectral369.utils.PDFHelper;
import com.spectral369.utils.Utils;

public class PDFCapelaCreator {
    File pdff = null;
    private transient PdfDocument document;
    private transient PdfWriter writer;
    String id = Utils.getRandomStringSerial("CC");

    public PDFCapelaCreator(Map<String, String> map, String tm) {

	pdff = new File(Utils.getSaveFileLocation("Cerere_capela_" + tm + ".pdf"));
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
	    document.getDocumentInfo().setTitle("Cerere_capela_" + tm);
	    document.setDefaultPageSize(PageSize.A4);
	    Document doc = new Document(document);
	    float width = doc.getPageEffectiveArea(PageSize.A4).getWidth();
	    document.addEventHandler(PdfDocumentEvent.START_PAGE, new handleEvt(width));

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
	    final Text t1 = new Text("\n\n\n\nCerere pentru Inchirierea Capelei\n\n\n").setBold();
	    titlu.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    titlu.setTextAlignment(TextAlignment.CENTER);
	    titlu.add(t1).addStyle(PDFHelper.bold12nr);
	    doc.add(titlu);

	    final Paragraph declaratie = new Paragraph();
	    declaratie.add(PDFHelper.addTab());
	    Text dec1 = new Text("Subsemnatul/a " + PDFHelper.getStrWithDots(40, "") + "  domiciliat in "
		    + PDFHelper.getStrWithDots(55, "") + " judet " + PDFHelper.getStrWithDots(18, "") + " nr. "
		    + PDFHelper.getStrWithDots(12, "") + " avand C.N.P " + PDFHelper.getStrWithDots(37, "")
		    + " prin prezenta solicit aprobarea inchirierii capelei mortuare din " + "Dudestii-Vechi pentru "
		    + PDFHelper.getStrWithDots(10, "") + " zile, incepand cu data de "
		    + PDFHelper.getStrWithDots(18, "") + ".\n");
	    declaratie.add(dec1);
	    declaratie.add(PDFHelper.addTab());
	    Text dec2 = new Text("Mentionez ca voi achita taxa de inchiriere in termen de  5 zile de"
		    + " la desfasurarea evenimentului.\n\n\n");
	    declaratie.add(dec2);
	    doc.add(declaratie);

	    Table semnaturi = new Table(1);

	    semnaturi.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph p1 = new Paragraph();
	    Text primar = new Text("Data");
	    p1.add(primar);
	    p1.addTabStops(new TabStop(width / 1.18f, TabAlignment.RIGHT));
	    p1.add(new Tab());
	    Text intocmit = new Text("Semnatura");
	    p1.add(intocmit);
	    Cell cell1 = new Cell();
	    cell1.setBorder(Border.NO_BORDER);
	    cell1.add(p1);
	    semnaturi.addCell(cell1);
	    doc.add(semnaturi);

	    Table semnaturiR = new Table(1);

	    semnaturiR.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph semnR = new Paragraph();
	  //  String dateNow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
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
	    document.getDocumentInfo().setTitle("Cerere_capela_" + tm);
	    document.setDefaultPageSize(PageSize.A4);
	    Document doc = new Document(document);
	  //  doc.setFontSize(12);// test
	    float width = doc.getPageEffectiveArea(PageSize.A4).getWidth();
	    document.addEventHandler(PdfDocumentEvent.START_PAGE, new handleEvt(width));

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
	    final Text t1 = new Text("\n\n\nCerere pentru Inchirierea Capelei\n\n\n").setBold().setUnderline();
	    titlu.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    titlu.setTextAlignment(TextAlignment.CENTER);
	    titlu.add(t1).addStyle(PDFHelper.bold12nr);
	    doc.add(titlu);
	    
	    
	    Paragraph decl1 =  new Paragraph();
	    decl1.add(new Tab());
	    decl1.add("Subsemnatul/a ");
	    decl1.add(PDFHelper.createAdjustableParagraph(40,
		    new Paragraph(map.get("nume")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    decl1.add(" domiciliat in ");
	    decl1.add(PDFHelper.createAdjustableParagraph(55,
		    new Paragraph(map.get("localitate")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    decl1.add(" judet ");
	    decl1.add(PDFHelper.createAdjustableParagraph(12,
		    new Paragraph(map.get("judet")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    decl1.add(" Nr. ");
	    decl1.add(PDFHelper.createAdjustableParagraph(10,
		    new Paragraph(map.get("nrStrada")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    decl1.add(" avand C.N.P ");
	    decl1.add(PDFHelper.createAdjustableParagraph(30,
		    new Paragraph(map.get("cnp")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    decl1.add(" prin prezenta solicit aprobarea inchirierii capelei mortuare din Dudestii-vechi pentru ");
	    decl1.add(PDFHelper.createAdjustableParagraph(8,
		    new Paragraph(map.get("nrZile")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    decl1.add(" zile, incepand cu data de ");
	    decl1.add(PDFHelper.createAdjustableParagraph(16,
		    new Paragraph(map.get("ziua") + "-" + map.get("luna") + "-" + map.get("anul")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    decl1.add(" .\n");
	    decl1.add(new Tab());
	    decl1.add("Mentionez ca voi achita taxa de inchiriere in termen de  5 zile de"
		    + " la desfasurarea evenimentului.\n\n\n");
	    doc.add(decl1);
	    
	    
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
	    Cell semn1 = new Cell();
	    semn1.setBorder(Border.NO_BORDER);
	    semn1.add(p1);
	    semnaturi.addCell(semn1);
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
	    Cell semn2 = new Cell();
	    semn2.setBorder(Border.NO_BORDER);
	    semn2.add(semnR);
	    semnaturiR.addCell(semn2);
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
