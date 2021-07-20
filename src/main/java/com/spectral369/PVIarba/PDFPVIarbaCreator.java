package com.spectral369.PVIarba;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
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
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TabAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.spectral369.birotica.PdfList;
import com.spectral369.utils.FooterEvt;
import com.spectral369.utils.PDFHelper;
import com.spectral369.utils.Utils;

public class PDFPVIarbaCreator {

    File pdff = null;
    private transient PdfDocument document;
    private transient PdfWriter writer;
    String id = Utils.getRandomStringSerial("CC");

    public PDFPVIarbaCreator(Map<String, String> map, String tm) {

	pdff = new File(Utils.getSaveFileLocation("PV_Iarba_" + tm + ".pdf"));
	if (map.isEmpty()) {
	    this.generatePDF(tm, pdff);
	    PdfList.addFile(id, pdff.getAbsolutePath());

	} else {

	    this.generatePDF(tm, pdff, map);
	    PdfList.addFile(id, pdff.getAbsolutePath());
	}
    }

    public String getID() {
	return this.id;
    }

    private void generatePDF(final String tm, final File pdfFile) {
	try {
	    writer = new PdfWriter(pdfFile);
	    Image antetLogo = PDFHelper.getAntetLogo();
	    document = new PdfDocument(writer);
	    document.getDocumentInfo().addCreationDate();
	    document.getDocumentInfo().setAuthor("spectral369");
	    document.setDefaultPageSize(PageSize.A4);
	    document.getDocumentInfo().setTitle("Proces_Verbal_Iarba_100" + tm);
	    Document doc = new Document(document);
	    doc.setFontSize(11.5f);
	    float width = doc.getPageEffectiveArea(PageSize.A4).getWidth();
	    document.addEventHandler(PdfDocumentEvent.START_PAGE, new FooterEvt(width));

	    Paragraph antet = new Paragraph();

	    float documentWidth = document.getDefaultPageSize().getWidth() - doc.getLeftMargin() - doc.getRightMargin();
	    float documentHeight = document.getDefaultPageSize().getHeight() - doc.getTopMargin()
		    - doc.getBottomMargin();

	    antetLogo.scaleToFit(documentWidth, documentHeight);

	    antet.add(antetLogo);
	    antet.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    doc.add(antet);

	    doc.add(new Paragraph(Utils.getRandomStringSerial("PVSP") + Calendar.getInstance().get(Calendar.YEAR))
		    .setFontSize(10));
	    Table table1 =  new Table(1);
		   table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
		    Paragraph l1 =  new  Paragraph();
		    Text nrI = new Text(
				    "\nNr. " +PDFHelper.getStrWithDots(15, "") + " " + "data " + PDFHelper.getStrWithDots(20, ""));
		    l1.add(nrI);
		    l1.addTabStops(new TabStop(width/1.2f, TabAlignment.RIGHT));
		    l1.add(new Tab());
		    Text aprobat =  new Text("Aprobat");
		    l1.add(aprobat);
		    l1.add(PDFHelper.addTab());
		    Cell l1Cell =  new Cell();
		    l1Cell.setBorder(Border.NO_BORDER);
		    l1Cell.add(l1);
		    table1.addCell(l1Cell);
		    doc.add(table1);
		    
		    
		    Table table2 =  new Table(1);
		    Paragraph l2 =  new Paragraph();
		    Text primSemn1=  new Text("");
		    l2.add(primSemn1);
		    l2.addTabStops(new TabStop(width/1.235f, TabAlignment.RIGHT));
		    l2.add(new Tab());
		    Text primartop =  new Text("Primar");
		    l2.add(primartop);
		    Cell l2Cell =  new Cell();
		    l2Cell.add(l2);
		    l2Cell.setBorder(Border.NO_BORDER);
		    table2.addCell(l2Cell);
		    doc.add(table2);
		    
		    Table table3 =  new Table(1);
		    Paragraph l3 =  new Paragraph();
		    Text topSemn1 =  new Text("");
		    l3.add(topSemn1);
		    l3.addTabStops(new TabStop(width/0.9f, TabAlignment.RIGHT));
		    l3.add(new Tab());
		    Text topSemn2 =  new Text(PDFHelper.getStrWithDots(28, ""));
		    l3.add(topSemn2);
		    l3.add(PDFHelper.addTab());
		    Cell l3Cell =  new Cell();
		    l3Cell.add(l3);
		    l3Cell.setBorder(Border.NO_BORDER);
		    table3.addCell(l3Cell);
		    doc.add(table3);
		    
	    final Paragraph title = new Paragraph("\nProces Verbal\n\n").setBold();
	    title.setTextAlignment(TextAlignment.CENTER);
	    doc.add(title);
	    
	    final Paragraph declaratie = new Paragraph();

	    declaratie.add(PDFHelper.addTab());
	    Text dec1 = new Text("Incheiat azi, ziua " + getStrWithDots(10, "") + ", luna " + getStrWithDots(10, "")
		    + ", anul " + getStrWithDots(15, "") + ", ora " + getStrWithDots(14, "") + " .\n");
	    declaratie.add(dec1);
	    declaratie.add(PDFHelper.addTab());
	    Text contribuabil = new Text(
		    "S-a constatat ca contribuabilul " + getStrWithDots(35, "") + "" + ", avand C.N.P "
			    + getStrWithDots(30, "") + " nu si-a indeplinit obligatiile de ingrijire a spatiului verde "
			    + "aferent imobilului aflat in proprietate sau ingrijire.\n");
	    declaratie.add(contribuabil);
	    declaratie.add(PDFHelper.addTab());

	    Text dec2 = new Text(
		    "Urmare a prevederilor H.C.L 63 din 13.07.2019, angajatii serviciului de Spatii Verzi din cadrul primariei "
			    + "comunei Dudestii-Vechi au executat urmatoarele lucrari:\n");
	    declaratie.add(dec2);
	    Text oper = new Text(getStrWithDots(820, "") + "\n");
	    declaratie.add(oper);
	    Text dec3 = new Text("in comuna Dudestii-Vechi sat " + getStrWithDots(40, "") + "" + "Numarul "
		    + getStrWithDots(12, "") + " deoarece in urma sesizarilor, proprietarul sau mostenitorul imobilului"
		    + " mai sus mentionat nu a respectat Hotararea de Consiliu Local 63/13.07.2019 privind intretinerea si curatenia "
		    + "terenului public aferent imobilului.\n");
	    declaratie.add(dec3);
	    declaratie.add(PDFHelper.addTab());
	    Text dec4 = new Text("\nAdresa imobilului" + getStrWithDots(120, "") + " .\n");
	    declaratie.add(dec4);
	    declaratie.add(PDFHelper.addTab());
	    Text dec5 = new Text("\nNota totala pe lucrari: " + getStrWithDots(25, "") + "\n\n");
	    declaratie.add(dec5);
	    doc.add(declaratie);

	    Table semnaturi = new Table(1);

	    semnaturi.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph p1 = new Paragraph();
	    Text primar = new Text("Sef serviciu spatii verzi");
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
	  //  semnPrim.setUnderline();
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
	    document.setDefaultPageSize(PageSize.A4);
	    document.getDocumentInfo().setTitle("Proces_Verbal_Iarba_100" + tm);
	    Document doc = new Document(document);
	    doc.setFontSize(12);
	    float width = doc.getPageEffectiveArea(PageSize.A4).getWidth();
	    document.addEventHandler(PdfDocumentEvent.START_PAGE, new FooterEvt(width));

	    Paragraph antet = new Paragraph();

	    float documentWidth = document.getDefaultPageSize().getWidth() - doc.getLeftMargin() - doc.getRightMargin();
	    float documentHeight = document.getDefaultPageSize().getHeight() - doc.getTopMargin()
		    - doc.getBottomMargin();

	    antetLogo.scaleToFit(documentWidth, documentHeight);

	    antet.add(antetLogo);
	    antet.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    doc.add(antet);

	    doc.add(new Paragraph(Utils.getRandomStringSerial("PVSP") + Calendar.getInstance().get(Calendar.YEAR))
		    .setFontSize(10));
	    Table table1 =  new Table(1);
		   table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
		    Paragraph l1 =  new  Paragraph();
		    Text nrI = new Text(
				    "\nNr. " +PDFHelper.getStrWithDots(15, "") + " " + "data " + PDFHelper.getStrWithDots(20, ""));
		    l1.add(nrI);
		    l1.addTabStops(new TabStop(width/1.2f, TabAlignment.RIGHT));
		    l1.add(new Tab());
		    Text aprobat =  new Text("Aprobat");
		    l1.add(aprobat);
		    l1.add(PDFHelper.addTab());
		    Cell l1Cell =  new Cell();
		    l1Cell.setBorder(Border.NO_BORDER);
		    l1Cell.add(l1);
		    table1.addCell(l1Cell);
		    doc.add(table1);
		    
		    
		    Table table2 =  new Table(1);
		    Paragraph l2 =  new Paragraph();
		    Text primSemn1=  new Text("");
		    l2.add(primSemn1);
		    l2.addTabStops(new TabStop(width/1.235f, TabAlignment.RIGHT));
		    l2.add(new Tab());
		    Text primartop =  new Text("Primar");
		    l2.add(primartop);
		    Cell l2Cell =  new Cell();
		    l2Cell.add(l2);
		    l2Cell.setBorder(Border.NO_BORDER);
		    table2.addCell(l2Cell);
		    doc.add(table2);
		    
		    Table table3 =  new Table(1);
		    Paragraph l3 =  new Paragraph();
		    Text topSemn1 =  new Text("");
		    l3.add(topSemn1);
		    l3.addTabStops(new TabStop(width/0.9f, TabAlignment.RIGHT));
		    l3.add(new Tab());
		    Text topSemn2 =  new Text(PDFHelper.getStrWithDots(28, ""));
		    l3.add(topSemn2);
		    l3.add(PDFHelper.addTab());
		    Cell l3Cell =  new Cell();
		    l3Cell.add(l3);
		    l3Cell.setBorder(Border.NO_BORDER);
		    table3.addCell(l3Cell);
		    doc.add(table3);
		    
	    final Paragraph title = new Paragraph("\nProces Verbal\n\n").setBold();
	    title.setTextAlignment(TextAlignment.CENTER);
	    doc.add(title);
	    
	    
	    final Paragraph declaratie = new Paragraph();
	    declaratie.add(PDFHelper.addTab());
	    Paragraph dec1 = new Paragraph();
	    Text dec11 = new Text("Incheiat azi, ziua ");
	    dec1.add(dec11);
	    dec1.add(getPhraseStrWithDots(10, map.get("ziua")));
	    Text dec12 = new Text(", luna ");
	    dec1.add(dec12);
	    dec1.add(getPhraseStrWithDots(10, map.get("luna")));
	    Text dec13 = new Text(", anul ");
	    dec1.add(dec13);
	    dec1.add(getPhraseStrWithDots(15, map.get("anul")));
	    Text dec14 = new Text(", ora ");
	    dec1.add(dec14);
	    dec1.add(getPhraseStrWithDots(14, map.get("time")));
	    Text dec15 = new Text(" .\n");
	    dec1.add(dec15);

	    declaratie.add(dec1);
	    declaratie.add(PDFHelper.addTab());
	    Paragraph contribuabil = new Paragraph();
	    Text con1 = new Text("S-a constatat ca contribuabilul ");
	    contribuabil.add(con1);
	    contribuabil.add(getPhraseStrWithDots(22, map.get("contribuabil")));
	    Text con2 = new Text(", avand C.N.P ");
	    contribuabil.add(con2);
	    contribuabil.add(getPhraseStrWithDots(28, map.get("cnp")));
	    Text con3 = new Text(" nu si-a indeplinit obligatiile de ingrijire a spatiului verde "
		    + "aferent imobilului aflat in proprietate sau ingrijire\n");
	    contribuabil.add(con3);
	    declaratie.add(contribuabil);
	    declaratie.add(PDFHelper.addTab());

	    Text dec2 = new Text(
		    "Urmare a prevederilor H.C.L 63 din 13.07.2019, angajatii serviciului de Spatii Verzi din cadrul primariei "
			    + "comunei Dudestii-Vechi au executat urmatoarele lucrari:\n");
	    declaratie.add(dec2);

	    // Text oper = new Text( getStrWithDots(780, "") + "\n");
	    declaratie.add(getJobsByLine(map.get("job")));
	    Paragraph dec3 = new Paragraph();
	    Text dec31 = new Text("in comuna Dudestii-Vechi sat ");
	    dec3.add(dec31);
	    dec3.add(getPhraseStrWithDots(40, map.get("localitate")));
	    Text dec32 = new Text(" numarul ");
	    dec3.add(dec32);
	    dec3.add(getPhraseStrWithDots(12, map.get("nrCasa")));
	    Text dec33 = new Text(" deoarece in urma sesizarilor, proprietarul sau mostenitorul imobilului"
		    + " mai sus mentionat nu a respectat Hotararea de Consiliu Local 63/13.07.2019 privind intretinerea si curatenia "
		    + "terenului public aferent imobilului.\n");
	    dec3.add(dec33);

	    declaratie.add(dec3);
	    declaratie.add(PDFHelper.addTab());
	   Paragraph dec4 = new Paragraph();
	    Text dec41 = new Text("Adresa imobilului ");
	    dec4.add(dec41);
	    int size2 = 120 - map.get("adresa").length();
	    dec4.add(getPhraseStrWithDots(size2, map.get("adresa")));
	    Text dec42 = new Text(" .\n");
	    dec4.add(dec42);

	    declaratie.add(dec4);
	    declaratie.add(PDFHelper.addTab());
	    Paragraph dec5 = new Paragraph();
	    Text dec51 = new Text("\nNota totala pe lucrari ");
	    dec5.add(dec51);
	    dec5.add(getPhraseStrWithDots(25, map.get("suma")));
	    Text dec52 = new Text(" .\n\n");
	    dec5.add(dec52);
	    declaratie.add(dec5);

	    doc.add(declaratie);
	    Table semnaturi = new Table(1);

	    semnaturi.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph p1 = new Paragraph();
	    Text primar = new Text("Sef serviciu spatii verzi");
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
	  
	    Text semnPrim = new Text(map.get("sef"));
	  //  semnPrim.setUnderline();
	    semnR.add(semnPrim);
	    semnR.addTabStops(new TabStop(width / 1.18f, TabAlignment.RIGHT));
	    semnR.add(new Tab());
	    Text semnIntocmit = new Text(map.get("intocmit"));
	   // semnIntocmit.setUnderline();
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

    private String getStrWithDots(final int dots, final String str) {
	final int strSize = str.length();
	final StringBuilder sb = new StringBuilder();
	int dotsRemained;
	if (strSize > dots) {
	    dotsRemained = 0;
	} else {
	    dotsRemained = dots - strSize;
	}
	for (int i = 0; i < dotsRemained; ++i) {
	    if (i == dotsRemained / 2) {
		sb.append(str);
	    }
	    sb.append(".");
	}
	return sb.toString();
    }

    private Paragraph getPhraseStrWithDots(final int dots, final String str) {
	final int strSize = str.length();
	final Paragraph sb = new Paragraph();

	int dotsRemained;
	if (strSize > dots) {
	    dotsRemained = 0;
	} else {
	    int nrLitereMari = 0;
	    int nrLitereMici = 0;
	    for (int k = 0; k < str.length(); k++) {
		if (Character.isUpperCase(str.charAt(k)))
		    nrLitereMari++;
		if (Character.isLowerCase(str.charAt(k)))
		    nrLitereMici++;
	    }
	    if (nrLitereMari > 0) {
		dotsRemained = dots - (nrLitereMari * 2);
		dotsRemained = dotsRemained - nrLitereMici;
	    } else {
		dotsRemained = dots - strSize;
	    }
	    // dotsRemained = dots - strSize;
	}
	//Text chDots = new Text("");
	StringBuilder chDots =  new StringBuilder();
	//Text chStr = new Text("").setBold();
	StringBuilder chStr =  new StringBuilder();
	//chStr.setTextRise(1.7f);
	for (int i = 0; i < dotsRemained; ++i) {
	    if (i == dotsRemained / 2) {
		chStr.append(str);
		sb.add(new Text(chDots.toString()));
		sb.add( new Text(chStr.toString()).setBold());
		chDots = new StringBuilder();
	    }
	    chDots.append(".");
	}
	sb.add( new Text(chDots.toString()));
	return sb;
    }

    private Paragraph getJobsByLine(final String job) {
	Paragraph ph = new Paragraph();
	for (String line : job.split("\n")) {
	    int size = 156 - line.length();
	    ph.add(getPhraseStrWithDots(size, line));
	    ph.add("\n");
	}
	return ph;
    }

}
