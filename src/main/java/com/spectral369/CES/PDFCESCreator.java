package com.spectral369.CES;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
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
import com.spectral369.utils.FooterEvt;
import com.spectral369.utils.PDFHelper;
import com.spectral369.utils.Utils;

public class PDFCESCreator {
    File pdff = null;
    private transient PdfDocument document;
    private transient PdfWriter writer;
    String id = Utils.getRandomStringSerial("CES");

    public PDFCESCreator(Map<String, String> map, String tm) {

	pdff = new File(Utils.getSaveFileLocation("CES_" + tm + ".pdf"));
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
	    document.getDocumentInfo().setTitle("Declaratie_de_Corespondenta_" + tm);
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

	    Paragraph title = new Paragraph();
	    Text t1 = new Text("\n\n\nDECLARATIE PE PROPRIA RASPUNDERE\n\n\n").setBold();
	    title.add(t1);
	    title.setTextAlignment(TextAlignment.CENTER);
	    doc.add(title);

	    Paragraph declaratie = new Paragraph();
	    declaratie.add(PDFHelper.addTab());
	    Text subsemnat = new Text("Subsemnatul/Subsemnata, " + PDFHelper.getStrWithDots(70, "")
		    + " Cod Numeric Personal: " + PDFHelper.getStrWithDots(45, "") + " identificat/identificata cu: "
		    + PDFHelper.getStrWithDots(45, "") + " seria: " + PDFHelper.getStrWithDots(15, "") + " numarul: "
		    + PDFHelper.getStrWithDots(20, "") + " cetatenia: " + PDFHelper.getStrWithDots(35, "")
		    + " cu domiciliul/resedinta in(adresa completa de corespondenta) " + "localitatea: "
		    + PDFHelper.getStrWithDots(65, "") + " strada: " + PDFHelper.getStrWithDots(60, "") + " numarul: "
		    + PDFHelper.getStrWithDots(15, "") + " bloc: " + PDFHelper.getStrWithDots(15, "") + " scara: "
		    + PDFHelper.getStrWithDots(15, "") + " etaj: " + PDFHelper.getStrWithDots(12, "") + " apartament: "
		    + PDFHelper.getStrWithDots(12, "") + " sector: " + PDFHelper.getStrWithDots(12, "") + " judet: "
		    + PDFHelper.getStrWithDots(25, "") + " cod postal: " + PDFHelper.getStrWithDots(20, "") + " .");
	    declaratie.add(subsemnat);
	    declaratie.add(PDFHelper.addTab());
	    Text contact2 = new Text("\nPot fi contactat la:\n");
	    Text fix = new Text("- telefon fix: " + PDFHelper.getStrWithDots(60, "") + "\n");
	    Text mobil = new Text("- telefon mobil: " + PDFHelper.getStrWithDots(60, "") + "\n");
	    Text email = new Text("- adresa de e-mail: " + PDFHelper.getStrWithDots(60, "") + "\n\n");
	    declaratie.add(contact2);
	    declaratie.add(PDFHelper.addTab());
	    declaratie.add(fix);
	    declaratie.add(PDFHelper.addTab());
	    declaratie.add(mobil);
	    declaratie.add(PDFHelper.addTab());
	    declaratie.add(email);
	    declaratie.add(PDFHelper.addTab());
	    Text acordTitlu = new Text(" Declar pe propria raspundere ca: \n");
	    declaratie.add(acordTitlu);
	    declaratie.add(PDFHelper.addTab());
	    Text acordOK = new Text(
		    " Sunt de acord cu transmiterea corespondentei prin mijloacele electronice la adresa de mail mentionata.\n");
	    declaratie.add(acordOK);
	    declaratie.add(PDFHelper.addTab());
	    
	    final Rectangle rectNot = new Rectangle(35.0f, 275.0f, 25.0f,20.0f);
	    new PdfCanvas(document.getFirstPage()).rectangle(rectNot).setLineWidth(2f).setStrokeColor(ColorConstants.BLACK).stroke();
	    final Rectangle rectOK = new Rectangle(35.0f, 310f, 25.0f,20.0f);
	    new PdfCanvas(document.getFirstPage()).rectangle(rectOK).setLineWidth(2f).setStrokeColor(ColorConstants.BLACK).stroke();
	    Text acordNot = new Text(
		    " Nu sunt de acord cu transmiterea corespondentei prin mijloacele electronice la adresa de mail mentionata.\n\n\n");
	    declaratie.add(acordNot);
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
	    String dateNow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	    Text semnPrim = new Text(dateNow);
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
	    document.getDocumentInfo().setTitle("Declaratie_de_Corespondenta_" + tm);
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

	    Paragraph title = new Paragraph();
	    Text t1 = new Text("\n\n\nDECLARATIE PE PROPRIA RASPUNDERE\n\n\n").setBold();
	    title.add(t1);
	    title.setTextAlignment(TextAlignment.CENTER);
	    doc.add(title);
	    
	    
	    Paragraph dec =  new Paragraph();
	    dec.add(new Tab());
	    final String titlu = map.get("titlu").toLowerCase().contains("dl") ? "Subsemnatul: " : "Subsemnata: ";
	    final String identifi = map.get("titlu").toLowerCase().contains("dl") ? " indentificat cu: "
		    : " identificata cu: ";
	    dec.add(titlu);
	    dec.add(PDFHelper.createAdjustableParagraph(75,
		    new Paragraph(map.get("prenume")+ " " + map.get("nume")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(" avand Cod Numeric Personal ");
	    dec.add(PDFHelper.createAdjustableParagraph(45,
		    new Paragraph(map.get("CNP")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(identifi);
	    dec.add(PDFHelper.createAdjustableParagraph(15,
		    new Paragraph("C.I").setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(" serie ");
	    dec.add(PDFHelper.createAdjustableParagraph(10,
		    new Paragraph(map.get("serie")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(" numarul ");
	    dec.add(PDFHelper.createAdjustableParagraph(20,
		    new Paragraph(map.get("nrSerie")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(" cetatenia ");
	    dec.add(PDFHelper.createAdjustableParagraph(35,
		    new Paragraph(map.get("cetatenie")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(" cu domiciliul/resedinta in(adresa completa de corespondenta) localitatea ");
	    dec.add(PDFHelper.createAdjustableParagraph(30,
		    new Paragraph(PDFHelper.solveIfEmpty(map.get("localitate"))).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(" strada ");
	    dec.add(PDFHelper.createAdjustableParagraph(42,
		    new Paragraph(PDFHelper.solveIfEmpty(map.get("strada"))).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(" numarul ");
	    dec.add(PDFHelper.createAdjustableParagraph(15,
		    new Paragraph(PDFHelper.solveIfEmpty(map.get("nrStrada"))).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(" bloc ");
	    dec.add(PDFHelper.createAdjustableParagraph(15,
		    new Paragraph(PDFHelper.solveIfEmpty(map.get("bloc"))).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(" scara ");
	    dec.add(PDFHelper.createAdjustableParagraph(15,
		    new Paragraph(PDFHelper.solveIfEmpty(map.get("scara"))).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(" etaj ");
	    dec.add(PDFHelper.createAdjustableParagraph(9,
		    new Paragraph(PDFHelper.solveIfEmpty(map.get("etaj"))).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(" apartament ");
	    dec.add(PDFHelper.createAdjustableParagraph(9,
		    new Paragraph(PDFHelper.solveIfEmpty(map.get("apartament"))).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(" sector ");
	    dec.add(PDFHelper.createAdjustableParagraph(9,
		    new Paragraph(PDFHelper.solveIfEmpty(map.get("sector"))).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(" judet ");
	    dec.add(PDFHelper.createAdjustableParagraph(16,
		    new Paragraph(map.get("judet")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(" cod postal ");
	    dec.add(PDFHelper.createAdjustableParagraph(12,
		    new Paragraph(PDFHelper.solveIfEmpty(map.get("codPostal"))).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(" .\n");
	    doc.add(dec);
	    
	    Paragraph contact =  new Paragraph("\n");
	  //  contact.add(new Tab());
	    contact.add("Pot fi contactat la: \n");
	    contact.add(new Tab());
	    contact.add("- Telefon Fix: ");
	    contact.add(PDFHelper.createAdjustableParagraph(60,
		    new Paragraph(PDFHelper.solveIfEmpty(map.get("fix"))).setBold().setTextAlignment(TextAlignment.CENTER)));
	    contact.add("\n");
	    contact.add(new Tab());
	    contact.add("- Telefon Mobil: ");
	    contact.add(PDFHelper.createAdjustableParagraph(60,
		    new Paragraph(PDFHelper.solveIfEmpty(map.get("mobil"))).setBold().setTextAlignment(TextAlignment.CENTER)));
	    contact.add("\n");
	    contact.add(new Tab());
	    contact.add("- Adresa de E-Mail: ");
	    contact.add(PDFHelper.createAdjustableParagraph(60,
		    new Paragraph(PDFHelper.solveIfEmpty(map.get("email"))).setBold().setTextAlignment(TextAlignment.CENTER)));
	    
	    doc.add(contact);
	    
	    Paragraph acordTitlu = new Paragraph("\n");
	    acordTitlu.add(PDFHelper.addTab());
	    Text preAcord = new Text("Declar pe propria raspundere ca: \n");
	    acordTitlu.add(preAcord);
	    doc.add(acordTitlu);

	    PdfFont zapfdingbats = PdfFontFactory.createFont(StandardFonts.ZAPFDINGBATS);
	    Text chunk = new Text("4").setFont(zapfdingbats).setFontSize(14);
	    Paragraph acordOK = new Paragraph();
	    if (map.get("acord").toLowerCase().contains("true"))
		acordOK.add(chunk);
	    else
		acordOK.add(PDFHelper.addTab());
	    
	    acordOK.add("Sunt de acord cu transmiterea corespondentei prin mijloacele electronice la adresa de mail mentionata.\n");
	    doc.add(acordOK);

	    
	    
	/*    final String acord = map.get("acord").toLowerCase().contains("true") ? "Yes" : "Off";
	    final String disacord = acord.toLowerCase().contains("true") ? "Off" : "Yes";
	    final PdfAcroForm form = new PdfAcroForm(writer);
	    form.addCheckBox("Disagree", disacord, true, 30.0f, 370.0f, 50.0f, 386.0f);
	    form.addCheckBox("Agree", acord, true, 30.0f, 410.0f, 50.0f, 426.0f);*/
	    
	    
	    Paragraph acordNot =  new Paragraph();
	 if(!map.get("acord").toLowerCase().contains("true"))
	     acordNot.add(chunk);
	 else
	     acordNot.add(PDFHelper.addTab());
	 
	   acordNot.add("Nu sunt de acord cu transmiterea corespondentei prin mijloacele electronice la adresa de mail mentionata.\n\n\n");
	   doc.add(acordNot);

	    Table semnaturi = new Table(1);

	    semnaturi.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph p1 = new Paragraph();
	    Text primar = new Text("Data");
	    p1.add(primar);
	    p1.addTabStops(new TabStop(width / 1.18f, TabAlignment.RIGHT));
	    p1.add(new Tab());
	    Text intocmit = new Text("Semnatura");
	    p1.add(intocmit);
	    Cell cell12 = new Cell();
	    cell12.setBorder(Border.NO_BORDER);
	    cell12.add(p1);
	    semnaturi.addCell(cell12);
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
	    Cell cell21 = new Cell();
	    cell21.setBorder(Border.NO_BORDER);
	    cell21.add(semnR);
	    semnaturiR.addCell(cell21);
	    doc.add(semnaturiR);

	    doc.close();
	    document.close();
	    writer.flush();

	} catch (IOException ex2) {
	}

    }

}
