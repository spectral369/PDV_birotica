package com.spectral369.CS;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class PDFCSCreator {

    
    File pdff = null;
    private transient PdfDocument document;
    private transient PdfWriter writer;
    String id = Utils.getRandomStringSerial("CS");

    public PDFCSCreator(Map<String, String> map, String tm) {
	
	pdff = new File(Utils.getSaveFileLocation("Cerere_Scutire_" + tm + ".pdf"));
	if (map.isEmpty()) {
	    generatePDF(tm, pdff);
	    PdfList.addFile(id, pdff.getAbsolutePath());

	} else {

	    generatePDF(tm, pdff, map);
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
	    document.getDocumentInfo().setTitle("CS_" + tm);
	    document.setDefaultPageSize(PageSize.A4);
	    Document doc = new Document(document);
	    float width = doc.getPageEffectiveArea(PageSize.A4).getWidth();
	   // doc.setFontSize(8f);
	    document.addEventHandler(PdfDocumentEvent.START_PAGE, new FooterEvt(width));

	    final Paragraph antet = new Paragraph();

	    float documentWidth = document.getDefaultPageSize().getWidth() - doc.getLeftMargin() - doc.getRightMargin();

	    float documentHeight = document.getDefaultPageSize().getHeight() - doc.getTopMargin()
		    - doc.getBottomMargin();

	    antetLogo.scaleToFit(documentWidth-250, documentHeight-250);

	    antet.add(antetLogo);
	    antet.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    antet.setVerticalAlignment(VerticalAlignment.TOP);
	    antet.setTextAlignment(TextAlignment.CENTER);
	  //  doc.add(antet);

	    final Paragraph nrInreg = new Paragraph();
	    nrInreg.add("\n\n");
	    nrInreg.add(new Tab());
	    Text nrI = new Text(
		    "\tNr. " + PDFHelper.getStrWithDots(15, "") + " " + "data " + PDFHelper.getStrWithDots(20, ""));
	    nrInreg.add(nrI);
	    doc.add(nrInreg);

	    final Paragraph titlu = new Paragraph();
	    titlu.setFontSize(12f);
	    Text t1 = new Text("\nCerere Scutire\n\n").setBold();
	    titlu.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    titlu.setTextAlignment(TextAlignment.CENTER);
	    titlu.add(t1).addStyle(PDFHelper.bold12nr);
	    doc.add(titlu);

	    final Paragraph declaratie = new Paragraph();
	    declaratie.setFontSize(9.5f);
	    declaratie.add(PDFHelper.addTab());
	    Text dec1 = new Text("Subsemnatul(a) " + PDFHelper.getStrWithDash(70, "") + " domiciliat(a) in "
		    + PDFHelper.getStrWithDash(68, "") + " tel. "+ PDFHelper.getStrWithDash(21,"") +" E-mail "+PDFHelper.getStrWithDash(28, "")+
		    " reprezentat(a) prin "+ PDFHelper.getStrWithDash(50,"") + "in calitate de imputernicit cu domiciliul in " + PDFHelper.getStrWithDash(55,"")+ 
		    " indentificat prin C.I seria "
		    + PDFHelper.getStrWithDash(10, "") + " nr. " + PDFHelper.getStrWithDash(15, "") + " avand C.N.P "
		    + PDFHelper.getStrWithDash(34, "") + " prin prezenta solicit scutirea de impozit/ taxa pe:.\n");
	    declaratie.add(dec1);
	    declaratie.add(PDFHelper.addTab());
	    declaratie.add(PDFHelper.addTab());
	    Text dec11 = new Text("[  ] Cladire\n");
	    declaratie.add(dec11);
	    
	    declaratie.add(PDFHelper.addTab());
	    declaratie.add(PDFHelper.addTab());
	    Text dec111 = new Text("[  ] Teren\n");
	    declaratie.add(dec111);
	    declaratie.add(PDFHelper.addTab());
	    declaratie.add(PDFHelper.addTab());
	    Text dec112 = new Text("[  ] Mijloc de transport\n");
	    declaratie.add(dec112);
	    declaratie.add(PDFHelper.addTab());
	    declaratie.add(PDFHelper.addTab());  
	    declaratie.add(PDFHelper.addTab());
	    declaratie.add(PDFHelper.addTab());
	    PdfFont zapfdingbats = PdfFontFactory.createFont(StandardFonts.ZAPFDINGBATS);
	    Text checkAdrImobil = new Text("3").setFont(zapfdingbats).setFontSize(14);
	    declaratie.add(checkAdrImobil);
	    Text TxtAdrImobil =  new  Text(" Pentru bunurile imobile(cladirile si terenurile) situate la adresele:\n");
	    declaratie.add(TxtAdrImobil);
	    declaratie.add(PDFHelper.getStrWithDash(95,""));
	    declaratie.add("\n");
	    declaratie.add(PDFHelper.addTab());
	    declaratie.add(PDFHelper.addTab());  
	    declaratie.add(PDFHelper.addTab());
	    declaratie.add(PDFHelper.addTab());
	    Text checkAdrMobil = new Text("3").setFont(zapfdingbats).setFontSize(14);
	    declaratie.add(checkAdrMobil);
	    Text TxtAdrMobil =  new  Text(" Pentru un singur mijloc de transport(marca, numar de identificare) :\n");
	    declaratie.add(TxtAdrMobil);
	    declaratie.add(PDFHelper.getStrWithDash(95,""));
	    
	    declaratie.add(PDFHelper.addTab());
	    Text dec12 = new Text("\nMentionez ca, detin calitatea de proprietar(unic / devalmasie / in indiviziune) impreuna cu :\n");
	    declaratie.add(dec12);
	    Text dec13 = new Text(PDFHelper.getStrWithDash(84, "") + " conform actului de dobandire nr. "+PDFHelper.getStrWithDash(10,"")+ " / "+
	    PDFHelper.getStrWithDash(12,"")+"\n");
	    declaratie.add(dec13);
	    Paragraph small1 =  new Paragraph();
	    small1.setFontSize(8);
	    small1.add(PDFHelper.addTab());
	    Text dec2 = new Text("Fiind beneficiar al:\n").setBold();
	    small1.add(dec2);
	    Paragraph l168 =  new Paragraph();
	    Text l168a =  new Text("[  ] Legea nr. 168/2020").setBold();
	    l168.add(l168a);
	    Text l168b =  new Text(" pentru recunoasterea meritelor personalului participant la actiunile militare, misiuni si operatii pe "
	    	+ "teritoriul sau in afara teritoriului statului roman si acordarea unor drepturi acestuia, familiei acestuia si urmasilor celui decedat.");
	    l168.add(l168b);
	    small1.add(l168);
	    
	    Paragraph l118=  new Paragraph();
	    Text l118a =  new Text("[  ] Decretul-Lege nr. 118/1990 art.1 si art.5 alin. (1)-(3)").setBold();
	    l118.add(l118a);
	    Text l118b =  new Text(" privind acordarea unor drepturi persoanelor persecutate din motive politice de dictatura instaurata cu "
	    	+ "incepere de la 6 martie 1945, precum si celor deportate in strainatate ori constituite in prizonieri, republicat, si a persoanelor fizice prevazute la ");
	    l118.add(l118b);
	    Text l118c =  new Text("art. 1 din Ordonanta Guvernului nr. 105/1999").setBold();
	    l118.add(l118c);
	    Text l118d =  new Text(", republicata, cu modificarile si completarile ulterioare; scutirea ramane valabila si in cazul transferului proprietatii prin mostenire catre"
	    	+ " copiii acestora, indiferent unde acestea domiciliaza.");
	    l118.add(l118d);
	    small1.add(l118);
	    
	    Paragraph l44 =  new Paragraph();
	    Text l44a =  new Text("[  ] Legea nr.44/1994").setBold();
	    l44.add(l44a);
	    Text l44b =  new Text(" - privind veteranii de razboi, vaduvele de razboi si vaduvele nerecasatorite ale veteranilor de razboi.");
	    l44.add(l44b);
	    small1.add(l44);
	    
	    Paragraph l448 =  new Paragraph();
	    Text l448a =  new Text("[  ] Legea nr.448/2006").setBold();
	    l448.add(l448a);
	    Text l448b =  new Text(" pentru apobarea OUG Nr. 102/1999 privind protectia speciala si incadrarea in munca a persoanelor cu handicap");
	    l448.add(l448b);
	    small1.add(l448);
	    
	    Paragraph l341 =  new Paragraph();
	    Text l341a =  new Text("[  ] Legea nr.341/2004").setBold();
	    l341.add(l341a);
	    Text l341b =  new Text(" Legea recunostintei pentru victoria Revolutiei din Decembrie 1989 si pentru revolta muncitoreasca anticomunista de la Brasov din noiembie 1987"
	    	+ " art.3 alin.1 lit.b si art.4 alin.1 .");
	    l341.add(l341b);
	    small1.add(l341);
	    
	    Paragraph cabmed =  new Paragraph();
	    Text cabmeda =  new Text("[  ] Scutire Cabinet medicina de familie").setBold();
	    cabmed.add(cabmeda);
	    Text cabmedb =  new Text(" conform Lege nr. 227/2015 art.456, lit.h");
	    cabmed.add(cabmedb);
	    small1.add(cabmed);
	    
	    
	    Paragraph covid =  new Paragraph();
	    Text covida =  new Text("[  ] Scutire COVID-19 urmasii personalului medical lege nr. 56/2020 art.4, alin.1, litera d").setBold();
	    covid.add(covida);
	    small1.add(covid);
	    doc.add(declaratie);
	    doc.add(small1);
	    
	    
	    
	    Table semnaturi = new Table(1);
	    semnaturi.setFontSize(10f);
	    semnaturi.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph p1 = new Paragraph();
	    Text primar = new Text("Data");
	    p1.add(primar);
	    p1.addTabStops(new TabStop(width / 1.18f, TabAlignment.RIGHT));
	    p1.add(new Tab());
	    Text intocmit = new Text("Semnatura(olografa)");
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
	    Text semnPrim = new Text(PDFHelper.getStrWithDash(15, ""));
	    semnR.add(semnPrim);
	    semnR.addTabStops(new TabStop(width / 1.18f, TabAlignment.RIGHT));
	    semnR.add(new Tab());
	    Text semnIntocmit = new Text(PDFHelper.getStrWithDash(18, ""));
	    semnR.add(semnIntocmit);
	    Cell cell2 = new Cell();
	    cell2.setBorder(Border.NO_BORDER);
	    cell2.add(semnR);
	    semnaturiR.addCell(cell2);
	    doc.add(semnaturiR);
	    
	    Paragraph footer1 =  new Paragraph();
	    footer1.setFontSize(8.2f);
	    footer1.add(PDFHelper.addTab());
	    Text  f1  =  new Text("1 - Se ataseaza copii de pe documentele justificative certificate cu mentiunea \"conform cu originalul\","
	    	+ " act de indentitate, legitimatie, \n");
	    Text f11 =  new Text("     certificat, adeverinta, document care atesta calitatea de beneficiar al scutirii conform legilor speciale.\n");
	    footer1.add(f1);
	    footer1.add(PDFHelper.addTab());
	    footer1.add(f11);
	    footer1.add(PDFHelper.addTab());
	    Text f2 =  new Text("2 - Pentru scutirea de impozit/taxa pe cladire, teren, mijloc de transport in functie de calitatea solicitantului se bufeaza unul "
	    	+ "din urmatoarele\n");
	    Text f21 =  new Text("     acte normative: Legea nr.168/2020, Decretul-Lege nr.118/1990, Legea nr.44/1994, Legea nr.341/2004 sau O.G. nr.105/1999, "
	    		+ "scutirea\n");
	    Text f22 =  new Text("     se acorda incepand cu anul urmator depunerii actelor prin care se atesta situatia.\n");
	    footer1.add(f2);
	    footer1.add(PDFHelper.addTab());
	    footer1.add(f21);
	    footer1.add(PDFHelper.addTab());
	    footer1.add(f22);
	    Text f3 =  new Text("3 - Pentru beneficiarii Legii nr.44/2006 scutirea de acorda cu data de 1 a lunii urmatoare depunerii actelor prin care se atesta situatia.\n");
	    footer1.add(PDFHelper.addTab());
	    footer1.add(f3);
	    footer1.add(PDFHelper.addTab());
	    Text f4 =  new Text("Nota bene:").setUnderline();
	    Text f5 =  new Text(" nedepunerea dosarului complet cuprinzand toate documentele justificative acordarii scutirii de impozit pe cladire / teren / "
	    	+ "mijloc\n");
	    Text f51 =  new Text("de transport, poate atrage neacordarea scutirii.");
	    footer1.add(f4);
	    footer1.add(f5);
	    footer1.add(PDFHelper.addTab());
	    footer1.add(f51);
	    doc.add(footer1);
	    

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
	    document.getDocumentInfo().setTitle("DPR_" + tm);
	    document.setDefaultPageSize(PageSize.A4);
	    Document doc = new Document(document);
	    float width = doc.getPageEffectiveArea(PageSize.A4).getWidth();
	    doc.setFontSize(12f);
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
	    Text t1 = new Text("\n\nDECLARATIE PE PROPRIA RASPUNDERE\n\n").setBold();
	    titlu.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    titlu.setTextAlignment(TextAlignment.CENTER);
	    titlu.add(t1).addStyle(PDFHelper.bold12nr);
	    doc.add(titlu);
	    
	    
	    
	    
	    
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
  
    
    
}
