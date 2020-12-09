package com.spectral369.CEAF;


import java.util.HashMap;
import java.util.Map;

import com.spectral369.birotica.MyUI;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class CerereEmitereAutorizatieFunctionareInfo extends CustomComponent implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "CerereEmitereAutorizatieFunctionareInfo";
	VerticalLayout content;
	HorizontalLayout backLayout;
	Button backbtn;
	HorizontalLayout titlelLayout;
	Button IarbaTitle;
	HorizontalLayout checkLayout;
	CheckBox complete;
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
		backbtn = new Button("Back", VaadinIcons.ARROW_CIRCLE_LEFT);
		backbtn.addStyleName("quiet");
		backbtn.addClickListener(evt -> MyUI.navigator.navigateTo("Index"));
		backLayout.addComponent(backbtn);
		content.addComponent(backLayout);
		content.setComponentAlignment(backLayout, Alignment.MIDDLE_LEFT);
		titlelLayout = new HorizontalLayout();
		IarbaTitle = new Button("Cerere Autorizatie Functionare", VaadinIcons.FILE_TEXT_O);
		IarbaTitle.setEnabled(false);
		IarbaTitle.setId("mainTitle");
		IarbaTitle.addStyleNames(new String[] { "borderless", "clearDisabled" });
		titlelLayout.addComponent(IarbaTitle);
		content.addComponent(titlelLayout);
		content.setComponentAlignment(titlelLayout, Alignment.MIDDLE_CENTER);
		checkLayout = new HorizontalLayout();
		checkLayout.setEnabled(false);
		(complete = new CheckBox("Cu completare de date ?!", false)).addValueChangeListener(evt -> {
			toggleVisibility();
			UI.getCurrent().push();
		});
		checkLayout.addComponent(complete);
		content.addComponent(checkLayout);
		content.setComponentAlignment(checkLayout, Alignment.MIDDLE_CENTER);
		(infoLayout = new HorizontalLayout()).setVisible(false);
	

		generateLayout = new HorizontalLayout();
		generate = new Button("Generate", VaadinIcons.FILE_PROCESS);
		generate.addStyleNames("primary", "friendly");
		generate.addClickListener(evt -> {
			// String output = input.substring(0, 1).toUpperCase() + input.substring(1);
			
		
			pdf = new CerereEmitereAutorizatieFunctionarePDF(map);
			MyUI.navigator.navigateTo("CerereEmitereAutorizatieFunctionarePDF");
		});
		generateLayout.addComponent(generate);

		
		content.addComponent(generateLayout);
		content.setComponentAlignment(generateLayout, Alignment.MIDDLE_CENTER);
		content.setMargin(false);
		setCompositionRoot(content);
		MyUI.navigator.addView("CerereEmitereAutorizatieFunctionareInfo", this);
	}

	private void toggleVisibility() {
		infoLayout.setVisible(!infoLayout.isVisible());
	

		generate.setEnabled(!generate.isEnabled());
	}
}
