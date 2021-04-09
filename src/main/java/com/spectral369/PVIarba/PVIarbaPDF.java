package com.spectral369.PVIarba;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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

public class PVIarbaPDF extends CustomComponent implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "PVIarbaPDF";
	public static String FNAME;
	private transient PdfWriter writer;
	// private final int fontSize = 14;
	private final transient Font bold;
	private final transient Font bold2;
	private final transient Font small;
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
		PVIarbaPDF.FNAME = "";
	}

	public PVIarbaPDF(final Map<String, String> map) {
		this.writer = null;
		this.bold = FontFactory.getFont("Times-Roman", 14.0f, 1);
		this.bold2 = FontFactory.getFont("Times-Roman", 12.0f, 1);
		this.small =  FontFactory.getFont("Times-Roman",9.0f,1);
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
						+ "PV_Iarba_" + tm + ".pdf");
				System.out.println(this.pdfFile.getCanonicalPath());
				PVIarbaPDF.FNAME = this.pdfFile.getCanonicalPath();
			} else {
				this.pdfFile = new File(String.valueOf(System.getProperty("java.io.tmpdir")) + File.separator
						+ "PV_Iarba_" + tm + ".pdf");
				System.out.println(this.pdfFile.getCanonicalPath());
				PVIarbaPDF.FNAME = this.pdfFile.getCanonicalPath();
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
			PVIarbaPDF.FNAME = "";
			UI.getCurrent().getNavigator().removeView("PVIarba");
			UI.getCurrent().getNavigator().removeView("PVIarbaPDF");
			UI.getCurrent().getNavigator().navigateTo("Index");
		});
		this.backLayout.addComponent((Component) this.backbtn);
		this.content.addComponent((Component) this.backLayout);
		this.content.setComponentAlignment((Component) this.backLayout, Alignment.MIDDLE_CENTER);
		this.content.setMargin(false);
		this.setCompositionRoot((Component) this.content);
		UI.getCurrent().getNavigator().addView("PVIarbaPDF", this);
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
			this.document.addTitle("Proces_Verbal_Iarba_100" + tm);
			final Paragraph antet = new Paragraph();

			float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
			float documentHeight = document.getPageSize().getHeight() - document.topMargin() - document.bottomMargin();
			antetLogo.scaleToFit(documentWidth, documentHeight);

			antet.add(antetLogo);
			antet.setAlignment(1);
			document.add(antet);
			document.add(new Chunk(Utils.getRandomStringSerial("PVSP"),small));
			final Paragraph nrInreg = new Paragraph();
			nrInreg.setAlignment(2);
			Chunk t1 = new Chunk("Nr. " + this.getStrWithDots(15, "") + " " + "data " + this.getStrWithDots(20, ""));
			nrInreg.add(t1);
			this.document.add(nrInreg);
			final Paragraph aprobat = new Paragraph();
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
			Chunk p3 = new Chunk(this.getStrWithDots(20, ""));
			prim.add(p3);
			prim.add(Chunk.TABBING);
			prim.add(Chunk.TABBING);
			prim.add(Chunk.TABBING);

			/*
			 * final Chunk primar = new Chunk( "\nAprobat,\n" + "Primar\n"+
			 * this.getStrWithDots(20, ""));
			 */
			aprobat.add(prim);
			document.add(aprobat);
			final Paragraph title = new Paragraph("\nProces Verbal\n\n", this.bold);
			title.setAlignment(1);
			document.add(title);
			final Paragraph declaratie = new Paragraph();
			declaratie.setLeading(20.0f);
			declaratie.setTabSettings(new TabSettings(15.0f));
			declaratie.add(Chunk.TABBING);
			Chunk dec1 = new Chunk(
					"Incheiat azi, ziua " + this.getStrWithDots(10, "") + ", luna " + this.getStrWithDots(10, "")
							+ ", anul " + this.getStrWithDots(15, "") + ", ora " + this.getStrWithDots(14, "") + " .\n");
			declaratie.add(dec1);
			declaratie.add(Chunk.TABBING);
			Chunk contribuabil = new Chunk("S-a constatat ca contribuabilul " + this.getStrWithDots(35, "") + ""
					+ ", avand C.N.P " + this.getStrWithDots(30, "")
					+ " nu si-a indeplinit obligatiile de ingrijire a spatiului verde "
					+ "aferent imobilului aflat in proprietate sau ingrijire.\n");
			declaratie.add(contribuabil);
			declaratie.add(Chunk.TABBING);

			Chunk dec2 = new Chunk(
					"Urmare a prevederilor H.C.L 63 din 13.07.2019, angajatii serviciului de Spatii Verzi din cadrul primariei "
							+ "comunei Dudestii-Vechi au executat urmatoarele lucrari:");
			declaratie.add(dec2);
			declaratie.add(Chunk.TABBING);
			Chunk oper = new Chunk(this.getStrWithDots(780, "") + "\n");
			declaratie.add(oper);
			Chunk dec3 = new Chunk("in comuna Dudestii-Vechi sat " + getStrWithDots(40, "") + "" + "Numarul "
					+ getStrWithDots(12, "") + " deoarece in urma sesizarilor, proprietarul sau mostenitorul imobilului"
					+ " mai sus mentionat nu a respectat Hotararea de Consiliu Local 63/13.07.2019 privind intretinerea si curatenia "
					+ "terenului public aferent imobilului.\n");
			declaratie.add(dec3);
			declaratie.add(Chunk.TABBING);
			Chunk dec4 = new Chunk("Adresa imobilului" + getStrWithDots(120, "") + " .\n");
			declaratie.add(dec4);
			declaratie.add(Chunk.TABBING);
			Chunk dec5 = new Chunk("\nNota totala pe lucrari: " + getStrWithDots(25, "") + "\n\n");
			declaratie.add(dec5);

			document.add(declaratie);

			final Paragraph dataSiSemnatura = new Paragraph();
			dataSiSemnatura.setLeading(25.0f);
			dataSiSemnatura.setTabSettings(new TabSettings(300.0f));
			dataSiSemnatura.setIndentationLeft(65.0f);
			final Chunk data = new Chunk("Sef serviciu spatii verzi");
			dataSiSemnatura.add((Element) data);
			dataSiSemnatura.add((Element) Chunk.TABBING);
			final Chunk semnatura = new Chunk("Intocmit\n");
			dataSiSemnatura.add((Element) semnatura);
			final Chunk dat = new Chunk(this.getStrWithDots(30, ""));
			dataSiSemnatura.add((Element) dat);
			dataSiSemnatura.add((Element) Chunk.TABBING);
			final Chunk semn = new Chunk(this.getStrWithDots(30, ""));
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

	private void generatePDF(final String tm, final File pdfFile, final Map<String, String> map) {
		try {
			this.document = new Document();

			this.document.addAuthor("spectral369");
			this.document.addCreationDate();
			this.document.addHeader("Page count", String.valueOf(this.document.getPageNumber() + 1));
			this.document.setPageSize(PageSize.A4);
			this.writer = PdfWriter.getInstance(this.document, (OutputStream) new FileOutputStream(pdfFile));
			this.document.open();
			this.document.addTitle("Proces_Verbal_Iarba_100" + tm);
			final Paragraph antet = new Paragraph();

			float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
			float documentHeight = document.getPageSize().getHeight() - document.topMargin() - document.bottomMargin();
			antetLogo.scaleToFit(documentWidth, documentHeight);

			antet.add(antetLogo);
			antet.setAlignment(1);
			document.add(antet);
			document.add(new Chunk(Utils.getRandomStringSerial("PVSP"),small));
			final Paragraph nrInreg = new Paragraph();
			nrInreg.setAlignment(2);
			Chunk t1 = new Chunk("Nr. " + this.getStrWithDots(15, "") + " " + "data " + this.getStrWithDots(20, ""));
			nrInreg.add(t1);
			this.document.add(nrInreg);
			final Paragraph aprobat = new Paragraph();
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
			Chunk p3 = new Chunk(this.getStrWithDots(20, ""));
			prim.add(p3);
			prim.add(Chunk.TABBING);
			prim.add(Chunk.TABBING);
			prim.add(Chunk.TABBING);

			/*
			 * final Chunk primar = new Chunk( "\nAprobat,\n" + "Primar\n"+
			 * this.getStrWithDots(20, ""));
			 */
			aprobat.add(prim);
			document.add(aprobat);
			final Paragraph title = new Paragraph("\nProces Verbal\n\n", this.bold);
			title.setAlignment(1);
			document.add(title);
			final Paragraph declaratie = new Paragraph();
			declaratie.setLeading(20.0f);
			declaratie.setTabSettings(new TabSettings(15.0f));
			declaratie.add(Chunk.TABBING);
			Phrase dec1 = new Phrase();
			Chunk dec11 = new Chunk("Incheiat azi, ziua ");
			dec1.add(dec11);
			dec1.add(this.getPhraseStrWithDots(10, map.get("ziua")));
			Chunk dec12 = new Chunk(", luna ");
			dec1.add(dec12);
			dec1.add(getPhraseStrWithDots(10, map.get("luna")));
			Chunk dec13 = new Chunk(", anul ");
			dec1.add(dec13);
			dec1.add(getPhraseStrWithDots(15, map.get("anul")));
			Chunk dec14 = new Chunk(", ora ");
			dec1.add(dec14);
			dec1.add(this.getPhraseStrWithDots(14, map.get("time")));
			Chunk dec15 = new Chunk(" .\n");
			dec1.add(dec15);

			declaratie.add(dec1);
			declaratie.add(Chunk.TABBING);
			Phrase contribuabil = new Phrase();
			Chunk con1 = new Chunk("S-a constatat ca contribuabilul ");
			contribuabil.add(con1);
			contribuabil.add(this.getPhraseStrWithDots(22, map.get("contribuabil")));
			Chunk con2 = new Chunk(", avand C.N.P ");
			contribuabil.add(con2);
			contribuabil.add(this.getPhraseStrWithDots(28, map.get("cnp")));
			Chunk con3 = new Chunk(" nu si-a indeplinit obligatiile de ingrijire a spatiului verde "
					+ "aferent imobilului aflat in proprietate sau ingrijire\n");
			contribuabil.add(con3);
			declaratie.add(contribuabil);
			declaratie.add(Chunk.TABBING);

			Chunk dec2 = new Chunk(
					"Urmare a prevederilor H.C.L 63 din 13.07.2019, angajatii serviciului de Spatii Verzi din cadrul primariei "
							+ "comunei Dudestii-Vechi au executat urmatoarele lucrari:\n");
			declaratie.add(dec2);
			
		//	Chunk oper = new Chunk(this.getStrWithDots(780, "") + "\n");
			declaratie.add(getJobsByLine(map.get("job")));
			Phrase dec3 = new Phrase();
			Chunk dec31 = new Chunk("in comuna Dudestii-Vechi sat ");
			dec3.add(dec31);
			dec3.add(this.getPhraseStrWithDots(40, map.get("localitate")));
			Chunk dec32 = new Chunk(" numarul ");
			dec3.add(dec32);
			dec3.add(this.getPhraseStrWithDots(12, map.get("nrCasa")));
			Chunk dec33 = new Chunk(" deoarece in urma sesizarilor, proprietarul sau mostenitorul imobilului"
					+ " mai sus mentionat nu a respectat Hotararea de Consiliu Local 63/13.07.2019 privind intretinerea si curatenia "
					+ "terenului public aferent imobilului.\n");
			dec3.add(dec33);

			declaratie.add(dec3);
			declaratie.add(Chunk.TABBING);
			Phrase dec4 = new Phrase();
			Chunk dec41 = new Chunk("Adresa imobilului ");
			dec4.add(dec41);
			int size2 =  120 -map.get("adresa").length();
			dec4.add(this.getPhraseStrWithDots(size2, map.get("adresa")));
			Chunk dec42 = new Chunk(" .\n");
			dec4.add(dec42);

			declaratie.add(dec4);
			declaratie.add(Chunk.TABBING);
			Phrase dec5 = new Phrase();
			Chunk dec51 = new Chunk("\nNota totala pe lucrari ");
			dec5.add(dec51);
			dec5.add(this.getPhraseStrWithDots(25, map.get("suma")));
			Chunk dec52 = new Chunk(" .\n\n");
			dec5.add(dec52);
			declaratie.add(dec5);

			document.add(declaratie);

			final Paragraph dataSiSemnatura = new Paragraph();
			dataSiSemnatura.setLeading(25.0f);
			dataSiSemnatura.setTabSettings(new TabSettings(300.0f));
			dataSiSemnatura.setIndentationLeft(65.0f);
			final Chunk data = new Chunk("Sef serviciu spatii verzi");
			dataSiSemnatura.add((Element) data);
			dataSiSemnatura.add((Element) Chunk.TABBING);
			final Chunk semnatura = new Chunk("Intocmit\n");
			dataSiSemnatura.add((Element) semnatura);
			final Chunk dat = new Chunk(map.get("sef"));
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

	/*private Phrase getPhraseStrWithDots(final int dots, final String str) {
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
	}*/
	private Phrase getPhraseStrWithDots(final int dots, final String str) {
		final int strSize = str.length();
		final Phrase sb = new Phrase();
		int dotsRemained;
		if (strSize > dots) {
			dotsRemained = 0;
		} else {
			int nrLitereMari = 0;
			int nrLitereMici = 0;
			for (int k = 0; k < str.length(); k++) {
			 if (Character.isUpperCase(str.charAt(k))) nrLitereMari++;
			 if (Character.isLowerCase(str.charAt(k))) nrLitereMici++;
			}
			if(nrLitereMari>0) {
			dotsRemained = dots-(nrLitereMari*2);
			dotsRemained = dotsRemained-nrLitereMici;
			}else {
			   dotsRemained = dots - strSize;
			}
			//dotsRemained = dots - strSize;
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
	

	private Phrase getJobsByLine(final String job) {
		Phrase ph = new Phrase();
		for (String line : job.split("\n")) {
			int size =  156-line.length();
			ph.add(getPhraseStrWithDots(size, line));
			ph.add(Chunk.NEWLINE);
		}
		return ph;
	}
	
}
