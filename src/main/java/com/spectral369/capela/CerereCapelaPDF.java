package com.spectral369.capela;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.spectral369.utils.PDFHelper;
import com.spectral369.utils.Utils;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.JavaScriptFunction;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import elemental.json.JsonArray;

public class CerereCapelaPDF extends CustomComponent implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "CerereCapelaPDF";
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
		CerereCapelaPDF.FNAME = "";
	}

	public CerereCapelaPDF(final Map<String, String> map) {
		this.writer = null;
		this.bold = FontFactory.getFont("Times-Roman", 14.0f, 1);
		this.bold2 = FontFactory.getFont("Times-Roman", 12.0f, 1);
		final ZonedDateTime time = ZonedDateTime.now();
		final DateTimeFormatter sdf2 = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		try {
			antetLogo = Image
					.getInstance(new FileResource(Utils.getImage("antet2")).getSourceFile().getCanonicalPath());
		} catch (BadElementException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String tm = time.format(sdf2);
		try {
			if (System.getProperty("os.name").toUpperCase().contains("WIN")) {
				tm = tm.replaceAll("\\.", "_");
				tm = tm.replaceAll(":", "_");
				this.pdfFile = new File(String.valueOf(System.getProperty("java.io.tmpdir")) + File.separator
						+ "Cerere_capela_" + tm + ".pdf");
				System.out.println(this.pdfFile.getCanonicalPath());
				CerereCapelaPDF.FNAME = this.pdfFile.getCanonicalPath();
			} else {
				this.pdfFile = new File(String.valueOf(System.getProperty("java.io.tmpdir")) + File.separator
						+ "Cerere_capela_" + tm + ".pdf");
				System.out.println(this.pdfFile.getCanonicalPath());
				CerereCapelaPDF.FNAME = this.pdfFile.getCanonicalPath();
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
			CerereCapelaPDF.FNAME = "";
			UI.getCurrent().getNavigator().removeView("CerereCapelaInfo");
			UI.getCurrent().getNavigator().removeView("CerereCapelaPDF");
			UI.getCurrent().getNavigator().navigateTo("Index");
		});
		this.backLayout.addComponent((Component) this.backbtn);
		this.content.addComponent((Component) this.backLayout);
		this.content.setComponentAlignment((Component) this.backLayout, Alignment.MIDDLE_CENTER);
		this.content.setMargin(false);
		this.setCompositionRoot((Component) this.content);
		UI.getCurrent().getNavigator().addView("CerereCapelaPDF", this);
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
			this.document.addTitle("Cerere_capela_" + tm);
			final Paragraph antet = new Paragraph();

			float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
			float documentHeight = document.getPageSize().getHeight() - document.topMargin() - document.bottomMargin();
			antetLogo.scaleToFit(documentWidth, documentHeight);

			antet.add(antetLogo);
			antet.setAlignment(1);
			document.add(antet);

			final Paragraph nrInreg = new Paragraph();
			nrInreg.setAlignment(0);
			Chunk nrI = new Chunk(
					"\n\nNr. " + PDFHelper.getStrWithDots(15, "") + " " + "data " + PDFHelper.getStrWithDots(20, ""));
			nrInreg.add(nrI);
			this.document.add(nrInreg);

			final Paragraph titlu = new Paragraph();
			final Chunk t1 = new Chunk("\n\n\n\n CERERE PENTRU INCHIRIEREA CAPELEI\n\n\n", bold2);
			titlu.setAlignment(1);
			titlu.add(t1);
			this.document.add(titlu);

			final Paragraph declaratie = new Paragraph();
			declaratie.setLeading(20.0f);
			declaratie.setTabSettings(new TabSettings(15.0f));
			declaratie.add(Chunk.TABBING);
			Chunk dec1 = new Chunk("Subsemnatul/a "+PDFHelper.getStrWithDots(25, "")+"  domiciliat in "+
			PDFHelper.getStrWithDots(50, "")+" judet "+PDFHelper.getStrWithDots(20, "")+" nr. "+PDFHelper.getStrWithDots(18, "")+
			" avand C.N.P "+PDFHelper.getStrWithDots(35, "")+" prin prezenta solicit aprobarea inchirierii capelei mortuare din "
					+ "Dudestii-Vechi pentru " + PDFHelper.getStrWithDots(12, "")+" zile, incepand cu data de "+PDFHelper.getStrWithDots(25, "")+
					". \n");
			declaratie.add(dec1);
			declaratie.add(Chunk.TABBING);
			Chunk dec2 = new Chunk("Mentionez ca voi achita taxa de inchiriere in termen de  5 zile de"
					+ " la desfasurarea evenimentului.\n\n\n");
			declaratie.add(dec2);

			final Paragraph dataSiSemnatura = new Paragraph();
			dataSiSemnatura.setLeading(25.0f);
			dataSiSemnatura.setTabSettings(new TabSettings(300.0f));
			dataSiSemnatura.setIndentationLeft(65.0f);
			final Chunk data = new Chunk("Data");
			dataSiSemnatura.add((Element) data);
			dataSiSemnatura.add((Element) Chunk.TABBING);
			final Chunk semnatura = new Chunk("Semnatura\n");
			dataSiSemnatura.add((Element) semnatura);
			String dateNow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            final Chunk dat = new Chunk(dateNow);
			dataSiSemnatura.add((Element) dat);
			dataSiSemnatura.add((Element) Chunk.TABBING);
			final Chunk semn = new Chunk(PDFHelper.getStrWithDots(30, ""));
			dataSiSemnatura.add((Element) semn);
			this.document.add((Element) declaratie);
			this.document.add((Element) dataSiSemnatura);
			final PdfContentByte canvas = this.writer.getDirectContent();

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
			this.document.addTitle("Cerere_capela_" + tm);
			final Paragraph antet = new Paragraph();

			float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
			float documentHeight = document.getPageSize().getHeight() - document.topMargin() - document.bottomMargin();
			antetLogo.scaleToFit(documentWidth, documentHeight);

			antet.add(antetLogo);
			antet.setAlignment(1);
			document.add(antet);

			final Paragraph nrInreg = new Paragraph();
			nrInreg.setAlignment(0);
			Chunk nrI = new Chunk(
					"\n\nNr. " + PDFHelper.getStrWithDots(15, "") + " " + "data " + PDFHelper.getStrWithDots(20, ""));
			nrInreg.add(nrI);
			this.document.add(nrInreg);

			final Paragraph titlu = new Paragraph();
			final Chunk t1 = new Chunk("\n\n\n\nCERERE PENTRU INCHIRIEREA CAPELEI\n\n\n", bold2);
			titlu.setAlignment(1);
			titlu.add(t1);
			this.document.add(titlu);

			Paragraph dec01 = new Paragraph(Chunk.TABBING);
			Chunk ch01 =  new Chunk("Subsemnatul/a ");
			dec01.add(ch01);
			Chunk ch02 =  new Chunk(PDFHelper.getEmptySpace(40));
			dec01.add(ch02);
			Chunk ch03 =  new Chunk(" domiciliat/a in ");
			dec01.add(ch03);
			Chunk ch04 =  new Chunk(PDFHelper.getEmptySpace(50));
			dec01.add(ch04);
			Chunk ch05 =  new Chunk(" judet ");
			dec01.add(ch05);
			Chunk ch06 =  new Chunk(PDFHelper.getEmptySpace(12));
			dec01.add(ch06);
			Chunk ch061 =  new Chunk(" nr. ");
			dec01.add(ch061);
			Chunk ch062 =  new Chunk(PDFHelper.getEmptySpace(8));
			dec01.add(ch062);
			Chunk ch07  =  new Chunk(" avand C.N.P ");
			dec01.add(ch07);
			Chunk ch08 =  new Chunk(PDFHelper.getEmptySpace(30));
			dec01.add(ch08);
			Chunk ch09 =  new Chunk(" prin prezenta solicit aprobarea inchirierii");
			dec01.add(ch09);
			Chunk ch091 =  new Chunk(" capelei mortuare din Dudestii-Vechi "
					+ " pentru ");
			dec01.add(ch091);
			Chunk ch10 = new Chunk(PDFHelper.getEmptySpace(8));
			dec01.add(ch10);
			Chunk ch11 =  new Chunk(" zile, incepand cu data de ");
			dec01.add(ch11);
			Chunk ch12 =  new Chunk(PDFHelper.getEmptySpace(18));
			dec01.add(ch12);
			Chunk ch13 =  new Chunk(".\n");
			dec01.add(ch13);
			document.add(dec01);
			
			float y = writer.getVerticalPosition(false);
			y += dec01.getLeading() * 2;
			
			float x2 = document.left() + ch01.getWidthPoint();
			float x3 = x2 + ch02.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("prenume") + " " + map.get("nume"), document, y, x3, x2, writer, true);
			x2 = x3+ ch03.getWidthPoint();
			x3 = x2+ch04.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("localitate"), document, y, x3, x2, writer, false);
			y -= dec01.getLeading();
			x2 = document.left() + ch05.getWidthPoint();
			x3 =  x2+ ch06.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("judet"), document, y, x3, x2, writer, false);
			x2 =  x3+ch061.getWidthPoint();
			x3 = x2+ch062.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("nrStrada"), document, y, x3, x2, writer, false);
			x2=  x3+ch07.getWidthPoint();
			x3 =  x2+ch08.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("cnp"), document, y, x3, x2, writer, false);
			y -= dec01.getLeading();
			x2 = document.left() + ch091.getWidthPoint();
			x3 =  x2+ch10.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("nrZile"), document, y, x3, x2, writer, false);
			x2 =  x3+ch11.getWidthPoint();
			x3 = x2+ch12.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("ziua") +"-" + map.get("luna") + "-" + map.get("anul"), document, y, x3, x2, writer, false);
			
			document.add(Chunk.NEWLINE);
			Paragraph dec02 = new Paragraph(Chunk.TABBING);
			Chunk gr01 = new Chunk("Mentionez ca voi achita taxa de inchriere in termen de 5 zile de la desfasurarea evenimentului.");
			dec02.add(gr01);
			document.add(dec02);
			document.add(Chunk.NEWLINE);
			final Paragraph dataSiSemnatura = new Paragraph();
			dataSiSemnatura.setLeading(25.0f);
			dataSiSemnatura.setTabSettings(new TabSettings(300.0f));
			dataSiSemnatura.setIndentationLeft(65.0f);
			final Chunk data = new Chunk("Data");
			dataSiSemnatura.add((Element) data);
			dataSiSemnatura.add((Element) Chunk.TABBING);
			final Chunk semnatura = new Chunk("Semnatura\n");
			dataSiSemnatura.add((Element) semnatura);
			String dateNow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            final Chunk dat = new Chunk(dateNow);
            dataSiSemnatura.add((Element) dat);
			dataSiSemnatura.add((Element) Chunk.TABBING);
			final Chunk semn = new Chunk(PDFHelper.getStrWithDots(30, ""));
			dataSiSemnatura.add((Element) semn);
			this.document.add((Element) dataSiSemnatura);
			final PdfContentByte canvas = this.writer.getDirectContent();

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
