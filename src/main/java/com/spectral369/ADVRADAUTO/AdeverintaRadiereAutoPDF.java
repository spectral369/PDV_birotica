package com.spectral369.ADVRADAUTO;

import com.vaadin.navigator.*;
import java.util.*;
import java.time.*;
import java.time.format.*;
import com.vaadin.icons.*;
import com.vaadin.server.*;
import com.spectral369.birotica.*;
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
						String.valueOf(System.getProperty("java.io.tmpdir")) + File.separator + "Adeverinta_radiere_auto_" + tm + ".pdf");
				System.out.println(this.pdfFile.getCanonicalPath());
				AdeverintaRadiereAutoPDF.FNAME = this.pdfFile.getCanonicalPath();
			} else {
				this.pdfFile = new File(
						String.valueOf(System.getProperty("java.io.tmpdir")) + File.separator + "Adeverinta_radiere_auto_" + tm + ".pdf");
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
			final Paragraph antet =  new Paragraph();
		
			float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
			float documentHeight = document.getPageSize().getHeight()- document.topMargin() - document.bottomMargin();
			antetLogo.scaleToFit(documentWidth, documentHeight);
			
			antet.add(antetLogo);
			antet.setAlignment(1);
			document.add(antet);
			final Paragraph titlu = new Paragraph();
		
			final Chunk t1 = new Chunk("\n\n\n\nAdeverinta\n\n\n",bold2);
			titlu.setAlignment(1);
			titlu.add(t1);
			this.document.add(titlu);
			
			final Paragraph declaratie = new Paragraph();
			declaratie.setLeading(20.0f);
			declaratie.setTabSettings(new TabSettings(15.0f));
			declaratie.add(Chunk.TABBING);
			Chunk dec1 = new Chunk("Se adevereste  de catre noi ca prin prezenta ca Dl/D-na "+this.getStrWithDots(60, "")+" "
					+ "cu domiciliul in comuna Dudestii-Vechi sat "+this.getStrWithDots(40, "")+" numarul "+this.getStrWithDots(15, "")+""
					+ " detine spatiu de depozitare pentru autoturismul marca "+this.getStrWithDots(30, "")+""
					+ " model "+this.getStrWithDots(30, "")+" capacitate cilindrica "+this.getStrWithDots(15, "")+" serie "
					+ "motor "+this.getStrWithDots(25, "")+" serie sasiu "+this.getStrWithDots(35, "")+" numarul de inmatriculare "
					+ ""+this.getStrWithDots(20, "")+" la adresa "+this.getStrWithDots(70, "")+" .\n");
			declaratie.add(dec1);
			declaratie.add(Chunk.TABBING);
			Chunk dec2 =  new Chunk("Prezenta s-a eliberat spre a servi la radiere.");
			declaratie.add(dec2);
			
			final Paragraph dataSiSemnatura = new Paragraph();
            dataSiSemnatura.setLeading(25.0f);
            dataSiSemnatura.setTabSettings(new TabSettings(300.0f));
            dataSiSemnatura.setIndentationLeft(65.0f);
            final Chunk data = new Chunk("Primar");
            dataSiSemnatura.add((Element)data);
            dataSiSemnatura.add((Element)Chunk.TABBING);
            final Chunk semnatura = new Chunk("Intocmit\n");
            dataSiSemnatura.add((Element)semnatura);
            final Chunk dat = new Chunk(this.getStrWithDots(30, ""));
            dataSiSemnatura.add((Element)dat);
            dataSiSemnatura.add((Element)Chunk.TABBING);
            final Chunk semn = new Chunk(this.getStrWithDots(30, ""));
            dataSiSemnatura.add((Element)semn);
            this.document.add((Element)declaratie);
            this.document.add((Element)dataSiSemnatura);
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
			final Paragraph antet =  new Paragraph();
		
			float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
			float documentHeight = document.getPageSize().getHeight()- document.topMargin() - document.bottomMargin();
			antetLogo.scaleToFit(documentWidth, documentHeight);
			
			antet.add(antetLogo);
			antet.setAlignment(1);
			document.add(antet);
			final Paragraph titlu = new Paragraph();
		
			final Chunk t1 = new Chunk("\n\n\n\nAdeverinta\n\n\n",bold2);
			titlu.setAlignment(1);
			titlu.add(t1);
			this.document.add(titlu);
			
			final Paragraph declaratie = new Paragraph();
			declaratie.setLeading(20.0f);
			declaratie.setTabSettings(new TabSettings(15.0f));
			declaratie.add(Chunk.TABBING);
			Phrase dec1 =  new Phrase();
			Chunk ch1 =  new Chunk("Se adevereste de catre noi prin prezenta ca ");
			dec1.add(ch1);
			dec1.add(map.get("titlu"));
		    dec1.add(this.getPhraseStrWithDots(60, map.get("prenume")+" "+map.get("nume")));
		    Chunk ch2 =  new Chunk("cu domiciliul in comuna Dudestii-Vechi sat ");
		    dec1.add(ch2);
		    dec1.add(this.getPhraseStrWithDots(30, map.get("localitate")));
		    Chunk ch3 =  new Chunk(" numarul ");
		    dec1.add(ch3);
		    dec1.add(this.getPhraseStrWithDots(10, map.get("nrStrada")));
		    Chunk ch4 =  new Chunk(" detine spatiu de depozitare pentru autoturismul marca ");
		    dec1.add(ch4);
		    dec1.add(this.getPhraseStrWithDots(20, map.get("marca")));
		    Chunk ch5 =  new Chunk(" model ");
		    dec1.add(ch5);
		    dec1.add(this.getPhraseStrWithDots(20, map.get("model")));
		    Chunk ch6 =  new Chunk(" capacitate cilindrica ");
		    dec1.add(ch6);
		    dec1.add(this.getPhraseStrWithDots(15, map.get("capacitate")));
		    Chunk ch7 =  new Chunk(" serie motor ");
		    dec1.add(ch7);
		    dec1.add(this.getPhraseStrWithDots(25, map.get("serieMotor")));
		    Chunk ch8 =  new Chunk(" serie sasiu ");
		    dec1.add(ch8);
		    dec1.add(this.getPhraseStrWithDots(40, map.get("serieSasiu")));
		    Chunk ch9 =  new Chunk(" numar de inmatriculare ");
		    dec1.add(ch9);
		    dec1.add(this.getPhraseStrWithDots(20, map.get("nrInmatriculare")));
		    Chunk ch10 =  new Chunk(" la adresa ");
		    dec1.add(ch10);
		    dec1.add(this.getPhraseStrWithDots(70, map.get("addrDepozitare")));
		    Chunk ch11 =  new Chunk(" .\n");
		    dec1.add(ch11);
		    
			declaratie.add(dec1);
			declaratie.add(Chunk.TABBING);
			Chunk dec2 =  new Chunk("Prezenta s-a eliberat spre a servi la radiere.");
			declaratie.add(dec2);
			
			final Paragraph dataSiSemnatura = new Paragraph();
            dataSiSemnatura.setLeading(25.0f);
            dataSiSemnatura.setTabSettings(new TabSettings(300.0f));
            dataSiSemnatura.setIndentationLeft(65.0f);
            final Chunk data = new Chunk("Primar");
            dataSiSemnatura.add((Element)data);
            dataSiSemnatura.add((Element)Chunk.TABBING);
            final Chunk semnatura = new Chunk("Intocmit\n");
            dataSiSemnatura.add((Element)semnatura);
            final Chunk dat = new Chunk(this.getStrWithDots(30, ""));
            dataSiSemnatura.add((Element)dat);
            dataSiSemnatura.add((Element)Chunk.TABBING);
            final Chunk semn = new Chunk(map.get("intocmit"));
            dataSiSemnatura.add((Element)semn);
            this.document.add((Element)declaratie);
            this.document.add((Element)dataSiSemnatura);
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

	private Phrase getPhraseStrWithDots(final int dots, final String str) {
		final int strSize = str.length();
		final Phrase sb = new Phrase();
		int dotsRemained;
		if (strSize > dots) {
			dotsRemained = 0;
		} else {
			dotsRemained = dots - strSize;
		}
		Chunk chDots = new Chunk();
		final Chunk chStr = new Chunk("", this.bold2);
		chStr.setTextRise(1.7f);
		for (int i = 0; i < dotsRemained; ++i) {
			if (i == dotsRemained / 2) {
				chStr.append(str);
				sb.add((Element) chDots);
				sb.add((Element) chStr);
				chDots = new Chunk();
			}
			chDots.append(".");
		}
		sb.add((Element) chDots);
		return sb;
	}
}
