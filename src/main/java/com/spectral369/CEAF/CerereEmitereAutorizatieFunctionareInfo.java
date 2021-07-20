package com.spectral369.CEAF;

import java.util.HashMap;
import java.util.Map;

import com.spectral369.birotica.MainView;
import com.spectral369.utils.Utils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLayout;

public class CerereEmitereAutorizatieFunctionareInfo extends HorizontalLayout
	implements RouterLayout, AfterNavigationObserver {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "CerereEmitereAutorizatieFunctionareInfo";
    VerticalLayout content;
    HorizontalLayout backLayout;
    Button backbtn;
    HorizontalLayout titlelLayout;
    Button IarbaTitle;
    HorizontalLayout checkLayout;
    Checkbox complete;
    HorizontalLayout infoLayout;
    VerticalLayout infoPart1;

    HorizontalLayout generateLayout;
    Button generate;
    CerereEmitereAutorizatieFunctionarePDF pdf;
    Map<String, String> map;

    public CerereEmitereAutorizatieFunctionareInfo() {
	content = new VerticalLayout();
	map = new HashMap<String, String>();

	backLayout = new HorizontalLayout();
	backLayout.setMargin(true);
	backbtn = new Button("Back", VaadinIcon.ARROW_CIRCLE_LEFT.create());
	backbtn.getClassNames().add("quiet");
	backbtn.addClickListener(evt -> {
	    RouteConfiguration.forSessionScope().removeRoute(NAME);
	    UI.getCurrent().navigate(MainView.class);
	});
	backLayout.add(backbtn);
	content.add(backLayout);
	content.setAlignItems(Alignment.START);
	titlelLayout = new HorizontalLayout();
	IarbaTitle = new Button("Cerere Autorizatie Functionare", VaadinIcon.FILE_TEXT_O.create());
	IarbaTitle.setEnabled(false);
	IarbaTitle.setId("mainTitle");
	IarbaTitle.addClassName("clearDisabled");
	titlelLayout.add(IarbaTitle);
	content.add(titlelLayout);
	content.setAlignItems(Alignment.CENTER);
	checkLayout = new HorizontalLayout();
	checkLayout.setEnabled(false);
	(complete = new Checkbox("Cu completare de date ?!", false)).addValueChangeListener(evt -> {
	    toggleVisibility();
	    UI.getCurrent().push();
	});
	checkLayout.add(complete);
	content.add(checkLayout);
	content.setAlignItems(Alignment.CENTER);
	(infoLayout = new HorizontalLayout()).setVisible(false);

	generateLayout = new HorizontalLayout();
	generate = new Button("Generate", VaadinIcon.FILE_PROCESS.create());
	generate.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
	generate.getClassNames().add("frendly");
	generate.addClickListener(evt -> {

	    PDFCEAFCreator pdfcr = new PDFCEAFCreator(map, Utils.getTimeStr());
	    String fn = pdfcr.getID();

	    RouteConfiguration.forSessionScope().removeRoute(CerereEmitereAutorizatieFunctionarePDF.class);
	    RouteConfiguration.forSessionScope().setRoute(CerereEmitereAutorizatieFunctionarePDF.NAME,
		    CerereEmitereAutorizatieFunctionarePDF.class);
	    Map<String, String> sss = new HashMap<String, String>();
	    sss.put("tm", fn);

	    UI.getCurrent().navigate("CerereEmitereAutorizatieFunctionarePDF", QueryParameters.simple(sss));

	});
	generateLayout.add(generate);

	content.add(generateLayout);
	content.setAlignItems(Alignment.CENTER);
	content.setMargin(false);
	add(content);
    }

    private void toggleVisibility() {
	infoLayout.setVisible(!infoLayout.isVisible());

	generate.setEnabled(!generate.isEnabled());
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
	RouteConfiguration.forSessionScope().removeRoute(NAME);

    }
}