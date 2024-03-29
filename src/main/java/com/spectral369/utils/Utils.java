package com.spectral369.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.spectral369.birotica.PdfList;

public class Utils {

    public static final String FOLDER_PDF = "pdfs";
    private static final String CONFIG_FILENAME = "birotica.config";

    public static String getResourcePath() {

	// URL resource1 = Utils.class.getClassLoader().getResource("");
	String path = Utils.class.getClassLoader().getResource("").getPath();
	if (System.getProperty("os.name").toLowerCase().contains("win")) {
	    path = path.substring(1);
	}
	return path + "META-INF" + File.separator + "resources" + File.separator;
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

    public static String getResourcePath(Class<?> currentClass, String fileName) {
	String path = currentClass.getProtectionDomain().getCodeSource().getLocation().getPath();
	if (System.getProperty("os.name").toLowerCase().contains("win")) {
	    path = path.substring(1);
	}

	return path + "META-INF/resources/pdfs/" + fileName;
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

    public static int checkIfOK(String ID) {
	URL url = null;
	int days = 0;
	try {
	    url = new URL("http://freelancingpeter.eu:8000/params/" + ID);

	} catch (MalformedURLException e) {
	    e.printStackTrace();
	}
	try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
	    for (String line; (line = reader.readLine()) != null;) {
		days = Integer.parseInt(line.substring(line.lastIndexOf(":") + 1).trim());
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	System.out.println("Days remaining: " + days);

	if (days > 5 && days < 10)
	    return 2;
	else if (days > 0 && days <= 5)
	    return 1;
	else if (days < 1) {
	    return 3;
	} else
	    return 0;
    }

    public static String getid() {
	String OS = System.getProperty("os.name").toLowerCase();
	String machineId = null;
	if (OS.indexOf("win") >= 0) {
	    StringBuffer output = new StringBuffer();
	    Process process;
	    // String[] cmd = {"wmic", "csproduct", "get", "UUID"}; default i think requires
	    // admin priv
	    String[] cmd = { "cmd", "/c", "reg query", "HKLM\\SOFTWARE\\Microsoft\\Cryptography\\", "/v",
		    "MachineGuid" };
	    try {
		process = Runtime.getRuntime().exec(cmd);
		process.waitFor();
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line = "";
		while ((line = reader.readLine()) != null) {
		    // System.out.println(line);
		    // output.append(line + "\n");
		    if (line.contains("REG_SZ"))
			output.append(line.substring(line.indexOf("REG_SZ") + 6).trim());

		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    machineId = output.toString();
	} else if (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0) {
	    StringBuffer output = new StringBuffer();
	    Process process;
	    // String[] cmd = {"/bin/sh", "-c", "echo <root password> | sudo -S cat
	    // /sys/class/dmi/id/product_uuid"};//var 0
	    // String [] cmd = {"/bin/sh","-c", "lshw |grep -m1 'serial:'"}; //var 1
	    String[] cmd = { "/bin/sh", "-c", "cat /var/lib/dbus/machine-id" }; // var 2
	    try {
		process = Runtime.getRuntime().exec(cmd);
		process.waitFor();
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line = "";
		while ((line = reader.readLine()) != null) {
		    output.append(line.trim() + "\n");
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    machineId = output.toString();
	}

	return getMachineName().concat("_").concat(machineId);
    }

    public static String getMachineName() {
	String hostname = new String("unknown");
	try {
	    InetAddress addr;
	    addr = InetAddress.getLocalHost();
	    hostname = addr.getHostName();
	} catch (UnknownHostException ex) {
	    System.out.println("Hostname can not be resolved");
	}

	return hostname.trim();
    }

    public static boolean saveSettings(Map<String, String[]> map) {
	String homedir = System.getProperty("user.home");
	File configFile = new File(homedir + File.separator + CONFIG_FILENAME);
	FileWriter fw;
	StringBuilder strBuilder = new StringBuilder();
	try {
	    fw = new FileWriter(configFile);

	    for (Map.Entry<String, String[]> item : map.entrySet()) {
		String key = item.getKey();
		String[] vals = item.getValue();
		StringBuilder sb = new StringBuilder();
		for (String sub_item : vals) {
		    sb.append(sub_item);
		    sb.append(";");
		}
		strBuilder.append(key + ":" + sb);
		strBuilder.append(System.lineSeparator());

	    }
	    fw.write(strBuilder.toString());

	    fw.close();

	} catch (IOException e) {

	    return false;
	}

	return true;
    }

    public static Map<String, String[]> getSettingsInfo() {

	Map<String, String[]> map = new HashMap<String, String[]>();
	String homedir = System.getProperty("user.home");
	File configFile = new File(homedir + File.separator + CONFIG_FILENAME);
	FileReader fr;
	try {
	    fr = new FileReader(configFile);

	    BufferedReader br = new BufferedReader(fr);
	    String line;
	    while ((line = br.readLine()) != null) {
		String[] data = line.split(":");
		String[] dataValues = data[1].split(";");

		map.put(data[0], dataValues);

	    }
	    br.close();
	    fr.close();
	} catch (IOException e) {
	    return null;
	}

	if (map.isEmpty())
	    return null;
	else

	    return map;
    }

}
