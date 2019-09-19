package com.spectral369.instiintareIarba;

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

public class InstiitareIarbaPDF extends CustomComponent implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "InstiitareIarbaPDF";
	public static String FNAME;
	private transient PdfWriter writer;
	// private final int fontSize = 14;
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
		InstiitareIarbaPDF.FNAME = "";
	}

	public InstiitareIarbaPDF(final Map<String, String> map) {
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
						String.valueOf(System.getProperty("java.io.tmpdir")) + File.separator + "Instiintare_Iarba_" + tm + ".pdf");
				System.out.println(this.pdfFile.getCanonicalPath());
				InstiitareIarbaPDF.FNAME = this.pdfFile.getCanonicalPath();
			} else {
				this.pdfFile = new File(
						String.valueOf(System.getProperty("java.io.tmpdir")) + File.separator + "Instiintare_Iarba_" + tm + ".pdf");
				System.out.println(this.pdfFile.getCanonicalPath());
				InstiitareIarbaPDF.FNAME = this.pdfFile.getCanonicalPath();
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
			InstiitareIarbaPDF.FNAME = "";
			MyUI.navigator.removeView("InstiitareIarba");
			MyUI.navigator.removeView("InstiintareIarbaPDF");
			MyUI.navigator.navigateTo("Index");
		});
		this.backLayout.addComponent((Component) this.backbtn);
		this.content.addComponent((Component) this.backLayout);
		this.content.setComponentAlignment((Component) this.backLayout, Alignment.MIDDLE_CENTER);
		this.content.setMargin(false);
		this.setCompositionRoot((Component) this.content);
		MyUI.navigator.addView("InstiintareIarbaPDF", this);
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
			this.document.addTitle("Instiintare_plata_Iarba_100" + tm);
			final Paragraph antet =  new Paragraph();
		
			float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
			float documentHeight = document.getPageSize().getHeight()- document.topMargin() - document.bottomMargin();
			antetLogo.scaleToFit(documentWidth, documentHeight);
			
			antet.add(antetLogo);
			antet.setAlignment(1);
			document.add(antet);
			final Paragraph catre = new Paragraph();
			catre.setLeading(20.0f);
			catre.setTabSettings(new TabSettings(15.0f));
			catre.add(Chunk.TABBING);
			final Chunk t1 = new Chunk("\n\n\n\nCatre: " + this.getStrWithDots(80, "") + "\n\n\n");
			catre.add(t1);
			this.document.add(catre);
			final Paragraph subscrisa = new Paragraph();
			subscrisa.setLeading(20.0f);
			subscrisa.setTabSettings(new TabSettings(15.0f));
			subscrisa.add(Chunk.TABBING);
			final Chunk subsemnat = new Chunk(
					"Subscrisa Comuna Dudestii-Vechi prin Dl. Primar, cu sediul in comuna\n Dudestii-Vechi"
							+ "nr. 255, jud. Timis formulez prezenta\n\n");
			subscrisa.add((Element) subsemnat);
			document.add(subscrisa);
			final Paragraph title = new Paragraph("INSTIINTARE\n\n", this.bold);
			title.setAlignment(1);
			document.add(title);
			final Paragraph declaratie = new Paragraph();
			declaratie.setLeading(20.0f);
			declaratie.setTabSettings(new TabSettings(15.0f));
			declaratie.add(Chunk.TABBING);
			Chunk dec1 = new Chunk("In vederea aplicarii dispozitiilor H.C.L 41 din 03.08.2015 coroboranta cu "
					+ "H.C.L 63 din 13.07.2019 va instiintam ca inspectorul zonal a constatat "
					+ "neindeplinirea obligatiei de\nintretinere a spatiului verde aferent sau limitrof "
					+ "imobilului situat in " + this.getStrWithDots(50, "") + " cu numarul "
					+ this.getStrWithDots(15, "") + " sens in care "
					+ "institutia noastra, prin compartimentul de specialitate a procedat la prestarea serviciilor de "
					+ "curatare si intretinere a acestor spatii verzi.\n\n");
			declaratie.add(dec1);
			declaratie.add(Chunk.TABBING);
			Chunk valoare =new Chunk("100 de RON",this.bold2);
			Chunk termen =  new Chunk("30 de zile",this.bold2);
			Phrase dec2 = new Phrase();
			Chunk dec21 =  new Chunk("Astfel ca aveti de achitat taxa speciala in cuantum de "); 
			dec2.add(dec21);
			dec2.add(valoare);
			Chunk dec22 =  new Chunk(" pentru acest serviciu in termen de ");
			dec2.add(dec22);
			dec2.add(termen);
			Chunk dec23 =  new Chunk(" de la data comunicarii prezentei instiintari.\n\n");
			dec2.add(dec23);
			
			declaratie.add(dec2);
			declaratie.add(Chunk.TABBING);
			Chunk dec3 = new Chunk(
					"Totodata va aducem la cunostiinta ca in baza noastra de taxe si impozite a fost\n introdus cuantumul acestei taxe.\n\n");
			declaratie.add(dec3);
			document.add(declaratie);

			final Paragraph dataSiSemnatura = new Paragraph();
			dataSiSemnatura.setLeading(25.0f);
			dataSiSemnatura.setTabSettings(new TabSettings(300.0f));
			dataSiSemnatura.setIndentationLeft(300.0f);
			final Chunk semnatura = new Chunk("Primar\n");
			dataSiSemnatura.add((Element) semnatura);
			//dataSiSemnatura.add(Chunk.NEWLINE);
			final Chunk semn = new Chunk(this.getStrWithDots(30, ""));
			dataSiSemnatura.add(semn);
			final PdfContentByte canvas = this.writer.getDirectContent();
			this.document.add((Element) dataSiSemnatura);
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
			this.document.addTitle("Instiintare_plata_Iarba_100" + tm);
			final Paragraph antet =  new Paragraph();
			
			float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
			float documentHeight = document.getPageSize().getHeight()- document.topMargin() - document.bottomMargin();
			antetLogo.scaleToFit(documentWidth, documentHeight);
			
			antet.add(antetLogo);
			antet.setAlignment(1);
			document.add(antet);
			final Paragraph catre = new Paragraph();
			catre.setLeading(20.0f);
			catre.setTabSettings(new TabSettings(15.0f));
			catre.add(Chunk.TABBING);
			Phrase t1 =  new Phrase();
			Chunk t11 =  new Chunk("\n\n\n\nCatre: ");
			t1.add(t11);
			t1.add(this.getPhraseStrWithDots(80, map.get("prenume")+" "+map.get("nume")));
			Chunk t12 =  new Chunk(".\n\n\n");
			t1.add(t12);
			catre.add(t1);
			this.document.add(catre);
			final Paragraph subscrisa = new Paragraph();
			subscrisa.setLeading(20.0f);
			subscrisa.setTabSettings(new TabSettings(15.0f));
			subscrisa.add(Chunk.TABBING);
			final Chunk subsemnat = new Chunk(
					"Subscrisa Comuna Dudestii-Vechi prin Dl. Primar, cu sediul in comuna\n Dudestii-Vechi"
							+ "nr. 255, jud. Timis formulez prezenta\n\n");
			subscrisa.add((Element) subsemnat);
			document.add(subscrisa);
			final Paragraph title = new Paragraph("INSTIINTARE\n\n", this.bold);
			title.setAlignment(1);
			document.add(title);
			final Paragraph declaratie = new Paragraph();
			declaratie.setLeading(20.0f);
			declaratie.setTabSettings(new TabSettings(15.0f));
			declaratie.add(Chunk.TABBING);
			Phrase dec1  =  new Phrase();
			Chunk dec11 = new Chunk("In vederea aplicarii dispozitiilor H.C.L 41 din 03.08.2015 coroboranta cu "
					+ "H.C.L 63 din 13.07.2019 va instiintam ca inspectorul zonal a constatat "
					+ "neindeplinirea obligatiei de intretinere a spatiului verde aferent sau limitrof "
					+ "imobilului situat in"); 
			dec1.add(dec11);
			dec1.add(getPhraseStrWithDots(25, map.get("localitate")));
			Chunk dec12 =  new Chunk(" cu numarul ");
			dec1.add(dec12);
			dec1.add(this.getPhraseStrWithDots(8, map.get("nrStrada")));
			Chunk dec13 =  new Chunk(" sens in care "
					+ "institutia noastra, prin compartimentul de specialitate a procedat la prestarea serviciilor de "
					+ "curatare si intretinere a acestor spatii verzi.\n\n");
			dec1.add(dec13);
			declaratie.add(dec1);
			declaratie.add(Chunk.TABBING);
			Chunk valoare =new Chunk("100 de RON",this.bold2);
			Chunk termen =  new Chunk("30 de zile",this.bold2);
			Phrase dec2 = new Phrase();
			Chunk dec21 =  new Chunk("Astfel ca aveti de achitat taxa speciala in cuantum de "); 
			dec2.add(dec21);
			dec2.add(valoare);
			Chunk dec22 =  new Chunk(" pentru acest serviciu in termen de ");
			dec2.add(dec22);
			dec2.add(termen);
			Chunk dec23 =  new Chunk(" de la data comunicarii prezentei instiintari.\n\n");
			dec2.add(dec23);
			
			declaratie.add(dec2);
			declaratie.add(Chunk.TABBING);
			Chunk dec3 = new Chunk(
					"Totodata va aducem la cunostiinta ca in baza noastra de taxe si impozite a fost\n introdus cuantumul acestei taxe.");
			declaratie.add(dec3);
			document.add(declaratie);

			final Paragraph dataSiSemnatura = new Paragraph();
			dataSiSemnatura.setLeading(25.0f);
			dataSiSemnatura.setTabSettings(new TabSettings(300.0f));
			dataSiSemnatura.setIndentationLeft(300.0f);
			final Chunk semnatura = new Chunk("Primar\n");
			dataSiSemnatura.add((Element) semnatura);
			final Chunk semn = new Chunk(this.getStrWithDots(30, ""));
			dataSiSemnatura.add((Element) semn);
			final PdfContentByte canvas = this.writer.getDirectContent();
			this.document.add((Element) dataSiSemnatura);
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
