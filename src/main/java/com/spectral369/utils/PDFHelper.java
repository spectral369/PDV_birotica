package com.spectral369.utils;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.Text;

public class PDFHelper {

    public static final Style bold10nr = new Style();
    public static final Style bold12nr = new Style();
    public static final Style bold14nr = new Style();
    public static final Style bold9nr = new Style();
    public static int CODE;

    public static Paragraph getPlainStr(final String str) {
	Paragraph par = new Paragraph();
	par.add(new Tab());
	Text plainstr = new Text(str).setBold();
	par.add(plainstr);
	par.add(new Tab());

	return par;
    }

    public static Text addTab() {
	StringBuilder sb = new StringBuilder();
	for (int i = 0; i < 8; i++)
	    sb.append("\u00a0");
	return new Text(sb.toString());
    }

    public static Paragraph getEmptySpace(int size) {
	Paragraph space = new Paragraph();
	space.setMaxWidth(size);
	for (int i = 0; i < size; i++) {
	    space.add("\u00a0");
	}
	return space;
    }

    
    
    public static  Paragraph createAdjustableParagraph(int widthInSpaces, Paragraph innerContent) {
	    AutoScalingParagraph paragraph = new AutoScalingParagraph(innerContent);
	   // paragraph.setBorder(new SolidBorder(1));

	    StringBuilder sb = new StringBuilder();
	    for(int i=0;i<widthInSpaces;i++) {
	        sb.append("\u00a0");
	    }
	    paragraph.add(sb.toString());
	    return paragraph;
	}
   /* public static void getPlainFill2(String str, Document doc, PdfDocument document, Paragraph root,
	    Paragraph space, boolean isCentred) {
	// System.out.println("prevText: "+prev.getText());
	float width = doc.getPageEffectiveArea(PageSize.A4).getWidth();
	float height = doc.getPageEffectiveArea(PageSize.A4).getHeight();
	if (str.isEmpty() || str.isBlank()) {
	    str = "________";
	}
	IRenderer spaceRenderer = space.createRendererSubTree().setParent(doc.getRenderer());

	LayoutResult spaceResult = spaceRenderer
		.layout(new LayoutContext(new LayoutArea(1, new Rectangle(width, height))));

	Rectangle rectSpaceBox = ((ParagraphRenderer) spaceRenderer).getOccupiedArea().getBBox();

	float writingWidth = rectSpaceBox.getWidth();
	float writingHeight = rectSpaceBox.getHeight();

	Rectangle remaining = doc.getRenderer().getCurrentArea().getBBox();
	float yReal = remaining.getTop() + 2f;// orig 4f

	float sizet = 0;
	for (int i = 0; i < root.getChildren().size(); i++) {
	    IElement e = root.getChildren().get(i);

	    if (e.equals(space)) {

		break;
	    }

	    IRenderer ss = e.createRendererSubTree().setParent(doc.getRenderer());
	    
	    LayoutResult ss2 = ss.layout(new LayoutContext(new LayoutArea(1, new Rectangle(width, height))));

	    sizet += ss.getOccupiedArea().getBBox().getWidth();

	    System.out.println("width: " + width + " current: " + sizet);

	}
	float start =  sizet+doc.getLeftMargin();
	 if(isCentred) 
	     start = (width - getRealWidth(doc, root,width,height))/2+doc.getLeftMargin()+sizet;
	 
	
	
	Rectangle towr = new Rectangle(start, yReal, writingWidth, writingHeight);// sizet+doc.getLeftMargin()

	PdfCanvas pdfcanvas = new PdfCanvas(document.getFirstPage());
	Canvas canvas = new Canvas(pdfcanvas, towr);
	canvas.setTextAlignment(TextAlignment.CENTER);
	canvas.setHorizontalAlignment(HorizontalAlignment.CENTER);

	Paragraph paragraph = new Paragraph(str).setTextAlignment(TextAlignment.CENTER).setBold();//.setMultipliedLeading(0.9f);//setbold oprtional
	Div lineDiv = new Div();
	lineDiv.setVerticalAlignment(VerticalAlignment.MIDDLE);
	lineDiv.add(paragraph);

	   float fontSizeL = 0.0001f, fontSizeR= 10000;
	   int adjust = 0;
	while (Math.abs(fontSizeL - fontSizeR) > 1e-1) {
	    float curFontSize = (fontSizeL + fontSizeR) / 2;
	    lineDiv.setFontSize(curFontSize);
	    // It is important to set parent for the current element renderer to a root
	    // renderer
	    IRenderer renderer = lineDiv.createRendererSubTree().setParent(canvas.getRenderer());
	    LayoutContext context = new LayoutContext(new LayoutArea(1, towr));
	    if (renderer.layout(context).getStatus() == LayoutResult.FULL) {
		// we can fit all the text with curFontSize
		fontSizeL = curFontSize;
		 adjust++;
	           if (adjust>1)
	        	   towr.setHeight(towr.getHeight()-0.90f);
	    } else {
		fontSizeR = curFontSize;
	    }
	
	}

	lineDiv.setFontSize(fontSizeL);
	canvas.add(lineDiv);
	// border
	// PdfCanvas(document.getFirstPage()).rectangle(towr).setStrokeColor(ColorConstants.BLACK).stroke();

	canvas.close();

    }*/
    
    
    
/*
    public static float getRealWidth (Document doc, Paragraph root,float width,float height) {
	 float sizet = 0;
		
	 for(int  i = 0;i<root.getChildren().size();i++) {
		 IElement e =  root.getChildren().get(i);
		 
		
			IRenderer ss = e.createRendererSubTree().setParent(doc.getRenderer());
   	    LayoutResult ss2 = ss.layout(new LayoutContext(new LayoutArea(1, new Rectangle(width,height))));
  
   	    sizet +=ss.getOccupiedArea().getBBox().getWidth();

	
	    	
	    }
    return sizet;
}*/
    
    public static Paragraph getPhraseStrWithDots(final int dots, final String str) {
	final int strSize = str.length();
	final Paragraph sb = new Paragraph();
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

	Paragraph chDots = new Paragraph();

	for (int i = 0; i < dotsRemained; ++i) {
	    if (i == dotsRemained / 2) {

		sb.add(chDots);
		sb.add(new Text(str).addStyle(bold12nr).setTextRise(1.7f));
	    }
	    sb.add(".");
	}
	sb.add(chDots);
	return sb;
    }

    public static String getStrWithDots(final int dots, final String str) {
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
    
    public static String getStrWithDash(final int dots, final String str) {
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
	    sb.append("_");
	}
	return sb.toString();
    }

    public static Image getAntetLogo() {
	Image antetLogo = null;
	try {

	    antetLogo = new Image(
		    ImageDataFactory.create(Utils.getResourcePath() + "images" + File.separator + "antet2.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return antetLogo;
    }
    //stackoverflow solution 
    public static String capitalizeWords(final String words) {
	    return Stream.of(words.trim().split("\\s"))
	    .filter(word -> word.length() > 0)
	    .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
	    .collect(Collectors.joining(" "));
	}
    
    public static  String solveIfEmpty(String str) {

	if (str.isEmpty() || str.isBlank()) {
	    str = "________";
	}
	return str;
    }
    
    
    public static void setCheckInt(int code) {
	CODE = code;
    }
    
}
