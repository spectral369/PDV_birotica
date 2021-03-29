package com.spectral369.ADVRADAUTO;

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
import com.itextpdf.text.pdf.*;

public class AdeverintaRadiereAutoPDF extends CustomComponent implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "ADVRadiereAutoPDF";
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
		AdeverintaRadiereAutoPDF.FNAME = "";
	}

	public AdeverintaRadiereAutoPDF(final Map<String, String> map) {
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
						+ "Adeverinta_radiere_auto_" + tm + ".pdf");
				System.out.println(this.pdfFile.getCanonicalPath());
				AdeverintaRadiereAutoPDF.FNAME = this.pdfFile.getCanonicalPath();
			} else {
				this.pdfFile = new File(String.valueOf(System.getProperty("java.io.tmpdir")) + File.separator
						+ "Adeverinta_radiere_auto_" + tm + ".pdf");
				System.out.println(this.pdfFile.getCanonicalPath());
				AdeverintaRadiereAutoPDF.FNAME = this.pdfFile.getCanonicalPath();
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
			AdeverintaRadiereAutoPDF.FNAME = "";
			MyUI.navigator.removeView("ADVRadiereAutoInfo");
			MyUI.navigator.removeView("ADVRadiereAutoPDF");
			MyUI.navigator.navigateTo("Index");
		});
		this.backLayout.addComponent((Component) this.backbtn);
		this.content.addComponent((Component) this.backLayout);
		this.content.setComponentAlignment((Component) this.backLayout, Alignment.MIDDLE_CENTER);
		this.content.setMargin(false);
		this.setCompositionRoot((Component) this.content);
		MyUI.navigator.addView("ADVRadiereAutoPDF", this);
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
			this.document.addTitle("Adeverinta_de_radiere_auto_" + tm);
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
			final Chunk t1 = new Chunk("\n\n\n\nAdeverinta\n\n\n", bold2);
			titlu.setAlignment(1);
			titlu.add(t1);
			this.document.add(titlu);

			final Paragraph declaratie = new Paragraph();
			declaratie.setLeading(20.0f);
			declaratie.setTabSettings(new TabSettings(15.0f));
			declaratie.add(Chunk.TABBING);
			Chunk dec1 = new Chunk("Se adevereste de catre noi ca prin prezenta ca Dl/D-na/Subscrisa "
					+ PDFHelper.getStrWithDots(46, "") + " " + "cu domiciliul/punct de lucru in "
					+ PDFHelper.getStrWithDots(55, "") + " numarul " + PDFHelper.getStrWithDots(22, "") + ""
					+ " detine spatiu de depozitare pentru autoturismul marca " + PDFHelper.getStrWithDots(60, "")
					+ "\n" + "model " + PDFHelper.getStrWithDots(38, "") + " capacitate cilindrica "
					+ PDFHelper.getStrWithDots(22, "") + " serie " + "motor " + PDFHelper.getStrWithDots(30, "")
					+ " serie sasiu " + PDFHelper.getStrWithDots(55, "") + " numarul de inmatriculare " + ""
					+ PDFHelper.getStrWithDots(30, "") + " la adresa " + PDFHelper.getStrWithDots(80, "") + " .\n");
			declaratie.add(dec1);
			declaratie.add(Chunk.TABBING);
			Chunk dec2 = new Chunk("Prezenta s-a eliberat spre a servi la radiere.");
			declaratie.add(dec2);

			final Paragraph dataSiSemnatura = new Paragraph();
			dataSiSemnatura.setLeading(25.0f);
			dataSiSemnatura.setTabSettings(new TabSettings(300.0f));
			dataSiSemnatura.setIndentationLeft(65.0f);
			final Chunk data = new Chunk("Primar");
			dataSiSemnatura.add((Element) data);
			dataSiSemnatura.add((Element) Chunk.TABBING);
			final Chunk semnatura = new Chunk("Intocmit\n");
			dataSiSemnatura.add((Element) semnatura);
			final Chunk dat = new Chunk(PDFHelper.getStrWithDots(30, ""));
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
			this.document.addTitle("Adeverinta_de_radiere_auto_" + tm);
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
			final Chunk t1 = new Chunk("\n\n\n\nAdeverinta\n\n\n", bold2);
			titlu.setAlignment(1);
			titlu.add(t1);
			this.document.add(titlu);

			Paragraph dec01 = new Paragraph(Chunk.TABBING);
			Chunk ch01 = new Chunk("Se adevereste de catre noi prin prezenta ca "+map.get("titlu")+" ");
			dec01.add(ch01);
			Chunk ch02 = new Chunk(PDFHelper.getEmptySpace(42));
			dec01.add(ch02);
			Chunk ch03 = null;
			Chunk ch031 = null;
			if (!map.get("titlu").contains("scris")) {
				ch03 = new Chunk(" cu domiciliul\n");
				ch031 = new Chunk("in ");
			} else {
				ch03 = new Chunk(" cu punct\n");
				ch031 = new Chunk("de lucru in comuna Dudestii-Vechi sat ");
			}
			dec01.add(ch03);
			dec01.add(ch031);
			Chunk ch04 = new Chunk(PDFHelper.getEmptySpace(40));
			dec01.add(ch04);
			Chunk ch041 = new Chunk(" numarul ");
			dec01.add(ch041);
			Chunk ch042 = new Chunk(PDFHelper.getEmptySpace(11));
			dec01.add(ch042);
			Chunk ch05 = null;
			Chunk ch0551 = null;
			if (!map.get("titlu").contains("scris"))
				ch05 = new Chunk(" detine spatiu de depozitare pentru autovehicolul \n");
			else {
				ch05 = new Chunk(" detine spatiu de \n");
				ch0551 = new Chunk("depozitare pentru autovehicolul ");
			}
			dec01.add(ch05);
			if (ch0551 != null)
				dec01.add(ch0551);
			Chunk ch051 = new Chunk("marca ");
			dec01.add(ch051);
			Chunk ch06 = new Chunk(PDFHelper.getEmptySpace(25));
			dec01.add(ch06);
			Chunk ch07 = new Chunk(" model ");
			dec01.add(ch07);
			Chunk ch08 = new Chunk(PDFHelper.getEmptySpace(20));
			dec01.add(ch08);
			Chunk ch09 = null;
			Chunk ch091 = null;
			if (!map.get("titlu").contains("scris"))
				ch09 = new Chunk(" capacitate cilindrica ");
			else {
				ch09 = new Chunk(" capacitate \n");
				ch091 = new Chunk("cilindrica ");
			}
			dec01.add(ch09);
			if (ch091 != null)
				dec01.add(ch091);
			Chunk ch10 = new Chunk(PDFHelper.getEmptySpace(10));
			dec01.add(ch10);
			Chunk ch11 = new Chunk(" serie motor ");
			dec01.add(ch11);
			Chunk ch12 = new Chunk(PDFHelper.getEmptySpace(20));
			dec01.add(ch12);
			Chunk ch13 = new Chunk(" serie sasiu ");
			dec01.add(ch13);
			Chunk ch14 = new Chunk(PDFHelper.getEmptySpace(45));
			dec01.add(ch14);
			Chunk ch141 = null;
			Chunk ch1411 = null;
			if (!map.get("titlu").contains("scris"))
				ch141 = new Chunk(" avand numar de inmatriculare ");
			else {
				ch141 = new Chunk(" avand numar\n");
				ch1411 = new Chunk("de inmatriculare ");
			}
			dec01.add(ch141);
			if (ch1411 != null)
				dec01.add(ch1411);
			Chunk ch142 = new Chunk(PDFHelper.getEmptySpace(28));
			dec01.add(ch142);
			Chunk ch15 = null;
			if (!map.get("titlu").contains("scris"))
				ch15 = new Chunk(" la \n");
			else
				ch15 = new Chunk(" la ");
			dec01.add(ch15);
			Chunk ch151 = new Chunk("adresa ");
			dec01.add(ch151);
			Chunk ch16 = new Chunk(PDFHelper.getEmptySpace(40));
			dec01.add(ch16);
			Chunk ch17 = new Chunk(" .");
			dec01.add(ch17);
			document.add(dec01);

			float y = writer.getVerticalPosition(false);

			y += dec01.getLeading() * 4;// minus 3 randuri

			float x2 = document.left() + ch01.getWidthPoint();
			float x3 = x2 + ch02.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("prenume") + " " + map.get("nume"), document, y, x3, x2, writer, true);
			y -= dec01.getLeading();// urmatorul rand
			x2 = document.left() + ch031.getWidthPoint();
			x3 = x2 + ch04.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("localitate"), document, y, x3, x2, writer, false);
			x2 = x3 + ch041.getWidthPoint();
			x3 = x2 + ch042.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("nrStrada"), document, y, x3, x2, writer, false);
			y -= dec01.getLeading();// urmatorul rand
			if (!map.get("titlu").contains("scris"))
				x2 = document.left() + ch051.getWidthPoint();
			else
				x2 = document.left() + (ch0551.getWidthPoint() + ch051.getWidthPoint());
			x3 = x2 + ch06.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("marca"), document, y, x3, x2, writer, false);
			x2 = x3 + ch07.getWidthPoint();
			x3 = x2 + ch08.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("model"), document, y, x3, x2, writer, false);
			if (map.get("titlu").contains("scris")) {
				y -= dec01.getLeading();// urmatorul rand
				x2 = document.left() + ch091.getWidthPoint();
			} else {
				x2 = x3 + ch09.getWidthPoint();
			}
			x3 = x2 + ch10.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("capacitate"), document, y, x3, x2, writer, false);
			x2 = x3 + ch11.getWidthPoint();
			x3 = x2 + ch12.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("serieMotor"), document, y, x3, x2, writer, false);
			if (!map.get("titlu").contains("scris")) {
				y -= dec01.getLeading();// urmatorul rand
				x2 = document.left() + ch13.getWidthPoint();
			} else
				x2 = x3 + ch13.getWidthPoint();
			x3 = x2 + ch14.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("serieSasiu"), document, y, x3, x2, writer, false);
			if (map.get("titlu").contains("scris")) {
				y -= dec01.getLeading();// urmatorul rand
				x2 = document.left() + ch1411.getWidthPoint();
			} else
				x2 = x3 + ch141.getWidthPoint();

			x3 = x2 + ch142.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("nrInmatriculare"), document, y, x3, x2, writer, false);
			if (!map.get("titlu").contains("scris")) {
				y -= dec01.getLeading();// urmatorul rand
				x2 = document.left() + ch151.getWidthPoint();
			} else
				x2 = x3 + ch151.getWidthPoint();
			x3 = x2 + ch16.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("addrDepozitare"), document, y, x3, x2, writer, false);

			document.add(Chunk.NEWLINE);
			Paragraph dec02 = new Paragraph(Chunk.TABBING);
			Chunk gr01 = new Chunk("Prezenta s-a eliberat spre a servi la radiere.");
			dec02.add(gr01);
			document.add(dec02);
			document.add(Chunk.NEWLINE);

			final Paragraph dataSiSemnatura = new Paragraph();
			dataSiSemnatura.setLeading(25.0f);
			dataSiSemnatura.setTabSettings(new TabSettings(300.0f));
			dataSiSemnatura.setIndentationLeft(65.0f);
			final Chunk data = new Chunk("Primar");
			dataSiSemnatura.add((Element) data);
			dataSiSemnatura.add((Element) Chunk.TABBING);
			final Chunk semnatura = new Chunk("Intocmit\n");
			dataSiSemnatura.add((Element) semnatura);
			final Chunk dat = new Chunk(PDFHelper.getStrWithDots(30, ""));
			dataSiSemnatura.add((Element) dat);
			dataSiSemnatura.add((Element) Chunk.TABBING);
			final Chunk semn = new Chunk(map.get("intocmit"));
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
