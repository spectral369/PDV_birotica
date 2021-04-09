package com.spectral369.Utilaje;

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
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
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

public class InchiriereUtilajePDF extends CustomComponent implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "InchiriereUtilajePDF";
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
		InchiriereUtilajePDF.FNAME = "";
	}

	public InchiriereUtilajePDF(final Map<String, String> map) {
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
						+ "Inchiriere_utilaje_" + tm + ".pdf");
				System.out.println(this.pdfFile.getCanonicalPath());
				InchiriereUtilajePDF.FNAME = this.pdfFile.getCanonicalPath();
			} else {
				this.pdfFile = new File(String.valueOf(System.getProperty("java.io.tmpdir")) + File.separator
						+ "Inchiriere_Utilaje_" + tm + ".pdf");
				System.out.println(this.pdfFile.getCanonicalPath());
				InchiriereUtilajePDF.FNAME = this.pdfFile.getCanonicalPath();
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
			InchiriereUtilajePDF.FNAME = "";
			UI.getCurrent().getNavigator().removeView("InchiriereUtilajeInfo");
			UI.getCurrent().getNavigator().removeView("InchiriereUtilajePDF");
			UI.getCurrent().getNavigator().navigateTo("Index");
		});
		this.backLayout.addComponent((Component) this.backbtn);
		this.content.addComponent((Component) this.backLayout);
		this.content.setComponentAlignment((Component) this.backLayout, Alignment.MIDDLE_CENTER);
		this.content.setMargin(false);
		this.setCompositionRoot((Component) this.content);
		UI.getCurrent().getNavigator().addView("InchiriereUtilajePDF", this);
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
			this.document.addTitle("Inchiriere_utilaje_" + tm);
			final Paragraph antet = new Paragraph();

			float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
			float documentHeight = document.getPageSize().getHeight() - document.topMargin() - document.bottomMargin();
			antetLogo.scaleToFit(documentWidth, documentHeight);

			antet.add(antetLogo);
			antet.setAlignment(1);
			document.add(antet);
			final Paragraph titlu = new Paragraph();
			
			final Paragraph nrInreg = new Paragraph();
			nrInreg.setAlignment(0);
			Chunk t2 = new Chunk("\n\nNr. " + PDFHelper.getStrWithDots(15, "") + " " + "data "
					+ PDFHelper.getStrWithDots(20, "") + "\n\n");
			nrInreg.add(t2);
			this.document.add(nrInreg);

			final Chunk t1 = new Chunk("\n\nCERERE PRIVIND\nINCHIRIEREA UTILAJELOR\n\n", bold2);
			titlu.setAlignment(1);
			titlu.add(t1);
			this.document.add(titlu);

			

			final Paragraph declaratie = new Paragraph();
			declaratie.setAlignment(1);
			declaratie.setLeading(20.0f);
			declaratie.setTabSettings(new TabSettings(15.0f));
			declaratie.add(Chunk.TABBING);
			Chunk dec1 = new Chunk("Subsemnatul " + PDFHelper.getStrWithDots(30, "") + " avand C.N.P "
					+ PDFHelper.getStrWithDots(30, "") + " domiciliat in comuna\n");
			declaratie.add(dec1);

			Chunk dec11 = new Chunk("Dudestii-Vechi la numarul " + PDFHelper.getStrWithDots(12, "") + " telefon "
					+ PDFHelper.getStrWithDots(20, "") + " .\n");

			declaratie.add(dec11);
			declaratie.add(Chunk.TABBING);
			Chunk dec2 = new Chunk("Doresc sa inchiriez utilajele primariei Dudestii-Vechi in data de "
					+ PDFHelper.getStrWithDots(25, "") + " la adresa Dudestii-Vechi numarul "
					+ PDFHelper.getStrWithDots(10, "") + " .\n\n\n");
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
			cell = new PdfPCell();
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.addElement(new Phrase(""));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(""));
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
			cell = new PdfPCell();
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.addElement(new Phrase(""));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(""));
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
			cell = new PdfPCell();
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.addElement(new Phrase(""));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(""));
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
			cell = new PdfPCell();
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.addElement(new Phrase(""));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(""));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			// row 5
			cell = new PdfPCell(new Phrase("Total"));
			// cell.setRowspan(2);
			cell.setColspan(2);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new PdfPCell();
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.addElement(new Phrase(""));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(""));
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
					"\n * Tarife stabilite in conformitate cu prevederile H.C.L Dudestii-Vechi  nr.28 din 11.08.2016.\n\n");
			declaratie2.add(dec4);
			declaratie2.add(Chunk.TABBING);
			Chunk dec5 = new Chunk(
					"Prezenta cerere este valabila doar insotita de dovada achitarii"
					+ " taxei, conform estimarilor facute de catre solicitant.\n");
			declaratie2.add(dec5);
			declaratie2.add(Chunk.TABBING);
			Chunk dec6 = new Chunk(
					"Serviciul va fi prestat in limita taxei achitate urmand ca prestarea unui serviciu suplimentar"
					+ " sa implice achitarea diferentei de taxa, raportat la numarul de ore de lucru.\n");
			declaratie2.add(dec6);
			declaratie2.add(Chunk.TABBING);
			Chunk dec7 = new Chunk(
					"Inchirierea utilajelor se face pentru minim o ora de lucru.\n\n\n");
			declaratie2.add(dec7);
			document.add(declaratie2);

			final Paragraph dataSiSemnatura = new Paragraph();
			dataSiSemnatura.setLeading(25.0f);
			dataSiSemnatura.setTabSettings(new TabSettings(300.0f));
			dataSiSemnatura.setIndentationLeft(65.0f);
			final Chunk data = new Chunk("Data");
			data.setUnderline(1.0f, -2f);
			dataSiSemnatura.add((Element) data);
			dataSiSemnatura.add((Element) Chunk.TABBING);
			final Chunk semnatura = new Chunk("Semnatura\n");
			semnatura.setUnderline(1.0f, -2f);
			dataSiSemnatura.add((Element) semnatura);
			final Chunk dat = new Chunk(PDFHelper.getStrWithDots(30, ""));
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

	private void generatePDF(final String tm, final File pdfFile, final Map<String, String> map) {
		try {
			this.document = new Document();
			this.document.addAuthor("spectral369");
			this.document.addCreationDate();
			this.document.addHeader("Page count", String.valueOf(this.document.getPageNumber() + 1));
			this.document.setPageSize(PageSize.A4);
			this.writer = PdfWriter.getInstance(this.document, (OutputStream) new FileOutputStream(pdfFile));
			this.document.open();
			this.document.addTitle("Inchiriere_utilaje_" + tm);
			final Paragraph antet = new Paragraph();

			float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
			float documentHeight = document.getPageSize().getHeight() - document.topMargin() - document.bottomMargin();
			antetLogo.scaleToFit(documentWidth, documentHeight);

			antet.add(antetLogo);
			antet.setAlignment(1);
			document.add(antet);
			final Paragraph titlu = new Paragraph();
			final Paragraph nrInreg = new Paragraph();
			nrInreg.setAlignment(0);
			Chunk t2 = new Chunk("\n\nNr. " + PDFHelper.getStrWithDots(15, "") + " " + "data "
					+ PDFHelper.getStrWithDots(20, "") + "\n\n");
			nrInreg.add(t2);
			this.document.add(nrInreg);

			final Chunk t1 = new Chunk("\n\nCERERE PRIVIND\nINCHIRIEREA UTILAJELOR\n\n", bold2);
			titlu.setAlignment(1);
			titlu.add(t1);
			this.document.add(titlu);
			

			document.add(Chunk.NEWLINE);
			//

			/*
			 * Paragraph pr = new Paragraph(Chunk.TABBING);
			 * 
			 * Chunk sp = new Chunk("Subsemnatul ");
			 * sp.append(PDFHelper.getEmptySpace(50).getContent()); Chunk sp2 = new
			 * Chunk(" avand C.N.P "); pr.add(sp); pr.add(sp2); document.add(pr);
			 * 
			 * float y = writer.getVerticalPosition(false); float x2 = document.left() +
			 * sp.getWidthPoint(); float x3 = document.left() + sp2.getWidthPoint();
			 * 
			 * PDFHelper.getPlainFillTest(map.get("prenume") + " " + map.get("nume"),
			 * document, y, x2,x3, writer,true);
			 * 
			 * 
			 * Chunk sp3 = PDFHelper.getEmptySpace(25); document.add(sp3); Chunk sp4 = new
			 * Chunk(" domiciliat in comuna/satul ");
			 * sp4.append(PDFHelper.getEmptySpace(20).getContent()); document.add(sp4); y =
			 * writer.getVerticalPosition(false); x2 += document.left()+
			 * sp2.getWidthPoint(); x3 += x2 + sp3.getWidthPoint();
			 * PDFHelper.getPlainFillTest(map.get("cnp"), document, y, x3,x2, writer,false);
			 * 
			 * 
			 * 
			 * //PDFHelper.getPlainStrWithFill(map.get("prenume") + " " + map.get("nume"),
			 * // document, ph1, 30, writer);
			 * 
			 * 
			 * 
			 * 
			 * 
			 * Chunk sp6 = new Chunk(" la numarul"); document.add(sp6); y =
			 * writer.getVerticalPosition(true); x2 = document.left() + sp4.getWidthPoint();
			 * x3 = document.left() + sp6.getWidthPoint();
			 * PDFHelper.getPlainFillTest(map.get("prenume") + " " + map.get("nume"),
			 * document, y, x2, x3, writer, false);
			 */

			// test

			Paragraph par = new Paragraph(Chunk.TABBING);
			Chunk p1 = new Chunk("Subsemantul ");
		//	p1.append(new String(PDFHelper.getEmptySpace(30).getContent()));
			par.add(p1);
			Chunk p2 = new Chunk(PDFHelper.getEmptySpace(45));
			par.add(p2);
			Chunk p3 = new Chunk(" avand C.N.P ");
			par.add(p3);
			Chunk p4 =  new Chunk(PDFHelper.getEmptySpace(32));
			par.add(p4);
			Chunk p5 = new Chunk(" domiciliat in ");
			par.add(p5);
			Chunk p51 =  new Chunk("localitatea ");
			par.add(p51);
			Chunk p6 = new Chunk(PDFHelper.getEmptySpace(30));
			par.add(p6);
			Chunk p7 = new Chunk(" la numarul ");
			par.add(p7);
			Chunk p8 = new Chunk(PDFHelper.getEmptySpace(8));
			par.add(p8);
			Chunk p9 = new Chunk(" si telefon ");
			par.add(p9);
			Chunk p10 = new Chunk(PDFHelper.getEmptySpace(22));
			par.add(p10);
			document.add(par);
			float y = writer.getVerticalPosition(false);
			y += par.getLeading();
			float x2 = document.left() + p1.getWidthPoint();
			float x3 = x2 + p2.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("prenume") + " " + map.get("nume"), document, y, x3, x2, writer,true);
			x2 =x3 + p3.getWidthPoint();
			x3 = x2 + p4.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("cnp"), document, y, x3, x2, writer, true);
			y -= par.getLeading();
			x2 = document.left()+p51.getWidthPoint();
			x3 = x2 + p6.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("localitate") ,document, y, x3, x2, writer, false);
			x2 = x3+p7.getWidthPoint();
			x3 = x2 + p8.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("nrCasaAddr"), document, y, x3, x2, writer, false);
			x2 = x3+p9.getWidthPoint();
			x3 = x2 + p10.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("telefon"), document, y, x3, x2, writer, false);
			
			
			//
			Paragraph par1 =  new Paragraph(Chunk.TABBING);
			Chunk pr1 =  new Chunk("Doresc sa inchiriez utilajele Primariei Dudestii-Vechi in data de ");
			par1.add(pr1);
			Chunk pr2 =  new Chunk(PDFHelper.getEmptySpace(15));
			par1.add(pr2);
			Chunk pr3 = new Chunk(" la adresa ");
			par1.add(pr3);
			Chunk pr31 =  new Chunk(PDFHelper.getEmptySpace(30));
			par1.add(pr31);
			Chunk pr32 =  new Chunk(" numarul ");
			par1.add(pr32);
			Chunk  pr4 = new Chunk(PDFHelper.getEmptySpace(8));
			par1.add(pr4);
			document.add(par1);
			y = writer.getVerticalPosition(false);
			y += par.getLeading();
			x2 = document.left() + pr1.getWidthPoint();
			x3 = x2 + pr2.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("ziuaLuc") +
					  "-" + map.get("lunaLuc") + "-" + map.get("anulLuc"), document, y, x3, x2, writer,true);
			y -= par.getLeading();
			x2 = document.left();//inceput de rand
			x3 = x2 + pr31.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("localitate"), document, y, x3, x2, writer,false);
			
			x2 = x3+ pr32.getWidthPoint();
			x3 = x2 + pr4.getWidthPoint();
			PDFHelper.getPlainFillTest(map.get("nrCasaAddrLuc")+" .", document, y, x3, x2, writer,false);
			
			
			
			
			document.add(Chunk.NEWLINE);


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
					"\n * Tarife stabilite in conformitate cu prevederile H.C.L Dudestii-Vechi  nr.28 din 11.08.2016.\n\n");
			declaratie2.add(dec4);
			declaratie2.add(Chunk.TABBING);
			Chunk dec5 = new Chunk(
					"Prezenta cerere este valabila doar insotita de dovada achitarii"
					+ " taxei, conform estimarilor facute de catre solicitant.\n");
			declaratie2.add(dec5);
			declaratie2.add(Chunk.TABBING);
			Chunk dec6 = new Chunk(
					"Serviciul va fi prestat in limita taxei achitate urmand ca prestarea unui serviciu suplimentar"
					+ " sa implice achitarea diferentei de taxa, raportat la numarul de ore de lucru.\n");
			declaratie2.add(dec6);
			declaratie2.add(Chunk.TABBING);
			Chunk dec7 = new Chunk(
					"Inchirierea utilajelor se face pentru minim o ora de lucru.\n\n\n");
			declaratie2.add(dec7);
			document.add(declaratie2);

			final Paragraph dataSiSemnatura = new Paragraph();
			dataSiSemnatura.setLeading(25.0f);
			dataSiSemnatura.setTabSettings(new TabSettings(300.0f));
			dataSiSemnatura.setIndentationLeft(65.0f);
			final Chunk data = new Chunk("Data");
			data.setUnderline(1.0f, -2f);
			dataSiSemnatura.add((Element) data);
			dataSiSemnatura.add((Element) Chunk.TABBING);
			final Chunk semnatura = new Chunk("Semnatura\n");
			semnatura.setUnderline(1.0f, -2f);
			dataSiSemnatura.add((Element) semnatura);
			final Chunk dat = new Chunk(PDFHelper.getStrWithDots(30, ""));
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
