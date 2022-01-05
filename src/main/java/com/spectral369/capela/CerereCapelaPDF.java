
package com.spectral369.capela;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import com.spectral369.birotica.MainView;
import com.spectral369.birotica.PdfList;
import com.spectral369.utils.Utils;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.IFrame;
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
import com.vaadin.flow.server.StreamRegistration;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.VaadinSession;

public class CerereCapelaPDF extends HorizontalLayout
	implements RouterLayout, AfterNavigationObserver, BeforeLeaveObserver, BeforeEnterObserver {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "CerereCapelaPDF";
    public static String FNAME;
    VerticalLayout content;
    HorizontalLayout titleLayout;
    Button title;
    HorizontalLayout pdfLayout;
    HorizontalLayout backLayout;
    Button backbtn;
    String fileName = null;
    // PdfView pdfView = null;
    StreamResource streamResource = null;
    Div loading = null;
    static {
	CerereCapelaPDF.FNAME = "";
    }
    PDFCapelaCreator capela = null;

    public CerereCapelaPDF() {

	try {
	    loading = new Div();
	    loading.addClassName("loader");
	    VaadinSession session = VaadinSession.getCurrent();
	    @SuppressWarnings("unchecked")
	    HashMap<String, String> map = (HashMap<String, String>) session.getAttribute("map");

	    capela = new PDFCapelaCreator(map, Utils.getTimeStr());
	} finally {
	    loading.setVisible(false);
	}

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

	    System.out.println(Files.deleteIfExists(
		    Path.of(Utils.getResourcePath(CerereCapelaPDF.class, streamResource.getName())).normalize()));

	} catch (IOException e) {

	    e.printStackTrace();
	}
	if (PdfList.isFilePresent(fileName))
	    PdfList.deleteFile(fileName);
	RouteConfiguration.forSessionScope().removeRoute(NAME);
	RouteConfiguration.forSessionScope().removeRoute(CerereCapelaPDF.class);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {

	RouteConfiguration.forSessionScope().removeRoute(CerereCapelaPDF.class);
	RouteConfiguration.forSessionScope().removeRoute(NAME);
    }

    @Override
    public void beforeLeave(BeforeLeaveEvent event) {

	try {
	    System.out.println(Files.deleteIfExists(
		    Path.of(Utils.getResourcePath(CerereCapelaPDF.class, streamResource.getName()))));
	} catch (IOException e) {

	    e.printStackTrace();
	}
	RouteConfiguration.forSessionScope().removeRoute(CerereCapelaPDF.class);
	RouteConfiguration.forSessionScope().removeRoute(NAME);
	VaadinSession.getCurrent().setAttribute("map", "");

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {

	streamResource = new StreamResource(capela.getFileName(),
		() -> getClass().getResourceAsStream("/META-INF/resources/pdfs/" + capela.getFileName()));
	StreamRegistration registration = VaadinSession.getCurrent().getResourceRegistry()
		.registerResource(streamResource);
	IFrame iframe = new IFrame(registration.getResourceUri().toString());
	iframe.setSizeFull();
	pdfLayout.add(iframe);

    }
}