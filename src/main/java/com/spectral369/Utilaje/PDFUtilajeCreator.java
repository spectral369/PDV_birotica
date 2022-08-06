package com.spectral369.Utilaje;

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
import com.itextpdf.layout.properties.VerticalAlignment;
import com.spectral369.birotica.PdfList;
import com.spectral369.utils.FooterEvt;
import com.spectral369.utils.PDFHelper;
import com.spectral369.utils.Utils;

public class PDFUtilajeCreator {
    File pdff = null;
    private transient PdfDocument document;
    private transient PdfWriter writer;
    String id = Utils.getRandomStringSerial("IU");

    public PDFUtilajeCreator(Map<String, String> map, String tm) {

	pdff = new File(Utils.getSaveFileLocation("Inchiriere_Utilaje_" + tm + ".pdf"));
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
	    Text t1 = new Text("\n\nCERERE PRIVIND\nINCHIRIEREA UTILAJELOR\n\n").setBold();
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
		    + PDFHelper.getStrWithDots(20, "") + " .\n");
	    dec11.setTextAlignment(TextAlignment.CENTER);
	    declaratie.add(dec11);
	    declaratie.add(PDFHelper.addTab());
	    Text dec2 = new Text("Doresc sa inchiriez utilajele primariei Dudestii-Vechi in data de "
		    + PDFHelper.getStrWithDots(25, "") + " la adresa "+PDFHelper.getStrWithDots(32, "")+" numarul "
		    + PDFHelper.getStrWithDots(10, "") + " .\n\n");
	    dec2.setTextAlignment(TextAlignment.CENTER);
	    declaratie.add(dec2);
	    declaratie.setTextAlignment(TextAlignment.CENTER);

	    doc.add(declaratie);
	    // work
	    Table table = new Table(4);
	    table.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.setVerticalAlignment(VerticalAlignment.MIDDLE);
	    table.setTextAlignment(TextAlignment.CENTER);
	    table.setWidth(width/1.3f);
	    Cell cell = new Cell();
	    cell.add(new Paragraph("Utilaj"));
	    //cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);

