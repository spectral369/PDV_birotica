package com.spectral369.CEAF;

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

public class PDFCEAFCreator {
    File pdff = null;
    private transient PdfDocument document;
    private transient PdfWriter writer;
    String id = Utils.getRandomStringSerial("CEAF");

    public PDFCEAFCreator(Map<String, String> map, String tm) {

	pdff = new File(Utils.getSaveFileLocation("CEAF_" + tm + ".pdf"));
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
	    document.getDocumentInfo().setTitle("Cerere_Autorizatie_Functionare" + tm);
	    document.setDefaultPageSize(PageSize.A4);
	    Document doc = new Document(document);
	    doc.setFontSize(11.5f);
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

	    Table table1 =  new Table(1);
		   table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
		    Paragraph l1 =  new  Paragraph();
		    Text nrI = new Text(
				    "\n\nNr. " +PDFHelper.getStrWithDots(15, "") + " " + "data " + PDFHelper.getStrWithDots(20, ""));
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
		    

	     Paragraph titlu = new Paragraph();

	    Text t1 = new Text("\n\n\n\nCERERE\n").setBold();
	    t1.setTextAlignment(TextAlignment.CENTER);
	    titlu.add(t1);
	    Text t11 = new Text(
		    "pentru emiterea acordului de functionare pentru exercitii comerciale in baza legii 650/2002\n\n\n");
	    titlu.add(t11);
	    titlu.setTextAlignment(TextAlignment.CENTER);
	    doc.add(titlu);
	
	    final Paragraph declaratie = new Paragraph();
	   declaratie.add(PDFHelper.addTab());
	    Text dec1 = new Text("Subsemnatul " + PDFHelper.getStrWithDots(55, "") + " cu domiciliul in "
		    + PDFHelper.getStrWithDots(40, "") + "\nstrada " + PDFHelper.getStrWithDots(20, "") + " numarul "
		    + PDFHelper.getStrWithDots(8, "") + " bloc " + PDFHelper.getStrWithDots(8, "") + " scara "
		    + PDFHelper.getStrWithDots(8, "") + " apartament " + PDFHelper.getStrWithDots(8, "") + " telefon "
		    + PDFHelper.getStrWithDots(24, "") + "\nin calitate de " + PDFHelper.getStrWithDots(20, "")
		    + " al S.C/P.F/I.I/I.F " + PDFHelper.getStrWithDots(25, "") + " cu sediul in "
		    + PDFHelper.getStrWithDots(35, "") + "\nstrada " + PDFHelper.getStrWithDots(20, "") + " numarul "
		    + PDFHelper.getStrWithDots(8, "") + " bloc " + PDFHelper.getStrWithDots(8, "") + " bloc "
		    + PDFHelper.getStrWithDots(8, "") + " scara " + PDFHelper.getStrWithDots(8, "") + " apartament "
		    + PDFHelper.getStrWithDots(8, "") + " inmatriculat\nla ORC " + PDFHelper.getStrWithDots(10, "")
		    + " C.U.I " + PDFHelper.getStrWithDots(12, "")
		    + " solicit obtinerea acordului Primariei comunei Dudestii-Vechi pentru desfasurarea la luctul de lucru situat"
		    + " in " + PDFHelper.getStrWithDots(25, "") + " numarul " + PDFHelper.getStrWithDots(8, "")
		    + " bloc " + PDFHelper.getStrWithDots(8, "") + " scara " + PDFHelper.getStrWithDots(8, "")
		    + "\napartament " + PDFHelper.getStrWithDots(8, "") + " a urmatoarelor exercitii comerciale "
		    + "conform cu Legea 650/2002.\n\n");
	    declaratie.add(dec1);

	    Paragraph declaratie2 = new Paragraph();
	    declaratie2.add(PDFHelper.addTab());

	    Text dec11 = new Text("Cod CAEN " + PDFHelper.getStrWithDots(25, "") + " Denumire "
		    + PDFHelper.getStrWithDots(80, "") + "\n");
	    declaratie2.add(dec11);
	    declaratie2.add(PDFHelper.addTab());
	    Text dec12 = new Text("Cod CAEN " + PDFHelper.getStrWithDots(25, "") + " Denumire "
		    + PDFHelper.getStrWithDots(80, "") + "\n");
	    declaratie2.add(dec12);
	    declaratie2.add(PDFHelper.addTab());
	    Text dec13 = new Text("Cod CAEN " + PDFHelper.getStrWithDots(25, "") + " Denumire "
		    + PDFHelper.getStrWithDots(80, "") + "\n");
	    declaratie2.add(dec13);
	    declaratie2.add(PDFHelper.addTab());
	    Text dec14 = new Text("Cod CAEN " + PDFHelper.getStrWithDots(25, "") + " Denumire "
		    + PDFHelper.getStrWithDots(80, "") + "\n");
	    declaratie2.add(dec14);
	    declaratie2.add(PDFHelper.addTab());
	    Text dec15 = new Text("Cod CAEN " + PDFHelper.getStrWithDots(25, "") + " Denumire "
		    + PDFHelper.getStrWithDots(80, "") + "\n\n\n");
	    declaratie2.add(dec15);

	    Paragraph orar1 = new Paragraph();
	    orar1.add(PDFHelper.addTab());
	    Text ora1 = new Text(
		    "Orarul de functionare propus: Luni-Vineri " + PDFHelper.getStrWithDots(25, "") + "\n");
	    orar1.add(ora1);

	    Paragraph orar2 = new Paragraph();
	    orar2.add(PDFHelper.addTab());
	    orar2.add(PDFHelper.addTab());
	    orar2.add(PDFHelper.addTab());
	    orar2.add(PDFHelper.addTab());
	    orar2.add(PDFHelper.addTab());
	    orar2.add(PDFHelper.addTab());
	    Text ora2 = new Text("Sambata " + PDFHelper.getStrWithDots(25, "") + "\n");
	    orar2.add(ora2);

	    Paragraph orar3 = new Paragraph();
	    orar2.add(PDFHelper.addTab());
	    orar2.add(PDFHelper.addTab());
	    orar2.add(PDFHelper.addTab());
	    orar2.add(PDFHelper.addTab());
	    orar2.add(PDFHelper.addTab());
	    orar2.add(PDFHelper.addTab());
	    Text ora3 = new Text("Duminica " + PDFHelper.getStrWithDots(25, "") + "\n");
	    orar3.add(ora3);

	    doc.add(declaratie);
	    doc.add(declaratie2);
	    doc.add(orar1);
	    doc.add(orar2);
	    doc.add(orar3);
	   
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
	    document.getDocumentInfo().setTitle("Cerere_Autorizatie_Functionare" + tm);
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

	    Table table1 =  new Table(1);
		   table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
		    Paragraph l1 =  new  Paragraph();
		    Text nrI = new Text(
				    "\n\nNr. " +PDFHelper.getStrWithDots(15, "") + " " + "data " + PDFHelper.getStrWithDots(20, ""));
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
		    

	     Paragraph titlu = new Paragraph();

	    Text t1 = new Text("\n\n\n\nCERERE\n").setBold();
	    t1.setTextAlignment(TextAlignment.CENTER);
	    titlu.add(t1);
	    Text t11 = new Text(
		    "pentru emiterea acordului de functionare pentru exercitii comerciale in baza legii 650/2002\n\n\n");
	    titlu.add(t11);
	    titlu.setTextAlignment(TextAlignment.CENTER);
	    doc.add(titlu);
	    //
	    
	    //ne completabil
	    //
	
	    doc.close();
	    document.close();
	    writer.flush();
	
	} catch (IOException ex2) {
	}
	
    }

}
