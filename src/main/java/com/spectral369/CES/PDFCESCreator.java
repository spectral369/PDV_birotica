package com.spectral369.CES;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
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
	    // Image antetLogo = PDFHelper.getAntetLogo();
	    document = new PdfDocument(writer);
	    document.getDocumentInfo().addCreationDate();
	    document.getDocumentInfo().setAuthor("spectral369");
	    document.getDocumentInfo().setTitle("Declaratie_Examinare_Sociala_" + tm);
	    document.setDefaultPageSize(PageSize.A4);

	    Document doc = new Document(document);
	    doc.setFontSize(11f);
	    float width = doc.getPageEffectiveArea(PageSize.A4).getWidth();
	    document.addEventHandler(PdfDocumentEvent.START_PAGE, new FooterEvt(width));

	    /*
	     * final Paragraph antet = new Paragraph();
	     * 
	     * float documentWidth = document.getDefaultPageSize().getWidth() -
	     * doc.getLeftMargin() - doc.getRightMargin(); float documentHeight =
	     * document.getDefaultPageSize().getHeight() - doc.getTopMargin() -
	     * doc.getBottomMargin(); antetLogo.scaleToFit(documentWidth, documentHeight);
	     * antet.add(antetLogo);
	     * antet.setHorizontalAlignment(HorizontalAlignment.CENTER); doc.add(antet);
	     */
	    Paragraph anexa4 = new Paragraph("Anexa Nr. 4");
	    anexa4.setTextAlignment(TextAlignment.RIGHT);
	    doc.add(anexa4);
	    Paragraph nrInreg = new Paragraph("Nr. .........../.............");
	    nrInreg.setTextAlignment(TextAlignment.RIGHT);
	    doc.add(nrInreg);

	    Paragraph title = new Paragraph();
	    Text t1 = new Text("\n\nDoamna/Domnule Director\n\n").setBold();
	    title.add(t1);
	    title.setTextAlignment(TextAlignment.CENTER);
	    doc.add(title);

	    Paragraph declaratie = new Paragraph();
	    declaratie.add(PDFHelper.addTab());
	    Text subsemnat = new Text("Subsemnatul(a), " + PDFHelper.getStrWithDots(73, "")
		    + ", in calitate  de solicitat/apartinator al Dl-ui/D-nei " + PDFHelper.getStrWithDots(50, "")
		    + ", domiciliat(a) in localitatea " + PDFHelper.getStrWithDots(58, "") + ", strada "
		    + PDFHelper.getStrWithDots(35, "") + ", nr " + PDFHelper.getStrWithDots(12, "") + ", bl "
		    + PDFHelper.getStrWithDots(12, "") + ", sc " + PDFHelper.getStrWithDots(8, "") + ", et"
		    + PDFHelper.getStrWithDots(15, "") + ", ap" + PDFHelper.getStrWithDots(10, "") + ", sector/judet "
		    + PDFHelper.getStrWithDots(15, "") + ", telefon " + PDFHelper.getStrWithDots(23, "")
		    + ", act de indentitate C.I seria " + PDFHelper.getStrWithDots(10, "") + ", nr "
		    + PDFHelper.getStrWithDots(12, "") + ", CNP " + PDFHelper.getStrWithDots(51, "")
		    + ", solicit evaluarea in cadrul serviciului de evaluare complexa a persoanelor adulte "
		    + "cu disabilitati, in vederea: \n\n");
	    subsemnat.setTextAlignment(TextAlignment.JUSTIFIED);
	    declaratie.add(subsemnat);

	    Text fix = new Text(" [  ] incadrarii intr-un grad de handicap\n");
	    Text mobil = new Text(" [  ] reevaluarii incadraii in grad de handicap\n");
	    Text email = new Text(" [  ] obtinerii certificatului de orientare profesionala\n\n");
	    declaratie.add(PDFHelper.addTab());
	    declaratie.add(fix);
	    declaratie.add(PDFHelper.addTab());
	    declaratie.add(mobil);
	    declaratie.add(PDFHelper.addTab());
	    declaratie.add(email);
	    declaratie.add(PDFHelper.addTab());
	    Text acordTitlu = new Text(
		    "Solicit corespondenta la urmatoarea adresa: " + PDFHelper.getStrWithDots(85, "") + "\n\n");
	    declaratie.add(acordTitlu);
	    declaratie.add(PDFHelper.getStrWithDots(165, "") + "\n");
	    declaratie.add("\n");
	    doc.add(declaratie);

	    Table semnaturi = new Table(1);

	    semnaturi.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph p1 = new Paragraph();
	    Text primar = new Text("Data:").setBold();
	    p1.add(primar);
	    p1.addTabStops(new TabStop(width / 1.7f, TabAlignment.RIGHT));
	    p1.add(new Tab());
	    Text intocmit = new Text("Semnatura:").setBold();
	    p1.add(intocmit);
	    Cell cell1 = new Cell();
	    cell1.setBorder(Border.NO_BORDER);
	    cell1.add(p1);
	    semnaturi.addCell(cell1);
	    doc.add(semnaturi);

	    Paragraph anexez = new Paragraph("\n\n");
	    anexez.add(PDFHelper.addTab());
	    Text anexText = new Text("Anexez la prezenta cerere urmatoarele documente:\n\n");
	    anexez.add(anexText);
	    Text a1 = new Text(" [  ] Copie dupa documentele de identitate\n");
	    anexez.add(a1);
	    Text a2 = new Text(
		    " [  ] Certifical medial ci referat priviind situatia medicala prezenta de la specialist(-in original)\n");
	    anexez.add(a2);
	    Text a3 = new Text(
		    " [  ] scrisoare medicala -tip, de la medicul de familie(numai in situatia primei prezentari la serviciul de evaluare "
			    + "complexa) (-in original)\n");
	    anexez.add(a3);
	    Text a4 = new Text(
		    " [  ] Acte medicale in copie: -imagistica, de laborato, bilete de externare, tratamente recuperatorii, etc\n");
	    anexez.add(a4);
	    Text a5 = new Text(
		    " [  ] Ancheta sociala, de la serviciul social al primariei de domiciliu (-in original)\n");
	    anexez.add(a5);
	    anexez.add(PDFHelper.addTab());
	    Text a6 = new Text(" [  ] Docimente care atesta statutul social al persoanei (-copie)\n");
	    anexez.add(a6);
	    anexez.add(PDFHelper.addTab());
	    anexez.add(PDFHelper.addTab());
	    Text a61 = new Text(" [  ] Cupon de pensie si decizia de pensionare\n");
	    anexez.add(a61);
	    anexez.add(PDFHelper.addTab());
	    anexez.add(PDFHelper.addTab());
	    Text a62 = new Text(" [  ] Decizie medicala asupra capacitatii de munca\n");
	    anexez.add(a62);
	    anexez.add(PDFHelper.addTab());
	    anexez.add(PDFHelper.addTab());
	    Text a63 = new Text(" [  ] Adeverinta din care sa rezulte calitatea de salariat\n");
	    anexez.add(a63);
	    Text a64 = new Text(
		    " [  ] Adeverinta conform careia persoana nu are nici un venit, in cazul persoanelor fara venituri\n");
	    anexez.add(a64);
	    Text a7 = new Text(
		    " [  ] Copie dupa actele de studii(numai in cazul obtinerii certificatului  de orientare profesionala)\n");
	    anexez.add(a7);

	    Text a8 = new Text(
		    " [  ] Copie dupa certificatul de incadrare in grad de handicap -precedent si programul individual de reabilitare si integrare profesionala\n");
	//    a8.setTextAlignment(TextAlignment.JUSTIFIED_ALL);
	    anexez.add(a8);
	    Text a9 = new Text(" [  ] Folie plastic/ Dosar plastic\n\n");
	    anexez.add(a9);
	   anexez.setTextAlignment(TextAlignment.JUSTIFIED);
	    doc.add(anexez);
	
	    Paragraph footer = new Paragraph();
	    footer.add(PDFHelper.addTab());
	    Text footer1 = new Text(
		    "Dosarul complet se depune la Serviciul Management de Caz Adulti, din cadrul D.G.A.S.P.C. Timis, P-ta Regina Maria Nr. 3, Timisoara "
			    + "(langa registratura institutiei)").setBold();
	    footer.add(footer1);
	    doc.add(footer);

	    doc.close();
	    document.close();
	    writer.flush();

	} catch (IOException ex2) {
	}

    }

    private void generatePDF(final String tm, final File pdfFile, final Map<String, String> map) {
	try {
	    writer = new PdfWriter(pdfFile);
	    document = new PdfDocument(writer);
	    document.getDocumentInfo().addCreationDate();
	    document.getDocumentInfo().setAuthor("spectral369");
	    document.getDocumentInfo().setTitle("Declaratie_Examinare_Sociala_" + tm);
	    document.setDefaultPageSize(PageSize.A4);

	    Document doc = new Document(document);
	    float width = doc.getPageEffectiveArea(PageSize.A4).getWidth();
	    document.addEventHandler(PdfDocumentEvent.START_PAGE, new FooterEvt(width));
	    doc.setFontSize(11.2f);

	    Paragraph anexa4 = new Paragraph("Anexa Nr. 4");
	    anexa4.setTextAlignment(TextAlignment.RIGHT);
	    doc.add(anexa4);
	    Paragraph nrInreg = new Paragraph("Nr. .........../.............");
	    nrInreg.setTextAlignment(TextAlignment.RIGHT);
	    doc.add(nrInreg);

	    Paragraph title = new Paragraph();
	    Text t1 = new Text("\n\nDoamna/Domnule Director\n\n").setBold();
	    title.add(t1);
	    title.setTextAlignment(TextAlignment.CENTER);
	    doc.add(title);

	    Paragraph dec = new Paragraph();
	    dec.add(new Tab());
	    final String titlu = map.get("titlu").toLowerCase().contains("dl") ? "Subsemnatul: " : "Subsemnata: ";
	    final String identifi = map.get("titlu").toLowerCase().contains("dl") ? " Dl-ui " : " D-nei ";
	    final String dom = map.get("titlu").toLowerCase().contains("dl") ? " domiciliat " : " domiciliata ";
	    dec.add(titlu);
	    dec.add(PDFHelper.createAdjustableParagraph(70,
		    new Paragraph(map.get("prenume")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(" in calitate de solicitant/apartinator al " + identifi);
	    dec.add(PDFHelper.createAdjustableParagraph(45,
		    new Paragraph(map.get("nume")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(dom + "in loc. ");
	    dec.add(PDFHelper.createAdjustableParagraph(35,
		    new Paragraph(map.get("localitate")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(", str ");
	    dec.add(PDFHelper.createAdjustableParagraph(28,
		    new Paragraph(map.get("strada")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(", nr ");
	    dec.add(PDFHelper.createAdjustableParagraph(8,
		    new Paragraph(map.get("nrStrada")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(", bl");
	    dec.add(PDFHelper.createAdjustableParagraph(10,
		    new Paragraph(map.get("bloc")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(", sc");
	    dec.add(PDFHelper.createAdjustableParagraph(10, new Paragraph(PDFHelper.solveIfEmpty(map.get("scara")))
		    .setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(", et");
	    dec.add(PDFHelper.createAdjustableParagraph(10, new Paragraph(PDFHelper.solveIfEmpty(map.get("etaj")))
		    .setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(", ap");
	    dec.add(PDFHelper.createAdjustableParagraph(15, new Paragraph(PDFHelper.solveIfEmpty(map.get("apartament")))
		    .setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(", sector/judet ");
	    dec.add(PDFHelper.createAdjustableParagraph(22, new Paragraph(PDFHelper.solveIfEmpty(map.get("judet")))
		    .setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(", telefon ");
	    dec.add(PDFHelper.createAdjustableParagraph(25, new Paragraph(PDFHelper.solveIfEmpty(map.get("mobil")))
		    .setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(", seria ");
	    dec.add(PDFHelper.createAdjustableParagraph(9, new Paragraph(PDFHelper.solveIfEmpty(map.get("serie")))
		    .setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(", nr ");
	    dec.add(PDFHelper.createAdjustableParagraph(12, new Paragraph(PDFHelper.solveIfEmpty(map.get("nrSerie")))
		    .setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(", CNP ");
	    dec.add(PDFHelper.createAdjustableParagraph(30, new Paragraph(PDFHelper.solveIfEmpty(map.get("CNP")))
		    .setBold().setTextAlignment(TextAlignment.CENTER)));
	    dec.add(" solicit evaluarea in cadrul serviciului de evaluare complexa a persoanelor adulte "
		    + "cu disabilitati, in vederea: \n");
	
	    doc.add(dec);

	    Paragraph alege = new Paragraph();
	    alege.add(PDFHelper.addTab());
	    PdfFont zapfdingbats = PdfFontFactory.createFont(StandardFonts.ZAPFDINGBATS);
	    Text chunkOK = new Text("3").setFont(zapfdingbats).setFontSize(14);
	    Text chunkNot = new Text("5").setFont(zapfdingbats).setFontSize(14);
	    if (map.get("incadrareHandicap").toLowerCase().contains("true"))
		alege.add(chunkOK);
	    else
		alege.add(chunkNot);
	    alege.add(" incadrarii intr-un grad de handicap\n");

	    alege.add(PDFHelper.addTab());
	    if (map.get("reevaluareHandicap").toLowerCase().contains("true"))
		alege.add(chunkOK);
	    else
		alege.add(chunkNot);
	    alege.add("reevaluarii incadrarii in grad de handicap\n");

	    alege.add(PDFHelper.addTab());
	    if (map.get("obtinereOrientare").toLowerCase().contains("true"))
		alege.add(chunkOK);
	    else
		alege.add(chunkNot);
	    alege.add(" obtinerii certificatului de orientare profesionala\n");

	    alege.add(PDFHelper.addTab());

	    doc.add(alege);

	    Paragraph solicit = new Paragraph(
		    "Solicit corespondenta la urmatoarea adresa: ");
	    Text addr =  new Text(map.get("addrCorespondenta") + "\n\n").setBold();
	    solicit.add(addr);
	    doc.add(solicit);

	    Table semnaturi = new Table(1);

	    semnaturi.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph p1 = new Paragraph();
	    Text primar = new Text("Data: " + map.get("data")).setBold();
	    p1.add(primar);
	    p1.addTabStops(new TabStop(width / 1.9f, TabAlignment.RIGHT));
	    p1.add(new Tab());
	    Text intocmit = new Text("Semnatura:").setBold();
	    p1.add(intocmit);
	    Cell cell1 = new Cell();
	    cell1.setBorder(Border.NO_BORDER);
	    cell1.add(p1);
	    semnaturi.addCell(cell1);
	    doc.add(semnaturi);

	    Paragraph anexez = new Paragraph("\n");
	    anexez.add(PDFHelper.addTab());
	    Text anexText = new Text("Anexez la prezenta cerere urmatoarele documente:\n");
	    anexez.add(anexText);
	    Text a1 = new Text(" [  ] Copie dupa documentele de identitate\n");
	    anexez.add(a1);
	    Text a2 = new Text(
		    " [  ] Certifical medial ci referat priviind situatia medicala prezenta de la specialist(-in original)\n");
	    anexez.add(a2);
	    Text a3 = new Text(
		    " [  ] scrisoare medicala -tip, de la medicul de familie(numai in situatia primei prezentari la serviciul de evaluare "
			    + "complexa) (-in original)\n");
	    anexez.add(a3);
	    Text a4 = new Text(
		    " [  ] Acte medicale in copie: -imagistica, de laborato, bilete de externare, tratamente recuperatorii, etc\n");
	    anexez.add(a4);
	    Text a5 = new Text(
		    " [  ] Ancheta sociala, de la serviciul social al primariei de domiciliu (-in original)\n");
	    anexez.add(a5);
	    anexez.add(PDFHelper.addTab());
	    Text a6 = new Text(" [  ] Docimente care atesta statutul social al persoanei (-copie)\n");
	    anexez.add(a6);
	    anexez.add(PDFHelper.addTab());
	    anexez.add(PDFHelper.addTab());
	    Text a61 = new Text(" [  ] Cupon de pensie si decizia de pensionare\n");
	    anexez.add(a61);
	    anexez.add(PDFHelper.addTab());
	    anexez.add(PDFHelper.addTab());
	    Text a62 = new Text(" [  ] Decizie medicala asupra capacitatii de munca\n");
	    anexez.add(a62);
	    anexez.add(PDFHelper.addTab());
	    anexez.add(PDFHelper.addTab());
	    Text a63 = new Text(" [  ] Adeverinta din care sa rezulte calitatea de salariat\n");
	    // anexez.add(PDFHelper.addTab());anexez.add(PDFHelper.addTab());
	    anexez.add(a63);
	    Text a64 = new Text(
		    " [  ] Adeverinta conform careia persoana nu are nici un venit, in cazul persoanelor fara venituri\n");
	    anexez.add(a64);
	    Text a7 = new Text(
		    " [  ] copie dupa actele de studii(numai in cazul obtinerii certificatului  de orientare profesionala)\n");
	    anexez.add(a7);
	    Text a8 = new Text(
		    " [  ] Copie dupa certificatul de incadrare in grad dehandicap - precedent si programul individual de reabilitare si integrare profesionala\n");
	    anexez.add(a8);
	    Text a9 = new Text(" [  ] Folie plastic/ Dosar plastic\n\n");

	    anexez.add(a9);
	    doc.add(anexez);

	    Paragraph footer = new Paragraph();
	    footer.add(PDFHelper.addTab());
	    Text footer1 = new Text(
		    "Dosarul complet se depune la Serviciul Management de Caz Adulti, din cadrul D.G.A.S.P.C. Timis, P-ta Regina Maria Nr. 3, Timisoara "
			    + "(langa registratura institutiei)").setBold();
	    footer.add(footer1);
	    doc.add(footer);

	    doc.close();
	    document.close();
	    writer.flush();

	} catch (IOException ex2) {
	}

    }

}
