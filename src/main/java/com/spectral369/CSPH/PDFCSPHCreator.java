package com.spectral369.CSPH;


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

public class PDFCSPHCreator {

    
    File pdff = null;
    private transient PdfDocument document;
    private transient PdfWriter writer;
    String id = Utils.getRandomStringSerial("CSPH");

    public PDFCSPHCreator(Map<String, String> map, String tm) {
	
	pdff = new File(Utils.getSaveFileLocation("Cerere_Scutire_Persoane_Handicap" + tm + ".pdf"));
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
	   
	    document = new PdfDocument(writer);
	    document.getDocumentInfo().addCreationDate();
	    document.getDocumentInfo().setAuthor("spectral369");
	    document.getDocumentInfo().setTitle("CSPH_" + tm);
	    document.setDefaultPageSize(PageSize.A4);
	    Document doc = new Document(document);
	    doc.setFontSize(11.5f);
	    float width = doc.getPageEffectiveArea(PageSize.A4).getWidth();
	    document.addEventHandler(PdfDocumentEvent.START_PAGE, new FooterEvt(width));

	    final Paragraph nrInreg = new Paragraph();
	  
	  //  nrInreg.add(new Tab());
	    Text nrI = new Text(
		    "CERERE SCUTIRE PENTRU PERSOANELE \n"
		    + "CU GRAD DE HANDICAP Nr. " + PDFHelper.getStrWithDots(15, "") + " " + "data " + PDFHelper.getStrWithDots(20, "")+"\n\n").setBold();
	    nrInreg.add(nrI);
	    nrInreg.setTextAlignment(TextAlignment.CENTER);
	    doc.add(nrInreg);

	    final Paragraph declaratie = new Paragraph();
	    declaratie.add(PDFHelper.addTab());
	    Text dec1 = new Text("Subsemnatul/a " + PDFHelper.getStrWithDots(100, "") + " domiciliat/a \nin "
		    + PDFHelper.getStrWithDots(135, "") + " legitimat/a cu C.I seria "
		    + PDFHelper.getStrWithDots(10, "") + " nr. " + PDFHelper.getStrWithDots(15, "") + " avand C.N.P "
		    + PDFHelper.getStrWithDots(55, "") + " telefon "+PDFHelper.getStrWithDots(20, ""));
	    declaratie.add(dec1);
	    
	    Text dec11 = new Text(" solicit scutirea impozitului/taxei pe cladire si teren<curti constructii> la imobilul de domiciliu si mijlocul de transport"
	    	+ " marca "+PDFHelper.getStrWithDots(35, "")+" conform art.456 alin. 1 lit. t; art. 454 alin. 1 lit. t, si/sau art. 469 alin. 1 lit. b din "
	    		+ "Legea Nr. 227/2015 privind Codul Fiscal cu modificarile si completarile ulterioare. Declar ca imobilul ");
	    declaratie.add(dec11);
	    Text dec11a =  new Text("[este] / [nu este] ").setUnderline();
	    declaratie.add(dec11a);
	    Text dec11b =  new Text("folosit in scop economic sau de agrement.\n");
	    declaratie.add(dec11b);
	    declaratie.add(PDFHelper.addTab());
	    Text dec111 =  new Text("Mentionez ca sunt(dupa caz) : [  ] persoana cu handicap grav sau accentuat; [  ] persoana incadrata in gradul I de invaliditate; "
	    		+ "[  ] reprezentant legal al persoanei cu handicap grav/ accentuat sau incadrata im grad I de invaliditate.\n");
	    declaratie.add(dec111);

	    
	    declaratie.add(PDFHelper.addTab());
	    Text dec12 = new Text("Anexez prezentei copii ale documentelor justificative, certificate in conformitate cu originalul:\n");
	    declaratie.add(dec12);
	    Text anex01 = new Text(" [  ] - Buletin / carte de indentitate(proprietar/coproprietar).\n");
	    declaratie.add(anex01);
	    Text anex02 =  new Text(" [  ] - Act proprietate / coproprietate.\n");
	    declaratie.add(anex02);
	    Text anex03 = new Text(" [  ] - Certificat de incadrare in grad de handicap.\n");
	    declaratie.add(anex03);
	    Text anex04 =  new Text(" [  ] - Hotararea Judecatoreasca definitiva de instituire a reprezentantului legal(dupa caz).\n");
	    declaratie.add(anex04);
	    Text anex05 =  new Text(" [  ] - Cartea de Indentitate a autovehiculului.\n");
	    declaratie.add(anex05);
	    Text anex06 =  new Text(" [  ] - Certificat de nastere minor, carte de indentitate parinti / reprezentant legal.\n");
	    declaratie.add(anex06);
	    Text anex07 =  new Text(" [  ] - contract de inchiriere / comodat pentru suprafata de "+PDFHelper.getStrWithDots(15, "")+" mp. (dupa caz).\n\n" );
	    declaratie.add(anex07);
	    
	    declaratie.add(PDFHelper.addTab());
	    Text dec13 = new Text("Scutirea se acorda integral pentru cladirea folosita ca domiciliu(rezidenta), terenul din categoria curti-contructii aferent acestia "
	    	+ "si un singur mijloc de transport la alegere, aflate in proprietatea sau coproprietatea (sot/sotie), persoanelor cu handicap grav sau accentuat si a persoanelor"
	    	+ " incadrate in gradul I de invaliditate, respectiv a reprezentantilor legali, pe perioada in care au in ingrijire, supraveghere si intretinere persoane cu"
	    	+ " handicap grav sau accentuat si persoane in gradul I de invaliditate. In situatia in care o cota-parte apartine unor terti, scutirea nu se acorda pentru"
	    	+ " cota-parte detinuta de acestea.\n");
	    dec13.setFontSize(9.5f);
	    declaratie.add(dec13);
	    declaratie.add(PDFHelper.addTab());
	    Text dec14 =  new Text("Scutirea de la plata impozitului / taxei  se acorda incepand cu data de 1 a lunii urmatoare depunerii cererii, pe baza "
	    	+ "documentelor justificative, certificate in conformitate cu orginalul(art.64 alin. 5 din Legea nr.207/2015 priviind Codul de Procedura Fiscala), care atesta "
	    	+ "situatia respectiva, cu exceptia contribuabililor care sunt deja inscrisi in baza de data a U.A.T Dudestii-vechi.\n");
	    dec14.setFontSize(9.5f);
	    declaratie.add(dec14);
	    declaratie.add(PDFHelper.addTab());
	    Text dec15 =  new Text("Daca intervin schimbari care conduc la modificarea conditiilor in care se acorda scutirile de impozite / taxe, persoanele in cauza trebuie sa depuna "
	    	+ "noi declaratii fiscale in termen de 45 de zile de la aparitia schimbarilor.\n");
	    dec15.setFontSize(9.5f);
	    declaratie.add(dec15);
	    declaratie.add(PDFHelper.addTab());
	    Text dec2 = new Text("Subsemnatul/a " + PDFHelper.getStrWithDots(52, "")
		    + " ,declar ca sunt de acord si imi"
		    + " exprim consimtamantul in mod express, neechivoc, liber si informat cu privire la prelucrarea datelor "
		    + "mele cu caracter personal, conform prevederilor Regulamentului(U.E) 679/2016 privind protectia "
		    + "persoanelor fizice in ceea ce priveste prelucrarea datelor cu caracter personal si privind libera "
		    + "circulatie a acestora, pentru a fi colectate, folosite si prelucrate (nume, prenume, C.N.P, adresa "
		    + "postala, adresa de e-mail, nr. de telefon, copie carte de indentitate, componenta familiei, extras "
		    + "de cont bancar, etc) de catre U.A.T Dudestii-Vechi in vederea indeplinirilor atributiilor legale ale "
		    + " acestei institutii .\n");
	    dec2.setFontSize(9.5f);
	    declaratie.add(dec2);
	    declaratie.add(PDFHelper.addTab());
	    Text dec3 = new Text("Am luat la cunostiinta de drepturile legale pe care le am odata cu prelucrarea, "
		    + "colectarea si folosirea datelor cu caracter personal conform informarii comunicate de catre operator.\n\n\n");
	    dec3.setFontSize(9.5f);
	    declaratie.add(dec3);
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
	   
	    document = new PdfDocument(writer);
	    document.getDocumentInfo().addCreationDate();
	    document.getDocumentInfo().setAuthor("spectral369");
	    document.getDocumentInfo().setTitle("CSPH_" + tm);
	    document.setDefaultPageSize(PageSize.A4);
	    Document doc = new Document(document);
	    doc.setFontSize(11.5f);
	    float width = doc.getPageEffectiveArea(PageSize.A4).getWidth();
	    document.addEventHandler(PdfDocumentEvent.START_PAGE, new FooterEvt(width));

	    final Paragraph nrInreg = new Paragraph();
	  
	  //  nrInreg.add(new Tab());
	    Text nrI = new Text(
		    "CERERE SCUTIRE PENTRU PERSOANELE \n"
		    + "CU GRAD DE HANDICAP Nr. " + PDFHelper.getStrWithDots(15, "") + " " + "data " + PDFHelper.getStrWithDots(20, "")+"\n\n").setBold();
	    nrInreg.add(nrI);
	    nrInreg.setTextAlignment(TextAlignment.CENTER);
	    doc.add(nrInreg);

	    final Paragraph declaratie = new Paragraph();
	    declaratie.add(PDFHelper.addTab());
	    final String titlu = map.get("titlu").toLowerCase().contains("dl") ? "Subsemnatul " : "Subsemnata ";
	    final String domiciliu = map.get("titlu").toLowerCase().contains("dl") ? "domiciliat " : "domiciliata: ";
	    final String legitimat = map.get("titlu").toLowerCase().contains("dl") ? "legitimat " : "legitimata ";
	    declaratie.add(titlu);
	    declaratie.add(PDFHelper.createAdjustableParagraph(50,
		    new Paragraph(map.get("nume")).setBold().setTextAlignment(TextAlignment.CENTER) ).setFixedLeading(11));
	    declaratie.add(" "+domiciliu+"in ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(60, new Paragraph(map.get("localitate")+" Nr."+
	    map.get("nrStrada")).setBold().setTextAlignment(TextAlignment.CENTER) ).setFixedLeading(11));
	    declaratie.add(" "+legitimat+"cu");
	    declaratie.add(new Text(" C.I ").setBold());
	    declaratie.add(" seria ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(10, new Paragraph(map.get("serie")).setBold().setTextAlignment(TextAlignment.CENTER) ).setFixedLeading(11));
	    declaratie.add(" nr. ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(13, new Paragraph(map.get("nrSerie")).setBold().setTextAlignment(TextAlignment.CENTER) ).setFixedLeading(11));
	    declaratie.add(" avand C.N P ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(38, new Paragraph(map.get("cnp")).setBold().setTextAlignment(TextAlignment.CENTER) ).setFixedLeading(11));
	    declaratie.add(" telefon ");
	    declaratie.add(PDFHelper.createAdjustableParagraph(18, new Paragraph(map.get("telefon")).setBold().setTextAlignment(TextAlignment.CENTER) ).setFixedLeading(11));
	    
	    
	   Paragraph dec11 = new Paragraph(" solicit scutirea impozitului/taxei pe cladire si teren<curti constructii> la imobilul de domiciliu si mijlocul de transport"
	    	+ " marca ");
		   dec11.add(PDFHelper.createAdjustableParagraph(20, new Paragraph(map.get("marcaAuto")).setBold().setTextAlignment(TextAlignment.CENTER) ).setFixedLeading(11));
		  dec11.add(" conform art.456 alin. 1 lit. t; art. 454 alin. 1 lit. t, si/sau art. 469 alin. 1 lit. b din "
	    		+ "Legea Nr. 227/2015 privind Codul Fiscal cu modificarile si completarile ulterioare. Declar ca imobilul ");
	  //  declaratie.add(dec11);
	    Text dec11a =  new Text("[este] / [nu este] ").setUnderline();
	    dec11.add(dec11a);
	    Text dec11b =  new Text("folosit in scop economic sau de agrement.\n");
	    dec11.add(dec11b);
	    declaratie.add(dec11);
	    declaratie.add(PDFHelper.addTab());
	    Text dec111 =  new Text("Mentionez ca sunt(dupa caz) : [  ] persoana cu handicap grav sau accentuat; [  ] persoana incadrata in gradul I de invaliditate; "
	    		+ "[  ] reprezentant legal al persoanei cu handicap grav/ accentuat sau incadrata im grad I de invaliditate.\n");
	    declaratie.add(dec111);
	    
	    declaratie.add(PDFHelper.addTab());
	    Text dec12 = new Text("Anexez prezentei copii ale documentelor justificative, certificate in conformitate cu originalul:\n");
	    declaratie.add(dec12);
	    Text anex01 = new Text(" [  ] - Buletin / carte de indentitate(proprietar/coproprietar).\n");
	    declaratie.add(anex01);
	    Text anex02 =  new Text(" [  ] - Act proprietate / coproprietate.\n");
	    declaratie.add(anex02);
	    Text anex03 = new Text(" [  ] - Certificat de incadrare in grad de handicap.\n");
	    declaratie.add(anex03);
	    Text anex04 =  new Text(" [  ] - Hotararea Judecatoreasca definitiva de instituire a reprezentantului legal(dupa caz).\n");
	    declaratie.add(anex04);
	    Text anex05 =  new Text(" [  ] - Cartea de Indentitate a autovehiculului.\n");
	    declaratie.add(anex05);
	    Text anex06 =  new Text(" [  ] - Certificat de nastere minor, carte de indentitate parinti / reprezentant legal.\n");
	    declaratie.add(anex06);
	    Text anex07 =  new Text(" [  ] - contract de inchiriere / comodat pentru suprafata de "+PDFHelper.getStrWithDots(15, "")+" mp. (dupa caz).\n\n" );
	    declaratie.add(anex07);
	    
	    declaratie.add(PDFHelper.addTab());
	    Text dec13 = new Text("Scutirea se acorda integral pentru cladirea folosita ca domiciliu(rezidenta), terenul din categoria curti-contructii aferent acestia "
	    	+ "si un singur mijloc de transport la alegere, aflate in proprietatea sau coproprietatea (sot/sotie), persoanelor cu handicap grav sau accentuat si a persoanelor"
	    	+ " incadrate in gradul I de invaliditate, respectiv a reprezentantilor legali, pe perioada in care au in ingrijire, supraveghere si intretinere persoane cu"
	    	+ " handicap grav sau accentuat si persoane in gradul I de invaliditate. In situatia in care o cota-parte apartine unor terti, scutirea nu se acorda pentru"
	    	+ " cota-parte detinuta de acestea.\n");
	    dec13.setFontSize(9.5f);
	    declaratie.add(dec13);
	    declaratie.add(PDFHelper.addTab());
	    Text dec14 =  new Text("Scutirea de la plata impozitului / taxei  se acorda incepand cu data de 1 a lunii urmatoare depunerii cererii, pe baza "
	    	+ "documentelor justificative, certificate in conformitate cu orginalul(art.64 alin. 5 din Legea nr.207/2015 priviind Codul de Procedura Fiscala), care atesta "
	    	+ "situatia respectiva, cu exceptia contribuabililor care sunt deja inscrisi in baza de data a U.A.T Dudestii-vechi.\n");
	    dec14.setFontSize(9.5f);
	    declaratie.add(dec14);
	    declaratie.add(PDFHelper.addTab());
	    Text dec15 =  new Text("Daca intervin schimbari care conduc la modificarea conditiilor in care se acorda scutirile de impozite / taxe, persoanele in cauza trebuie sa depuna "
	    	+ "noi declaratii fiscale in termen de 45 de zile de la aparitia schimbarilor.\n");
	    dec15.setFontSize(9.5f);
	    declaratie.add(dec15);
	    declaratie.add(PDFHelper.addTab());
	    Text dec2 = new Text("Subsemnatul/a " + PDFHelper.getStrWithDots(52, "")
		    + " ,declar ca sunt de acord si imi"
		    + " exprim consimtamantul in mod express, neechivoc, liber si informat cu privire la prelucrarea datelor "
		    + "mele cu caracter personal, conform prevederilor Regulamentului(U.E) 679/2016 privind protectia "
		    + "persoanelor fizice in ceea ce priveste prelucrarea datelor cu caracter personal si privind libera "
		    + "circulatie a acestora, pentru a fi colectate, folosite si prelucrate (nume, prenume, C.N.P, adresa "
		    + "postala, adresa de e-mail, nr. de telefon, copie carte de indentitate, componenta familiei, extras "
		    + "de cont bancar, etc) de catre U.A.T Dudestii-Vechi in vederea indeplinirilor atributiilor legale ale "
		    + " acestei institutii .\n");
	    dec2.setFontSize(9.5f);
	    declaratie.add(dec2);
	    declaratie.add(PDFHelper.addTab());
	    Text dec3 = new Text("Am luat la cunostiinta de drepturile legale pe care le am odata cu prelucrarea, "
		    + "colectarea si folosirea datelor cu caracter personal conform informarii comunicate de catre operator.\n\n\n");
	    dec3.setFontSize(9.5f);
	    declaratie.add(dec3);
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
  
    
    
}
