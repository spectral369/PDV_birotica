package com.spectral369.CSPH;

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
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.BeforeLeaveEvent;
import com.vaadin.flow.router.BeforeLeaveObserver;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;

public class CerereScutirePersoaneHandicapPDF extends HorizontalLayout
	implements RouterLayout, AfterNavigationObserver, BeforeLeaveObserver, BeforeEnterObserver {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "CerereScutirePersoaneHandicap";
    public static String FNAME;

    VerticalLayout content;
    HorizontalLayout titleLayout;
    Button title;
    HorizontalLayout pdfLayout;
    HorizontalLayout backLayout;
    Button backbtn;
    String fileName = null;
    PdfView pdfView = null;
    private Map<String, List<String>> parameters = null;

    static {
	CerereScutirePersoaneHandicapPDF.FNAME = "";
    }

    public CerereScutirePersoaneHandicapPDF() {

	content = new VerticalLayout();
	titleLayout = new HorizontalLayout();

	title = new Button("Generated PDF", VaadinIcon.FILE_PRESENTATION.create());
	title.setEnabled(false);
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
	backLayout.add(routerLink);
	content.add(backLayout);
	content.setAlignItems(Alignment.CENTER);
	content.setMargin(false);
	add(content);

	setSizeFull();

	UI.getCurrent().getPage()
		.executeJs("window.addEventListener('beforeunload', () => $0.$server.windowClosed()); ", getElement()); // does
															// not
															// trigger
															// on
															// tab
															// close
															// !!!!!!!
	UI.getCurrent().getPage().executeJs("window.addEventListener('unload', () => $0.$server.windowClosed()); ",
		getElement()); // does trigger on tab close !!!!!!!

    }

    @ClientCallable
    public void windowClosed() {
	System.out.println("Window closed");

	try {
	    String fullPath = Utils.getFullPath(fileName, false);
	    if (fullPath != null) {
		System.out.println(Files.deleteIfExists(Path.of(fullPath)));
	    }
	} catch (IOException e) {

	    e.printStackTrace();
	}
	if (PdfList.isFilePresent(fileName))
	    PdfList.deleteFile(fileName);
	RouteConfiguration.forSessionScope().removeRoute(NAME);
	RouteConfiguration.forSessionScope().removeRoute(CerereScutirePersoaneHandicapPDF.class);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
	// parameters = event.getLocation().getQueryParameters().getParameters();

	if (fileName == null) {
	    fileName = new String(parameters.get("tm").get(0));
	}
	/*
	 * if (!fileName.isEmpty()) {
	 * 
	 * pdfView.add(Utils.getFullPath(fileName, true)); }
	 */

	RouteConfiguration.forSessionScope().removeRoute(CerereScutirePersoaneHandicapPDF.class);
	RouteConfiguration.forSessionScope().removeRoute(NAME);
    }

    @Override
    public void beforeLeave(BeforeLeaveEvent event) {

	try {
	    System.out.println(Files.deleteIfExists(Path.of(Utils.getFullPath(fileName, false))));
	    if (PdfList.isFilePresent(fileName))
		PdfList.deleteFile(fileName);
	} catch (IOException e) {

	    e.printStackTrace();
	}
	RouteConfiguration.forSessionScope().removeRoute(CerereScutirePersoaneHandicapPDF.class);
	RouteConfiguration.forSessionScope().removeRoute(NAME);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {

	parameters = event.getLocation().getQueryParameters().getParameters();

	if (fileName == null) {
	    fileName = new String(parameters.get("tm").get(0));
	}
	if (!fileName.isEmpty()) {
	    // System.out.println("Before enter event "+fileName);
	    pdfView.add(Utils.getFullPath(fileName, true));

	}

    }
}