	    cell = new Cell();
	    cell.add(new Paragraph("Pret / Ora"));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);

	    cell = new Cell();
	    cell.add(new Paragraph("Nr. Ore lucrate"));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);

	    cell = new Cell();
	    cell.add(new Paragraph("Total"));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    // row1
	    cell = new Cell();
	    cell.add(new Paragraph("Tractor + Remorca"));
	    // cell.setRowspan(2);
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    cell = new Cell();
	    cell.add(new Paragraph("120 Ron"));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    cell = new Cell();
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    cell.add(new Paragraph(""));
	    table.addCell(cell);
	    cell = new Cell();
	    cell.add(new Paragraph(""));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    // row2
	    cell = new Cell();
	    cell.add(new Paragraph("Ifron"));
	    // cell.setRowspan(3);
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    cell = new Cell();
	    cell.add(new Paragraph("120 Ron"));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    cell = new Cell();
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    cell.add(new Paragraph(""));
	    table.addCell(cell);
	    cell = new Cell();
	    cell.add(new Paragraph(""));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    // Row3
	    cell = new Cell();
	    cell.add(new Paragraph("Tractor cu utilaj"));
	    // cell.setRowspan(4);
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    cell = new Cell();
	    cell.add(new Paragraph("120 Ron"));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    cell = new Cell();
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    cell.add(new Paragraph(""));
	    table.addCell(cell);
	    cell = new Cell();
	    cell.add(new Paragraph(""));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    // row4
	    cell = new Cell();
	    cell.add(new Paragraph("Minicastor"));
	    // cell.setRowspan(5);
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    cell = new Cell();
	    cell.add(new Paragraph("120 Ron"));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    cell = new Cell();
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    cell.add(new Paragraph(""));
	    table.addCell(cell);
	    cell = new Cell();
	    cell.add(new Paragraph(""));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    // row 5
	    cell = new Cell(1,2);
	    cell.add(new Paragraph("Total"));
	    // cell.setRowspan(2);
	   // cell.setColspan(2);
	   
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    cell = new Cell();
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    cell.add(new Paragraph(""));
	    table.addCell(cell);
	    cell = new Cell();
	    cell.add(new Paragraph(""));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);

	    doc.add(table);

	    // work

	    final Paragraph declaratie2 = new Paragraph();
	 
	    declaratie2.add(PDFHelper.addTab());
	    Text dec4 = new Text(
		    "\n * Tarife stabilite in conformitate cu prevederile H.C.L Dudestii-Vechi nr.32 din 19.07.2021.\n\n");
	    dec4.setTextAlignment(TextAlignment.CENTER);
	    declaratie2.add(dec4);
	    declaratie2.add(PDFHelper.addTab());
	    Text dec5 = new Text("Prezenta cerere este valabila doar insotita de dovada achitarii"
		    + " taxei, conform estimarilor facute de catre solicitant.\n");
	    dec5.setTextAlignment(TextAlignment.CENTER);
	    declaratie2.add(dec5);
	    declaratie2.add(PDFHelper.addTab());
	    Text dec6 = new Text(
		    "Serviciul va fi prestat in limita taxei achitate urmand ca prestarea unui serviciu suplimentar"
			    + " sa implice achitarea diferentei de taxa, raportat la numarul de ore de lucru.\n");
	    dec6.setTextAlignment(TextAlignment.CENTER);
	    declaratie2.add(dec6);
	    declaratie2.add(PDFHelper.addTab());
	    Text dec7 = new Text("Inchirierea utilajelor se face pentru minim o ora de lucru.\n\n");
	    dec7.setTextAlignment(TextAlignment.CENTER);
	    declaratie2.add(dec7);
	    declaratie2.setTextAlignment(TextAlignment.CENTER);
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
	    Text t1 = new Text("\nCERERE PRIVIND\nINCHIRIEREA UTILAJELOR\n\n").setBold();
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
	    dec.add(" .\n");
	    dec.add(new Tab());
	    dec.add("Doresc sa inchiriez utilajele Primariei Dudestii-Vechi in data de ");
	    dec.add(PDFHelper.createAdjustableParagraph(15,
		    new Paragraph(map.get("ziuaLuc") + "-" + map.get("lunaLuc") + "-" + map.get("anulLuc")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(" la adresa ");
	    dec.add(PDFHelper.createAdjustableParagraph(30,
		    new Paragraph(map.get("localitate")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(" la numarul ");
	    dec.add(PDFHelper.createAdjustableParagraph(8,
		    new Paragraph(map.get("nrCasaAddrLuc")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(" .\n");
	    doc.setTextAlignment(TextAlignment.CENTER);
	    doc.add(dec);
	    
	    

	    // work
	    Table table = new Table(4);
	    table.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.setVerticalAlignment(VerticalAlignment.MIDDLE);
	    table.setTextAlignment(TextAlignment.CENTER);
	    table.setWidth(width/1.3f);
	    Cell cell = new Cell();
	    cell.add(new Paragraph("Utilaj"));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);

	    cell = new Cell();
	    cell.add(new Paragraph("Pret / Ora"));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);

	    cell = new Cell();
	    cell.add(new Paragraph("Nr. Ore lucrate"));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);

	    cell = new Cell();
	    cell.add(new Paragraph("Total"));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    // row1
	    cell = new Cell();
	    cell.add(new Paragraph("Tractor + Remorca"));
	    // cell.setRowspan(2);
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    cell = new Cell();
	    cell.add(new Paragraph("120 Ron"));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    cell = new Cell();
	    cell.add(new Paragraph(map.get("tractorRemorca")));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    int tr = (Integer.parseInt(map.get("tractorRemorca")) * 120);
	    cell = new Cell();
	    cell.add(new Paragraph(String.valueOf(tr)));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    // row2
	    cell = new Cell();
	    cell.add(new Paragraph("Ifron"));
	    // cell.setRowspan(3);
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    cell = new Cell();
	    cell.add(new Paragraph("120 Ron"));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    cell = new Cell();
	    cell.add(new Paragraph(map.get("ifron")));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    int ifron = (Integer.parseInt(map.get("ifron")) * 120);
	    cell = new Cell();
	    cell.add(new Paragraph(String.valueOf(ifron)));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    // Row3
	    cell = new Cell();
	    cell.add(new Paragraph("Tractor cu utilaj"));
	    // cell.setRowspan(4);
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    cell = new Cell();
	    cell.add(new Paragraph("120 Ron"));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    cell = new Cell();
	    cell.add(new Paragraph(map.get("tractorUtilaj")));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    int tu = (Integer.parseInt(map.get("tractorUtilaj")) * 120);
	    cell = new Cell();
	    cell.add(new Paragraph(String.valueOf(tu)));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    // row4
	    cell = new Cell();
	    cell.add(new Paragraph("Minicastor"));
	    // cell.setRowspan(5);
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    cell = new Cell();
	    cell.add(new Paragraph("120 Ron"));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    cell = new Cell();
	    cell.add(new Paragraph(map.get("minicastor")));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    int minicastor = (Integer.parseInt(map.get("minicastor")) * 120);
	    cell = new Cell();
	    cell.add(new Paragraph(String.valueOf(minicastor)));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    // row 5
	    cell = new Cell(1,2);
	    // cell.setRowspan(2);
	   cell.add(new Paragraph("Total"));
	   cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    cell = new Cell();
	    cell.add(new Paragraph(map.get("totalOre")));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);
	    cell = new Cell();
	    cell.add(new Paragraph(map.get("sumaTotal")));
	    cell.setTextAlignment(TextAlignment.CENTER);
	    cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    table.addCell(cell);

	    doc.add(table);

	    // work

	    final Paragraph declaratie2 = new Paragraph();
	    declaratie2.add(PDFHelper.addTab());
	    Text dec4 = new Text(
		    "\n * Tarife stabilite in conformitate cu prevederile H.C.L Dudestii-Vechi nr.32 din 19.07.2021.\n\n");
	    dec4.setTextAlignment(TextAlignment.CENTER);
	    declaratie2.add(dec4);
	
	    declaratie2.add(PDFHelper.addTab());
	    Text dec5 = new Text("Prezenta cerere este valabila doar insotita de dovada achitarii"
		    + " taxei, conform estimarilor facute de catre solicitant.\n");
	    dec5.setTextAlignment(TextAlignment.CENTER);
	    declaratie2.add(dec5);
	    declaratie2.add(PDFHelper.addTab());
	    Text dec6 = new Text(
		    "Serviciul va fi prestat in limita taxei achitate urmand ca prestarea unui serviciu suplimentar"
			    + " sa implice achitarea diferentei de taxa, raportat la numarul de ore de lucru.\n");
	    dec6.setTextAlignment(TextAlignment.CENTER);
	    declaratie2.add(dec6);
	    declaratie2.add(PDFHelper.addTab());
	    Text dec7 = new Text("Inchirierea utilajelor se face pentru minim o ora de lucru.\n\n");
	    dec7.setTextAlignment(TextAlignment.CENTER);
	    declaratie2.add(dec7);
	    declaratie2.setTextAlignment(TextAlignment.CENTER);
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
