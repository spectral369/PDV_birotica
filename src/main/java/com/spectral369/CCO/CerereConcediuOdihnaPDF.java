package com.spectral369.CCO;


import com.vaadin.navigator.*;
import java.util.*;
import java.time.*;
import java.time.format.*;
import com.vaadin.icons.*;
import com.vaadin.server.*;
import com.spectral369.birotica.*;
import com.spectral369.utils.PDFHelper;
import com.spectral369.utils.Utils;
import com.vaadin.ui.*;

import elemental.json.JsonArray;

import com.itextpdf.text.*;
import com.itextpdf.text.Image;

import java.io.*;
import java.text.SimpleDateFormat;

import com.itextpdf.text.pdf.*;

public class CerereConcediuOdihnaPDF extends CustomComponent implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "CerereConcediuOdihnaPDF";
	public static String FNAME;
	private transient PdfWriter writer;
	// private final int fontSize = 14;
	@SuppressWarnings("unused")
	private final transient Font bold;
	private final transient Font bold2;
	private transient Document document;
	private File pdfFile;
	VerticalLayout content;
	HorizontalLayout titleLayout;
	Button title;
	HorizontalLayout pdfLayout;
	HorizontalLayout backLayout;
	Button backbtn;
	Image antetLogo;

	static {
		CerereConcediuOdihnaPDF.FNAME = "";
	}

	public CerereConcediuOdihnaPDF(final Map<String, String> map) {
		this.writer = null;
		this.bold = FontFactory.getFont("Times-Roman", 14.0f, 1);
		this.bold2 = FontFactory.getFont("Times-Roman", 12.0f, 1);
		final ZonedDateTime time = ZonedDateTime.now();
		final DateTimeFormatter sdf2 = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		try {
			antetLogo = Image.getInstance(new FileResource(Utils.getImage("antet2")).getSourceFile().getCanonicalPath());
		} catch (BadElementException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String tm = time.format(sdf2);
		try {
			if (System.getProperty("os.name").toUpperCase().contains("WIN")) {
				tm = tm.replaceAll("\\.", "_");
				tm = tm.replaceAll(":", "_");
				this.pdfFile = new File(
						String.valueOf(System.getProperty("java.io.tmpdir")) + File.separator + "Cerere_Concediu_Odihna" + tm + ".pdf");
				System.out.println(this.pdfFile.getCanonicalPath());
				CerereConcediuOdihnaPDF.FNAME = this.pdfFile.getCanonicalPath();
			} else {
				this.pdfFile = new File(
						String.valueOf(System.getProperty("java.io.tmpdir")) + File.separator + "Cerere_Concediu_Odihna" + tm + ".pdf");
				System.out.println(this.pdfFile.getCanonicalPath());
				CerereConcediuOdihnaPDF.FNAME = this.pdfFile.getCanonicalPath();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (map.isEmpty()) {
			this.generatePDF(tm, this.pdfFile);
		} else {
			this.generatePDF(tm, this.pdfFile, map);
		}
		this.content = new VerticalLayout();
		this.titleLayout = new HorizontalLayout();
		(this.title = new Button("Generated PDF", (Resource) VaadinIcons.FILE_PRESENTATION)).setEnabled(false);
		this.title.addStyleNames(new String[] { "borderless", "clearDisabled" });
		this.titleLayout.addComponent((Component) this.title);
		this.content.addComponent((Component) this.titleLayout);
		this.content.setComponentAlignment((Component) this.titleLayout, Alignment.MIDDLE_CENTER);
		this.pdfLayout = new HorizontalLayout();
		final BrowserFrame pdf = new BrowserFrame("", (Resource) new FileResource(this.pdfFile));
		pdf.setHeight((float) Page.getCurrent().getBrowserWindowHeight(), Sizeable.Unit.PIXELS);
		pdf.setWidth((float) Page.getCurrent().getBrowserWindowWidth(), Sizeable.Unit.PIXELS);
		this.pdfLayout.addComponent((Component) pdf);
		this.content.addComponent((Component) this.pdfLayout);
		this.content.setComponentAlignment((Component) this.pdfLayout, Alignment.MIDDLE_CENTER);
		this.backLayout = new HorizontalLayout();
		(this.backbtn = new Button("Close", (Resource) VaadinIcons.CLOSE_CIRCLE_O)).addStyleName("friendly");
		this.backbtn.addClickListener(evt -> {
			if (this.pdfFile.exists()) {
				this.pdfFile.delete();
			}
			CerereConcediuOdihnaPDF.FNAME = "";
			MyUI.navigator.removeView("CerereConcediuOdihnaInfo");
			MyUI.navigator.removeView("CerereConcediuOdihnaPDF");
			MyUI.navigator.navigateTo("Index");
		});
		this.backLayout.addComponent((Component) this.backbtn);
		this.content.addComponent((Component) this.backLayout);
		this.content.setComponentAlignment((Component) this.backLayout, Alignment.MIDDLE_CENTER);
		this.content.setMargin(false);
		this.setCompositionRoot((Component) this.content);
		MyUI.navigator.addView("CerereConcediuOdihnaPDF", this);
		JavaScript.getCurrent().addFunction("aboutToClose", new JavaScriptFunction() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void call(JsonArray arguments) {
				System.out.println("Got aboutToClose callback!!");
				handleClose();

			}
		});

		Page.getCurrent().getJavaScript().execute(
				"window.onbeforeunload = function (e) { var e = e || window.event; aboutToClose(); return; };");

	}

	public void handleClose() {
		if (this.pdfFile.exists()) {
			this.pdfFile.delete();
		}
	}
	
	private void generatePDF(final String tm, final File pdfFile) {
		try {
			this.document = new Document();
			this.document.addAuthor("spectral369");
			this.document.addCreationDate();
			this.document.addHeader("Page count", String.valueOf(this.document.getPageNumber() + 1));
			this.document.setPageSize(PageSize.A4);
			this.writer = PdfWriter.getInstance(this.document, (OutputStream) new FileOutputStream(pdfFile));
			this.document.open();
			this.document.addTitle("Cerere_Concediu_Odihna" + tm);
			final Paragraph antet =  new Paragraph();
		
			float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
			float documentHeight = document.getPageSize().getHeight()- document.topMargin() - document.bottomMargin();
			antetLogo.scaleToFit(documentWidth, documentHeight);
			
			antet.add(antetLogo);
			antet.setAlignment(1);
			document.add(antet);
			
			
			Paragraph nrInreg = new Paragraph();
			nrInreg.setAlignment(0);
			Chunk t1 = new Chunk("\n\n\nNr. " + PDFHelper.getStrWithDots(15, "") + " " + "data " + PDFHelper.getStrWithDots(20, ""));
			nrInreg.add(t1);
			this.document.add(nrInreg);
			Paragraph aprobat = new Paragraph();
			aprobat.setAlignment(2);
			Phrase prim = new Phrase();
			Chunk p1 = new Chunk("\nAproabat,");
			prim.add(p1);
			prim.add(Chunk.TABBING);
			prim.add(Chunk.TABBING);
			prim.add(Chunk.TABBING);
			prim.add(new Chunk("\n"));
			Chunk p2 = new Chunk("Primar");
			prim.add(p2);
			prim.add(Chunk.TABBING);
			prim.add(Chunk.TABBING);
			prim.add(Chunk.TABBING);
			prim.add(Chunk.TABBING);
			prim.add(new Chunk("\n"));
			Chunk p3 = new Chunk(PDFHelper.getStrWithDots(28, ""));
			prim.add(p3);
			prim.add(Chunk.TABBING);
			prim.add(Chunk.TABBING);
			aprobat.add(prim);
			document.add(aprobat);
			
			final Paragraph titlu = new Paragraph();	
			final Chunk tit = new Chunk("\n\n\nCERERE CONCEDIU ODIHNA\n\n\n",bold2);
			titlu.setAlignment(1);
			titlu.add(tit);
			this.document.add(titlu);
			
			final Paragraph declaratie = new Paragraph();
			declaratie.setLeading(20.0f);
			declaratie.setTabSettings(new TabSettings(15.0f));
			declaratie.add(Chunk.TABBING);
			Chunk dec1 = new Chunk("Subsemnatul/a "+PDFHelper.getStrWithDots(65, "")+", salariat al Primariei Dudestii-vechi, jud. "
					+ "Timis, avand functia de "+PDFHelper.getStrWithDots(40, "")+" prin prezenta, va rog sa-mi aprobati efectuarea"
					+ " a "+PDFHelper.getStrWithDots(8, "")+" zile din concediul de odihna aferent anului "+PDFHelper.getStrWithDots(10, "")+" "
							+ "din data de "+PDFHelper.getStrWithDots(22, "")+" pana in data de "+PDFHelper.getStrWithDots(22, "")+".");
			declaratie.add(dec1);
			declaratie.add(Chunk.TABBING);
			Chunk dec2 =  new Chunk("\n\n\nVa multumesc!\n\n");
			declaratie.add(dec2);
			
			final Paragraph dataSiSemnatura = new Paragraph();
            dataSiSemnatura.setLeading(25.0f);
            dataSiSemnatura.setTabSettings(new TabSettings(300.0f));
            dataSiSemnatura.setIndentationLeft(65.0f);
            final Chunk data = new Chunk("Data");
            dataSiSemnatura.add((Element)data);
            dataSiSemnatura.add((Element)Chunk.TABBING);
            final Chunk semnatura = new Chunk("Semnatura\n");
            dataSiSemnatura.add((Element)semnatura);
            String dateNow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            final Chunk dat = new Chunk(dateNow);
            dataSiSemnatura.add((Element)dat);
            dataSiSemnatura.add((Element)Chunk.TABBING);
            final Chunk semn = new Chunk(PDFHelper.getStrWithDots(30, "")+"\n\n\n");
            dataSiSemnatura.add((Element)semn);
            this.document.add((Element)declaratie);
            this.document.add((Element)dataSiSemnatura);
			final PdfContentByte canvas = this.writer.getDirectContent();
			
			Paragraph inlocuitor =  new Paragraph();
			inlocuitor.setLeading(20.0f);
			inlocuitor.setTabSettings(new TabSettings(15.0f));
			inlocuitor.add(Chunk.TABBING);
			Chunk inloc =  new Chunk("Pe perioada efectuarii concediului de odihna integral sau partial, atributiile de serviciu "
					+ "sunt preluate de "+PDFHelper.getStrWithDots(45, ""));
			inlocuitor.add(inloc);
			document.add(inlocuitor);
			
			final Phrase footer = new Phrase(
					String.valueOf(this.document.getPageNumber() + 1) + "/" + this.writer.getPageNumber());
			ColumnText.showTextAligned(canvas, 0, footer, this.writer.getPageSize().getWidth() / 2.0f, 0.0f, 0.0f);
			this.document.close();
		} catch (DocumentException ex) {
		} catch (FileNotFoundException ex2) {
		}
		this.writer.flush();
	}

	private void generatePDF(final String tm, final File pdfFile, final Map<String, String> map) {
		try {
			this.document = new Document();
			this.document.addAuthor("spectral369");
			this.document.addCreationDate();
			this.document.addHeader("Page count", String.valueOf(this.document.getPageNumber() + 1));
			this.document.setPageSize(PageSize.A4);
			this.writer = PdfWriter.getInstance(this.document, (OutputStream) new FileOutputStream(pdfFile));
			this.document.open();
			this.document.addTitle("Cerere_Concediu_Odihna" + tm);
			final Paragraph antet =  new Paragraph();
		
			float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
			float documentHeight = document.getPageSize().getHeight()- document.topMargin() - document.bottomMargin();
			antetLogo.scaleToFit(documentWidth, documentHeight);
			
			antet.add(antetLogo);
			antet.setAlignment(1);
			document.add(antet);
			
			Paragraph nrInreg = new Paragraph();
			nrInreg.setAlignment(0);
			Chunk t1 = new Chunk("\n\n\nNr. " + PDFHelper.getStrWithDots(15, "") + " " + "data " + PDFHelper.getStrWithDots(20, ""));
			nrInreg.add(t1);
			this.document.add(nrInreg);
			Paragraph aprobat = new Paragraph();
			aprobat.setAlignment(2);
			Phrase prim = new Phrase();
			Chunk p1 = new Chunk("\nAproabat,");
			prim.add(p1);
			prim.add(Chunk.TABBING);
			prim.add(Chunk.TABBING);
			prim.add(Chunk.TABBING);
			prim.add(new Chunk("\n"));
			Chunk p2 = new Chunk("Primar");
			prim.add(p2);
			prim.add(Chunk.TABBING);
			prim.add(Chunk.TABBING);
			prim.add(Chunk.TABBING);
			prim.add(Chunk.TABBING);
			prim.add(new Chunk("\n"));
			Chunk p3 = new Chunk(PDFHelper.getStrWithDots(28, ""));
			prim.add(p3);
			prim.add(Chunk.TABBING);
			prim.add(Chunk.TABBING);
			prim.add(Chunk.TABBING);
			aprobat.add(prim);
			document.add(aprobat);
			
			final Paragraph titlu = new Paragraph();	
			final Chunk tit = new Chunk("\n\n\nCERERE CONCEDIU ODIHNA\n\n\n",bold2);
			titlu.setAlignment(1);
			titlu.add(tit);
			this.document.add(titlu);
			
			
			
			Paragraph dec01 =  new Paragraph(Chunk.TABBING);
			Chunk d01 =  new Chunk("Subsemnatul/a ");
			dec01.add(d01);
			Chunk d02 =  new Chunk(PDFHelper.getEmptySpace(45));
			dec01.add(d02);
			Chunk d03 =  new Chunk(" salariat al Primariei Dudestii-Vechi, jud. Timis ");
			dec01.add(d03);
			Chunk  d031 =  new Chunk("avand functia de ");
			dec01.add(d031);
			Chunk d04 =  new Chunk(PDFHelper.getEmptySpace(40));
			dec01.add(d04);
			Chunk d05 =  new Chunk(" prin prezenta, va rog sa-mi aprobati efectuarea\n");
			dec01.add(d05);
			Chunk d051 =  new Chunk("a ");
			dec01.add(d051);
			Chunk d06 =  new Chunk(PDFHelper.getEmptySpace(8));
			dec01.add(d06);
			Chunk d07 =  new Chunk(" zile din concediul de odihna aferent anului ");
			dec01.add(d07);
			Chunk d08  =  new Chunk(PDFHelper.getEmptySpace(10));
			dec01.add(d08);
			Chunk d09 =  new Chunk(" din data de ");
			dec01.add(d09);
			Chunk d10 =  new Chunk(PDFHelper.getEmptySpace(16));
			dec01.add(d10);
			Chunk d11 =  new Chunk(" pana in \n");
			dec01.add(d11);
			Chunk d111 =  new Chunk("data de ");
			dec01.add(d111);
			Chunk d12 =  new Chunk(PDFHelper.getEmptySpace(16));
			dec01.add(d12);
			Chunk d13 =  new Chunk(" .");
			dec01.add(d13);
			document.add(dec01);
			float y = writer.getVerticalPosition(false);
			y += dec01.getLeading()* 3 ;
			float x2 = document.left() + d01.getWidthPoint();
			float x3 = x2 + d02.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("prenume") + " " + map.get("nume"), document, y, x3, x2, writer,true);
			y -= dec01.getLeading();
			x2 = document.left() + d031.getWidthPoint();
			x3 = x2 + d04.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("functia"), document, y, x3, x2, writer,false);
			y -= dec01.getLeading();
			x2 = document.left() + d051.getWidthPoint();
			x3 = x2 + d06.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("nrZile"), document, y, x3, x2, writer,false);
			x2 = x3 + d07.getWidthPoint();
			x3 = x2 + d08.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("anConcediu"), document, y, x3, x2, writer,false);
			x2 = x3+ d09.getWidthPoint();
			x3 = x2 + d10.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("ziStart")+"/"+map.get("lunaStart")+"/"+map.get("anStart"), document, y, x3, x2, writer,false);
			y -= dec01.getLeading();
			x2 = document.left() + d111.getWidthPoint();
			x3 = x2 + d12.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("ziEnd")+"/"+map.get("lunaEnd")+"/"+map.get("anEnd"), document, y, x3, x2, writer,false);
			
			Paragraph thx =  new Paragraph(Chunk.TABBING);
			Chunk dec2 =  new Chunk("\n\n\nVa multumesc!\n\n");
			thx.add(dec2);
			document.add(thx);
			
			final Paragraph dataSiSemnatura = new Paragraph();
            dataSiSemnatura.setLeading(25.0f);
            dataSiSemnatura.setTabSettings(new TabSettings(300.0f));
            dataSiSemnatura.setIndentationLeft(65.0f);
            final Chunk data = new Chunk("Data");
            dataSiSemnatura.add((Element)data);
            dataSiSemnatura.add((Element)Chunk.TABBING);
            final Chunk semnatura = new Chunk("Semnatura\n");
            dataSiSemnatura.add((Element)semnatura);
            String dateNow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            final Chunk dat = new Chunk(dateNow);
            dataSiSemnatura.add((Element)dat);
            dataSiSemnatura.add((Element)Chunk.TABBING);
            final Chunk semn = new Chunk(PDFHelper.getStrWithDots(30, "")+"\n\n\n");
            dataSiSemnatura.add((Element)semn);
      
            this.document.add((Element)dataSiSemnatura);
			final PdfContentByte canvas = this.writer.getDirectContent();
			
			
			
			Paragraph inloc =  new Paragraph(Chunk.TABBING);
			Chunk i01 =  new Chunk("Pe perioada efectuarii concediului de odihna integral sau partial, atributiile de serviciu "
					+ "sunt ");
			inloc.add(i01);
			Chunk i011 =  new Chunk("preluate de ");
			inloc.add(i011);
			Chunk i02 =  new Chunk(PDFHelper.getEmptySpace(45));
			inloc.add(i02);
			document.add(inloc);
			y = writer.getVerticalPosition(false);
			x2 = document.left() + i011.getWidthPoint();
			x3 = x2 + i02.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("inlocuitor"), document, y, x3, x2, writer,false);
			/*Paragraph inlocuitor =  new Paragraph();
			inlocuitor.setLeading(20.0f);
			inlocuitor.setTabSettings(new TabSettings(15.0f));
			inlocuitor.add(Chunk.TABBING);
			declaratie.add(inlocuitor);

			Phrase inloc = new Phrase();
			Chunk inCh01 =  new Chunk("Pe perioada efectuarii concediului de odihna integral sau partial, atributiile de serviciu "
					+ "sunt preluate de ");
			inloc.add(inCh01);
			inloc.add(this.getPhraseStrWithDots(45, map.get("inlocuitor")));
			inlocuitor.add(inloc);
			document.add(inlocuitor);*/
			
			final Phrase footer = new Phrase(
					String.valueOf(this.document.getPageNumber() + 1) + "/" + this.writer.getPageNumber());
			ColumnText.showTextAligned(canvas, 0, footer, this.writer.getPageSize().getWidth() / 2.0f, 0.0f, 0.0f);
			this.document.close();
		} catch (DocumentException ex) {
		} catch (FileNotFoundException ex2) {
		}
		this.writer.flush();
	}


}

