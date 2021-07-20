package com.spectral369.Utilaje;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import com.spectral369.birotica.MainView;
import com.spectral369.birotica.PdfList;
import com.spectral369.utils.PdfView;
import com.spectral369.utils.Utils;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;

public class InchiriereUtilajePDF extends HorizontalLayout implements RouterLayout, AfterNavigationObserver {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "InchiriereUtilajePDF";
    public static String FNAME;


    VerticalLayout content;
    HorizontalLayout titleLayout;
    Button title;
    HorizontalLayout pdfLayout;
    HorizontalLayout backLayout;
    Button backbtn;
    String fileName = null;
    PdfView pdfView = null;

    static {
	InchiriereUtilajePDF.FNAME = "";
    }

    public InchiriereUtilajePDF() {

	

	content = new VerticalLayout();
	titleLayout = new HorizontalLayout();

	title = new Button("Generated PDF", VaadinIcon.FILE_PRESENTATION.create());
	title.setEnabled(false);
	// this.title.addStyleNames(new String[] { "borderless", "clearDisabled" });
	title.getClassNames().add("borderless");
	title.getClassNames().add("clearDisabled");
	titleLayout.add(title);
	content.add(titleLayout);
	content.setAlignItems(Alignment.CENTER);
	pdfLayout = new HorizontalLayout();


	pdfView = new PdfView();

	pdfLayout.add(pdfView);
	pdfLayout.setSizeFull();
	pdfLayout.setId("pdfLayout");

	content.add(pdfLayout);

	content.setAlignItems(Alignment.CENTER);

	backLayout = new HorizontalLayout();

	backbtn = new Button("Close", VaadinIcon.CLOSE_CIRCLE_O.create());
	backbtn.getClassNames().add("frendly");

	RouterLink routerLink = new RouterLink("", MainView.class);
	routerLink.getElement().appendChild(backbtn.getElement());
	this.backbtn.addClickListener(evt -> {
	    if (fileName != null) {

		File pd = new File(Utils.getFullPath(fileName, false));
		try {
		    System.out.println("before del: " + pd.getAbsolutePath());
		    System.out.println("del1: " + Files.deleteIfExists(Path.of(pd.getAbsolutePath())));
		} catch (IOException e) {
		 
		    e.printStackTrace();
		}

		PdfList.deleteFile(fileName);

	    }
	    RouteConfiguration.forSessionScope().removeRoute(InchiriereUtilajePDF.class);
	    RouteConfiguration.forSessionScope().removeRoute(NAME);
	});
	backLayout.add(routerLink);
	content.add(backLayout);
	content.setAlignItems(Alignment.CENTER);
	content.setMargin(false);
	add(content);

	setSizeFull();

	UI.getCurrent().getPage().executeJs(
		"window.addEventListener('beforeunload', function (e) {    $0.$server.windowClosed(); var nAgt = navigator.userAgent;if ((verOffset=nAgt.indexOf('Chrome'))!=-1) { (e || window.event).returnValue = null ; } return; });",
		getElement());

    }

    @ClientCallable
    public void windowClosed() {
	System.out.println("Window closed");
	
	try {
	    System.out.println(Files.deleteIfExists(Path.of(Utils.getFullPath(fileName, false))));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	PdfList.deleteFile(fileName);
	RouteConfiguration.forSessionScope().removeRoute(NAME);
	RouteConfiguration.forSessionScope().removeRoute(InchiriereUtilajePDF.class);
    }



    @Override
    public void afterNavigation(AfterNavigationEvent event) {
	    Map<String, List<String>> parameters = event.getLocation().getQueryParameters().getParameters();
		
	    	if(fileName == null) {
	    	fileName = new String(parameters.get("tm").get(0));
	    	}
	    	if(!fileName.isEmpty()) {
	    	 
	    
	    	pdfView.add(Utils.getFullPath(fileName,true));
	    	}
		
		RouteConfiguration.forSessionScope().removeRoute(InchiriereUtilajePDF.class);
		RouteConfiguration.forSessionScope().removeRoute(NAME);

    }

}
