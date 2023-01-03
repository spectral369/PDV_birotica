package com.spectral369.instiintareIarba;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.spectral369.birotica.PdfList;
import com.spectral369.utils.FooterEvt;
import com.spectral369.utils.PDFHelper;
import com.spectral369.utils.Utils;

public class PDFIICreator {
    File pdff = null;
    private transient PdfDocument document;
    private transient PdfWriter writer;
    String id = Utils.getRandomStringSerial("II");

    public PDFIICreator(Map<String, String> map, String tm,boolean catreCheck, boolean sumeHide, boolean hclHide) {
	
	pdff = new File(Utils.getSaveFileLocation("Insiintare_Iarba_" + tm + ".pdf"));
	if (map.isEmpty()) {
	    this.generatePDF(tm, pdff, catreCheck,sumeHide,hclHide);
	    PdfList.addFile(id, pdff.getAbsolutePath());

	} else {

	    this.generatePDF(tm, pdff, map, catreCheck);
	    PdfList.addFile(id, pdff.getAbsolutePath());
	}
    }
    public String getID() {
	return this.id;
    }
    
   private void generatePDF(final String tm, final File pdfFile, Boolean catreCheck, Boolean sumeHide,
	    Boolean hclHide) {
	try {
	    writer =  new PdfWriter(pdfFile);
	    Image antetLogo = PDFHelper.getAntetLogo();
	    document = new PdfDocument(writer);
	    document.getDocumentInfo().addCreationDate();
	     document.getDocumentInfo().setAuthor("spectral369");
	     document.setDefaultPageSize(PageSize.A4);
	     document.getDocumentInfo().setTitle("Instiintare_plata_Iarba_100" + tm);
	     
	     Document doc =  new Document(document);
	     doc.setFontSize(11.5f);
	     float width = doc.getPageEffectiveArea(PageSize.A4).getWidth();
	     document.addEventHandler(PdfDocumentEvent.START_PAGE,new FooterEvt(width) );
	  
	     
	     Paragraph antet = new Paragraph();
	  

	     float documentWidth =    document.getDefaultPageSize().getWidth() - doc.getLeftMargin()- doc.getRightMargin();
	     float documentHeight =  document.getDefaultPageSize().getHeight() - doc.getTopMargin()- doc.getBottomMargin();
		    
	    antetLogo.scaleToFit(documentWidth, documentHeight);

	    antet.add(antetLogo);
	    antet.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    doc.add(antet);
	    
	    Paragraph serieData = new Paragraph();
	  
	    serieData.add(PDFHelper.addTab());
	   Text serie = new Text("Seria: " + Utils.getRandomStringSerial("IISP")+Calendar.getInstance().get(Calendar.YEAR));
	    serieData.add(serie);
	    /*
	     * serieData.add(Text.addTab()); Text data = new Text(" Data " +
	     * PDFHelper.getStrWithDots(22, "")); serieData.add(data);
	     */
	    if (catreCheck.equals(Boolean.FALSE)) {
		serieData.add("\n");
		//serieData.add("\n");
	    }
	    doc.add(serieData);

	    if (catreCheck.equals(Boolean.TRUE)) {
		final Paragraph catre = new Paragraph();
		
		catre.add(PDFHelper.addTab());
		final Text t1 = new Text("\nCatre: " + PDFHelper.getStrWithDots(80, "") + "\n\n");
		catre.add(t1);
		doc.add(catre);
	    }
	    final Paragraph subscrisa = new Paragraph();
	    
	    subscrisa.add(PDFHelper.addTab());
	    final Text subsemnat = new Text(
		    "Subscrisa Comuna Dudestii-Vechi prin Dl. Primar, cu sediul in comuna\n Dudestii-Vechi"
			    + "nr. 255, jud. Timis formulez prezenta\n\n");
	    subscrisa.add( subsemnat);
	    doc.add(subscrisa);
	    
	    final Paragraph title = new Paragraph("INSTIINTARE\n\n").setBold();
	    title.setTextAlignment(TextAlignment.CENTER);
	    doc.add(title);
	    
	    final Paragraph declaratie = new Paragraph();
	    declaratie.add(PDFHelper.addTab());
	    Text dec1 = new Text("In vederea aplicarii dispozitiilor H.C.L 41 din 03.08.2015 coroboranta cu "
		    + "H.C.L 63 din 13.07.2019 va instiintam ca inspectorul zonal a constatat "
		    + "neindeplinirea obligatiei de curatare a domeniului public(spatiu verde, materiale de constructii,"
		    + " moloz, fosa septica," + " zapada) aferent sau limitrof " + "imobilului situat in "
		    + PDFHelper.getStrWithDots(50, "") + " cu numarul " + PDFHelper.getStrWithDots(15, "")
		    + " . Va aducem la cunostinta ca aveti ");
	    Text zile = new Text("5 zile").setBold();
	    declaratie.add(dec1);
	    declaratie.add(zile);
	    Text dec12 = new Text(
		    " la dispozitie pentru solutionarea problemei in cauza. In caz contrar institutia noastra"
			    + " prin departamentul de specialitate va executa masurile necesare pentru curatarea domeniului public.\n");
	    declaratie.add(dec12);

	    declaratie.add(PDFHelper.addTab());
	    if (hclHide.equals(Boolean.TRUE)) {
		Text consta = new Text("S-a constatat ca:\n");
		declaratie.add(consta);
		/// Text oper = new Text( PDFHelper.getStrWithDots(624, "") + "\n");
		// 01
		Paragraph oper1 = new Paragraph();
		Text hcl1 = new Text("H.C.L. " + PDFHelper.getStrWithDots(10, ""));
		oper1.add(hcl1);
		Text art1 = new Text(" art. " + PDFHelper.getStrWithDots(10, ""));
		oper1.add(art1);
		Text alin1 = new Text(" alin. " + PDFHelper.getStrWithDots(10, "") + " . ");
		oper1.add(alin1);
		Text dots1 = new Text(PDFHelper.getStrWithDots(96, ""));
		oper1.add(dots1);
		oper1.add("\n");
		Text dots12 = new Text(PDFHelper.getStrWithDots(131, ""));
		oper1.add(dots12);
		if (sumeHide.equals(Boolean.TRUE)) {
		    Text suma1 = new Text(" Suma " + PDFHelper.getStrWithDots(14, ""));
		    oper1.add(suma1);
		} else {
		    oper1.add(PDFHelper.getStrWithDots(25, ""));
		}
		oper1.add("\n");
		declaratie.add(oper1);
		// 02
		Paragraph oper2 = new Paragraph();
		Text hcl2 = new Text("H.C.L. " + PDFHelper.getStrWithDots(10, ""));
		oper2.add(hcl2);
		Text art2 = new Text(" art. " + PDFHelper.getStrWithDots(10, ""));
		oper2.add(art2);
		Text alin2 = new Text(" alin. " + PDFHelper.getStrWithDots(10, "") + " . ");
		oper2.add(alin2);
		Text dots2 = new Text(PDFHelper.getStrWithDots(96, ""));
		oper2.add(dots2);
		oper2.add("\n");
		Text dots21 = new Text(PDFHelper.getStrWithDots(131, ""));
		oper2.add(dots21);
		if (sumeHide.equals(Boolean.TRUE)) {
		    Text suma2 = new Text(" Suma " + PDFHelper.getStrWithDots(14, ""));
		    oper2.add(suma2);
		} else {
		    oper2.add(PDFHelper.getStrWithDots(25, ""));
		}
		oper2.add("\n");
		declaratie.add(oper2);
		// 03
		Paragraph oper3 = new Paragraph();
		Text hcl3 = new Text("H.C.L. " + PDFHelper.getStrWithDots(10, ""));
		oper3.add(hcl3);
		Text art3 = new Text(" art. " + PDFHelper.getStrWithDots(10, ""));
		oper3.add(art3);
		Text alin3 = new Text(" alin. " + PDFHelper.getStrWithDots(10, "") + " . ");
		oper3.add(alin3);
		Text dots3 = new Text(PDFHelper.getStrWithDots(96, ""));
		oper3.add(dots3);
		oper3.add("\n");
		Text dots31 = new Text(PDFHelper.getStrWithDots(131, ""));
		oper3.add(dots31);
		if (sumeHide.equals(Boolean.TRUE)) {
		    Text suma3 = new Text(" Suma " + PDFHelper.getStrWithDots(14, ""));
		    oper3.add(suma3);
		} else {
		    oper3.add(PDFHelper.getStrWithDots(25, ""));
		}
		oper3.add("\n");
		declaratie.add(oper3);
		declaratie.add(PDFHelper.addTab());
		if (sumeHide.equals(Boolean.TRUE)) {
		    Paragraph tota = new Paragraph();
		    tota.setTextAlignment(TextAlignment.RIGHT);
		    Text totalSuma = new Text("Suma totala " + PDFHelper.getStrWithDots(18, ""));
		    tota.add(totalSuma);
		    tota.add("\n");
		    declaratie.add(tota);
		}
	    }
	    // Text valoare = new Text("100 de RON", bold2);
	    Text termen = new Text("30 de zile").setBold();
	    /*
	     * Phrase dec2 = new Phrase(); Text dec21 = new
	     * Text("Astfel ca aveti de achitat taxa speciala in cuantum de ");
	     * dec2.add(dec21); dec2.add(valoare); Text dec22 = new
	     * Text(" pentru acest serviciu in termen de "); dec2.add(dec22);
	     * dec2.add(termen); Text dec23 = new
	     * Text(" de la data comunicarii prezentei instiintari.\n\n"); dec2.add(dec23);
	     * 
	     * declaratie.add(dec2);
	     */
	    Paragraph dec2 = new Paragraph();
	    dec2.add(PDFHelper.addTab());
	    Text dec9 = new Text("Cuantumul datorat se poate achita in termen de ");
	    dec2.add(dec9);
	    dec2.add(termen);
	    Text dec91 = new Text(" de la data comunicarii prezentei instiintari.");
	    dec2.add(dec91);
	    dec2.add("\n");
	    if (sumeHide.equals(Boolean.TRUE)) {
		declaratie.add(dec2);
	    }
	    declaratie.add(PDFHelper.addTab());
	    Text cazcontrar = new Text(
		    "La a-2a abatere se aplica amenda contraventionala in suma de 200 lei pana la 2500 lei, "
			    + "conform H.C.L 41 din 03.08.2015 alin. 4 art. 10 .\n");
	    declaratie.add(cazcontrar);
	    declaratie.add(PDFHelper.addTab());
	    Text dec3 = new Text(
		    "Totodata va aducem la cunostiinta ca in baza noastra de taxe si impozite a fost\n introdus cuantumul acestei taxe.\n\n");
	    if (sumeHide.equals(Boolean.TRUE)) {
		declaratie.add(dec3);
	    }
	    doc.add(declaratie);

	    Table semnaturi = new Table(1);

	    semnaturi.setHorizontalAlignment(HorizontalAlignment.CENTER);
	    Paragraph p1 = new Paragraph();
	    Text primar = new Text("Data");
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
	    String dateNow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	    Text semnPrim = new Text(dateNow);
	    //semnPrim.setUnderline();
	    semnR.add(semnPrim);
	    semnR.addTabStops(new TabStop(width / 1.18f, TabAlignment.RIGHT));
	    semnR.add(new Tab());
	    Text semnIntocmit = new Text(PDFHelper.getStrWithDots(30, ""));
	    //semnIntocmit.setUnderline();
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

    private void generatePDF(final String tm, final File pdfFile, final Map<String, String> map, Boolean catreCheck) {
	/*try {
	    Image antetLogo =  getAntetLogo();
	    document = new Document();
	    document.addAuthor("spectral369");
	    document.addCreationDate();
	    document.addHeader("Page count", String.valueOf(document.getPageNumber() + 1));
	    document.setPageSize(PageSize.A4);
	    writer = PdfWriter.getInstance(document, (OutputStream) new FileOutputStream(pdfFile));
	    document.open();
	    document.addTitle("Instiintare_plata_Iarba_100" + tm);
	    final Paragraph antet = new Paragraph();
	    Font bold2 = FontFactory.getFont("Times-Roman", 12.0f, 1);

	    float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
	    float documentHeight = document.getPageSize().getHeight() - document.topMargin() - document.bottomMargin();
	    antetLogo.scaleToFit(documentWidth, documentHeight);

	    antet.add(antetLogo);
	    antet.setAlignment(1);
	    document.add(antet);
	    if (catreCheck.equals(Boolean.TRUE)) {
		final Paragraph catre = new Paragraph();
		catre.setLeading(20.0f);
		catre.setTabSettings(new TabSettings(15.0f));
		catre.add(Text.addTab());
		Phrase t1 = new Phrase();
		Text t11 = new Text("\n\n\n\nCatre: ");
		t1.add(t11);
		t1.add(getPhraseStrWithDots(80, map.get("prenume") + " " + map.get("nume")));
		Text t12 = new Text(".\n\n\n");
		t1.add(t12);
		catre.add(t1);
		document.add(catre);
	    }
	    final Paragraph subscrisa = new Paragraph();
	    subscrisa.setLeading(20.0f);
	    subscrisa.setTabSettings(new TabSettings(15.0f));
	    subscrisa.add(Text.addTab());
	    final Text subsemnat = new Text(
		    "Subscrisa Comuna Dudestii-Vechi prin Dl. Primar, cu sediul in comuna\n Dudestii-Vechi"
			    + "nr. 255, jud. Timis formulez prezenta\n\n");
	    subscrisa.add((Element) subsemnat);
	    document.add(subscrisa);
	    final Paragraph title = new Paragraph("INSTIINTARE\n\n", bold2);
	    title.setAlignment(1);
	    document.add(title);
	    final Paragraph declaratie = new Paragraph();
	    declaratie.setLeading(20.0f);
	    declaratie.setTabSettings(new TabSettings(15.0f));
	    declaratie.add(Text.addTab());
	    Phrase dec1 = new Phrase();
	    Text dec11 = new Text("In vederea aplicarii dispozitiilor H.C.L 41 din 03.08.2015 coroboranta cu "
		    + "H.C.L 63 din 13.07.2019 va instiintam ca inspectorul zonal a constatat "
		    + "neindeplinirea obligatiei de intretinere a spatiului verde aferent sau limitrof "
		    + "imobilului situat in");
	    dec1.add(dec11);
	    dec1.add(getPhraseStrWithDots(25, map.get("localitate")));
	    Text dec12 = new Text(" cu numarul ");
	    dec1.add(dec12);
	    dec1.add(getPhraseStrWithDots(8, map.get("nrStrada")));
	    Text dec13 = new Text(" sens in care "
		    + "institutia noastra, prin compartimentul de specialitate a procedat la prestarea serviciilor de "
		    + "curatare si intretinere a acestor spatii verzi.\n\n");
	    dec1.add(dec13);
	    declaratie.add(dec1);
	    declaratie.add(Text.addTab());
	    Text valoare = new Text("100 de RON", bold2);
	    Text termen = new Text("30 de zile", bold2);
	    Phrase dec2 = new Phrase();
	    Text dec21 = new Text("Astfel ca aveti de achitat taxa speciala in cuantum de ");
	    dec2.add(dec21);
	    dec2.add(valoare);
	    Text dec22 = new Text(" pentru acest serviciu in termen de ");
	    dec2.add(dec22);
	    dec2.add(termen);
	    Text dec23 = new Text(" de la data comunicarii prezentei instiintari.\n\n");
	    dec2.add(dec23);

	    declaratie.add(dec2);
	    declaratie.add(Text.addTab());
	    Text dec3 = new Text(
		    "Totodata va aducem la cunostiinta ca in baza noastra de taxe si impozite a fost\n introdus cuantumul acestei taxe.");
	    declaratie.add(dec3);
	    document.add(declaratie);

	    final Paragraph dataSiSemnatura = new Paragraph();
	    dataSiSemnatura.setLeading(25.0f);
	    dataSiSemnatura.setTabSettings(new TabSettings(300.0f));
	    dataSiSemnatura.setIndentationLeft(300.0f);
	    final Text semnatura = new Text("Primar\n");
	    dataSiSemnatura.add((Element) semnatura);
	    final Text semn = new Text(PDFHelper.getStrWithDots(30, ""));
	    dataSiSemnatura.add((Element) semn);
	    final PdfContentByte canvas = writer.getDirectContent();
	    document.add((Element) dataSiSemnatura);
	    final Phrase footer = new Phrase(
		    String.valueOf(document.getPageNumber() + 1) + "/" + writer.getPageNumber());
	    ColumnText.showTextAligned(canvas, 0, footer, writer.getPageSize().getWidth() / 2.0f, 0.0f, 0.0f);
	    document.close();
	} catch (DocumentException ex) {
	} catch (FileNotFoundException ex2) {
	}
	writer.flush();*/
    }

 
}
