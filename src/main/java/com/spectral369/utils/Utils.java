package com.spectral369.utils;

import java.io.File;
import java.util.Random;


public class Utils {

	
	public static File getImage(String resource) {
		ClassLoader classLoader = Utils.class.getClassLoader();
		File file = new File(classLoader.getResource("/" + resource + ".png").getFile());
		if (file.exists())
			return file;
		else
			return null;

	}
	
	public static String getRandomStringSerial(String idetificator) {
		String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb =  new StringBuilder();
		sb.append(idetificator.toUpperCase());
		Random rd =  new Random();
		for(int i = 0;i<6;i++) {
		char letter = abc.charAt(rd.nextInt(abc.length()));
		sb.append(letter);
		}
		return sb.toString();
	  
	}
	
	
	
}
