package com.spectral369.utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFHelper {
	
	static final transient Font bold2  = FontFactory.getFont("Times-Roman", 10.0f, 1);
	
	public static Phrase getPlainStr(final String str) {
		Phrase phstr =  new Phrase(); 
		//phstr.add(Chunk.WHITESPACE);
		phstr.add(Chunk.TABBING);
		Chunk plainstr =  new Chunk("",bold2);
		plainstr.append(str);
		phstr.add(plainstr);
		phstr.add(Chunk.TABBING);
		
		return phstr;
	}
	
	public static Chunk getEmptySpace(int size) {
		Chunk ch =  new Chunk();
		for(int i = 0;i<=size;i++) {
			ch.append("\u00a0");
		}
		
		return new Chunk(ch);
	}
	
	
	public static void getPlainFillTest(String str,Document document,float y, float x1pos,
			float x2pos, PdfWriter writer,boolean withTab) {
		
			if(str.isEmpty() || str.isBlank()) {
				str =  "________";
			}

	        Rectangle rec2 = null;// new Rectangle(x3,y-6, x2-2, y-20);
	        if(!withTab)
	        	rec2 = new Rectangle(x2pos, y, x1pos-2,y+10);
	        else {
	        	rec2 = new Rectangle(x2pos+35, y, x1pos+33,y+10);
	        }
	        BaseFont bf = bold2.getBaseFont();
	        PdfContentByte cb = writer.getDirectContent();
	        float fontSize = getMaxFontSize(bf, str,(int)rec2.getWidth(), (int)rec2.getHeight());
	        Phrase phrase = new Phrase(str,  new Font(bf,  fontSize));
	        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, phrase,
	                // center horizontally
	                (rec2.getLeft() + rec2.getRight()) / 2,
	                // shift baseline based on descent
	                rec2.getBottom() - bf.getDescentPoint(str, fontSize),0);
	     
	        /*cb.saveState();//patrulaterul albastru
	        cb.setColorStroke(BaseColor.BLUE);
	        cb.rectangle(rec2.getLeft(), rec2.getBottom(), rec2.getWidth(), rec2.getHeight());
	        cb.stroke();
	        cb.restoreState();*/
	}
	
	
	
	
	
	
	public static Phrase getPhraseStrWithDots(final int dots, final String str) {
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
		final Chunk chStr = new Chunk("", bold2);
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
	
	
	
	//stackoverflow solution
	  private static float getMaxFontSize(BaseFont bf, String text, int width, int height){
		   // avoid infinite loop when text is empty
		    if(text.isEmpty()){
		        return 0.0f;
		    }


		    float fontSize = 0.1f;
		    while(bf.getWidthPoint(text, fontSize) < width){
		        fontSize += 0.1f;
		    }

		    float maxHeight = measureHeight(bf, text, fontSize);
		    while(maxHeight > height){
		        fontSize -= 0.1f;
		        maxHeight = measureHeight(bf, text, fontSize);
		    };

		    return fontSize;
		}

		public static  float measureHeight(BaseFont baseFont, String text, float fontSize) 
		{ 
		    float ascend = baseFont.getAscentPoint(text, fontSize); 
		    float descend = baseFont.getDescentPoint(text, fontSize); 
		    return ascend - descend; 
		}
	 	
	
	

}
