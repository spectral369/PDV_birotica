package com.spectral369.DDC;


import com.vaadin.navigator.*;
import java.util.*;
import java.time.*;
import java.time.format.*;
import com.vaadin.icons.*;
import com.vaadin.server.*;
import com.spectral369.birotica.*;
import com.vaadin.ui.*;

import elemental.json.JsonArray;

import com.itextpdf.text.*;
import java.io.*;
import com.itextpdf.text.pdf.*;

public class DDCPDF extends CustomComponent implements View
{
    private static final long serialVersionUID = 1L;
    public static final String NAME = "DDCPDF";
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
    
    static {
        DDCPDF.FNAME = "";
    }
    
    public DDCPDF(final Map<String, String> map) {
        this.writer = null;
        this.bold = FontFactory.getFont("Times-Roman", 14.0f, 1);
        this.bold2 = FontFactory.getFont("Times-Roman", 12.0f, 1);
        final ZonedDateTime time = ZonedDateTime.now();
        final DateTimeFormatter sdf2 = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        String tm = time.format(sdf2);
        try {
            if (System.getProperty("os.name").toUpperCase().contains("WIN")) {
                tm = tm.replaceAll("\\.", "_");
                tm = tm.replaceAll(":", "_");
                this.pdfFile = new File(String.valueOf(System.getProperty("java.io.tmpdir")) + File.separator + "DDC_" + tm + ".pdf");
                System.out.println(this.pdfFile.getCanonicalPath());
                DDCPDF.FNAME = this.pdfFile.getCanonicalPath();
            }
            else {
                this.pdfFile = new File(String.valueOf(System.getProperty("java.io.tmpdir")) + File.separator + "DDC_" + tm + ".pdf");
                System.out.println(this.pdfFile.getCanonicalPath());
                DDCPDF.FNAME = this.pdfFile.getCanonicalPath();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if (map.isEmpty()) {
            this.generatePDF(tm, this.pdfFile);
        }
        else {
            this.generatePDF(tm, this.pdfFile, map);
        }
        this.content = new VerticalLayout();
        this.titleLayout = new HorizontalLayout();
        (this.title = new Button("Generated PDF", (Resource)VaadinIcons.FILE_PRESENTATION)).setEnabled(false);
        this.title.addStyleNames(new String[] { "borderless", "clearDisabled" });
        this.titleLayout.addComponent((Component)this.title);
        this.content.addComponent((Component)this.titleLayout);
        this.content.setComponentAlignment((Component)this.titleLayout, Alignment.MIDDLE_CENTER);
        this.pdfLayout = new HorizontalLayout();
        final BrowserFrame pdf = new BrowserFrame("", (Resource)new FileResource(this.pdfFile));
        pdf.setHeight((float)Page.getCurrent().getBrowserWindowHeight(), Sizeable.Unit.PIXELS);
        pdf.setWidth((float)Page.getCurrent().getBrowserWindowWidth(), Sizeable.Unit.PIXELS);
        this.pdfLayout.addComponent((Component)pdf);
        this.content.addComponent((Component)this.pdfLayout);
        this.content.setComponentAlignment((Component)this.pdfLayout, Alignment.MIDDLE_CENTER);
        this.backLayout = new HorizontalLayout();
        (this.backbtn = new Button("Close", (Resource)VaadinIcons.CLOSE_CIRCLE_O)).addStyleName("friendly");
        this.backbtn.addClickListener(evt -> {
            if (this.pdfFile.exists()) {
                this.pdfFile.delete();
            }
            DDCPDF.FNAME = "";
            MyUI.navigator.removeView("DDC");
            MyUI.navigator.removeView("DDCPDF");
            MyUI.navigator.navigateTo("Index");
        });
        this.backLayout.addComponent((Component)this.backbtn);
        this.content.addComponent((Component)this.backLayout);
        this.content.setComponentAlignment((Component)this.backLayout, Alignment.MIDDLE_CENTER);
        this.content.setMargin(false);
        this.setCompositionRoot((Component)this.content);
        MyUI.navigator.addView("DDCPDF", (View)this);
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
            (this.document = new Document()).addAuthor("spectral369");
            this.document.addCreationDate();
            this.document.addHeader("Page count", String.valueOf(this.document.getPageNumber() + 1));
            this.document.setPageSize(PageSize.A4);
            this.writer = PdfWriter.getInstance(this.document, (OutputStream)new FileOutputStream(pdfFile));
            this.document.open();
            this.document.addTitle("Declaratie_de_Corespondenta_" + tm);
            final Paragraph title = new Paragraph();
            final Chunk t1 = new Chunk("\n\n\n\nDECLARATIE PE PROPRIA RASPUNDERE\n\n\n", this.bold);
            title.add((Element)t1);
            title.setAlignment(1);
            this.document.add((Element)title);
            final Paragraph declaratie = new Paragraph();
            declaratie.setLeading(20.0f);
            declaratie.setTabSettings(new TabSettings(15.0f));
            declaratie.add((Element)Chunk.TABBING);
            final Chunk subsemnat = new Chunk("Subsemnatul/Subsemnata, " + this.getStrWithDots(70, "") + " Cod Numeric Personal: " + this.getStrWithDots(45, "") + " identificat/identificata cu: " + this.getStrWithDots(45, "") + " seria: " + this.getStrWithDots(15, "") + " numarul: " + this.getStrWithDots(20, "") + " cetatenia: " + this.getStrWithDots(35, "") + " cu domiciliul/resedinta in(adresa completa de corespondenta) " + "localitatea: " + this.getStrWithDots(65, "") + " strada: " + this.getStrWithDots(60, "") + " numarul: " + this.getStrWithDots(15, "") + " bloc: " + this.getStrWithDots(15, "") + " scara: " + this.getStrWithDots(15, "") + " etaj: " + this.getStrWithDots(12, "") + " apartament: " + this.getStrWithDots(12, "") + " sector: " + this.getStrWithDots(12, "") + " judet: " + this.getStrWithDots(25, "") + " cod postal: " + this.getStrWithDots(20, "") + " .");
            declaratie.add((Element)subsemnat);
            declaratie.add((Element)Chunk.TABBING);
            final Chunk contact2 = new Chunk("\nPot fi contactat la:\n");
            final Chunk fix = new Chunk("- telefon fix: " + this.getStrWithDots(60, "") + "\n");
            final Chunk mobil = new Chunk("- telefon mobil: " + this.getStrWithDots(60, "") + "\n");
            final Chunk email = new Chunk("- adresa de e-mail: " + this.getStrWithDots(60, "") + "\n\n");
            declaratie.add((Element)contact2);
            declaratie.add((Element)Chunk.TABBING);
            declaratie.add((Element)fix);
            declaratie.add((Element)Chunk.TABBING);
            declaratie.add((Element)mobil);
            declaratie.add((Element)Chunk.TABBING);
            declaratie.add((Element)email);
            declaratie.add((Element)Chunk.TABBING);
            final Chunk acordTitlu = new Chunk(" Declar pe propria raspundere ca: \n");
            declaratie.add((Element)acordTitlu);
            declaratie.add((Element)Chunk.TABBING);
            final Chunk acordOK = new Chunk(" Sunt de acord cu transmiterea corespondentei prin mijloacele electronice la adresa de mail mentionata.\n");
            declaratie.add((Element)acordOK);
            declaratie.add((Element)Chunk.TABBING);
            final PdfContentByte canvas = this.writer.getDirectContent();
            final Rectangle rectNot = new Rectangle(30.0f, 370.0f, 50.0f, 386.0f);
            rectNot.setBorder(15);
            rectNot.setBorderWidth(2.0f);
            final Rectangle rectOK = new Rectangle(30.0f, 410.0f, 50.0f, 426.0f);
            rectOK.setBorder(15);
            rectOK.setBorderWidth(2.0f);
            canvas.rectangle(rectNot);
            canvas.rectangle(rectOK);
            final Chunk acordNot = new Chunk(" Nu sunt de acord cu transmiterea corespondentei prin mijloacele electronice la adresa de mail mentionata.\n\n\n");
            declaratie.add((Element)acordNot);
            final Paragraph dataSiSemnatura = new Paragraph();
            dataSiSemnatura.setLeading(25.0f);
            dataSiSemnatura.setTabSettings(new TabSettings(300.0f));
            dataSiSemnatura.setIndentationLeft(65.0f);
            final Chunk data = new Chunk("Data: ");
            dataSiSemnatura.add((Element)data);
            dataSiSemnatura.add((Element)Chunk.TABBING);
            final Chunk semnatura = new Chunk("Semnatura:\n");
            dataSiSemnatura.add((Element)semnatura);
            final Chunk dat = new Chunk(this.getStrWithDots(30, ""));
            dataSiSemnatura.add((Element)dat);
            dataSiSemnatura.add((Element)Chunk.TABBING);
            final Chunk semn = new Chunk(this.getStrWithDots(30, ""));
            dataSiSemnatura.add((Element)semn);
            this.document.add((Element)declaratie);
            this.document.add((Element)dataSiSemnatura);
            final Phrase footer = new Phrase(String.valueOf(this.document.getPageNumber() + 1) + "/" + this.writer.getPageNumber());
            ColumnText.showTextAligned(canvas, 0, footer, this.writer.getPageSize().getWidth() / 2.0f, 0.0f, 0.0f);
            this.document.close();
        }
        catch (DocumentException ex) {}
        catch (FileNotFoundException ex2) {}
        this.writer.flush();
    }
    
    private void generatePDF(final String tm, final File pdfFile, final Map<String, String> map) {
        try {
            (this.document = new Document()).addAuthor("spectral369");
            this.document.addCreationDate();
            this.document.addHeader("Page count", String.valueOf(this.document.getPageNumber() + 1));
            this.document.setPageSize(PageSize.A4);
            this.writer = PdfWriter.getInstance(this.document, (OutputStream)new FileOutputStream(pdfFile));
            this.document.open();
            this.document.addTitle("Declaratie_de_Corespondenta_" + tm);
            final Paragraph title = new Paragraph();
            final Chunk t1 = new Chunk("\n\n\n\nDECLARATIE PE PROPRIA RASPUNDERE\n\n\n", this.bold);
            title.add((Element)t1);
            title.setAlignment(1);
            this.document.add((Element)title);
            final Paragraph declaratie = new Paragraph();
            declaratie.setLeading(20.0f);
            declaratie.setTabSettings(new TabSettings(15.0f));
            declaratie.add((Element)Chunk.TABBING);
            final Phrase subsemnat2 = new Phrase();
            final String titlu = map.get("titlu").toLowerCase().contains("dl") ? "Subsemnatul: " : "Subsemnata: ";
            final String identifi = map.get("titlu").toLowerCase().contains("dl") ? " indentificat cu: " : " identificata cu: ";
            final Chunk titlul = new Chunk(titlu);
            subsemnat2.add((Element)titlul);
            subsemnat2.add((Element)this.getPhraseStrWithDots(85, String.valueOf(map.get("prenume")) + " " + map.get("nume")));
            final Chunk cnp = new Chunk(" Cod Numeric Personal: ");
            subsemnat2.add((Element)cnp);
            subsemnat2.add((Element)this.getPhraseStrWithDots(45, map.get("CNP")));
            final Chunk identificat = new Chunk(identifi);
            subsemnat2.add((Element)identificat);
            subsemnat2.add((Element)this.getPhraseStrWithDots(30, "C.I"));
            subsemnat2.add(" serie: ");
            subsemnat2.add((Element)this.getPhraseStrWithDots(15, map.get("serie")));
            subsemnat2.add(" numarul: ");
            subsemnat2.add((Element)this.getPhraseStrWithDots(20, map.get("nrSerie")));
            subsemnat2.add(" cetatenia: ");
            subsemnat2.add((Element)this.getPhraseStrWithDots(35, map.get("cetatenie")));
            subsemnat2.add(" cu domiciliul/resedinta in(adresa completa de corespondenta) locatitatea: ");
            subsemnat2.add((Element)this.getPhraseStrWithDots(40, map.get("localitate")));
            subsemnat2.add(" strada: ");
            subsemnat2.add((Element)this.getPhraseStrWithDots(60, map.get("strada")));
            subsemnat2.add(" numarul: ");
            subsemnat2.add((Element)this.getPhraseStrWithDots(15, map.get("nrStrada")));
            subsemnat2.add(" bloc: ");
            subsemnat2.add((Element)this.getPhraseStrWithDots(15, map.get("bloc")));
            subsemnat2.add(" scara: ");
            subsemnat2.add((Element)this.getPhraseStrWithDots(15, map.get("scara")));
            subsemnat2.add(" etaj: ");
            subsemnat2.add((Element)this.getPhraseStrWithDots(12, map.get("etaj")));
            subsemnat2.add(" apartament: ");
            subsemnat2.add((Element)this.getPhraseStrWithDots(12, map.get("apartament")));
            subsemnat2.add(" sector: ");
            subsemnat2.add((Element)this.getPhraseStrWithDots(12, map.get("sector")));
            subsemnat2.add(" judet: ");
            subsemnat2.add((Element)this.getPhraseStrWithDots(25, map.get("judet")));
            subsemnat2.add(" cod postal: ");
            subsemnat2.add((Element)this.getPhraseStrWithDots(20, map.get("codPostal")));
            subsemnat2.add(" .");
            declaratie.add((Element)subsemnat2);
            final Phrase contact = new Phrase();
            contact.add((Element)Chunk.NEWLINE);
            final Chunk cont = new Chunk("Pot fi contactat la:");
            contact.add((Element)Chunk.NEWLINE);
            contact.add((Element)cont);
            contact.add((Element)Chunk.NEWLINE);
            final Chunk fix = new Chunk("- telefon fix: ");
            contact.add((Element)Chunk.TABBING);
            contact.add((Element)fix);
            contact.add((Element)this.getPhraseStrWithDots(60, map.get("fix")));
            contact.add((Element)Chunk.NEWLINE);
            contact.add((Element)Chunk.TABBING);
            final Chunk mobil = new Chunk("- telefon mobil: ");
            contact.add((Element)mobil);
            contact.add((Element)this.getPhraseStrWithDots(60, map.get("mobil")));
            contact.add((Element)Chunk.NEWLINE);
            contact.add((Element)Chunk.TABBING);
            final Chunk email = new Chunk("- adresa de e-mail: ");
            contact.add((Element)email);
            contact.add((Element)this.getPhraseStrWithDots(60, map.get("email")));
            contact.add((Element)Chunk.NEWLINE);
            contact.add((Element)Chunk.NEWLINE);
            declaratie.add((Element)contact);
            final Chunk acordTitlu = new Chunk("Declar pe propria raspundere ca: \n");
            declaratie.add((Element)acordTitlu);
            declaratie.add((Element)Chunk.TABBING);
            final Chunk acordOK = new Chunk(" Sunt de acord cu transmiterea corespondentei prin mijloacele electronice la adresa de mail mentionata.\n");
            declaratie.add((Element)acordOK);
            declaratie.add((Element)Chunk.TABBING);
            final PdfContentByte canvas = this.writer.getDirectContent();
            final String acord = map.get("acord").toLowerCase().contains("true") ? "Yes" : "Off";
            final String disacord = acord.toLowerCase().contains("true") ? "Off" : "Yes";
            final PdfAcroForm form = new PdfAcroForm(this.writer);
            form.addCheckBox("Disagree", disacord, true, 30.0f, 370.0f, 50.0f, 386.0f);
            form.addCheckBox("Agree", acord, true, 30.0f, 410.0f, 50.0f, 426.0f);
            final Chunk acordNot = new Chunk(" Nu sunt de acord cu transmiterea corespondentei prin mijloacele electronice la adresa de mail mentionata.\n\n\n");
            declaratie.add((Element)acordNot);
            final Paragraph dataSiSemnatura = new Paragraph();
            dataSiSemnatura.setLeading(25.0f);
            dataSiSemnatura.setTabSettings(new TabSettings(300.0f));
            dataSiSemnatura.setIndentationLeft(65.0f);
            final Chunk data = new Chunk("Data: ");
            dataSiSemnatura.add((Element)data);
            dataSiSemnatura.add((Element)Chunk.TABBING);
            final Chunk semnatura = new Chunk("Semnatura:\n");
            dataSiSemnatura.add((Element)semnatura);
            final Phrase dat = new Phrase(this.getPhraseStrWithDots(30, map.get("data")));
            dataSiSemnatura.add((Element)dat);
            dataSiSemnatura.add((Element)Chunk.TABBING);
            final Chunk semn = new Chunk(this.getStrWithDots(30, ""));
            dataSiSemnatura.add((Element)semn);
            this.document.add((Element)declaratie);
            this.document.add((Element)dataSiSemnatura);
            final Phrase footer = new Phrase(String.valueOf(this.document.getPageNumber() + 1) + "/" + this.writer.getPageNumber());
            ColumnText.showTextAligned(canvas, 0, footer, this.writer.getPageSize().getWidth() / 2.0f, 0.0f, 0.0f);
            this.document.close();
        }
        catch (DocumentException ex) {}
        catch (FileNotFoundException ex2) {}
        this.writer.flush();
    }
    
    private String getStrWithDots(final int dots, final String str) {
        final int strSize = str.length();
        final StringBuilder sb = new StringBuilder();
        int dotsRemained;
        if (strSize > dots) {
            dotsRemained = 0;
        }
        else {
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
        }
        else {
            dotsRemained = dots - strSize;
        }
        Chunk chDots = new Chunk();
        final Chunk chStr = new Chunk("", this.bold2);
        chStr.setTextRise(1.7f);
        for (int i = 0; i < dotsRemained; ++i) {
            if (i == dotsRemained / 2) {
                chStr.append(str);
                sb.add((Element)chDots);
                sb.add((Element)chStr);
                chDots = new Chunk();
            }
            chDots.append(".");
        }
        sb.add((Element)chDots);
        return sb;
    }
}
