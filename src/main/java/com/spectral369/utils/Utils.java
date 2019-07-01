package com.spectral369.utils;

import java.io.File;


public class Utils {

	
	public static File getImage(String resource) {
		ClassLoader classLoader = Utils.class.getClassLoader();
		File file = new File(classLoader.getResource("/" + resource + ".png").getFile());
		if (file.exists())
			return file;
		else
			return null;

	}
}
