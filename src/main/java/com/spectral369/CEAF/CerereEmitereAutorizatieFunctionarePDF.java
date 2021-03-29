package com.spectral369.CEAF;


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

public class CerereEmitereAutorizatieFunctionarePDF extends CustomComponent implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "CerereEmitereAutorizatieFunctionarePDF";
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
		CerereEmitereAutorizatieFunctionarePDF.FNAME = "";
	}

	public CerereEmitereAutorizatieFunctionarePDF (final Map<String, String> map) {
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
						+ "Cerere_Autorizatie_Functionare" + tm + ".pdf");
				System.out.println(this.pdfFile.getCanonicalPath());
				CerereEmitereAutorizatieFunctionarePDF.FNAME = this.pdfFile.getCanonicalPath();
			} else {
				this.pdfFile = new File(String.valueOf(System.getProperty("java.io.tmpdir")) + File.separator
						+ "Cerere_Autorizatie_Functionare" + tm + ".pdf");
				System.out.println(this.pdfFile.getCanonicalPath());
				CerereEmitereAutorizatieFunctionarePDF.FNAME = this.pdfFile.getCanonicalPath();
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
			CerereEmitereAutorizatieFunctionarePDF.FNAME = "";
			MyUI.navigator.removeView("CerereEmitereAutorizatieFunctionareInfo");
			MyUI.navigator.removeView("CerereEmitereAutorizatieFunctionarePDF");
			MyUI.navigator.navigateTo("Index");
		});
		this.backLayout.addComponent((Component) this.backbtn);
		this.content.addComponent((Component) this.backLayout);
		this.content.setComponentAlignment((Component) this.backLayout, Alignment.MIDDLE_CENTER);
		this.content.setMargin(false);
		this.setCompositionRoot((Component) this.content);
		MyUI.navigator.addView("CerereEmitereAutorizatieFunctionarePDF", this);
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
			this.document.addTitle("Cerere_Autorizatie_Functionare" + tm);
			final Paragraph antet = new Paragraph();

			float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
			float documentHeight = document.getPageSize().getHeight() - document.topMargin() - document.bottomMargin();
			antetLogo.scaleToFit(documentWidth, documentHeight);

			antet.add(antetLogo);
			antet.setAlignment(1);
			document.add(antet);
			
			final Paragraph nrInreg = new Paragraph();
			nrInreg.setAlignment(2);
			Chunk nr = new Chunk("\n\nNr. " + this.getStrWithDots(15, "") + " " + "data " + this.getStrWithDots(20, "")+"\n");
			nrInreg.add(nr);
			this.document.add(nrInreg);
			
			final Paragraph aprobat = new Paragraph();
			aprobat.setAlignment(2);
			Phrase prim = new Phrase();
			Chunk p1 = new Chunk("\nAprobat,");
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
			aprobat.add(prim);
			document.add(aprobat);
			
			
			
			
			final Paragraph titlu = new Paragraph();

			final Chunk t1 = new Chunk("\n\n\n\nCERERE\n", bold2);
			titlu.setAlignment(1);
			titlu.add(t1);
			Chunk t11 =  new Chunk("pentru emiterea acordului de functionare pentru exercitii comerciale in baza legii 650/2002\n\n\n");
			titlu.add(t11);
			this.document.add(titlu);
