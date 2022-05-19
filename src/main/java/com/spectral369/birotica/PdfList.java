package com.spectral369.birotica;

import java.util.LinkedHashMap;

public class PdfList {
    
   
    public static final LinkedHashMap<String,String> pdfList =  new LinkedHashMap<String,String>();
    
    public  static void addFile(String id,String path) {
	pdfList.put(id, path);
    }
    
    public static void deleteFile(String id) {
	pdfList.remove(id);
    }

    public static String getPath(String id) {
	return pdfList.get(id);
    }
    public static Boolean isFilePresent(String id) {
	return pdfList.containsKey(id);
    }
    public static LinkedHashMap<String, String> getPDFList() {
	return pdfList;
    }

    

}
