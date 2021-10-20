package com.spectral369;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.UUID;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.Environment;
import org.vaadin.artur.helpers.LaunchUtil;

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
@PWA(name = "Birotica", shortName = "Birotica", description = "Site Birotica Dudestii-Vechi", iconPath = "images/log.jpg", offlineResources = {"images/log.jpg"})
@Meta(name = "Author", content = "spectral369") 
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@Push(value = PushMode.AUTOMATIC, transport = Transport.WEBSOCKET_XHR) 
public class Application extends SpringBootServletInitializer implements AppShellConfigurator {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5170116799200123108L;

	public static void main(String[] args) {
     //   LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(Application.class, args));
          SpringApplication app = new SpringApplication(Application.class);
        //  PDFHelper.init();//test
       //   PDFHelper.setCheckInt(checkIfOK(setGetId()));
          app.setBanner(new Banner() {
			
			@Override
			public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
				out.println("###########################################");
				out.println("#################Birotica #################");
				out.println("####Created by (github.com/)spectral369####");
				out.println("###########################################");
				
			}
		});
       LaunchUtil.launchBrowserInDevelopmentMode( app.run(args));
     
    }
	
	
	protected static String setGetId() {
	    String uniqueID = null;
	   // Properties properties =  new Properties();
	    File prop =  new File("/config/properties.config");
	    if(!prop.exists()) {
		FileOutputStream out;
		try {
		    out = new FileOutputStream(prop);
		    uniqueID =  UUID.randomUUID().toString();
		    out.write(UUID.randomUUID().toString().getBytes());
		    out.close();
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		
	    }
	    
	   try {
	    
	    FileInputStream io = new FileInputStream(prop);
	    uniqueID =  io.readAllBytes().toString();
	    io.close();
	    
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	   return uniqueID;
	    
	}
	
	protected static int checkIfOK(String ID) {
	    //TODO implement check
	    
	    return 0;
	}
	
	

}