/*
			final Paragraph nrInreg = new Paragraph();
			nrInreg.setAlignment(2);
			Chunk t2 = new Chunk(
					"Nr. " + this.getStrWithDots(15, "") + " " + "data " + this.getStrWithDots(20, "") + "\n\n");
			nrInreg.add(t2);
			this.document.add(nrInreg);*/

			final Paragraph declaratie = new Paragraph();
			declaratie.setAlignment(1);
			declaratie.setLeading(20.0f);
			declaratie.setTabSettings(new TabSettings(15.0f));
			declaratie.add(Chunk.TABBING);
			Chunk dec1 = new Chunk("Subsemnatul " + this.getStrWithDots(60, "") + " cu domiciliul in "+this.getStrWithDots(42, "")+
					" strada "+this.getStrWithDots(20, "")+" numarul "+this.getStrWithDots(8, "")+
					" bloc "+this.getStrWithDots(8, "")+" scara "+this.getStrWithDots(8, "")+
					" apartament "+this.getStrWithDots(8, "")+" telefon "+this.getStrWithDots(24, "")+
					" in calitate de "+this.getStrWithDots(20, "")+" al S.C/P.F/I.I/I.F "+this.getStrWithDots(25, "")+
					" cu sediul in "+this.getStrWithDots(35, "")+" strada "+this.getStrWithDots(20, "")+
					" numarul "+this.getStrWithDots(8, "")+ " bloc "+this.getStrWithDots(8, "")+" bloc "+this.getStrWithDots(8, "")+
					" scara "+this.getStrWithDots(8, "")+" apartament "+this.getStrWithDots(8, "")+
					" inmatriculat la ORC "+this.getStrWithDots(10, "")+" C.U.I "+this.getStrWithDots(12, "")+
					" solicit obtinerea acordului Primariei comunei Dudestii-Vechi pentru desfasurarea la luctul de lucru situat"
					+ " in "+this.getStrWithDots(25, "")+" numarul "+this.getStrWithDots(8, "")+" bloc "+this.getStrWithDots(8, "")+
					" scara "+this.getStrWithDots(8, "")+" apartament "+this.getStrWithDots(8, "")+" a urmatoarelor exercitii comerciale "
							+ "conform cu Legea 650/2002.\n\n\n");
			declaratie.add(dec1);
			
			Paragraph declaratie2 =  new Paragraph();
			declaratie2.add(Chunk.TABBING);
			
			Chunk dec11 = new Chunk("Cod CAEN "+this.getStrWithDots(25, "")+" Denumire "+this.getStrWithDots(80, "")+"\n");
			declaratie2.add(dec11);
			declaratie2.add(Chunk.TABBING);
			Chunk dec12 = new Chunk("Cod CAEN "+this.getStrWithDots(25, "")+" Denumire "+this.getStrWithDots(80, "")+"\n");
			declaratie2.add(dec12);
			declaratie2.add(Chunk.TABBING);
			Chunk dec13 = new Chunk("Cod CAEN "+this.getStrWithDots(25, "")+" Denumire "+this.getStrWithDots(80, "")+"\n");
			declaratie2.add(dec13);
			declaratie2.add(Chunk.TABBING);
			Chunk dec14 = new Chunk("Cod CAEN "+this.getStrWithDots(25, "")+" Denumire "+this.getStrWithDots(80, "")+"\n");
			declaratie2.add(dec14);
			declaratie2.add(Chunk.TABBING);
			Chunk dec15 = new Chunk("Cod CAEN "+this.getStrWithDots(25, "")+" Denumire "+this.getStrWithDots(80, "")+"\n\n\n");
			declaratie2.add(dec15);
			
			Paragraph orar1 =  new Paragraph();
			orar1.add(Chunk.TABBING);
			orar1.setTabSettings(new TabSettings(80f));
			orar1.setIndentationLeft(25f);
			Chunk ora1 =  new Chunk("Orarul de functionare propus: Luni-Vineri "+this.getStrWithDots(25, "")+"\n");
			orar1.add(ora1);
			
			Paragraph orar2 =  new Paragraph();
			orar2.add(Chunk.TABBING);
			orar2.setTabSettings(new TabSettings(200f));
			orar2.setIndentationLeft(64f);
			Chunk ora2 =  new Chunk("Sambata "+this.getStrWithDots(25, "")+"\n");
			orar2.add(ora2);
			
			Paragraph orar3 =  new Paragraph();
			orar3.add(Chunk.TABBING);
			orar3.setTabSettings(new TabSettings(200f));
			orar3.setIndentationLeft(64f);
			Chunk ora3 =  new Chunk("Duminica "+this.getStrWithDots(25, "")+"\n");
			orar3.add(ora3);

			
			
			document.add(declaratie);
			document.add(declaratie2);
			document.add(orar1);
			document.add(orar2);
			document.add(orar3);
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
			this.document.addTitle("Cerere_Autorizatie_Functionare" + tm);
			final Paragraph antet = new Paragraph();

			float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
			float documentHeight = document.getPageSize().getHeight() - document.topMargin() - document.bottomMargin();
			antetLogo.scaleToFit(documentWidth, documentHeight);

			antet.add(antetLogo);
			antet.setAlignment(1);
			document.add(antet);
			final Paragraph titlu = new Paragraph();

			final Chunk t1 = new Chunk("\n\n\n\nInchiriere Utilaje\n\n\n", bold2);
			titlu.setAlignment(1);
			titlu.add(t1);
			this.document.add(titlu);
			final Paragraph nrInreg = new Paragraph();
			nrInreg.setAlignment(2);
			Chunk t2 = new Chunk(
					"Nr. " + this.getStrWithDots(15, "") + " " + "data " + this.getStrWithDots(20, "") + "\n\n");
			nrInreg.add(t2);
			this.document.add(nrInreg);

			final Paragraph declaratie = new Paragraph();
			declaratie.setAlignment(1);
			declaratie.setLeading(20.0f);
			declaratie.setTabSettings(new TabSettings(15.0f));
			declaratie.add(Chunk.TABBING);
			Phrase dec1 = new Phrase();
			Chunk ph1 = new Chunk("Subsemnatul ");
			dec1.add(ph1);
			dec1.add(this.getPhraseStrWithDots(30, map.get("prenume") + " " + map.get("nume")));
			Chunk ph2 = new Chunk(" avand C.N.P ");
			dec1.add(ph2);
			dec1.add(this.getPhraseStrWithDots(30, map.get("cnp")));
			Chunk ph3 = new Chunk(" domiciliat in comuna ");
			dec1.add(ph3);
			declaratie.add(dec1);
			Phrase dec11 = new Phrase();
			Chunk ph4 = new Chunk("Dudestii-Vechi la numarul ");
			dec11.add(ph4);
			dec11.add(this.getPhraseStrWithDots(12, map.get("nrCasaAddr")));
			Chunk ph5 = new Chunk(" telefon ");
			dec11.add(ph5);
			dec11.add(this.getPhraseStrWithDots(20, map.get("telefon")));
			Chunk ph6 = new Chunk(" .\n");
			dec11.add(ph6);

			declaratie.add(dec11);
			declaratie.add(Chunk.TABBING);
			Phrase dec2 = new Phrase();
			Chunk dec21 = new Chunk("Doresc sa inchiriez utilajele Primariei Dudestii-Vechi in data de ");
			dec2.add(dec21);
			dec2.add(this.getPhraseStrWithDots(25,
					map.get("ziuaLuc") + "-" + map.get("lunaLuc") + "-" + map.get("anulLuc")));
			Chunk dec22 = new Chunk(" la adresa Dudestii-Vechi numarul ");
			dec2.add(dec22);
			dec2.add(this.getPhraseStrWithDots(10, map.get("nrCasaAddrLuc")));
			Chunk dec23 = new Chunk(" .\n\n\n");
			dec2.add(dec23);

			declaratie.add(dec2);

			this.document.add(declaratie);
			// work
			PdfPTable table = new PdfPTable(4);
			PdfPCell cell = new PdfPCell(new Phrase("Utilaj"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Pret / Ora"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Nr. Ore lucrate"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Total"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			// row1
			cell = new PdfPCell(new Phrase("Tractor + Remorca"));
			// cell.setRowspan(2);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("80 Ron"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(map.get("tractorRemorca")));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			int tr = (Integer.parseInt(map.get("tractorRemorca")) * 80);
			cell = new PdfPCell(new Phrase(String.valueOf(tr)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			// row2
			cell = new PdfPCell(new Phrase("Ifron"));
			// cell.setRowspan(3);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("80 Ron"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(map.get("ifron")));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			int ifron = (Integer.parseInt(map.get("ifron")) * 80);
			cell = new PdfPCell(new Phrase(String.valueOf(ifron)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			// Row3
			cell = new PdfPCell(new Phrase("Tractor cu utilaj"));
			// cell.setRowspan(4);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("80 Ron"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(map.get("tractorUtilaj")));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			int tu = (Integer.parseInt(map.get("tractorUtilaj")) * 80);
			cell = new PdfPCell(new Phrase(String.valueOf(tu)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			// row4
			cell = new PdfPCell(new Phrase("Minicastor"));
			// cell.setRowspan(5);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("70 Ron"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(map.get("minicastor")));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			int minicastor = (Integer.parseInt(map.get("minicastor")) * 70);
			cell = new PdfPCell(new Phrase(String.valueOf(minicastor)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			// row 5
			cell = new PdfPCell(new Phrase("Total"));
			// cell.setRowspan(2);
			cell.setColspan(2);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(map.get("totalOre")));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(map.get("sumaTotal")));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			document.add(table);

			// work

			final Paragraph declaratie2 = new Paragraph();
			declaratie2.setAlignment(1);
			declaratie2.setLeading(20.0f);
			declaratie2.setTabSettings(new TabSettings(15.0f));
			declaratie2.add(Chunk.TABBING);
			Chunk dec4 = new Chunk(
					"Subsemnatul este obligat sa platesca inchirierea utilajelor in termen de 48 de ore de"
							+ " la terminarea lucrarilor la caseria Primariei Dudestii-Vechi.\n");
			declaratie2.add(dec4);
			declaratie2.add(Chunk.TABBING);
			Chunk dec5 = new Chunk(
					"In cazul neplatii in termen de 48 de ore se va impune ca debit la persoana solicitanta "
							+ "ca taxa 'prestari servicii' de catre departamentul Taxe si Impozite.\n\n\n\n");
			declaratie2.add(dec5);
			document.add(declaratie2);

			final Paragraph dataSiSemnatura = new Paragraph();
			dataSiSemnatura.setLeading(25.0f);
			dataSiSemnatura.setTabSettings(new TabSettings(300.0f));
			dataSiSemnatura.setIndentationLeft(65.0f);
			final Chunk data = new Chunk("Semnatura Solicitant");
			dataSiSemnatura.add((Element) data);
			dataSiSemnatura.add((Element) Chunk.TABBING);
			final Chunk semnatura = new Chunk("Viceprimar\n");
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
			int nrLitereMari = 0;
			int nrLitereMici = 0;
			for (int k = 0; k < str.length(); k++) {
				if (Character.isUpperCase(str.charAt(k)))
					nrLitereMari++;
				if (Character.isLowerCase(str.charAt(k)))
					nrLitereMici++;
			}
			if (nrLitereMari > 0) {
				dotsRemained = dots - (nrLitereMari * 2);
				dotsRemained = dotsRemained - nrLitereMici;
			} else {
				dotsRemained = dots - strSize;
			}
			// dotsRemained = dots - strSize;
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

