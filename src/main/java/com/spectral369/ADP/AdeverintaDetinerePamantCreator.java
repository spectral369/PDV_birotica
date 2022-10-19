package com.spectral369.ADP;

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
import com.spectral369.birotica.PdfList;
import com.spectral369.utils.FooterEvt;
import com.spectral369.utils.PDFHelper;
import com.spectral369.utils.Utils;

public class AdeverintaDetinerePamantCreator {
    File pdff = null;
    private transient PdfDocument document;
    private transient PdfWriter writer;
    String id = Utils.getRandomStringSerial("ADP");

    public AdeverintaDetinerePamantCreator(Map<String, String> map, String tm) {

	pdff = new File(Utils.getSaveFileLocation("Adeverinta_Detinere_Pamant_" + tm + ".pdf"));
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
	    Image antetLogo2 = PDFHelper.getAntetLogo();
	    document = new PdfDocument(writer);
	    document.getDocumentInfo().addCreationDate();
	    document.getDocumentInfo().setAuthor("spectral369");
	    document.getDocumentInfo().setTitle("Adeverinta_detinere_teren_extravilan" + tm);
	    document.setDefaultPageSize(PageSize.A4);
	    Document doc = new Document(document);
	    doc.setFontSize(11.0f);
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
	    Text t1 = new Text("\nAdeverinta\n").setCharacterSpacing(2f).setBold().setFontSize(14f);
	    titlu.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    titlu.setTextAlignment(TextAlignment.CENTER);
	    titlu.add(t1).addStyle(PDFHelper.bold12nr);
	    doc.add(titlu);

	    final Paragraph declaratie = new Paragraph();
	    declaratie.add(PDFHelper.addTab());
	    Text dec1 = new Text("Se adevereste prin prezenta ca numitul/a " + PDFHelper.getStrWithDash(32, "")
		    + " domiciliat/a in comuna " + PDFHelper.getStrWithDash(21, "") + ", nr.  "
		    + PDFHelper.getStrWithDash(8, "") + ", judetul Timis, avand C.N.P "
		    + PDFHelper.getStrWithDash(18, "") + ""
		    + " conform registrului agricol al localitatii Dudestii-Vechi " + PDFHelper.getStrWithDash(25, "")
		    + " teren agricol.\n");
	    declaratie.add(dec1);

	    declaratie.add(PDFHelper.addTab());
	    Text dec2 = new Text(
		    "Am eliberat prezenta spre a-i servi la susnumitului/ei la completarea dosarului pentru rechizite si a"
			    + " bursei scolare" + ".\n");
	    declaratie.add(dec2);

	    doc.add(declaratie);

	    Table semnaturi = new Table(3);
	    semnaturi.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    semnaturi.setTextAlignment(TextAlignment.CENTER);

	    semnaturi.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph p1 = new Paragraph();
	    Paragraph p2 = new Paragraph();
	    Paragraph p3 = new Paragraph();
	    Text primar = new Text("PRIMAR,");
	    primar.setUnderline();
	    p1.add(primar);

	    Text secretar = new Text("SECRETAR,");
	    secretar.setUnderline();
	    p2.add(secretar);

	    Text intocmit = new Text("INTOCMIT,");
	    intocmit.setUnderline();
	    p3.add(intocmit);

	    Cell cell1 = new Cell();
	    cell1.setWidth(width / 3.4f);
	    cell1.setBorder(Border.NO_BORDER);
	    cell1.add(p1);

	    Cell cell2 = new Cell();
	    cell2.setWidth(width / 3.4f);
	    cell2.setBorder(Border.NO_BORDER);
	    cell2.add(p2);

	    Cell cell3 = new Cell();
	    cell3.setWidth(width / 3.4f);
	    cell3.setBorder(Border.NO_BORDER);
	    cell3.add(p3);

	    semnaturi.addCell(cell1);
	    semnaturi.addCell(cell2);
	    semnaturi.addCell(cell3);
	    doc.add(semnaturi);

	    Table semnaturiR = new Table(3);
	    semnaturiR.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    semnaturiR.setTextAlignment(TextAlignment.CENTER);

	    Paragraph semnR1 = new Paragraph();
	    Paragraph semnR2 = new Paragraph();
	    Paragraph semnR3 = new Paragraph();

	    Text semnPrim = new Text(Utils.getSettingsInfo().get("primar")[0].toUpperCase());
	    semnR1.add(semnPrim);

	    Text semnSecretar = new Text(Utils.getSettingsInfo().get("secretar")[0].toUpperCase());
	    semnR2.add(semnSecretar);

	    Text semnIntocmit = new Text(PDFHelper.getStrWithDash(20, ""));
	    semnR3.add(semnIntocmit);

	    Cell cell21 = new Cell();
	    cell21.setWidth(width / 3.4f);
	    cell21.setBorder(Border.NO_BORDER);
	    cell21.add(semnR1);

	    Cell cell22 = new Cell();
	    cell22.setWidth(width / 3.4f);
	    cell22.setBorder(Border.NO_BORDER);
	    cell22.add(semnR2);

	    Cell cell23 = new Cell();
	    cell23.setWidth(width / 3.4f);
	    cell23.setBorder(Border.NO_BORDER);
	    cell23.add(semnR3);

	    semnaturiR.addCell(cell21);
	    semnaturiR.addCell(cell22);
	    semnaturiR.addCell(cell23);
	    doc.add(semnaturiR);

	    Paragraph hr = new Paragraph(
		    "\n\n________________________________________________________________________________\n");
	    hr.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    hr.setTextAlignment(TextAlignment.CENTER);
	    doc.add(hr);

	    // pt2
	    final Paragraph antet2 = new Paragraph();

	    antetLogo2.scaleToFit(documentWidth-50, documentHeight-80);

	    antet2.add(antetLogo2);
	    antet2.setTextAlignment(TextAlignment.CENTER);
	    antet2.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    doc.add(antet2);

	    final Paragraph nrInreg2 = new Paragraph();
	    nrInreg2.add("\n\n");
	    nrInreg2.add(new Tab());
	    Text nrI2 = new Text(
		    "\tNr. " + PDFHelper.getStrWithDash(12, "") + " " + "/ " + PDFHelper.getStrWithDash(18, ""));
	    nrInreg2.add(nrI2);
	    doc.add(nrInreg2);

	    final Paragraph titlu2 = new Paragraph();
	    Text t12 = new Text("\nAdeverinta\n").setCharacterSpacing(2f).setBold().setFontSize(14f);
	    titlu2.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    titlu2.setTextAlignment(TextAlignment.CENTER);
	    titlu2.add(t12).addStyle(PDFHelper.bold12nr);
	    doc.add(titlu2);

	    final Paragraph declaratie2 = new Paragraph();
	    declaratie2.add(PDFHelper.addTab());
	    Text dec12 = new Text("Se adevereste prin prezenta ca numitul/a " + PDFHelper.getStrWithDash(32, "")
		    + " domiciliat/a in comuna " + PDFHelper.getStrWithDash(21, "") + ", nr.  "
		    + PDFHelper.getStrWithDash(8, "") + ", judetul Timis, avand C.N.P "
		    + PDFHelper.getStrWithDash(18, "") + ""
		    + " conform registrului agricol al localitatii Dudestii-Vechi " + PDFHelper.getStrWithDash(25, "")
		    + " teren agricol.\n");
	    declaratie2.add(dec12);

	    declaratie2.add(PDFHelper.addTab());
	    Text dec22 = new Text(
		    "Am eliberat prezenta spre a-i servi la susnumitului/ei la completarea dosarului pentru rechizite si a"
			    + " bursei scolare" + ".\n");
	    declaratie2.add(dec22);

	    doc.add(declaratie2);

	    Table semnaturi2 = new Table(3);
	    semnaturi2.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    semnaturi2.setTextAlignment(TextAlignment.CENTER);

	    semnaturi2.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph p12 = new Paragraph();
	    Paragraph p22 = new Paragraph();
	    Paragraph p32 = new Paragraph();
	    Text primar2 = new Text("PRIMAR,");
	    primar2.setUnderline();
	    p12.add(primar2);

	    Text secretar2 = new Text("SECRETAR,");
	    secretar2.setUnderline();
	    p22.add(secretar2);

	    Text intocmit2 = new Text("INTOCMIT,");
	    intocmit2.setUnderline();
	    p32.add(intocmit2);

	    Cell cell12 = new Cell();
	    cell12.setWidth(width / 3.4f);
	    cell12.setBorder(Border.NO_BORDER);
	    cell12.add(p12);

	    Cell cell222 = new Cell();
	    cell222.setWidth(width / 3.4f);
	    cell222.setBorder(Border.NO_BORDER);
	    cell222.add(p22);

	    Cell cell32 = new Cell();
	    cell32.setWidth(width / 3.4f);
	    cell32.setBorder(Border.NO_BORDER);
	    cell32.add(p32);

	    semnaturi2.addCell(cell12);
	    semnaturi2.addCell(cell22);
	    semnaturi2.addCell(cell32);
	    doc.add(semnaturi);

	    Table semnaturiR2 = new Table(3);
	    semnaturiR2.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    semnaturiR2.setTextAlignment(TextAlignment.CENTER);

	    Paragraph semnR12 = new Paragraph();
	    Paragraph semnR22 = new Paragraph();
	    Paragraph semnR32 = new Paragraph();

	    Text semnPrim2 = new Text(Utils.getSettingsInfo().get("primar")[0].toUpperCase());
	    semnR12.add(semnPrim2);

	    Text semnSecretar2 = new Text(Utils.getSettingsInfo().get("secretar")[0].toUpperCase());
	    semnR22.add(semnSecretar2);

	    Text semnIntocmit2 = new Text(PDFHelper.getStrWithDash(20, ""));
	    semnR32.add(semnIntocmit2);

	    Cell cell212 = new Cell();
	    cell212.setWidth(width / 3.4f);
	    cell212.setBorder(Border.NO_BORDER);
	    cell212.add(semnR12);

	    Cell cell2222 = new Cell();
	    cell2222.setWidth(width / 3.4f);
	    cell2222.setBorder(Border.NO_BORDER);
	    cell2222.add(semnR22);

	    Cell cell232 = new Cell();
	    cell232.setWidth(width / 3.4f);
	    cell232.setBorder(Border.NO_BORDER);
	    cell232.add(semnR32);

	    semnaturiR2.addCell(cell212);
	    semnaturiR2.addCell(cell2222);
	    semnaturiR2.addCell(cell232);
	    doc.add(semnaturiR2);

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
	    Image antetLogo2 = PDFHelper.getAntetLogo();
	    document = new PdfDocument(writer);
	    document.getDocumentInfo().addCreationDate();
	    document.getDocumentInfo().setAuthor("spectral369");
	    document.getDocumentInfo().setTitle("Adeverinta_detinere_teren_extravilan" + tm);
	    document.setDefaultPageSize(PageSize.A4);
	    Document doc = new Document(document);
	    doc.setFontSize(11.0f);

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
	    antet.setTextAlignment(TextAlignment.CENTER);
	    antet.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    doc.add(antet);

	    final Paragraph nrInreg = new Paragraph();
	    nrInreg.add("\n\n");
	    nrInreg.add(new Tab());
	    Text nrI = new Text(
		    "\tNr. " + PDFHelper.getStrWithDash(12, "") + " " + "/ " + PDFHelper.getStrWithDash(18, ""));
	    nrInreg.add(nrI);
	    doc.add(nrInreg);

	    final Paragraph titlu = new Paragraph();
	    Text t1 = new Text("\nAdeverinta\n").setCharacterSpacing(2f).setBold().setFontSize(14f);
	    titlu.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    titlu.setTextAlignment(TextAlignment.CENTER);
	    titlu.add(t1).addStyle(PDFHelper.bold12nr);
	    doc.add(titlu);

	    final Paragraph declaratie = new Paragraph();
	    declaratie.setFixedLeading(12.9f);
	    declaratie.add(PDFHelper.addTab());

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

	    declaratie.add("Se adevereste prin prezenta ca ");
	    declaratie.add(num + " ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(64,
		    new Paragraph(map.get("nume")).setFontSize(13f).setUnderline().setBold()
		    .setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" " + dom);
	    declaratie.add(" in comuna ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(23,
		    new Paragraph(map.get("localitate")).setUnderline().setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(", nr. ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(12,
		    new Paragraph(map.get("nrCasaAddr")).setUnderline().setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(", judetul Timis, avand ");
	    declaratie.add(new Text("C.N.P ").setBold().setFontSize(10f));
	    declaratie.add(PDFHelper.createAdjustableParagraph(35,
		    new Paragraph(map.get("cnp")).setUnderline().setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add(" conform registrului agricol al localitatii Dudestii-Vechi ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(25,
		    new Paragraph(map.get("detine")).setUnderline().setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie.add("teren agricol.\n");
	    declaratie.add(PDFHelper.addTab());
	    Text dec2 = new Text("Am eliberat prezenta spre a-i servi la " + susnum
		    + " la completarea dosarului pentru rechizite si a" + " bursei scolare" + ".\n");
	    declaratie.add(dec2);

	    doc.add(declaratie);

	    Table semnaturi = new Table(3);
	    semnaturi.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    semnaturi.setTextAlignment(TextAlignment.CENTER);

	    semnaturi.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph p1 = new Paragraph();
	    Paragraph p2 = new Paragraph();
	    Paragraph p3 = new Paragraph();
	    Text primar = new Text("PRIMAR,");
	    primar.setUnderline();
	    p1.add(primar);

	    Text secretar = new Text("SECRETAR,");
	    secretar.setUnderline();
	    p2.add(secretar);

	    Text intocmit = new Text("INTOCMIT,");
	    intocmit.setUnderline();
	    p3.add(intocmit);

	    Cell cell1 = new Cell();
	    cell1.setWidth(width / 3.4f);
	    cell1.setBorder(Border.NO_BORDER);
	    cell1.add(p1);

	    Cell cell2 = new Cell();
	    cell2.setWidth(width / 3.4f);
	    cell2.setBorder(Border.NO_BORDER);
	    cell2.add(p2);

	    Cell cell3 = new Cell();
	    cell3.setWidth(width / 3.4f);
	    cell3.setBorder(Border.NO_BORDER);
	    cell3.add(p3);

	    semnaturi.addCell(cell1);
	    semnaturi.addCell(cell2);
	    semnaturi.addCell(cell3);
	    doc.add(semnaturi);

	    Table semnaturiR = new Table(3);
	    semnaturiR.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    semnaturiR.setTextAlignment(TextAlignment.CENTER);

	    Paragraph semnR1 = new Paragraph();
	    Paragraph semnR2 = new Paragraph();
	    Paragraph semnR3 = new Paragraph();

	    Text semnPrim = new Text(Utils.getSettingsInfo().get("primar")[0].toUpperCase());
	    semnR1.add(semnPrim);

	    Text semnSecretar = new Text(Utils.getSettingsInfo().get("secretar")[0].toUpperCase());
	    semnR2.add(semnSecretar);

	    Text semnIntocmit = new Text(map.get("intocmit").toUpperCase());
	    semnR3.add(semnIntocmit);

	    Cell cell21 = new Cell();
	    cell21.setWidth(width / 3.4f);
	    cell21.setBorder(Border.NO_BORDER);
	    cell21.add(semnR1);

	    Cell cell22 = new Cell();
	    cell22.setWidth(width / 3.4f);
	    cell22.setBorder(Border.NO_BORDER);
	    cell22.add(semnR2);

	    Cell cell23 = new Cell();
	    cell23.setWidth(width / 3.4f);
	    cell23.setBorder(Border.NO_BORDER);
	    cell23.add(semnR3);

	    semnaturiR.addCell(cell21);
	    semnaturiR.addCell(cell22);
	    semnaturiR.addCell(cell23);
	    doc.add(semnaturiR);

	    Paragraph hr = new Paragraph(
		    "\n\n________________________________________________________________________________\n");
	    hr.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    hr.setTextAlignment(TextAlignment.CENTER);
	    doc.add(hr);

	    // pt2
	    final Paragraph antet2 = new Paragraph();

	    antetLogo2.scaleToFit(documentWidth-50, documentHeight-80);

	    antet2.add(antetLogo2);
	    antet2.setTextAlignment(TextAlignment.CENTER);
	    antet2.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    doc.add(antet2);

	    final Paragraph nrInreg2 = new Paragraph();
	    nrInreg2.add("\n\n");
	    nrInreg2.add(new Tab());
	    Text nrI2 = new Text(
		    "\tNr. " + PDFHelper.getStrWithDash(12, "") + " " + "/ " + PDFHelper.getStrWithDash(18, ""));
	    nrInreg2.add(nrI2);
	    doc.add(nrInreg2);

	    final Paragraph titlu2 = new Paragraph();
	    Text t12 = new Text("\nAdeverinta\n").setCharacterSpacing(2f).setBold().setFontSize(14f);;
	    titlu2.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    titlu2.setTextAlignment(TextAlignment.CENTER);
	    titlu2.add(t12).addStyle(PDFHelper.bold12nr);
	    doc.add(titlu2);

	    final Paragraph declaratie2 = new Paragraph();
	    declaratie2.add(PDFHelper.addTab());
	    declaratie2.setFixedLeading(12.9f);
	    declaratie2.add("Se adevereste prin prezenta ca ");
	    declaratie2.add(num + " ");
	    declaratie2.add(PDFHelper.createAdjustableParagraph(60,
		    new Paragraph(map.get("nume")).setFontSize(13f).setUnderline().setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie2.add(" " + dom);
	    declaratie2.add(" in comuna ");
	    declaratie2.add(PDFHelper.createAdjustableParagraph(23,
		    new Paragraph(map.get("localitate")).setUnderline().setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie2.add(", nr. ");
	    declaratie2.add(PDFHelper.createAdjustableParagraph(8,
		    new Paragraph(map.get("nrCasaAddr")).setUnderline().setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie2.add(", judetul Timis, avand ");
	    declaratie2.add(new Text("C.N.P ").setBold().setFontSize(10f));
	    declaratie2.add(PDFHelper.createAdjustableParagraph(35,
		    new Paragraph(map.get("cnp")).setUnderline().setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie2.add(" conform registrului agricol al localitatii Dudestii-Vechi ");
	    declaratie2.add(PDFHelper.createAdjustableParagraph(25,
		    new Paragraph(map.get("detine")).setUnderline().setBold().setTextAlignment(TextAlignment.CENTER)));
	    declaratie2.add("teren agricol.\n");

	    declaratie2.add(PDFHelper.addTab());
	    Text dec22 = new Text(
		    "Am eliberat prezenta spre a-i servi la susnumitului/ei la completarea dosarului pentru rechizite si a"
			    + " bursei scolare" + ".\n");
	    declaratie2.add(dec22);

	    doc.add(declaratie2);

	    Table semnaturi2 = new Table(3);
	    semnaturi2.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    semnaturi2.setTextAlignment(TextAlignment.CENTER);

	    semnaturi2.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph p12 = new Paragraph();
	    Paragraph p22 = new Paragraph();
	    Paragraph p32 = new Paragraph();
	    Text primar2 = new Text("PRIMAR,");
	    primar2.setUnderline();
	    p12.add(primar2);

	    Text secretar2 = new Text("SECRETAR,");
	    secretar2.setUnderline();
	    p22.add(secretar2);

	    Text intocmit2 = new Text("INTOCMIT,");
	    intocmit2.setUnderline();
	    p32.add(intocmit2);

	    Cell cell12 = new Cell();
	    cell12.setWidth(width / 3.4f);
	    cell12.setBorder(Border.NO_BORDER);
	    cell12.add(p12);

	    Cell cell222 = new Cell();
	    cell222.setWidth(width / 3.4f);
	    cell222.setBorder(Border.NO_BORDER);
	    cell222.add(p22);

	    Cell cell32 = new Cell();
	    cell32.setWidth(width / 3.4f);
	    cell32.setBorder(Border.NO_BORDER);
	    cell32.add(p32);

	    semnaturi2.addCell(cell12);
	    semnaturi2.addCell(cell22);
	    semnaturi2.addCell(cell32);
	    doc.add(semnaturi);

	    Table semnaturiR2 = new Table(3);
	    semnaturiR2.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    semnaturiR2.setTextAlignment(TextAlignment.CENTER);

	    Paragraph semnR12 = new Paragraph();
	    Paragraph semnR22 = new Paragraph();
	    Paragraph semnR32 = new Paragraph();

	    Text semnPrim2 = new Text(Utils.getSettingsInfo().get("primar")[0].toUpperCase());
	    semnR12.add(semnPrim2);

	    Text semnSecretar2 = new Text(Utils.getSettingsInfo().get("secretar")[0].toUpperCase());
	    semnR22.add(semnSecretar2);

	    Text semnIntocmit2 = new Text(map.get("intocmit").toUpperCase());
	    semnR32.add(semnIntocmit2);

	    Cell cell212 = new Cell();
	    cell212.setWidth(width / 3.4f);
	    cell212.setBorder(Border.NO_BORDER);
	    cell212.add(semnR12);

	    Cell cell2222 = new Cell();
	    cell2222.setWidth(width / 3.4f);
	    cell2222.setBorder(Border.NO_BORDER);
	    cell2222.add(semnR22);

	    Cell cell232 = new Cell();
	    cell232.setWidth(width / 3.4f);
	    cell232.setBorder(Border.NO_BORDER);
	    cell232.add(semnR32);

	    semnaturiR2.addCell(cell212);
	    semnaturiR2.addCell(cell2222);
	    semnaturiR2.addCell(cell232);
	    doc.add(semnaturiR2);

	    doc.close();
	    document.close();
	    writer.flush();

	} catch (IOException ex2) {
	}

    }

}
