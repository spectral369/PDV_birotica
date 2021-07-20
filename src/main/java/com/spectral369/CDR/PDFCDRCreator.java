package com.spectral369.CDR;

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
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.spectral369.birotica.PdfList;
import com.spectral369.utils.PDFHelper;
import com.spectral369.utils.Utils;

public class PDFCDRCreator {

    File pdff = null;
    private transient PdfDocument document;
    private transient PdfWriter writer;
    String id = Utils.getRandomStringSerial("CDR");

    public PDFCDRCreator(Map<String, String> map, String tm) {

	pdff = new File(Utils.getSaveFileLocation("Cerere_de_restituire_" + tm + ".pdf"));
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

    private void generatePDF(String tm, File pdfFile) {
	try {

	    writer = new PdfWriter(pdfFile);
	 
	    document = new PdfDocument(writer);

	    document = new PdfDocument(writer);
	    document.getDocumentInfo().addCreationDate();
	    document.getDocumentInfo().setAuthor("spectral369");

	    document.getDocumentInfo().setTitle("Cerere de restituire_" + tm);
	    document.setDefaultPageSize(PageSize.A4);
	

	    Document doc = new Document(document);
	    doc.setFontSize(10.6f);
	    float width = doc.getPageEffectiveArea(PageSize.A4).getWidth();
	    document.addEventHandler(PdfDocumentEvent.START_PAGE, new handleEvt(width));


	    Paragraph title = new Paragraph();
	    Text t1 = new Text("CERERE DE RESTITUIRE\n").setBold();
	    t1.setTextAlignment(TextAlignment.CENTER);
	    title.add(t1);
	    title.setTextAlignment(TextAlignment.CENTER);
	    doc.add(title);

	    Paragraph title2 = new Paragraph();
	    Text t2 = new Text(
		    "a unor sume reprezentand taxe sau alte venituri ale bugetului de stat, platite in plus sau\n"
			    + "necuvenit si pentru care nu exista obligatia de declarare\n\n").setBold();
	    t2.setTextAlignment(TextAlignment.CENTER);
	    title2.add(t2);
	    title2.setTextAlignment(TextAlignment.CENTER);
	    doc.add(title2);

	    Paragraph p1 = new Paragraph();

	    Text p1c = new Text("Nr. " +PDFHelper.getStrWithDots(40, "") + " din data de " + PDFHelper.getStrWithDots(40, "") + " \n"
		    + "Catre" + PDFHelper.getStrWithDots(65, "") + " *1)/" + PDFHelper.getStrWithDots(55, "") + "*2)\n\n");
	    p1.add(p1c);
	    p1.setTextAlignment(TextAlignment.LEFT);
	    doc.add(p1);

	    Paragraph info1 = new Paragraph();
	    info1.add(PDFHelper.addTab());
	    Text line1 = new Text("Prin prezenta, " + PDFHelper.getStrWithDots(90, "") + "*3), cu sediul/domiciliul in "
		    + "localitatea " + PDFHelper.getStrWithDots(65, "") + ", str. " + PDFHelper.getStrWithDots(40, "") + "nr. " + ""
		    + PDFHelper.getStrWithDots(15, "") + "\nbl. " + PDFHelper.getStrWithDots(5, "") + ", sc. " + "" + PDFHelper.getStrWithDots(5, "")
		    + ", ap. " + PDFHelper.getStrWithDots(5, "") + ", judetul/sectorul " + "" + "" + PDFHelper.getStrWithDots(25, "")
		    + ", avand C.U.I/C.N.P. " + PDFHelper.getStrWithDots(50, "") + "" + ", administrat de "
		    + PDFHelper.getStrWithDots(70, "") + "*4), si arondat unitatii Trezoreriei\nStatului " + ""
		    + PDFHelper.getStrWithDots(70, "") + "*5), rog a mi se  restitui suma de " + PDFHelper.getStrWithDots(20, "") + ""
		    + "*6), reprezentand" + PDFHelper.getStrWithDots(80, "") + "*7)achitata in data de "
		    + PDFHelper.getStrWithDots(20, "") + "" + ", avand in vedere faptul ca " + PDFHelper.getStrWithDots(70, "") + "*8).\n");

	    info1.add(line1);
	    Text line2 = new Text("Restituirea va fi efectuata in numerar/in contul " + PDFHelper.getStrWithDots(70, "") + ""
		    + " *9)\ncod IBAN " + PDFHelper.getStrWithDots(55, "") + "*10), deschis la " + PDFHelper.getStrWithDots(50, "")
		    + "*11).\n\n");
	    info1.add(PDFHelper.addTab());
	    info1.add(line2);

	    doc.add(info1);

	    Paragraph sign = new Paragraph();
	    Text sn1 = new Text("Semnatura\n");
	    sn1.setUnderline(1.0f, -2f);
	    Text sn2 = new Text(PDFHelper.getStrWithDots(45, "") + "\n");
	    Text sn3 = new Text("L.S(daca este cazul)\n\n");
	    sn3.setUnderline(1.0f, -2f);
	    sign.add(PDFHelper.addTab());
	    sign.add(PDFHelper.addTab());
	    sign.add(sn1);
	    sign.add(PDFHelper.addTab());
	    sign.add(PDFHelper.addTab());
	    sign.add(sn2);
	    sign.add(PDFHelper.addTab());
	    sign.add(PDFHelper.addTab());
	    sign.add(sn3);
	    sign.setTextAlignment(TextAlignment.CENTER);
	    doc.add(sign);

	    Paragraph nota = new Paragraph();
	    nota.add(PDFHelper.addTab());
	    Text n1 = new Text("NOTA:\n").setBold();
	    nota.add(n1);

	    nota.add(PDFHelper.addTab());
	    Text n2 = new Text("Restituirea sumelor mai mici de 500 lei cuvenite persoanelor fizice se va efectua "
		    + "fie in numerar, fie in contul bancar inscris de solicitant in cererea de restituire.\n");
	    nota.add(n2);

	    nota.add(PDFHelper.addTab());
	    Text n3 = new Text("Excercitarea optiunii solicitantului se va face prin bararea cu o linie orizontala a "
		    + "denumirii necorespunzatoare. In situatia in care solicitantul nu isi exercita optiunea, "
		    + "restutuirea se va efectua in numerar, la ghiseul unitatii Trezoreriei Statului la care "
		    + "este arondat solicitantul.\n");
	    nota.add(n3);

	    nota.add(PDFHelper.addTab());
	    Text n4 = new Text("Restituirea sumelor cuvenite persoanelor juridice se va efectua numai prin decontare "
		    + "bancara, in contul inscris de solicitant in cererea de restituire.\n");
	    nota.add(n4);
	    doc.add(nota);

	    Paragraph legenda = new Paragraph();
	    legenda.add(PDFHelper.addTab());
	    Text ln = new Text("-----------------\n");
	    legenda.add(ln);

	    legenda.add(PDFHelper.addTab());
	    Text l1 = new Text("*1) Denumirea autoritatii/institutiei publice care a incasat suma.\n");
	    legenda.add(l1);
	    legenda.add(PDFHelper.addTab());
	    Text l2 = new Text("*2) Denumirea organului fiscal competent in administrarea solicitantului.\n");
	    legenda.add(l2);
	    legenda.add(PDFHelper.addTab());
	    Text l3 = new Text("*3) Numele sau denumirea solicitantului.\n");
	    legenda.add(l3);
	    legenda.add(PDFHelper.addTab());
	    Text l4 = new Text("*4)  Denumirea organului fiscal competent in administrarea solicitantului.\n");
	    legenda.add(l4);
	    legenda.add(PDFHelper.addTab());
	    Text l5 = new Text("*4) Denumirea unitatii Trezoreriei Statului la care este arondat solicitantul sau "
		    + "organul fiscal competent in administrarea acestuia.\n");
	    legenda.add(l5);
	    legenda.add(PDFHelper.addTab());
	    Text l6 = new Text("*6) Cuantumul  sumei pentru care se solicita restituirea, in cifre si litere.\n");
	    legenda.add(l6);
	    legenda.add(PDFHelper.addTab());
	    Text l7 = new Text("*7) Denumirea taxei/tarifului/comisionului/contributiei/etc platit(e) in plus "
		    + "fata de obligatia legala ori platit(e) necuvenit, dupa caz.\n");
	    legenda.add(l7);
	    legenda.add(PDFHelper.addTab());
	    Text l8 = new Text("*8) Se va preciza motivul pentru care se solicita restituirea, respectiv daca suma "
		    + "a fost platita in plus sau necuvenit.\n");
	    legenda.add(l8);
	    legenda.add(PDFHelper.addTab());
	    Text l9 = new Text("*9) Simbolul contului bancar in care se doreste restituirea sumei.\n");
	    legenda.add(l9);
	    legenda.add(PDFHelper.addTab());
	    Text l10 = new Text("*10) Codul IBAN  aferent contului in care se solicita restituirea sumei.\n");
	    legenda.add(l10);
	    legenda.add(PDFHelper.addTab());
	    Text l11 = new Text("*11) Denumirea bancii/trezoreriei/institutiei autorizate sa desfasoare operatiuni de "
		    + "plata, dupa caz, la care solicitantul are deschis contul.\n");
	    legenda.add(l11);
	    doc.add(legenda);

	    doc.close();
	    document.close();
	    writer.flush();
	} catch (IOException ex) { //
	    ex.getStackTrace();

	}

    }

    private void generatePDF(String tm, File pdfFile, Map<String, String> map) {
	try {
	    writer = new PdfWriter(pdfFile);
	    document = new PdfDocument(writer);

	    document = new PdfDocument(writer);
	    document.getDocumentInfo().addCreationDate();
	    document.getDocumentInfo().setAuthor("spectral369");

	    document.getDocumentInfo().setTitle("Cerere de restituire_" + tm);
	    document.setDefaultPageSize(PageSize.A4);

	    Document doc = new Document(document);
	    doc.setFontSize(12f);
	    float width = doc.getPageEffectiveArea(PageSize.A4).getWidth();
	    document.addEventHandler(PdfDocumentEvent.START_PAGE, new handleEvt(width));

	    Paragraph title = new Paragraph();
	    Text t1 = new Text("CERERE DE RESTITUIRE\n").setBold();
	    t1.setTextAlignment(TextAlignment.CENTER);
	    title.add(t1);
	    title.setTextAlignment(TextAlignment.CENTER);
	    doc.add(title);

	    Paragraph title2 = new Paragraph();
	    Text t2 = new Text(
		    "a unor sume reprezentand taxe sau alte venituri ale bugetului de stat, platite in plus sau\n"
			    + "necuvenit si pentru care nu exista obligatia de declarare\n\n").setBold();
	    t2.setTextAlignment(TextAlignment.CENTER);
	    title2.add(t2);
	    title2.setTextAlignment(TextAlignment.CENTER);
	    doc.add(title2);
	    
	    
	    
	    
	    Paragraph p1 =  new Paragraph();
	    p1.add("Nr. ");
	    p1.add(PDFHelper.createAdjustableParagraph(40,
		    new Paragraph(map.get("nr1")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    p1.add(" din data de ");
	    p1.add(PDFHelper.createAdjustableParagraph(40,
		    new Paragraph(map.get("data")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    p1.add("Catre ");
	    p1.add(PDFHelper.createAdjustableParagraph(65,
		    new Paragraph(map.get("catre")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    p1.add(" *1)/");
	    p1.add(PDFHelper.createAdjustableParagraph(55,
		    new Paragraph(map.get("autoritateSuma")).setBold().setTextAlignment(TextAlignment.CENTER)));
	    p1.add("\n\n");
	    doc.add(p1);
	    

	    Paragraph info1 = new Paragraph();
	    info1.add(PDFHelper.addTab());
	    Text line1 = new Text("Prin prezenta, " + PDFHelper.getStrWithDots(90, "") + "*3), cu sediul/domiciliul in "
		    + "localitatea " + PDFHelper.getStrWithDots(65, "") + ", str. " + PDFHelper.getStrWithDots(40, "") + "nr. " + ""
		    + PDFHelper.getStrWithDots(8, "") + "\nbl. " + PDFHelper.getStrWithDots(5, "") + ", sc. " + "" + PDFHelper.getStrWithDots(5, "")
		    + ", ap. " + PDFHelper.getStrWithDots(5, "") + ", judetul/sectorul " + "" + "" + PDFHelper.getStrWithDots(45, "")
		    + ", avand C.U.I/C.N.P. " + PDFHelper.getStrWithDots(50, "") + "" + ", administrat de "
		    + PDFHelper.getStrWithDots(50, "") + "*4), si arondat unitatii Trezoreriei Statului " + ""
		    + PDFHelper.getStrWithDots(70, "") + "*5), rog a mi se  restitui suma de " + PDFHelper.getStrWithDots(20, "") + ""
		    + "*6), reprezentand" + PDFHelper.getStrWithDots(80, "") + "*7)\nachitata in data de "
		    + PDFHelper.getStrWithDots(20, "") + "" + ", avand in vedere faptul ca " + PDFHelper.getStrWithDots(50, "") + "*8).\n");

	    info1.add(line1);
	    Text line2 = new Text("Restituirea va fi efectuata in numerar/in contul " + PDFHelper.getStrWithDots(70, "") + ""
		    + " *9)\ncod IBAN " + PDFHelper.getStrWithDots(55, "") + "*10), deschis la " + PDFHelper.getStrWithDots(50, "")
		    + "*11).\n\n");
	    info1.add(PDFHelper.addTab());
	    info1.add(line2);
	  

	    doc.add(info1);

	    Paragraph sign = new Paragraph();
	    Text sn1 = new Text("Semnatura\n");
	    sn1.setUnderline(1.0f, -2f);
	    Text sn2 = new Text(PDFHelper.getStrWithDots(45, "") + "\n");
	    Text sn3 = new Text("L.S(daca este cazul)\n\n");
	    sn3.setUnderline(1.0f, -2f);
	    sign.add(PDFHelper.addTab());
	    sign.add(PDFHelper.addTab());
	    sign.add(sn1);
	    sign.add(PDFHelper.addTab());
	    sign.add(PDFHelper.addTab());
	    sign.add(sn2);
	    sign.add(PDFHelper.addTab());
	    sign.add(PDFHelper.addTab());
	    sign.add(sn3);
	    sign.setTextAlignment(TextAlignment.CENTER);
	    doc.add(sign);

	    Paragraph nota = new Paragraph();
	    nota.add(PDFHelper.addTab());
	    Text n1 = new Text("NOTA:\n").setBold();
	    nota.add(n1);

	    nota.add(PDFHelper.addTab());
	    Text n2 = new Text(
		    "Restituirea sumelor mai mici de 500 lei cuvenite persoanelor fizice se va efectua "
			    + "fie in numerar, fie in contul bancar inscris de solicitant in cererea de restituire.\n");
	    nota.add(n2);

	    nota.add(PDFHelper.addTab());
	    Text n3 = new Text("Excercitarea optiunii solicitantului se va face prin bararea cu o linie orizontala a "
		    + "denumirii necorespunzatoare. In situatia in care solicitantul nu isi exercita optiunea, "
		    + "restutuirea se va efectua in numerar, la ghiseul unitatii Trezoreriei Statului la care "
		    + "este arondat solicitantul.\n" );
	    nota.add(n3);

	    nota.add(PDFHelper.addTab());
	    Text n4 = new Text("Restituirea sumelor cuvenite persoanelor juridice se va efectua numai prin decontare "
		    + "bancara, in contul inscris de solicitant in cererea de restituire.\n" );
	    nota.add(n4);
	    doc.add(nota);

	    Paragraph legenda = new Paragraph();
	    legenda.add(PDFHelper.addTab());
	    Text ln = new Text("-----------------\n" );
	    legenda.add(ln);

	    legenda.add(PDFHelper.addTab());
	    Text l1 = new Text("*1) Denumirea autoritatii/institutiei publice care a incasat suma.\n" );
	    legenda.add(l1);
	    legenda.add(PDFHelper.addTab());
	    Text l2 = new Text("*2) Denumirea organului fiscal competent in administrarea solicitantului.\n" );
	    legenda.add(l2);
	    legenda.add(PDFHelper.addTab());
	    Text l3 = new Text("*3) Numele sau denumirea solicitantului.\n" );
	    legenda.add(l3);
	    legenda.add(PDFHelper.addTab());
	    Text l4 = new Text("*4)  Denumirea organului fiscal competent in administrarea solicitantului.\n" );
	    legenda.add(l4);
	    legenda.add(PDFHelper.addTab());
	    Text l5 = new Text("*4) Denumirea unitatii Trezoreriei Statului la care este arondat solicitantul sau "
		    + "organul fiscal competent in administrarea acestuia.\n" );
	    legenda.add(l5);
	    legenda.add(PDFHelper.addTab());
	    Text l6 = new Text("*6) Cuantumul  sumei pentru care se solicita restituirea, in cifre si litere.\n");
	    legenda.add(l6);
	    legenda.add(PDFHelper.addTab());
	    Text l7 = new Text("*7) Denumirea taxei/tarifului/comisionului/contributiei/etc platit(e) in plus "
		    + "fata de obligatia legala ori platit(e) necuvenit, dupa caz.\n" );
	    legenda.add(l7);
	    legenda.add(PDFHelper.addTab());
	    Text l8 = new Text("*8) Se va preciza motivul pentru care se solicita restituirea, respectiv daca suma "
		    + "a fost platita in plus sau necuvenit.\n" );
	    legenda.add(l8);
	    legenda.add(PDFHelper.addTab());
	    Text l9 = new Text("*9) Simbolul contului bancar in care se doreste restituirea sumei.\n" );
	    legenda.add(l9);
	    legenda.add(PDFHelper.addTab());
	    Text l10 = new Text("*10) Codul IBAN  aferent contului in care se solicita restituirea sumei.\n" );
	    legenda.add(l10);
	    legenda.add(PDFHelper.addTab());
	    Text l11 = new Text("*11) Denumirea bancii/trezoreriei/institutiei autorizate sa desfasoare operatiuni de "
		    + "plata, dupa caz, la care solicitantul are deschis contul.\n" );
	    legenda.add(l11);
	    doc.add(legenda);

	    doc.close();
	    document.close();
	    writer.flush();
	} catch (IOException ex) { //
	    ex.getStackTrace();

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
