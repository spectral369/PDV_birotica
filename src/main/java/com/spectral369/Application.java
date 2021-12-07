package com.spectral369;

import java.io.PrintStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.Environment;
import org.vaadin.artur.helpers.LaunchUtil;

import com.spectral369.utils.PDFHelper;
import com.spectral369.utils.Utils;
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
public class Application extends SpringBootServletInitializer implements AppShellConfigurator, ServletContextListener {

    /**
     * 
     */
    private static final long serialVersionUID = -8766732757774007948L;

    /**
     * 
     */


    public static void main(String[] args) {
	SpringApplication app = new SpringApplication(Application.class);
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
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
	ScheduledExecutorService scheduledService = Executors.newScheduledThreadPool(1, new ThreadFactory() {
	    public Thread newThread(Runnable r) {
		       Thread thread = new Thread(r);
		       thread.setDaemon(true);
		       return thread;
		    }
		});
	scheduledService.scheduleWithFixedDelay(new Runnable() {

	    @Override
	    public void run() {

		PDFHelper.setCheckInt(Utils.checkIfOK(Utils.getid()));

	    }
	}, 0, 4, TimeUnit.HOURS);
    }



 

}
