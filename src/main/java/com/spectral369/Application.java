package com.spectral369;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.Environment;
import org.vaadin.artur.helpers.LaunchUtil;

import com.spectral369.utils.PDFHelper;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Meta;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.shared.communication.PushMode;
import com.vaadin.flow.shared.ui.Transport;
import com.vaadin.flow.theme.Theme;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the * and some desktop browsers.
 *
 */
@SpringBootApplication
@Theme(value = "Birotica")
@PWA(name = "Birotica", shortName = "Birotica", description = "Site Birotica Dudestii-Vechi", iconPath = "images/log.jpg", offlineResources = {
	"images/log.jpg" })
@Meta(name = "Author", content = "spectral369")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@Push(value = PushMode.AUTOMATIC, transport = Transport.WEBSOCKET_XHR)
public class Application extends SpringBootServletInitializer implements AppShellConfigurator {

    /**
     * 
     */
    private static final long serialVersionUID = -5170116799200123108L;
    private static final String config = "config" + File.separator + "properties.config";

    public static void main(String[] args) {
	// LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(Application.class,
	// args));
	SpringApplication app = new SpringApplication(Application.class);

	//setGetId();

	app.setBanner(new Banner() {

	    @Override
	    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
		out.println("###########################################");
		out.println("#################Birotica #################");
		out.println("####Created by (github.com/)spectral369####");
		out.println("###########################################");

	    }
	});
	LaunchUtil.launchBrowserInDevelopmentMode(app.run(args));
	System.out.println("id =" + getid());
	ScheduledExecutorService scheduledService = Executors.newScheduledThreadPool(1);
	scheduledService.scheduleWithFixedDelay(new Runnable() {

	    @Override
	    public void run() {

		PDFHelper.setCheckInt(checkIfOK(getid()));

	    }
	}, 0, 1, TimeUnit.DAYS);
    }

    protected static String setGetId() {
	String uniqueID = null;
	// Properties properties = new Properties();
	File prop = new File(config);
	if (!prop.exists()) {

	    FileOutputStream out;
	    try {
		out = new FileOutputStream(prop);
		uniqueID = UUID.randomUUID().toString() /* + "->30" */;
		out.write(uniqueID.getBytes());
		out.close();
	    } catch (IOException e) {
		System.out.println("error in setgetid1");
		e.printStackTrace();
	    }

	}

	try {

	    FileInputStream io = new FileInputStream(prop);
	    uniqueID = new String(io.readAllBytes(), StandardCharsets.UTF_8);
	    io.close();

	} catch (IOException e) {
	    System.out.println("error in setgetid2");
	    e.printStackTrace();
	}
	return uniqueID;

    }

    protected static int checkIfOK( String ID ) {
	// TODO implement check
	/*
	 * File prop = new File(config); FileInputStream in = new FileInputStream(prop);
	 * String line = new String(in.readAllBytes(), StandardCharsets.UTF_8); String
	 * id = line.substring(0,line.indexOf("->")); in.close(); int days =
	 * Integer.parseInt(line.substring(line.length() - 2));
	 * 
	 * id = id.replace(String.valueOf(days), String.valueOf(days - 1));
	 * FileOutputStream out = new FileOutputStream(prop); out.write(id.getBytes());
	 * out.close(); System.out.println("days: " + String.valueOf(days));
	 */
	URL url = null;
	int days = 30;
	try {
	    url = new URL("http://freelancingpeter.eu:8000/params/"+ID);
	} catch (MalformedURLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
	    for (String line; (line = reader.readLine()) != null;) {
		System.out.println(line);
		days = Integer.parseInt(line.substring(line.lastIndexOf(":") + 1).trim());
	    }
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	System.out.println("->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+days);

	if (days > 10 && days < 15)
	    return 2;
	else if (days > 0 && days < 10)
	    return 1;
	else
	    return 0;
    }

    protected static String getid() {
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
	return machineId;
    }

}
