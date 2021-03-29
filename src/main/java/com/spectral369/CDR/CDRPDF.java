package com.spectral369.CDR;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.PdfWriter;
import com.spectral369.birotica.Index;
import com.spectral369.birotica.MyUI;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.JavaScriptFunction;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import elemental.json.JsonArray;

public class CDRPDF extends CustomComponent implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "CDRPDF";
	public static String FNAME = "";

	private transient PdfWriter writer = null;
	private final int fontSize = 14;

	private final transient Font bold = FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize, Font.BOLD);
	private final transient Font bold2 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD);
	private final transient Font normal = FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize, Font.NORMAL);
	private transient Document document;
	private File pdfFile;

	VerticalLayout content;
	HorizontalLayout titleLayout;
	Button title;
	HorizontalLayout pdfLayout;
//	WTPdfViewer pdfView;

	HorizontalLayout backLayout;
	Button backbtn;

	public CDRPDF(Map<String, String> map) {

		// String tmp = System.getProperty("java.io.tmpdir");

		ZonedDateTime time = ZonedDateTime.now();
		DateTimeFormatter sdf2 = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		String tm = time.format(sdf2);
		// pdfFile = new File(tmp + "/CDR_" + tm + ".pdf");
		try {
			// pdfFile = File.createTempFile("CDR_" + tm, ".pdf");
			if (System.getProperty("os.name").toUpperCase().contains("WIN")) {
				tm = tm.replaceAll("\\.", "_");
				tm = tm.replaceAll(":", "_");
				pdfFile = new File(System.getProperty("java.io.tmpdir") + File.separator + "CDR_" + tm + ".pdf");

				System.out.println(pdfFile.getCanonicalPath());
				FNAME = pdfFile.getCanonicalPath();
			} else {
				pdfFile = new File(System.getProperty("java.io.tmpdir") + File.separator + "CDR_" + tm + ".pdf");
				System.out.println(pdfFile.getCanonicalPath());
				FNAME = pdfFile.getCanonicalPath();
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		if (map.isEmpty())
			generatePDF(tm, pdfFile);
		else
			generatePDF(tm, pdfFile, map);

		content = new VerticalLayout();
		titleLayout = new HorizontalLayout();
		title = new Button("Generated PDF", VaadinIcons.FILE_PRESENTATION);
		title.setEnabled(false);
		title.addStyleNames(ValoTheme.BUTTON_BORDERLESS, "clearDisabled");
		titleLayout.addComponent(title);
		content.addComponent(titleLayout);
		content.setComponentAlignment(titleLayout, Alignment.MIDDLE_CENTER);

		pdfLayout = new HorizontalLayout();

		/*
		 * Embedded pdf = new Embedded("", new FileResource(pdfFile));
		 * pdf.setMimeType("application/pdf"); pdf.setType(Embedded.TYPE_BROWSER);
		 */
		BrowserFrame pdf = new BrowserFrame("", new FileResource(pdfFile));
		pdf.setHeight(Page.getCurrent().getBrowserWindowHeight(), Unit.PIXELS);
		pdf.setWidth(Page.getCurrent().getBrowserWindowWidth(), Unit.PIXELS);

		pdfLayout.addComponent(pdf);

		content.addComponent(pdfLayout);
		content.setComponentAlignment(pdfLayout, Alignment.MIDDLE_CENTER);

		backLayout = new HorizontalLayout();
		backbtn = new Button("Close", VaadinIcons.CLOSE_CIRCLE_O);
		backbtn.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		backbtn.addClickListener(evt -> {
			if (pdfFile.exists())
				pdfFile.delete();
			FNAME = "";
			MyUI.navigator.removeView(CDRInfo.NAME);
			MyUI.navigator.removeView(CDRPDF.NAME);
			MyUI.navigator.navigateTo(Index.NAME);

		});
		backLayout.addComponent(backbtn);
		content.addComponent(backLayout);
		content.setComponentAlignment(backLayout, Alignment.MIDDLE_CENTER);

		content.setMargin(false);
		setCompositionRoot(content);
		MyUI.navigator.addView(CDRPDF.NAME, this);

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
		if (pdfFile.exists())
			pdfFile.delete();
	}

	private void generatePDF(String tm, File pdfFile) {
		try {

			document = new Document();
			document.setPageSize(PageSize.A4);// .rotate() for landscape

			writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));

			document.open();
			document.addTitle("Cerere de restituire_" + tm);

			Paragraph title = new Paragraph();
			Chunk t1 = new Chunk("CERERE DE RESTITUIRE\n", bold);
			title.add(t1);
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);

			Paragraph title2 = new Paragraph();
			Chunk t2 = new Chunk(
					"a unor sume reprezentand taxe sau alte venituri ale bugetului de stat, platite in plus sau\n"
							+ "necuvenit si pentru care nu exista obligatia de declarare\n\n",
					bold);
			title2.add(t2);
			title2.setAlignment(Element.ALIGN_CENTER);

			document.add(title2);
			Paragraph p1 = new Paragraph();

			Chunk p1c = new Chunk("Nr. " + getStrWithDots(40, "") + " din data de " + getStrWithDots(40, "") + " \n"
					+ "Catre" + getStrWithDots(65, "") + " *1)/" + getStrWithDots(55, "") + "*2)\n\n");
			p1.add(p1c);
			p1.setAlignment(Element.ALIGN_LEFT);
			document.add(p1);

			Paragraph info1 = new Paragraph();
			info1.setTabSettings(new TabSettings(15f));
			info1.add(Chunk.TABBING);
			Chunk line1 = new Chunk("Prin prezenta, " + getStrWithDots(90, "") + "*3), cu sediul/domiciliul in "
					+ "localitatea " + getStrWithDots(65, "") + ", str. " + getStrWithDots(40, "") + "nr. " + ""
					+ getStrWithDots(8, "") + "\nbl. " + getStrWithDots(5, "") + ", sc. " + "" + getStrWithDots(5, "")
					+ ", ap. " + getStrWithDots(5, "") + ", judetul/sectorul " + "" + "" + getStrWithDots(45, "")
					+ ", avand C.U.I/C.N.P. " + getStrWithDots(50, "") + "" + ", administrat de "
					+ getStrWithDots(50, "") + "*4), si arondat unitatii Trezoreriei Statului " + ""
					+ getStrWithDots(70, "") + "*5), rog a mi se  restitui suma de " + getStrWithDots(20, "") + ""
					+ "*6), reprezentand" + getStrWithDots(80, "") + "*7)\nachitata in data de "
					+ getStrWithDots(20, "") + "" + ", avand in vedere faptul ca " + getStrWithDots(50, "") + "*8).\n");

			info1.add(line1);
			Chunk line2 = new Chunk("Restituirea va fi efectuata in numerar/in contul " + getStrWithDots(70, "") + ""
					+ " *9)\ncod IBAN " + getStrWithDots(55, "") + "*10), deschis la " + getStrWithDots(50, "")
					+ "*11).\n\n");
			info1.add(Chunk.TABBING);
			info1.add(line2);
			info1.setAlignment(Element.ALIGN_LEFT);

			document.add(info1);

			Paragraph sign = new Paragraph();
			sign.setTabSettings(new TabSettings(15f));
			sign.add(Chunk.TABBING);
			Chunk sn1 = new Chunk("Semnatura\n", normal);
			Chunk sn2 = new Chunk(getDots(45) + "\n", normal);
			Chunk sn3 = new Chunk("L.S(daca este cazul)\n\n", normal);
			sign.add(sn1);
			sign.add(sn2);
			sign.add(sn3);
			sign.setIndentationLeft(45);
			document.add(sign);

			Paragraph nota = new Paragraph();
			nota.setTabSettings(new TabSettings(15f));
			nota.add(Chunk.TABBING);
			Chunk n1 = new Chunk("NOTA:\n", bold);
			nota.add(n1);

			nota.add(Chunk.TABBING);
			Chunk n2 = new Chunk(
					"Restituirea sumelor mai mici de 500 lei cuvenite persoanelor fizice se va efectua "
							+ "fie in numerar, fie in contul bancar inscris de solicitant in cererea de restituire.\n",
					normal);
			nota.add(n2);

			nota.add(Chunk.TABBING);
			Chunk n3 = new Chunk("Excercitarea optiunii solicitantului se va face prin bararea cu o linie orizontala a "
					+ "denumirii necorespunzatoare. In situatia in care solicitantul nu isi exercita optiunea, "
					+ "restutuirea se va efectua in numerar, la ghiseul unitatii Trezoreriei Statului la care "
					+ "este arondat solicitantul.\n", normal);
			nota.add(n3);

			nota.add(Chunk.TABBING);
			Chunk n4 = new Chunk("Restituirea sumelor cuvenite persoanelor juridice se va efectua numai prin decontare "
					+ "bancara, in contul inscris de solicitant in cererea de restituire.\n", normal);
			nota.add(n4);
			document.add(nota);

			Paragraph legenda = new Paragraph();
			legenda.setTabSettings(new TabSettings(15f));
			Chunk ln = new Chunk("-----------------\n", normal);
			legenda.add(ln);

			legenda.add(Chunk.TABBING);
			Chunk l1 = new Chunk("*1) Denumirea autoritatii/institutiei publice care a incasat suma.\n", normal);
			legenda.add(l1);
			legenda.add(Chunk.TABBING);
			Chunk l2 = new Chunk("*2) Denumirea organului fiscal competent in administrarea solicitantului.\n", normal);
			legenda.add(l2);
			legenda.add(Chunk.TABBING);
			Chunk l3 = new Chunk("*3) Numele sau denumirea solicitantului.\n", normal);
			legenda.add(l3);
			legenda.add(Chunk.TABBING);
			Chunk l4 = new Chunk("*4)  Denumirea organului fiscal competent in administrarea solicitantului.\n",
					normal);
			legenda.add(l4);
			legenda.add(Chunk.TABBING);
			Chunk l5 = new Chunk("*4) Denumirea unitatii Trezoreriei Statului la care este arondat solicitantul sau "
					+ "organul fiscal competent in administrarea acestuia.\n", normal);
			legenda.add(l5);
			legenda.add(Chunk.TABBING);
			Chunk l6 = new Chunk("*6) Cuantumul  sumei pentru care se solicita restituirea, in cifre si litere.\n",
					normal);
			legenda.add(l6);
			legenda.add(Chunk.TABBING);
			Chunk l7 = new Chunk("*7) Denumirea taxei/tarifului/comisionului/contributiei/etc platit(e) in plus "
					+ "fata de obligatia legala ori platit(e) necuvenit, dupa caz.\n", normal);
			legenda.add(l7);
			legenda.add(Chunk.TABBING);
			Chunk l8 = new Chunk("*8) Se va preciza motivul pentru care se solicita restituirea, respectiv daca suma "
					+ "a fost platita in plus sau necuvenit.\n", normal);
			legenda.add(l8);
			legenda.add(Chunk.TABBING);
			Chunk l9 = new Chunk("*9) Simbolul contului bancar in care se doreste restituirea sumei.\n", normal);
			legenda.add(l9);
			legenda.add(Chunk.TABBING);
			Chunk l10 = new Chunk("*10) Codul IBAN  aferent contului in care se solicita restituirea sumei.\n", normal);
			legenda.add(l10);
			legenda.add(Chunk.TABBING);
			Chunk l11 = new Chunk(
					"*11) Denumirea bancii/trezoreriei/institutiei autorizate sa desfasoare operatiuni de "
							+ "plata, dupa caz, la care solicitantul are deschis contul.\n",
					normal);
			legenda.add(l11);
			document.add(legenda);

			document.close();
			// return 1;
		} catch (DocumentException | FileNotFoundException ex) {
			// Logger.getLogger(CDRPDF.class.getName()).log(Level.SEVERE, null, ex);

		}
		writer.flush();

	}

	private void generatePDF(String tm, File pdfFile, Map<String, String> map) {
		try {

			document = new Document();
			document.setPageSize(PageSize.A4);// .rotate() for landscape

			writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));

			document.open();
			document.addTitle("Cerere de restituire_" + tm);

			Paragraph title = new Paragraph();
			Chunk t1 = new Chunk("CERERE DE RESTITUIRE\n", bold);
			title.add(t1);
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);

			Paragraph title2 = new Paragraph();
			Chunk t2 = new Chunk(
					"a unor sume reprezentand taxe sau alte venituri ale bugetului de stat, platite in plus sau\n"
							+ "necuvenit si pentru care nu exista obligatia de declarare\n\n",
					bold);
			title2.add(t2);
			title2.setAlignment(Element.ALIGN_CENTER);

			document.add(title2);
			Paragraph p1 = new Paragraph();
			Phrase p1phr = new Phrase();
			// Chunk p1c = new Chunk();

			p1phr.add(new Chunk("Nr. "));
			p1phr.add(getPhraseStrWithDots(40, map.get("nr1")));
			Chunk p1c2 = new Chunk(" din data de ");
			p1phr.add(p1c2);
			p1phr.add(getPhraseStrWithDots(40, map.get("data")));
			p1phr.add(Chunk.NEWLINE);
			Chunk p1c3 = new Chunk("Catre: ");
			p1phr.add(p1c3);
			p1phr.add(getPhraseStrWithDots(65, map.get("catre")));
			p1phr.add(new Chunk("*1)/"));
			p1phr.add(getPhraseStrWithDots(55, map.get("autoritateSuma")));
			p1phr.add(Chunk.NEWLINE);
			p1phr.add(Chunk.NEWLINE);

			p1.add(p1phr);

			/*
			 * Chunk p1c = new Chunk( "Nr. " + getStrWithDots(40, map.get("nr1")) +
			 * " din data de " + getStrWithDots(40, map.get("data")) + " \n" + "Catre" +
			 * getStrWithDots(65, "") + " *1)/" + getStrWithDots(55, "") + "*2)\n\n");
			 * p1.add(p1c);
			 */

			p1.setAlignment(Element.ALIGN_LEFT);
			document.add(p1);

			Paragraph info1 = new Paragraph();
			info1.setTabSettings(new TabSettings(15f));
			info1.add(Chunk.TABBING);
			Chunk line1 = new Chunk("Prin prezenta, " + getStrWithDots(90, "") + "*3), cu sediul/domiciliul in "
					+ "localitatea " + getStrWithDots(65, "") + ", str. " + getStrWithDots(40, "") + "nr. " + ""
					+ getStrWithDots(8, "") + "\nbl. " + getStrWithDots(5, "") + ", sc. " + "" + getStrWithDots(5, "")
					+ ", ap. " + getStrWithDots(5, "") + ", judetul/sectorul " + "" + "" + getStrWithDots(45, "")
					+ ", avand C.U.I/C.N.P. " + getStrWithDots(50, "") + "" + ", administrat de "
					+ getStrWithDots(50, "") + "*4), si arondat unitatii Trezoreriei Statului " + ""
					+ getStrWithDots(70, "") + "*5), rog a mi se  restitui suma de " + getStrWithDots(20, "") + ""
					+ "*6), reprezentand" + getStrWithDots(80, "") + "*7)\nachitata in data de "
					+ getStrWithDots(20, "") + "" + ", avand in vedere faptul ca " + getStrWithDots(50, "") + "*8).\n");

			info1.add(line1);
			Chunk line2 = new Chunk("Restituirea va fi efectuata in numerar/in contul " + getStrWithDots(70, "") + ""
					+ " *9)\ncod IBAN " + getStrWithDots(55, "") + "*10), deschis la " + getStrWithDots(50, "")
					+ "*11).\n\n");
			info1.add(Chunk.TABBING);
			info1.add(line2);
			info1.setAlignment(Element.ALIGN_LEFT);

			document.add(info1);

			Paragraph sign = new Paragraph();
			sign.setTabSettings(new TabSettings(15f));
			sign.add(Chunk.TABBING);
			Chunk sn1 = new Chunk("Semnatura\n", normal);
			Chunk sn2 = new Chunk(getDots(45) + "\n", normal);
			Chunk sn3 = new Chunk("L.S(daca este cazul)\n\n", normal);
			sign.add(sn1);
			sign.add(sn2);
			sign.add(sn3);
			sign.setIndentationLeft(45);
			document.add(sign);

			Paragraph nota = new Paragraph();
			nota.setTabSettings(new TabSettings(15f));
			nota.add(Chunk.TABBING);
			Chunk n1 = new Chunk("NOTA:\n", bold);
			nota.add(n1);

			nota.add(Chunk.TABBING);
			Chunk n2 = new Chunk(
					"Restituirea sumelor mai mici de 500 lei cuvenite persoanelor fizice se va efectua "
							+ "fie in numerar, fie in contul bancar inscris de solicitant in cererea de restituire.\n",
					normal);
			nota.add(n2);

			nota.add(Chunk.TABBING);
			Chunk n3 = new Chunk("Excercitarea optiunii solicitantului se va face prin bararea cu o linie orizontala a "
					+ "denumirii necorespunzatoare. In situatia in care solicitantul nu isi exercita optiunea, "
					+ "restutuirea se va efectua in numerar, la ghiseul unitatii Trezoreriei Statului la care "
					+ "este arondat solicitantul.\n", normal);
			nota.add(n3);

			nota.add(Chunk.TABBING);
			Chunk n4 = new Chunk("Restituirea sumelor cuvenite persoanelor juridice se va efectua numai prin decontare "
					+ "bancara, in contul inscris de solicitant in cererea de restituire.\n", normal);
			nota.add(n4);
			document.add(nota);

			Paragraph legenda = new Paragraph();
			legenda.setTabSettings(new TabSettings(15f));
			Chunk ln = new Chunk("-----------------\n", normal);
			legenda.add(ln);

			legenda.add(Chunk.TABBING);
			Chunk l1 = new Chunk("*1) Denumirea autoritatii/institutiei publice care a incasat suma.\n", normal);
			legenda.add(l1);
			legenda.add(Chunk.TABBING);
			Chunk l2 = new Chunk("*2) Denumirea organului fiscal competent in administrarea solicitantului.\n", normal);
			legenda.add(l2);
			legenda.add(Chunk.TABBING);
			Chunk l3 = new Chunk("*3) Numele sau denumirea solicitantului.\n", normal);
			legenda.add(l3);
			legenda.add(Chunk.TABBING);
			Chunk l4 = new Chunk("*4)  Denumirea organului fiscal competent in administrarea solicitantului.\n",
					normal);
			legenda.add(l4);
			legenda.add(Chunk.TABBING);
			Chunk l5 = new Chunk("*4) Denumirea unitatii Trezoreriei Statului la care este arondat solicitantul sau "
					+ "organul fiscal competent in administrarea acestuia.\n", normal);
			legenda.add(l5);
			legenda.add(Chunk.TABBING);
			Chunk l6 = new Chunk("*6) Cuantumul  sumei pentru care se solicita restituirea, in cifre si litere.\n",
					normal);
			legenda.add(l6);
			legenda.add(Chunk.TABBING);
			Chunk l7 = new Chunk("*7) Denumirea taxei/tarifului/comisionului/contributiei/etc platit(e) in plus "
					+ "fata de obligatia legala ori platit(e) necuvenit, dupa caz.\n", normal);
			legenda.add(l7);
			legenda.add(Chunk.TABBING);
			Chunk l8 = new Chunk("*8) Se va preciza motivul pentru care se solicita restituirea, respectiv daca suma "
					+ "a fost platita in plus sau necuvenit.\n", normal);
			legenda.add(l8);
			legenda.add(Chunk.TABBING);
			Chunk l9 = new Chunk("*9) Simbolul contului bancar in care se doreste restituirea sumei.\n", normal);
			legenda.add(l9);
			legenda.add(Chunk.TABBING);
			Chunk l10 = new Chunk("*10) Codul IBAN  aferent contului in care se solicita restituirea sumei.\n", normal);
			legenda.add(l10);
			legenda.add(Chunk.TABBING);
			Chunk l11 = new Chunk(
					"*11) Denumirea bancii/trezoreriei/institutiei autorizate sa desfasoare operatiuni de "
							+ "plata, dupa caz, la care solicitantul are deschis contul.\n",
					normal);
			legenda.add(l11);
			document.add(legenda);

			document.close();
			// return 1;
		} catch (DocumentException | FileNotFoundException ex) {
			// Logger.getLogger(CDRPDF.class.getName()).log(Level.SEVERE, null, ex);

		}
		writer.flush();

	}

	private String getDots(int number) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < number; i++) {
			sb.append(".");
		}
		return sb.toString();
	}

	private String getStrWithDots(int dots, String str) {

		int strSize = str.length();
		int dotsRemained;// =0
		StringBuilder sb = new StringBuilder();
		if ((strSize/**
					 * 2
					 */
		) > dots) {
			dotsRemained = 0;
		} else {
			dotsRemained = dots - (strSize/**
											 * 2
											 */
			);
		}

		for (int i = 0; i < dotsRemained; i++) {
			if (i == (dotsRemained / 2)) {
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

	/*
	 * private Phrase getPhraseStrWithDots(int dots, String str) {
	 * 
	 * int strSize = str.length(); int dotsRemained;// =0 Phrase sb = new Phrase();
	 * if ((strSize
	 *//**
		 * 2
		 */
	/*
	 * ) > dots) { dotsRemained = 0; } else { dotsRemained = dots - (strSize
	 *//**
		 * 2
		 *//*
			 * ); } Chunk chDots = new Chunk(); Chunk chStr = new Chunk("", bold2);
			 * chStr.setTextRise(1.7f); for (int i = 0; i < dotsRemained; i++) { if (i ==
			 * (dotsRemained / 2)) { chStr.append(str); sb.add(chDots); sb.add(chStr);
			 * chDots = new Chunk(); }
			 * 
			 * chDots.append(".");
			 * 
			 * } sb.add(chDots); return sb; }
			 */

}
