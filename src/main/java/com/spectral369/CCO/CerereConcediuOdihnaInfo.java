package com.spectral369.CCO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.vaadin.data.Binder;
import com.vaadin.data.ValueProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.Setter;
import com.vaadin.server.UserError;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class CerereConcediuOdihnaInfo extends CustomComponent implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "CerereConcediuOdihnaInfo";
	VerticalLayout content;
	HorizontalLayout backLayout;
	Button backbtn;
	HorizontalLayout titlelLayout;
	Button IarbaTitle;
	HorizontalLayout checkLayout;
	CheckBox complete;
	HorizontalLayout infoLayout;
	VerticalLayout infoPart1;
	VerticalLayout infoPart2;
	VerticalLayout infoPart3;
	VerticalLayout infoPart4;
	HorizontalLayout infoLayout2;
	VerticalLayout infoPart5;
	VerticalLayout infoPart6;
	VerticalLayout infoPart7;
	VerticalLayout infoPart8;


	TextField prenumeField;
	TextField numeField;
	TextField functiaField;
	TextField nrZileField;
	TextField anField;
	DateField dataStartField;
	DateField dataEndField;
	TextField inloctiitorField;
	

	HorizontalLayout generateLayout;
	Button generate;
	CerereConcediuOdihnaPDF pdf;
	Map<String, String> map;
	private Binder<CerereConcediuOdihnaInfo> binder;

	public CerereConcediuOdihnaInfo() {
		content = new VerticalLayout();
		map = new HashMap<String, String>();
		binder = new Binder<CerereConcediuOdihnaInfo>(CerereConcediuOdihnaInfo.class);
		backLayout = new HorizontalLayout();
		backLayout.setMargin(true);
		backbtn = new Button("Back", VaadinIcons.ARROW_CIRCLE_LEFT);
		backbtn.addStyleName("quiet");
		backbtn.addClickListener(evt -> UI.getCurrent().getNavigator().navigateTo("Index"));
		backLayout.addComponent(backbtn);
		content.addComponent(backLayout);
		content.setComponentAlignment(backLayout, Alignment.MIDDLE_LEFT);
		titlelLayout = new HorizontalLayout();
		IarbaTitle = new Button("Cerere Concediu", VaadinIcons.FILE_TEXT_O);
		IarbaTitle.setEnabled(false);
		IarbaTitle.setId("mainTitle");
		IarbaTitle.addStyleNames(new String[] { "borderless", "clearDisabled" });
		titlelLayout.addComponent(IarbaTitle);
		content.addComponent(titlelLayout);
		content.setComponentAlignment(titlelLayout, Alignment.MIDDLE_CENTER);
		checkLayout = new HorizontalLayout();
		(complete = new CheckBox("Cu completare de date ?!", false)).addValueChangeListener(evt -> {
			toggleVisibility();
			UI.getCurrent().push();
		});
		checkLayout.addComponent(complete);
		content.addComponent(checkLayout);
		content.setComponentAlignment(checkLayout, Alignment.MIDDLE_CENTER);
		(infoLayout = new HorizontalLayout()).setVisible(false);
		infoPart1 = new VerticalLayout();
		prenumeField = new TextField("Prenume:");
		prenumeField.setRequiredIndicatorVisible(true);
		binder.forField(prenumeField).asRequired()
		.withValidator(str -> str.length() >2, "Prenumele sa fie mai mare decat 2 caractere")

				.bind(new ValueProvider<CerereConcediuOdihnaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(CerereConcediuOdihnaInfo source) {
						return null;
					}
				}, new Setter<CerereConcediuOdihnaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CerereConcediuOdihnaInfo bean, String fieldvalue) {

					}
				});

		prenumeField.setResponsive(true);
		prenumeField.setComponentError((ErrorMessage) new UserError("Prenume Required !"));
		infoPart1.addComponent(prenumeField);
		infoLayout.addComponent(infoPart1);
		infoLayout.setComponentAlignment(infoPart1, Alignment.MIDDLE_CENTER);
		
		infoPart2 = new VerticalLayout();
		numeField = new TextField("Nume:");
		numeField.setRequiredIndicatorVisible(true);
		binder.forField(numeField).asRequired()
		.withValidator(str -> str.length() >2, "Numele sa fie mai mare decat 2 caractere")
				.bind(new ValueProvider<CerereConcediuOdihnaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(CerereConcediuOdihnaInfo source) {
						return null;
					}
				}, new Setter<CerereConcediuOdihnaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CerereConcediuOdihnaInfo bean, String fieldvalue) {

					}
				});

		numeField.setResponsive(true);
		numeField.setComponentError((ErrorMessage) new UserError("Nume Required !"));
		infoPart2.addComponent(numeField);
		infoLayout.addComponent(infoPart2);
		infoLayout.setComponentAlignment(infoPart2, Alignment.MIDDLE_CENTER);
		
		infoPart3 = new VerticalLayout();
		functiaField = new TextField("Functia:");
		
		functiaField .setRequiredIndicatorVisible(true);
		binder.forField(functiaField).asRequired()
		.withValidator(str -> str.length() >2, "Functia necesita mai mult de 2 caractere")

				.bind(new ValueProvider<CerereConcediuOdihnaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(CerereConcediuOdihnaInfo source) {
						return null;
					}
				}, new Setter<CerereConcediuOdihnaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CerereConcediuOdihnaInfo bean, String fieldvalue) {

					}
				});

		functiaField.setResponsive(true);
		functiaField.setComponentError((ErrorMessage) new UserError("Functie required !"));
		infoPart3.addComponent(functiaField);
		infoLayout.addComponent(infoPart3);
		infoLayout.setComponentAlignment(infoPart3, Alignment.MIDDLE_CENTER);
		
		infoPart4 = new VerticalLayout();
		nrZileField = new TextField("Nr. Zile Concediu:");
		nrZileField.setRequiredIndicatorVisible(true);
		binder.forField(nrZileField).asRequired()

				.bind(new ValueProvider<CerereConcediuOdihnaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(CerereConcediuOdihnaInfo source) {
						return null;
					}
				}, new Setter<CerereConcediuOdihnaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CerereConcediuOdihnaInfo bean, String fieldvalue) {

					}
				});

		nrZileField.setResponsive(true);
		nrZileField.setComponentError((ErrorMessage) new UserError("Nr. Zile !"));
		infoPart4.addComponent(nrZileField);
		infoLayout.addComponent(infoPart4);
		infoLayout.setComponentAlignment(infoPart4, Alignment.MIDDLE_CENTER);
		///info 2
		infoLayout2 =  new HorizontalLayout();
		infoLayout2.setVisible(false);
		

		infoPart5 = new VerticalLayout();
		anField = new TextField("An Concediu:");
		anField.setRequiredIndicatorVisible(true);
		binder.forField(anField).asRequired()
				.bind(new ValueProvider<CerereConcediuOdihnaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(CerereConcediuOdihnaInfo source) {
						return null;
					}
				}, new Setter<CerereConcediuOdihnaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CerereConcediuOdihnaInfo bean, String fieldvalue) {

					}
				});

		anField.setResponsive(true);
		anField.setComponentError((ErrorMessage) new UserError("Zile Concediu din an Required !"));
		infoPart5.addComponent(anField);
		infoLayout2.addComponent(infoPart5);
		infoLayout2.setComponentAlignment(infoPart5, Alignment.MIDDLE_CENTER);
		
		
		infoPart6 = new VerticalLayout();
		dataStartField = new DateField("Data Start:");
		dataStartField.setDateFormat("dd-MM-yyyy");
		dataStartField.setRequiredIndicatorVisible(true);
		binder.forField(dataStartField).asRequired()

				.bind(new ValueProvider<CerereConcediuOdihnaInfo, LocalDate>() {

					private static final long serialVersionUID = 1L;

					@Override
					public LocalDate apply(CerereConcediuOdihnaInfo source) {
						return null;
					}
				}, new Setter<CerereConcediuOdihnaInfo, LocalDate>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CerereConcediuOdihnaInfo bean, LocalDate fieldvalue) {

					}
				});
		
		dataStartField.setResponsive(true);
		dataStartField.setComponentError((ErrorMessage) new UserError("Data de start Required !"));
		infoPart6.addComponent(dataStartField);
		infoLayout2.addComponent(infoPart6);
		infoLayout2.setComponentAlignment(infoPart6, Alignment.MIDDLE_CENTER);
		
		infoPart7 = new VerticalLayout();
		dataEndField= new DateField("Data Sfarsit:");
		dataEndField.setDateFormat("dd-MM-yyyy");
		dataEndField.setRequiredIndicatorVisible(true);
		binder.forField(dataEndField).asRequired()

				.bind(new ValueProvider<CerereConcediuOdihnaInfo, LocalDate>() {

					private static final long serialVersionUID = 1L;

					@Override
					public LocalDate apply(CerereConcediuOdihnaInfo source) {
						return null;
					}
				}, new Setter<CerereConcediuOdihnaInfo, LocalDate>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CerereConcediuOdihnaInfo bean, LocalDate fieldvalue) {

					}
				});
		
		dataEndField.setResponsive(true);
		dataEndField.setComponentError((ErrorMessage) new UserError("Data de incheiere Required !"));
		infoPart7.addComponent(dataEndField);
		infoLayout2.addComponent(infoPart7);
		infoLayout2.setComponentAlignment(infoPart7, Alignment.MIDDLE_CENTER);
		
	
		
		infoPart8 = new VerticalLayout();
		inloctiitorField = new TextField("Inlocuitor:");
		inloctiitorField.setRequiredIndicatorVisible(true);
		binder.forField(inloctiitorField).asRequired()
		.withValidator(str -> str.length() >2, "Nume inlocuitor sa fie mai mare decat 2 caractere")
				.bind(new ValueProvider<CerereConcediuOdihnaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(CerereConcediuOdihnaInfo source) {
						return null;
					}
				}, new Setter<CerereConcediuOdihnaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CerereConcediuOdihnaInfo bean, String fieldvalue) {

					}
				});

		inloctiitorField.setResponsive(true);
		inloctiitorField.setComponentError((ErrorMessage) new UserError("Inlocuitor Required !"));
		infoPart8.addComponent(inloctiitorField);
		infoLayout2.addComponent(infoPart8);
		infoLayout2.setComponentAlignment(infoPart8, Alignment.MIDDLE_CENTER);
		
		
		

	

		content.addComponent(infoLayout);
		content.setComponentAlignment(infoLayout, Alignment.MIDDLE_CENTER);
		content.addComponent(infoLayout2);
		content.setComponentAlignment(infoLayout2, Alignment.MIDDLE_CENTER);
		
	
		
		generateLayout = new HorizontalLayout();
		generate = new Button("Generate", VaadinIcons.FILE_PROCESS);
		generate.addStyleNames("primary", "friendly");
		generate.addClickListener(evt -> {
			//String output = input.substring(0, 1).toUpperCase() + input.substring(1);
			if (complete.getValue()) {
				String prenume = prenumeField.getValue().trim().substring(0,1).toUpperCase()+prenumeField.getValue().trim().substring(1);
				String nume = numeField.getValue().trim().substring(0,1).toUpperCase()+numeField.getValue().trim().substring(1);
				
				map.put("prenume", prenume);
				map.put("nume", nume);
				map.put("functia", functiaField.getValue().trim());
				map.put("nrZile", nrZileField.getValue());
				LocalDate dt = dataStartField.getValue();
				dt.format(DateTimeFormatter.ISO_LOCAL_DATE);
				map.put("ziStart", String.valueOf(dt.getDayOfMonth()));
				map.put("lunaStart", String.valueOf(dt.getMonthValue()));
				map.put("anStart", String.valueOf(dt.getYear()));
				dt = dataEndField.getValue();
				dt.format(DateTimeFormatter.ISO_LOCAL_DATE);
				map.put("ziEnd", String.valueOf(dt.getDayOfMonth()));
				map.put("lunaEnd", String.valueOf(dt.getMonthValue()));
				map.put("anEnd", String.valueOf(dt.getYear()));
				
				map.put("anConcediu", anField.getValue().trim());
				map.put("inlocuitor", inloctiitorField.getValue().trim());
			}
			prenumeField.clear();
			numeField.clear();
			functiaField.clear();
			anField.clear();
			nrZileField.clear();
			dataEndField.clear();
			dataStartField.clear();
			inloctiitorField.clear();
			pdf = new CerereConcediuOdihnaPDF(map);
			UI.getCurrent().getNavigator().navigateTo("CerereConcediuOdihnaPDF");
		});
		generateLayout.addComponent(generate);
		binder.addStatusChangeListener(event -> {
			System.out.println(binder.isValid());
			if (!complete.getValue() || binder.isValid()) {
				generate.setEnabled(true);
			} else {
				generate.setEnabled(false);
			}
		});
		content.addComponent(generateLayout);
		content.setComponentAlignment(generateLayout, Alignment.MIDDLE_CENTER);
		content.setMargin(false);
		setCompositionRoot(content);
		UI.getCurrent().getNavigator().addView("CerereConcediuOdihnaInfo", this);
	}

	private void toggleVisibility() {
		infoLayout.setVisible(!infoLayout.isVisible());
		infoLayout2.setVisible(!infoLayout2.isVisible());
		

		generate.setEnabled(!generate.isEnabled());
	}
}
