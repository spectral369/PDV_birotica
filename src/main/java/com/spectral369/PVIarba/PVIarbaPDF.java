package com.spectral369.PVIarba;


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

public class PVIarbaPDF extends CustomComponent implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "PVIarbaPDF";
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
		PVIarbaPDF.FNAME = "";
	}

	public PVIarbaPDF(final Map<String, String> map) {
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
						String.valueOf(System.getProperty("java.io.tmpdir")) + File.separator + "PV_Iarba_" + tm + ".pdf");
				System.out.println(this.pdfFile.getCanonicalPath());
				PVIarbaPDF.FNAME = this.pdfFile.getCanonicalPath();
			} else {
				this.pdfFile = new File(
						String.valueOf(System.getProperty("java.io.tmpdir")) + File.separator + "PV_Iarba_" + tm + ".pdf");
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
			MyUI.navigator.removeView("PVIarba");
			MyUI.navigator.removeView("PVIarbaPDF");
			MyUI.navigator.navigateTo("Index");
		});
		this.backLayout.addComponent((Component) this.backbtn);
		this.content.addComponent((Component) this.backLayout);
		this.content.setComponentAlignment((Component) this.backLayout, Alignment.MIDDLE_CENTER);
		this.content.setMargin(false);
		this.setCompositionRoot((Component) this.content);
		MyUI.navigator.addView("PVIarbaPDF", this);
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
			final Paragraph antet =  new Paragraph();
		
			float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
			float documentHeight = document.getPageSize().getHeight()- document.topMargin() - document.bottomMargin();
			antetLogo.scaleToFit(documentWidth, documentHeight);
			
			antet.add(antetLogo);
			antet.setAlignment(1);
			document.add(antet);
			final Paragraph nrInreg = new Paragraph();
			nrInreg.setAlignment(2);
			Chunk t1 =  new Chunk("Nr. "+this.getStrWithDots(15, "")+" "
					+ "data "+this.getStrWithDots(20, ""));
			nrInreg.add(t1);
			this.document.add(nrInreg);
			final Paragraph aprobat = new Paragraph();
			aprobat.setAlignment(2);
			Phrase prim =  new Phrase();
			Chunk p1 =  new Chunk("\nAproabat,");
			prim.add(p1);
			prim.add(Chunk.TABBING);
			prim.add(Chunk.TABBING);
			prim.add(Chunk.TABBING);
			prim.add(new Chunk("\n"));
			Chunk p2 =  new Chunk("Primar");
			prim.add(p2);
			prim.add(Chunk.TABBING);
			prim.add(Chunk.TABBING);
			prim.add(Chunk.TABBING);
			prim.add(Chunk.TABBING);
			prim.add(new Chunk("\n"));
			Chunk p3 =  new Chunk(this.getStrWithDots(20, ""));
			prim.add(p3);
			prim.add(Chunk.TABBING);
			prim.add(Chunk.TABBING);
			prim.add(Chunk.TABBING);
			
			
			/*final Chunk primar = new Chunk(
					"\nAprobat,\n"
					+ "Primar\n"+
							this.getStrWithDots(20, ""));*/
			aprobat.add(prim);
			document.add(aprobat);
			final Paragraph title = new Paragraph("\nProces Verbal\n\n", this.bold);
			title.setAlignment(1);
			document.add(title);
			final Paragraph declaratie = new Paragraph();
			declaratie.setLeading(20.0f);
			declaratie.setTabSettings(new TabSettings(15.0f));
			declaratie.add(Chunk.TABBING);
			Chunk dec1 = new Chunk("Incheiat azi, ziua "+this.getStrWithDots(10, "")+", luna "+this.getStrWithDots(10, "")+
					", anul "+this.getStrWithDots(15, "")+", ora "+this.getStrWithDots(20, "")+"\n");
			declaratie.add(dec1);
			
			Chunk dec2 =  new Chunk("Urmare a prevederilor H.C.L 63 din 13.07.2019, angajatii serviciului de Spatii Verzi din cadrul primariei "
					+ "comunei Dudestii-Vechi au executat urmatoarele lucrari:");
			declaratie.add(dec2);
			declaratie.add(Chunk.TABBING);
			Chunk oper = new Chunk(this.getStrWithDots(780, "")+"\n");
			declaratie.add(oper);
			Chunk dec3  =  new Chunk("in comuna Dudestii-Vechi sat "+getStrWithDots(30, "")+""
					+ "Numarul "+getStrWithDots(10, "")+" deoarece in urma sesizarilor, proprietarul sau mostenitorul imobilului"
					+ " mai sus mentionat nu a respectat Hotararea de Consiliu Local 63/13.07.2019 privind intretinerea si curatenia "
					+ "terenului public aferent imobilului.\n");
			declaratie.add(dec3);
			declaratie.add(Chunk.TABBING);
			Chunk dec4 =  new Chunk("Adresa "+getStrWithDots(138, "")+"\n");
			declaratie.add(dec4);
			declaratie.add(Chunk.TABBING);
			Chunk dec5 =  new Chunk("Nota totala pe lucrari: "+getStrWithDots(25, "")+"\n\n");
			declaratie.add(dec5);
			

			document.add(declaratie);

			final Paragraph dataSiSemnatura = new Paragraph();
            dataSiSemnatura.setLeading(25.0f);
            dataSiSemnatura.setTabSettings(new TabSettings(300.0f));
            dataSiSemnatura.setIndentationLeft(65.0f);
            final Chunk data = new Chunk("Sef serviciu spatii verzi");
            dataSiSemnatura.add((Element)data);
            dataSiSemnatura.add((Element)Chunk.TABBING);
            final Chunk semnatura = new Chunk("Intocmit\n");
            dataSiSemnatura.add((Element)semnatura);
            final Chunk dat = new Chunk(this.getStrWithDots(30, ""));
            dataSiSemnatura.add((Element)dat);
            dataSiSemnatura.add((Element)Chunk.TABBING);
            final Chunk semn = new Chunk(this.getStrWithDots(30, ""));
            dataSiSemnatura.add((Element)semn);
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

