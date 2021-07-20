package com.spectral369.utils;

import java.io.File;
import java.net.URL;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import com.spectral369.birotica.PdfList;

public class Utils {

    public static final String FOLDER_PDF = "pdfs";

    public static String getResourcePath() {

	URL resource1 = Utils.class.getClassLoader().getResource("");
	return resource1.getPath() + "META-INF" + File.separator + "resources" + File.separator;
    }

    public static String getSaveFileLocation(String fileName) {

	URL resource = Utils.class.getClassLoader().getResource("");

	String path = resource.getPath();
	if (System.getProperty("os.name").toLowerCase().contains("win")) {
	    path = path.substring(1);
	}

	return path + "META-INF" + File.separator + "resources" + File.separator + Utils.FOLDER_PDF + File.separator
		+ fileName;

    }

    public static String getRandomStringSerial(String idetificator) {
	String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	StringBuilder sb = new StringBuilder();
	sb.append(idetificator.toUpperCase());
	Random rd = new Random();
	for (int i = 0; i < 6; i++) {
	    char letter = abc.charAt(rd.nextInt(abc.length()));
	    sb.append(letter);
	}
	return sb.toString();

    }

    public static String getTimeStr() {
	final ZonedDateTime time = ZonedDateTime.now();
	final DateTimeFormatter sdf2 = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
	String tm = time.format(sdf2);
	if (System.getProperty("os.name").toLowerCase().contains("win")) {
	    tm = tm.replaceAll("\\.", "_");
	    tm = tm.replaceAll(":", "_");
	}
	return tm;
    }

    public static String getFullPath(String id, boolean isPublic) {

	String fullP = PdfList.getPath(id);
	String fne = null;
	if (isPublic)
	    fne = fullP.substring(fullP.lastIndexOf(File.separator) + 1);
	else {
	    fne = fullP;
	}
	return fne;
    }

}
