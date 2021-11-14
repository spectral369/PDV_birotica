package com.spectral369.ARD;

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
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TabAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.spectral369.birotica.PdfList;
import com.spectral369.utils.PDFHelper;
import com.spectral369.utils.Utils;

public class PDFCR {
    File pdff = null;
    private transient PdfDocument document;
    private transient PdfWriter writer;
    String id = Utils.getRandomStringSerial("ARD");

    public PDFCR(Map<String, String> map, String tm) {

	pdff = new File(Utils.getSaveFileLocation("Adeverinta_radiere_auto_" + tm + ".pdf"));
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

    private void generatePDF(final String tm, final File pdfFile) {
	try {
	    writer = new PdfWriter(pdfFile);
	    Image antetLogo = PDFHelper.getAntetLogo();
	    document = new PdfDocument(writer);
	    document.getDocumentInfo().addCreationDate();
	    document.getDocumentInfo().setAuthor("spectral369");
	    document.getDocumentInfo().setTitle("Adeverinta_de_radiere_auto_" + tm);

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
	    Text t1 = new Text("\n\n\n\nAdeverinta\n\n\n").setBold().setUnderline();
	    titlu.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    titlu.setTextAlignment(TextAlignment.CENTER);
	    titlu.add(t1).addStyle(PDFHelper.bold12nr);
	    doc.add(titlu);

	    Paragraph declaratie = new Paragraph();
	    declaratie.add(PDFHelper.addTab());
	    Text dec1 = new Text("Se adevereste de catre noi ca prin prezenta ca Dl/D-na/Subscrisa "
		    + PDFHelper.getStrWithDots(46, "") + " " + "cu domiciliul/punct de lucru in "
		    + PDFHelper.getStrWithDots(55, "") + " numarul " + PDFHelper.getStrWithDots(22, "") + ""
		    + " detine spatiu de depozitare pentru autoturismul marca " + PDFHelper.getStrWithDots(38, "") + ""
		    + "model " + PDFHelper.getStrWithDots(38, "") + " capacitate cilindrica "
		    + PDFHelper.getStrWithDots(22, "") + " serie " + "motor " + PDFHelper.getStrWithDots(30, "")
		    + " serie sasiu " + PDFHelper.getStrWithDots(55, "") + " numarul de inmatriculare " + ""
		    + PDFHelper.getStrWithDots(30, "") + " la adresa " + PDFHelper.getStrWithDots(80, "") + " .\n\n");
	    declaratie.add(dec1);
	    doc.add(declaratie);

	    Paragraph dec02 = new Paragraph();
	    dec02.add(new Text("\n\n"));
	    dec02.add(new Tab());
	    Text gr01 = new Text("Prezenta s-a eliberat spre a servi la radiere.");
	    dec02.add(gr01);
	    dec02.add(new Text("\n\n\n"));
	    doc.add(dec02);

	    Table semnaturi = new Table(1);

	    semnaturi.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph p1 = new Paragraph();
	    Text primar = new Text("Primar");
	    primar.setUnderline();
	    p1.add(primar);
	    p1.addTabStops(new TabStop(width / 1.18f, TabAlignment.RIGHT));
	    p1.add(new Tab());
	    Text intocmit = new Text("Intocmit");
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
	    document.getDocumentInfo().setTitle("Adeverinta_de_radiere_auto_" + tm);

	    document.setDefaultPageSize(PageSize.A4);
	    Document doc = new Document(document);
	    if (map.get("titlu").contains("scris"))
		doc.setFontSize(11);
	    else
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

	    final Paragraph nrInreg = new Paragraph();
	    nrInreg.setHorizontalAlignment(HorizontalAlignment.LEFT);
	    Text nrI = new Text(
		    "\n\nNr. " + PDFHelper.getStrWithDots(15, "") + " " + "data " + PDFHelper.getStrWithDots(20, ""));
	    nrInreg.add(nrI);
	    doc.add(nrInreg);
	    Paragraph titlu = new Paragraph();
	    Text tit = new Text("\n\n\nAdeverinta\n\n\n");
	    tit.setTextAlignment(TextAlignment.CENTER);
	    titlu.setTextAlignment(TextAlignment.CENTER);
	    tit.setUnderline();
	    titlu.add(tit).setBold();
	    doc.add(titlu);

	    Paragraph dec1 = new Paragraph();
	    dec1.add(PDFHelper.addTab());
	    String PF = null;
	    if (map.get("titlu").contains("scris"))
		PF = new String("cu punct de lucru");// \n
	    else
		PF = new String("cu domiciliul\n");

	    dec1.add("Se adevereste de catre noi prin prezenta ca ");

	    dec1.add(map.get("titlu") + " ");
	    if (map.get("titlu").contains("scris"))
	    dec1.add(PDFHelper.createAdjustableParagraph(58,
		    new Paragraph(map.get("nume")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    else
		 dec1.add(PDFHelper.createAdjustableParagraph(46,
			    new Paragraph(map.get("nume")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec1.add(" " + PF + " in");
	    dec1.add(PDFHelper.createAdjustableParagraph(40,
		    new Paragraph(map.get("localitate")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec1.add(" numarul ");
	    dec1.add(PDFHelper.createAdjustableParagraph(22,
		    new Paragraph(map.get("nrStrada")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec1.add(" detine spatiu de depozitare pentru autoturismul marca ");
	    if (map.get("titlu").contains("scris"))
	    dec1.add(PDFHelper.createAdjustableParagraph(31,
		    new Paragraph(map.get("marca")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    else
		dec1.add(PDFHelper.createAdjustableParagraph(24,
			    new Paragraph(map.get("marca")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec1.add(" model ");
	    dec1.add(PDFHelper.createAdjustableParagraph(17,
		    new Paragraph(map.get("model")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec1.add(" capacitate cilindrica ");
	    dec1.add(PDFHelper.createAdjustableParagraph(12,
		    new Paragraph(map.get("capacitate")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec1.add(" serie motor ");
	    if (map.get("titlu").contains("scris"))
	    dec1.add(PDFHelper.createAdjustableParagraph(29, new Paragraph(PDFHelper.solveIfEmpty(map.get("serieMotor")))
			    .setBold().setTextAlignment(TextAlignment.CENTER)));
	    else
		dec1.add(PDFHelper.createAdjustableParagraph(25, new Paragraph(PDFHelper.solveIfEmpty(map.get("serieMotor")))
			    .setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec1.add(" serie sasiu ");
	    dec1.add(PDFHelper.createAdjustableParagraph(60,
		    new Paragraph(map.get("serieSasiu")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec1.add(" numarul de inmatriculare ");
	    dec1.add(PDFHelper.createAdjustableParagraph(30,
		    new Paragraph(PDFHelper.solveIfEmpty(map.get("nrInmatriculare"))).setBold()
			    .setTextAlignment(TextAlignment.CENTER)));
	    if (map.get("titlu").contains("scris"))
	    dec1.add(" la \nadresa ");
	    else
		dec1.add(" la adresa ");
	    dec1.add(PDFHelper.createAdjustableParagraph(55,
		    new Paragraph(map.get("addrDepozitare")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec1.add(" .\n\n");

	    doc.add(dec1);

	    Paragraph dec02 = new Paragraph();
	    dec02.add(new Text("\n\n"));
	    dec02.add(new Tab());
	    Text gr01 = new Text("Prezenta s-a eliberat spre a servi la radiere.");
	    dec02.add(gr01);
	    dec02.add(new Text("\n\n\n"));
	    doc.add(dec02);

	    Table semnaturi = new Table(1);

	    semnaturi.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph p1 = new Paragraph();
	    Text primar = new Text("Primar");
	    primar.setUnderline();
	    p1.add(primar);
	    p1.addTabStops(new TabStop(width / 1.18f, TabAlignment.RIGHT));
	    p1.add(new Tab());
	    Text intocmit = new Text("Intocmit");
	    intocmit.setUnderline();
	    p1.add(intocmit);
	    Cell cellSemnHeader = new Cell();
	    cellSemnHeader.setBorder(Border.NO_BORDER);
	    cellSemnHeader.add(p1);
	    semnaturi.addCell(cellSemnHeader);
	    doc.add(semnaturi);

	    Table semnaturiR = new Table(1);

	    semnaturiR.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph semnR = new Paragraph();

	    Text semnPrim = new Text(PDFHelper.getStrWithDots(30, ""));
	    // semnPrim.setUnderline();
	    semnR.add(semnPrim);
	    semnR.addTabStops(new TabStop(width / 1.18f, TabAlignment.RIGHT));
	    semnR.add(new Tab());
	    Text semnIntocmit = new Text(map.get("intocmit"));
	    // semnIntocmit.setUnderline();
	    semnR.add(semnIntocmit);
	    Cell cellSemn = new Cell();
	    cellSemn.setBorder(Border.NO_BORDER);
	    cellSemn.add(semnR);
	    semnaturiR.addCell(cellSemn);
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
