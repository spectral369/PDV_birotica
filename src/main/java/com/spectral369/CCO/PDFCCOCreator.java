package com.spectral369.CCO;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
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
import com.itextpdf.layout.properties.VerticalAlignment;
import com.spectral369.birotica.PdfList;
import com.spectral369.utils.PDFHelper;
import com.spectral369.utils.Utils;

public class PDFCCOCreator {
    File pdff = null;
    private transient PdfDocument document;
    private transient PdfWriter writer;
    String id = Utils.getRandomStringSerial("CCO");

    public PDFCCOCreator(Map<String, String> map, String tm) {

	pdff = new File(Utils.getSaveFileLocation("Cerere_Concediu_Odihna_" + tm + ".pdf"));
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
	    document.setDefaultPageSize(PageSize.A4);
	    document.getDocumentInfo().setTitle("Cerere_Concediu_Odihna" + tm);

	    Document doc = new Document(document);
	    doc.setFontSize(12);
	    float width = doc.getPageEffectiveArea(PageSize.A4).getWidth();
	    document.addEventHandler(PdfDocumentEvent.START_PAGE, new handleEvt(width));

	    Paragraph antet = new Paragraph();

	    float documentWidth = document.getDefaultPageSize().getWidth() - doc.getLeftMargin() - doc.getRightMargin();
	    float documentHeight = document.getDefaultPageSize().getHeight() - doc.getTopMargin()
		    - doc.getBottomMargin();

	 
	    antetLogo.scaleToFit(documentWidth, documentHeight);

	    antet.add(antetLogo);
	    antet.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    doc.add(antet);

	    Table table1 = new Table(1);
	    table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph l1 = new Paragraph();
	    Text nrI = new Text(
		    "\n\nNr. " + PDFHelper.getStrWithDots(15, "") + " " + "data " + PDFHelper.getStrWithDots(20, ""));
	    l1.add(nrI);
	    l1.addTabStops(new TabStop(width / 1.2f, TabAlignment.RIGHT));
	    l1.add(new Tab());
	    Text aprobat = new Text("Aprobat");
	    l1.add(aprobat);
	    l1.add(PDFHelper.addTab());
	    Cell l1Cell = new Cell();
	    l1Cell.setBorder(Border.NO_BORDER);
	    l1Cell.add(l1);
	    table1.addCell(l1Cell);
	    doc.add(table1);

	    Table table2 = new Table(1);
	    Paragraph l2 = new Paragraph();
	    Text primSemn1 = new Text("");
	    l2.add(primSemn1);
	    l2.addTabStops(new TabStop(width / 1.235f, TabAlignment.RIGHT));
	    l2.add(new Tab());
	    Text primartop = new Text("Primar");
	    l2.add(primartop);
	    Cell l2Cell = new Cell();
	    l2Cell.add(l2);
	    l2Cell.setBorder(Border.NO_BORDER);
	    table2.addCell(l2Cell);
	    doc.add(table2);

	    Table table3 = new Table(1);
	    Paragraph l3 = new Paragraph();
	    Text topSemn1 = new Text("");
	    l3.add(topSemn1);
	    l3.addTabStops(new TabStop(width / 0.9f, TabAlignment.RIGHT));
	    l3.add(new Tab());
	    Text topSemn2 = new Text(PDFHelper.getStrWithDots(28, ""));
	    l3.add(topSemn2);
	    l3.add(PDFHelper.addTab());
	    Cell l3Cell = new Cell();
	    l3Cell.add(l3);
	    l3Cell.setBorder(Border.NO_BORDER);
	    table3.addCell(l3Cell);
	    doc.add(table3);

	    Paragraph titlu = new Paragraph();
	    Text t1 = new Text("\n\nCerere Concediu Odihna\n\n\n").setBold().setUnderline();
	    titlu.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    titlu.setTextAlignment(TextAlignment.CENTER);
	    titlu.add(t1).addStyle(PDFHelper.bold12nr);
	    doc.add(titlu);

	    Paragraph declaratie = new Paragraph();
	    declaratie.add(PDFHelper.addTab());
	    Text dec1 = new Text("Subsemnatul/a " + PDFHelper.getStrWithDash(35, "")
		    + ", salariat al Primariei Dudestii-Vechi, jud. " + "Timis, avand functia de "
		    + PDFHelper.getStrWithDash(38, "") + " prin prezenta, va rog sa-mi aprobati efectuarea" + " a "
		    + PDFHelper.getStrWithDash(7, "") + " zile din concediul de odihna aferent anului "
		    + PDFHelper.getStrWithDash(8, "") + " " + "din data de " + PDFHelper.getStrWithDash(18, "")
		    + " pana in data de " + PDFHelper.getStrWithDash(18, "") + ".");
	    declaratie.add(dec1);
	    declaratie.add(PDFHelper.addTab());
	    Text dec2 = new Text("\n\n\nVa multumesc!\n\n");
	    declaratie.add(dec2);
	    doc.add(declaratie);

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
	    // String dateNow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	    Text semnPrim = new Text(PDFHelper.getStrWithDots(30, ""));
	    // semnPrim.setUnderline();
	    semnR.add(semnPrim);
	    semnR.addTabStops(new TabStop(width / 1.18f, TabAlignment.RIGHT));
	    semnR.add(new Tab());
	    Text semnIntocmit = new Text(PDFHelper.getStrWithDots(30, ""));
	    // semnIntocmit.setUnderline();
	    semnR.add(semnIntocmit);
	    Cell cell2 = new Cell();
	    cell2.setBorder(Border.NO_BORDER);
	    cell2.add(semnR);
	    semnaturiR.addCell(cell2);
	    doc.add(semnaturiR);

	    Text inloc = new Text(
		    "Pe perioada efectuarii concediului de odihna integral sau partial, atributiile de serviciu "
			    + "sunt preluate de " + PDFHelper.getStrWithDash(45, ""));
	    setBottomtext(doc, document, inloc.getText());

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
	    document = new PdfDocument(writer);
	    document.getDocumentInfo().addCreationDate();
	    document.getDocumentInfo().setAuthor("spectral369");
	    document.getDocumentInfo().setTitle("Cerere_Concediu_Odihna" + tm);
	    document.setDefaultPageSize(PageSize.A4);
	    Document doc = new Document(document);
	    doc.setFontSize(12);
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

	    Table table1 = new Table(1);
	    table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph l1 = new Paragraph();
	    Text nrI = new Text(
		    "\n\nNr. " + PDFHelper.getStrWithDots(15, "") + " " + "data " + PDFHelper.getStrWithDots(20, ""));
	    l1.add(nrI);
	    l1.addTabStops(new TabStop(width / 1.2f, TabAlignment.RIGHT));
	    l1.add(new Tab());
	    Text aprobat = new Text("Aprobat");
	    l1.add(aprobat);
	    l1.add(PDFHelper.addTab());
	    Cell l1Cell = new Cell();
	    l1Cell.setBorder(Border.NO_BORDER);
	    l1Cell.add(l1);
	    table1.addCell(l1Cell);
	    doc.add(table1);

	    Table table2 = new Table(1);
	    Paragraph l2 = new Paragraph();
	    Text primSemn1 = new Text("");
	    l2.add(primSemn1);
	    l2.addTabStops(new TabStop(width / 1.235f, TabAlignment.RIGHT));
	    l2.add(new Tab());
	    Text primartop = new Text("Primar");
	    l2.add(primartop);
	    Cell l2Cell = new Cell();
	    l2Cell.add(l2);
	    l2Cell.setBorder(Border.NO_BORDER);
	    table2.addCell(l2Cell);
	    doc.add(table2);

	    Table table3 = new Table(1);
	    Paragraph l3 = new Paragraph();
	    Text topSemn1 = new Text("");
	    l3.add(topSemn1);
	    l3.addTabStops(new TabStop(width / 0.9f, TabAlignment.RIGHT));
	    l3.add(new Tab());
	    Text topSemn2 = new Text(PDFHelper.getStrWithDots(28, ""));
	    l3.add(topSemn2);
	    l3.add(PDFHelper.addTab());
	    Cell l3Cell = new Cell();
	    l3Cell.add(l3);
	    l3Cell.setBorder(Border.NO_BORDER);
	    table3.addCell(l3Cell);
	    doc.add(table3);

	    Paragraph titlu = new Paragraph();
	    Text t1 = new Text("\n\nCerere Concediu Odihna\n\n\n").setBold().setUnderline();
	    titlu.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    titlu.setTextAlignment(TextAlignment.CENTER);
	    titlu.add(t1).addStyle(PDFHelper.bold12nr);
	    doc.add(titlu);

	    Paragraph dec1 = new Paragraph();
	    dec1.add("Subsemnatul/a ");
	    dec1.add(PDFHelper.createAdjustableParagraph(42,
		    new Paragraph(map.get("nume")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec1.add(" salariat al Primariei Dudestii-Vechi, jud. Timis avand functia de ");
	    dec1.add(PDFHelper.createAdjustableParagraph(40,
		    new Paragraph(map.get("functia")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec1.add(" prin prezenta, va rog sa-mi aprobati efectuarea a ");
	    dec1.add(PDFHelper.createAdjustableParagraph(8,
		    new Paragraph(map.get("nrZile")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec1.add(" zile din concediul de odihna aferent anului ");
	    dec1.add(PDFHelper.createAdjustableParagraph(10,
		    new Paragraph(map.get("anConcediu")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec1.add(" din data de ");
	    dec1.add(PDFHelper.createAdjustableParagraph(16,
		    new Paragraph(map.get("ziStart") + "/" + map.get("lunaStart") + "/" + map.get("anStart")).setBold()
			    .setTextAlignment(TextAlignment.CENTER)));
	    dec1.add(" pana in data de ");
	    dec1.add(PDFHelper.createAdjustableParagraph(16,
		    new Paragraph(map.get("ziEnd") + "/" + map.get("lunaEnd") + "/" + map.get("anEnd")).setBold()
			    .setTextAlignment(TextAlignment.CENTER)));
	    dec1.add(" .");
	    doc.add(dec1);

	    Paragraph thx = new Paragraph("\n\n");
	    thx.add(PDFHelper.addTab());
	    Text dec2 = new Text("Va multumesc!\n\n");
	    thx.add(dec2);
	    doc.add(thx);

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
	    Cell cellt1 = new Cell();
	    cellt1.setBorder(Border.NO_BORDER);
	    cellt1.add(p1);
	    semnaturi.addCell(cellt1);
	    doc.add(semnaturi);

	    Table semnaturiR = new Table(1);

	    semnaturiR.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph semnR = new Paragraph();
	    // String dateNow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

	    Text semnPrim = new Text(map.get("ziCerere") + "/" + map.get("lunaCerere") + "/" + map.get("anCerere"));
	    // semnPrim.setUnderline();
	    semnR.add(semnPrim);
	    semnR.addTabStops(new TabStop(width / 1.18f, TabAlignment.RIGHT));
	    semnR.add(new Tab());
	    Text semnIntocmit = new Text(PDFHelper.getStrWithDots(30, ""));
	    // semnIntocmit.setUnderline();
	    semnR.add(semnIntocmit);
	    Cell cellt2 = new Cell();
	    cellt2.setBorder(Border.NO_BORDER);
	    cellt2.add(semnR);
	    semnaturiR.addCell(cellt2);
	    doc.add(semnaturiR);

	    float height = doc.getPageEffectiveArea(PageSize.A4).getHeight();

	    PdfCanvas pdfCanvas = new PdfCanvas(document.getFirstPage());
	    Rectangle remaining = doc.getRenderer().getCurrentArea().getBBox();
	    remaining.setHeight(height / 12);
	    Paragraph p = new Paragraph();
	    p.add(new Tab());
	    p.add("Pe perioada efectuarii concediului de odihna integral sau partial, atributiile de serviciu sunt preluate de ");

	    p.add(PDFHelper
		    .createAdjustableParagraph(40,
			    new Paragraph(map.get("inlocuitor")).setBold().setTextAlignment(TextAlignment.CENTER))
		    .setTextAlignment(TextAlignment.CENTER));
	    p.setTextAlignment(TextAlignment.CENTER);
	    Canvas canvas = new Canvas(pdfCanvas, remaining);
	    canvas.add(p);
	    // canvas.add(p2);
	    canvas.close();

	    doc.close();
	    document.close();
	    writer.flush();

	} catch (IOException ex2) {
	}

    }

    private void setBottomtext(Document doc, PdfDocument document, String text) {
	float height = doc.getPageEffectiveArea(PageSize.A4).getHeight();
	PdfCanvas pdfCanvas = new PdfCanvas(document.getFirstPage());
	Rectangle remaining = doc.getRenderer().getCurrentArea().getBBox();
	remaining.setHeight(height / 12);
	Text title = new Text(text);
	title.setHorizontalAlignment(HorizontalAlignment.CENTER);
	title.setTextAlignment(TextAlignment.CENTER);
	Paragraph p = new Paragraph().add(title);
	p.setHorizontalAlignment(HorizontalAlignment.CENTER);
	p.setTextAlignment(TextAlignment.CENTER);
	p.setVerticalAlignment(VerticalAlignment.BOTTOM);
	Canvas canvas = new Canvas(pdfCanvas, remaining);
	canvas.add(p);
	// new
	// PdfCanvas(document.getFirstPage()).rectangle(remaining).setStrokeColor(ColorConstants.BLACK).stroke();
	canvas.close();
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
